

import java.util.*;


public class SuffixTreeIndex {

    public SuffixTreeIndex() {
        root = new SuffixTreeIndexNode();
    }

    
    public void addText(String text, int matchIndex) {
        new SuffixTreeOverlay(text, matchIndex);
    }

    
    public ArrayList<Integer> search(String searchText) {
        SuffixTreeIndexNode searchNode = root;
        int edgeIndex = 0;
        int length = searchText.length();
        int searchIndex = 0;
        SuffixTreeIndexEdge edge = null;
        boolean match = true;
        ArrayList<Integer> returnList = new ArrayList<Integer>();

        while (searchIndex < length) {

           
            if (edge == null) {
                edge = searchNode.getChild(searchText.charAt(searchIndex));
                if (edge == null) {
                    match = false;
                    break;
                }
                edgeIndex = 0;
            } else {
                
                if (searchText.charAt(searchIndex) != edge.charAt(edgeIndex)) {
                    match = false;
                    break;
                }
            }
           
            if (edgeIndex == (edge.length() - 1)) {
                searchNode = edge.getChild();
                edge = null;
            }

            
            searchIndex++;
            edgeIndex++;
        }

        if (match) {
            if (edge != null) {
                searchNode = edge.getChild();
            }
            searchNode.fillMatchIds(returnList, 100);
        }
        return returnList;
    }

    
    private SuffixTreeIndexNode root;

    
    private class SuffixTreeOverlay {

        SuffixTreeOverlay(String text, int matchIndex) {
            root = new SuffixTreeOverlayNode(SuffixTreeIndex.this.root);
            this.text = text;
            this.matchIndex = matchIndex;
            newLeafEdges = new LinkedList<SuffixTreeOverlayEdge>();

            build();
        }

        public void build() {
            SKTuple sk = new SKTuple(root, 1);
            int length = text.length();

            for (int i = 1; i <= length; i++) {
                
                update(sk, i);
                
                canonize(sk, i);
            }

            addMatchIds(sk, length);
            
            
            ListIterator<SuffixTreeOverlayEdge> leafIterator = newLeafEdges.listIterator(0);
            while (leafIterator.hasNext()) {
                SuffixTreeOverlayEdge leafEdge = leafIterator.next();

                
                leafEdge.getStIndexEdge().setChild(new SuffixTreeIndexNode(matchIndex),
                                                   text.substring(leafEdge.getStartIndex() - 1, length));
            }
        }

        private class SKTuple {
            int kIndex;
            SuffixTreeOverlayNode sState;

            SKTuple(SuffixTreeOverlayNode sState, int kIndex) {
                this.kIndex = kIndex;
                this.sState = sState;
            }
        }

        private class TestAndSplitReturn {
            boolean endPointReached;
            SuffixTreeOverlayNode rState;

