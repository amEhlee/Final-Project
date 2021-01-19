
public class GameObject 
{
	// set up initials
	private int x;
	private int y;
	private char sprite;
	
	// initalize constructor for gameobject
	public GameObject(int x, int y, char sprite)
	{
		// set base values for x
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	// Initialize getters
	public int getX() {return x; }
	public int getY() {return y; }
	public char getSprite() {return sprite; }

	// Initialize setters
	public void setX(int givenX ) { x = givenX; }
	public void setY(int givenY ) { y = givenY; } 
	public void setSprite(char givensprite) { sprite = givensprite; }
}
