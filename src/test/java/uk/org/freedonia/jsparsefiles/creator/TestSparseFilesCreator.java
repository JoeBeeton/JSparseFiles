package uk.org.freedonia.jsparsefiles.creator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import uk.org.freedonia.jsparsefiles.creator.validator.ValidationException;

public class TestSparseFilesCreator extends TestBase {
	

	@Test( expected=ValidationException.class )
	public void testCreateSparseFileWithFileAlreadyExistingAndOverwriteOff() throws IOException, ValidationException {
		Path testDir = getTestDir();
		Path testSparseFile = Paths.get( testDir.toString(), "testSparse.dat" );
		testSparseFile.toFile().createNewFile();
		SparseFileCreator.createSparseFile( 
				new SparseFileRequest( 
						testSparseFile, 
						1000, 
						false )
			);
	}
	
	@Test
	public void testCreateSparseFileWithFileAlreadyExistingAndOverwriteOn() throws IOException, ValidationException {
		Path testDir = getTestDir();
		Path testSparseFile = Paths.get( testDir.toString(), "testSparse.dat" );
		testSparseFile.toFile().createNewFile();
		long fileSize = 1000;
		SparseFileCreator.createSparseFile( 
				new SparseFileRequest( 
						testSparseFile, 
						fileSize, 
						true )
			);
		assertTrue( testSparseFile.toFile().isFile() );
		assertEquals( fileSize, testSparseFile.toFile().length() );
		SparseFileValidatorUtils.testThatFileIsZerosBetween( testSparseFile.toFile(), 0, fileSize );
	}
	
	@Test
	public void testCreateSparseFileOfSize() throws IOException, ValidationException {
		Path testDir = getTestDir();
		Path testSparseFile = Paths.get( testDir.toString(), "testSparse.dat" );
		long fileSize = FileUtils.ONE_MB;
		SparseFileCreator.createSparseFile( 
				new SparseFileRequest( 
						testSparseFile, 
						fileSize, 
						true )
				);
		assertTrue( testSparseFile.toFile().isFile() );
		assertEquals( fileSize, testSparseFile.toFile().length() );
		SparseFileValidatorUtils.testThatFileIsZerosBetween( testSparseFile.toFile(), 0, fileSize );
	}
	
	@Test
	public void testCreateSparseFileWithDataSection() throws IOException, ValidationException {
		Path testDir = getTestDir();
		Path testSparseFile = Paths.get( testDir.toString(), "testSparse.dat" );
		long fileSize = FileUtils.ONE_MB;
		SparseFileRequest request = new SparseFileRequest( 
				testSparseFile, 
				fileSize, 
				true, 
				new DataSection( 1024l, 2048l ) );
		SparseFileCreator.createSparseFile( request );
		assertTrue( testSparseFile.toFile().isFile() );
		assertEquals( fileSize, testSparseFile.toFile().length() );
		SparseFileValidatorUtils.validateDataSectionsWrittenCorrectly(request);
	}
	
	@Test
	public void testCreateSparseFileWithMultipleDataSections() throws IOException, ValidationException {
		Path testDir = getTestDir();
		Path testSparseFile = Paths.get( testDir.toString(), "testSparse.dat" );
		testSparseFile.toFile().createNewFile();
		long fileSize = FileUtils.ONE_MB;
		SparseFileRequest request = new SparseFileRequest( 
				testSparseFile, 
				fileSize, 
				true, 
				new DataSection( 1024l, 2048l ),
				new DataSection( 102400l, 204800l ));
		SparseFileCreator.createSparseFile( request );
		assertTrue( testSparseFile.toFile().isFile() );
		assertEquals( fileSize, testSparseFile.toFile().length() );
		SparseFileValidatorUtils.validateDataSectionsWrittenCorrectly(request);
	}
	
	
	

	
}
