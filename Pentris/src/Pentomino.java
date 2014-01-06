import java.util.*;
public class Pentomino {
	
	private static ArrayList <char[][]> allShapes = getShapes();
	static ArrayList <char[][]> allShapes2 = getShapes2();
	private static char[][] currentShape;
	private static char[][] nextShape;
	private enum Direction {LEFT, RIGHT};

	/**
		Constructs the pentomino shapes.
	*/
	public static ArrayList<char[][]> getShapes() 
	{  
		// All letters and their rotations only 
		//variations of a letter are represented as double character arrays,with a triple character array containing all those variations 
		
		//P 
		char[][] P = {{'P','P'},{'P','P'},{'P','O'}};
		
		//p 
		char[][] p = {{'p','O'},{'p','p'},{'p','p'}};
				 
		//X 
		char[][] X = {{'O','X','O'},{'X','X','X'},{'O','X','O'}}; 
				 
		//F 
		char[][] F = {{'O','F','F'}, {'F','F','O'}, {'O','F','O'}};
		
		//f
		char[][] f = {{'f','f','O'}, {'O','f','f'}, {'O','f','O'}};
				  
		//V 
		char[][] V = {{'V','O','O'}, {'V','O','O'},{'V','V','V'}}; 
  		  
		//T 
		char[][] T = {{'T','T','T'}, {'O','T','O'}, {'O','T','O'}}; 
		  
		//Z 
		char[][] Z = {{'Z','Z','O'}, {'O','Z','O'}, {'O','Z','Z'}};    
		  
		//z 
		char[][] z = {{'O','z','z'}, {'O','z','O'}, {'z','z','O'}};  
		
		//U 
		char[][] U = {{'U','O','U'}, {'U','U','U'}}; 
		       
		//W
		char[][] W= {{'W','O','O'},{'W','W','O'},{'O','W','W'}}; 
		
		//w
		char[][] w= {{'O','O','w'},{'O','w','w'},{'w','w','O'}}; 

		//N 
		char[][] N={{'N','N','O','O'},{'O','N','N','N'}}; 
		
		//n
		char[][] n={{'O','n','n','n'},{'n','n','O','O'}};
		  
		//L
		char[][] L={{'O','O','O','L'},{'L','L','L','L'}};
		
		//l
		char[][] l={{'l','l','l','l'},{'O','O','O','l'},};
		  
		//Y
		char[][] Y={{'O','Y'},{'Y','Y'},{'O','Y'},{'O','Y'}};
		
		//y
		char[][] y={{'y','O'},{'y','y'},{'y','O'},{'y','O'}};
		  
		//I
		char[][] I = {{'I'},{'I'},{'I'},{'I'},{'I'}};
		  
		//0
		char[][] O = {{}};
	     
		//create an ArrayList containing all pentominoes 
		ArrayList <char[][]> allShapes = new ArrayList<char[][]>();  
		allShapes.add(0, V); 
		allShapes.add(1, F); 
		allShapes.add(2, X);
		allShapes.add(3, N);
		allShapes.add(4, T); 
		allShapes.add(5, U);
		allShapes.add(6, Z);
		allShapes.add(7, W);
		allShapes.add(8, Y);
		allShapes.add(9, P); 
		allShapes.add(10, I); 
		allShapes.add(11, L); 
		allShapes.add(12, p);
		allShapes.add(13, f);
		allShapes.add(14, z);
		allShapes.add(15, w);
		allShapes.add(16, n);
		allShapes.add(17, y);
		allShapes.add(18, l);
		allShapes.add(19, O);
		      
		return allShapes; 
	} 
	
