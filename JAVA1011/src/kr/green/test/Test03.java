package kr.green.test;

public class Test03 {
	public static void main(String[] args) {
		System.out.println(getLength(3));
		System.out.println(getLength(7));
		System.out.println(getLength(9901));
	}
	private static int getLength(int n) {
		int length = 1;
		String t = "1";
		while (Long.parseLong(t)%n!=0) {
			t += "1";
			length++;
		}
		System.out.println(t);
		return length;
	}
}
