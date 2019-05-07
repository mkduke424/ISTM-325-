package Trees;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;


public class lab6 {
	
	static TreeNode root = null;
	
	public static void main(String args[]) {
		menu();	
	}
	public static void menu() {
		System.out.println("Which option would you like to choose");
		System.out.println("Option 1: Read in from a file and insert into tree");
		System.out.println("Option 2: Print the list from the tree structure, sorted");
		System.out.println("Option 3: Write the sorted list to a file ");
		System.out.println("Or press 4 to Exit");
		
		try {
		Scanner input = new Scanner(System.in);
		int response = input.nextInt();
		
		boolean done = false;
		
		while(!done) {
			if(response == 1) {
				readIn();
				menu();
			}
			else if(response == 2) {
				println(root);
				menu();
			}
			
			else if(response == 3) {
				exportFile();
				menu();
			}
			else if(response == 4) {
				System.out.println("Goodbye");
				done = true;
				System.exit(0);
			}
		}
		}
		catch(Exception e) {
			System.out.println("nawlll chief integers only");
		}
	}

	
	public static void readIn() {
		System.out.println("What is the name of the csv file you wish to read in");
		try {
			Scanner scan = new Scanner(System.in);
			String filename = scan.nextLine();
			
			Scanner filein = new Scanner(new File(filename));
			
			while(filein.hasNextLine()) {
				String currentline = filein.nextLine();
				String [] linevalue = currentline.split(",");
				
				if(linevalue[2].equals("UIN")) {
					System.out.println("Begining of classlist");
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
		
	}
	public static void println (TreeNode a) {
		if(a != null)
		{
			println(a.getleft());
			System.out.println(a.toString());
			println(a.getright());
			
		}
	}
	public static void exportFile()
	{
		try {
			//Scanner scan = new Scanner(System.in);
//			System.out.println("What would you like to name your file");
			//String file = scan.nextLine();
			PrintWriter newFile = new PrintWriter("BST.csv");
			newFile.println("Last Name, First Name, UIN ");
			root.exportList(newFile);
			newFile.flush();
			newFile.close();
			
		}
		catch(Exception e) {
			
		}
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
		
//		public TreeNode getroot() {
//			return root;
//		}
//		public void setroot(TreeNode root) {
//			this.root = root;
//		}
		
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
//		public void print(TreeNode node) {
//			if(left != null)
//			{
//				left.print(node);
//			}
//			System.out.println(node.toString());
//			if(right != null) {
//				right.print(node);
//			}
//			
//		}
		
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
		
			
//		public void insert(TreeNode a) {
//			if(a.getuin() <= uin) {
//				if(left == null) {
//					left = new TreeNode(a);
//				}
//				else {
//					left.insert(a);
//				}	
//				}
//			else(a.getuin() >= uin){
//				if(right == null) {
//					right = new TreeNode(a);
//				}
//				else {
//					right.insert(a);
//				}
//			}	
//			}
	 }

	
	
