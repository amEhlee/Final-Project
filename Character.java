

public class Character extends Sprite {
	
	// Initialize all variables for OBJECTS so things like player / monster or item
	/// this may be reserved for later objects
	private int Atk;
	private int Def;

	public Character(int x, int y, char charSprite, int health, int atk, int def) {
		super(x, y, charSprite,health);
		
		// TODO Auto-generated constructor stub
		this.Atk = atk;
		this.Def = def;
	}

	// Initialize getters for appropriate values
	public int getAtk() {return Atk; }
	public int getDef() {return Def; }

	// Initialize setters for appropriate values
	public void setAtk(int givenatk) { Atk = givenatk; }
	public void setDef(int givendef) { Def = givendef; }
}
