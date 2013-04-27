import java.io.*;
public class reverseInput
	{
		public static void main(String[] args)
		{
			Stack s=new Stack(args.length);
			for(int i=0;i<args.length;i++)
			{
				s.push(args[i]);
			}
			for(int j=0;j<args.length;j++)
			{
				System.out.println(s.pop());
			}
		}
	}
