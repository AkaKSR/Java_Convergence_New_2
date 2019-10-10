package kr.green.test;
/*
	문제 1] 다음 입사문제 중에서
	1차원의 점들이 주어졌을 때, 그 중 가장 거리가 짧은 것의 쌍을 출력하는 함수를 작성하시오. (단 점들의 배열은 모두 정렬되어있다고 가정한다.)
	예를들어 S={1, 3, 4, 8, 13, 17, 20} 이 주어졌다면, 결과값은 (3, 4)가 될 것이다.
 */
public class Test01 {
	public static void main(String[] args) {
		int[] ar1 = { 1, 3, 4, 8, 13, 17, 20 };
		int index = shortFind(ar1);
		System.out.println("(" + ar1[index - 1] + "," + ar1[index] + ")");
		
		int[] ar2 = { 1, 2, 5, 8, 11, 17, 22, 37, 38 };
		index = shortFind(ar2);
		System.out.println("(" + ar2[index - 1] + "," + ar2[index] + ")");
	}
	
	private static int shortFind(int[] ar) {
		int shortest = 9999;
		int index = 0;
		for (int i = 0; i < ar.length - 1; i++) {
			if (ar[i + 1] - ar[i] < shortest) {
				shortest = ar[i + 1] - ar[i];
				index = i + 1;
			}
		}
		return index;
	}
}
