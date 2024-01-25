import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class bitmanipulation{
    static int odd_number(int[] elements, int n) {
        int a = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)a ^= elements[j];
            if(a != 0)break;
        }
        return a;
    }
    
    static int ones(int n){
        int one = 0;
        while(n > 0){
            if((n & 1) == 1)one++;
            n = n >> 1;
        }
        return one;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Choose Your function:");
            System.out.println("1- GET unique element in an array.");
            System.out.println("2- GET NUMBER OF '1' BITS IN AN INTEGER.");
            System.out.println("3-Exit The program.");
            System.out.println("Enter Your Choice: ");
            int choice = in.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the size of the array: ");
                    int n = in.nextInt();
                    while(n%2 == 0){
                        System.out.println("Size of the array must be odd");
                        System.out.println("Enter the size again: ");
                        n = in.nextInt();
                    }
                    int[] a = new int[n];
                    System.out.println("Enter array elements: ");
                    for(int i = 0; i < n; i++){
                        a[i] = in.nextInt();
                    }
                    System.out.println("The number that appear one time is: "+odd_number(a, n));
                    break;
                case 2:
                    System.out.println("Enter the number: ");
                    int n1 = in.nextInt();
                    System.out.println("The number of ones in "+ n1 + " is "+ones(n1));
                    break;
                case 3:
                    System.out.println("The program ends!");
                    System.exit(0);
                    in.close();
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }
        }
    }
}