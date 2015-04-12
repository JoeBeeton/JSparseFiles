package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.cmd.options.CMDOptions;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * The SparseFileRequestParser takes the arguments from the main() method and constructs and returns a 
 * SparseFileRequest from the values in the command line arguments.
 * @author jbeeton
 *
 */
public class SparseFileRequestParser {
	
	/**
	 * takes the command line arguments and constructs and returns a SparsrFileRequest from those
	 * arguments
	 * @param args the command line arguments
	 * @return a SparseFileRequest containing the values from the arguments
	 * @throws ParseException if not all of the required arguments are passed.
	 */
	public SparseFileRequest getRequestFromCMDArgs( String[] args ) throws ParseException {
		SparseFileRequest request = new SparseFileRequest();
		Options options = CMDOptions.getOptions();
		CommandLineParser parser = getCMDParser();
		CommandLine cmdLine = parser.parse(options, args, true);
		for ( Option opt : cmdLine.getOptions() ) {
			OptionParserFactory.getParserFor(opt).optionPopulator(request, opt, cmdLine);
		}
		return request;
	}
	
	/**
	 * Returns The CommandLineParser implementation, currently this is just the BasicParser.
	 * @return
	 */
	private CommandLineParser getCMDParser() {
		return new BasicParser();
	}

}
