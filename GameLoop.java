
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/*
 * This class is for the sole purpose of making the real main
 * method look A LOT cleaner only saving that for the sole purposes 
 * of actually running the game. 
 * TL;DR this class bring every other class together and prepares it 
 * for the main method 
 */
public class GameLoop {

	// keeps an idea of where the player is in the game 
	// and if they want to keep playing 
	static boolean gamerunning = false;
	static boolean firstrun = true;
	
	// these vars act as the collective to be interacted with
	static ArrayList<Monster> allMonsters = new ArrayList<Monster>();
	static ArrayList<Item> allitem = new ArrayList<Item>();
	
	// creates the dungeon world everything would move in and player object
	static World dungeon = new World();
	static Player player = new Player(0, 0, '\u00D8', 100, 15 ,5);


	// create a new input scanner
	static Scanner input = new Scanner(System.in);
	static Random rand = new Random();
	static int choice;




	/*
	 * This randomizes things like map, start locations for player, monster
	 * start, item locations and exit location
	 */
	private static void RandStart()
	{


		// create all neccesary objects and place them on the map 
		// create new player and set position on map 
		// order of arguments is (x, y, symbol, health, atk, def)
		dungeon.changemap(player.getX(), player.getY(), player.getSprite());

		// create new exit and update map 
		dungeon.setExit(5, 6, 'X');
		dungeon.changemap(dungeon.getExitx(),dungeon.getExity(),dungeon.getExitSymbol());


		/*
		 * These next lines of code create all the monsters present in the game 
		 * adding them to the list "allmonsters" in order to address collisions
		 * with the whole collective rather then one instance
		 */
		// create a new monster and update map 
		Monster monster1 = new Monster(5, 5, 'W', "Eddy the Wolf",25, 15, 10);
		Monster monster2 = new Monster(2, 9, 'V', "Jake the Vampire", 35, 12, 7);

		// add all monsters to the list THIS WILL WILL ADRESS COLLECTIVE RATHER THAN SINGLE INSTANCES
		allMonsters.add(monster1);
		allMonsters.add(monster2);

		// iterate through the list of monsters
		for(Monster monsters : allMonsters)
		{
			// add all the monsters to the map 
			dungeon.changemap(monsters.getX(), monsters.getY(), monsters.getSprite());
		}



		/*
		 * These next lines of code create all the monsters present in the game 
		 * adding them to the list "allmonsters" in order to address collisions
		 * with the whole collective rather then one instance
		 */
		// create all the items 
		Item item1 = new Item(9,9,'I',"Long Sword", "Adds +3 to your total attack",5,true);
		Item item2 = new Item(2,7,'I',"Iron Armor", "Adds +3 to your total defense",5,true);
		Item item3 = new Item(9,2,'I',"Health Potion", "heals you for 15 health",15,true);
		
		// add all items to the list
		allitem.add(item1);
		allitem.add(item2);
		allitem.add(item3);

		// for every item in "allitems" add it to the map
		for(Item items : allitem)
		{
			dungeon.changemap(items.getX(), items.getY(), items.getSprite());
		}

		// set places for items on the map 
		

	}

	/*
	 * This method just handles item collisions
	 * nothin special 
	*/
	private static void itemColli()
	{
		/*
		 * This for loop iterates through every item in the all items list
		 * and handles collisions and checks for every item present in the list
		 * such as if the item exists and if the player collects it 
		 */
		for(Item items : allitem)
		{
			// the player touches the item. pick it up !
			if((player.compareposition(items.getX(), items.getY()) && (items.getPickup() == true)))
			{
				// Print out the item, its name and description
				System.out.println("\n\nYou got an Item "
						+ "\nName:\n" + items.getName() + "\n"
						+ "\nDescription:\n" + items.getDescript()
				);
				
				// add the item to player inventory
				player.additem(items);
				
				// make sure the player can't pick up the item more than once
				items.setPickup(false);
				
				

				// make sure to erase item when player is on the tile 
				dungeon.changemap(items.getX(), items.getY(), player.getSprite());
			}


			// if the item has already been picked up and player is not stepping on it 
			if((items.getPickup() == false) && !(player.compareposition(player.getX(), player.getY())))
			{
				// clears item space when player isnt on it 
				dungeon.changemap(items.getX(), items.getY(), dungeon.getsprite());
			}


			// if it exists make sure monster can't erase it 
			for(Monster monster : allMonsters)
			{
				if((items.getPickup() == true) && (monster.getExists() == true))
				{
					// this is just safety to prevent the monster from erasing the exit symbol
					if(!monster.compareposition(items.getX(),items.getY()))
					{
						// for scenarios when the monster has passed over the exit erasing the tile reset the marker
						dungeon.changemap(items.getX(),items.getY(),items.getSprite());
					}
				}
			}

		}
	}
	
