package kr.green.test;

import java.util.Scanner;

public class Test03 {
	public static void main(String[] args) {
		int ar[] = new int[4];
		int arMultiple[] = new int[4];
		Scanner sc = new Scanner(System.in);
		
		System.out.print("값 4개를 입력하시오 > ");
		for (int i = 0; i < ar.length; i++) ar[i] = sc.nextInt();
		arMultiple[0] = ar[1]*ar[2]*ar[3];
		arMultiple[1] = ar[0]*ar[2]*ar[3];
		arMultiple[2] = ar[0]*ar[1]*ar[3];
		arMultiple[3] = ar[0]*ar[1]*ar[2];
		
		System.out.println(arMultiple[0] + ", " + arMultiple[1] + ", " + arMultiple[2] + ", " + arMultiple[3]);
	}
}
