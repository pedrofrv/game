import org.lwjgl.opengl.GL11;

public class Player extends Entity {

	public void moveRight() {
		if(posX < 800-SIZE)
			posX+=MOVEMENT;
	}

	public void moveLeft() {
		if(posX > 0)
			posX-=MOVEMENT;
	}

	public void moveUp() {
		if(posY > 0)
			posY-=MOVEMENT;
	}

	public void moveDown() {
		if(posY < 600-SIZE)
			posY+=MOVEMENT;
	}
	
	public boolean colision() {
		return false;
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
