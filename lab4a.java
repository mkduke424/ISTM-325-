package Linked_List;



import java.awt.List;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

public class lab4a {
	
	static classList list = new classList();
	static classList unsorted;
	static classList sorted;
	
	
	public static void main(String args[]) {
		
	

		Scanner input = new Scanner(System.in);
		unsorted = new classList();
		System.out.println("I am now reading the file you asked me to," );
		readIn();
		
		
		int choice = 0;
		while (choice != 7) {
			printmenu();
			choice = getChoice(input,1,7);
	//	System.out.println("you entered " + choice);
			switch(choice) {
			case -1: System.out.println("This is not a valid choice, enter again");
				break;
			case 1: System.out.println(unsorted);
				break;
			case 2: bubblesort();
				break;
			case 3: insertionsort();
				break;
			case 4: pigeonholesort();
				break;
			case 5: System.out.println(sorted);
				break;
			case 6: savefile();
				break;
			}
		}
		
	}
	
	public static void printmenu() {
		System.out.println();System.out.println();
		System.out.println("1. Print the Unsorted list to the screen");
		System.out.println("2. Sort the list using bubble sort ");
		System.out.println("3. Sort the list using insertion sort");
		System.out.println("4. Change the list using pigeonhole sort");
		System.out.println("5. Print the sorted list to the screen");
		System.out.println("6. Save the sorted list to file");
		System.out.println("7. Exit");
	}
// Utility method to get input from the console. min = min valid choice,
// While max = max valid choice 
// any invalid choice will return -1
	static int getChoice(Scanner input, int min, int max) {
		String yike = input.nextLine();
		try {
			int typed = Integer.parseInt(yike);
			if(typed < min || typed > max)
				return -1;
			return typed;
		}
		catch (Exception e) {
			return -1;
		}
		}
	
	static void readIn() {
try {
			
			System.out.println("what is cvfile");
				Scanner consolein = new Scanner(System.in);
				String filenamee = consolein.nextLine();


				
				Scanner fileIn = new Scanner(new File(filenamee));
		

				while(fileIn.hasNextLine()) {
				

					String currentLine = fileIn.nextLine();
					String [] linevalue = currentLine.split(",");
					
					if(linevalue[2].equals("UIN"))
						System.out.println("Header");
					else {
						
					studentNode node = new studentNode(linevalue[1],linevalue[0],Integer.parseInt(linevalue[2]));
					unsorted.insert(node);
					
					}
				
				}
				}
				catch(Exception e){
					System.out.println("This aint it chief");
				}
	}
	
	static void bubblesort() {
		
		classList toSort = unsorted.clone();
		long startTime = System.currentTimeMillis();
		toSort.bubble();
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("The bubble sort took"+ elapsedTime + "ms");
		sorted = toSort;
	
	}
	static void insertionsort() {
		classList toSort = unsorted.clone();
		long startTime = System.currentTimeMillis();
		sorted = toSort.insertionsort();
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("The insertion sort took" + elapsedTime + "ms");
		
		
	}
	static void pigeonholesort() {
		classList toSort = unsorted.clone();
		long startTime = System.currentTimeMillis();
	
		sorted = toSort.pigeonholesorT();
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		System.out.println("The pigeonhole sort took"+ elapsedTime + "ms");
		
	}
	static void savefile() {
		try {
			PrintWriter output = new PrintWriter("output.csv");
			output.println(sorted);
			output.flush();
			output.close();
		}
		catch(Exception e) {
			System.out.println("There was a problem"+ e);
		}
		
	}
	
	static void addStudent() {
		Scanner scan = new Scanner(System.in);
	
	
		System.out.println("What is the Student's first name");
		
		String firstname = scan.nextLine();
		
		System.out.println("What is the Student's last name");
		
		String lastname = scan.nextLine();
		
		System.out.println("What is the student's UIN");
		
		int UIN = scan.nextInt();
		
		studentNode node = new studentNode(firstname, lastname, UIN);
		if(!list.isDuplicate(node, UIN))
			list.insert(node);	
	}
	
	static void insertStudent() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is the student firstname");
		
