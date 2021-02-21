import commandLine.Option;

public class MockOptionFactory {
	
	public static Option get(String optionName) {
		Option option =  null;
		
		if(optionName.equalsIgnoreCase("sayHi_default")) {
			option = new SayHi_Default();
		}
		
		return option;
	}

	static class SayHi_Default extends Option{

		@Override
		public void execute(String arg0) {
			System.out.println("hi");
		}

		@Override
		protected void setInfo() {
			alias = "default";
		}
		
	}
}
