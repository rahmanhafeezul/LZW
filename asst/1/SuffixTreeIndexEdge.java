
public class SuffixTreeIndexEdge {


SuffixTreeIndexEdge (String edgeText, SuffixTreeIndexNode childNode) {
this.text = edgeText;
this.childNode = childNode;
}

SuffixTreeIndexEdge () {
this.text = null;
this.childNode = null;	
}

SuffixTreeIndexNode getChild () {
return childNode;
}

void setChild (SuffixTreeIndexNode childNode, String text) {
this.childNode = childNode;	
this.text = text;
}

char charAt (int index) {
return text.charAt(index);
}

int length () {
return text.length();
}

void setText (String text) {
this.text = text;
}

String getText () {
return this.text;
}


boolean isNewLeaf () {
return (childNode == null);
}



private String text;
private SuffixTreeIndexNode childNode;

} 
