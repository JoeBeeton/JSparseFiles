package uk.org.freedonia.jsparsefiles.creator.validator;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * Implementations if the IValidator are used to check if the values in the SparseFileRequest are valid.
 * @author jbeeton
 *
 */
public interface IValidator {
	
	/**
	 * Validates the SparseFileRequest. 
	 * @param request
	 * @return
	 */
	public ValidationResult validateRequest( SparseFileRequest request );

}