	/*
	 * This method just handles monster collisions
	 * nothin special 
	*/
	private static void monsterColli()
	{
		for(Monster monster : allMonsters)
		{
			// MOVE THE MONSTER  AFTER PLAYER TURN
			// MONSTER MOVES ONLY IF THEY ARE ALIVE 
			if(monster.getExists()) 
			{

				// takes in a random int to move monster in 1 of 4 directions 1-up, 2-right, 3-down, 4-left
				int randomInt = rand.nextInt(5-1) + 1;


				// clear last monster position
				dungeon.changemap(monster.getX(), monster.getY(), dungeon.getsprite());

				// move the monster in a random direction based on the int 
				monster.moveObject(randomInt);

				// update map after moving monster
				dungeon.changemap(monster.getX(), monster.getY(), monster.getSprite());	

				// this is just safety to prevent the monster from erasing the exit symbol
				if(!monster.compareposition(dungeon.getExitx(),dungeon.getExity()))
				{
					// for scenarios when the monster has passed over the exit erasing the tile reset the marker
					dungeon.changemap(dungeon.getExitx(),dungeon.getExity(),dungeon.getExitSymbol());
				}


			}


			// CHECK IF THE PLAYER OR MONSTER HAVE TOUCHED EACHOTHER
			// IF SO DO SOMETHING 
			if (monster.getExists() == true)
			{
				if((player.compareposition(monster.getX(), monster.getY())) || (monster.compareposition(player.getX(), player.getY()))  )
				{
					System.out.println("A Battle has begun!");
					battleScript(monster);
					// here would probably be a fight where the monster has a chance to fight the player
					dungeon.changemap(player.getX(),player.getY(), '?');

					// maybe spawn the princess after a monster has been defeated randomly on the map 
				}
			}
		}
	}

	private static void battleScript(Monster currMonster)
	{
		// while the player and monster havent died continue the fight 
		while((player.getHealth() > 0) && (currMonster.getHealth() > 0))
		{
			// print out a break and  status of enemies and players (health)
			System.out.println("\n\n---- BATTLE ---- \n\n\n\n");
			System.out.println(currMonster.getName() + " has " + currMonster.getHealth() + " health points remaining");
			System.out.println("\nYou have " + player.getHealth() + " health points left");
			
			// make the monster taunt the player
			currMonster.sayQuote();
			
			// starts off player side of the fight player chooses action
			player.battleAction(currMonster);
			
			// make next monster action random 
			int randomInt = rand.nextInt(4-1) + 1;
			
			// make the monster preform its action
			currMonster.battleAction(randomInt,player);
		}
		
		if(player.getHealth() <= 0)
		{
			System.out.println("YOU DIED... GAME OVER!");
			System.exit(0);
			return;
		}
		
		if(currMonster.getHealth() <= 0)
		{
			
			// the vampire drops something special
			if(currMonster.getSprite() == 'V')
			{
				System.out.println("The vampire dropped a note saying.. Morse Code Translator..? it has a link too!");
				System.out.println("https://morsecode.world/international/translator.html");
				System.out.println("Wonder what that could mean... ");
			}
			
			// remove the monster from the map and set its existence to false
			currMonster.setExists(false);
			dungeon.changemap(currMonster.getX(), currMonster.getY(), dungeon.getsprite());
		}
	}
	/*
	 * This method just holds all the text and needed code 
	 * for the start of the game prompting user to if they want to start
	 * the game and such 
	 */
	private static void gameStart()
	{
		// check if the user wants to continue the game 
		if(firstrun == true)
		{
			System.out.println("do you want to start the game (probably should)"
					+ "\n1. yes"
					+ "\n2. no "
					);

			// prompt them for input 
			choice = input.nextInt();
		}
		else
		{
			// if it goes here they already went through the game once and want to continue
			choice = 1;
		}




		// act on choice by user to continue or stop game
		if(choice == 1)
		{
			// continue game loop 
			gamerunning = true;
		}
		else
		{
			// end the game loop
			if(firstrun == true)
			{
				System.out.println("aight..... cya? tell me when you do");

			}
			else 
			{
				System.out.println("Thank you for playing hope you had fun!");

			}

			// stops the game
			gamerunning = false;
			System.exit(0);
		}
	}

