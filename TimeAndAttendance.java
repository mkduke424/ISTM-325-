package FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import java.io.PrintWriter;

 class TimeAndAttendance {
	
	// This Creates all of the list needed throughout the duration of the project. 
	// Mix of vecteors and linked list
	
	static STXList slist = new STXList();
	static STXList sorted = new STXList();
	static Vector<String> vecv = new Vector<String>();
	static Vector<String> vece = new Vector<String>();
	static Vector<String> vecp = new Vector<String>();

	//RA1 Reads in files entered
	public static void main (String [] args) {
		
		String filein = args[0];
		
		//String testfilein = "HQlocations.csv";
				
		Scanner scanny = new Scanner(System.in);
		
		System.out.println("-----HQ---");
		
		
		try {
			Scanner firstfilein = new Scanner(new File(filein));
			while(firstfilein.hasNextLine()) {
				String currentLine = firstfilein.nextLine();
				String [] linevalue = currentLine.split(",");
				if(linevalue[0].equals("Locations ")) {
					System.out.println("Beginning of loction list with general managers");
				}
				else {
					System.out.println(linevalue[0] + "," + linevalue[1]);
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Your test file didn't read in");
		}
		
		
		System.out.println("What is the filename of the location you would like to access....(STX,HTX,ATX)");
		
		
		String response = scanny.nextLine();
		if(response.equals("STX_Employees.csv")) {
			
			try {
				String filenamee = "STX_Employees.csv" ;
				
			
				Scanner fileIn = new Scanner(new File(filenamee));
			
				
				while(fileIn.hasNextLine()) {
				

					String currentLine = fileIn.nextLine();
					String [] linevalue = currentLine.split(",");
					
					
					if(linevalue[0].equals("Employee_ID")) {
						System.out.println();
					}
					else {
						employeeNode node = new employeeNode(Integer.parseInt(linevalue[0]), linevalue[1], linevalue[2],linevalue[3],linevalue[4]);
						slist.insert(node);
					}
	
				}
				
				fileIn.close();
				}

				catch(Exception e){
					System.out.println("This aint it chief");
					
				}
			
			System.out.println("------------------------------------------");
			System.out.println("              STX Menu                    ");
			System.out.println("------------------------------------------");
			Scanner input = new Scanner(System.in);
			
			int choice = 0;
			while (choice != 10) {
				printmenu();
				choice = getChoice(input,1,10);
		//	System.out.println("you entered " + choice);
				switch(choice) {
				case -1: System.out.println("This is not a valid choice, enter again");
					break;
				case 1: 
					
					empadd();
					break;
				case 2:  changeemployee();
					break;
				case 3:  empdelete();
					break;
				case 4: clockhours();
					break;
				case 5: clockpto();
					break;
					// make sure GM are the only ones to view employee records 
				case 6: printlist();
					break;
					// make sure GM are the only ones to search employee records 
				case 7: searchemployee();
					break;
					
				case 8:
					// make sure the GM is the only one that can do this 
					bubblesort();
					break;
				case 9:
					export();
					break;
				}
			}
		}
		else {
			System.out.println("This file doesn't exist!");
		}
		
	}
	// This method verifies that the user's position is a General Managers and it shows the unsorted list of employee records
	static void printlist() {
		try {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is your id");
		
		int re = scan.nextInt();
		
		if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
			
			System.out.println(slist);
			
			}
			else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")){
				System.out.println("\n Invalid: You need to be a GM to view employee data \n");
			}
		}
		catch(Exception e){
		
		
			System.out.println("This uin doesn't exisit");
		}
	}
	// RA5 & RA9 Method that uses Polymorphism to export proper files 
	static void export() {
				Scanner yes = new Scanner(System.in);

				 
				 int choice = 0;
					while (choice != 6) {
						System.out.println("Choose the type of employee data you would like to export");
						 
						 System.out.println("Option 1. Export employee records list ");
						 System.out.println("Option 2. Export the sorted employee records list");
						 System.out.println("Option 3. Export the Verified employee clock in list");
						 System.out.println("Option 4. Export the Employee Clock in list");
						 System.out.println("Option 5. Export the employee PTO list");
						 System.out.println("Option 6. Exit to master menu");
						
						 
						
						choice = getChoice(yes,1,6);
				//	System.out.println("you entered " + choice);
						switch(choice) {
						case -1: System.out.println("This is not a valid choice, enter again");
							break;
						case 1: 
							new exportR();
							break;
						case 2:  new exportSP();
							break;
						case 3:  new exportV();
							break;
						case 4: new exportE();
							break;
						case 5: new exportP();
							break;
							
					
						}
					}
	}
	
	static void clockhours() {

		
		int totaltime;
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		
		System.out.println("What is your ID?");
		
		int rid = scan.nextInt();
		 
		System.out.println("What employee would you like to clock hours for");
		
		int sid = scan.nextInt();
		
		if(isvalid(sid,slist.Searchid(sid).getposition(),rid,slist.Searchid(rid).getposition())) {
			
			if(slist.Searchid(rid).getposition().equals("GM")|| slist.Searchid(rid).getposition().equals("AM")||slist.Searchid(rid).getposition().equals("AM")){
			// come back and do a verify 
			int toreturn = 0;
			
			for(int i = 1; i <= 5; i++) {
			
			System.out.println("What is the total clock in hours for day " + i +" Military time pls \n");
			
			System.out.print("Clock in hours:");
		
			int clockin = scan.nextInt();
			
			System.out.println("Clcok out hours:");
			
			int clockout = scan.nextInt();
			
			int totalclock = ((clockout - clockin)/100);
			
			toreturn += totalclock;
			
			}
			
		
			System.out.println("----Manager clock/verify list-----");
			vecv.add(slist.Searchid(sid) +"," + Integer.toString(toreturn) + " hours;"+" (Verified)");
			System.out.println(vecv);
			}
			else if(slist.Searchid(rid).getposition().equals("R")){
					
					int toreturn = 0;
					
					for(int i = 1; i <= 5; i++) {
					
					System.out.println("What is the total clock in hours for day " + i +" Military time pls \n");
					
					System.out.print("Clock in hours:");
				
					int clockin = scan.nextInt();
					
					System.out.println("Clcok out hours:");
					
					int clockout = scan.nextInt();
					
					int totalclock = ((clockout - clockin)/100);
					
					toreturn += totalclock;
					
					}

					System.out.println("----Employee Clockinlist-----");
					vece.add("\n " +slist.Searchid(sid) +"," + Integer.toString(toreturn) + " hours; " + "(Unverified)");
					System.out.println(vece);
			}

		}
		else
			System.out.println("Remember if you are an R level employee you can clock hours for yourself. \n If you are a manager you cannot clock hours for yourself..another manager has to do it for you");
		
		//if the slist.search(id).isman(id,pos) && (slist.searchid(sid).getposition == slist.search(eid).getposit
		// you cant clock in for yourself 
		// else if slist.search(id)
	
	}
	
	
	
	
	
	static void clockpto() {
		
		Scanner scan1 = new Scanner(System.in);
		// What is your id 
		System.out.println("What is your ID?");
		
		int rid = scan1.nextInt();
		// What is the employee id you are looking for 
		System.out.println("What employee would you like to award pto for");
		
		
		int sid = scan1.nextInt();
		
		if(slist.Searchid(rid) != null && slist.Searchid(rid).getposition().equals("GM")) {
		
			System.out.println("Awarding pto for " + slist.Searchid(sid).getFname());

			// sick hours etc
			System.out.println("How many available sick hours does "+ slist.Searchid(sid).getFname() + " " + slist.Searchid(sid).getLname() +" have");
			
			double oldsick = scan1.nextDouble();
			// assuming hours are awarded at the end of each week.
			System.out.println("How many hours did " + slist.Searchid(sid).getFname() + " " +slist.Searchid(sid).getLname() + " make this week");
			
			double totalmade = scan1.nextDouble();
			
			System.out.println("How many sick hours did " + slist.Searchid(sid).getFname() + " "+  slist.Searchid(sid).getLname() + " use this week");
			double usesick = scan1.nextDouble();
			
		
			
			
			// vacation hours etc 
			System.out.println("How many available vacation hours does "+ slist.Searchid(sid).getFname() +" "+ slist.Searchid(sid).getLname() +" have");
			double oldvac = scan1.nextDouble();
			// assuming hours are awarded at the end of each week.
			
			
			System.out.println("How many vacation hours did " + slist.Searchid(sid).getFname() + " "+ slist.Searchid(sid).getLname() + " use this week");
			double usevac = scan1.nextDouble();
			
			
		
		   if(slist.Searchid(sid).getposition().equals("AM")) {
			   
			   if(totalmade > 5) {
					double newsick = (2 + oldsick) - usesick;
					// calculate vacation
					double newvac = (3+ oldvac) - usevac;
					
					vecp.add("Employee id & name: "+ "(" + slist.Searchid(sid).getempid() + ")" + slist.Searchid(sid).getFname() 
							+ " "+ slist.Searchid(sid).getLname() +"; "+  " Available sick hours: " + newsick 
							+ "; Available vacation hours: " + newvac +"; Sick time used: "+ usesick + "; Vacation time used: "+ usevac);
					System.out.println(vecp);
					}
					else {
						System.out.println("Didn't work enough hours to receive PTO");
					}
			}
			else if(slist.Searchid(sid).getposition().equals("SM")) {
				
				if(totalmade >= 38) {
					double newsick = (4 + oldsick) - usesick;
					// calculate vacation
					double newvac = (8 + oldvac) - usevac;
					
					vecp.add("Employee id & name: "+ "(" + slist.Searchid(sid).getempid() + ")" + slist.Searchid(sid).getFname() + " "+ slist.Searchid(sid).getLname() +"; "+  " Available sick hours: " + newsick + "; Available vacation hours: " + newvac +"; Sick time used: "+ usesick + "; Vacation time used: "+ usevac);
					System.out.println(vecp);
					}
					else {
						System.out.println("Didn't work enough hours to receive PTO");
					}
				
			}
			else if (slist.Searchid(sid).getposition().equals("HM")) {
				
				if(totalmade >= 8) {
					double newsick = (2 + oldsick) - usesick;
					// calculate vacation
					double newvac = (4 + oldvac) - usevac;
					
					vecp.add("Employee id & name: "+ "(" + slist.Searchid(sid).getempid() + ")" + slist.Searchid(sid).getFname() + " "+ slist.Searchid(sid).getLname() +"; "+  " Available sick hours: " + newsick + "; Available vacation hours: " + newvac +"; Sick time used: "+ usesick + "; Vacation time used: "+ usevac);
					System.out.println(vecp);
					}
					else {
						System.out.println("Didn't work enough hours to receive PTO");
					}
				
			}
			else if(slist.Searchid(sid).getposition().equals("R")) {
				// calculate sick
				if(totalmade >= 5) {
				double newsick = (2 + oldsick) - usesick;
				// calculate vacation
				double newvac = (2+ oldvac) - usevac;
				
				vecp.add("Employee id & name: "+ "(" + slist.Searchid(sid).getempid() + ")" + slist.Searchid(sid).getFname() + " "+ slist.Searchid(sid).getLname() +"; "+  " Available sick hours: " + newsick + "; Available vacation hours: " + newvac +"; Sick time used: "+ usesick + "; Vacation time used: "+ usevac);
				System.out.println(vecp);
				}
				else {
					System.out.println("Didn't work enough hours to receive PTO");
				}
			}
	
		}
		else if (slist.Searchid(rid).getposition().equals("R")|| slist.Searchid(rid).getposition().equals("SM")||slist.Searchid(rid).getposition().equals("AM")){
			System.out.println("\n Invalid: You need to be a GM to view employee data \n");
		}

	}
	
	// Validator logic for who can clock in for who
	static boolean isvalid(int sid, String sidpos, int rid, String ridpos) {
		if(sid ==rid) {
			if(sidpos.equals("GM")|| sidpos.equals("AM")|| sidpos.equals("SM")|| sidpos.equals("HM")){
					return false;
					}
			else
				return true;
		}
		else if(rid != sid){
			if(ridpos.equals("GM") || ridpos.equals("AM") || ridpos.contentEquals("SM")|| ridpos.contentEquals("HM")) {
				return true;
			}
		}
		return false;
	}
	
	// RA6 Adds an employee to the employee list.
	static void empadd() {
			Scanner scan = new Scanner(System.in);
			Scanner scan2 = new Scanner(System.in);
			
			System.out.println("What is your id");
				int re = scan.nextInt();
				
				if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
					
				System.out.println("Howdy," + slist.Searchid(re).getFname() +" "+ slist.Searchid(re).getLname());
				
				System.out.println("What is the Employees first name");
				
				String firstname = scan2.nextLine();
				
				System.out.println("What is the Employees last name");
				
				String lastname = scan2.nextLine();
				
				System.out.println("What is the Employees ID");
				
				int ID = scan.nextInt();
				Scanner scan1 = new Scanner(System.in);
				
				System.out.println("What is the employees position");
				
				String position = scan1.nextLine();
				
				//System.out.println("What is the employees location");
				
				String location = "STX";

				employeeNode node2 = new employeeNode(ID,firstname,lastname,position,location);
				if(!slist.isDuplicate(node2, ID))
					slist.insert(node2);
					
				}
				else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")||slist.Searchid(re).getposition().equals("HM")){
					System.out.println("\n Invalid: You need to be a GM to make these edits \n");
					
				}
				else
				System.out.println("This uin doesn't exisit");	
	}

