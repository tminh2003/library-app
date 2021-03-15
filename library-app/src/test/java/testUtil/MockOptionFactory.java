package testUtil;
import java.util.Scanner;

import commandLine.Option;

public class MockOptionFactory {
	
	public static Option get(String optionName) {
		Option option =  null;
		
		if(optionName.equalsIgnoreCase("sayHi_default")) {
			option = new SayHi_Default();
		}else if(optionName.equalsIgnoreCase("twoParam_default")) {
			option = new TwoParam_Default();
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
	
	static class TwoParam_Default extends Option{

		@Override
		public void execute(String arg0) {
			System.out.println(arg0);
			Scanner scanner = new Scanner(arg0);
			scanner.useDelimiter(",");
			System.out.println(scanner.next() + " " + scanner.next());
		}

		@Override
		protected void setInfo() {
			alias = "default";
		}
		
	}
}
