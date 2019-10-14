package kr.green.test;

public class RecursiveCall {
	public static void main(String[] args) {
		power1(5, 5);
		power2(5, 5);
		show1(5);
		System.out.println();
		show2(10);
	}
	
	// x의 y승을 구하는 일반 메서드
	private static int power1(int x, int y) {
		int result = 1;
		while (y>0) {
			result *= x;
			y--;
		}
		return result;
	}
	// x의 y승을 구하는 재귀호출 매서드
	private static int power2(int x, int y) {
		return y==1 ? x : x * power2(x, y-1); 
	}
	
	// n ... 5 4 3 2 1 2 3 4 5 ... n을 출력하는 일반 메서드
	private static void show1(int n) {
		for (int i = n; i >= 1; i--) System.out.print(i + " ");
		for (int i = 2; i <= n; i++) System.out.print(i + " ");
	}
	
	// n ... 5 4 3 2 1 2 3 4 5 ... n을 출력하는 재귀호출 메서드
	private static void show2(int n) {
		System.out.print(n + " ");
		if (n==1) return;
		show2(n-1);
		System.out.print(n + " ");
	}
	
}
