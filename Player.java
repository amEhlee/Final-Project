
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Character{

	// create a new inventory list
	private ArrayList<Item> inventory = new ArrayList();

	// create a new scanner for input on where to move
	Scanner input = new Scanner(System.in);
	
	// checks to see if the player is trying to defend from the next attack
	boolean defending = false;
	
	// this is for choice when its used later
	int choice;
	

	public Player(int x, int y, char charSprite, int health, int atk , int def) {
		super(x, y, charSprite, health, atk, def);
		// TODO Auto-generated constructor stub
	}

	// adds items to the player inventory 
	public void additem(Item item)
	{
		inventory.add(item);
	} // end of item


	/*
	 *	Shows every item in the inventory
	 *	if the inventory has nothing in it 
	 *	print that
	 */
	public void showInventory()
	{

		// print stuff
		System.out.println("\nIn your inventory you have:");

		// what items in inventory
		for(int i = 0; i < inventory.size(); i ++)
		{
			if((inventory.isEmpty()) == true)
			{
				System.out.println("Nothing!");
			}
			else
			{
				// print items in inventory
				System.out.println("- " + (i + 1) + ". " + inventory.get(i).getName());
			}

		}
	} // end of inventory

	public void updateStats()
	{
		
		/*
		 * Ok this line is a bit to explain... this factors in discrepancy
		 * between user's choice and how arrays start counting from 0 
		 * moving their choice down by one because on the console where i set it 
		 * to appear to count from one is not accurate to how arrays truly work
		 * hopefully that makes sense?
		*/
		choice -= 1;
		
		/*
		 * Uses the item name as a type of id and then applying what 
		 * value that item has to player stats
		*/
		if(inventory.get(choice).getName() == "Health Potion")
		{
			// sets new player health to current health + hot much they healed for
			System.out.println("Your Health was previously: " + getHealth());
			setHealth(getHealth() + (inventory.get(choice).getValue()));
			System.out.println("Your Health is now: " + getHealth());
		}
		else if(inventory.get(choice).getName() == "Long Sword")
		{
			// sets new player basic attack = base attack + long sword
			System.out.println("Your attack was previously: " + getAtk());
			setAtk(getAtk() + (inventory.get(choice).getValue()));
			System.out.println("Your attack is now: " + getAtk());
		}
		else if(inventory.get(choice).getName() == "Iron Armor")
		{
			// sets new player basic defense = base defense + iron armor 
			System.out.println("Your Defense was previously: " + getDef());
			setDef(getDef() + (inventory.get(choice).getValue()));
			System.out.println("Your Defense is now: " + getDef());
		}
	}
	
	/*
	 * uses every item in the inventory 
	 */
	public void useItem()
	{


		if(!inventory.isEmpty())
		{
			
			// prompt the user for their choice on what they want to do
			System.out.println("what item do you want to use?");
			showInventory();

			choice = input.nextInt();
			
			// remove the index 
			updateStats();
			inventory.remove(choice);
			
		}
		else
		{
			System.out.println("You have nothing in your inventory");
		}



	} // end of use item 


	// standard script text to prompt user for input decisions
	public void doAction()
	{

		// prompt user for movement commands
		System.out.println
		("where move next? "
				+ "\n1. move up "
				+ "\n2. move right"
				+ "\n3. move down"
				+ "\n4. move left"
				+ "\n5. show inventory"
				+ "\n6. use item"
				);

		// take users next input 
		int choice = input.nextInt();


		/*
		 * Switch case just to do actions for the options above e.g move in direction
		 * open up inventory or use an item 
		 * switch case was used just for readability
		 */
		switch(choice)
		{
		//1.up,  2.right, 3.down, 4.left, 5.show inventory, 6. useItem
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

		case 5:
			// move inventory
			showInventory();
			break;

		case 6:
			// use the item
			useItem();
			break;
		}



	} // end of move object 
	
	public void battleAction(Monster recipient)
	{
		// prompt user for battle commands
		// add more commands like meditate or 
		System.out.println
		("where move next? "
				+ "\n1. attack"
				+ "\n2. defend"
				+ "\n3. useItem"
				+ "\n4. do nothing..."
		);

		// take users next input 
		int choice = input.nextInt();
		
		// stores how much damage the user did 
		int damageDealt = 0; 

		// cuts damage received in half when defending 
		if(recipient.defending == true)
		{
			// add an extra bit of defense to damage dealt
			damageDealt = ( (getAtk() - recipient.getDef()) / 2 );
			
			// turn of the "defending" state
			recipient.setdefend(false);
		}
		else 
		{
			// if they aren't defending make it normal damage
			damageDealt = (getAtk() - (recipient.getDef()));
		}

		// switch between users choice to find out what they want to do 
		switch(choice)
		{
			//1.up,  2.right, 3.down, 4.left, 5.show inventory, 6. useItem
			case 1:
				
				// attack monster // prints something like this (You attacked larry for 8 points of damage 
				System.out.println("You attacked " + recipient.getName() + " for " + damageDealt + " points of damage!" );
				recipient.setHealth(recipient.getHealth() - (damageDealt));
				break;

	
			case 2:
				// this just tells the enemy that the player is defending
				// this cause them to have extra defense from the next attack 
				System.out.println("You're defending from the next attack!");
				defending = true;
				break;
	
			case 3:
				// call useItem
				useItem();
				break;
				
			case 4:
				// do nothing 
				System.out.println("You did. nothing...");
				break;
			
			default:
				System.out.println("either incorrect or no input was entered therefore... SKIP A TURN!");
				break;
		}
	}
	
	// Initialize getters
	public boolean getdefend() {return defending; }
	
	// Initialize setter
	public void setdefend(boolean status) { defending = status; }
	


}
