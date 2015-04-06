package uk.org.freedonia.jsparsefiles.creator;

import java.io.IOException;

import uk.org.freedonia.jsparsefiles.creator.validator.RequestValidator;
import uk.org.freedonia.jsparsefiles.creator.validator.ValidationException;
import uk.org.freedonia.jsparsefiles.creator.validator.ValidationResult;
import uk.org.freedonia.jsparsefiles.creator.writer.SparseFileGenerator;
import uk.org.freedonia.jsparsefiles.creator.writer.random.RandomSectionWriter;
 



/**
 * The SparseFileCreator is the entry point into the JSparseFiles library. It is passed a SparseFileRequest
 * and uses that information to generate a sparse file on the file system. Or throws an exception if that cannot
 * occur due to the request being invalid or an IO problem occurring.
 * @author jbeeton
 *
 */
public class SparseFileCreator {
	
	
	/**
	 * Creates a new sparse file based on the parameters in the SparseFileRequest.
	 * @param request the parameters used to create the sparse file.
	 * @throws IOException if a problem occurs during the creation of the sparse file.
	 * @throws ValidationException if a problem occurs during the validation of the SparseFileRequest
	 */
	public static void createSparseFile( SparseFileRequest request ) throws IOException, ValidationException {
		validateRequest( request );
		generateSparseFile( request );
		writeDataSections( request );
	}
	
	/**
	 * Validates the SparseFileRequest to ensure that the request is valid.
	 * @param request the request to be validated.
	 * @throws ValidationException if the request is not valid.
	 */
	private static void validateRequest( SparseFileRequest request ) throws ValidationException {
		RequestValidator validator = new RequestValidator();
		ValidationResult result = validator.validateRequest( request );
		if ( !result.isValid() ) {
			throw new ValidationException( result.getMessage() );
		}
	}
	
	/**
	 * Creates the sparse file. 
	 * @param request the request used to generate the file.
	 * @throws IOException if a IO problem occurs during the generation of the file.
	 */
	private static void generateSparseFile( SparseFileRequest request ) throws IOException {
		SparseFileGenerator gen = new SparseFileGenerator();
		gen.generateSparseFile( request );
	}
	
	/**
	 * Writes the random data sections to the file.
	 * @param request the request used to generate the file.
	 * @throws IOException if a IO problem occurs during the writing of the random data sections.
	 */
	private static void writeDataSections( SparseFileRequest request ) throws IOException {
		RandomSectionWriter writer = new RandomSectionWriter();
		writer.writeDataSections( request );
	}


	

	

}
