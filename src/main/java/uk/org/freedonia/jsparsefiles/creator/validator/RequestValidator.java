package uk.org.freedonia.jsparsefiles.creator.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * RequestValidator checks the SparseFileRequest to ensure that the values passed are valid based on several rules.
 * @author jbeeton
 *
 */
public class RequestValidator {
	
	/**
	 * Validates the specified SparseFileRequest and returns a ValidationResult containing the results of the various
	 * checks made.
	 * @param request
	 * @return
	 * @throws ValidationException
	 */
	public ValidationResult validateRequest( SparseFileRequest request ) throws ValidationException {
		Collection<ValidationResult> validationResults =  Collections.synchronizedCollection( new ArrayList<ValidationResult>() );
		Consumer<IValidator> validatorConsumer = getValidatorExecutor( request, validationResults );
		getValidators().parallelStream().forEach( validatorConsumer );
		return getCollatedResults( validationResults );
	}

	
	
	/**
	 * Returns the list of IValidators used to validate the SparseFileRequest
	 * @return
	 */
	private List<IValidator> getValidators() {
		return Arrays.asList( 
				new PathIsNotNullCheck(),
				new FileExistsCheck(), 
				new FileIsAFileCheck(),
				new DataSectionsStartGreaterThanEndCheck(),
				new DataSectionsOffsetNegativeCheck(),
				new DataSectionsLargerThanFileSizeCheck() );
	}
	
	/**
	 * Collates the specified results into a single ValidationResult
	 * @param validationResults
	 * @return
	 */
	private ValidationResult getCollatedResults( Collection<ValidationResult> validationResults ) {
		ValidationResult collatedResult = new ValidationResult();
		validationResults.stream().filter( vs-> !vs.isValid() ).forEach( collateResultsConsumer( collatedResult ) );
		return collatedResult;
	}
	
	/**
	 * Consumer used to add the ValidationResult into the passed in collatedResult if the validation failed.
	 * @param collatedResult
	 * @return
	 */
	private Consumer<ValidationResult> collateResultsConsumer( final ValidationResult collatedResult ) {
		return new Consumer<ValidationResult>() {
			@Override
			public void accept(ValidationResult t) {
				collatedResult.setValid(false);
				StringBuilder msg = new StringBuilder();
				if ( !collatedResult.getMessage().isEmpty() ) {
					msg.append(collatedResult.getMessage());
					msg.append("\n");
				}
				msg.append( t.getMessage() );
				collatedResult.setMessage( msg.toString() );
			}
		};
	}

	/**
	 * Consumer used to run the validation and add the results to the passed in collection.
	 * @param request
	 * @param results
	 * @return
	 */
	private Consumer<IValidator> getValidatorExecutor( final SparseFileRequest request, final Collection<ValidationResult> results ) {
		return new Consumer<IValidator>() {
			@Override
			public void accept(IValidator t) {
				results.add( t.validateRequest( request ) );
			}
		};
	}
	
	
	

}
