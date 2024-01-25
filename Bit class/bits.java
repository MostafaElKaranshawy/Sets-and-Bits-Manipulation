import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface BIT{
    public int getbit(int number, int position);
    public int setbit(int number, int position);
    public int clearbit(int number, int position);
    public int updatebit(int number, int position, boolean value);
}
public class bits implements BIT{
    public int getbit(int number, int position){
        return (number>>(position)) & 1;
    }
    public int setbit(int number, int position){
        number = number | (1 << (position));
        return number;
    }
    public int clearbit(int number, int position){
        number = number & ~(1 << (position));
        return number;
    }
    public int updatebit(int number, int position, boolean value){
        if(value)return setbit(number, position);
        else return clearbit(number, position);
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        BIT b = new bits();
        while(true){
            System.out.println("Choose operation: ");
            System.out.println("1-Get bit");
            System.out.println("2-Set bit");
            System.out.println("3-Clear bit");
            System.out.println("4-Update bit");
            System.out.println("5-Exit the program");
            int choose = in.nextInt();
            while(choose > 5){
                System.out.println("Wrong Input!\n please choose from 1 to 5");
                choose = in.nextInt();
            }
            if(choose == 5){
                System.out.println("THE PROGRAM ENDS!");
                System.exit(0);
            }
            System.out.println("Enter the number: ");
            int n = in.nextInt();
            System.out.print("Enter the position of the bit: ");
            int p = in.nextInt();
            switch(choose){
                case 1:
                    int ans1 = b.getbit(n, p);
                    System.out.println("The bit is: "+ans1);
                    break;
                case 2:
                    int ans2 = b.setbit(n, p);
                    System.out.println("The number after set bit is: "+ans2);
                    break;
                case 3:
                    int ans3 = b.clearbit(n, p);
                    System.out.println("The number after clear bit is: "+ans3);
                    break;
                case 4:
                    System.out.println("Enter the new value of the bit: ");
                    int p5 = in.nextInt();
                    while(p5 != 0 && p5 !=1){
                        System.out.println("Enter a valid value to set (0,1): ");
                        p5 = in.nextInt();
                    }
                    boolean value = false;
                    if(p5 == 0)value = false;
                    else value = true;
                    int ans4 = b.updatebit(n, p, value);
                    System.out.println("The number after update bit is: "+ans4);
                    break;
                default:
                    in.close();
                    System.out.println("Wrong input");
                    break;
            }
            System.out.println("");
        }
    }
}