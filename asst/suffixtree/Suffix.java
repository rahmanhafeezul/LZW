class Suffix {
    public int origin_node;
    public int first_char_index;
    public int last_char_index;

    public Suffix(int node, int start, int stop) {
        this.origin_node = node;
        this.first_char_index = start;
        this.last_char_index = stop;
    }

    public boolean explicit() {
        return this.first_char_index > this.last_char_index;
    }

    public boolean implicit() {
        return last_char_index >= first_char_index;
    }

    public void canonize() {
        if (!explicit()) {
            Edge edge = Edge.find(origin_node, SuffixTree.T[first_char_index]);
            int edge_span = edge.last_char_index - edge.first_char_index;
            while (edge_span <= (last_char_index - first_char_index)) {
                first_char_index = first_char_index + edge_span + 1;
                origin_node = edge.end_node;
                if (first_char_index <= last_char_index) {
                    edge = Edge.find(edge.end_node, SuffixTree.T[first_char_index]);
                    edge_span = edge.last_char_index - edge.first_char_index;
                }
            }
        }
    }
} 
    
