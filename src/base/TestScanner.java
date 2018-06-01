package base;


import java.util.Scanner;

public class TestScanner{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入");
        int n = sc.nextInt();
        System.out.println("n的值是"+n);
        String s = sc.next();
        System.out.println(s);
        sc.close();
    }
}