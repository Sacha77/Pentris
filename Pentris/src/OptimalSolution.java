
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*; 
import java.util.*; 

import javax.swing.*;
import javax.swing.Timer;
  
public class OptimalSolution extends JPanel implements ActionListener{ 
    private final int HEIGHT=12; 
    private final int WIDTH=5; 
    private char[][] boardSolution=new char[HEIGHT][WIDTH]; 
    private int currentY; 
    private int currentX; 
    private enum Direction {LEFT, RIGHT};
	private ArrayList <char[][]> allShapes = Pentomino.getShapes2();
    private char[][] currentPentomino = allShapes.get(0);
	private Timer timer;
	private double screenMultiplier = 1;
	private  Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
	private JFrame frame4;
	private int cntr=1;


    public OptimalSolution() throws InterruptedException{   
    	timer=new Timer(700, this);
    	timer.start();
    	a.height *= screenMultiplier;
    	a.width *= screenMultiplier;
    	frame4 = new JFrame("Solution");
		JPanel thePanel = new PaintSolution(a.width-50, a.height-115);
		frame4.add(thePanel);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.setLocation(550,0);
		frame4.setSize(new Dimension(a.width-1080, a.height-90));
		frame4.setVisible(true);

    	addShape();
    	frame4.repaint();
        setPentomino(currentPentomino); 
    
        for(int i=0; i<this.HEIGHT; i++){ 
            for(int j=0; j<this.WIDTH; j++){ 
                boardSolution[i][j]='O'; 
            } 
        } 
        currentX=WIDTH/2-1; 
        currentY=0; 
    }
      
   /** 
         * Sets current pentomino 
         * @param pentomino the shape that is set to current pentomino 
         */
    public void setPentomino(char[][] pentomino){ 
        this.currentPentomino=pentomino; 
    } 

    public boolean isRotateLegal(){
    	if(currentX + currentPentomino.length > WIDTH|| currentY == 0 )
    		return false;
    	for(int i = 0; i < currentPentomino.length; i++)
    	{
    		for(int j = 0; j < currentPentomino[0].length; j++) 
    		{
    			if(boardSolution[currentY + j][currentX + i] != 'O')
    				return false;
    		}
    	}
    	return true;
    }
 
    /** 
     *  Adds currentPentomino to the board on currentX and currentY coordinates  
     */
    public void addShape(){
        for(int i = 0; i < currentPentomino.length; i++) { 
            for(int j = 0; j < currentPentomino[i].length; j++) {
            		if(currentPentomino[i][j]!='O')
                    boardSolution[currentY+i][currentX+j] = currentPentomino[i][j]; 
            } 
        } 
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

		currentPentomino = rotatedShape.clone();
		addShape();
		
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
	
	
	
	public void solution(){
		if(cntr>=16){
			cntr++;
		}
		if(cntr==22){
			frame4.dispose();
			boardSolution=null;
			new Menu();
		}
		if(cntr==15){
			cntr++;
			for(int i=0; i<4; i++){
				for(int j=0; j<5; j++){
					boardSolution[3-i][j]='c';
				}
			}
			frame4.repaint();
		}
		
		if(cntr==14){
			currentX=3;
			currentY=0;
			currentPentomino= allShapes.get(11);
			rotatePentominoLeft();
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==13){
			cntr++;
			for(int i=0; i<5; i++){
				for(int j=0; j<5; j++){
					boardSolution[8-i][j]='b';
				}
			}
			frame4.repaint();
		}
		
		if(cntr==12){
			currentX=4;
			currentY=4;
			currentPentomino= allShapes.get(10);
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==11){
			currentX=0;
			currentY=0;
			currentPentomino= allShapes.get(9);
			rotatePentominoRight();
			addShape();
			frame4.repaint();
			cntr++;
		}

		if(cntr==10){
			currentX=0;
			currentY=1;
			currentPentomino= allShapes.get(8);
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==9){
			currentX=1;
			currentY=1;
			currentPentomino= allShapes.get(7);
			rotatePentominoLeft();
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==8){
			currentX=1;
			currentY=3;
			currentPentomino= allShapes.get(6);
			rotatePentominoLeft();
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==7){
			currentX=2;
			currentY=5;
			currentPentomino= allShapes.get(5);
			rotatePentominoLeft();
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==6){
			currentX=0;
			currentY=5;
			currentPentomino= allShapes.get(4);
			rotatePentominoLeft();
			addShape();
			frame4.repaint();
			cntr++;
		}
		if(cntr==5){
			cntr++;
			for(int i=0; i<3; i++){
				for(int j=0; j<5; j++){
					boardSolution[HEIGHT-i-1][j]='a';
				}
			}
			frame4.repaint();
		}
		
		if(cntr==4){
			currentX=3;
			currentY=8;
			currentPentomino= allShapes.get(3);
			rotatePentominoRight();
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==3){
			currentX=0;
			currentY=7;
			currentPentomino= allShapes.get(2);
			addShape();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==2){
			currentX=1;
			currentY=9;
			currentPentomino=allShapes.get(1);
			rotatePentominoRight();
			addShape();
			PaintSolution.boardSolution = boardSolution;
			frame4.revalidate();
			frame4.repaint();
			cntr++;
		}
		
		if(cntr==1){
			currentX=0;
			currentY=9;
			currentPentomino=allShapes.get(0);
			addShape();
			PaintSolution.boardSolution = boardSolution;
			frame4.revalidate();
			frame4.repaint();
			cntr++;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		solution();
		
	}
}
	
