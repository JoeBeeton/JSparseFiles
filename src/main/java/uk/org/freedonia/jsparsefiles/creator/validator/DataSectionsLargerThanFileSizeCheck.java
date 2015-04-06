package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.function.Predicate;

import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if either the start or end offset is greater than the specified file size.
 * @author jbeeton
 *
 */
public class DataSectionsLargerThanFileSizeCheck extends DataSectionCheckBase implements IValidator {

	@Override
	protected ValidationResult getInvalidResult( DataSection ds,
			SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append( "Invalid data section found : " + ds.toString() );
		msg.append( ". Offset is larger than File Size of : " );
		msg.append( request.getSize() );
		return new ValidationResult( msg.toString(), false );
	}

	@Override
	protected Predicate<DataSection> getDataSectionPredicate( SparseFileRequest request ) {
		return getLargerThanFileSizePredicate( request );
	}
	
	
	private static Predicate<DataSection> getLargerThanFileSizePredicate( SparseFileRequest request ) {
		return ds->
		ds.getStartOffset() > request.getSize() ||
		ds.getEndOffset() > request.getSize();
	}

}
