package kr.green.test;
// 재귀호출이 독이 되는 경우
public class RecursiveCall2 {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) System.out.print(fibo1(i) + " ");
		System.out.println();
		for (int i = 1; i <= 10; i++) System.out.print(fibo2(i) + " ");
		System.out.println();
	}
	// n번째 피보나치 수열을 구해주는 일반 메소드
	// 1 1 2 3 5 8 ......
	private static int fibo1(int n) {
		if (n==1 || n==2) return 1;
		int first = 0, second = 1;
		while (n>1) {
			second = first + second;
			first = second - first;
			n--;
		}
		return second;
	}
	
	// n번째 피보나치 수열을 구해주는 재귀호출 메서드
	private static int fibo2(int n) {
		return n==1 || n==2 ? 1 : fibo2(n-1) + fibo2(n-2);
	}
}
