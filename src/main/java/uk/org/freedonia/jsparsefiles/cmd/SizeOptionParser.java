package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

/**
 * SizeOptionParser is used to populate the SparseFileRequest's Size value from the arguments
 * passed into the application.
 * @author jbeeton
 *
 */
public class SizeOptionParser implements OptionParser {

	@Override
	public void optionPopulator( SparseFileRequest request, Option opt,
			CommandLine cmdLine ) throws ParseException {
		try {
			request.setSize( Long.parseLong( cmdLine.getOptionValue( opt.getOpt() ) ) );
		} catch ( NumberFormatException e ) {
			throw new ParseException( "Size is not a valid number " + e.getMessage() );
		}
	}

}
