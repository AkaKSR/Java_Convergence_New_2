package kr.green.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test01 {
	static int count = 1;
	
	public static void main(String[] args) {
		int n = 10, m = 3;
		
		System.out.println(people(n, m));
	}
	
	private static List<Integer> people(int i, int j) {
		List<Integer> list = null;
		
		list = new ArrayList<Integer>();
		for (int k = 1; k <= i; k++) {
			list.add(k);
		}
		while (list.size()>1) {
			Iterator<Integer> it = list.iterator();
			while (it.hasNext()) {
				int delNum = it.next();
				if (count%j==0) {
					it.remove();
					System.out.print(delNum + " ");
				}
				count++;
			}
			System.out.println();
			System.out.println(list);
		}
		return list;
	}
	
	
}
