package mmn11;

import java.util.Scanner;
public class Equation{
    public static void main (String [] args){
        //Receiving a real number
        Scanner scan = new Scanner (System.in);
        System.out.println("Enter 3 coefficients of the polynomial equation:");
        double a = scan.nextDouble();
        double b = scan.nextDouble();
        double c = scan.nextDouble();

        //Discriminant calculus
        double dis = (b*b) - (4*a*c);
        
        //Calculation of two results
        double plus = (-b + Math.sqrt(dis)) / (2*a);
        double minus = (-b - Math.sqrt(dis)) / (2*a);

        //Printing results according to conditions
        if(dis >= 0){
            if(dis==0){
                System.out.println("There is 1 solution. X1 = " + plus);
            }
            else if(dis>0){
                System.out.println("There is 1 solution. X1 = " + plus + " , " + "X2 = " + minus);
            }
        }
        else{
            System.out.println("There is no solution." );
        }
    }
}