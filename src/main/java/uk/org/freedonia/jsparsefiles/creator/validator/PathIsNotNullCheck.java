package uk.org.freedonia.jsparsefiles.creator.validator;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if the path is null.
 * @author jbeeton
 *
 */
public class PathIsNotNullCheck implements IValidator {

	@Override
	public ValidationResult validateRequest(SparseFileRequest request) {
		if ( request.getPath() == null ) {
			return getInvalidResult();
		}
		return new ValidationResult();
	}

	private ValidationResult getInvalidResult() {
		return new ValidationResult( "The path must have a value", false );
	}

}
