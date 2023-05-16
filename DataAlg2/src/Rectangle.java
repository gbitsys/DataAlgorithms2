/**
* @author Giorgos Bitsis
*/
public class Rectangle{
    private int width, height;
    private Point center;

    public Rectangle(int width, int height, Point center) {
        this.width = width;
        this.height = height;
        this.center = center;
    }

    public Rectangle(int dimension){
        this.width = dimension;
        this.height = dimension;
        this.center = new Point(width/2, height/2);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getCenter() {
        return center;
    }

    public boolean containsPoint(Point pointInsert) {
        return pointInsert.getX()>=center.getX()-(width/2) && pointInsert.getX()<center.getX()+(width/2) && pointInsert.getY()>= getCenter().getY()-(height/2) && pointInsert.getY() < center.getY()+(height/2);
    }

    public Rectangle divide(String direction) {
        int centerXE = center.getX()+(width/4);
        int centerYN = center.getY()+(height/4);
        int centerXW = center.getX()-(width/4);
        int centerYS = center.getY()-(height/4);
        int widthNew = width/2;
        int heightNew = height/2;
        Point centerNew;
        if (direction == "ne") {
            centerNew = new Point(centerXE, centerYN);
            return new Rectangle(widthNew, heightNew, centerNew);
        }
        else if(direction == "nw"){
            centerNew = new Point(centerXW, centerYN);
            return new Rectangle(widthNew, heightNew, centerNew);
        }
        else if(direction == "sw"){
            centerNew = new Point(centerXW, centerYS);
            return new Rectangle(widthNew, heightNew, centerNew);
        }
        else if(direction == "se"){
            centerNew = new Point(centerXE, centerYS);
            return new Rectangle(widthNew, heightNew, centerNew);
        }
        else return null;
    }

    
    
}