import org.lwjgl.opengl.GL11;


public class Wall extends Entity {

	public Wall(int x, int y){
		posX = x;
		posY = y;
	}
	
	@Override
	public void draw() {
		GL11.glColor3f(0.2f,0.2f,0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(posX,posY);
		GL11.glVertex2f(posX+SIZE,posY);
		GL11.glVertex2f(posX+SIZE,posY+SIZE);
		GL11.glVertex2f(posX,posY+SIZE);
		GL11.glEnd();

	}

}
