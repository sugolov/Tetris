
// Drawing Panel of the Graphical Program

import java.util.*;
import java.awt.*; // graphical package
import java.awt.event.*; // graphical package
import java.awt.image.*;

public class TetrisPanel extends Panel implements KeyListener {
     // instance fields
     Dimension dim = null;
     TetrisGame game = new TetrisGame();

     Timer myTimer = new Timer();
     long period = 1000; // rate of the TimerTask

     BufferedImage offScreenImage = null;
     Graphics offScreenGraphics = null;

     TimerTask motionTask = new TimerTask() {
          public void run() {
               if (dim != null) {
                    game.moveDown();
                    repaint();
               }
          }
     };

     // constructor
     public TetrisPanel() {
          addKeyListener(this);
          myTimer.scheduleAtFixedRate(motionTask, (long) 1000, period);
     }

     public void paint(Graphics g) {
          dim = this.getSize();

          // allocate a new buffer for graphics
          offScreenImage = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

          offScreenGraphics = offScreenImage.getGraphics(); // get the graphics of the buffer

          update(g);
     }

     public void update(Graphics g) {
          offScreenGraphics.setColor(Color.WHITE);

          offScreenGraphics.fillRect(0, 0, dim.width, dim.height);

          game.display(offScreenGraphics, dim);

          g.drawImage(offScreenImage, 0, 0, this);

     }

     // methods of KeyListener
     public void keyReleased(KeyEvent key) {
     }

     public void keyPressed(KeyEvent k) {
          // game.processKey(k);
          int key = k.getKeyCode();

          switch (key) {
          case KeyEvent.VK_LEFT:
               game.moveLeft();
               break;
          case KeyEvent.VK_RIGHT:
               game.moveRight();
               break;
          case KeyEvent.VK_DOWN:
               game.dropDown();
               break;
          case KeyEvent.VK_UP:
               game.rotate();
               break;
          default:
               break;
          }
          repaint();
     }

     public void keyTyped(KeyEvent key) {
     }
}