// RA8 Deletes an employee record from the employee list.
	static void empdelete() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("What is your id");
		
		int re = scan.nextInt();
		
		if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
			
			System.out.println("Howdy," + slist.Searchid(re).getFname() + slist.Searchid(re).getLname());
			System.out.println("What ID would you like to remove");
			  int id = scan.nextInt();
			  slist.toDelete(id);
			
			}
			
			else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")||slist.Searchid(re).getposition().equals("HM")){
				System.out.println("\n Invalid: You need to be a GM to make these edits \n");
			}
		
			else
			System.out.println("This uin doesn't exisit");
		
		
	}
	
	
	// RA3 Sorts the employee records based on ID
static void bubblesort() {
	
		Scanner scan = new Scanner(System.in);
		System.out.println("What is your id");
	
		int re = scan.nextInt();

		if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
			
			STXList toSort = slist.clone();
			
			toSort.bubble();
			
			
			sorted = toSort;
			System.out.println(sorted);
			
			}
		else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")||slist.Searchid(re).getposition().equals("HM")){
			System.out.println("\n Invalid: You need to be a GM to make these edits \n");
		}
	
		else
		System.out.println("This uin doesn't exisit");
	
	}

	// RA7 Changes employee record
	static void changeemployee(){
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("What is your id");
		
		int re = scan.nextInt();
		
		if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
			
			System.out.println("Howdy," + slist.Searchid(re).getFname() +" "+ slist.Searchid(re).getLname());
			
			System.out.println("What employee would you like to change? (Enter a uin)");
			
			int newid = scan.nextInt();
			
			if(slist.isCopy(newid)) {
			slist.changeemployee(newid);
			}
			
			else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")||slist.Searchid(re).getposition().equals("HM")){
				System.out.println("\n Invalid: You need to be a GM to make these edits \n");
			}
			else {
			System.out.println("This uin doesn't exisit");
			}
		}
	}
	
	//RA2 search for an employee record with a given ID as a parameter
