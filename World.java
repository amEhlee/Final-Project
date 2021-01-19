
public class World{
	

	// set what icon will fill the tiles on the world map 
	static char w = '~';
	
	// this sets the basic map tiles to the char 'w'
	// allows for easy switching of tiles and prevents hard code
	// this will act as the BASE map with no walls or anything 
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
	
	
	// set the exit position
	private int exitx;
	private int exity;
	private char exitSymbol;
	
	// i have no clue why these are here except a reminder 	
	// ill delete later 
	private int mapRows; // horizontal
	private int mapCols; // vertical
	
	public static char[][] getmap()
	{
		// this updates the currentmap to the most recent map state
		char[][] currentmap = map;
		
		// return the most recent map state
		return currentmap;
	}
	
	public static void resetmap()
	{
		// store the original map in a variable. THIS MAP IS EMPTY
		char originalmap[][] = 		
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
		
		// set the current map to the original map 
		map = originalmap;

	}
	
	/*
	 * This allows up to change specific values on the map 
	 * this will ultimately be used to move objects around the map 
	 * to place items on the map as well as obstacles 
	 * this works by taking in a new x and y for the object
	 * and moving the symbol of the object given to that
	 * specific position 
	*/
	public void changemap(int x, int y, char symbol)
	{
		// get the most recently updated map 
		char[][] currentmap = getmap();

		// add the symbol to this position on the map 
		currentmap[y][x] = symbol;

		// the map to its updated state 
		// this will later be called so it needs to be MOST RECENT
		map = currentmap;
		
	}
	
	/*
	 * This iterates through the double array of rows and
	 * columns and every value into a big table effectively
	 * creating a "map" that the player and other objects in 
	 * moving their index can interact with 
	*/
	public void printmap()
	{
		// this calls the CURRENT map with all previous changes applied 
		char[][] currentmap = getmap();

		// for better organization add a break line 
		System.out.println("\n\n------------------ BREAK --------------------\n");
		
		// both these for loops iterate through the array and print each value
		for(int i = 0; i < currentmap.length; i ++)
		{
			for(int j = 0; j < currentmap.length; j ++)
			{
				
				System.out.print(currentmap[i][j] + "   ");
			}
			// when you reach the end of a row move down a column
			System.out.println("\n");
		}
	}
	
	
	
	// SETTERS AND GETTERS
	// this return the current sprite being used for map tiles
	public char getsprite() 
	{
		// return current tile id 
		return w;
	}
	
	// get position of the exit on the map 
	public int getExitx() {return exitx;}
	public int getExity() {return exity;}
	public char getExitSymbol() {return exitSymbol;}
	
	// setter for the exit on the map 
	public void setExit(int newexitx, int newexity, char exitsym) 
	{
		exity = newexity;
		exitx = newexitx;
		exitSymbol = exitsym;
	}



	
}
