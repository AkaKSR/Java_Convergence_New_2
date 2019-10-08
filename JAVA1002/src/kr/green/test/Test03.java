package kr.green.test;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;

/*
문제 3] 삼각형 구별하기 : 3개의 각으로 삼각형의 예각, 직각, 둔각을 구별하는 프로그램을 만들어라.
[60, 60, 60] = 예각삼각형
[30, 60, 90] = 직각삼각형
[20, 40, 120] = 둔각삼각형
[0, 90, 90] = 삼각형이 아니다
[60, 70, 80] = 삼각형이 아니다
[40, 40, 50, 50] = 삼각형이 아니다
예각삼각형 : 3개의 각이 모두 예각인 삼각형
직각삼각형 : 1개의 각이 직각인 삼각형
둔각삼각형 : 1개의 각이 둔각인 삼각형
① 각이 3개가 아닐 경우 삼각형이 아니다.
② 3개의 각의 합이 180°가 아닐 경우 삼각형이 아니다.
 */
public class Test03 {
	public static void main(String[] args) {
		ex01(Arrays.asList(new Integer[] {60, 60, 60}));
		ex01(Arrays.asList(new Integer[] {30, 60, 90}));
		ex01(Arrays.asList(new Integer[] {20, 40, 120}));
		ex01(Arrays.asList(new Integer[] {0, 90, 90}));
		ex01(Arrays.asList(new Integer[] {90, 90, 0}));
		ex01(Arrays.asList(new Integer[] {60, 70, 80}));
		ex01(Arrays.asList(new Integer[] {40, 40, 50, 50}));
	}
	private static void ex01(List<Integer> list) {
		int check = 3; // 삼각형이 아니다.
		if(list.size()==3){ // 3개 
			if(list.get(0)+list.get(1)+list.get(2)==180) { // 180도
				check = 0; // 일단 예각 삼각형이라고 가정
				for(int i : list) {
					if(i<=0) { check=3; break;} // 각도에 0이 있으면 삼각형 아님 더이상 반복 필요 없다
					if(i==90) check=1; // 90도이면 직각
					if(i>90)  check=2; // 90도보다 크면 둔각
				}
			}
		}
		switch (check) {
		case 0:			System.out.println("예각 삼각형");			break;
		case 1:			System.out.println("직각 삼각형");			break;
		case 2:			System.out.println("둔각 삼각형");			break;
		default:		System.out.println("삼각형이 아니다");
		}
	}
}
