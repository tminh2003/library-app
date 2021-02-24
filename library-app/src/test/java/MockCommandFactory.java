import commandLine.Command;

public class MockCommandFactory {
	
	
	public static Command get(String commandName) {
		Command command = null;
		if(commandName.equalsIgnoreCase("sayHi")) {
			command = new SayHiCommand();
		}else if(commandName.equalsIgnoreCase("twoParam")) {
			command = new TwoParamCommand();
		}else if(commandName.equalsIgnoreCase("oneParam")){
			command = new OneParamCommand();
		}else {
			System.out.println("No such command to create: " + commandName);
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
	
	static class OneParamCommand extends Command{

		@Override
		protected void setInfo() {
			alias = "oneParam";
			param = "one";
			desc = "";
			optionList.register(MockOptionFactory.get("twoParam_default"));
			
		}
		
	}
	
	static class TwoParamCommand extends Command{

		@Override
		protected void setInfo() {
			alias = "twoParam";
			param = "one, two";
			desc = "";
			optionList.register(MockOptionFactory.get("twoParam_default"));
			
		}
		
	}
}
