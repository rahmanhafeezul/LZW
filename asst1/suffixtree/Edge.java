




public class Edge {
    public int first_char_index;
    public int last_char_index;
    public int end_node;
    public int start_node;

   
    public void insert() {
        int i = hash(start_node, SuffixTree.T[first_char_index]);
        while (SuffixTree.EDGES[i].start_node != -1) {
            i = ++i % SuffixTree.HASH_TABLE_SIZE;
        }
        SuffixTree.EDGES[i] = this;
    }

    
    public void remove() {
        int i = hash(start_node, SuffixTree.T[first_char_index]);
        while (SuffixTree.EDGES[i].start_node != start_node ||
                SuffixTree.EDGES[i].first_char_index != first_char_index)
            i = ++i % SuffixTree.HASH_TABLE_SIZE;
        for (; ;) {
            SuffixTree.EDGES[i].start_node = -1;
            int j = i;
            for (; ;) {
                i = ++i % SuffixTree.HASH_TABLE_SIZE;
                if (SuffixTree.EDGES[i].start_node == -1)
                    return;
                int r = hash(SuffixTree.EDGES[i].start_node, SuffixTree.T[SuffixTree.EDGES[i].first_char_index]);
                if (i >= r && r > j)
                    continue;
                if (r > j && j > i)
                    continue;
                if (j > i && i >= r)
                    continue;
                break;
            }
            SuffixTree.EDGES[j] = SuffixTree.EDGES[i];
        }
    }

    
    public Edge() {
        start_node = -1;
    }

    
    public Edge(int init_first_char_index, int init_last_char_index, int parent_node) {
        this.first_char_index = init_first_char_index;
        this.last_char_index = init_last_char_index;
        this.start_node = parent_node;
        this.end_node = Node.Count++;
    }

    
    public int splitEdge(Suffix s) {
        remove();
        Edge new_edge = new Edge(first_char_index,
                first_char_index + s.last_char_index - s.first_char_index,
                s.origin_node);
        new_edge.insert();
        SuffixTree.NODES[new_edge.end_node].suffix_node = s.origin_node;
        first_char_index += s.last_char_index - s.first_char_index + 1;
        start_node = new_edge.end_node;
        insert();
        return new_edge.end_node;
    }

    
    public static Edge find(int node, int c) {
        int i = hash(node, c);
        for (; ;) {
            if (SuffixTree.EDGES[i].start_node == node)
                if (c == SuffixTree.T[SuffixTree.EDGES[i].first_char_index])
                    return SuffixTree.EDGES[i];
            if (SuffixTree.EDGES[i].start_node == -1)
                return SuffixTree.EDGES[i];
            i = ++i % SuffixTree.HASH_TABLE_SIZE;
        }
    }

    
    public static int hash(int node, int c) {
        return ((node << 8) + c) % SuffixTree.HASH_TABLE_SIZE;
    }
}
