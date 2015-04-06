package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.function.Predicate;

import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if a DataSection has a negative offset.
 * @author jbeeton
 *
 */
public class DataSectionsOffsetNegativeCheck extends DataSectionCheckBase implements IValidator {

	@Override
	protected ValidationResult getInvalidResult( DataSection ds,
			SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append("Invalid data section found : " + ds.toString() );
		return new ValidationResult( msg.toString(), false );
	}

	@Override
	protected Predicate<DataSection> getDataSectionPredicate( SparseFileRequest request ) {
		return getNegativeDataSectionOffsetPredicate();
	}
	
	private static Predicate<DataSection> getNegativeDataSectionOffsetPredicate() {
		return ds->
		ds.getStartOffset() < 0 ||
		ds.getEndOffset() < 0;
	}
	


}
