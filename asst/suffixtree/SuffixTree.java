

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class SuffixTree {
    public static final int MAX_LENGTH = 1000;

   
    public static int HASH_TABLE_SIZE = 2179;

    
    public static Edge[] EDGES = new Edge[HASH_TABLE_SIZE];

    
    public static Node[] NODES = new Node[MAX_LENGTH * 2];

    static {
        for (int i = 0; i < EDGES.length; i++) {
            EDGES[i] = new Edge();
        }
        for (int i = 0; i < NODES.length; i++) {
            NODES[i] = new Node();
        }
    }

    
    public static char[] T;
    public static int N;

    
    public static void addPrefix(Suffix active, int last_char_index) {
        int parent_node;
        int last_parent_node = -1;

        for (; ;) {
            Edge edge;
            parent_node = active.origin_node;

            
            if (active.explicit()) {
                edge = Edge.find(active.origin_node, T[last_char_index]);
                if (edge.start_node != -1)
                    break;
            } else {
                
                edge = Edge.find(active.origin_node, T[active.first_char_index]);
                int span = active.last_char_index - active.first_char_index;
                if (T[edge.first_char_index + span + 1] == T[last_char_index])
                    break;
                parent_node = edge.splitEdge(active);
            }

            
            Edge new_edge = new Edge(last_char_index, N, parent_node);
            new_edge.insert();
            if (last_parent_node > 0)
                NODES[last_parent_node].suffix_node = parent_node;
            last_parent_node = parent_node;

            
            if (active.origin_node == 0)
                active.first_char_index++;
            else
                active.origin_node = NODES[active.origin_node].suffix_node;
            active.canonize();
        }
        if (last_parent_node > 0)
            NODES[last_parent_node].suffix_node = parent_node;
        active.last_char_index++;  
        active.canonize();
    }

    
    public static void dump_edges(int current_n) {
        System.out.println("\tFirst \t\tLast \t\tString");
        for (int j = 0; j < HASH_TABLE_SIZE; j++) {
            Edge s = EDGES[j];
            if (s.start_node == -1)
                continue;

            System.out.print("\t"  + " " +
                   + s.first_char_index + " " +
                    "\t\t" + s.last_char_index + " " +
                    "\t\t");
            int top;
            if (current_n > s.last_char_index)
                top = s.last_char_index;
            else
                top = current_n;
            for (int l = s.first_char_index; l <= top; l++) {
                System.out.print(T[l]);
            }
            System.out.println();
            System.out.flush();
        }
    }
    public  static void search(Node[] arr,String P)throws IOException
{
	int p=P.length();
	int j=0;
Node v=SuffixTree.NODES[0];
 
 
	boolean f;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String text=br.readLine();
        char[] X=text.toCharArray();
	while(f=true){
	for (int i = 0; i < SuffixTree.HASH_TABLE_SIZE; i++)
	{ Edge s=EDGES[j];
            int k=s.first_char_index;
            char[] pattern=P.toCharArray();
            if(pattern[j]==X[i])
            {int x=s.last_char_index-i+1;
                if(p<=x)
                {for(int h=j;h<=j+p-1;h++)
                    System.out.println(i-j);
                }
                else{System.out.println("pattern is not suffix of x");}}
         else
            {int x=s.last_char_index-i+1;
            
                for(int w=j;w<=j+x-1;w++)
            {if(pattern[j]==X[i])
            {p=p-x;
            j=j+x;
            
            f=false;
            break;
            }
                }
            }
                    }
        System.out.println("pattern not found;");
            }
                
                
        }

   


 
    
    
    
    
    
}
