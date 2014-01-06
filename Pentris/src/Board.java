 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*; 
import java.io.FileNotFoundException; 
import java.util.*; 

import javax.swing.*;
import javax.swing.Timer;
  
  
public class Board extends JPanel implements ActionListener{ 
    private final int HEIGHT=20; 
    private final int WIDTH=10; 
    private char[][] board=new char[HEIGHT][WIDTH]; 
    private char[][] boardBeforeMove=new char[HEIGHT][WIDTH]; 
    private char[][] boardAfterMove=new char[HEIGHT][WIDTH]; 
    private int score=0; 
    private char[][] currentPentomino; // 
    private char[][] nextPentomino; //
    private int currentY; 
    private int currentX; 
    private int lastY;  //top most Y coordinate of last pentomino 
    private int points;
    private boolean legality;
    private Pentomino shape = new Pentomino();
    private int delay=400; //milliseconds
    private final int TIME=400;
    private Timer timer;
    private enum Direction {LEFT, RIGHT};
    private String currentLevel="Level 1";
    private int[] levels={5,10,15,20,25,30,35,40};
    private String toNextLevel="To next level: " + levels[0] +  " points";
    private Timer moveDown;
	boolean gameOverBoolean;
	String scoreString=score+"";
	Highscore object = null;
	JTextField text = new JTextField();
	private JPanel pausePanel = new PaintPause(700, 90);
	private JFrame pauseFrame = new JFrame();
	private JPanel gameOverPanel = new PaintGameOver(700,90);
	private JFrame gameOverFrame = new JFrame();
	private Timer once;
	
	
    

    public Board(){     	
    	timer =new Timer(TIME,this);
    	pauseFrame.setLocation(538,250);
    	pauseFrame.setSize(new Dimension(340, 90)); 
    	pauseFrame.add(pausePanel);
    	pauseFrame.setUndecorated(true);
    	
    	gameOverFrame.setLocation(538,250);
    	gameOverFrame.setSize(new Dimension(340, 220)); 
    	gameOverFrame.add(gameOverPanel);
    	gameOverFrame.setUndecorated(true);
    	
        currentPentomino = shape.getPentomino(); 
        nextPentomino=shape.getNextPentomino();
        setPentomino(currentPentomino); 
        moveDown();
        legality=false;
    	timer.start();
        
        this.points=1;   
        this.lastY=0; 
          
        for(int i=0; i<this.HEIGHT; i++){ 
            for(int j=0; j<this.WIDTH; j++){ 
                board[i][j]='O'; 
            } 
        } 
          
        overWriteBoardAfterMove(); 
        overWriteBoardBeforeMove();
        currentX=WIDTH/2-1; 
        currentY=0; 
          
          
         
       	board[18][0]='P'; 
        board[18][1]='T'; 
        board[18][2]='P'; 
        board[18][7]='T';
        board[18][4]='V';
        board[18][3]='F';
        board[18][6]='P';
        board[18][8]='V';
        board[18][9]='I';
        
       	board[19][0]='P'; 
        board[19][1]='T'; 
        board[19][2]='P'; 
        board[19][7]='T';
        board[19][4]='V';
        board[19][3]='F';
        board[19][6]='P';
        board[19][8]='V';
        board[19][9]='I';
        
        board[17][0]='P'; 
        board[17][1]='T'; 
        board[17][2]='P'; 
        board[17][3]='I'; 
        board[17][7]='W'; 
        board[17][8]='I'; 
        board[17][9]='V'; 
       
          
          
          
          
        /**  
         * Returns current board 
         * @return board current board 
         */
    } 

   
    public char[][] getBoard(){ 
        return board; 
    } 
      
   /** 
         * Sets current pentomino 
         * @param pentomino the shape that is set to current pentomino 
         */
    public void setPentomino(char[][] pentomino){ 
        this.currentPentomino=pentomino; 
    } 
      
      
      
