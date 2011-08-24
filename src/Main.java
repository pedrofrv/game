import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.examples.spaceinvaders.SoundManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Main {

	private long lastFrame;
	private long lastFPS;
	private int fps;
	
	int posX, posY;
	
	private SoundManager soundManager; 
	private List<Entity> entities = new ArrayList<Entity>();
	private Player player = new Player();

	public Main() {
		//soundManager = new SoundManager();
		//soundManager.addSound("fallbig.wav");
		entities.add(player);
		entities.add(new Wall(50,0));
		entities.add(new Wall(50,50));
		entities.add(new Wall(100,50));
	}
	
	public void start(){
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		getDelta();
		lastFPS = getTime();
		
		
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			updateFPS();
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			
			
			draw();
			
			pollInput();
			Display.update();
			Display.sync(60);
		}
	}
	
	private void pollInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
				if (Keyboard.getEventKeyState()) {
					player.moveLeft();
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
				if (Keyboard.getEventKeyState()) {
					player.moveRight();
				}
			}
			
			if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
				if (Keyboard.getEventKeyState()) {
					player.moveDown();
				}
			}
			
			if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
				if (Keyboard.getEventKeyState()) {
					player.moveUp();
				}
			}
		}
		
		/*if(Mouse.isButtonDown(0)) {
			System.out.println(Mouse.getX() + Mouse.getY());
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) &&
				posX > 0) {
			posX-=5;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) &&
				posX < 800-SIZE) {
			posX+=5;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) &&
				posY < 600-SIZE) {
			posY+=5;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) &&
				posY > 0) {
			posY-=5;
		}*/
		
	}
	
	private void draw() {
		for (Entity entity : entities) {
			entity.draw();
		}
	}
	
	/**
	 * Get the time in milliseconds
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
		
	}

}
