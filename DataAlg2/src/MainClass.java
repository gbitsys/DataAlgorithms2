/**
* @author Giorgos Bitsis
*/
import java.util.Random;
public class MainClass {
    private static final int upper=65536;
    
    public static void main(String [] args){
        int M [] = {200, 500, 1000, 10000, 30000, 50000, 70000, 100000};
        System.out.println("KD trees results");
        for (int i=0; i<M.length; i++){
            kdTreesTest(M[i]);
        }
        System.out.println();
        System.out.println("PR Quadtrees results");
        for (int i=0; i<M.length; i++){
            prTreesTest(M[i]);
        }        

    }
    /**
    * 
    * @param numOfPoints
    * the parameter used to create an array of points,
    * we split this array to search for existing and non-existing keys,
    * with the help of a numOfSearches variable inside function
    */
    public static void kdTreesTest(int numOfPoints){
        int numOfSearches = 100;
        int depthFound = 0;
        int depthNotFound = 0;
        Point tempPoint;
        Point[] points = new Point[numOfPoints];
        Random rn = new Random();

        KdTree tree = new KdTree();

        //inserting keys that don't already exists and keep this array for searching existing keys
        int i=0;
        do{
            tempPoint = new Point(rn.nextInt(upper), rn.nextInt(upper));
            if (tree.searchPoint(tempPoint)==null) {
                tree.insertNode(tempPoint);
            	points[i]= tempPoint;
            	i++;
            }
        } while(i<numOfPoints);
        System.out.println("Points inserted: "+numOfPoints);
        
        i=0;
        int j=0;
        //searching existing keys
        do{
            if (tree.searchPoint(points[rn.nextInt(numOfPoints)])!=null) {
            	depthFound+=MultiCounter.getCount(2); 
            	i++;
                j++;
            	}
                
        } while(i<numOfSearches);
        System.out.println("Keys: "+numOfPoints+" Found depth: "+depthFound/(double)numOfSearches);

        //searching non-existing keys
        j=0;
        do{
            if (tree.searchPoint(new Point(rn.nextInt(upper), rn.nextInt(upper)))== null) {
                depthNotFound+=MultiCounter.getCount(2); 
                j++;
            }
        } while(j<numOfSearches);
        System.out.println("Keys: "+numOfPoints+" Not found depth: "+depthNotFound/(double)numOfSearches);
    }
    
    public static void prTreesTest(int numOfPoints){
        int numOfSearches = 100;
        int depthFound = 0;
        int depthNotFound = 0;
        Point tempPoint;
        Point[] points = new Point[numOfPoints];
        Random rn = new Random();

        PrTree tree;
        tree = new PrTree(new Rectangle(upper));

        //inserting keys that don't already exists and keep this array for searching existing keys
        int i=0;
        do{
            tempPoint = new Point(rn.nextInt(upper), rn.nextInt(upper));
            if (tree.insertPoint(tempPoint)) {
                points[i]=tempPoint;
                i++;
            }
        } while(i<numOfPoints);
        System.out.println("Points inserted: "+i);

        i=0;
        int j=0;
        MultiCounter.resetCounter(2);
        //searching existing keys
        do{
            if (tree.searchPoint(points[rn.nextInt(numOfPoints)])!=null) {
            	depthFound+=MultiCounter.getCount(2); 
                MultiCounter.resetCounter(2);
            	i++;
                j++;
            	}
                
        } while(i<numOfSearches);
        System.out.println("Keys: "+numOfPoints+" Found depth: "+depthFound/(double)numOfSearches);

        //searching non-existing keys
        j=0;
        MultiCounter.resetCounter(2);
        do{
            if (tree.searchPoint(new Point(rn.nextInt(upper), rn.nextInt(upper)))==null) {
                depthNotFound+=MultiCounter.getCount(2); 
                MultiCounter.resetCounter(2);
                j++;
            }
        } while(j<numOfSearches);
        System.out.println("Keys: "+numOfPoints+" Not found depth: "+depthNotFound/(double)numOfSearches);

    }
}
