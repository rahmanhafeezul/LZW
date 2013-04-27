import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class input{static SuffixTreeIndex suffixTreeIndex = new SuffixTreeIndex();
public static void ReadFile2String(String InputFile) throws IOException
    {
     
        FileInputStream fstream = new FileInputStream(InputFile);
        
        DataInputStream in = new DataInputStream(fstream);
 
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        String text = "";
            while ((strLine = br.readLine()) != null)
            {
            
            text +=(strLine+"\n");
            System.out.println(text);}
            
            String s = text;
            BufferedReader keybd=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter the text u want to search for::");
            String textual=keybd.readLine();
            String[] arr = s.split(" ");    

            for ( int i=0;i<arr.length;i++) {

                  
                  suffixTreeIndex.addText(arr[i], i);
             }ArrayList<Integer> caca = suffixTreeIndex.search(textual);
             System.out.println("The text u wanted is in position::" + caca);
        
        }
        
    
	public static void main(String[] args) throws IOException {
		
		input.ReadFile2String("file.txt");
		
	}

}
