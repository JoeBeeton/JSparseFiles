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
	public static final String HELP_NAME = "help";
	@SuppressWarnings("static-access")
	public static final Option HELP = OptionBuilder.withArgName( 
			HELP_NAME ).isRequired(false).withDescription("Prints the help message").create( HELP_NAME );
	public static final String OVERWRITE_NAME = "overwrite";
	@SuppressWarnings("static-access")
	public static final Option OVERWRITE = OptionBuilder.withArgName( 
			OVERWRITE_NAME ).isRequired(false).withDescription("When set and the file already exists, it will be overwritten.").create( HELP_NAME );
			
	
	public static Options getOptions() {
		Options opts = new Options();
		opts.addOption( SIZE );
		opts.addOption( PATH );
		opts.addOption( DATA_SECTIONS );
		opts.addOption( OVERWRITE );
		opts.addOption( HELP );
		return opts;
	}
	
	public static List<Option> getOptionsAsList() {
		return Arrays.asList( SIZE, PATH, DATA_SECTIONS, OVERWRITE, HELP );
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
	
	public static Option getOverWriteOption() {
		return OVERWRITE;
	}
	
	public static Option getHelpOption() {
		return HELP;
	}
	

}
