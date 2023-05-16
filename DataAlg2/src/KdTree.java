/**
* @author Giorgos Bitsis
*/
public class KdTree {
    KdNode root;
    int depth; //just a variable that helps us in some methods

    public KdTree(){
        this.root = null;
        this.depth = 0;
    }

    //nested class for nodes of this tree
    private class KdNode{
        KdNode leftChild = null;
        KdNode rightChild = null;
        Point point = null; //our data in node

        public KdNode(Point point){
            this.point = point;
            this.leftChild = null;
            this.rightChild = null;
        }

        public KdNode getLeftChild() {
            return leftChild;
        }

        public KdNode getRightChild() {
            return rightChild;
        }

        public Point getPoint() {
            return point;
        }

        /**
         * 
         * @return true, node is leaf
         * @return false node is not a leaf
         */
        public boolean isLeaf(KdNode node){
            return (node.getLeftChild()==null && node.getRightChild()==null);
        }

        @Override
        public String toString(){
            return ("KdNode X = "+this.point.getX()+" Y = "+this.point.getY());
        }
    }

    public KdNode insertNode(Point pointInsert){
        this.root =  insertPriv(pointInsert, this.root, 0);
        return root;

    }

    private KdNode insertPriv(Point pointInsert, KdNode node, int privDepth){
        //if tree is empty then the node we insert is root node
        if (isEmpty()) {
            this.root = new KdNode(pointInsert);
            return root;
        }  
        if (node == null) {
            node = new KdNode(pointInsert);
            return node; 
        }
        int xIns = pointInsert.getX();
        int yIns = pointInsert.getY();
        if (node.getPoint().getX() == xIns && node.getPoint().getY() == yIns) return node;//checking if node already exists

        //comparison with 'x' value
        if(privDepth%2==0){
            if (xIns < node.getPoint().getX()){
                node.leftChild = (insertPriv(pointInsert, node.getLeftChild(), privDepth+1));
            }
            else if(xIns >= node.getPoint().getX()){
                node.rightChild = (insertPriv(pointInsert, node.getRightChild(), privDepth+1));
            }
        }
        //comparison with 'y' value
        else{
            if(yIns < node.getPoint().getY()){
                node.leftChild = (insertPriv(pointInsert, node.getLeftChild(), privDepth+1));
            }
            else if(yIns >= node.getPoint().getY()){
                node.rightChild = (insertPriv(pointInsert, node.getRightChild(), privDepth+1));
            }
        }

        return node;
    }

    public KdNode searchPoint(Point pointSearch){
        return searchNodePriv(pointSearch, this.root, 0);
    }

    /**
     * Method implementation for actual search in the tree
     * @param pointSearch
     * @param node
     * @param privDepth
     * @return KdNode if point is found, null if it isn't found
     */
    private KdNode searchNodePriv(Point pointSearch, KdNode node, int privDepth){
        MultiCounter.resetCounter(2);
        int xSearch = pointSearch.getX();
        int ySearch = pointSearch.getY();

        if (node == null) {
            MultiCounter.increaseCounter(2, privDepth); 
            return null; 
        }//key not found

        if (Point.checkIfEqual(pointSearch, node.point)){
            MultiCounter.increaseCounter(1); 
            MultiCounter.increaseCounter(2, privDepth); 
            return node; 
        } //key found
  
         //comparison with 'x' value
        if(privDepth%2==0){
            if (xSearch < node.point.getX()){
                return searchNodePriv(pointSearch, node.getLeftChild(), privDepth+1);
            }
            else{
                return searchNodePriv(pointSearch, node.getRightChild(), privDepth+1);
            }
        }
        //comparison with 'y' value
        if(ySearch < node.point.getY()){
                return searchNodePriv(pointSearch, node.getLeftChild(), privDepth+1);
        }
        else{
            return searchNodePriv(pointSearch, node.getRightChild(), privDepth+1);
        }
            
    }


    public void setRoot(KdNode rootToSet){
        this.root = rootToSet;
    }
    public KdNode getRoot() {
        return root;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isEmpty(){
        return root==null;
    }
}
