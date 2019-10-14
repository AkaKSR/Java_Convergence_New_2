package kr.green.test;

import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) {
		int x, y, n;
		Scanner sc = new Scanner(System.in);
		System.out.print("Command > ");
		x = sc.nextInt();
		y = sc.nextInt();
		n = sc.nextInt();
		
		print(x, y, n);
		sc.close();
	}
	
	private static void print(int x, int y, int n) {
		System.out.print(n/x + " " + n/y);
	}
}
