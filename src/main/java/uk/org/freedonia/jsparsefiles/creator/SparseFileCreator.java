package uk.org.freedonia.jsparsefiles.creator;

import java.io.IOException;

import uk.org.freedonia.jsparsefiles.creator.validator.RequestValidator;
import uk.org.freedonia.jsparsefiles.creator.validator.ValidationException;
import uk.org.freedonia.jsparsefiles.creator.validator.ValidationResult;
 
public class SparseFileCreator {
	
	public static void createSparseFile( SparseFileRequest request ) throws IOException, ValidationException {
		validateRequest( request );
		generateSparseFile( request );
	}
	
	private static void generateSparseFile( SparseFileRequest request ) throws IOException {
		SparseFileGenerator gen = new SparseFileGenerator();
		gen.generateSparseFile( request );
	}
	
	private static void validateRequest( SparseFileRequest request ) throws ValidationException {
		RequestValidator validator = new RequestValidator();
		ValidationResult result = validator.validateRequest( request );
		if ( !result.isValid() ) {
			throw new ValidationException( result.getMessage() );
		}
	}
	

}
