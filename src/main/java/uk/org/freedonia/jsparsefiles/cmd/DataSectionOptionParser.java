package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.creator.DataSection;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * DataSectionOptionParser is used to populate the SparseFileRequest's DataSection list with
 * values from the passed in arguments to the command line.
 * @author jbeeton
 *
 */
public class DataSectionOptionParser implements OptionParser {

	@Override
	public void optionPopulator( SparseFileRequest request, Option opt,
			CommandLine cmdLine ) throws ParseException {
		for ( String arg : cmdLine.getOptionValues( opt.getOpt() ) ) {
			DataSection ds = getSectionFromArg( arg );
			request.getSections().add( ds );
		}
	}
	
	private DataSection getSectionFromArg( String arg ) throws ParseException {
		try {
			return new DataSection( 
					Long.parseLong( arg.split(",")[0].trim() ), 
					Long.parseLong( arg.split(",")[1].trim() )
			);
		} catch ( NumberFormatException e ) {
			throw new ParseException( "Data Section number is invalid" + e.getMessage() );
		}
	}

}