	/*
	 * This handles every part of how the game will play 
	 * aka creating the maps, players, monsters items and their interactions 
	 * with each other.
	 */
	private static void gameScript()
	{
		RandStart();
		/*
		 * This while loop is what the entire game is based on 
		 * basic case is "if the player wants to keep playing. Keep the game running"
		 */
		while (gamerunning == true){

			
			/*
			 * To help with things like Organization methods for collisions with 
			 * the player, items and monsters have been made into methods
			*/
			
			// print the current map with all changes done
			dungeon.printmap();


			// MOVE THE PLAYER
			// clear last player position
			dungeon.changemap(player.getX(), player.getY(), dungeon.getsprite());
			//prompt player for movement
			player.doAction();
			// set new player x and y on the map 
			dungeon.changemap(player.getX(), player.getY(), player.getSprite());

			// these are just basic calls to handle item and monster collisions
			monsterColli();
			itemColli();



			// if the player is on the exit tile with all conditions met prompt for newgame
			///////////// TEMP EXIT VALUE ///////////////////
			if(player.compareposition(dungeon.getExitx(),dungeon.getExity()))
			{
				// congratulate the user and ask if they want to continue 
				System.out.println("\nCONGRATULATIONS YOUVE REACHED THE EXIT!"
						+ "\ndo you want to play again?"
						+ "\n1. yes"
						+ "\n2. no");

				// take in user choice
				choice = input.nextInt();

				// act on that choice
				if(choice == 1)
				{
					// this will change some text later on to improve "immersion"
					firstrun = false;
					RandStart(); // randomize the game
					gameStart(); // start the game 
				}
				else
				{
					// break out of the while loop if we choose not to continue
					System.out.println("Thank you for play .. -. --. wait... what was that?");
					
					// what does this mean.. 
					System.out.println("-.-. .-. . -.. .. - ... ---... / ... .--. . -.-. .. .- .-.. / - .... .- -. -.- ... / - --- / ... - . .--. .... . -. .. . / -- . -.-- . .-. / -.-. .-. . .- - --- .-. / --- ..-. / - .-- .. .-.. .. --. .... - / .-- .... .. -.-. .... / .. ... / .-- .... . .-. . / .. / --. --- - / .- .-.. .-.. / -- -.-- / --.- ..- --- - . ... / ..-. .-. --- -- .-.-.- / ... .--. . -.-. .. .- .-.. / - .... .- -. -.- ... / - --- / .- .--- / -- -.-- / ..-. .-. .. . -. -.. / .-- .... --- / ... ..- --. --. . ... - . -.. / - .... . / .. -.. . .- / - .... .- -. -.- / -.-- --- ..- / ..-. --- .-. / .--. .-.. .- -.-- .. -. --. / .. / ... .-- . .- .-. / .. / -.. .. -.. / - .... .. ... / ..-. --- .-. / ..-. ..- -. / .- -. -.. / -.. .. -.. / -. --- - / .-. . .- -.. / .- / ... .. -. --. .-.. . / -... --- --- -.- -.-.--");
					if(allMonsters.get(1).getExists() == false)
					{
						// if the vampire doesnt exist which drops something special 
						
					}
					gamerunning = false;
				}
			} // end of reached exit


		} // end of main while loop 
	}



	/*
	 * This method has the sole purpose of organizing events that happen in the 
	 * game 
	 */
	private static void PlayGame()
	{
		gameStart(); // call the game start
		gameScript(); // call the game's script
	}



	public void main(String[] args)
	{
		PlayGame(); // start the game
	} // end of main


} // end of class 
