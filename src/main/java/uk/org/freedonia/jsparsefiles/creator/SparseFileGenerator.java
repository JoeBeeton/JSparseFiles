package uk.org.freedonia.jsparsefiles.creator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

/**
 * The SparseFileGenerator is used to generate the file in a hopefully sparse way.
 * However that is entirely dependent on the underlying os and file system.
 * @author jbeeton
 *
 */
public class SparseFileGenerator {
	
	/**
	 * generates a sparse file based on the values found in the specified request.
	 * @param request
	 * @throws IOException
	 */
	public void generateSparseFile( SparseFileRequest request ) throws IOException {
		OpenOption[] options = getOptions( request );
		try ( SeekableByteChannel channel = Files.newByteChannel( request.getPath(), options ) ) {
			if ( request.getSize() > 0 ) {
				// after repositioning need to write a single byte to ensure that the file
				// is given size.
				channel.position( request.getSize() -1 );
				channel.write(ByteBuffer.wrap(new byte[]{(byte)0}));
			}
		}
	}
	
	private OpenOption[] getOptions( SparseFileRequest request ) {
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

}