        /** 
         * Returns current pentomino 
         * @return currentPentomino the actual shape 
         */
    public char[][] getPentomino(){ 
        return this.currentPentomino; 
    } 
          
      
      
      
        /** Returns the score from current game 
        *  @return score - score from current game 
        */
    public int getScore(){ 
        return this.score; 
    } 
      
      
      
        /** 
         * Makes a deep copy of board and assign it to boardAfterMove 
         */
    public void overWriteBoardAfterMove(){ 
        for(int i=0; i< boardAfterMove.length; i++){ 
            this.boardAfterMove[i]=Arrays.copyOf(board[i], boardAfterMove[0].length); 
        } 
    } 
      
      
      
        /** 
         * Makes a deep copy of boardBeforeMove and assign it to board 
         */
    public void overWriteBoard(){ 
  
        for(int i=0; i< board.length; i++){ 
            this.board[i]=Arrays.copyOf(boardBeforeMove[i], board[0].length); 
        } 
    } 
      
      
      
        /** 
         * Makes a deep copy of boardAfterMove and assign it to boardBeforeMove 
        */
    public void overWriteBoardBeforeMove(){ 
          
        for(int i=0; i< boardBeforeMove.length; i++){ 
            this.boardBeforeMove[i]=Arrays.copyOf(boardAfterMove[i], boardBeforeMove[0].length); 
        } 
    } 
          
      
      
        /**  
         * Shows the current state of the board 
         */
    public void showBoard(){ 
        for(int i=0; i<this.HEIGHT; i++){ 
            for(int j=0; j<this.WIDTH; j++){ 
                System.out.print(board[i][j]+" "); 
            } 
            System.out.println(); 
        } 
        System.out.println(); 
    } 
      
      
      
    public void start(){ 
    	pauseFrame.setVisible(false);
        moveDown.restart();
        timer.restart();
    } 
      
      
      
    public void pause(){ 
    	pauseFrame.setAlwaysOnTop(true);
    	pauseFrame.setVisible(true);
    	PentominoFrame.thePanel.requestFocus();
    	PentominoFrame.thePanel.requestFocusInWindow();
        moveDown.stop();
        timer.stop();
    } 
  
      
      
    /** 
     *Checks whether there are full lines after putting pentomino, clears full lines and adds points to score. 
     *It gives n times more points if there are n cleared lines at once. 
     */
    public void clearFullLines(){ 
        int cntr; 
        int cleanedLines=0; 
        int bonus; 
        int maxHeight=5; //max height of pentomino 
        level();

        for(int i=this.lastY; i<board.length && i<this.lastY+maxHeight; i++){  
            cntr=0; 
            for(int j=0; j<board[0].length; j++){ 
                if(board[i][j]!='O') 
                    cntr++; 
                if(cntr==this.board[0].length){ 
                    cleanedLines++; 
                      
                    for(int k=i; k>0; k--){ 
                        for(int l=0; l<board[0].length; l++){ 
                            this.board[k][l]='O'; 
                            this.board[k][l]=this.board[k-1][l]; 
                        } 
                    } 
                } 
                  
            } 
        } 
        bonus=cleanedLines; 
        this.score=this.score+this.points*bonus*cleanedLines; 
          
        PentominoFrame.refreshScore();
        PentominoFrame.frame.repaint();
        currentY=0;
    } 
    
    


    public boolean isRotateLegal(){
    	if(currentX + currentPentomino.length > WIDTH|| currentY == 0 )
    		return false;
    	for(int i = 0; i < currentPentomino.length; i++)
    	{
    		for(int j = 0; j < currentPentomino[0].length; j++) 
    		{
    			if(boardBeforeMove[currentY + j][currentX + i] != 'O')
    				return false;
    		}
    	}
    	return true;
    }
    
    
 
    /** 
     * Checks if the move down is legal 
     * @return legal says whether the move is legal or not 
     */
    
