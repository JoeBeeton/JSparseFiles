package uk.org.freedonia.jsparsefiles.cmd;

import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * PathOptionParser is used to populate the SparseFileRequest's Path option
 * with the value passed in from the command line arguments.
 * @author jbeeton
 *
 */
public class PathOptionParser implements OptionParser {

	@Override
	public void optionPopulator(SparseFileRequest request, Option opt,
			CommandLine cmdLine) throws ParseException {
		String pathString =  cmdLine.getOptionValue( opt.getOpt() );
		if ( pathString != null ) {
			request.setPath( Paths.get( pathString ) );
		} else {
			throw new ParseException( "No path is specified" );
		}
	}

}
