import commandLine.Command;

public class MockCommandFactory {
	
	
	public static Command get(String commandName) {
		Command command = null;
		if(commandName.equalsIgnoreCase("sayHi")) {
			command = new SayHiCommand();
		}
		return command;
	}
	
	static class SayHiCommand extends Command{

		@Override
		protected void setInfo() {
			alias = "sayHi";
			param = "";
			desc = "";
			optionList.register(MockOptionFactory.get("sayHi_default"));
			
		}
		
	}
}
