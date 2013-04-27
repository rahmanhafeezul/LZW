import java.util.HashMap;


class SuffixTreeOverlayNode {
SuffixTreeOverlayNode(SuffixTreeIndexNode stIndexNode) {
childNodes = new HashMap<Character, SuffixTreeOverlayEdge>();
suffixLink = null;
this.stIndexNode = stIndexNode;
}


void addChild (char childChar, SuffixTreeOverlayEdge edge) {
childNodes.put(childChar, edge);
}

SuffixTreeOverlayEdge getChild (char childChar) {
return childNodes.get(childChar);
}


void setSuffixLink (SuffixTreeOverlayNode suffixLink) {
this.suffixLink = suffixLink;
}

SuffixTreeOverlayNode getSuffixLink () {
return suffixLink;
}

SuffixTreeIndexNode getStIndexNode () {
return stIndexNode;
}

boolean isLeaf() {
return childNodes.isEmpty();
}


private HashMap<Character, SuffixTreeOverlayEdge> childNodes;

private SuffixTreeOverlayNode suffixLink;

private SuffixTreeIndexNode stIndexNode;


} 

