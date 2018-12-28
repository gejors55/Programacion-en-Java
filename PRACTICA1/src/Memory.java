public class Memory {
	private Integer[] memory;
	public boolean write(int pos, int value){
		if(pos >= 0){
			memory[pos]= value;
		}
		return memory[pos]!=null; //devuelve cierto si esta escrito bien
	}
	public int read(int pos){
		return memory[pos];
	}
}
