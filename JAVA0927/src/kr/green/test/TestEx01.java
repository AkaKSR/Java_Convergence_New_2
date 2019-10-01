package kr.green.test;

public class TestEx01 {
	public static void main(String[] args) {
		int sumResult = 0, squaredResult = 0;
		for (int i = 1; i <= 100 ; i++) {
			sumResult += i;
			squaredResult = squaredResult + (i*i);
		}
		sumResult *= sumResult;
		
		System.out.println();
		System.out.println("1~100까지 자연수의 \"합의 제곱\"과 \"제곱의 합\"의 차이");
		System.out.println(sumResult-squaredResult);
	}
}
