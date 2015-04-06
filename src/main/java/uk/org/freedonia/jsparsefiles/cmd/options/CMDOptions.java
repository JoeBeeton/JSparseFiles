package uk.org.freedonia.jsparsefiles.cmd.options;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CMDOptions {
	
	private static Option SIZE = new Option( "size", true, "The size of the file to be created in bytes." );
	private static Option PATH = new Option( "path", true, "The location of the file to be created." );
	private static Option DATA_SECTIONS =  new Option( "datasections", false, "The start and end offsets of the "
			+ "sections containing real random data" );
	
	public static Options getOptions() {
		Options opts = new Options();
		opts.addOption( SIZE );
		opts.addOption( PATH );
		opts.addOption( DATA_SECTIONS );
		return opts;
	}
	
	public static Option getSizeOption() {
		return SIZE;
	}
	
	public static Option getPathOption() {
		return PATH;
	}
	
	public static Option getDataSectionsOption() {
		return DATA_SECTIONS;
	}
	

}
