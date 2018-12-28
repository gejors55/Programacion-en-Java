package tp.pr1;

public class CPU {
	private OperandStack stack;// = new OperandStack();
	private Memory mem;// = new Memory();
	private boolean halt;
	
	public CPU(){
		this.stack = new OperandStack();
		this.mem = new Memory();
		this.halt = false;
	}

	
	public boolean execute(ByteCode instr){
		if(instr.getName() == ENUM_BYTECODE.HALT){
			this.halt = true;
			return halt;
		}
		else if(!halt){
			if(instr.getName() == ENUM_BYTECODE.PUSH)
				//this.finish =  instr.push(stack);
				return this.stack.add(instr.getParam());
			else if(instr.getName() == ENUM_BYTECODE.LOAD)
				return  instr.load(stack, mem);
			else if(instr.getName() == ENUM_BYTECODE.STORE)
				return  instr.store(stack, mem);
			else if(instr.getName() == ENUM_BYTECODE.OUT)
				return  this.stack.out();
			else
				return stack.arithmetic(instr);
		}
		else
			return true;
	//	return this.finish;
	}
	
	public void reset(){
		this.stack.reset();
		this.mem.reset();
		this.halt = false;
	}
	
	public void showCPU(){
		System.out.println("Estado de la CPU: \n" + "  " + this.mem.toString() + '\n' + "  " + this.stack.toString() + '\n');
	}
}








/*public String toString (){
return "Estado de la CPU: \n" + "  " + mem.toString() + '\n' + "  " + stack.toString() + '\n';
}*/
/*
if(instr.getname() == ENUM_BYTECODE.HALT)
	return false;
else{
	if(instr.getname() == ENUM_BYTECODE.PUSH)
		return instr.push(stack);
	else if(instr.getname() == ENUM_BYTECODE.LOAD)
		return instr.load(stack, mem);
	else if(instr.getname() == ENUM_BYTECODE.STORE)
		return instr.store(stack, mem);
	else if(instr.getname() == ENUM_BYTECODE.OUT)
		return instr.out(stack);
	else
		return instr.arithmetic(instr, stack);
	//finish = true;
}
//return this.finish;*/