public class Calculator
	{
		private Stack theStack;
   		private String input;
   		private String output = "";
   		public Calculator(String in) {
      		input = in;
      		int stackSize = input.length();
      		theStack = new Stack(stackSize);
		}
	public String convertExpression(String input) {
      		for (int j = 0; j < input.length(); j++) {
         	char ch = input.charAt(j);
         	switch (ch) {
            case '+':
            case '-':output=output+" "; 
            ifOperator(ch, 1); 
            break; 
            case '*':
            output=output+" ";
            ifOperator(ch, 2); 
            break; 
            case '(': 
            theStack.push(ch);
            break;
            case ')':
            ifBrackets(ch); 
            break;
            default: 
            output = output + ch; 
            break;
         }
      }
      while (!theStack.isEmpty()) {
         output = output +theStack.pop();
      }
      
      return output; 
   }
   public void ifOperator(char opThis, int priority1) {
      while (!theStack.isEmpty()) {
         char opTop = (char)theStack.pop();
         if (opTop == '(') {
            theStack.push(opTop);
            break;
         }
         else {
            int priority2;
            if (opTop == '+' || opTop == '-')
            priority2 = 1;
            else
            priority2 = 2;
            if (priority2 < priority1) { 
               theStack.push(opTop);
               break;
            }
		    else
            output = output+opTop;
         }
      }
      theStack.push(opThis);
   }
   public void ifBrackets(char ch){ 
      while (!theStack.isEmpty()) {
         char chx = (char)theStack.pop();
         if (chx == '(') 
         break; 
         else
         output = output+" "+ chx; 
      }
   }


   


}
