import org.lwjgl.opengl.GL11;


public abstract class Entity {
	
	protected int posX;
	protected int posY;
	protected static final int SIZE = 50;
	protected static final int MOVEMENT = 50;

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosY() {
		return posY;
	}
	
	public abstract void draw();
}
