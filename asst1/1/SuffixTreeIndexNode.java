

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;



class SuffixTreeIndexNode {
SuffixTreeIndexNode() {
}

SuffixTreeIndexNode(int matchIndex) {
addMatchIndex(matchIndex);	
}


void addChild (char childChar, SuffixTreeIndexEdge edge) {
childNodes.put(childChar, edge);
}

SuffixTreeIndexEdge getChild (char childChar) {
return childNodes.get(childChar);
}

void addMatchIndex(int matchIndex) {
matchIndices.add(matchIndex);	
}

Iterator<Integer> getmatchIndexIterator() {
return matchIndices.iterator();
}

int fillMatchIds(ArrayList<Integer> returnList, int maxEntries) {
int numAdded = 0;
Iterator<Integer> matchIdIterator = matchIndices.iterator();


while (numAdded < maxEntries) {
if (matchIdIterator.hasNext()) {
returnList.add(matchIdIterator.next());
numAdded++;
}
else {

break;
}
}

if (numAdded == maxEntries) {
return numAdded;
}


Iterator<SuffixTreeIndexEdge> childNodeIterator = childNodes.values().iterator();

while (numAdded < maxEntries) {
if (childNodeIterator.hasNext()) {
numAdded += childNodeIterator.next().getChild().fillMatchIds(returnList, maxEntries - numAdded);
}
else {

break;
}
}

return numAdded;	

}


private HashMap<Character, SuffixTreeIndexEdge> childNodes = new HashMap<Character, SuffixTreeIndexEdge>();
private HashSet<Integer> matchIndices = new HashSet<Integer>();



} 
