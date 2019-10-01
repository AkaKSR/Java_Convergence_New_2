package kr.green.test;

import java.util.Scanner;

public class TestEx01_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("자연수 입력(0은 종료) > ");
			int n = sc.nextInt();
			if (n==0) break;
			for (int i = 1; i <= n; i++) { // n까지
				int sum = 0; // 약수 합
				for (int j = 1; j < i; j++) { // n-1까지 약수
					if (i%j==0) { // 약수면 더하기
						sum+=j;
					}
				}
				if (i==sum) { // 같으면 완전수
					System.out.println(i);
				}
			}
		}
		sc.close();
	}
}
