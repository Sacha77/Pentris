import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Menu extends JFrame{
	double screenMultiplier = 1;
	public Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
	private static Board board;
	public Menu(){
		
		final JFrame frame = new JFrame("Pentris");
		frame.setTitle("Pentris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.height *= screenMultiplier;
		a.width *= screenMultiplier;
		frame.setSize(a.height, a.width);
		frame.setPreferredSize(new Dimension(a.width, a.height-40)); 
		JPanel thePanel=new JPanel();
		thePanel.setSize(a.height,a.width);
		
		JButton play= new JButton("New game");
		play.setPreferredSize(new Dimension(200,60));
		JLabel background=new JLabel(new ImageIcon("C:\\Users\\Kuba\\workspace\\Pentris\\src\\backgroundMenu.jpg"));
		add(background);
		background.setLayout(new FlowLayout());
		
	      class PlayGameListener implements ActionListener{
	    	  public void actionPerformed(ActionEvent event){	
	    		  
	    		  frame.dispose();
	    		  board =new Board();
	    		  try {
					new PentominoFrame();
	    		  } catch (FileNotFoundException e) {} 
	    	  }	    	 
	      }
	    	 
		play.addActionListener(new PlayGameListener());
		
		
		JButton top= new JButton("Top10");
		

	      class Top10Listener implements ActionListener{
	    	  public void actionPerformed(ActionEvent event){
	    		  	Highscore top10;
	    		  	frame.dispose();
					try {
						top10 = new Highscore();
						String[][] top = top10.getTop10();
						
		    			JLabel t = new JLabel(
						"<html><pre>        Top10 results:" + 
		    			"<br>        1: "+ top[0][0] + "\n"+
		    			"<br>        2: "+ top[1][0] + "\n"+
		    			"<br>        3: "+ top[2][0] + "\n"+
		    			"<br>        4: "+ top[3][0] + "\n"+
		    			"<br>        5: "+ top[4][0] + "\n"+
		    			"<br>        6: "+ top[5][0] + "\n"+
		    			"<br>        7: "+ top[6][0] + "\n"+
		    			"<br>        8: "+ top[7][0] + "\n"+
		    			"<br>        9: "+ top[8][0] + "\n"+
		    			"<br>        10:"+ top[9][0] + "\n </pre><html>");
		    			
		    			Font f = new Font("Dialog", Font.BOLD, 24);
		    			t.setFont(f);
		    			t.setForeground(Color.WHITE);	
		    			final JFrame frame2 = new JFrame("Top 10");
		    			
		    			JButton back = new JButton("Back");
		    			
		    			class BackListener implements ActionListener{
							public void actionPerformed(ActionEvent arg0) {
								new Menu();
								frame2.dispose();
								
							}
		    			}

		    			back.addActionListener(new BackListener());
		    			
		    			JLabel background=new JLabel(new ImageIcon("C:\\Users\\Kuba\\workspace\\Pentris\\src\\background.jpg"));
		    			add(background);
		    			background.setLayout(new FlowLayout());
		    			
		    	        GridBagConstraints c = new GridBagConstraints();
		    	        c.anchor = GridBagConstraints.LAST_LINE_END;
	    	    		    	        
		    			JPanel panel1 = new JPanel();
		    			panel1.setLayout(new GridBagLayout());
		    			panel1.setOpaque(false);
		    			c.insets = new Insets(0,275,0,0);
		    			panel1.add(t, c);
		    			c.insets = new Insets(0,275,40,0);
		    			panel1.add(back, c); 			
		    			background.add(panel1);
		    			
		    			frame2.add(background);
		    			frame2.setTitle("Pentris");
		    			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    			a.height *= screenMultiplier;
		    			a.width *= screenMultiplier;
		    			frame2.setSize(a.height, a.width);
		    			frame2.setSize(new Dimension(a.width, a.height-40));
		    			frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    			frame2.setVisible(true);
		    			
		    			
					} catch (FileNotFoundException e) {System.out.println("ERROR");}
	    	  }
	      }
	    top.addActionListener(new Top10Listener());
		top.setPreferredSize(new Dimension(200,60));
		
		JButton rules= new JButton("Rules");
		class RulesListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				final JFrame frame3 = new JFrame("Rules");
				frame.dispose();
				JLabel label = new JLabel("<html><pre>				 Control instruction:" + 
		    			"<br>			1:	arrow up	- rotation  \n"+
		    			"<br>			2:	arrow down	- move down  \n"+
		    			"<br>			3:	left arrow	- move left \n"+
		    			"<br>			4:	right arrow	- move right  \n"+
		    			"<br>			5:	space		- drop pentomino  \n"+
		    			"<br>			6:	p		- pause  \n"+
		    			"<br>			7:	s		- start after pause  \n"+
		    			"<br>											\n"+
		    			"<br>		After clearing multiple lines at once you get more points!!  \n"+
		    			"\n </pre><html>");
				JPanel panel = new JPanel();
				Font f = new Font("Dialog", Font.BOLD, 24);
    			label.setFont(f);
    			label.setForeground(Color.WHITE);
    			label.setBackground(Color.BLACK);
				panel.setBackground(Color.BLACK);
				
				JButton back = new JButton("Back");
				back.setPreferredSize(new Dimension(200,40));
				
				
				
				
    			class BackListener implements ActionListener{
					public void actionPerformed(ActionEvent arg0) {
						new Menu();
						frame3.dispose();
						
					}
    			}
    			back.addActionListener(new BackListener());
    			panel.setLayout(new BorderLayout());
    			panel.add(label, BorderLayout.PAGE_START);
    			panel.add(back, BorderLayout.PAGE_END);
    			
    			frame3.add(panel);
    			frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			a.height *= screenMultiplier;
    			a.width *= screenMultiplier;
    			frame3.setSize(a.height, a.width);
    			frame3.setSize(new Dimension(a.width, a.height-40));
    			frame3.setExtendedState(JFrame.MAXIMIZED_BOTH);
    			frame3.setVisible(true);
    			
			}	
		}
		rules.addActionListener(new RulesListener());
		rules.setPreferredSize(new Dimension(200,60));
		
		
		JButton solution= new JButton("Optimal solution");
		class ShapesListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				try {
					OptimalSolution optimal = new OptimalSolution();
				} catch (InterruptedException e1) {}
				
			}

		}
		solution.addActionListener(new ShapesListener());
		solution.setPreferredSize(new Dimension(200,60));

		JButton quit = new JButton("Quit");
		quit.setPreferredSize(new Dimension(200,60));
		
		class QuitListener implements ActionListener{
	    	  public void actionPerformed(ActionEvent event){

	    		  System.exit(0);
	    		 
	    	  }
		}
		quit.addActionListener(new QuitListener());
		
		
		thePanel.setLayout(new GridLayout(5,1,20,20));

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		JPanel buttonPane2 = new JPanel(new GridBagLayout());
		JPanel buttonPane3 = new JPanel(new GridBagLayout());
		JPanel buttonPane4= new JPanel(new GridBagLayout());
		JPanel buttonPane5 = new JPanel(new GridBagLayout());
		
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight=20;
        c.gridwidth=200;

		buttonPanel.add(play,c);
		buttonPane2.add(top,c);
		buttonPane3.add(rules,c);
		buttonPane4.add(solution,c);
		buttonPane5.add(quit,c);
		
		thePanel.add(buttonPanel);
		thePanel.add(buttonPane2);
		thePanel.add(buttonPane3);
		thePanel.add(buttonPane4);
		thePanel.add(buttonPane5);
		thePanel.setOpaque(false);

		background.add(thePanel);
		frame.add(background);
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
	}
	public static Board getBoard(){
		return board;
	}
}
