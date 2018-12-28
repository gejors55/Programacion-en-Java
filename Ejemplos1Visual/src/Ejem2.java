import javax.swing.JFrame;
import java.awt.EventQueue;

public class Ejem2{
  public static void main(String []args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame ventana = 
          new JFrame("Mi primera ventana - Ejemplo 2");
        ventana.setSize(320, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
      }
    });
  }
}
