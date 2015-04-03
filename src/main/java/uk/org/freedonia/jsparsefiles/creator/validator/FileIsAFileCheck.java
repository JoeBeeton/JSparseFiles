package uk.org.freedonia.jsparsefiles.creator.validator;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * Validator used to check if the file specified is a file. Although this check can only occur
 * if there is a file system object that exists at the specified path.
 * @author jbeeton
 *
 */
public class FileIsAFileCheck implements IValidator {

	@Override
	public ValidationResult validateRequest( SparseFileRequest request ) {
		if ( request.getPath() != null && request.getPath().toFile().exists() && !request.getPath().toFile().isFile() ) {
			return getInvalidRequestMsg( request );
		}
		return new ValidationResult();
	}
	
	private ValidationResult getInvalidRequestMsg( SparseFileRequest request ) {
		StringBuilder msg = new StringBuilder();
		msg.append( "File : " );
		msg.append( request.getPath().toString() );
		if ( request.getPath().toFile().isDirectory() ) {
			msg.append(" is a directory.");
		} else {
			msg.append(" is not a regular file.");
		}
		return new ValidationResult( msg.toString(), false );
	}

}
