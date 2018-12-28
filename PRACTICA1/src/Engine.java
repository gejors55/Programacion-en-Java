public class Engine {
	private boolean end;
	private ByteCodeProgram program;
	public void start(){
		while(!end){
			System.out.print("> ");
			Command comando = null;
			line = in.nextline();
			comando = CommandParser.parse(line);
			if(comando != null){
				Command.execute(this);
					
			}
				
				
		}
			
			
}
}
	

