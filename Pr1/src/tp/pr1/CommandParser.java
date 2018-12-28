package tp.pr1;

public class CommandParser {
	public static Command parse(String line){
		//line = line.trim();
		String[] palabras = line.split(" ");
		if (palabras.length == 0) return null;
		//else palabras[0] = palabras[0].toLowerCase();
		else {
			palabras[0] = palabras[0].toLowerCase();
			if (palabras.length == 1){
				if(palabras[0].equalsIgnoreCase("QUIT")){
					return new Command(ENUM_COMANDO.QUIT);
				}
				else if(palabras[0].equalsIgnoreCase("HELP")){
					return new Command(ENUM_COMANDO.HELP);
				}
				else if(palabras[0].equalsIgnoreCase("RUN")){
					return new Command(ENUM_COMANDO.RUN);
				}
				else if(palabras[0].equalsIgnoreCase("RESET")){
					return new Command(ENUM_COMANDO.RESET);
				}
				else return null;
			}
			else if(palabras.length == 2){
				if(palabras[0].equalsIgnoreCase("NEWINST")){
					ByteCode bc = ByteCodeParser.parse(palabras[1]);
					if(bc != null)
						return new Command(ENUM_COMANDO.NEWINST, bc);
					else 
						return null;
					
				}
				else if(palabras[0].equalsIgnoreCase("REPLACE")){
					int reenplazo = new Integer(palabras[1]);
					return new Command(ENUM_COMANDO.REPLACE, reenplazo);
				}
				else return null;
			}
			else if (palabras.length == 3){
				if(palabras[0].equalsIgnoreCase("NEWINST")){
					String bytecode = palabras[1] + " " + palabras[2];
					ByteCode bc = ByteCodeParser.parse(bytecode);
					if(bc != null)
						return new Command(ENUM_COMANDO.NEWINST, bc);
					else 
						return null;
				}
				else return null;
			}
			else return null;
		}
	}
}

