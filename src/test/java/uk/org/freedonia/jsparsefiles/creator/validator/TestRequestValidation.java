package uk.org.freedonia.jsparsefiles.creator.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import uk.org.freedonia.jsparsefiles.creator.DataSections;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;
import uk.org.freedonia.jsparsefiles.creator.TestBase;

public class TestRequestValidation extends TestBase {
	
	private void runPassValidationTest( SparseFileRequest request ) throws  ValidationException {
		RequestValidator validator = new RequestValidator();
		ValidationResult result = validator.validateRequest( request );
		assertNotNull( result );
		assertNotNull( result.getMessage() );
		assertTrue( result.getMessage().isEmpty() );
		assertTrue( result.isValid() );
	}
	
	private void runFailValidationTest( SparseFileRequest request ) throws  ValidationException {
		RequestValidator validator = new RequestValidator();
		ValidationResult result = validator.validateRequest( request );
		assertNotNull( result );
		assertNotNull( result.getMessage() );
		assertFalse( result.getMessage().isEmpty() );
		assertFalse( result.isValid() );
	}
	
	@Test
	public void testFileOverwriteFlagOffWithFileExists() throws IOException, ValidationException {
		Path file = Paths.get( getTestDir().toString(), "sparsefile.dat" );
		file.toFile().createNewFile();
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, false );
		runFailValidationTest( request );
	}
	
	@Test
	public void testFileOverWriteFlagOnWithFileExists() throws IOException, ValidationException {
		Path file = Paths.get( getTestDir().toString(), "sparsefile.dat" );
		file.toFile().createNewFile();
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, true );
		runPassValidationTest( request );
	}
	
	@Test
	public void testWithPathBeingSetToADirectoryWithOverwriteFlagOff() throws ValidationException {
		Path file = Paths.get( getTestDir().toString() );
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, false );
		runFailValidationTest( request );
	}

	@Test
	public void testWithPathBeingSetToADirectoryWithOverwriteFlagOn() throws ValidationException {
		Path file = Paths.get( getTestDir().toString() );
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, true );
		runFailValidationTest( request );
	}
	
	@Test
	public void testWithOverlappingDataSections() throws ValidationException {
		Path file = Paths.get( getTestDir().toString(), "sparsefile.dat" );
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, true, 
				new DataSections( 1000, 2000 ),
				new DataSections( 1500, 2500 ) );
		runFailValidationTest( request );
	}
	
	@Test
	public void testWithNegativeDataSections() throws ValidationException {
		Path file = Paths.get( getTestDir().toString(), "sparsefile.dat" );
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, true, 
				new DataSections( -1000l, 2000l ));
		runFailValidationTest( request );
	}
	
	@Test
	public void testWithDataSectionLargerThanStatedFileSize() throws ValidationException {
		Path file = Paths.get( getTestDir().toString(), "sparsefile.dat" );
		SparseFileRequest request = new SparseFileRequest( file, FileUtils.ONE_MB, true, 
				new DataSections( FileUtils.ONE_MB, 5*FileUtils.ONE_MB ));
		runFailValidationTest( request );
	}
	
	
}
