package kr.green.test;

import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("양의 정수를 입력하시오(0은 종료) > ");
			int input = sc.nextInt();
			if (input <= 0) System.out.println("양의 정수만 입력가능합니다.");
			if (input == 0) break;
			if (input > 0) {
				String convertInput = String.valueOf(input);
				System.out.println(convertInput.length() + "자리의 수입니다.");
			}
		}
		sc.close();
	}
}
