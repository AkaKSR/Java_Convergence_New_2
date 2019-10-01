package kr.green.test;

public class TestEx02 {
	public static void main(String[] args) {
		System.out.println("main Method Start");
		long sum = 0, n = 0;
		for (int i = 1; i <= 40000; i++) {
			n = fibo(i);
			if (n%2==0) sum += n;
			System.out.println("i: " + i + ", n: " + n + ", sum: " + sum);
		}
		System.out.println("sum: " + sum + ", n: " + n);
		System.out.println(sum);
	}

	// 피보나치 구하는 메서드
	public static long fibo(int n) {
		int first = 0, second = 1, cnt = 0;
		while (cnt < n) {
			cnt++;
			second = first+second;
			first = second-first;
		}
		return second;
	}
}
