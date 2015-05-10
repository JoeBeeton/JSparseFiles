package uk.org.freedonia.jsparsefiles.cmd;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

import uk.org.freedonia.jsparsefiles.cmd.options.CMDOptions;
import uk.org.freedonia.jsparsefiles.creator.SparseFileCreator;
import uk.org.freedonia.jsparsefiles.creator.SparseFileRequest;
import uk.org.freedonia.jsparsefiles.creator.validator.ValidationException;

public class SparseCMD {
	
	public static void main( String [] args ) {
		try {
			SparseFileRequest request = new SparseFileRequestParser().getRequestFromCMDArgs( args );
			SparseFileCreator.createSparseFile(request);
		} catch (ParseException | IOException | ValidationException e) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
			new HelpPrinter().printHelp( CMDOptions.getOptions() );
		}
	}

}
