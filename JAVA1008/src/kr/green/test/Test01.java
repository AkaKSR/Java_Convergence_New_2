package kr.green.test;

public class Test01 {
	public static void main(String[] args) {
		boolean[] selfNumber = new boolean[10000];
		for (int i = 1; i <= 5000; i++) {
			selfNumber[generator(i)] = true;
		}
		int sum = 0;
		for (int i = 1; i <= 5000; i++) {
			if (!selfNumber[i]) {
				System.out.println(i);
				sum += i;
			}
		}
		System.out.println("정답 : " + sum);
	}
	
	private static int generator(int n) {
		int sum = n;
		while(n>0) {
			sum += n%10;
			n /= 10;
		}
		return sum;
	}
}
