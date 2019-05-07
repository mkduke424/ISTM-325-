package lab8;

import java.io.File;
import java.util.Scanner;

public class lab8a {

	public static void main (String [] args){
		Grade[] feedback = new Grade[10];
		
		
		System.out.println("What is the name of the csv file you wish to read in");
		try {
			Scanner scan = new Scanner(System.in);
			String filename = scan.nextLine();
			
			
			Scanner filein = new Scanner(new File(filename));
			for(int i = 0; i < 10; i++)
			{
			while(filein.hasNextLine()) {
			
				String currentline = filein.nextLine();
				
				
				if(currentline.equals("A")){
					feedback[i] = new AGrade();
					break;
					
				}
				else if(currentline.equals("B")) {
					feedback[i] = new BGrade();
					break;
					
				}
				else if(currentline.equals("C")) {
					feedback[i] = new CGrade();
					break;
				}
				else if(currentline.equals("D")) {
					feedback[i] = new DGrade();
					break;
				}
				else if(currentline.equals("F")) {
					feedback[i] = new FGrade();
					break;
				}
					
			}
			
		}
			for(Grade element: feedback) {
				System.out.println(element);
			}
		}
		catch(Exception e) {
			System.out.println("you don goofed cuz");
		}
		
	}
	
}
	
class Grade{
	String toreturn = ("You shouldn't be here");
	public String toString() {
		return toreturn;
	}
		
	}
class AGrade extends Grade{
	String toreturn = ("Great job A student!");
	public String toString() {
		return toreturn;
	}
}
class BGrade extends Grade{
	String toreturn = ("OK job B student!");
	public String toString() {
		return toreturn;
	}
}
class CGrade extends Grade{
	String toreturn = ("ehhh you coulda done better c student!");
	public String toString() {
		return toreturn;
	}
}
class DGrade extends Grade{
	String toreturn = ("This is no bueno D person");
	public String toString() {
		return toreturn;
	}
}
class FGrade extends Grade{
	String toreturn = ("You did terrible go die!");
	public String toString() {
		return toreturn;
	}
}



