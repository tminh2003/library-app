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
	
	@Override
	public void execute(String query) {
		query += " ";
		
		String alias = query.substring(0, query.indexOf(" "));
		if(!allCommandAliases.contains(alias)) {
			throw new NoSuchCommandAddedException();
		}
		
		super.execute(query.substring(0, query.length()-1));
	}
	
}
