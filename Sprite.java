
import java.util.Scanner;
import java.util.Random;




// all monsters play objects and npcs extend from this class
// this gives them all base stats like health, attack, defense etc
public class Sprite extends GameObject {

	// Initialize health which will be carried down
	private double Health;
	
	// constructor for character 
	// just brings down everything from above :P
	public Sprite(int x, int y, char charSprite, int health) {
		// gives the character base x and y values
		super(x, y, charSprite); 
		this.Health = health;
	}


	// direction will always be a negative or positive only effectively moving it l or r
	public void moveX(int move)
	{
		// check if the object is within the bounds of the map 
		if((getX() + move) >= 0)
		{
			// check if they go beyond the length of the list 
			if((getX() + move) < (World.getmap().length))
			{
				setX(getX() + move);
			}
		}

	}

	// direction will always be a negative or positive only effectively moving it u or d 
	public void moveY(int move)
	{
		
		// check if the object is within the bounds of the map 
		if((getY() + move) >= 0)
		{
			// check if they go beyond the length of the list 
			if((getY() + move) < (World.getmap().length))
			{
				setY(getY() + move);
			}
		}
	}


	// simplifies position of obj so i dont have to say PLAYER.GETX == BLAH BLAH
	public boolean compareposition(int otherx, int othery)
	{
		// checks if the position is the same or not
		boolean check = false;

		// check if current object is on top of other object
		if(getX() == otherx)
		{
			if(getY() == othery)
			{
				check = true;
			}
		}
		
		// returns true or fall
		return check;
	}

	
	// Initialize getters
	public double getHealth() {return Health; }
	
	// Initialize setter
	public void setHealth(double givenhealth) { Health = givenhealth; }
	

}
