public class Stack
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
		public Object size()
		{
			return (topArray+1);
		}
		public void push(Object o)
		{
			array[++topArray]=o;
			
			
		}
		public Object pop()
		{
			
			return array[topArray--];
		
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




		
			
