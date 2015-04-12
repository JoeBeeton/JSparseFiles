package uk.org.freedonia.jsparsefiles.cmd.options;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class CMDOptions {
	
	
	public static final String SIZE_NAME = "size";
	@SuppressWarnings("static-access")
	public static final Option SIZE = OptionBuilder.withArgName( 
			SIZE_NAME).isRequired(true).hasArg(true).withDescription("The size of the file to be created in bytes.").create(SIZE_NAME);
	public static final String PATH_NAME = "path";
	@SuppressWarnings("static-access")
	public static final Option PATH = OptionBuilder.withArgName( 
			PATH_NAME ).isRequired(true).hasArg(true).withDescription("The location of the file to be created.").create( PATH_NAME );
	public static final String DATA_SECTIONS_NAME = "datasections";
	@SuppressWarnings("static-access")
	public static final Option DATA_SECTIONS = OptionBuilder.withArgName( 
			DATA_SECTIONS_NAME ).isRequired(false).hasArgs().withDescription("The start and end offsets of the "
			+ "sections containing real random data").create( DATA_SECTIONS_NAME );
			
	
	public static Options getOptions() {
		Options opts = new Options();
		opts.addOption( SIZE );
		opts.addOption( PATH );
		opts.addOption( DATA_SECTIONS );
		
		return opts;
	}
	
	public static List<Option> getOptionsAsList() {
		return Arrays.asList( SIZE, PATH, DATA_SECTIONS );
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
