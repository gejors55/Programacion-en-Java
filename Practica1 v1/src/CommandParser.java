

public class CommandParser {
	public static Command parse(String line){
		String[] com = line.split(" ");
		
		if (com.length == 1){
			if(com[0].equalsIgnoreCase("QUIT")){
				return new Command(ENUM_COMANDO.QUIT);
			}
			if(com[0].equalsIgnoreCase("HELP")){
				return new Command(ENUM_COMANDO.HELP);
			}
			if(com[0].equalsIgnoreCase("RUN")){
				return new Command(ENUM_COMANDO.RUN);
			}
		}
		if(com.length == 2){
			if(com[0].equalsIgnoreCase("NEWINST")){
				ByteCode bc = ByteCodeParser.parse(com[1]);  // creo objeto de clase bytecode que recoge la bytecode
				return new Command(ENUM_COMANDO.NEWINST, bc);   // le paso la instruccion y el bytecode
			}
			if(com[0].equalsIgnoreCase("REPLACE")){
				int reenplazo = new Integer(com[1]);
				return new Command(ENUM_COMANDO.REPLACE, reenplazo);
			}
		}
		if (com.length == 3){
			ByteCode bc = ByteCodeParser.parse(com[1]+com[2]);
			return new Command(ENUM_COMANDO.NEWINST, bc, numero);
		}
		return null;
	}
}
