package tp.pr1;

public class Command {
	private ENUM_COMANDO command;
	private ByteCode instruction;
	private int replace;
	
	public Command (ENUM_COMANDO comando){
		this.command = comando;		
	}
	public Command(ENUM_COMANDO comando, ByteCode bytecode){
		this.command = comando;
		this.instruction = bytecode;
	}
	public Command(ENUM_COMANDO comando, int numero){
		this.command = comando;
		this.replace = numero;
	}
	
	public ENUM_COMANDO getCommand(){
		return this.command;
	}
	
	
	public boolean execute (Engine engine){
		//System.out.println("Comienza la ejecución de " + this.enumToString());
		//System.out.println("Comienza la ejecución de " + this.toString());
		if(this.command == ENUM_COMANDO.HELP){
			return Engine.showHelp();
		}
		else if(this.command == ENUM_COMANDO.QUIT){
			engine.quit();
			return true;
		}	
		else if(this.command == ENUM_COMANDO.NEWINST){
			return engine.addInstr(this.instruction);
		}    
		else if(this.command == ENUM_COMANDO.RUN){
			return engine.executeRun();
		}
		else if(this.command == ENUM_COMANDO.RESET){
			engine.reset();
			return true;
		}
		else if(this.command == ENUM_COMANDO.REPLACE)
			return engine.replace(this.replace);
		return false;

	}
	
	public String toString(){
		if(this.command == ENUM_COMANDO.NEWINST)
			return "" + this.getCommand() + this.instruction.toString(); //+ this.instruction.toString();
		else if(this.command == ENUM_COMANDO.REPLACE)
			return "" + this.getCommand() + " " + this.replace;
		else 
			return "" + this.getCommand();
	}
	
}

/*
  public String commandToString(){
		String name = "Comienza la ejecución de ";
		switch(this.command){
			case HELP:
				name += "HELP";
				break;
			case QUIT:
				name += "QUIT";
				break;
			case NEWINST:
				name += "NEWINST " + this.instruction.getName() + this.instruction.getParam();//+ INSTR + PARAM
				break;
			case RUN:
				name += "RUN";
				break;
			case RESET:
				name += "RESET";
				break;
			case REPLACE:
				name += "REPLACE " + this.replace;// + PARAM
				break;
			default:
				name = "Error: Comando incorrecto";
				break;
		}
		return name;
	}*/
