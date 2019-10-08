package kr.green.test;

public class Test02 {
	public static void main(String[] args) {
		System.out.println(carryOperation(123,456) + " carry operation");
		System.out.println(carryOperation(555,555) + " carry operation");
		System.out.println(carryOperation(123,594) + " carry operation");
		System.out.println(carryOperation(55,8971) + " carry operation");
		System.out.println(carryOperation(555,445) + " carry operation");
		System.out.println();
	}
	private static int carryOperation(int n, int m) {
		int count = 0, x, y;
		while(n>0 || m>0) {
			x = n%10; y = m%10;
			if(x+y >= 10 ) count++;
			n/=10;
			m/=10;
			if(n%10+m%10 == 9 ) count++;
		}
		return count;
	}
}
