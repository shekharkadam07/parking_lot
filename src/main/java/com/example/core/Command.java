package com.example.core;

public class Command {
	
	private String command;
	private String arg1;
	private String arg2;
	
	public Command(String command, String arg1, String arg2) {
		super();
		this.command = command;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	public String getCommand() {
		return command;
	}

	public String getArg1() {
		return arg1;
	}

	public String getArg2() {
		return arg2;
	}

	
		

}
