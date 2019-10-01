package kr.green.test;

import java.util.Scanner;

public class TestEx02 {
//	public static void main(String[] args) {
//		// 1. 암호로 만들 문장 + n의 수치 입력
//		Scanner sc = new Scanner(System.in);
//		System.out.print("암호로 만들 문장을 입력하시오 > ");
//		String str = sc.nextLine();
//		System.out.println("n의 키값을 입력하시오 > ");
//		int n = sc.nextInt();
//		
//		char convertStr;
//		for (int i = 0; i < str.length(); i++) {
//			convertStr = str.charAt(i);
//		}
//		
//		sc.close();
//	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("암호 입력(0은 종료) > ");
			String password = sc.nextLine();
			if (password.equals("0")) break;
			System.out.print("암호키 입력(1자리 정수) > ");
			int key = sc.nextInt();
			System.out.println(makePassword(password, key));
			sc.nextLine();
		}
		sc.close();
	}
	
	private static String makePassword(String password, int key) {
		StringBuilder sb = new StringBuilder();
		
		for (char c : password.toCharArray()) {
			sb.append((char)(c + key));
		}
		
		return sb.toString();
	}
}
