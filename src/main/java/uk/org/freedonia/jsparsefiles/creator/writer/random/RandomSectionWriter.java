package uk.org.freedonia.jsparsefiles.creator.writer.random;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.OpenOption;
import java.util.function.Consumer;

import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;
import uk.org.freedonia.jsparsefiles.creator.writer.SeekableChannelWriterBase;


/**
 * RandomSectionWriter writes random data to the locations held within the SparseFileRequest to 
 * the file.
 * @author jbeeton
 *
 */
public class RandomSectionWriter extends SeekableChannelWriterBase {
	
	/**
	 * Writes the Data Sections to the sparse file.
	 * @param request
	 * @throws IOException
	 */
	public void writeDataSections( SparseFileRequest request ) throws IOException {
		if ( !request.getSections().isEmpty() ) {
			OpenOption[] options = getOptions( request );
			try ( SeekableByteChannel channel = openByteChannel( request.getPath(), options ) ) {
				request.getSections().stream().forEach( getDSWriterConsumer( channel ) );
			}
		}
	}
	
	private Consumer<DataSection> getDSWriterConsumer( SeekableByteChannel channel ) {
		return new RandomSectionWriterConsumer( channel );
	}
	

}
