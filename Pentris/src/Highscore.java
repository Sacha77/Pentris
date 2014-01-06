import java.util.Scanner;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintWriter;

public class Highscore {
	private String score;
	private String highscore;
	private ArrayList<String> top10=new ArrayList<String>();
	
/**
Constructs highscore.
@param score the result from last game
*/		

public Highscore()throws FileNotFoundException{
	readFromFile();
}

public void setName(){
	//TODO
}

/**
Reads top10 results from file and saves them in arrayList.
*/
public void readFromFile() throws FileNotFoundException {
	File inputFile = new File("top10.txt");
	Scanner in = new Scanner(inputFile);
	while(in.hasNextLine()){
		this.top10.add(in.nextLine());
	}
	in.close();
}


/**
Writes updated sorted(decreasing) top10 list to file (including result from last game).
*/
public void writeToFile() throws FileNotFoundException {
	
	Collections.sort(top10);
	Collections.reverse(top10);
	
	PrintWriter out = new PrintWriter("top10.txt");
	for(int i=0; i<10; i++){
		out.println(top10.get(i));
	}
	out.close();
}


/**
Gets the current highscore.
@return score if the score from last game is higher than the best result from top10
@return highscore if the score from last game is not higher than the best result from top10
 */

public String getHighscore(){
	int cntr=0;
	boolean end=true;
	char digit=' ';
	
	
	if(this.score.compareTo(this.highscore)>0){
		for(int i=0; i< 9 && end; i++){
			digit=this.top10.get(0).charAt(i);
			
			if(digit=='0'){
				cntr++;
			}
			
			if(digit!='0'){
				end=false;
			}
		}
		return (this.score).substring(cntr);
	}
	else{
		for(int i=0; i< 9 && end; i++){
			digit=this.top10.get(0).charAt(i);
			
			if(digit=='0'){
				cntr++;
			}
			
			if(digit!='0'){
				end=false;
			}
		}
		return (this.highscore).substring(cntr);
	}
	
}


/**
Gets top10 list.
@return top10 results
 */
public String[][] getTop10(){
	int cntr=0;
	boolean end=true;
	char digit=' ';
	String[][] top=new String[10][1];

	for(int i=0; i< 10; i++){
		for(int j=0; j<9  && end; j++){
			
			digit=this.top10.get(i).charAt(j);
		
			if(digit=='0'){
				cntr++;
			}
		
			if(digit!='0'){
				end=false;
			}
		}
		top[i][0]=this.top10.get(i).substring(cntr);
		cntr=0;
		end=true;
	}
	return top;
		
}
public String getLastScore(){
	return top10.get(9);
}
public void setTop10(String scoreName){
	top10.remove(9);
	top10.add(scoreName);
	try {
		writeToFile();
	} catch (FileNotFoundException e) {}
}

}

