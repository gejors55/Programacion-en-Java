public enum ENUM_COMANDO {
	HELP, QUIT, NEWIST(2), RUN, RESET, REPLACE(1);
	private int numPalabras;

	ENUM_COMANDO() {
		this(0);
	}

	ENUM_COMANDO(int palabras) {
		numPalabras = palabras;
	}

	public int getNumPalabras() {
		return numPalabras;
	}
}
