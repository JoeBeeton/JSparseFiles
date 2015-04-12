package uk.org.freedonia.jsparsefiles.creator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * SparseFileRequest is the VO containing the values required to generate a sparse file. 
 * @author jbeeton
 *
 */
public class SparseFileRequest {

	private Path path;
	private long size;
	private List<DataSection> sections = new ArrayList<>();
	private boolean overwriteExistingFile = false;
	
	/**
	 * Constructs an empty SparseFileRequest
	 */
	public SparseFileRequest() {};

	/**
	 * Constructs a new Sparse File Request with the specified values.
	 * @param path the location of the file to be generated.
	 * @param size the size of the sparse file to be generated.
 	 * @param sections the optional data sections specifying the locations within the sparse file that will
 	 * be filled with real data.
	 */
	public SparseFileRequest( Path path, long size, DataSection ...sections ) {
		this.setPath( path );
		this.setSize( size );
		this.setSections( sections );
	}
	
	/**
	 * Constructs a new Sparse File Request with the specified values.
	 * @param path the location of the file to be generated.
	 * @param size the size of the sparse file to be generated.
	 * @param overwriteExistingFile flag to say whether if an existing file exists at the specified location
	 * should it be overwritten
 	 * @param sections the optional data sections specifying the locations within the sparse file that will
 	 * be filled with real data.
	 */
	public SparseFileRequest( Path path, long size, boolean overwriteExistingFile, DataSection ...sections ) {
		this.setPath( path );
		this.setSize( size );
		this.setSections( sections );
		this.setOverwriteExistingFile( overwriteExistingFile );
	}

	/**
	 * Returns the Path to the sparse file.
	 * @return
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * Sets the path to the sparse file.
	 * @param path the location of the sparse file.
	 */
	public void setPath( Path path ) {
		this.path = path;
	}

	/**
	 * Returns the size of the sparse file.
	 * @return the size of the sparse file.
	 */
	public long getSize() {
		return size;
	}

	/**
	 * Sets the size of the sparse file.
	 * @param size the size of the sparse file.
	 */
	public void setSize( long size ) {
		this.size = size;
	}

	/**
	 * Returns the list of optional data sections to be generated within the sparse file.
	 * @return the possibly empty list of data sections.
	 */
	public List<DataSection> getSections() {
		return sections;
	}
	
	/**
	 * Sets the list of data sections.
	 * @param sections the list of data sections.
	 */
	public void setSections( List<DataSection> sections ) {
		this.sections = sections;
	}

	private void setSections( DataSection[] sections ) {
		getSections().addAll( Arrays.asList( sections ) );
	}

	/**
	 * Returns true if an existing file should be overwritten with the sparse file.
	 * @return true if overwrite is enabled else returns false.
	 */
	public boolean isOverwriteExistingFile() {
		return overwriteExistingFile;
	}

	/**
	 * sets whether an existing file should be overwritten.
	 * @param overwriteExistingFile true if the file should be overwritten.
	 */
	public void setOverwriteExistingFile(boolean overwriteExistingFile) {
		this.overwriteExistingFile = overwriteExistingFile;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (overwriteExistingFile ? 1231 : 1237);
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result
				+ ((sections == null) ? 0 : sections.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SparseFileRequest other = (SparseFileRequest) obj;
		if (overwriteExistingFile != other.overwriteExistingFile)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (sections == null) {
			if (other.sections != null)
				return false;
		} else if (!sections.equals(other.sections))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SparseFileRequest [path=" + path + ", size=" + size
				+ ", sections=" + sections + ", overwriteExistingFile="
				+ overwriteExistingFile + "]";
	}
	
	
	
}
