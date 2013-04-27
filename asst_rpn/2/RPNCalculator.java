public class RPNCalculator
	{
		private String input;
		public RPNCalculator(String i)
		{
			input=i;
		}
		public void evaluateRPN(String input)
		{
			int arraySize=input.length();
			Stack s=new Stack(arraySize);
			int t=0,flag=0;String c= "";
			search:for(int j=0;j<input.length();j++)
			{
			char ch=input.charAt(j);
			
			switch(ch)
			{
			case'+':if(((s.size())==1)||((s.size())==0)){
				System.out.println("Please enter a valid RPN expression");flag++;break search;}
				s.push((int)s.pop()+(int)s.pop());break  ;
			case'-':if(((s.size())==1)||((s.size())==0)){
				System.out.println("Please enter a valid RPN expression");flag++;break search;}
				s.push(((int)s.pop()-(int)s.pop())*(-1));break;
			case'*':if(((s.size())==1)||((s.size())==0)){
				System.out.println("Please enter a valid RPN expression");flag++;break search;}
				s.push((int)s.pop()*(int)s.pop());
				break;
			case' ':break;

			case '1':case '2':case '3':case '4':case'5':case'6':case'7':case'8':case'9':case'0':
				c=c+Character.toString(ch);
					if((input.charAt(j+1)==' ')||(input.charAt(j+1)=='+')||(input.charAt(j+1)=='*')||(input.charAt(j+1)=='-'))
						{s.push(Integer.parseInt(c));c="";
						}break;
			}
			}
			if(flag==0)
			{
			System.out.println("The final value of the given expression is::"+s.top());
			}
			else
			{}
	}
	}	
