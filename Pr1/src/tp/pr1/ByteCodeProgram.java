package tp.pr1;

public class ByteCodeProgram {
	private ByteCode[] program;
	private  int cont;
	public final static int DIM_PROGAM = 50;
	
	public ByteCodeProgram(){
		this.program = new ByteCode[ByteCodeProgram.DIM_PROGAM];
		this.cont = 0;
	}
	
	public int getCont(){ 
		return this.cont;
	}
	
	public ByteCode getByteCode(int pos){
		return this.program[pos];
	}
	
	public void addByteCode(ByteCode instr){
		this.program[cont] = instr;//new ByteCode(instr.getName(), instr.getParam()); //!!puntero
		this.cont++;
	}
	
	public boolean replaceInstr(ByteCode instr, int pos){
		if(pos < 0 && pos >= this.cont && instr.equals(null))
			return false;
		else{
			this.program[pos] = instr;
			return true;
		}
	}
	
	public void reset(){
		for(int i = 0; i < this.cont; i++)  //No necessary
			this.program[i] = null;
		this.cont = 0;
	}	

	public String toString(){
		if(this.cont == 0)
			return "Programa almacenado: <vacio>";
		else{
			//StringBuilder sb = new StringBuilder();
			String insts = "";
			for(int i = 0; i < this.cont; i++){
					insts += i + ": " + program[i].toString() + "\n";
					//program += i + ":  " + this.program[i].getName() + "\n";
			}
			return "Programa almacenado: \n" + insts;
		}
	}
}

/*if(this.program[i].getName() == ENUM_BYTECODE.LOAD || this.program[i].getName() == ENUM_BYTECODE.STORE || this.program[i].getName() == ENUM_BYTECODE.PUSH){
	sb.append(i);
	sb.append(": ");
	sb.append(this.program[i].getName());
	sb.append(" ");
	sb.append(this.program[i].getParam());
	sb.append("\n");
}
	//program += i + ":  " + this.program[i].getName() + " " + this.program[i].getParam() + "\n";
else {
	sb.append(i);
	sb.append(": ");
	sb.append(this.program[i].getName());
	sb.append("\n");
}
insts += sb.toString();*/

/*public void viewProgram(){
	System.out.println("Programa almacenado: ");
	for(int i = 0; i < this.cont; i++)
		System.out.println(i + ":  " + this.program[i].getName() + this.program[i].getParam());//this.enumToString(this.program[i], i));
	 
}*/



/*public void addInstr(ByteCode instr, int pos){
if(pos >= 0 && pos < this.cont)
	this.program[pos] = instr;
}*/


/*public String toString(int i){
/String progam = "\n";
for(int i = 0; i < this.cont; i++)
	progam = progam + i + ":  " + this.enumToString(this.program[i]) + this.program[i].getParam() + "\n";
return progam;/
return i + ":  " + this.enumToString(this.program[i], i); //+ this.program[i].getParam();
}*/

/*	public String enumToString(ByteCode instr, int pos){
		String name = "";
		switch(instr.getName()){
			case PUSH:
				name = "PUSH" + this.program[pos].getParam();;
				break;
			case LOAD:
				name = "LOAD" + this.program[pos].getParam();;
				break;
			case STORE:
				name = "STORE" + this.program[pos].getParam();;
				break;
			case ADD:
				name = "ADD";
				break;
			case SUB:
				name = "SUB";
				break;
			case MUL:
				name = "MUL";
				break;
			case DIV:
				name = "DIV";
				break;
			case OUT:
				name = "OUT";
				break;
			case HALT:
				name = "HALT";
				break;
		}
		return  name;
	}*/
