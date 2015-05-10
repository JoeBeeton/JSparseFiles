package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.cmd.options.CMDOptions;

/**
 * OptionParserFactory is used to return the correct OptionParser for the specified Option.
 * @author jbeeton
 *
 */
public class OptionParserFactory {
	
	/**
	 * returns the correct OptionParser for the passed in Option.
	 * @param opt
	 * @return
	 * @throws ParseException if no OptionParser is available for the specified Option.
	 */
	public static OptionParser getParserFor( Option opt ) throws ParseException {
		OptionParser parser = null;
		switch ( opt.getOpt() ) {
			case CMDOptions.HELP_NAME : {
				parser = new DataSectionOptionParser();
				break;
			}
			case CMDOptions.SIZE_NAME : {
				parser = new SizeOptionParser();
				break;
			}
			case CMDOptions.PATH_NAME : {
				parser = new PathOptionParser();
				break;
			}
			case CMDOptions.DATA_SECTIONS_NAME : {
				parser = new DataSectionOptionParser();
				break;
			} case CMDOptions.OVERWRITE_NAME : {
				parser = new OverwriteOptionParser();
				break;
			} default : {
				throw new ParseException( "Unknown Option type : " + opt.getOpt() );
			}
		}
		return parser;
	}

}
