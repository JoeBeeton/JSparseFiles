package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class HelpPrinter {

	private static final int WIDTH = 100;
	
	public void printHelp( Options options ) {
		HelpFormatter usageFormatter = new HelpFormatter();  
		usageFormatter.printHelp( WIDTH, getCmdLineSyntax(), getHeader(),options, getFooter());
	}
	
	private String getHeader() {
		StringBuilder msg = new StringBuilder();
		msg.append("JSparseFiles is used to generate sparse files in a platform independent way.");
		return msg.toString();
	}
	
	private String getFooter() {
		StringBuilder msg = new StringBuilder();
		return msg.toString();
	}

	private String getCmdLineSyntax() {
		StringBuilder msg = new StringBuilder();
		msg.append("java -jar jsparsefiles.jar  -size 1659218604 -path c:/path/to/file/to/create/sparse.dat");
		return msg.toString();
	}
	
	
	
}
