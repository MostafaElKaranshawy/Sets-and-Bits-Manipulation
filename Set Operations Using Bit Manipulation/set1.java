import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class SET{
    String[] Uni;
    int size, Ub;
    private int[] bit_set = new int[1000];
    SET(String[] s, int nn){
        this.Uni = s;
        this.size = nn;
    }
    public void addsubset(String[] s, int s_size, int index){
        bit_set[index] = convert(s, s_size);
    }
    boolean check(String[] w, String e){
        for(String ee : w){
            if(ee.equals(e))return true;
        }
        return false;
    }
    public int convert(String[] ss, int s1){
        int ans = 0;
        for(int i = 0; i < size; i++){
            ans = ans << 1;
            if(check(ss,Uni[i])){
                ans = ans | 1;
            }
        }
        return ans;
    }
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
    public int ones(int n){
        int one = 0;
        while(n > 0){
            if(n%2 == 1)one++;
            n = n >> 1;
        }
        return one;
    }

    public int add(String element, String[] s_set){
        int set_size = s_set.length;
        int set = convert(s_set,set_size);
        int index = size-1;
        for(int i = 0; i <size; i++){
            if(Uni[i].equals(element))break;
            index--;
        }
        return setbit(set, index);
    }
    public String[] getSubset(int index){
        return(getSet(bit_set[index]));
    }
    public String[] getSet(int n1){
        int set = n1;
        int sn = ones(set);
        String[] s = new String[sn];
        int index = 0;
        for(int i = size-1; i >= 0 && index < sn; i--){
            if(set%2 != 0){
                s[index] = Uni[i];
                index++;
            }
            set/=2;
        }
        return s;
    }
    public String[] union(int n1, int n2){
        int set1 = bit_set[n1];
        int set2 = bit_set[n2];
        int a = set1|set2;
        String[] ans = getSet(a);
        return ans;
    }
    public String[] intersection(int n1, int n2){
        int set1 = bit_set[n1];
        int set2 = bit_set[n2];
        int a = set1&set2;
        String[] ans = getSet(a);
        return ans;
    }
    public String[] complement(int n1){
        int set1 = bit_set[n1];
        int a = Ub&~set1;
        String[] ans = getSet(a);
        return ans;
    }
    public String[] difference(int n1, int n2){
        int set1 = bit_set[n1];
        int set2 = bit_set[n2];
        int a = set1&~set2;
        String[] ans = getSet(a);
        return ans;
    }
    public int cardinality(int n1){
        int set = bit_set[n1];
        return ones(set);
    }
}

public class set1 {
    
