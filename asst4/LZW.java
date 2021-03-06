import java.io.*;
import java.util.*;
 
public class LZW {
	public String readTextFile(String filename)
	{String returnValue="";
	FileReader file=null;
	String line="";
	try{
		file=new FileReader(filename);
		BufferedReader reader=new BufferedReader(file);
		
		
		while((line=reader.readLine())!=null){
			
				returnValue+=line;
		}		
		}catch(FileNotFoundException e){
			throw new RuntimeException("File not found");
	}catch(IOException e){
		throw new RuntimeException("IO Error occured");
		
	}finally{
		if(file!=null){
			try{
				file.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}return returnValue;
	}
   
    public static List<Integer> compress(String uncompressed) {
        
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);
 	String dict="";
	
	for(int u=32;u<=32;u++)
		dict=dict+"["+"Key:"+Character.toString((char)u)+" , "+"Code:"+u+"]"+"\n";
	for(int o=46;o<=46;o++)
		dict=dict+"["+"Key:"+Character.toString((char)o)+" , "+"Code:"+o+"]"+"\n";
	for(int h=97;h<=122;h++)
		dict=dict+"["+"Key:"+Character.toString((char)h)+" , "+"Code:"+h+"]"+"\n";
	for(int s=65;s<=90;s++)
		dict=dict+"["+"Key:"+Character.toString((char)s)+" , "+"Code:"+s+"]"+"\n";

	
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                
                dictionary.put(wc, dictSize++);
		dict=dict+"["+"Key:"+wc+" , "+"Code:"+dictSize+"]"+"\n";
                w = "" + c;
            }
		
        }
        if (!w.equals(""))
            result.add(dictionary.get(w));
	LZW.write(result.toString());
	LZW.writeto(dict);
        return result;
    }
 
    
    public static String decompress(List<Integer> compressed) {
       
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
        String result = w;
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result += entry;
 
            
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result;
	
    }
 
    public static void main(String[] args)throws IOException {
        LZW myfile=new LZW();
	System.out.println("Options available::\n\t1.Compress a file.\n\t2.Decompress a file.\nPlease enter an option..");
	BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
	String in=b.readLine();
	String choice=new String("1");
	String choic=new String("2");
	if(choice.equals(in))
	{
	System.out.println("Please enter the text file that you want to compress::");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	String file=br.readLine();
		String input=myfile.readTextFile(file);
	List<Integer> compressed = compress(input);
	}	
	else if(choic.equals(in))
	{
	System.out.println("Please enter the text file that you want to decompress::");
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	String files=br.readLine();
		String input=myfile.readTextFile(files);
	List<Integer> result = new ArrayList<Integer>();
	String[] d=input.split(", ");
	String e=d[0];String h="";
	for(int f=1;f<e.length();f++)
	{h+=e.charAt(f);
	
        }
	result.add(Integer.parseInt(h));
	for(int q=1;q<=d.length-2;q++)
	{
		result.add(Integer.parseInt(d[q]));
	}
	String w=d[d.length-1];
	String a="";
	for(int g=0;g<w.length()-2;g++)
	{
	a+=w.charAt(g);
	}
	result.add(Integer.parseInt(a));
	
	String decompressed = decompress(result);
	LZW.writein(decompressed);	}
	else{System.out.println("Retry with a correct option");}
        
    }
public static int  prime(int k)
	{
		
		while(true)
		{
			if(rime(k))
				{return k;
				
				}
			else
				k++;
		}
	}
	
public static boolean rime(int n)
	{int c=0;
	
	for(int i=1;i<=n;i++)
		{
		if(n%i==0)
			c++;
		}
	if(c==2)
		return true;
	else 
		return false;
	}
	
public static void put(String ch,int i,Node[] hashtable)
{	int g=hashtable.length;
	int length=54+ch.length();
	int h=0;int o=0;
	while(o<g)
	{
		int j=i%g;
		if(hashtable[j].code==0)
			{
				hashtable[j].key=(ch);
				hashtable[j].code=i;
				return;
			}
		else
			{i=i+h*h;
			h++;o++;}
	}
}
public static boolean containsKey(String s,Node[] hashtable)
{	int g=hashtable.length;
	int h=0;
int i=0;
		for(int f=0;f<s.length();f++)
		{
			i+=(int)s.charAt(f);
}
	while(h<=g)
	{
		int j=i%g;
		if(hashtable[j].key==s)
			{
				return true;
				
			}
		else
			{i=i+h*h;
			h++;}
	}
		
return false;
}
public static int get(String s,Node[] hashtable)
	{int i=0;
		for(int f=0;f<s.length();f++)
		{
			i+=(int)s.charAt(f);
}
		int g=hashtable.length;
	int h=0;
	while(h<=g)
	{
		int j=i%g;
		if(hashtable[j].key==s)
			{
				return hashtable[j].code;
		
			}
		
			i=i+h*h;
			h++;
	
		
}
return 0;
}


public static void write(String s)
{ 
            
    
    
     try {
BufferedWriter out = new BufferedWriter(new FileWriter("Compressed.txt"));
out.write(s);
out.close();
System.out.println("Generated Compressed File::Compressed.txt");
}
catch (IOException e)
{
System.out.println("Exception ");
}


}
public static void writeto(String s)
{ 
            
    
    
     try {
BufferedWriter out = new BufferedWriter(new FileWriter("Dictionary.txt"));
out.write(s);
out.close();
System.out.println("Generated Dictionary File::Dictionary.txt");
}
catch (IOException e)
{
System.out.println("Exception ");
}


}
public static void writein(String s)
{ 
            
    
    
     try {
BufferedWriter out = new BufferedWriter(new FileWriter("Decompressed.txt"));
out.write(s);
out.close();
System.out.println("Generated Decompressed File::Decompressed.txt");
}
catch (IOException e)
{
System.out.println("Exception ");
}


}}
class Node{
String key;
int code;
Node()
{
key=null;
code=0;
}}
