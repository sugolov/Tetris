
// Window of the Graphical Program

import java.awt.*; // graphical package
import java.awt.event.*; // interfaces 

public class TetrisWindow extends Frame implements WindowListener {
   TetrisPanel panel = new TetrisPanel();

   public TetrisWindow(String title) {
      setTitle(title);
      setSize(400, 800);
      setResizable(false);
      setVisible(true);

      add(panel);
      addWindowListener(this);
   }

   // methods of WindowListener
   public void windowClosing(WindowEvent we) {
      System.exit(0);
   }

   public void windowDeactivated(WindowEvent we) {
   }

   public void windowActivated(WindowEvent we) {
   }

   public void windowDeiconified(WindowEvent we) {
   }

   public void windowIconified(WindowEvent we) {
   }

   public void windowClosed(WindowEvent we) {
   }

   public void windowOpened(WindowEvent we) {
   }

}