package kr.green.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test01 {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("ex01()");
		ex01();
		System.out.println("ex02()");
		ex02();
	}

	private static void ex01() {
		// 변수
		int ar[] = new int[3];
		// 입력
		System.out.print("정수 3개 입력 > ");
		for (int i = 0; i < ar.length; i++) ar[i] = sc.nextInt();
		Arrays.sort(ar);
		System.out.println(ar[1]);
	}
	
	private static void ex02() {
		int a, b, c;
		System.out.print("정수 3개 입력 > ");
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		if (a > b) {
			a = a^b; b = a^b; a = a^b;
		}
		if (b > c) {
			b = b^c; c = b^c; b = b^c;
		}
		if (a > b) {
			a = a^b; b = a^b; c = a^b;
		}
		System.out.println(b);
	}
}
