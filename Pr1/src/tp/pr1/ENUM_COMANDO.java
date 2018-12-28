package tp.pr1;

/*ublic enum ENUM_COMANDO {
	HELP, QUIT, NEWINST, RUN, RESET, REPLACE;
}*/
public enum ENUM_COMANDO {
	HELP, QUIT, NEWINST(1), RUN, RESET, REPLACE(1);
	
	private int numArgs;
	ENUM_COMANDO() { 
		this(0); 
	}
	ENUM_COMANDO(int n) {
		numArgs = n;
	}
	public int getNumArgs() { 
		return numArgs;
	}
}
