package uk.org.freedonia.jsparsefiles.creator.random;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import uk.org.freedonia.jsparsefiles.creator.writer.random.AlmostRandomStream;

public class TestAlmostRandomStream {
	
	@Test
	public void testThatNoZeroBytesAreInStream() throws IOException {
		try( InputStream randomStream = new AlmostRandomStream() ) {
			for ( int i = 0; i < FileUtils.ONE_MB; i++ ) {
				assertFalse( "Found 0 byte at position : " + i,
						randomStream.read() == 0 );
			}
		}
	}

}
