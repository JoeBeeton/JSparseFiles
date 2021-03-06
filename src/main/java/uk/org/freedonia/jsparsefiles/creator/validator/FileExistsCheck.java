package uk.org.freedonia.jsparsefiles.creator.validator;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * Validator used to check if the file exists and the overwrite flag is not set.
 * @author jbeeton
 *
 */
public class FileExistsCheck implements IValidator {

	@Override
	public ValidationResult validateRequest( SparseFileRequest request ) {
		if ( request.getPath() != null && request.getPath().toFile().isFile() && !request.isOverwriteExistingFile() ) {
			return getInvalidRequestMsg( request );
		}
		return new ValidationResult();
	}
	
	private ValidationResult getInvalidRequestMsg( SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append( "File : " );
		msg.append( request.getPath().toString() );
		msg.append(" exists and overwrite flag is not set.");
		return new ValidationResult( msg.toString(), false );
	}

}
