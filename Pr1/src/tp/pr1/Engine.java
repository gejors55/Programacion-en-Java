package tp.pr1;
import java.util.Scanner;

public class Engine {
	private ByteCodeProgram program;
	private CPU cpu;
	private boolean end;
	private static Scanner input = new Scanner(System.in);
	
	public Engine(){
		this.program = new ByteCodeProgram();
		this.cpu = new CPU();
		this.end = false;
	}
	
	public void start(){
		String line;
		while(!this.end){
			System.out.print("> ");
			line = input.nextLine();
			Command command = null;
			command = CommandParser.parse(line);
			if(command == null)
				System.out.println("Error: Comando incorrecto");
			else{
				System.out.println("Comienza la ejecución de " + command.toString());
				if(!command.execute(this))
					System.out.println("Error: Ejecucion incorrecta del comando");
				//si bcprogram no es vacio mostrarla
				//System.out.println(this.program.toString());
			}
			if(this.program.getCont() != 0)
				System.out.println(this.program.toString());
			/*command.execute(this);
			if(command.getCommand() != ENUM_COMANDO.HELP)
				this.program.viewProgram();*/
			//System.out.println("Error: Ejecucion incorrecta del comando");
		}
		System.out.println("Fin de la ejecucion....");
		input.close();
	}
	
	
	
	public static boolean showHelp(){
		System.out.println("HELP: Muestra esta ayuda");
		System.out.println("QUIT: Cierra la aplicacion");
		System.out.println("RUN: Ejecuta el programa");
		System.out.println("NEWINST BYTECODE: Introduce una nueva instrucción al programa");
		System.out.println("RESET: Vacia el programa actual");
		System.out.println("REPLACE N: Reemplaza la instruccion N por la solicitada al usuario");
		return true;
	}
	public void quit(){
		this.end = true;
	}
	public boolean addInstr(ByteCode instr){
		this.program.addByteCode(instr);
		return true;
	}
	
	public boolean executeRun(){
		this.cpu.reset();    			//show error
		boolean ok = true;
		int i = 0;
		while(ok && i < this.program.getCont()){
			//System.out.println("El estado de la maquina tras ejecutar el bytecode" + this.program.getByteCode(i).toString() + " es: \n");// + this.program.enumToString(this.program.getByteCode(i), i) + " es:");
			if(this.cpu.execute(this.program.getByteCode(i))){
				System.out.println("El estado de la maquina tras ejecutar el bytecode" + this.program.getByteCode(i).toString() + " es: \n");
				this.cpu.showCPU();
				ok = true;
				i++;
			}
			else
				ok = false;	
		}
		/*for(int i = 0; i < this.program.getCont(); i++){
			System.out.println("El estado de la maquina tras ejecutar el bytecode " + this.program.enumToString(this.program.getByteCode(i), i) + " es:");
			error = this.cpu.execute(this.program.getByteCode(i));
			this.cpu.showCPU();
		}*/
		return ok;
	}
	
	//Solicita al usuario una nueva instrucción BC, que en caso de ser correcta reemplazará a la instrucción bytecode número N del programa.
	//Solo acepta instrucciones bytecode
	public boolean replace(int pos){
		String line;
		boolean right = false;
		if(pos >= 0 && pos < this.program.getCont()){
			System.out.print("Nueva instrucción: ");
			//Coger nueva instruccionparsear y pasar a newinst
			//right = this.program.addInstr(ByteCodeParser.parse(this.getScreen()), pos);
			line = input.nextLine();
			right = this.program.replaceInstr(ByteCodeParser.parse(line), pos);
		}
		else
			right = false;
			//return false;
		return right;
	}
	
	public void reset(){
		this.program.reset();
		this.cpu.reset();
	}
}

/*public String getScreen(){
String command;
Scanner input = new Scanner(System.in);
//System.out.print("> ");
command = input.nextLine();
input.close();
return command; 
}*/