    public boolean isMoveRightLegal(){
    	if (currentX + currentPentomino[0].length == WIDTH || currentY+currentPentomino.length == HEIGHT)
    		return false;
    	int lastCharInRow = 0;
    	for(int i = 0; i < currentPentomino.length; i++)
    	{
    		for(int j = currentPentomino[0].length-1; j >= 0; j--)
    		{
    			if(currentPentomino[i][j] != 'O'){
    				lastCharInRow = j;
    				break;
    			}
     		}
			try
			{
				if(board[currentY + i][currentX + lastCharInRow +1] != 'O')
					return false;
			}
			catch(Exception e){}
    	}
    	    	
    	return true;
    }
    
    public boolean isMoveLeftLegal(){
    	if (currentX == 0 || currentY+currentPentomino.length == HEIGHT )
    		return false;
    	int firstCharInRow = 0;
    	for(int i = 0; i < currentPentomino.length; i++)
    	{
    		for(int j = 0; j < currentPentomino[0].length; j++)
    		{
    			if(currentPentomino[i][j] != 'O')
    			{
    				firstCharInRow = j;
    				break;
    			}
    		}
    		try
    		{
    			if(board[currentY + i][currentX+firstCharInRow-1]!= 'O')
					return false;	
    		}
    		catch(Exception e){}
    	}
    	return true;
    }
      
    public boolean isMoveDownLegal(){ 
        if (currentY+currentPentomino.length == HEIGHT){

        	return false;
        }
    	int lastCharInColumn =  0;
        	for(int j = 0; j < currentPentomino[0].length; j++)
        	{
        		for(int i = currentPentomino.length-1; i>=0; i--)
        		{ 
        			if(currentPentomino[i][j] != 'O') { 
        				lastCharInColumn = i;
        				break;
        			}
        			
        		}
    			try
    			{
    				if(board[currentY + lastCharInColumn + 1][currentX + j] != 'O'){
    					return false;		
    				}
    				
    			}
    			catch(Exception e){}
        	}
        	legality = false;
        	return true;
        }
    
