import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    public int Method1(int []array) {
        int sum = 0;
        for (int i = 0;i < array.length;i++) {
            sum+=array[i];
        }
        return sum;
    }

    public void Method2(String str) {
        int max = 0;
        int[] count = new int[256];
        int arr_len = str.length();
        ArrayList<Character> arr_res = new ArrayList<Character>();

        for (int i =0;i<arr_len;i++) {
            count[str.charAt(i)]++;
        }

        for (int i = 0;i<arr_len;i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
            }
            if (count[str.charAt(i)]==count[str.charAt(max)]) {
                arr_res.add(str.charAt(i));
            }
        }
        
        Set<Character> set_res = new HashSet<Character>(arr_res);
        if (set_res.size()>=2) {
            for (Character temp :set_res ){
                System.out.println(temp);
            }
        }else {
            System.out.print(str.charAt(max));
        }

        
    }
    public void Method3(int array[]) {
        for (int i=1;i<array.length;i++) {
            int val = array[i];
            int j = i;

            while (j > 0 && array[j-1]>val) {
                array[j] = array[j-1];
                j = j - 1;
            }
            array[j] = val;
        }
    }
    public boolean Method4(int n) {
        if (n<=1 || n%2==0) {
            return false;
        }

        else if (n==2) {
            return true;
        }

        for (int i=3;i<=Math.sqrt(n);i++) {
            if (n%i==0) {
                return false;
            }
        }
        return true;
    }
    // public static double area;
    public class invalidTriangle extends Exception {
        public invalidTriangle (String str) {
            super(str);
        }
    }
    public double Method5 (double a, double b,double c) throws invalidTriangle{
        double s = (a+b+c)/2;
        double area;
        if (a+b<=c || b+c<=a || a+c<=b) {
            throw new invalidTriangle("Invalid Triangle");
        }else {
            area = (double)Math.sqrt(s*(s-a)*(s-b)*(s-c));
        }
       
        return area;
    }
    public void Method6 (int radius) {
        double d;
        for (int i=0;i<=2*radius;i++) {
            for (int j=0;j<=2*radius;j++) {
                d = Math.sqrt((i-radius)*(i-radius)+(j-radius)*(j-radius));

                if (d > radius - 0.5 && d < radius + 0.5) {
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[])  {
        Scanner scanner = new Scanner(System.in);
        Solution M = new Solution();
        int num_case = scanner.nextInt();
        switch (num_case) {
            case 1:
                int arr_size = scanner.nextInt(); 
                int []array = new int[arr_size];
                for (int i =0;i<arr_size;i++) {
                    array[i] = scanner.nextInt();
                }
                System.out.print(M.Method1(array));
                break;
                
            case 2:
                String str = scanner.next();
                M.Method2(str);
                break;
            case 3:
                int size = scanner.nextInt(); 
                int []arr = new int[size];
                M.Method3(arr);
                for (int i=0;i<size;i++) {
                    System.out.print(arr[i]);
                }
                break;
                
            case 4:
                int n = scanner.nextInt();
                if (M.Method4(n)) {
                    System.out.print("True");
                }else {
                    System.out.print("False");
                }
                break;
               
            case 5:
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double c = scanner.nextDouble();
                try {
                    System.out.print(M.Method5(a, b, c));
                }catch(invalidTriangle e) {
                   System.out.print("Invalid Triangle");
                }
                break;
               
            case 6:
                int rad = scanner.nextInt();
                M.Method6(rad);
                break;
        }
        scanner.close();
    }
}