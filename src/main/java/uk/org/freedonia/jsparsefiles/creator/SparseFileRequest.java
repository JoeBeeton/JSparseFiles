package uk.org.freedonia.jsparsefiles.creator;

import java.nio.file.Path;

public class SparseFileRequest {

	private Path path;
	private long size;
	private DataSections[] sections;
	private boolean overwriteExistingFile = false;

	public SparseFileRequest( Path path, long size, DataSections ...sections ) {
		this.setPath( path );
		this.setSize( size );
		this.setSections( sections );
	}
	
	public SparseFileRequest( Path path, long size, boolean overwriteExistingFile, DataSections ...sections ) {
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

	public DataSections[] getSections() {
		return sections;
	}

	public void setSections( DataSections[] sections ) {
		this.sections = sections;
	}

	public boolean isOverwriteExistingFile() {
		return overwriteExistingFile;
	}

	public void setOverwriteExistingFile(boolean overwriteExistingFile) {
		this.overwriteExistingFile = overwriteExistingFile;
	}
	
}
