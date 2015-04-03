package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.function.Predicate;

import uk.org.freedonia.jsparsefiles.creator.DataSections;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if the start of an offset is greater than the end of an offset.
 * @author jbeeton
 *
 */
public class DataSectionsStartGreaterThanEndCheck extends DataSectionCheckBase implements IValidator {

	@Override
	protected ValidationResult getInvalidResult (DataSections ds,
			SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append( "Invalid data section found : " + ds.toString() );
		msg.append( ". Start offset is larger than the end offset : " );
		msg.append( ds.toString() );
		return new ValidationResult( msg.toString(), false );
	}

	@Override
	protected Predicate<DataSections> getDataSectionPredicate(
			SparseFileRequest request ) {
		return getStartLargerThanEndOffsetPredicate();
	}
	
	private static Predicate<DataSections> getStartLargerThanEndOffsetPredicate() {
		return ds->
			ds.getStartOffset() > ds.getEndOffset();
	}

}
