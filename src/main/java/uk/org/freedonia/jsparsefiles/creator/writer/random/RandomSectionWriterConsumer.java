package uk.org.freedonia.jsparsefiles.creator.writer.random;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.function.Consumer;

import uk.org.freedonia.jsparsefiles.creator.DataSection;


/**
 * This Consumer is used to write a random data between the offsets specified in the DataSection
 * to the passed in SeekableByteChannel which is a channel to a sparse file.
 * @author jbeeton
 *
 */
public class RandomSectionWriterConsumer implements Consumer<DataSection> {
	
	
	private AlmostRandomStream randomStream = new AlmostRandomStream();
	private SeekableByteChannel channel;
	private static int DEFAULT_BUFFER_SIZE = 4096;
	
	public RandomSectionWriterConsumer( SeekableByteChannel channel ) {
		this.channel = channel;
	}
	
	/**
	 * Returns a byte buffer. This buffer is either the default buffer size or the size of the 
	 * data section whichever is smaller.
	 * @param t
	 * @return
	 */
	private byte[] getBuffer( DataSection t ) {
		long sectionSize = getSectionSize( t );
		if ( sectionSize > DEFAULT_BUFFER_SIZE ) {
			return new byte[DEFAULT_BUFFER_SIZE];
		} else {
			return new byte[(int) sectionSize];
		}
	}
	
	@Override
	public void accept( DataSection t ) {
		try {
			channel.position( t.getStartOffset() );
			long sectionSize = getSectionSize( t );
			byte[] buffer = getBuffer( t );
			long bytesWritten = 0l;
			while ( bytesWritten < sectionSize ) {
				if ( buffer.length < sectionSize - bytesWritten ) {
					randomStream.read( buffer );
					writeBufferToChannel( buffer );
					bytesWritten += buffer.length;
				} else {
					int leftToRead = (int) (sectionSize - bytesWritten );
					randomStream.read( buffer, 0, leftToRead );
					writeBufferToChannel( Arrays.copyOf( buffer, leftToRead ) );
					bytesWritten +=leftToRead;
				}
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * writes the specified byte array to the the channel.
	 * @param buffer
	 * @throws IOException
	 */
	private void writeBufferToChannel(  byte[] buffer ) throws IOException {
		channel.write( ByteBuffer.wrap( buffer ) );
	}
	
	/**
	 * Returns the size of the data section.
	 * @param t
	 * @return
	 */
	private long getSectionSize( DataSection t ) {
		return t.getEndOffset() - t.getStartOffset();
	}
}
