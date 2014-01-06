import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class PaintSolution extends JPanel 
{
	public static char[][] boardSolution = new char[12][5];
	private int width, height;
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		int squareWidth = width/25;
		int squareHeight = squareWidth;
		
		for(int x = 0;x < boardSolution.length ;x++)
		{
			for(int y = 0;y< boardSolution[x].length;y++)
			{
				switch(boardSolution[x][y])
				{
					case 'P' :
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
						g2d.setColor(Color.LIGHT_GRAY);
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
					
					case 'a':
					{
						g2d.setColor(new Color(180,211,213));
						break;
					}
					case 'b':
					{
						g2d.setColor(new Color(80,120,120));
						break;
					}
					case 'c':
					{
						g2d.setColor(new Color(120,150,120));
						break;
					}
					case 'O':
					{
						g2d.setColor(Color.WHITE);
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
	
	public PaintSolution(int width, int height)
	{
		
		for(int i=0; i<boardSolution.length; i++){
			for(int j=0; j<boardSolution[0].length; j++){
				boardSolution[i][j]='O';
			}
		}
		
		setPreferredSize(new Dimension(width, height));
		this.width = width;
		this.height = height;
	}
}
