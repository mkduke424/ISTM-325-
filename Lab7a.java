package lab7;
import java.io.File;

import java.util.Scanner;



import java.util.*;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class Lab7a {
	
	static classList list = new classList();
	static classList flist = new classList();
	static classList nlist = new classList();
	static TreeNode root = null;
	static String toreturn = "";
	
	public static void main(String args[])  {

		Scanner scanny = new Scanner(System.in);
		System.out.println("what is the student list file" );
		String file1 = scanny.nextLine();
		System.out.println("what is the search file" );
		String file2 = scanny.nextLine();
		
		readInN(file1);
		compare(file2);
		readInB(file1,file2);
		readinH(file1,file2);
		//export
		// Date date = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat ("yyy--MMdd hh-mm-ss");
		//String strDate = formatter.format(date);
		//strDate +=".txt";
		//exportFile(strDate,results);
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			String strDate = formatter.format(date);
			strDate += ".txt";
			PrintStream myconsole = new PrintStream(strDate);
			System.setOut(myconsole);
			myconsole.print(flist);
			myconsole.print(nlist);
			myconsole.print(toreturn);
			//myconsole.print(b);

		}
		catch(FileNotFoundException fx){
			System.out.println(fx);
			
		}
		
}
	static void compare(String filenamee) {
		
try {
			
//			System.out.println("what is cvfile");
//				Scanner consolein = new Scanner(System.in);
//				String filenamee = consolein.nextLine();


				long startTime = System.currentTimeMillis();
				Scanner fileIn = new Scanner(new File(filenamee));
		

				while(fileIn.hasNextLine()) {
				

					String currentLine = fileIn.nextLine();
					
					
					if(currentLine.equals("UIN"))
						System.out.println("--Naive Search--");
					else {
						studentNode node = list.head;
						int flag = 0;
						
						for(node = list.head; node != null;) {
							if(node.getUIN() == Integer.parseInt(currentLine)) {
								 flag = 1;
								studentNode link = new studentNode(node.getFname(),node.getLname(),node.getUIN());
								flist.insert(link);
							}
							
							node = node.getNext();
					
						}
						if(flag == 0) {
							studentNode bink = new studentNode(null,null,Integer.parseInt(currentLine));
							nlist.insert(bink);
						}	
					}	
				}
				System.out.println("Found list\n" +flist);
				
				System.out.println("Not Found list\n" + nlist);
				long endTime = System.currentTimeMillis();
				long elapsed = (endTime - startTime);
				
				System.out.println("The Naive Search took "+ elapsed+ "ms");
				}
				catch(Exception e){
					System.out.println("This aint it chief");
				}
	}
	static void readInN(String filenamee) {
		try {
					
//					System.out.println("what is cvfile");
//						Scanner consolein = new Scanner(System.in);
//						String filenamee = consolein.nextLine();


						
						Scanner fileIn = new Scanner(new File(filenamee));
				

						while(fileIn.hasNextLine()) {
						

							String currentLine = fileIn.nextLine();
							String [] linevalue = currentLine.split(",");
							
							if(linevalue[2].equals("UIN"))
								System.out.println();
							else {
								
							studentNode node = new studentNode(linevalue[1],linevalue[0],Integer.parseInt(linevalue[2]));
							list.insert(node);
							
							}
						}
						//System.out.println(list);
						}
						catch(Exception e){
							System.out.println("This aint it chief");
						}
			}
	static String readInB(String filename,String filenamee) {
		//System.out.println("What is the name of the csv file you wish to read in");
		//String toreturn = "";
		try {
//			Scanner scan = new Scanner(System.in);
//			String filename = scan.nextLine();
			
			Scanner filein = new Scanner(new File(filename));
			
			while(filein.hasNextLine()) {
				String currentline = filein.nextLine();
				String [] linevalue = currentline.split(",");
				
				if(linevalue[2].equals("UIN")) {
					System.out.println("--Binary Search Tree--");
				}
				else {
					TreeNode node = new TreeNode(linevalue[0],linevalue[1],Integer.parseInt(linevalue[2]));
					if(root == null) {
						root = node;
					}
					else
					{
						//node.insert(node);
						root.insert(node);
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("you don goofed cuz");
		}
		long startTime = System.currentTimeMillis();
		try {
			
//			System.out.println("what is cvfile");
//				Scanner consolein = new Scanner(System.in);
//				String filenamee = consolein.nextLine();


				
				Scanner fileIn = new Scanner(new File(filenamee));
		

				while(fileIn.hasNextLine()) {
				

					String currentLine = fileIn.nextLine();
					
					
					if(currentLine.equals("UIN")) {
						System.out.println();
					
					}
					
					else if(root.search(Integer.parseInt(currentLine))) {
	
						//System.out.println(root.getlname() +"," + root.getfname()+ ","+ root.getuin() + " was found");
						//toreturn += ("\n" + root.getlname() +"," + root.getfname()+ ","+ root.getuin() + " was found");
					}
					else {
						System.out.println(currentLine +" was not found");
						toreturn +=("\n"+ currentLine +" was not found");
					}
				
				}
				long endTime = System.currentTimeMillis();
				long elapsed = (endTime - startTime);
				System.out.println("Binary Search took "+ elapsed +"ms");
				
				
				}
				catch(Exception e){
					System.out.println("This aint it chief");
				}
		return toreturn;
	}
	static void readinH(String filenamee,String filename) {
		try {
//			System.out.println("what is cvfile");
//				Scanner consolein = new Scanner(System.in);
//				String filenamee = consolein.nextLine();


				Scanner fileIn = new Scanner(new File(filenamee));
				studentNode[] sturay = new studentNode[1000000];
				
				

				while(fileIn.hasNextLine()){
				

					String currentLine = fileIn.nextLine();
					String [] linevalue = currentLine.split(",");
					
					
					if(linevalue[2].equals("UIN"))
						System.out.println("--Hash Search--");
					else {
						
					studentNode node = new studentNode(linevalue[1],linevalue[0],Integer.parseInt(linevalue[2]));
					sturay[node.getUIN()] = node;
					
					}
				}
				
//				System.out.println("what is the csv file search");
//				Scanner consol = new Scanner(System.in);
//				String filename = consol.nextLine();

				long startTime = System.currentTimeMillis();
				Scanner fileIn1 = new Scanner(new File(filename));
				while(fileIn1.hasNextLine()) {
					
					String curr = fileIn1.nextLine();
					
					if(curr.equals("UIN")) {
						System.out.println();
					}
					else {
						lookupuin(sturay,curr);
					}
					
						
					}
				long endTime = System.currentTimeMillis();
				long elapsed = (endTime - startTime);
				System.out.println("Hash Search took "+ elapsed+"ms");
				}
				catch(Exception e){
					System.out.println("This aint it chief");
				}
		
		
	}
	public static void lookupuin(studentNode[] students, String l) {
		int flag = 0;
		for(int i = students.length - 1;i >= 0; i--) {
			if(students[i] != null) {
				if(students[i].getUIN() == Integer.parseInt(l)) {
				// This line of code isn't working like it should :(
					flag = 1;
					System.out.println(students[i]);
					toreturn += students[i];
				}
									

			}
			
		}
		 
		if (flag == 0) {
			System.out.println(l + "wasn not found");
			toreturn += l + "Was not found";
		}
		

			
	}

}

class classList {
	 studentNode head;
	
	public  classList() {
		head = null;
	}
	
	public void insert(studentNode toinsert) {

		// if the head is null, this is the front of the list.
		
		if(head == null) {
			head = toinsert;
			return;
		}
		// find the end of the list
		studentNode possibleTail = head;
		while(possibleTail.getNext() != null) { // this is not the end
			possibleTail = possibleTail.getNext();
		}
		// change next pointer of last node to be toinsert
		possibleTail.setNext(toinsert);
		}
	
	public String toString() {
		studentNode step = head;
		
		String answer = "Students: " + System.lineSeparator();
		
		while(step != null) {
			answer += step.toString() + System.lineSeparator();
			step = step.getNext();
		}
		return answer + "End of List";
	}
}

class studentNode{
	
	public String myFname;
	public String myLname;
	public int myuin;
	public studentNode next;
	
	
	public studentNode() {
		next = null;
	}
	public studentNode(String fname, String lname, int uin) {
		myFname = fname; myLname = lname; myuin = uin; next = null;
		}

	public studentNode getNext() {
		return next;
	}
	
	public void setNext(studentNode ralph) {
		next = ralph;
	}
	
	public String toString() {
		
	return myFname + ", " + myLname + "," + "(" + myuin + ")";
	}
	
	public int getUIN() {
		return myuin;
		
	}
	public String getFname() {
		return myFname;
	}
	public void setFname(String fname) {
		
		myFname = fname;
	}
	public String getLname() {
		return myLname;
	}
	public void setLname(String lname) {
		myLname = lname;
	}
	
	public void setUin(int uin) {
		myuin = uin;
	}
	
	public studentNode clone() {
		
		return new studentNode(myLname, myFname, myuin);
	}

}

class TreeNode{
	
	private String fname;
	private String lname;
	private int uin;
	
//	private TreeNode root;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode() {
		//root = null; 
		left = null; right = null;
		
	}
	public TreeNode(String fname, String lname,int uin) {
		this.fname = fname; this.lname = lname; this.uin = uin; left = null; right = null;
		//root = null;
	}
	public String getfname() {
		return fname;
	}
	public void setfname(String fname) {
		this.fname = fname;
	}
	public String getlname() {
		return lname;
	}
	public void setlname(String lname) {
		this.lname = lname;	
	}
	public int getuin() {
		return uin;
	}
	public void setuin(int uin) {
		this.uin = uin;
	}
	
	
	public TreeNode getright() {
		return right;
	}
	public void setright(TreeNode right) {
		this.right = right;
	}
	public TreeNode getleft() {
		return left;
	}
	public void setleft(TreeNode left) {

		this.left = left;
	}
	public void insert(TreeNode a) {
		if(a.getuin() == uin) {
			System.out.println(a + "has a duplicate UIN with"+ toString() + "cannot insert");
			return;
		}
		else if(a.getuin() <= uin) {
			if(left == null) {
				left = a;
			}
			else {
				left.insert(a);
			}
		}
		else if(a.getuin() >= uin){
			if(right == null) {
				right = a;
			}
			else {
				right.insert(a);
			}
		}
	}
	public boolean search(int a) {
		if(a == uin) {
			//found
			 System.out.println(fname +","+ lname+ "," + uin + "was found");
			 return true;
		}
		else if (a < uin){
			if(left == null)
				return false;
			else
				return left.search(a);
		}
			else if(a>uin) {
				if(right == null)
					return false;
				else
					return right.search(a);
		}
		return false;
		
	}
	public String toString() {
		return(fname + "," + lname + "," + ")" +uin +")");
	}
	public void exportList(PrintWriter file) {
		try {
			if(left != null)
			{
				left.exportList(file);
			}
			file.println(lname + ", " + fname + ", "+ uin);
			if(right != null) {
				right.exportList(file);
			}
		}
		catch(Exception e) {
			System.out.println();
		}
	}
 }


	

