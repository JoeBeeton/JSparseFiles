package uk.org.freedonia.jsparsefiles.cmd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import uk.org.freedonia.jsparsefiles.cmd.options.CMDOptions;
import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;
import uk.org.freedonia.jsparsefiles.creator.TestBase;


public class TestSparseFileRequestParser extends TestBase {
	
	
	
	@Test( expected = ParseException.class )
	public void testWithNoSize() throws ParseException {
		SparseFileRequestParser parser = new SparseFileRequestParser();
		parser.getRequestFromCMDArgs( 
						getPathArgs( getSparseFilePath() ).toArray( new String[]{}) );
		
	}
	
	
	
	@Test( expected = ParseException.class )
	public void testWithNoPath() throws ParseException {
		SparseFileRequestParser parser = new SparseFileRequestParser();
		parser.getRequestFromCMDArgs( 
						getSizeArgs( 12345l ).toArray( new String[]{}) );
	}
	
	@Test
	public void testWithNoDataSections() throws ParseException {
		SparseFileRequest expectedRequest = new SparseFileRequest( getSparseFilePath(), 12334567l );
		SparseFileRequestParser parser = new SparseFileRequestParser();
		SparseFileRequest actualRequest = 
				parser.getRequestFromCMDArgs( getSizeAndPathArgs( expectedRequest.getSize(),
				expectedRequest.getPath() ).toArray( new String[]{} ) );
		assertNotNull( actualRequest );
		assertEquals( expectedRequest, actualRequest );
	}
	
	
	@Test
	public void testWithSingleDataSection() throws ParseException {
		SparseFileRequest expectedRequest = new SparseFileRequest( getSparseFilePath(), 12334567l, new DataSection( 1234,5678 ) );
		SparseFileRequestParser parser = new SparseFileRequestParser();
		SparseFileRequest actualRequest = 
				parser.getRequestFromCMDArgs( getSizePathAndDataSectionArgs( expectedRequest.getSize(),
				expectedRequest.getPath(), expectedRequest.getSections() ).toArray( new String[]{} ) );
		assertNotNull( actualRequest );
		assertEquals( expectedRequest, actualRequest );
	}
	
	
	private Path getSparseFilePath() {
		Path testDir = getTestDir();
		return Paths.get( testDir.toString(), "testSparse.dat" );
	}



	@Test
	public void testWithMultipleDataSections() throws ParseException {
		SparseFileRequest expectedRequest = new SparseFileRequest( getSparseFilePath(), 12334567l, new DataSection( 1234,5678 ),
				new DataSection( 10000, 20000 ) );
		SparseFileRequestParser parser = new SparseFileRequestParser();
		SparseFileRequest actualRequest = 
				parser.getRequestFromCMDArgs( getSizePathAndDataSectionArgs( expectedRequest.getSize(),
				expectedRequest.getPath(), expectedRequest.getSections() ).toArray( new String[]{} ) );
		assertNotNull( actualRequest );
		assertEquals( expectedRequest, actualRequest );
	}
	
	
	
	
	private List<String> getSizePathAndDataSectionArgs(long size, Path path,
			List<DataSection> sections) {
		List<String> args = getSizeAndPathArgs( size, path );
		for ( DataSection ds : sections ) {
			args.add( CMDOptions.getDataSectionsOption().getArgName() );
			args.add( ds.getStartOffset()+","+ds.getEndOffset() );
		}
		return args;
	}

	
	private List<String> getSizeAndPathArgs( long size, Path path ) {
		List<String> args = new ArrayList<>();
		args.addAll( getPathArgs( path ) );
		args.addAll( getSizeArgs( size ) );
		return args;
	}
	
	private List<String> getSizeArgs( long size ) {
		return Arrays.asList(CMDOptions.getSizeOption().getArgName(), ""+size );
	}
	
	private List<String> getPathArgs( Path path ) {
		return Arrays.asList(CMDOptions.getPathOption().getArgName(), path.toString() );
	}
	
	

}
