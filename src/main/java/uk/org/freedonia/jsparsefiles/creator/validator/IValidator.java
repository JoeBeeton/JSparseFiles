package uk.org.freedonia.jsparsefiles.creator.validator;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

public interface IValidator {
	
	public ValidationResult validateRequest( SparseFileRequest request );

}
