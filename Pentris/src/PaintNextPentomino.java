import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



public class PaintNextPentomino extends JPanel{

	private char[][] shape;
	private int shapeWidth, shapeHeight;

	public PaintNextPentomino(int width, int height, char[][] pent){
		setPreferredSize(new Dimension(width, height));
		this.shapeWidth = width;
		this.shapeHeight = height;
		this.shape=pent;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
	
	
	int squareWidth = shapeWidth/9;
	int squareHeight = squareWidth;
	
	
	for(int x = 0;x < shape.length ;x++)
	{
		for(int y = 0;y< shape[x].length;y++)
		{
			switch(shape[x][y])
			{
				case 'P':
				{
					g2d.setColor(new Color(0,0,128));
					break;
				}
				case 'X':
				{
					g2d.setColor(new Color(112,128,144));
					break;
				}
				case 'F':
				{
					g2d.setColor(new Color(127,255,212));
					break;
				}
				case 'V':
				{
					g2d.setColor(Color.GREEN);
					break;
				}
				case 'T':
				{
					g2d.setColor(new Color(255,79,0));
					break;
				}
				case 'Z':
				{
					g2d.setColor(new Color(255,215,0));
					break;
				}
				case 'U':
				{
					g2d.setColor(new Color(140,0,0));
					break;
				}
				case 'W':
				{
					g2d.setColor(new Color(0,120,0));
					break;
				}
				case 'N':
				{
					g2d.setColor(Color.RED);
					break;
				}
				case 'L':
				{
					g2d.setColor(Color.WHITE);
					break;
				}
				case 'Y':
				{
					g2d.setColor(new Color(150,70,0));
					break;
				}
				case 'I':
				{
					g2d.setColor(new Color(50,160,255));
					break;
				}
				
				case 'p':
				{
					g2d.setColor(new Color(140,240,90));
					break;
				}
				
				case 'f':
				{
					g2d.setColor(new Color(230,240,80));;
					break;
				}
				
				case 'z':
				{
					g2d.setColor(new Color(220,180,120));;
					break;
				}
				
				case 'w':
				{
					g2d.setColor(new Color(140,30,115));;
					break;
				}
				
				case 'n':
				{
					g2d.setColor(new Color(150,20,40));;
					break;
				}
				
				case 'y':
				{
					g2d.setColor(new Color(5,60,85));;
					break;
				}
				
				case 'l':
				{
					g2d.setColor(new Color(190,80,45));;
					break;
				}
				
				case 'O':
				{
					g2d.setColor(Color.BLACK);
					break;
				}
				
			}
			//Add KPCP feature in the program - play the pentris backwards 
			g2d.fillRect(squareHeight*y,squareWidth*x, squareWidth, squareHeight);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(squareHeight*y,squareWidth*x, squareWidth, squareHeight);
		}
	}
}
}
	


