package kr.green.test;

/*
문제 1] 문자열을 제거한뒤 숫자만반환
난이도:(쉬움) 현우는 축구를보다가 우리나라선수들의몸값을 알고싶었다
그래서 검색을해서 메모장에 적는데 키보드가 조그만하고 안좋은지라
자꾸 숫자가아닌 문자를 같이입력해버린다
ex: xxx : 1627000000 > xxx : 1w627r00o00p00 만 (특수문자제외)
현우는 왜인지모르지만 뜻대로안되는것에
너무화가나서 자신이 수량을 입력하면 
문자열만 딱빼서 숫자만 반환하는 코드를 만들고싶어한다
화가난 현우를위해 코드를 만들어보자!
 */
public class Test01 {
	public static void main(String[] args) {
		System.out.println(ex01("1w627r00o00p00"));
		System.out.println(ex01("1k2j3h5y67udsaf437812dfsjf"));
	}
	private static String ex01(String str) {
		StringBuilder sb = new StringBuilder();
		for(char ch : str.toCharArray()) {
			if(ch>='0' && ch<='9') sb.append(ch);
		}
		return sb.toString();
	}

}
