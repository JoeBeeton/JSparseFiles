package uk.org.freedonia.jsparsefiles.creator.writer;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * 
 * Base classed using by the writer classes to write out to the sparse file using a SeekableByteChannel
 * @author jbeeton
 *
 */
public class SeekableChannelWriterBase {

	
	protected OpenOption[] getOptions( SparseFileRequest request ) {
		if ( request.isOverwriteExistingFile() ) {
			return new OpenOption[]{
					StandardOpenOption.WRITE,
					StandardOpenOption.CREATE,
					StandardOpenOption.SPARSE
			};
		} else {
			return new OpenOption[]{
					StandardOpenOption.WRITE,
					StandardOpenOption.CREATE_NEW,
					StandardOpenOption.SPARSE
			};
		}
	}
	
	protected SeekableByteChannel openByteChannel( Path path, OpenOption[] options ) throws IOException {
		return Files.newByteChannel( path, options );
	}
	
	
}
