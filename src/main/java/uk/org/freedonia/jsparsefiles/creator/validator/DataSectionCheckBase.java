package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.Optional;
import java.util.function.Predicate;

import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Base class used by the data section validators. Using this base class
 * all the validators have to do is supply the predicate required to invalidate
 * a DataSection as well as the failure message. e.g if a datasection passes the predicate then it
 * is an invalid data section.
 * @author jbeeton
 *
 */
public abstract class DataSectionCheckBase implements IValidator {

	@Override
	public ValidationResult validateRequest( SparseFileRequest request ) {
		if ( request.getSections() != null  ) {
			Optional<DataSection> optionalResult = 
					request.getSections().parallelStream().filter( getDataSectionPredicate( request ) ).findFirst();
			if ( optionalResult.isPresent() ) {
				return getInvalidResult( optionalResult.get(), request );
			} 
		}
		return new ValidationResult();
	}
	
	/**
	 * returns the ValidationResult used if the validation fails.
	 * @param ds
	 * @param request
	 * @return
	 */
	protected abstract ValidationResult getInvalidResult( DataSection ds, SparseFileRequest request );
	
	/**
	 * Returns the predicate used to test the DataSection in question. If the predicate passes the DataSection is invalid.
	 * @param request
	 * @return
	 */
	protected abstract Predicate<DataSection> getDataSectionPredicate( SparseFileRequest request );


	


}
