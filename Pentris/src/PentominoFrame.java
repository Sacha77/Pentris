 import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.FlowLayout; 
import java.awt.Font; 
import java.awt.Toolkit; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException; 
  

import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.Timer;
  
public class PentominoFrame extends JFrame implements KeyListener{ 
      
    double screenMultiplier = 1; 
    public static Dimension a = Toolkit.getDefaultToolkit().getScreenSize(); 
    public static JPanel thePanel;
    public static JPanel scorePanel;
    public static JPanel main;
    public static JFrame frame;
    private static Board d;
    private final int TIME=200;
    public Timer refresh;
    public static JPanel score; 
    public static JPanel nextPent;
    public static JPanel level;
    public static JPanel c;
    public static JPanel t;
      
    public PentominoFrame() throws FileNotFoundException 
    { 
    	thePanel = new PaintObject(a.width, a.height-100);  
    	scorePanel = new JPanel(); 
        main = new JPanel(); 
        frame = new JFrame(); 
        d = Menu.getBoard();
        score = new JPanel(); 
        nextPent = new JPanel(); 
        level = new JPanel();
        c= new JPanel();
        t= new JPanel();
    	
    	
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\Kuba\\workspace\\Pentris\\src\\backgroundGame.jpg")); 
        background.setLayout(new FlowLayout()); 
        final JButton end = new JButton("End game"); 
          
        class EndListener implements ActionListener{ 
             public void actionPerformed(ActionEvent event){ 
            		 d.gameOver();  
            		 end.setEnabled(false);
            } 
              
        } 
        end.addActionListener(new EndListener()); 
        end.setPreferredSize(new Dimension(50,30)); 
          
        frame.setPreferredSize(new Dimension(a.width, a.height-40)); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JLabel score1 = new JLabel("Score: " + d.getScore()); 
        score.add(score1); 
        Font f = new Font("Dialog", Font.BOLD, 20); 
        score1.setFont(f);      
        BorderLayout border2 = new BorderLayout(); 
        border2.setVgap(200); 
        scorePanel.setLayout(border2); 
        scorePanel.add(score, BorderLayout.PAGE_START); 
        scorePanel.add(end, BorderLayout.PAGE_END); 
          
          
        a.height *= screenMultiplier; 
        a.width *= screenMultiplier; 
          
        JLabel nextPentomino1 = new JLabel("Next pentomino:"); 
        nextPentomino1.setFont(f); 
        JPanel nextPentomino = new JPanel(); 
        nextPentomino.add(nextPentomino1);
        
        char[][] pent = d.getNextPentomino(); 
        JPanel nextShape = new PaintNextPentomino(350 , 400, pent);
        nextShape.setBackground(Color.BLACK);  
        nextPent.setLayout(new BorderLayout()); 
        nextPent.add(nextPentomino, BorderLayout.PAGE_START); 
        nextPent.add(nextShape, BorderLayout.CENTER); 
        scorePanel.add(nextPent,BorderLayout.CENTER); 
        JPanel empty = new JPanel();
        
        String currentLevel = d.getLevel();
        String toNextLevel = d.getToNextLevel();
        JLabel current = new JLabel(currentLevel);
        JPanel c= new JPanel();
        c.add(current);
        JLabel toNext  = new JLabel(toNextLevel);
        JPanel t= new JPanel();
        t.add(toNext);
        current.setFont(f);
        toNext.setFont(f);
        
        BorderLayout border3 = new BorderLayout(); 
        border3.setVgap(10); 
        border3.setHgap(10); 
        
        level.setPreferredSize(new Dimension(250, 150)); 
        level.setLayout(border3);
        level.add(c, BorderLayout.PAGE_START);
        level.add(t, BorderLayout.PAGE_END);
        level.setOpaque(false);
        empty.add(level, BorderLayout.PAGE_START);
        current.setBackground(Color.WHITE);
        toNext.setBackground(Color.WHITE);
        empty.setOpaque(false);
        
        refresh =new Timer(TIME, (new ActionListener(){       
        		public void actionPerformed(ActionEvent evt){
        			boolean gameOver = d.gameOverBoolean;
        			if(gameOver){
        				refresh.stop();
        			}
        			if(!gameOver)
        				refreshNextPentomino();
        			refreshScore(); 
        			refreshLevel();
        		}
        }));
        refresh.start();

        empty.setOpaque(false); 
        empty.setPreferredSize(new Dimension(250, 700)); 
        
        
        
        thePanel.setPreferredSize(new Dimension(350, 700));
        thePanel.addKeyListener(this);
        scorePanel.setPreferredSize(new Dimension(200, 700)); 
          
        BorderLayout border = new BorderLayout(); 
        border.setHgap(100); 
  
          
        main.setLayout(border); 
        main.add(empty, BorderLayout.LINE_START); 
        main.add(thePanel); 
        main.add(scorePanel, BorderLayout.LINE_END); 
  
        main.setOpaque(false); 
        scorePanel.setOpaque(false); 
  
        background.add(main); 
        frame.add(background); 
        frame.pack(); 
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  
        frame.setVisible(true); 
        thePanel.requestFocus();
          
    } 
      
    public static void refreshScore(){ 
        Font f = new Font("Dialog", Font.BOLD, 20); 
        JLabel score1 = new JLabel("Score: " + d.getScore()); 
        score1.setFont(f); 
        score.remove(0); 
        score.add(score1); 
        frame.revalidate(); 
    }
    public static void refreshNextPentomino(){
    	char[][] pent = d.getNextPentomino();			
		JPanel nextShape = new PaintNextPentomino(350 , 400, pent);
		nextShape.setBackground(Color.BLACK);  
		nextPent.remove(1);
		nextPent.add(nextShape, BorderLayout.CENTER); 
		frame.revalidate();
    }
    public static void refreshLevel(){
    	 Font f = new Font("Dialog", Font.BOLD, 20); 
    	 String currentLevel = d.getLevel();
         String toNextLevel = d.getToNextLevel();
         JLabel current = new JLabel(currentLevel);
         JLabel toNext  = new JLabel(toNextLevel);
         current.setFont(f);
         toNext.setFont(f);
         current.setBackground(Color.WHITE);
         toNext.setBackground(Color.WHITE);
         t.removeAll();
         c.removeAll();
         c.add(current);
         t.add(toNext);
         level.removeAll();
         level.add(c, BorderLayout.PAGE_START);
         level.add(t, BorderLayout.PAGE_END);
         frame.revalidate();
         
    }

    public void keyPressed(KeyEvent e) 
    { 
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    	  d.moveRight();
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    	  d.moveLeft();
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    	  d.moveOneSquare();;
      }
      if (e.getKeyCode() == KeyEvent.VK_UP) {
    	  d.rotatePentominoRight();
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    	  d.moveFullDown();
      }
	  if (e.getKeyCode() == KeyEvent.VK_P){
	   	  d.pause();
	  }
	  if (e.getKeyCode() == KeyEvent.VK_S){
    	  d.start();
      }
    } 
    public void keyReleased(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}

} 
