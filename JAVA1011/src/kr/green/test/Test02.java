package kr.green.test;

public class Test02 {
	public static void main(String[] args) {
		System.out.println(everyOtherDigit("a1b2cde3~g45hi6"));
	}
	
	private static String everyOtherDigit(String str) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			if (i%2!=0) {
				char c = str.charAt(i);
				if (c>='0' && c<='9') sb.append("*");
				else sb.append(c);
				} else {
					sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
}
