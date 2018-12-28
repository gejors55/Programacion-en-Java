package tp.pr1;

public class Memory {

	private Integer[] memory;
	private int size;
	public final static int MAX_MEM = 200;
	private boolean vacia;
	
	public Memory(){
		this.vacia = true;
		this.memory = new Integer[Memory.MAX_MEM];
		this.size = Memory.MAX_MEM;
		for (int i = 0; i < this.size; i++)
			this.memory[i] = null;
	}
	
	private void resize(int pos){
		if (pos >= this.size){
			Integer[] mem_aux = new Integer[pos * 2];
			for (int i = 0; i < pos * 2; i++)
				mem_aux[i] = null;
			for (int i = 0; i < this.size; i++)
				mem_aux[i] = this.memory[i];
			this.memory = mem_aux;
			this.size = pos * 2;
		}
	}
	
	public boolean write(int pos, int value){
		this.vacia = false;
		if (pos < 0)
			return false;
		else {
			this.resize(pos);
			this.memory[pos]= value;
			return true;
		}
	}
	
	public int read(int pos){
		return this.memory[pos];
	}
	
	public String toString (){
		if(vacia)
			return "Memoria: <vacia> ";
		
		else{
			String mensaje = "";
			int i = 0;
			while(i < this.size){
				if(this.memory[i] != null)
					mensaje = mensaje + "[" + i + "] :" + this.memory[i] + "  ";
				i++;
			}
			return "Memoria: " + mensaje;
		}
	}
	
	public void reset(){
		for(int i = 0; i < MAX_MEM; i++)
			this.memory[i] = null;
		this.vacia = true;
	}
}