		String firstname = scan.nextLine();
		
		System.out.println("What is the student's lastname");
		
		String lastname = scan.nextLine();
		
		System.out.println("What is the student's uin");
		
		int UIN = scan.nextInt();
		
		studentNode node = new studentNode(firstname,lastname,UIN);
		if(!list.isDuplicate(node,UIN))
			list.insertend(node);
	}
	static void removeStudent() {
	
		
		Scanner scan = new Scanner(System.in);
		System.out.println("What UIN would you like to remove");
	  int uin = scan.nextInt();
	  list.toDelete(uin);
	  
	   
	  }
	  

	
	static void addlist() {
		try {
			
			System.out.println("what is cvfile");
				Scanner consolein = new Scanner(System.in);
				String filename = consolein.nextLine();


				
				Scanner fileIn = new Scanner(new File(filename));
		

				while(fileIn.hasNextLine()) {
				

					String currentLine = fileIn.nextLine();
					String [] linevalue = currentLine.split(",");
					
					if(linevalue[2].equals("UIN"))
						System.out.println("Header");
					else {
						
					studentNode node = new studentNode(linevalue[1],linevalue[0],Integer.parseInt(linevalue[2]));
					list.insert(node);
					}
				
				}
				}
				catch(Exception e){
					System.out.println("This aint it chief");
				}
		System.out.println(list);
	}
	
	
	static void changeStudent(){
		System.out.println("What student would you like to change? (Enter a uin)");
		Scanner scan = new Scanner(System.in);
		int newuin = scan.nextInt();
		
		if(list.isCopy(newuin)) {
		list.changeStudent(newuin);
		}
		else
			System.out.println("Invalid output; Student not listed");
	}
	
	static void printStudent() {
		System.out.println(list);
	}
	}

class classList {
	private studentNode head;
	
	public  classList() {
		head = null;
	}
	public void addStudentFront(studentNode input) {
		String firstname = input.getFname();
		String lastname = input.getLname();
		int uin = input.getUIN();
		
		studentNode toAdd = new studentNode(firstname,lastname,uin);
		addSorted(toAdd);
		
	}
	
