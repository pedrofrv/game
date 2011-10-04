import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class Player extends Entity {
	private List<Entity> entities;
	boolean hasLoot = false;
	
	
	public Player(List<Entity>e) {
		entities = e;
		
	}

	public void moveRight() {
		if(posX < 800-SIZE)
			posX+=MOVEMENT;
		if(colision()) {
			posX-=MOVEMENT;
		}
	}

	public void moveLeft() {
		if(posX > 0)
			posX-=MOVEMENT;
		if(colision()) {
			posX+=MOVEMENT;
		}
	}

	public void moveUp() {
		if(posY > 0)
			posY-=MOVEMENT;
		if(colision()) {
			posY+=MOVEMENT;
		}
	}

	public void moveDown() {
		if(posY < 600-SIZE)
			posY+=MOVEMENT;
		if(colision()) {
			posY-=MOVEMENT;
		}
	}
	
	public boolean colision() {
		Rectangle thisRectangle = new Rectangle(posX, posY, SIZE, SIZE);
		for (Entity e : entities) {
			if (e != this) {
				Rectangle rectangle = new Rectangle(e.posX, e.posY, SIZE, SIZE);
				if (thisRectangle.contains(rectangle)) {
					e.collide(this);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void collide(Player p) {		
	}
	
	public void loot(Loot l) {
		//entities.remove(l);
		l.setPosX(900);
		l.setPosY(100);
		hasLoot = true;
	}
	
	public void draw() {
		GL11.glColor3f(0.1f,0.1f,0.5f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(posX,posY);
		GL11.glVertex2f(posX+SIZE,posY);
		GL11.glVertex2f(posX+SIZE,posY+SIZE);
		GL11.glVertex2f(posX,posY+SIZE);
		GL11.glEnd();
	}
}