static void searchemployee() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What is your id");
		
		int re = scan.nextInt();
		
		if(slist.Searchid(re) != null && slist.Searchid(re).getposition().equals("GM")) {
			
			System.out.println("What employee would you like to search for; Eneter their ID");
			int rid = scan.nextInt();
			
			System.out.println(slist.Searchid(rid));
			
			}
	else if (slist.Searchid(re).getposition().equals("R")|| slist.Searchid(re).getposition().equals("SM")||slist.Searchid(re).getposition().equals("AM")||slist.Searchid(re).getposition().equals("HM")){
				System.out.println("\n Invalid: You need to be a GM to view this data  \n");
			}
		
			else
			System.out.println("This uin doesn't exisit");

	}
	// Prints the menu to the screen
	static void printmenu() {
		
		System.out.println("--------------Options------------------------------------------------------");
		System.out.println("Option 1. Would you like to add an employee to this location? \n");
		System.out.println("Option 2. Would you like to change employee records from this loation? \n");
		System.out.println("Option 3. Would you like to delete employee records from this location? \n ");
		System.out.println("Option 4. Add and view employee hours  \n");
		System.out.println("Option 5. Add and view employee PTO \n");
		System.out.println("Option 6. Would you like to view employee data for this location? \n");
		System.out.println("Option 7. Would you like to search specific employee data by entering their ID \n");
		System.out.println("Option 8. Would you like to sort all employee records based on their ID \n");
		System.out.println("Option 9. Export employee data to a .txt file");
		System.out.println("Option 10. Exit\n");
		System.out.println("--------------------------------------------------------------------------");
		
	}
	
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
}



