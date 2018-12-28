package tp.pr1;

/*public enum ENUM_BYTECODE {
	PUSH, LOAD, STORE, ADD, SUB, MUL, DIV, OUT, HALT;
}*/
public enum ENUM_BYTECODE {
	PUSH(1), LOAD(1), STORE(1), ADD, SUB, MUL, DIV, OUT, HALT;
	
	private int numArgs;
	
	ENUM_BYTECODE() {
		this(0);
	}
	
	ENUM_BYTECODE(int n){
		numArgs = n;
	}

	public int getNumArgs(){
		return numArgs;
	}
}
