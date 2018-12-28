
public class Memory {
	private Integer[] memory;
	private int size;
	public final static int MAX_MEMORY = 200;
	private boolean vacia;
	
	public Memory(){// inicializa todo!
		this.vacia = true;
		this.memory = new Integer[Memory.MAX_MEMORY];
		this.size = Memory.MAX_MEMORY;
		for(int i=0; i <this.size; i++){
			this.memory[i] = null;
		}	
	}
	
	private void resize(int pos){
		if(pos >= this.size){// si se llena la memoria, como tiene que ser ilimitada
			Integer[] mem_aux= new Integer [pos *2];// crea un array el doble de grande
			for(int i = 0; i < pos*2; i++){// lo pone todo a null otra vez
				mem_aux[i] = null;
			}
			for(int i = 0; i < this.size; i++){// mete lo que habia antes en memoria antes de ponerlo toda a null
				mem_aux[i] = this.memory[i];
			}
			this.memory = mem_aux;// y actualiza la memoria y el tamaño;
			this.size = pos*2;	
		}
	}
	
	public boolean write(int pos, int value){
		this.vacia = false;
		if(pos < 0){
			return false;
		}
		else{
			this.resize(pos);
			memory[pos]= value;
			return true;
		}
	}
	public int read(int pos){
		return memory[pos];
	}


	//public String toString(){					FALTAN LOS TO STRINGGGGG
	//	System.out.println("Memoria: "+)
	
	
	//}
}
