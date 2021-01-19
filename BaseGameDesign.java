/**
 * Here is the base game design for my rpgs game. 
 * This was used to get a basic idea for the concept and how I wanted it to play from the console
 * Hardest points were figuring out how to a 2d array for a map. 
 * So think of this as the test run! Just to get the player moving ~
 */

import java.util.ArrayList;
import java.util.Scanner;

public class BaseGameDesign {

	static char w = '~'; // w stands for walls as shown below this is for convenience
	private static char[][] map =
		{
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
				{w,w,w,w,w,w,w,w,w,w,},
		};
	
	static int health = 10;
	static int attack = 3;
	static int magic = 15;
	static int defense = 3;
	static ArrayList<String> inventory = new ArrayList<String>();

	public static char[][] getmap() {

		// set up a new variable storing the current map 
		char[][] currentmap = map;
		return currentmap;
	}

	// takes in arguments for which spot in the 2d array
	public static void changemap(int x, int y, char symbol) {
		char[][] currentmap = getmap();

		// change our current map to fit our changes
		currentmap[y][x] = symbol;

		// set the NEW MAP equal to our changes
		map = currentmap;

	}

	public static void printmap()
	{
		char[][] currentmap = getmap();

		
		// prints the entire array or tile map 
		
		for(int i = 0; i < currentmap.length; i ++)
		{
			for(int j = 0; j < currentmap.length; j ++)
			{
				System.out.print(currentmap[i][j] + "   ");
			}
			System.out.println("\n");
		}
	}
	
	public static void printstats()
	{
		System.out.println
						(
						  "\nCurrent stats are: \n"
						+ "health = " + health + "\n"
						+ "attack = " + attack + "\n"
						+ "defense = " + defense + "\n"
						+ "magic = " + magic + "\n"
						);
		
		System.out.println("in your inventory you currently have :");
		for(int i = 0; i < inventory.size(); i ++)
		{
			System.out.println(inventory.get(i));
		}
	}

	public static void main(String args[])
	{
		System.out.println("where do you want to move");

		int playerx = 3;
		int playery = 2;
		char playerIcon = '\u00D8';

		System.out.println("first map ------------");
		printmap();

		System.out.println("\n\n------------------ BREAK --------------------\n");

		System.out.println("the player has come at x " + playerx + " and y at " + playery);
		changemap(playerx,playery,playerIcon);

		System.out.println("2nd map after changes ----------");
		printmap();

		System.out.println("\n\n------------------ BREAK --------------------\n");

		System.out.println("3rd map after changes ----------");
		int itemx = 5;
		int itemy = 6;
		boolean itemexists = true;
		changemap(itemx,itemy,'\u0204');
		printmap();

		// move the player around
		boolean wantto = false;

		Scanner input = new Scanner(System.in);
		int choice = 0;

		System.out.println("start the simulation? (1 means yes)");
		choice = input.nextInt();
		if(choice == 1)
		{
			wantto = true;
		}

		while (wantto)
		{
			System.out.println
			("where move next? "
					+ "\n1. up "
					+ "\n2. right"
					+ "\n3. down"
					+ "\n4. left"
					);
			choice = input.nextInt();
			if(choice == 1)
			{
				// set last index to a blank tile
				changemap(playerx,playery,'~');
				
				// Change players x/y depending on cmd and update their position
				playery -= 1;
				changemap(playerx,playery,playerIcon);
			}
			else if(choice == 2)
			{
				// set last index to a blank tile
				changemap(playerx,playery,'~');
				playerx += 1;
				
				// Change players x/y depending on cmd and update their position
				changemap(playerx,playery,playerIcon);
			}
			else if(choice == 3)
			{
				// set last index to a blank tile
				changemap(playerx,playery,'~');
				
				// Change players x/y depending on cmd and update their position
				playery += 1;
				changemap(playerx,playery,playerIcon);
			}
			else if(choice == 4)
			{
				// set last index to a blank tile
				changemap(playerx,playery,'~');
				
				// Change players x/y depending on cmd and update their position
				playerx -= 1;
				changemap(playerx,playery,playerIcon);
			}

			if(playerx == itemx && playery == itemy && itemexists)
			{
				System.out.println("ITEREREREMRMERM FOUNNDDEDED");
				System.out.println("YOUR STATS GO UP");
				System.out.println("you now have a long sword");
				inventory.add("long sword");
				inventory.add("bagel");
				
				itemexists = false;
			}
			
			printstats();

			System.out.println("\n\n------------------ BREAK --------------------\n");
			printmap();

			System.out.println("continue simulation (1 for yes)");
			choice = input.nextInt();

			if(choice == 1)
			{
				wantto = true;
			}
			else
			{
				wantto = false;
			}
		}

	}

}
