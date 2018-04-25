package package1;


import java.util.Arrays;
import java.util.regex.Pattern;





public class Demo1 {
	
	public static void main(String args[]){

		/*if(Pattern.matches("Your last payment paid on (0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)", "Your last payment paid on 30/11/2017"))
		{
			System.out.println("1 pass");
			System.out.println("babu");
		}
		if(Pattern.matches("x+", "xxwxxxx"))
		{
			System.out.println("1 fail");
		}
		if(Pattern.matches("[a-z,A-Z]+", "aE"))
		{
			System.out.println("2 pass");
		}
		if(Pattern.matches("[a-z,A-Z]+", "sd3"))
		{
			System.out.println("2 fail");
		}
		if(Pattern.matches("[0-9]{2}\\*{4}\\**[0-9]{2}", "23*****36"))
		{
			System.out.println("3 pass");
		}
		if(Pattern.matches("[0-9]{2}\\*{4}\\**[0-9]{2}", "23***222"))
		{
			System.out.println("3 fail");
		}
		if(Pattern.matches("[0-9]{2} \\*{2}\\** [0-9]{2}", "23 **** 24"))
		{
			System.out.println("4 pass");
		}
		if(Pattern.matches("[0-9]{2} \\*{2}\\** [0-9]{2}", "23  *** 2"))
		{
			System.out.println("4 fail");
		}*/
         String inputString="babubabu";
		char tempArray[] = inputString.toCharArray();
        
        // sort tempArray
        Arrays.sort(tempArray);
        
        System.out.println(tempArray);
        int max=0;
        int count=1;
        int alpb = 0;
        for(int i=0;i<tempArray.length-1;i++)
        {
        	if(tempArray[i]==tempArray[i+1])
        	{
        		count++;
        		
        	}
        	else if(count>max){
        		max=count;
        		count=1;
        		alpb=i;
        	}
        }
		
		System.out.println(max);
		System.out.println(tempArray[alpb]);
		
	} 
}