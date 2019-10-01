package kr.green.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test02 {
	public static void main(String[] args) throws FileNotFoundException {
		int count = 0;
		Scanner sc = new Scanner(new File("src/kr/green/test/Test01.java"));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			for (char ch : line.toCharArray()) {
				if (ch != ' ' && ch != '\t' && ch != '\n') {
					count++;
				}
			}
		}
		System.out.println(count);
		sc.close();
	}
}
