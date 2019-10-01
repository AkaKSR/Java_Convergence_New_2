package kr.green.test;

import java.util.Scanner;

public class TestEx03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("자연수 입력(0은 종료) > ");
			int num = sc.nextInt();
			if (num==0) break;
			System.out.println(num + " : " + dec2bin(num) + " : " + Integer.toBinaryString(num));
		}
		sc.close();
	}
	
	private static String dec2bin(int num) {
		StringBuilder sb = new StringBuilder();
		int mask = 0x8000;
		for (int i = 0; i < 16; i++) {
			sb.append(((num & mask)) == mask ? "1":"0");
			mask >>= 1;
		}
		return sb.toString();
	}
}
