import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PaintPause extends JPanel{

	public char[][] pause ={{'L','L','L','O','L','L','L','O','L','O','L','O','L','L','L','O','L','L','L'},
							{'L','O','L','O','L','O','L','O','L','O','L','O','L','O','O','O','L','O','O'},
							{'L','L','L','O','L','L','L','O','L','O','L','O','L','L','L','O','L','L','L'},
							{'L','O','O','O','L','O','L','O','L','O','L','O','O','O','L','O','L','O','O'},
							{'L','O','O','O','L','O','L','O','L','L','L','O','L','L','L','O','L','L','L'}};

	private int width, height;

	public PaintPause(int width, int height){
		setPreferredSize(new Dimension(width, height));
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
	
	
		int pauseWidth = width/38;
		int pauseHeight = pauseWidth;
		
		for(int x = 0;x < pause.length ;x++)
		{
			for(int y = 0;y< pause [x].length;y++)
			{
				switch(pause[x][y])
				{
				case 'L':
				{
					g2d.setColor(Color.WHITE);
					break;
				}
				case 'O':
				{
					g2d.setColor(Color.BLACK);
					break;
				}
			}
			g2d.fillRect(pauseWidth*y,pauseWidth*x, pauseWidth, pauseWidth);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(pauseWidth*y,pauseWidth*x, pauseWidth, pauseWidth);
		}
	}
}
}