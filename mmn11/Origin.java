package mmn11;

import java.util.Scanner;
public class Origin
{
 public static void main (String [] args)
 {
 //Receiving input from the user
 Scanner scan = new Scanner (System.in);
 System.out.println("Enter first point coordinates:");
 int x1 = scan.nextInt();
 int y1 = scan.nextInt();
 System.out.println ("Enter second point coordinates:");
 int x2 = scan.nextInt();
 int y2 = scan.nextInt();
 System.out.println ("Enter third point coordinates:");
 int x3 = scan.nextInt();
 int y3 = scan.nextInt();
 
 //Calculation of distance to each point
 double d1 = Math.sqrt(Math.pow((x1-0),2) + Math.pow((y1-0),2));
 double d2 = Math.sqrt(Math.pow((x2-0),2) + Math.pow((y2-0),2));
 double d3 = Math.sqrt(Math.pow((x3-0),2) + Math.pow((y3-0),2));
 
 //Finding the minimum distance
 double minNum = Math.min(d1,Math.min(d2,d3));
 
 //Print the nearest points
 if(minNum == d1){
     System.out.println ("The nearest point to the origin is" + "(" + x1 + "," + y1 + ")");
 }
 else if(minNum == d2){
     System.out.println ("The nearest point to the origin is" + "(" + x2 + "," + y2 + ")");
 }
 else{
     System.out.println ("The nearest point to the origin is" +""+ "(" + x3 + "," + y3 + ")");
 }
 
 } // end of method main
} //end of class Origin