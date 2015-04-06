package uk.org.freedonia.jsparsefiles.creator;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseFileRequest {

	private Path path;
	private long size;
	private List<DataSection> sections = new ArrayList<>();
	private boolean overwriteExistingFile = false;

	public SparseFileRequest( Path path, long size, DataSection ...sections ) {
		this.setPath( path );
		this.setSize( size );
		this.setSections( sections );
	}
	
	public SparseFileRequest( Path path, long size, boolean overwriteExistingFile, DataSection ...sections ) {
		this.setPath( path );
		this.setSize( size );
		this.setSections( sections );
		this.setOverwriteExistingFile( overwriteExistingFile );
	}

	public Path getPath() {
		return path;
	}

	public void setPath( Path path ) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize( long size ) {
		this.size = size;
	}

	public List<DataSection> getSections() {
		return sections;
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

	public void setSections( List<DataSection> sections ) {
		this.sections = sections;
	}

	public void setSections( DataSection[] sections ) {
		this.sections.addAll( Arrays.asList( sections ) );
	}

	public boolean isOverwriteExistingFile() {
		return overwriteExistingFile;
	}

	public void setOverwriteExistingFile(boolean overwriteExistingFile) {
		this.overwriteExistingFile = overwriteExistingFile;
	}
	
}
