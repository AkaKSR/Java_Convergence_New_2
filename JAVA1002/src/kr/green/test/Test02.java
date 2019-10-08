package kr.green.test;

import java.util.Arrays;
import java.util.List;

/*
문제 2] 중앙값 구하기
리스트에 있는 숫자들의 중앙값을 구하는 프로그램을 만들어라.
[7, 9, 14] = 9 , [24, 31, 35, 49] = 33, [17, 37, 37, 47, 57] = 37
중앙값 : 자료를 작은 값에서부터 크기순으로 나열할 때 중앙에 위치한 값
① 자료의 개수가 홀수이면 가운데 위치한 값이 중앙값이다.
② 자료의 개수가 짝수이면 가운데 위치한 두 값의 평균이 중앙값이다.

 */
public class Test02 {
	public static void main(String[] args) {
		System.out.println(ex01(Arrays.asList(new Integer[] {7, 9, 14})));
		System.out.println(ex01(Arrays.asList(new Integer[] {24, 31, 35, 49})));
		System.out.println(ex01(Arrays.asList(new Integer[] {17, 37, 37, 47, 57})));
	}

	private static int ex01(List<Integer> list) {
		int result = 0;
		int size = list.size();
		if (size % 2 == 0) {
			result = (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
		} else {
			result = list.get(size / 2);
		}
		return result;
	}
}
