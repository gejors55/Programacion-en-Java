package tp.pr1;

public class ByteCodeParser {
	public static ByteCode parse(String inst){
		String[] palabras = inst.split(" ");
		if (palabras.length == 1){
			if(palabras[0].equalsIgnoreCase("ADD"))
				return new ByteCode(ENUM_BYTECODE.ADD);
			else if(palabras[0].equalsIgnoreCase("SUB"))
				return new ByteCode(ENUM_BYTECODE.SUB);
			else if(palabras[0].equalsIgnoreCase("MUL"))
				return new ByteCode(ENUM_BYTECODE.MUL);
			else if(palabras[0].equalsIgnoreCase("DIV"))
				return new ByteCode(ENUM_BYTECODE.DIV);
			else if(palabras[0].equalsIgnoreCase("OUT"))
				return new ByteCode(ENUM_BYTECODE.OUT);
			else if(palabras[0].equalsIgnoreCase("HALT"))
				return new ByteCode(ENUM_BYTECODE.HALT);
			else return null;
		}
		else if (palabras.length == 2){
			int pos = new Integer(palabras[1]);
			if(palabras[0].equalsIgnoreCase("PUSH")){
				return new ByteCode(ENUM_BYTECODE.PUSH, pos);
			}
			else if(palabras[0].equalsIgnoreCase("LOAD")){
				return new ByteCode(ENUM_BYTECODE.LOAD, pos);
			}
			else if(palabras[0].equalsIgnoreCase("STORE")){
				return new ByteCode(ENUM_BYTECODE.STORE, pos);
			}
			else return null;
		}
		else return null;
	}
}