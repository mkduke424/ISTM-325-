
import java.util.Scanner;

public class Week2_project {
	
	public static void main(String args[])
	{	
		menu();
			
	}
	
	static void menu()
	{
		System.out.println("Choose an option:"+ "\n Option 1: Prime List" + "\n Option 2: Goldbach's Conjecture" +"\n Option 3: Fibonacci series");
		Scanner scanny = new Scanner(System.in);
		String response = scanny.nextLine();
		
		if (response.equals("1"))
		{
		Primelist();
		}
		else if(response.equals("2"))
		{
			Goldbach();
		}
		else if(response.contentEquals("3"))
		{
			Fibonaci();
		}
				
		else
			System.out.println("invalid response");
		
		menu();
			
	}
	
	static void Primelist()
	{
		System.out.println("Enter a number ");
		try {
		Scanner scanny = new Scanner(System.in);
		int input = scanny.nextInt();
		
		if(input == 1 || input == 0 )
		{
			System.out.println("Invalid Response");
		}
		else if(input >= 2)
		{
			for(int i = 2; i <= input; i++)
			{
			if(isPrime(i))
				System.out.println(i + ",");
			}
		}
		}
		catch(Exception e) {
		
			System.out.println("Invalid Response");
		}
		menu();
			
	}
	
	public static boolean isPrime(int input)
	{
		for (int i = 2; i<= input/2; i++)
		{
			if(input % i == 0)
				return false;			
		}
		return true;	
	}
	
	static void Goldbach()
	{
		try {
		System.out.println("Enter an even Integer");
		Scanner scanny = new Scanner(System.in);
		int input = scanny.nextInt();
		
		if(input >= 2 && isEven(input))
		{
			for(int i = 3; i<= input/2; i++)
			{
				if(isPrime(i) && isPrime(input - i))
					System.out.println("The two numbers are " + i + " and " + (input - i));
			}
		}
		else
			
			System.out.println("Invalid Response");
		}
		catch(Exception e)
		{
			System.out.println("Invalid response");
		}
		menu();
	
		
		
	}
	public static boolean isEven(int input)
	{
		if(input % 2 != 0)
		{
			return false;
		}
		return true;
	}
	public static void Fibonaci()
	{
		try {
		System.out.println("Enter an element");
		Scanner scanny = new Scanner(System.in);
		int input = scanny.nextInt();
		
		System.out.println(RecurFib(input));
		
		}
		
		catch(Exception e)
		{
			System.out.println("Invalid Response");
		}
		menu();
		
		
	}
	static int RecurFib(int input)
	{
		int answer = 0;
		if(input == 0)
		{
			answer = 0;
		}
		else if(input == 1)
		{
			answer = 1;
		}
		else
		answer = RecurFib(input - 1) + RecurFib(input - 2);
	
		return answer;
		}
	}
	
	

	


