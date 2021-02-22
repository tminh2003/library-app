package minhTo.libraryApp;

import java.util.ArrayList;

import commandLine.Command;
import commandLine.CommandList;
import minhTo.libraryApp.exception.NoSuchCommandAddedException;

public class ErrorAwareCommandList extends CommandList{
	
	private ArrayList<String> allCommandAliases;
	
	public ErrorAwareCommandList() {
		super();
		allCommandAliases = new ArrayList<String>();
	}
	
	@Override
	public void register(Command command) {
		super.register(command);
		allCommandAliases.add(command.getAlias());
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

		query += " ";
		//Add space for when command doesn't need a param: ("[command1]"). 
		//												vs ("[command2] [param]")
		//Command 1 has no space behind command name. We add a space so the next statement would
		//fetch the alias for either commands.
		
		String alias = query.substring(0, query.indexOf(" "));
		
		if(!allCommandAliases.contains(alias)) {
			throw new NoSuchCommandAddedException(alias);
		}
		
		
		
		super.execute(query.substring(0, query.length()-1));
	}
	
}
