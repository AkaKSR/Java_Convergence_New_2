package kr.green.test;

import java.util.Arrays;

public class Test01 {
	public static void main(String[] args) {
		System.out.println(isJollyJumpers(new int[] {4,1,4,2,3}) ? "Jolly" : "Not jolly");
		System.out.println(isJollyJumpers(new int[] {5,1,4,2,-1,6}) ? "Jolly" : "Not jolly");
	}
	
	private static boolean isJollyJumpers(int[] ar) {
		boolean isJolly = true;  // 일단 유쾌한 점퍼(jolly jumper)라고 가정
		int ar2[] = new int[ar[0]-1]; // 첫번째 숫자는 개수이므로 데이터 개수보다 1적게 배열
		for(int i=1;i<ar.length-1;i++) ar2[i-1] = Math.abs(ar[i]-ar[i+1]); // 차의 절대값 저장
		Arrays.sort(ar2); // 정렬
		System.out.println(Arrays.toString(ar2)); // 확인차 인쇄 나중에 주석
		for(int i=0;i<ar2.length;i++) {
			if(ar2[i] != i+1) { // 숫자가 차례대로가 아니면 
				isJolly = false; // Not jolly
				break; // 더이상 판단 무의미
			}
		}
		return isJolly; // 리턴
	}
}
