package uk.org.freedonia.jsparsefiles.creator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;


@Ignore
public class TestBase {
	
	@Before
	public void setup() throws IOException {
		FileUtils.deleteDirectory( getTestDir().toFile() );
	}
	
	
	private File getTmpDir() {
		return new File( System.getProperty("java.io.tmpdir") );
	}
	
	protected Path getTestDir() {
		File tmpDir = getTmpDir();
		Path testDir = Paths.get( 
				tmpDir.getAbsolutePath(),
				this.getClass().getSimpleName() );
		testDir.toFile().mkdirs();
		return testDir;
	}
	

}
