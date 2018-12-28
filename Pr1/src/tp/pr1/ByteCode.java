package tp.pr1;

public class ByteCode {
	private ENUM_BYTECODE name;
	private int param;
	
	public ByteCode(ENUM_BYTECODE bytecode){
		this.name = bytecode;
	}
	
	public ByteCode(ENUM_BYTECODE bytecode, int pos){
		this.name = bytecode;
		this.param = pos;
	}
	
	public ENUM_BYTECODE getName(){
		return this.name;
	}
	
	public int getParam(){
		return this.param;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		sb.append(this.getName());
		if(this.name == ENUM_BYTECODE.LOAD || this.name == ENUM_BYTECODE.PUSH || this.name == ENUM_BYTECODE.STORE){
			sb.append(" ");
			sb.append(this.getParam());
		}
		//String name = " " + this.getName() + " " + this.getParam();
		return sb.toString();
	}
	 
	/*public boolean push(OperandStack stack){
		stack.add(this.param);
		return true;
	}*/
	
	public boolean load(OperandStack stack, Memory mem){
		if(this.param < 0)
			return false;
		else{
			//int value = mem.read(this.param);
			stack.add(mem.read(this.param));
			return true;
		}
	}
	
	public boolean store(OperandStack stack, Memory mem){
		if(this.param < 0)
			return false;
		else{
			int value = stack.pop();
			mem.write(this.param, value);
			//stack.remove();
			return true;
		}
	}
	
	/*public boolean out(OperandStack stack){
		System.out.println("Entero almacenado en la cima de la pila: " + stack.pop());
		return true;
	}*/
	
}

/*public boolean executeInstr (ByteCode instr, OperandStack stack, Memory mem){
//boolean finish;
if(instr.name == ENUM_BYTECODE.HALT)
	return false;
else{
	if(instr.name == ENUM_BYTECODE.PUSH)
		return this.push(stack);
	else if(instr.name == ENUM_BYTECODE.LOAD)
		return this.load(stack, mem);
	else if(instr.name == ENUM_BYTECODE.STORE)
		return this.store(stack, mem);
	else if(instr.name == ENUM_BYTECODE.OUT)
		return this.out(stack);
	else
		return this.arithmetic(instr, stack);
	//finish = true;
}
//return finish;
}*/

	/*public void add(OperandStack stack){
		int value = stack.undertop() + stack.top();
		stack.subtract();
		stack.subtract();
		stack.add(value);
	}
	
	public void sub(OperandStack stack){
		int value = stack.undertop() - stack.top();
		stack.subtract();
		stack.subtract();
		stack.add(value);
	}
	
	public void mul(OperandStack stack){
		int value = stack.undertop() * stack.top();
		stack.subtract();
		stack.subtract();
		stack.add(value);
	}
	
	public void div(OperandStack stack){
		int value = stack.undertop() / stack.top();
		stack.subtract();
		stack.subtract();
		stack.add(value);
	}*/
	
