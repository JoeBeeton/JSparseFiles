package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.cmd.options.CMDOptions;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

public class SparseFileRequestParser {
	
	public SparseFileRequest getRequestFromCMDArgs( String[] args ) throws ParseException {
		Options options = CMDOptions.getOptions();
		return null;
	}
	
	private CommandLineParser getCMDParser() {
		return new GnuParser();
	}

}
