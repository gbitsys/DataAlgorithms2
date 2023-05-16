/**
* @author Giorgos Bitsis
*/
public class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static boolean checkIfEqual(Point point1, Point point2){
        return ((point1.getX() == point2.getX()) && (point1.getY() == point2.getY()));
    }

    public static String compareToCenter(Point center, Point point){
        if(point.getX() <= center.getX() && point.getY() > center.getY()) return "nw";
        else if(point.getX() < center.getX() && point.getY() <= center.getY()) return "sw";
        else if(point.getX() >= center.getX() && point.getY() > center.getY()) return "ne";
        else if(point.getX() >center.getX() && point.getY() <= center.getY()) return "se";
        else if(checkIfEqual(center, point)) return "eq";
        else return "uk";//uknown
        
    }

    @Override 
    public String toString(){
        return ("Point: X = "+x+" Y = "+y);
    }
    
}   
