

public class Item extends GameObject {

	private String name; // description of name
	private String description; // description of the item 
	private int value; // how much the item gives the player
	private boolean pickup;
	
	public Item(int x, int y, char sprite, String name, String descript, int value , boolean pickup) {
		// bring it doooooooooowwwwwwwwwwnnnnnnnnnnnnnn
		super(x, y, sprite);
		
		// set up constructors 
		this.name = name;
		this.description = descript;
		this.value = value;
		this.pickup = pickup;
	}
	
	
	// Initialize getters
	public String getName() {return name; }
	public String getDescript() {return description; }
	public int getValue() {return value; }
	public boolean getPickup() {return pickup; }
	
	
	// Initialize setters
	public void setName(String givenName ) { name = givenName; }
	public void setDescript(String givenDescript ) { description = givenDescript; } 
	public void setValue(int givenValue) { value = givenValue; }
	public void setPickup(boolean givenPickup) { pickup = givenPickup; }
}