    /**  
     * Moves down pentominoes 
     */
    public void moveDown(){  
    	moveDown = new Timer(delay, this);
        if (!gameOverBoolean)
        	moveDown.start();
        	moveDown.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent evt){

                	PaintObject.board = board;
                	PentominoFrame.thePanel.repaint();
                    boolean moveDownLegal=isMoveDownLegal(); 

                      
                    if(currentY==0){  
                    	overWriteBoardAfterMove();  
                    	overWriteBoardBeforeMove();  
                    	currentX=WIDTH/2-1;  
                    		if(!moveDownLegal){
                    			gameOver();
                    		}
                    		
                    }
                                         
                    if(!moveDownLegal){  	
                        level();
                        lastY=currentY;
                        currentY=0;  
                        moveDown.stop();  
                        clearFullLines();      
                        overWriteBoardAfterMove();
                        overWriteBoardBeforeMove();
                        overWriteBoard();
                        
                        legality=true;
                    }  
                    
                    else{  
                        overWriteBoardAfterMove();  
                        overWriteBoard();  
                        currentY++; 
                        addShape();
                    }
        		} 

        });
    }
    
   
    public void moveLeft(){ 
    	if(isMoveLeftLegal() && currentY!=0 ){
    		overWriteBoardAfterMove();  
        	overWriteBoard();
        	currentX--;
        	addShape();
        	PentominoFrame.frame.repaint();
    	}
   } 
     
    public void moveRight(){ 
    	if(isMoveRightLegal() && currentY!=0 ){
    		overWriteBoardAfterMove();  
    		overWriteBoard();
    		currentX++;
    		addShape();
    		PentominoFrame.frame.repaint();
    	}
    } 
      
    public void moveFullDown(){ 
        while(isMoveDownLegal()){ 
            overWriteBoardAfterMove();  
            overWriteBoard();  
            currentY++;  
            addShape();  
        } 
    } 
      
    /** 
     *  Adds currentPentomino to the board on currentX and currentY coordinates  
     */
    public void addShape(){
        for(int i = 0; i < currentPentomino.length; i++) { 
            for(int j = 0; j < currentPentomino[i].length; j++) {
            		if(currentPentomino[i][j]!='O')
                    board[currentY+i][currentX+j] = currentPentomino[i][j]; 
            } 
        } 
    } 

	public void actionPerformed(ActionEvent arg0) {
        level();
		if(legality){
			legality=false;
			shape.getRandomPentomino();
			currentPentomino = shape.getPentomino();
			nextPentomino=shape.getNextPentomino();
			currentX=WIDTH/2-1;
			moveDown();
		}
		
	}
	public char[][] getNextPentomino(){
		return nextPentomino;
	}
	
	public void rotatePentomino(Direction d)
	{
		int lengthRotated = currentPentomino[0].length;
		int widthRotated = currentPentomino.length;
		
		char[][] rotatedShape = new char[lengthRotated][widthRotated];
		
		for(int i = 0; i < lengthRotated; i++)
			for(int j = 0; j < widthRotated; j++)
				if (d == Direction.LEFT)
					rotatedShape[i][j] = currentPentomino[j][(lengthRotated - 1) - i];
				else
					rotatedShape[i][j] = currentPentomino[(widthRotated - 1) - j][i];
		
		overWriteBoard();
		currentPentomino = rotatedShape.clone();
		addShape();
		
	}
	

	/**
	Rotates pentomino clockwise.
	 */
	public void rotatePentominoRight()
	{
		if(isRotateLegal())
			rotatePentomino(Direction.RIGHT);
	}

	/**
		Rotates pentomino counterclockwise.
	*/
	
	public void rotatePentominoLeft()
	{
		if(isRotateLegal())
			rotatePentomino(Direction.LEFT);
	}
	
	public void gameOver(){
		final JFrame nameWindow=new JFrame();
		nameWindow.setTitle("GAME OVER");
    	nameWindow.setSize(220,250);
    	nameWindow.setLocation(970,250);
    	nameWindow.setUndecorated(true);
    	nameWindow.setAlwaysOnTop(true);
    	gameOverFrame.setAlwaysOnTop(true);
    	gameOverFrame.setVisible(true);
		gameOverBoolean=true;
		moveDown.stop();
        timer.stop();
        
    	String number=score+"";
    	int numberOfDigits=number.length();
    	int zeros = 5-numberOfDigits;
    	scoreString="";
    	for(int i=0; i < zeros; i++){
    		scoreString=scoreString+ "0";
    	}
    	scoreString=scoreString+score;
        try {
			object = new Highscore();
		} catch (FileNotFoundException e) {}
        if(scoreString.compareTo(object.getLastScore())>0){
        	
        	Font d = new Font("Dialog", Font.BOLD, 15);
        	Font f = new Font("Dialog", Font.BOLD, 20);
        	JButton buttonOk = new JButton("OK");
        	buttonOk.setPreferredSize(new Dimension(100,30));
        	JPanel panel = new JPanel();
        	JPanel buttonPanel = new JPanel();
        	JLabel label = new JLabel("Enter your name: ");
        	label.setFont(d);
        	buttonOk.addActionListener(new java.awt.event.ActionListener(){


				@Override
        	    public void actionPerformed(java.awt.event.ActionEvent evt) {
					String name = text.getText();
					scoreString = scoreString + " - " + name;
		        	object.setTop10(scoreString);
		        	nameWindow.dispose();
		        	PentominoFrame.frame.dispose();
		        	gameOverFrame.dispose();
		        	
		        	PentominoFrame.thePanel = null;
		            board=null;
		            PentominoFrame.scorePanel = null;; 
		            PentominoFrame.main =  null; 
		            PentominoFrame.frame =  null;
		            PentominoFrame.score =  null; 
		            PentominoFrame.nextPent =  null; 
		            PentominoFrame.level =  null;
		            PentominoFrame.c=  null;
		            PentominoFrame.t=  null;
		        	
		        	new Menu();
        	    }

        	});
        	
        	JButton buttonCancel = new JButton("Cancel");
        	buttonCancel.setPreferredSize(new Dimension(100,30));
        	buttonCancel.setFont(d);
        	buttonCancel.addActionListener(new java.awt.event.ActionListener(){

				@Override
        	    public void actionPerformed(java.awt.event.ActionEvent evt) {
					nameWindow.dispose();
					gameOverFrame.dispose();
					PentominoFrame.frame.dispose();
		        	
		        	PentominoFrame.thePanel = null;
		            board=null;
		            PentominoFrame.scorePanel = null;; 
		            PentominoFrame.main =  null; 
		            PentominoFrame.frame =  null;
		            PentominoFrame.score =  null; 
		            PentominoFrame.nextPent =  null; 
		            PentominoFrame.level =  null;
		            PentominoFrame.c=  null;
		            PentominoFrame.t=  null;
		            
		            new Menu();
        	    }

        	});
        	text.setPreferredSize(new Dimension(150, 80));
        	text.setFont(f);
        	buttonOk.setFont(d);
        	text.setBackground(Color.WHITE);
        	buttonOk.setBackground(Color.WHITE);
        	panel.add(label,BorderLayout.PAGE_START);
        	panel.add(text, BorderLayout.CENTER);
        	buttonPanel.add(buttonOk);
        	buttonPanel.add(buttonCancel);
        	panel.setBackground(Color.BLACK);
        	buttonPanel.setBackground(Color.BLACK);
        	label.setForeground(Color.WHITE);
        	panel.add(buttonPanel,BorderLayout.PAGE_END);
        	nameWindow.add(panel);
        	nameWindow.setVisible(true);
        }
        else{
        	
        	once = new Timer(3000, new ActionListener(){
        		public void actionPerformed(ActionEvent evt){
        			PentominoFrame.frame.dispose();
        			gameOverFrame.dispose();
        			
        			PentominoFrame.thePanel = null;
        	        board=null;
        	        PentominoFrame.scorePanel = null;; 
        	        PentominoFrame.main =  null; 
        	        PentominoFrame.frame =  null;
        	        PentominoFrame.score =  null; 
        	        PentominoFrame.nextPent =  null; 
        	        PentominoFrame.level =  null;
        	        PentominoFrame.c=  null;
        	        PentominoFrame.t=  null;
        			
        	        new Menu();
        			once.stop();
        		}
        	});
        	
        	once.setInitialDelay(3000);
        	once.start();
        	
        }
        
        
	}
	public void level(){
		if(score < levels[0]){
			currentLevel="Level 1";
			toNextLevel="To next level: " + (levels[0]-score) +" points";
			delay = 400;
		}
		else if(score < levels[1]){
			currentLevel="Level 2";
			toNextLevel="To next level: " + (levels[1]-score) +" points";
			delay = 350;
		}
		else if(score < levels[2]){
			toNextLevel="To next level: " + (levels[2]-score) +" points";
			currentLevel="Level 3";
			delay = 320;
		}
		else if(score < levels[3]){
			toNextLevel="To next level: " + (levels[3]-score) +" points";
			currentLevel="Level 4";
			delay = 300;
		}
		else if(score < levels[4]){
			toNextLevel="To next level: " + (levels[4]-score) +" points";
			currentLevel="Level 5";
			delay = 280;
		}
		else if(score < levels[5]){
			toNextLevel="To next level: " + (levels[5]-score) +" points";
			currentLevel="Level 6";
			delay = 260;
		}
		else if(score < levels[6]){
			toNextLevel="To next level: " + (levels[6]-score) +" points";
			currentLevel="Level 7";
			delay = 230;
		}
		else if(score >= levels[7]){
			toNextLevel="This is the last level";
			currentLevel="Level 8";
			delay = 200;
		}
	}
	
	public void moveOneSquare(){
		if(isMoveDownLegal()){
			overWriteBoardAfterMove();  
			overWriteBoard();  
			currentY++; 
			addShape();
			PentominoFrame.frame.repaint();
		}
	}
	
	public String getLevel(){
		return currentLevel;
	}
	public String getToNextLevel(){
		return toNextLevel;
	}
	
	public static void main(String[] args){ 
		new Menu(); 
	}

	
} 