package GameObject;


public interface Mortal extends GameObject {
	public void changeHealth(double amount);

	public boolean isDead();
}