// List list for the employees from STX
	class STXList{
		
	 employeeNode head;
	 
	 public STXList() {
		 head = null;
	 }
	 
	    public void insert(employeeNode toinsert) {

	        // if the head is null, this is the front of the list.

	        if(head == null) {
	            head = toinsert;
	            return;
	        }
	        // find the end of the list
	        employeeNode possibleTail = head;
	       
	        while(possibleTail.getNext() != null) { // this is not the end
	            possibleTail = possibleTail.getNext();
	        }
	        // change next pointer of last node to be toinsert
	        possibleTail.setNext(toinsert);
	    }
	    public void bubble() {
			if(head == null || head.getNext() == null)
				return;
			employeeNode previous = null;
			employeeNode current = head;
			employeeNode next = head.getNext();
			
			boolean swapped;
			do {
				swapped = false;
				previous = null;
				current = head;
				next = head.getNext();
				
				while(next != null) {
					if(current.getempid() > next.getempid()) {
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
	    public String toString() {
	        employeeNode step = head;

	        String answer = "Employees:" + System.lineSeparator();

	        while(step != null) {
	            answer += step.toString() + System.lineSeparator();
	            step = step.getNext();
	        }
	        return answer + "End of List";   
	}
	    // Checks for duplicate employee id's within the list 
	    
	    public boolean isDuplicate(employeeNode node1, int id) {
			
			if(head == null)
				return false;
			 
			for(employeeNode node = head; node != null;) {
				
				if(node.getempid() == id) {
					System.out.println("Invalid option(Employee already exist! Please select another employee id!!");
					return true;
				}
				node = node.getNext();
			}
			return false;
		}
	    
	    public void toDelete(int id) {
			
			employeeNode previous = null;
			employeeNode curr = head;
			
			if(curr.getempid() == id) {
			 head = curr.getNext();
			}
			else {
				
				for(curr = head; curr != null;) {
					if(curr.getempid() == id) {
						System.out.println(curr);
						previous.setNext(curr.getNext());
					}
				previous = curr;
				curr = curr.getNext();
				
				}
			}
			
		}
	    public STXList clone() {
			STXList toReturn = new STXList();
			employeeNode temp = head;
			while(temp != null) {
				toReturn.insert(temp.clone());
				temp = temp.getNext();
			}
			return toReturn;
		}
	    public void changeemployee(int id) {
			

			employeeNode curr = head;
			
			for(curr = head; curr != null;) {
				
				
			if(curr.getempid() == id){
				Scanner scan = new Scanner(System.in);
				
				System.out.println("The current Employee info is: ");
				System.out.println(curr);
				
				
				System.out.println("What is the new ID, or press ENTER to keep it the same uin. ");
				String strchangeID = scan.nextLine();
				if(strchangeID.equals("")) {
					curr.getFname();	
				}
				else if(isDuplicate(curr,Integer.parseInt(strchangeID))) {
					System.exit(0);
				}
				else {
					curr.setempid(Integer.parseInt(strchangeID));
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
				
				System.out.println("What is the new position, or press ENTER to keep the same position. ");
				String position = scan.nextLine();
				if(position.equals("")){
					curr.getposition();
				}
				else {
					curr.setposition(position);
				}
				
//				System.out.println("What is the new location, or press ENTER to keep the same location. ");
//				String location = scan.nextLine();
//				if(location.equals("")){
//					curr.getLocation();
//				}
//				else {
//					curr.setLocation(location);
//				}
				curr.setLocation("STX");
			
				
			}
			curr = curr.getNext();
			}
			
			
			}
	    public boolean isCopy(int id) {
			employeeNode curr = head;
			for(curr = head; curr != null;) {
				if(curr.getempid() == id)
					return true;
				curr = curr.getNext();
			}
			return false;
			
		}
	    public employeeNode Searchid(int id) {
			employeeNode curr = head;
			for(curr = head; curr != null;) {
				if(curr.getempid() == id)
					return curr;
				curr = curr.getNext();
			}
			return null;
		}   
	}



// RA4 Mainly done here. classs that creates the employeenode
	class employeeNode{
		private int empid;
		private String myFname;
		private String myLname;
		private String myposition;
		private String mylocation;
		
		
		public employeeNode next;
		
		
		public employeeNode() {
			next = null;
		}
		public employeeNode(int id,String fname, String lname, String position, String location) {
			myFname = fname; myLname = lname; empid = id; myposition = position; mylocation = location; next = null;
			}

		public employeeNode getNext() {
			return next;
		}
		
		public void setNext(employeeNode nex) {
			next = nex;
		}
		
		public String toString() {
			
		return "(" +empid + ")"+ ", "+ myFname + ", " + myLname + "," + myposition + "," + mylocation;
		}
		
		public int getempid() {
			return empid;
			
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
		
		public void setempid(int id) {
			empid = id;
		}
		public String getposition() {
			return myposition;
		}
		public void setposition(String position) {
			
			myposition = position;
		}
		public String getLocation() {
			return mylocation;
		}
		public void setLocation(String location) {
			
			mylocation = location;
		}
		
		public employeeNode clone() {
			return new employeeNode(empid,myFname,myLname,myposition,mylocation);
		}
		
	}
	
	class exportFile{
		String toreturn = ("You shouldn't be in this spot!!");
		public String toString() {
			return toreturn;
		}
	
	}
	
	class exportR extends exportFile{
		{
			try {
	            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	            String strDate = formatter.format(date);
            strDate += ".txt";
	            PrintWriter ex = new PrintWriter(strDate);
	           ex.println(TimeAndAttendance.slist);
	           ex.flush();
	           ex.close();
         System.out.println("Your .txt file was successfully creaated: Contains Employee Records");

	        }
	        catch(FileNotFoundException fx){
            System.out.println(fx);
	        }
			
		}
	
	}
	class exportSP extends exportFile{
		 {
				try {
		            Date date = new Date();
	            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		            String strDate = formatter.format(date);
	            strDate += ".txt";
		            PrintWriter ex = new PrintWriter(strDate);
		           ex.println(TimeAndAttendance.sorted);
		           ex.flush();
		           ex.close();
	         System.out.println("Your .txt file was successfully creaated: Contains Sorted Employee Records");
	         
		        }
		        catch(FileNotFoundException fx){
	            System.out.println(fx);
		        }
				
			}
		}
	class exportV extends exportFile{
		{
			try {
	            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	            String strDate = formatter.format(date);
            strDate += ".txt";
	            PrintWriter ex = new PrintWriter(strDate);
	           ex.println(TimeAndAttendance.vecv);
	           ex.flush();
	           ex.close();
	          
	        System.out.println("Your .txt file was successfully creaated: Contains Verified Employee clock in list ");
			
	        }
	        catch(FileNotFoundException fx){
            System.out.println(fx);
	        }
			
		}
	}
	
	class exportE extends exportFile{
		{	
			try {
	            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	            String strDate = formatter.format(date);
            strDate += ".txt";
	            PrintWriter ex = new PrintWriter(strDate);
	           ex.println(TimeAndAttendance.vece);
	           ex.flush();
	           ex.close();
        System.out.println("Your .txt file was successfully creaated: Contains unvarified Employee clock in list ");

	        }
	        catch(FileNotFoundException fx){
            System.out.println(fx);
	        }
		}
	}
class exportP extends exportFile{
	{
		
			try {
	            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	            String strDate = formatter.format(date);
            strDate += ".txt";
	            PrintWriter ex = new PrintWriter(strDate);
	           ex.println(TimeAndAttendance.vecp);
	           ex.flush();
	           ex.close();
         System.out.println("Your .txt file was successfully creaated: Contains PTO data ");

	        }
	        catch(FileNotFoundException fx){
            System.out.println(fx);
	        }
	}
			
		
}	
	
	

	
	


