public class RPN
	{
		private String input;
		public RPN(String i)
		{
			input=i;
		}
		public void evaluateRPN(String input)
		{
			int arraySize=input.length();
			Stack s=new Stack(arraySize);
			int t=0;String c= "";
			for(int j=0;j<input.length();j++)
			{
			char ch=input.charAt(j);
			
			switch(ch)
			{
			case'+':s.push((int)s.pop()+(int)s.pop());break;
			case'-':s.push(((int)s.pop()-(int)s.pop())*(-1));break;
			case'*':s.push((int)s.pop()*(int)s.pop());
				break;
			case' ':break;

			case '1':case '2':case '3':case '4':case'5':case'6':case'7':case'8':case'9':case'0':
				c=c+Character.toString(ch);
					if((input.charAt(j+1)==' ')||(input.charAt(j+1)=='+')||(input.charAt(j+1)=='*')||(input.charAt(j+1)=='-'))
						{s.push(Integer.parseInt(c));c="";
						}break;
			}
			}
			
			System.out.println("The final value of the given expression is::"+s.top());
			if(s.isEmpty())
			{System.out.println("error");
			}
	}
	}	