            TestAndSplitReturn(boolean endPointReached, SuffixTreeOverlayNode rState) {
                this.endPointReached = endPointReached;
                this.rState = rState;
            }
        }

        
        private void update(SKTuple sk, int i) {
            SuffixTreeOverlayNode oldr = root;
            TestAndSplitReturn testAndSplitReturn;

            testAndSplitReturn = testAndSplit(sk, i - 1, text.charAt(i - 1));

            while (!testAndSplitReturn.endPointReached) {
                
                SuffixTreeIndexEdge newIndexEdge = new SuffixTreeIndexEdge();
                SuffixTreeOverlayEdge newOverlayEdge = new SuffixTreeOverlayEdge(i, newIndexEdge);

                newLeafEdges.add(newOverlayEdge);
                testAndSplitReturn.rState.getStIndexNode().addChild(text.charAt(i - 1), newIndexEdge);
                testAndSplitReturn.rState.addChild(text.charAt(i - 1), newOverlayEdge);

                
                if (oldr != root) {
                    oldr.setSuffixLink(testAndSplitReturn.rState);
                }

                oldr = testAndSplitReturn.rState;

                
                if (sk.sState == root) {
                    
                    if (sk.kIndex <= i - 1) {
                        sk.kIndex++;
                    }
                   
                    else {
                        sk.kIndex++;
                        break;
                    }
                } else {
                    sk.sState = sk.sState.getSuffixLink();
                    canonize(sk, i - 1);
                }
                testAndSplitReturn = testAndSplit(sk, i - 1, text.charAt(i - 1));
            }

            
            if (oldr != root) {
                oldr.setSuffixLink(sk.sState);
            }

            return;
        }

       
        private TestAndSplitReturn testAndSplit(SKTuple sk, int pIndex, char tVal) {
            SuffixTreeOverlayEdge tOverlayEdge = sk.sState.getChild(text.charAt(sk.kIndex - 1));
            SuffixTreeIndexEdge tIndexEdge = sk.sState.getStIndexNode().getChild(text.charAt(sk.kIndex - 1));

            if (sk.kIndex <= pIndex) {

                
                char nextChar = (tOverlayEdge.isDiscoveryLeaf()) ?
                                 tIndexEdge.charAt(pIndex - sk.kIndex + 1) :
                                 text.charAt(tOverlayEdge.getStartIndex() + pIndex - sk.kIndex);

                if (tVal == nextChar) {
                    
                    return new TestAndSplitReturn(true, sk.sState);
                } else {
                    
                    SuffixTreeIndexNode rIndexState = new SuffixTreeIndexNode();
                    SuffixTreeOverlayNode rOverlayState = new SuffixTreeOverlayNode(rIndexState);

                    SuffixTreeIndexEdge newIndexEdge = new SuffixTreeIndexEdge(text.substring(sk.kIndex - 1, pIndex), rIndexState);
                    if (!tOverlayEdge.isDiscoveryLeaf()) {
                        SuffixTreeOverlayEdge newOverlayEdge = new SuffixTreeOverlayEdge(tOverlayEdge.getStartIndex(),
                                                                                         tOverlayEdge.getStartIndex() + pIndex - sk.kIndex,
                                                                                         rOverlayState, newIndexEdge);
                        sk.sState.addChild(text.charAt(sk.kIndex - 1), newOverlayEdge);
                        tOverlayEdge.setStartIndex(tOverlayEdge.getStartIndex() + pIndex - sk.kIndex + 1);
                        rOverlayState.addChild(nextChar, tOverlayEdge);
                    } else {
                        tOverlayEdge.setChild(rOverlayState, tOverlayEdge.getStartIndex() + pIndex - sk.kIndex);
                    }

                    
                    sk.sState.getStIndexNode().addChild(text.charAt(sk.kIndex - 1), newIndexEdge);

                   
                    if (!tOverlayEdge.isNewLeaf()) {
                        tIndexEdge.setText(tIndexEdge.getText().substring(pIndex - sk.kIndex + 1));
                    }

                    rIndexState.addChild(nextChar, tIndexEdge);

                    return new TestAndSplitReturn(false, rOverlayState);
                }
            } else {
                

                if (tOverlayEdge != null) {
                    return new TestAndSplitReturn(true, sk.sState);
                } else if (tIndexEdge != null) {
                    
                    SuffixTreeOverlayEdge newOverlayEdge = new SuffixTreeOverlayEdge(sk.kIndex, tIndexEdge);
                    sk.sState.addChild(text.charAt(sk.kIndex - 1), newOverlayEdge);
                    return new TestAndSplitReturn(true, sk.sState);
                } else {
                    return new TestAndSplitReturn(false, sk.sState);
                }
            }

        }

        
        private void canonize(SKTuple sk, int pIndex) {
            if (pIndex < sk.kIndex) {
                return;
            } else {
                SuffixTreeOverlayEdge overlayEdge = sk.sState.getChild(text.charAt(sk.kIndex - 1));
                SuffixTreeIndexEdge indexEdge = sk.sState.getStIndexNode().getChild(text.charAt(sk.kIndex - 1));

                
                while (!indexEdge.isNewLeaf()) {

                   
                    if (overlayEdge == null) {
                        overlayEdge = new SuffixTreeOverlayEdge(sk.kIndex, indexEdge);
                    }

                    if (indexEdge.length() - 1 <= (pIndex - sk.kIndex)) {

                        
                        if (overlayEdge.isDiscoveryLeaf()) {
                            SuffixTreeOverlayNode newState = new SuffixTreeOverlayNode(indexEdge.getChild());
                            overlayEdge.setChild(newState, sk.kIndex + indexEdge.length() - 1);

                            
                            SKTuple localSk = new SKTuple(sk.sState, sk.kIndex);
                            if (localSk.sState == root) {
                                localSk.kIndex++;
                            } else {
                                localSk.sState = localSk.sState.getSuffixLink();
                            }

                            
                            canonize(localSk, overlayEdge.getEndIndex());

                            
                            newState.setSuffixLink(localSk.sState);

                        }

                        sk.kIndex = sk.kIndex + indexEdge.length();
                        sk.sState = overlayEdge.getChild();

                        if (pIndex < sk.kIndex) {
                            break;
                        }

                        overlayEdge = sk.sState.getChild(text.charAt(sk.kIndex - 1));
                        indexEdge = sk.sState.getStIndexNode().getChild(text.charAt(sk.kIndex - 1));
                    } else {
                        break;
                    }
                }
                return;
            }
        } 
        private void addMatchIds(SKTuple sk, int pIndex) {

            if (pIndex < sk.kIndex) {
                
                if (sk.sState.isLeaf()) {
                    sk.sState.getStIndexNode().addMatchIndex(matchIndex);
                }
                
                
                if (sk.sState != root) {
                    sk.sState = sk.sState.getSuffixLink();
                    
                    addMatchIds(sk, pIndex);

                }
                
            }
            else {
                SuffixTreeOverlayEdge overlayEdge = sk.sState.getChild(text.charAt(sk.kIndex - 1));
                SuffixTreeIndexEdge indexEdge = sk.sState.getStIndexNode().getChild(text.charAt(sk.kIndex - 1));

                // if the edge is a discovery node, create a new node
                // and add the match index
                if (overlayEdge.isDiscoveryLeaf()) {
                    SuffixTreeIndexNode newIndexState = new SuffixTreeIndexNode();
                    SuffixTreeIndexEdge newIndexEdge = new SuffixTreeIndexEdge(text.substring(sk.kIndex - 1, pIndex), newIndexState);

                    // add the transition for index from s,k,p to r
                    sk.sState.getStIndexNode().addChild(text.charAt(sk.kIndex - 1), newIndexEdge);

                    // add the transition to the rest of the tree ahead
                    indexEdge.setText(indexEdge.getText().substring(pIndex - sk.kIndex + 1));

                    newIndexState.addChild(indexEdge.getText().charAt(0), indexEdge);
                    
                    newIndexState.addMatchIndex(matchIndex);
                    
                    // find the suffix link for sState
                    // in this implementation, we do not have a separate
                    // node for state _|_
                    // the check for root state tells how to find the
                    // suffixlink for the root
                    if (sk.sState == root) {
                        sk.kIndex++;
                    } else {
                        sk.sState = sk.sState.getSuffixLink();
                    }

                    // discover suffix link for the new state
                    canonize(sk, pIndex);

                    // add match id to the suffix
                    addMatchIds(sk, pIndex);
                }
            }

        }
        

        // private
        private SuffixTreeOverlayNode root;
        private String text;
        private int matchIndex;
        private LinkedList<SuffixTreeOverlayEdge> newLeafEdges;

    } // class SuffixTreeOverlay

}


