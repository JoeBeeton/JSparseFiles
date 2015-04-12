package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;


/**
 * implementations of the OptionParser are used to populate a value in the SparseFileRequest.
 * @author jbeeton
 *
 */
public interface OptionParser {
	
	/**
	 * populates a value in the SparseFileRequest using the passed in arguments in the CommandLine
	 * @param request
	 * @param opt
	 * @param cmdLine
	 * @throws ParseException
	 */
	public void optionPopulator( SparseFileRequest request, Option opt, CommandLine cmdLine ) throws ParseException;

}
