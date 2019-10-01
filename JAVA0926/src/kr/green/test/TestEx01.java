package kr.green.test;

import java.util.Scanner;

public class TestEx01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("n의 수를 입력하시오 >> ");
			int n = sc.nextInt();
			
			if (n == 0) break;
			
			for (int i = 1; i <= n; i++) {
				if (n%i==0) {
					System.out.println(i);
				}
			}
		}
	}
	
	private int[] mathArr() {
		int[] mathArr = null;
		
		
		
		return mathArr;
	}
}
