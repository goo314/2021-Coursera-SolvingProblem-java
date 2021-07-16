import edu.duke.*;
import java.io.File;

/**
 * Calculate a lots of interesting facts about shapes
 * 
 * @author goo314
 * @version Jun 26, 2021
 */

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

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point currPT : s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalLength = getPerimeter(s);
        int numPt = getNumPoints(s);
        double AverageL = totalLength/numPt;       
        return AverageL;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double max = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(max < currDist){
                max = currDist;
            }
            prevPt = currPt;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largeX = 0.0;
        for(Point currPt : s.getPoints()){
            if(largeX < currPt.getX()){
                largeX = currPt.getX();
            }
        }
        return largeX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largePerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(largePerim < currPerim){
                largePerim = currPerim;
            }
        }
        return largePerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File file = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        /*
        double largePerim = getLargestPerimeterMultipleFiles();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(currPerim == largePerim){
                file = f;
            }
        }
        */
        
        double largePerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(largePerim < currPerim){
                file = f;
                largePerim = currPerim;
            }
        }
        return file.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int num = getNumPoints(s);
        System.out.println("num = " + num);
        
        double AvgL = getAverageLength(s);
        System.out.println("average = " + AvgL);
        
        double largeSide = getLargestSide(s);
        System.out.println("largest side = " + largeSide);
        
        double largeX = getLargestX(s);
        System.out.println("largest X = " + largeX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largePerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter = " + largePerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fname = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter = " + fname);
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
    }
}
