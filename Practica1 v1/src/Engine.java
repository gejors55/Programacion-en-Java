
import java.util.*;

public class Engine {
	private boolean end;
	private ByteCodeProgram program;
	Scanner in = new Scanner(System.in);// scanner para leer los datos;
	
	public void start(){
		while(!end){
			System.out.print("> ");
			Command comando = null;
			String line = in.nextLine();
			comando = CommandParser.parse(line);
			if(comando != null){
				Command.execute(this);
					
			}
				
				
		}
			
			
}
}
	

