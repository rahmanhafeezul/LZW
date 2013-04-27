import java.io.*;
public class test{
public static void main(String[] args)throws IOException
{
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter a string of which u need to build the suffix tree::");
String input=br.readLine();
String in=input+"$";
SuffixTree.T = in.toCharArray();
        SuffixTree.N = in.length() - 1;

        
        Suffix active = new Suffix(0, 0, -1);
        for (int i = 0; i <= SuffixTree.N; i++) {
            SuffixTree.addPrefix(active, i);
        }
        SuffixTree.dump_edges(SuffixTree.N);
  /*  BufferedReader keybd=new BufferedReader(new InputStreamReader(System.in));
        String inp=keybd.readLine();
	char[] inpu=inp.toCharArray();
        SuffixTree.search(SuffixTree.NODES, inp);*/
}}
	
