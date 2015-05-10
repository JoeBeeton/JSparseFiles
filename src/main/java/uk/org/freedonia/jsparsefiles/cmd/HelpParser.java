package uk.org.freedonia.jsparsefiles.cmd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;

public class HelpParser implements OptionParser {

	@Override
	public void optionPopulator( SparseFileRequest request, Option opt,
			CommandLine cmdLine ) throws ParseException {
		throw new ParseException("");
	}

}
