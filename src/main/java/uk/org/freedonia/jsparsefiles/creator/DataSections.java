package uk.org.freedonia.jsparsefiles.creator;

public class DataSections {
	
	private long startOffset;
	private long endOffset;

	public DataSections( long startOffset, long endOffset ) {
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