	public static ArrayList<char[][]> getShapes2() 
	{  
		// All letters and their rotations only 
		//variations of a letter are represented as double character arrays,with a triple character array containing all those variations 
		
		//P 
		char[][] P = {{'P','P'},{'P','P'},{'P','O'}}; 
				 
		//X 
		char[][] X = {{'O','X','O'},{'X','X','X'},{'O','X','O'}}; 
				 
		//F 
		char[][] F = {{'O','F','F'}, {'F','F','O'}, {'O','F','O'}}; 
				  
		//V 
		char[][] V = {{'V','O','O'}, {'V','O','O'},{'V','V','V'}}; 
  		  
		//T 
		char[][] T = {{'T','T','T'}, {'O','T','O'}, {'O','T','O'}}; 
		  
		//Z 
		char[][] Z = {{'Z','Z','O'}, {'O','Z','O'}, {'O','Z','Z'}};    
		  
		//U 
		char[][] U = {{'U','O','U'}, {'U','U','U'}}; 
		       
		//w
		char[][] W= {{'W','O','O'},{'W','W','O'},{'O','W','W'}}; 

		//N 
		char[][] N={{'N','N','O','O'},{'O','N','N','N'}}; 
		
		//n
		char[][] n={{'O','N','N','N'},{'N','N','O','O'}}; 
		  
		//L
		char[][] L={{'O','O','O','L'},{'L','L','L','L'}}; 
		  
		//Y
		char[][] Y={{'O','Y'},{'Y','Y'},{'O','Y'},{'O','Y'}};
		
		//y
		char[][] y={{'Y','O'},{'Y','Y'},{'Y','O'},{'Y','O'}};
		  
		//I
		char[][] I = {{'I'},{'I'},{'I'},{'I'},{'I'}};
		  
		//0
		char[][] O = {{}};
	     
		ArrayList <char[][]> allShapes2 = new ArrayList<char[][]>();
		allShapes2.add(0, V); 
		allShapes2.add(1, F); 
		allShapes2.add(2, X);
		allShapes2.add(3, n);
		allShapes2.add(4, T); 
		allShapes2.add(5, U);
		allShapes2.add(6, Z);
		allShapes2.add(7, W);
		allShapes2.add(8, y);
		allShapes2.add(9, P); 
		allShapes2.add(10, I); 
		allShapes2.add(11, L); 
		allShapes2.add(12, O);
		      
		return allShapes2; 
	} 

	/**
		Constructs an empty shape.
	*/
	public Pentomino() 
	{
		int shapeIndex = (int)((allShapes.size()-1)*Math.random());
		currentShape = allShapes.get(shapeIndex);
		shapeIndex = (int)((allShapes.size()-1)*Math.random());
		nextShape = allShapes.get(shapeIndex);
	}
	
	
	/**
		Generates a random pentomino.
	*/
	public void getRandomPentomino()
	{
		int shapeIndex = (int)((allShapes.size()-1)*Math.random());
		currentShape=nextShape;
		nextShape = allShapes.get(shapeIndex);
		
	}
	
	/**
		Rotates Pentomino.
		@param d the direction
	*/
	public void rotatePentomino(Direction d)
	{
		int lengthRotated = currentShape[0].length;
		int widthRotated = currentShape.length;
		
		char[][] rotatedShape = new char[lengthRotated][widthRotated];
		
		for(int i = 0; i < lengthRotated; i++)
			for(int j = 0; j < widthRotated; j++)
				if (d == Direction.LEFT)
					rotatedShape[i][j] = currentShape[j][(lengthRotated - 1) - i];
				else
					rotatedShape[i][j] = currentShape[(widthRotated - 1) - j][i];
		
		currentShape = rotatedShape.clone();
	}
	
	/**
		Rotates pentomino clockwise.
	*/
	public void rotatePentominoRight()
	{
		rotatePentomino(Direction.RIGHT);
	}
	
	/**
		Rotates pentomino counterclockwise.
	*/
	public void rotatePentominoLeft()
	{
		rotatePentomino(Direction.LEFT);
	}
	
	/**
		Sets current pentomino shape.
	*/
	public void setPentomino(char[][] shape)
	{
		this.currentShape = shape;
	}
	
	/**
		Gets the current pentomino shape.
		@return the current pentomino shape
	*/
	public char[][] getPentomino()
	{
		return currentShape;
	}
	
	public char[][] getNextPentomino()
	{
		return nextShape;
	}
	public static ArrayList<char[][]> getAllShapes(){
		return allShapes2;
	}
}
