package uk.org.freedonia.jsparsefiles.creator.writer.random;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.apache.commons.lang3.RandomUtils;


/**
 * Returns an almost random stream of bytes forever.
 * However to help denote the start and end of the sparseness in the file I've
 * excluded the NULL ( zero ) bytes from the stream. All Zeros are now 1's. Also -1 is used to denote the end of the
 * file in a stream so -1 is also replaced with 1.
 * @author jbeeton
 *
 */
public class AlmostRandomStream extends InputStream {

	LinkedList<Byte> almostRandomBuffer = new LinkedList<Byte>();
	
	
	@Override
	public int read() throws IOException {
		
		if ( almostRandomBuffer.isEmpty() ) {
			loadNewRandomyData();
		}
		return almostRandomBuffer.removeFirst();
	}
	
	/**
	 * loads a new random array of 4096 bytes into the linkedlist.
	 */
	private void loadNewRandomyData() {
		byte[] byteArray = RandomUtils.nextBytes(4096);
		for (int i = 0; i < byteArray.length; i++) {
			if ( byteArray[i] == (byte)0 || byteArray[i] == (byte)-1 ) {
				byteArray[i] = (byte)1;
			}
			almostRandomBuffer.add(byteArray[i]);
		}
	}

}
