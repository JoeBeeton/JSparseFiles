package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.function.Predicate;

import uk.org.freedonia.jsparsefiles.creator.DataSections;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if a DataSection has a negative offset.
 * @author jbeeton
 *
 */
public class DataSectionsOffsetNegativeCheck extends DataSectionCheckBase implements IValidator {

	@Override
	protected ValidationResult getInvalidResult( DataSections ds,
			SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append("Invalid data section found : " + ds.toString() );
		return new ValidationResult( msg.toString(), false );
	}

	@Override
	protected Predicate<DataSections> getDataSectionPredicate( SparseFileRequest request ) {
		return getNegativeDataSectionOffsetPredicate();
	}
	
	private static Predicate<DataSections> getNegativeDataSectionOffsetPredicate() {
		return ds->
		ds.getStartOffset() < 0 ||
		ds.getEndOffset() < 0;
	}
	


}
