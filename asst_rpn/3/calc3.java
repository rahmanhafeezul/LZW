import java.io.*;
public class calc3
{               public static String output = "";
		public static void main(String[] args)throws IOException
	{
		String input;
		
			System.out.print("enter the expression to be calculated in arithmetic form::");
			input=acceptString();
		if (verifyString(input))
		{System.out.println("You entered a valid expression");
		Calculator calculator =new Calculator(input);
		String processed=calculator.convertExpression(input);
		System.out.println("The expression in RPN format is::"+processed);	
		RPN thecalc=new RPN(processed);
		thecalc.evaluateRPN(processed);
		}
		else
		{System.out.println("Please enter a correct expression and try again");
		}
		
	}

	public static String acceptString()throws IOException
	{InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader keybd=new BufferedReader(isr);
		String s=keybd.readLine();
		return s;
	}


	public static boolean Bracket(String s)
	{
		int i,j=0,k=0;
		for(i=0;i<s.length();i++)
		{char ch=s.charAt(i);
		switch(ch)
		{
			case '(':j++;break;
			case ')':k++;break;
			default:break;
		}}
		if(!(j==k))
		
			{System.out.println("There was some error in bracketting");
			return false;}
		else return true;
	}
		
	public static boolean Order(String s)
	{
		
		char ar=s.charAt(0);
			if((ar=='+')||(ar=='-')||(ar=='*'))
			{	System.out.println("An expression can never begin with an operator");
				return false;
			}
			else
			{return true;	
			}
			
	}
	public static boolean operCheck(String s)
	{
		int i;
		label:for(i=0;i<s.length()-1;i++)
		{
			char ch=s.charAt(i);
			char next=s.charAt(i+1);
			switch(ch)
			{
			
		case'+':case'-':case'*':if(((int)next>=48&&(int)next<=57)||(next=='('))
							{break label;
							}
							else
							{
							System.out.println("An operator should always be succeeded by an integer or '(' only");
								return false;}
			
		}}
		return true;
	}
	public static boolean last(String s)
		{
			char ch=s.charAt(s.length()-1);
			if(((int)ch>=42)&&((int)ch<=45))
				{
					System.out.println("A valid expression can never last with an operator");
					return false;
				}
			else
				return true;
		}
	public static boolean verifyString(String s)
		{
			if((Order(s))&&(Bracket(s))&&(last(s))&&(operCheck(s)))
			return true;
			else
			return false;
		}
	


}













































