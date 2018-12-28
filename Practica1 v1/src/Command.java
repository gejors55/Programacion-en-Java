
public class Command {
	private ENUM_COMANDO command;
	private ByteCode instruction;
	private int replace;
	public boolean execute(Engine engine){
		
		
	}
	Command (ENUM_COMANDO comando){// constructora que recibe de parser el comando que es
		this.command = comando;		// y devuelve el comando para engine
	}
	Command(ENUM_COMANDO comando, ByteCode bytecode){
		this.command = comando;
		this.instruction = bytecode;
	}
	Command(ENUM_COMANDO comando, int numero){
		this.command = comando;
		this.replace = numero;
	}
	Command(ENUM_COMANDO comando, ByteCode bytecode ,int numero ){
		this.command = comando;
		this.instruction = bytecode;
		this.replace = numero;// no se si puedo usar replace para esto porque aqui entra cuando es newinst(3)
}
