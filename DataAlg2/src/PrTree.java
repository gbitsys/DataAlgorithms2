
/**
* @author Giorgos Bitsis
*/
class PrTree{
    private static final int QT_CAPACITY = 1;
    private Rectangle boundary;

    private Point points[] = null;

    //childs
    private PrTree nw;
    private PrTree sw;
    private PrTree ne;
    private PrTree se;

    public PrTree (Rectangle boundary){
        this.boundary = boundary;
        this.points = new Point[QT_CAPACITY];
        this.points[0] = null;
        nw = null;
        sw = null;
        ne = null;
        se = null;
    }
    
    public Rectangle getBoundary() {
        return boundary;
    }

    public PrTree getNw() {
        return nw;
    }

    public PrTree getSw() {
        return sw;
    }

    public PrTree getNe() {
        return ne;
    }

    public PrTree getSe() {
        return se;
    }

    public boolean insertPoint(Point pointInsert) {return insertPriv(pointInsert);}

    private boolean insertPriv(Point pointInsert){
        if (searchPoint(pointInsert)!=null) return false; //we do not want to insert a key twice

        if (!boundary.containsPoint(pointInsert)) 
            return false; //we accept only points in our boundary

        if (points[0]==null && nw == null){
            this.points[0] = pointInsert;
            return true;
        }

        if (nw==null){
           subdivide();
        }

        if (nw.insertPriv(pointInsert)){
            return true;
        }
        if (sw.insertPriv(pointInsert)){
            return true;
        }
        if (se.insertPriv(pointInsert)){
            return true;
        }
        if (ne.insertPriv(pointInsert)){
            return true;
        }

        return false; //reaches here when we reached lowest size of rectangle available

    }

    private void subdivide(){
        Point tempPoint = this.points[0];
        this.nw = new PrTree(boundary.divide("nw"));
        this.ne = new PrTree(boundary.divide("ne"));
        this.sw = new PrTree(boundary.divide("sw"));
        this.se = new PrTree(boundary.divide("se"));
        this.points[0] = null;
        
        //when we can't subdivide the rectangle anymore
        if (this.boundary.getWidth()<=2 && this.boundary.getHeight()<=2){
            this.nw.points[0] = tempPoint;
            return;
        }

        
        this.insertPriv(tempPoint);

    }

    public PrTree searchPoint(Point pointSearch){return searchPriv(pointSearch);}

    private PrTree searchPriv(Point pointSearch){
        if (!boundary.containsPoint(pointSearch)) return null;

        if (this.boundary.getWidth()<=2 && this.boundary.getHeight()<=2 && nw!=null){
            if (nw.points[0]!=null && Point.checkIfEqual(pointSearch, nw.points[0])){
                return nw;
            }
        }   
        //leaf node
        if (nw==null){
            if (points[0]!=null && Point.checkIfEqual(pointSearch, points[0])){
                return this;
            }
            return null;
        }

        if (nw.boundary.containsPoint(pointSearch)){
            MultiCounter.increaseCounter(2, 1);
            return nw.searchPriv(pointSearch);
        }
        if (sw.boundary.containsPoint(pointSearch)){
            MultiCounter.increaseCounter(2, 1);
            return sw.searchPriv(pointSearch);
        }
        if (ne.boundary.containsPoint(pointSearch)){
            MultiCounter.increaseCounter(2, 1);
            return ne.searchPriv(pointSearch);
        }
        if (se.boundary.containsPoint(pointSearch)){
            MultiCounter.increaseCounter(2, 1);
            return se.searchPriv(pointSearch);
        }
        return null;
    }
    
}