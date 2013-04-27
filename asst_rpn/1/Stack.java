
public class Stack implements Stacks
	{
		private int sizeArray;
		private Object[] array;
		private int empty;
		private int topArray;
		
		public Stack(int mentionSize)
		{
			sizeArray=mentionSize;
			empty=-1;
			array=new Object[sizeArray];
			topArray=empty;
		}
		public boolean isFull()
		{return (topArray==(sizeArray-1));
		}
		public void push(Object o)
		{if(!(isFull()))
			array[++topArray]=o;
		else
			System.out.println("Stack is full");	
			
		}
		public Object pop()
		{if(!(isEmpty()))
		return
			 array[topArray--];
		else
			return null;
		}
			
		public boolean isEmpty()
		{
			return(topArray==-1);
		}
	
		public Object top()
		{
			
			return array[topArray];
		}
	}




		
			
