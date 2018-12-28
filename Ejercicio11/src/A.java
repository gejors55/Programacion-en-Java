public class A {
	protected int x;
	protected static int y = 10;

	public A(int x) {
		this.x = x;
	}

	public static void incrementY() {
		A.y++;
	}

	public void incrementX() {
		this.x++;
	}

	public String toString() {
		return "x: " + this.x + " y: " + A.y;
	}
}
