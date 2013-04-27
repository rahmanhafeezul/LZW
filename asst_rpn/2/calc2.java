import java.io.*;
public class calc2
{	public static void main(String[] args)throws IOException
	{
		String input;
		
			System.out.print("enter the expression to be calculated in RPN format::");
			input=acceptString();
			
		RPNCalculator thecalc=new RPNCalculator(input);
		thecalc.evaluateRPN(input);
		
	}

	public static String acceptString()throws IOException
	{InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader keybd=new BufferedReader(isr);
		String s=keybd.readLine();
		return s;
	}
}
