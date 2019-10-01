package kr.green.test;

public class Test01 {
	public static void main(String[] args) {
		for (int i = 1; i <= 999; i++) {
			for (int j = 1; j <= 999; j++) {
				if (isPalindrome(i*j+"")) {
					System.out.println(i + " * " + j + " = " + (i*j));
				}
			}
		}
	}
	public static boolean isPalindrome(String str) {
		return str.equals(new StringBuilder(str).reverse().toString());
	}
}
