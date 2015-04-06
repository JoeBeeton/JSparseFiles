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
