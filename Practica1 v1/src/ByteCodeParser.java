
public class ByteCodeParser {
	public static ByteCode parse(String s){
			if(s.equalsIgnoreCase("ADD")){
				return new ByteCode(ENUM_BYTECODE.ADD);
			}
			if(s.equalsIgnoreCase("SUB")){
				return new ByteCode(ENUM_BYTECODE.SUB);
			}
			if(s.equalsIgnoreCase("MUL")){
				return new ByteCode(ENUM_BYTECODE.MUL);
			}
			if(s.equalsIgnoreCase("DIV")){
				return new ByteCode(ENUM_BYTECODE.DIV);
			}
			if(s.equalsIgnoreCase("OUT")){
				return new ByteCode(ENUM_BYTECODE.OUT);
			}
			if(s.equalsIgnoreCase("HALT")){
				return new ByteCode(ENUM_BYTECODE.HALT);
			}
			if(s.equalsIgnoreCase("PUSH")){
				return new ByteCode(ENUM_BYTECODE.PUSH);
			}
			if(s.equalsIgnoreCase("LOAD")){
				return new ByteCode(ENUM_BYTECODE.LOAD);
			}
			if(s.equalsIgnoreCase("STORE")){
				return new ByteCode(ENUM_BYTECODE.STORE);
			}
			return null;
	}
}
