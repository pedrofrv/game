import org.lwjgl.opengl.GL11;


public class Loot extends Entity {

	public Loot(int x, int y){
		super();
		posX = x;
		posY = y;
	}
	
	@Override
	public void draw() {
		GL11.glColor3f(1f,1f,0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(posX,posY);
		GL11.glVertex2f(posX+SIZE,posY);
		GL11.glVertex2f(posX+SIZE,posY+SIZE);
		GL11.glVertex2f(posX,posY+SIZE);
		GL11.glEnd();
	}

	@Override
	public void collide(Player p) {		
		p.loot(this);
	}

}
