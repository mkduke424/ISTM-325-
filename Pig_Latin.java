
import java.util.Scanner;

public class Pig_Latin {
	
	public static void main(String args[])
	{
		
		try {
		System.out.println("What would you like to translate to piglatin: ");
		
		Scanner scanny = new Scanner(System.in);
		String input = scanny.nextLine();
		
		input = input.trim();
		input = input.toLowerCase();
		
		
		
		String [] arrofStr = input.split(" ");  // sections off each word between spaces
		
		
	for(String a: arrofStr)
	{
	System.out.print(translate(a) + " ");    // for each word in the string; throw it into the function
		
	}
		}
		catch(Exception e){
			System.out.println("Nawwl. no numbers");
		
			
		}
	
	}
	

	 public static String translate(String word)   
	{
		
		 String output = "";
		 String punctuation = "";
		 
		 // iterates through word and stores punctuation
		 
		 for(int i = 0; i < word.length(); i++) 
		 {
//			 if(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')
//			 {
//				 System.out.println("error!!! no numbers");
//			 }
			 if(word.charAt(i) == ',' || word.charAt(i) == '!' || word.charAt(i) == '.'|| word.charAt(i)=='?')
			 {
				 punctuation = Character.toString(word.charAt(i));
				  	 
			 }
			  
		 }
		 
		 String newword = word.replace(punctuation,"");  // everywhere there is a punctuation we will remove it
	
			
		 // pig latin rules 
		if(newword.charAt(0) == 'a' || newword.charAt(0) == 'e'||newword.charAt(0) == 'i' || 
				newword.charAt(0) == 'o' || newword.charAt(0) == 'u')
		{
			
			output = newword.substring(0,newword.length()) + "way" ;
		}
		else if(newword.charAt(0) != 'a' && newword.charAt(1) != 'a' && newword.charAt(0) != 'e' && newword.charAt(1) != 'e' &&
				newword.charAt(0) != 'i' && newword.charAt(1) != 'i' && newword.charAt(0) != 'o' && newword.charAt(1) != 'o' &&
						newword.charAt(0) != 'u' && newword.charAt(1) != 'u')
		{
			output = newword.substring(2,newword.length()) + newword.charAt(0) + newword.charAt(1)+ "ay";
		}
		else if(newword.charAt(0) != 'a' && newword.charAt(1) == 'a' || newword.charAt(0) != 'e' && newword.charAt(1) == 'e' ||
				newword.charAt(0) != 'i' && newword.charAt(1) == 'i' || newword.charAt(0) != 'o' && newword.charAt(1) == 'o' ||
						newword.charAt(0) != 'u' && newword.charAt(1) == 'u')
		{
			output = newword.substring(1,newword.length()) + newword.charAt(0) + "ay" ;
		}
			
		return output + punctuation;
	}
	
	}
	 

	
	


