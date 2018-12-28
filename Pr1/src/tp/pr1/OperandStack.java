package tp.pr1;

public class OperandStack {
	private int[] stack;
	private int cont;
	public final static int MAX_STACK = 30; //resize?
	
	public OperandStack(){
		this.stack = new int[OperandStack.MAX_STACK];
		this.cont = 0;
	}
	
	public int getCont(){
		return this.cont;
	}
	
	public boolean add(int elem){   //LOAD & PUSH
		this.stack[cont] = elem;
		this.cont++;
		return true;
	}

	public void remove(){
		this.cont--;
	}
	public boolean arithmetic(ByteCode instr){
		//int value = 0;
		boolean right = true;
		if(this.cont >= 2){
			int num = this.pop();
			switch (instr.getName()){
				case ADD: 
					this.add(this.pop() + num);// stack.pop());
					right = true;
					break;
				case SUB:
					this.add(this.pop() - num); //stack.pop());
					right = true;
					break;
				case MUL:
					this.add(this.pop() * num);//stack.pop());
					right = true;
					break;
				case DIV:
					//int num = stack.pop();
					if(num != 0){
						right = true;
						this.add(this.pop() / num);
					}
					else 
						right = false;
					break;
				default:
					right = false;
					break;
			}
		}
		else
			right = false;
		
		return right;
	}
	
		
	/*public int top(){    //it can be improved
		return this.stack[cont - 1];
	}
	
	public int undertop(){
		return this.stack[cont - 2];
	}*/
	
	public int pop(){
		int top = this.stack[cont - 1];
		this.cont--;
		return top;
	}
	
	/*public boolean isEmpty() {
		if(this.cont < 2)
			return true;
		else
			return false;
	}*/
	public boolean out(){
		System.out.println("Entero almacenado en la cima de la pila: " + this.stack[this.cont - 1]);
		return true;
	}
	
	public String toString (){
		String mensaje = "";
		if(this.cont == 0)
			mensaje = "<vacia>";
		else{
			for(int i = 0; i < this.cont; i++)
				mensaje = mensaje + this.stack[i] + "  ";
		}
		return "Pila: " + mensaje;
	}
	
	public void reset(){
		for(int i = 0; i < this.cont; i++)
			this.stack[i] = 0;
		this.cont = 0;
	}
}


/*private int[] stack donde almacenar los operandos, así como métodos
públicos para meter y sacar un elemento de la pila.*/
/*

public String toString(){
	String pila = "Pila: " + this.stack[];// no se hacer los to string
	
	
	public String toString (){
	String mensaje="El empleado se llama "+nombre+" "+apellido+" con "+edad+" años " +
			"y un salario de "+salario;
	return mensaje;
}
}*/