	public void addSorted(studentNode input) {
		
		input.setNext(head);
		head = input;
		
		
		
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
	
	public void insertend(studentNode toinsert) {
		if (head == null) {
			head = toinsert;
			return;
		}
		toinsert.setNext(head);
		head = toinsert;
		}
	public void changeStudent(int uin) {
		

		studentNode curr = head;
		
		for(curr = head; curr != null;) {
			
			
		if(curr.getUIN() == uin) {
			Scanner scan = new Scanner(System.in);
			
			System.out.println("The current Student info is: ");
			System.out.println(curr);
			
			
			System.out.println("What is the new uin, or press ENTER to keep it the same uin. ");
			String strchangeUIN = scan.nextLine();
			if(strchangeUIN.equals("")) {
				curr.getFname();	
			}
			else if(isDuplicate(curr,Integer.parseInt(strchangeUIN))) {
				System.exit(0);
			}
			else {
				curr.setUin(Integer.parseInt(strchangeUIN));
			}
			
		
			
			System.out.println("What is the new first name, or press ENTER to keep the same first name. ");
			String firstname = scan.nextLine();
			if(firstname.equals("")){
				curr.getFname();
			}
			else {
				curr.setFname(firstname);
			}
			
			
			System.out.println("What is the new last name, or press ENTER to keep the same last name. ");
			String lastname = scan.nextLine();
			if(lastname.equals("")) {
				curr.getFname();
			}
			else {
			curr.setLname(lastname);
			}
			
		}
		curr = curr.getNext();
		}
		
		}
		
		
	public void addNumberSorted(studentNode input) {
		// handle empty list
		if(head==null) {
			head = input;
			return;
		}
		if(head.getNext()==null)
		{
			if(input.getUIN()< head.getUIN())
			{
				input.setNext(head);
				head = input;
				return;
			}
			else
			{
				head.setNext(input);
				return;
			}
		}
		studentNode current = head;
		while(current != null)
		{
			if(current == head && (input.getUIN()< current.getUIN()))
			{
				input.setNext(head);
				head = input;
			}
			else if ((input.getUIN()>current.getUIN()&&(current.getNext()==null)))
			{
				current.setNext(input);
				
			}
			else if ((input.getUIN() > current.getUIN())&& (current.getNext().getUIN()>input.getUIN()))
			{
				input.setNext(current.getNext());
				current.setNext(input);
				
			}
			current = current.getNext();
		}
		
//		//handle adding to front of list
//		if
//		if(head.getUIN() > input.getUIN()) {
//			input.setNext(head);
//			
//		}
//		// otherwise, find where we go
//		studentNode temp = head;
//		while(temp.getNext() != null) {
//			if((temp.getUIN()< input.getUIN() && (temp.getNext().getUIN() > input.getUIN()))) {//goes between temp and next
//				input.setNext(temp.getNext());
//				temp.setNext(input);
//				return;
//				
//			}
//			temp = temp.getNext();
//		}
//		// we have to add to the end of the list if we get here
//		temp.setNext(input);
		
	}
	
//	public boolean isUIN(studentNode node1, int UIN) {
//		
//		if(head == null)
//			return false;
//		
//		for(studentNode node = head; node != null;) {
//			
//			if(node.getUIN() == UIN) {
//				return true;
//			}
//			node = node.getNext();
//		}
//		return false;
//		
//	}
	public void toDelete(int UIN) {
		
		studentNode previous = null;
		studentNode curr = head;
		
		if(curr.getUIN() == UIN) {
		 head = curr.getNext();
		}
		else {
			
			for(curr = head; curr != null;) {
				if(curr.getUIN() == UIN) {
					System.out.println(curr);
					previous.setNext(curr.getNext());
				}
			previous = curr;
			curr = curr.getNext();
			
			}
		}
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
	public boolean isDuplicate(studentNode node1, int UIN) {
		
		if(head == null)
			return false;
		 
		for(studentNode node = head; node != null;) {
			
			if(node.getUIN() == UIN) {
				System.out.println("Invalid option select another uin!!");
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	
	public boolean isCopy(int uin) {
		studentNode curr = head;
		for(curr = head; curr != null;) {
			if(curr.getUIN() == uin)
				return true;
			curr = curr.getNext();
		}
		return false;
		
	}
	
	public void bubble() {
		if(head == null || head.getNext() == null)
			return;
		studentNode previous = null;
		studentNode current = head;
		studentNode next = head.getNext();
		
		boolean swapped;
		do {
			swapped = false;
			previous = null;
			current = head;
			next = head.getNext();
			
			while(next != null) {
				if(current.getUIN() > next.getUIN()) {
					swapped = true;
					if(previous == null) {//front of the list
						current.setNext(next.getNext());
						next.setNext(current);
						head = next;
					}
					else {//we are in the middle somewhere
						current.setNext(next.getNext());
						next.setNext(current);
						previous.setNext(next);
						
					}
				}
				previous = current;
				current = next;
				next = next.getNext();
			
				
			}
			
		}while(swapped);
		
	}
	public classList insertionsort() {
		classList toReturn = new classList();
		studentNode temp = head;
		while(temp != null) {
			toReturn.addNumberSorted(temp.clone());
			temp = temp.getNext();
			
		}
		return toReturn;
	}
	
	public classList pigeonholesorT() {
		classList toReturn = new classList();
		studentNode pigeon[] = new studentNode[100000];
		studentNode temp = head;
		
		while(temp != null) {//walk through the list
			pigeon[temp.getUIN()] = temp.clone(); //set pointer to temp for each element in the list
			temp = temp.getNext();
		}
		
		for(int i = 99999; i >= 0; i--) {//Walk through our array of pointers, skipping null elements
			if(pigeon[i] != null) {
				toReturn.addStudentFront(pigeon[i]);
			}
		}
		
		
		return toReturn;
	}
	

	
	
	public classList clone() {
		classList toReturn = new classList();
		studentNode temp = head;
		while(temp != null) {
			toReturn.insert(temp.clone());
			temp = temp.getNext();
		}
		return toReturn;
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

			
	
	


