package uk.org.freedonia.jsparsefiles.creator;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;

public class SparseFileValidatorUtils {
	
	private interface ByteValidator {
		boolean isByteValid( byte b );
	}
	
	private static ByteValidator getZeroValidator() {
		return new ByteValidator() {
			@Override
			public boolean isByteValid(byte b) {
				boolean	isValid = false;
				if ( b == 0 ) {
					isValid = true;
				}
				return isValid;
			}
		};
	}
	
	private static ByteValidator getNotZeroValidator() {
		return new ByteValidator() {
			@Override
			public boolean isByteValid(byte b) {
				boolean	isValid = false;
				if ( b != 0 ) {
					isValid = true;
				}
				return isValid;
			}
		};
	}
	
	public static void validateDataSectionsWrittenCorrectly( SparseFileRequest request ) {
		request.getSections().parallelStream().forEach( getNotNullConsumer( request ) );
	}
	
	
	private static Consumer<DataSection> getNotNullConsumer( final SparseFileRequest request ) {
		return new Consumer<DataSection>(){
			@Override
			public void accept( DataSection t ) {
				try {
					testThatFileIsNotNullBetween( request.getPath().toFile(), t.getStartOffset(), t.getEndOffset() );
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
		};
	}
	
	public static void testThatFileIsZerosBetween( File sparseFile, long startOffset, long endOffset ) throws FileNotFoundException, IOException {
		validateFile( sparseFile, startOffset, endOffset, getZeroValidator() );
	}
	
	public static void testThatFileIsNotNullBetween( File sparseFile, long startOffset, long endOffset ) throws FileNotFoundException, IOException {
		validateFile( sparseFile, startOffset, endOffset, getNotZeroValidator() );
	}
	
	private static void validateFile( File sparseFile, long startOffset, long endOffset, ByteValidator validator ) throws FileNotFoundException, IOException {
		try ( InputStream is = new FileInputStream( sparseFile ) ) {
			IOUtils.skip( is, startOffset );
			long currentOffset = startOffset;
			int i;
			// This is slower than reading a byte array but easier to code. If tests turn out to be 
			// slow this can be changed then.
			while ( ( i = is.read() )  != -1 && currentOffset != endOffset ) {
				if ( !validator.isByteValid( (byte)i ) ) {
					fail( "Found invalid byte at offset : " + currentOffset );
				}
				currentOffset++;
			}
		}
	}
	
	

}
