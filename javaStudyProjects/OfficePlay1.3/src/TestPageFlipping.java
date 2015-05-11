import java.awt.image.BufferStrategy;
	import javax.swing.*;
	import java.awt.*;
public class TestPageFlipping {
	 int width = 640;
	 int height = 480;

	 GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
	 GraphicsDevice graphDevice = graphEnv.getDefaultScreenDevice();
	 GraphicsConfiguration graphicConf = graphDevice.getDefaultConfiguration();

	 public TestPageFlipping() {
	  JFrame jFrame = new JFrame(graphicConf);
	  jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  jFrame.setTitle("double buffer test");
	  jFrame.setResizable(false);
	  jFrame.setFocusTraversalKeysEnabled(false);

	  Canvas canvas = new Canvas();
	  canvas.setSize(width, height);
	  canvas.setIgnoreRepaint(true);

	  jFrame.getContentPane().add(canvas);
	  jFrame.pack();
	  jFrame.setVisible(true);

	  System.out.println("GraphicsConfiguration flipping? " + graphicConf.getBufferCapabilities().isPageFlipping());
	  canvas.createBufferStrategy(2);
	  BufferStrategy bufferStrategy = canvas.getBufferStrategy();
	  System.out.println("BufferStrategy flipping? " + bufferStrategy.getCapabilities().isPageFlipping());

	  while(true) {
	    Graphics g = bufferStrategy.getDrawGraphics();
	    g.setColor(Color.BLACK);
	    g.fillRect(0,0,width,height);
	    g.setColor(Color.RED);
	    g.drawLine((int)(Math.random()*width),(int)(Math.random()*height),
	               (int)(Math.random()*width),(int)(Math.random()*height));
	    bufferStrategy.show();
	    g.dispose();
	  }
	 }

	 public static void main(String[] args) {
	  new TestPageFlipping();
	}
}
