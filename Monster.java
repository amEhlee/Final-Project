
import java.util.ArrayList;
import java.util.Random;

public class Monster extends Character {

	// they will prevent the monster from performing actions of having interactions if its dead
	private boolean exists = true;
	
	// checks to see if the monster is trying to defend from the next attack
	boolean defending = false;
	
	// stores the name of the monster, quotes, and the means to make the quotes random
	private String name;
	static Random rand = new Random();
	private ArrayList<String> WolfQuotes = new ArrayList();
	private ArrayList<String> VampQuotes = new ArrayList();

	// regular ol contructor brings down everything from above 
	public Monster(int x, int y, char charSprite, String name, int health, int atk , int def) {
		super(x, y, charSprite, health, atk, def);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	/*
	 * This method moves the object just as the above
	 * BUT DOES NOT REQUIRE INPUT OF ANY SORT 
	 * this will probably be used to randomly move monsters
	 * or if i get far enough move the player in scripted events? Probably not though
	 */
	public void moveObject(int ranmove)
	{
		// if the monster exists allow them to move 
		if(exists = true)
		{
			/*
			 * Depending on the value of random int monster moves in the direction 
			 * associated with that number 
			*/
			switch(ranmove)
			{
				case 1:
					// move up
					moveY(-1);
					break;
					
				case 2:
					// move right 
					moveX(1);
					break;
				
				case 3:
					// move down
					moveY(1);
					break;
				
				case 4:
					// move left 
					moveX(-1);
					break;
			}
		}

	}
	
	/*
	 * In battles randomize the actions of the monsters. also 
	 * takes in who will be reciving the damage 
	 * all this is done via cases
	 * case 1 - attack 1 (wolf doess more damage from this case)
	 * case 2 - attack 2 (vampire heals from this case)
	 * case 3 - defend   (both benefit equally)
	*/
	public void battleAction(int randInt, Player recipient )
	{
		// stores the amount of damage the monster is dealing 
		double damageDealt = 0; 
		
		// this factors in the player defending 
		if(recipient.defending == true)
		{
			// take half damage when defending
			damageDealt = ((getAtk() - recipient.getDef()) / 2);
			
			// turn of the "defending" state
			recipient.setdefend(false);
		}
		else 
		{
			// if they aren't defending make it normal damage
			damageDealt = (getAtk() - (recipient.getDef()));
		}
		
		switch(randInt)
		{
		
			// vary this based on what monster it is e.g scratches hurt more for wolf while bites heal Vampire 
			case 1:
				// if youre fighting a wolf that has claws
				if(getSprite() == 'W')
				{
					// due to claws he deals 1.5x the damage
					System.out.println(getName() + " having claws attacks for 1.5x the damage!");
					damageDealt = damageDealt*1.5;
				}
				
				// attack monster // prints something like this (You attacked larry for 8 points of damage 
				System.out.println(getName() + " has attacked the player with a scratch for " + (damageDealt) + " points of damage!" );
				recipient.setHealth(recipient.getHealth() - (damageDealt));
				break;
			
			case 2:
				// if you're fighting a vampire that drains blood
				if(getSprite() == 'V')
				{
					// due to him draining your blood he deals 1.5x the  damage and heals for half damage
					damageDealt = damageDealt*1.5;
					setHealth(getHealth() + (damageDealt / 2));
					System.out.println(getName() + " Drains some of your blood ");
				}
				// attack monster // prints something like this (You attacked larry for 8 points of damage 
				System.out.println(getName() + " has attacked the player with a bite for " + (damageDealt) + " points of damage!" );
				recipient.setHealth(recipient.getHealth() - (damageDealt));
				break;
				
			case 3:
				System.out.println(getName() + " is now defending for 1 turn!");
				defending = true;
				break;
		}
	}

	public void sayQuote()
	{
		int RandInt = 0;
		// add some quotes to Vampire to select from DONT ASK WHERE THESE COME FROM
		VampQuotes.add("\"Darkness is so predictable.\"");
		VampQuotes.add("\"You're intoxicated by my very presence.\"");
		VampQuotes.add("\"Go sit down and look pale.\"");
		VampQuotes.add("\"Do I dazzle you?\"");
		VampQuotes.add("\"Speaking of which, would you like to explain to me how you\"re alive\"");
		VampQuotes.add("\"You can't trust a vampire, trust me\"");
		VampQuotes.add("\"Life sucks, and then you die...\"");
		
		// add some quotes to the wolf 
		WolfQuotes.add("\"You need a brain?\"");
		WolfQuotes.add("\"Why didn't I just walk away? Oh right, because I'm a idiot.\"");
		WolfQuotes.add("\"You're awfully small to be so hugely irritating.\"");
		WolfQuotes.add("\"I\"m prepared to be annoyingly persistent.\"");
		WolfQuotes.add("\"You're not the center of the universe, you know.\"");
		WolfQuotes.add("\"Stupid, stupid, stupid.\"");
		
		// prints a random battle quote from the wolf's selection
		if(getSprite() == 'W') 
		{
			// make next monster action random 
			int randomInt = rand.nextInt((WolfQuotes.size()-1)) + 1;
			System.out.println("\nThe monster taunts you saying: \n" + WolfQuotes.get(randomInt) + "\n");
		}
		
		// this prints a random quote from the vampire's selection
		if(getSprite() == 'V')
		{
			// make next monster action random 
			int randomInt = rand.nextInt((VampQuotes.size()-1)) + 1;
			System.out.println("\nThe monster taunts you saying: \n" + VampQuotes.get(randomInt) + "\n");
		}
		
	}
	
	// Initialize getters and setters
	public boolean getExists() {return exists; }
	public String getName() {return name; }
	public boolean getdefend() {return defending; }

	// set state of monster
	public void setExists(boolean state ) { exists = state; } 
	public void setName(String givenName) {name = givenName; }
	public void setdefend(boolean status) { defending = status; }

	
}
