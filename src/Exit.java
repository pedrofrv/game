import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;


public class Exit extends Entity {

	public Exit(int x, int y){
		super();
		posX = x;
		posY = y;
	}
	
	@Override
	public void draw() {
		GL11.glColor3f(0f,0f,1f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(posX,posY);
		GL11.glVertex2f(posX+SIZE,posY);
		GL11.glVertex2f(posX+SIZE,posY+SIZE);
		GL11.glVertex2f(posX,posY+SIZE);
		GL11.glEnd();
	}

	@Override
	public void collide(Player p) {		
		if(p.hasLoot) {
			
			
			
			System.exit(0);
		}
	}

}