    // create a new subset.
    public static void create(SET set, int k){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the size of the set "+k);
        int n1 = in.nextInt();
        System.out.println("Enter the elements of set "+k);

        String[] set1 = new String[n1];
        for(int i = 0; i < n1; i++){
            set1[i] = in.next();
        }
        set.addsubset(set1, n1, k);
        
        // return ans;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        // take universal set.
        System.out.println("Enter the universal set size \">=31\": ");
        int n = in.nextInt();
        // Check the size of the set.
        while(n > 32){
            System.out.println("Please enter a valid size '>=31: ");
            n = in.nextInt();
        }
        // take universal set elements.
        System.out.println("Enter the Universal set (" + n + ") elements: ");
        String[] U = new String[n];
        for(int i = 0; i < n; i++){
            U[i] = in.next();
            // remove duplicates.
            for(int j = 0; j < i; j++){
                if(U[j].equals(U[i])){
                    i--;
                    n--;
                    break;
                }
            }
        }

        SET set = new SET(U, n);
        set.Ub = set.convert(U,n);

        // Take subsets of universal set.
        System.out.println("Enter the number of Subsets: ");
        int subs = in.nextInt();
        // int[] subsets = new int[subs+1];
        for(int i = 1; i <= subs; i++){
            create(set, i);
        }

        // Program starts.
        while(true){
            // Main Menu
            System.out.println("Choose one operation from the following: ");
            System.out.println("1-Union of two sets");
            System.out.println("2-intersection of two sets");
            System.out.println("3-Complement of set");
            System.out.println("4-Difference between two sets");
            System.out.println("5-Cardinality of a set");
            System.out.println("6-Print a set");
            System.out.println("7-Exit the program");
            // option selection.
            System.out.println("Enter your choose: ");
            int choose = in.nextInt();
            // Invalid option number.
            while(choose > 7){
                System.out.println("Please enter a valid choose");
                choose = in.nextInt();
            }
            switch(choose){
                // The union set
                case 1:
                    System.out.println("Enter the number of two sets: ");
                    int i1 = in.nextInt();
                    int i2 = in.nextInt();
                    String[] a = set.union(i1, i2);
                    // System.out.println(ans);
                    String[] m1 = set.getSubset(i1);
                    String[] m2 = set.getSubset(i2);
                    // String[] a = set.getSubset(ans);
                    System.out.print("The Union of ");
                    for(String x: m1)System.out.print(x+" ");
                    System.out.print(" and ");
                    for(String x: m2)System.out.print(x+" ");
                    System.out.println("is: ");
                    for(String x: a)System.out.print(x+" ");
                    break;
                // The intersection set.
                case 2:
                    System.out.println("Enter the number of two sets: ");
                    int i11 = in.nextInt();
                    int i21 = in.nextInt();
                    String[] a1 = set.intersection(i11, i21);
                    // String[] a1 = set.getSubset(ans1);
                    String[] m11 = set.getSubset(i11);
                    String[] m22 = set.getSubset(i21);
                    System.out.print("The Intersection of ");
                    for(String x: m11)System.out.print(x+" ");
                    System.out.print(" and ");
                    for(String x: m22)System.out.print(x+" ");
                    System.out.println("is: ");
                    for(String x: a1)System.out.print(x+" ");
                    break;
                // The complement set.
                case 3:
                    System.out.println("Enter the number of the set: ");
                    int n_set = in.nextInt();
                    String[] a2 = set.complement(n_set);
                    // String[] a2 = set.getSubset(ans2);
                    String[] d_set = set.getSubset(n_set);
                    System.out.print("The complement of the set ");
                    for(String x: d_set)System.out.print(x+" ");
                    System.out.println("is : ");
                    for(String x: a2)System.out.print(x+" ");
                    break;
                // The difference set.
                case 4:
                    System.out.println("Enter the number of two sets: ");
                    int set1 = in.nextInt();
                    int set2 = in.nextInt();
                    String[] diff_set = set.difference(set1, set2);
                    // String[] diff_set = set.getSubset(diff);
                    String[] m3 = set.getSubset(set1);
                    String[] m4 = set.getSubset(set2);
                    System.out.print("The Difference between ");
                    for(String x: m3)System.out.print(x+" ");
                    System.out.print(" and ");
                    for(String x: m4)System.out.print(x+" ");
                    System.out.println("is: ");
                    if(diff_set.length == 0)System.out.println("[]");
                    for(String x: diff_set)System.out.print(x+" ");
                    break;
                // The cardinality of the set.
                case 5:
                    System.out.println("Enter the number of the set: ");
                    int a_set = in.nextInt();
                    int card = set.cardinality(a_set);
                    String[] ss = set.getSubset(a_set);
                    System.out.print("The Cardinality of ");
                    for(String x: ss)System.out.print(x+" ");
                    System.out.println("is: "+card);
                    break;
                // print the set.
                case 6:
                    System.out.println("Enter the number of the set");
                    int nset = in.nextInt();
                    String[] the_set = set.getSubset(nset);
                    System.out.print("The set number "+ nset + " is: ");
                    for(String x: the_set)System.out.print(x+" ");
                    break;
                // Exit the program.
                case 7:
                    System.out.println("\nTHE PROGRAM ENDS!");
                    System.exit(0);
                    break;
                // Wrong option.
                default:
                    System.out.println("Wrong input");
                    break;
            }
            System.out.println("");
        }
    }
}
