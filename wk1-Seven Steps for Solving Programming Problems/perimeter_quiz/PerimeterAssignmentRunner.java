import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    // returns an integer that is the number of points in Shape s
    public int getNumPoints (Shape s) {
        int numOfPoints = 0;
        for (Point p : s.getPoints()){
            numOfPoints += 1;
        }
        return numOfPoints;
    }
    //This method returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S
    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        int numOfPoints = getNumPoints(s);
        return perimeter/((double)numOfPoints);
    }
    
    //returns a number of type double that is the longest side in the Shape S.
    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist >= largestSide){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }
    //returns a number of type double that is the largest x value over all the points in the Shape s.
    public double getLargestX(Shape s) {
        int largestX = 0;
        for(Point pt : s.getPoints()){
            int x = pt.getX();
            if(x >= largestX){
                largestX = x;
            }
        }
        return (double)largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curPeri = getPerimeter(s);
            if (curPeri >= largestPerimeter){
                largestPerimeter = curPeri;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null; 
        double largestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curPeri = getPerimeter(s);
            if (curPeri >= largestPerimeter){
                largestPerimeter = curPeri;
                temp = f;
            }
        }
        return temp.getName();
    }
    
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        //call getNumPoints and to print the result
        int numOfPoints = getNumPoints(s);
        System.out.println("number of points = " + numOfPoints);
        //call the method getAverageLength and to print out the result.
        double avgLength = getAverageLength(s);
        System.out.println("average side length = " + avgLength);
        //call the method getLargestSide and to print out the result
        double largestSide = getLargestSide(s);
        System.out.println("largest side length = " + largestSide);
        //call the method getLargestX and to print out the result
        double largestX = getLargestX(s);
        System.out.println("largest x value = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter is = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String filename = getFileWithLargestPerimeter();
        System.out.println("The file with largest perimeter is = " + filename);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        System.out.println("");
    }
}
