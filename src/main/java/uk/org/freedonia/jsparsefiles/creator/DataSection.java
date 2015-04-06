package uk.org.freedonia.jsparsefiles.creator;


/**
 * A DataSection describes the location within the file of randomly generated data.
 * @author jbeeton
 *
 */
public class DataSection {
	
	private long startOffset;
	private long endOffset;

	/**
	 * Constructs a new DataSection.
	 * @param startOffset the start of the randomly generated data.
	 * @param endOffset the end of the randomly generated data.
	 */
	public DataSection( long startOffset, long endOffset ) {
		this.setStartOffset(startOffset);
		this.setEndOffset(endOffset);
	}

	public long getEndOffset() {
		return endOffset;
	}

	public void setEndOffset(long endOffset) {
		this.endOffset = endOffset;
	}

	public long getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(long startOffset) {
		this.startOffset = startOffset;
	}
	
	@Override
	public String toString() {
		return "DataSections [startOffset=" + startOffset + ", endOffset="
				+ endOffset + "]";
	}

}
