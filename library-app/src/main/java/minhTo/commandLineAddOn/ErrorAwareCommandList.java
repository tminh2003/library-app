package minhTo.commandLineAddOn;

import java.util.Scanner;

import commandLine.Command;
import commandLine.CommandList;
import minhTo.libraryApp.exception.IncorrectNumberOfParamException;
import minhTo.libraryApp.exception.NoSuchCommandAddedException;

public class ErrorAwareCommandList extends CommandList{
	
	public ErrorAwareCommandList() {
		super();
	}
	
	/**
	 * 
	 * @param query the query to be executed
	 * @throws NoSuchCommandAddedException if you call a nonexistent command
	 */
	
	@Override
	public void execute(String query) {
		//Remove extraneous space. ex: ("     [command name]")
		while(query.charAt(0) == ' ') {
			query = query.substring(1);
		}

		Scanner queryScanner = new Scanner(query);
		
		//Add space for when command doesn't need a param: ("[command1]"). 
		//												vs ("[command2] [param]")
		//Command 1 has no space behind command name. We add a space so the next statement would
		//fetch the alias for either commands.
		
		String alias = queryScanner.next();
		Command expectedCommand = getCommand(alias);
		
		if(expectedCommand == null) {
			queryScanner.close();
			throw new NoSuchCommandAddedException(alias);
		}
		
		
		
		int expectedNumParam = 0;
		int actualNumParam = 0;
		
		Scanner expectedParamScanner = new Scanner(expectedCommand.getParam());
		expectedParamScanner.useDelimiter(",");
		
		while(expectedParamScanner.hasNext()) {
			expectedNumParam++;
			expectedParamScanner.next();
		}
		
		String trimmedParam = "";
		queryScanner.useDelimiter(",");
		while(queryScanner.hasNext()) {
			actualNumParam++;
			trimmedParam += queryScanner.next().trim() + ",";
		}
		
		if(trimmedParam.length() > 0 && trimmedParam.charAt(trimmedParam.length() - 1) == ',')
			trimmedParam = trimmedParam.substring(0, trimmedParam.length() - 1);
		
		if(expectedNumParam != actualNumParam) {
			queryScanner.close();
			expectedParamScanner.close();
			throw new IncorrectNumberOfParamException("command " + expectedCommand.getAlias() 
													+ " needs " + expectedNumParam + " parameter(s)"
													+ ". "  + actualNumParam + " provided instead.");
		}
		
		queryScanner.close();
		expectedParamScanner.close();
		
		
		super.execute(alias + " " + trimmedParam);
	}
	
	public Command getCommand(String alias) {
		for(Command command : allCommands) {
			if(alias.equals(command.getAlias())) {
				return command;
			}
		}
		
		return null;
	}
	
}
