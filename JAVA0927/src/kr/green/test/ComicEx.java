package kr.green.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ComicEx {
	public static void main(String[] args) {
		// <img
		// src="http://comics.mrblue.com/MrBlueComicsData_02/hwangsung//jilpungsega1_hs/1/001.jpg"
		// onerror="this.style.display='none'" alt="">
		String addr = "http://comics.mrblue.com/MrBlueComicsData_02/hwangsung//jilpungsega1_hs/1/";
		for (int i = 1; i <= 100; i++) {
			try {
				String fileName = String.format("%03d.jpg", i);
				URL url = new URL(addr + fileName);
				InputStream is = url.openStream();
				FileOutputStream fos = new FileOutputStream(fileName);
				byte[] data = new byte[2048];
				int n;
				while ((n = is.read(data)) > 0) {
					fos.write(data, 0, n);
					fos.flush();
				}
				System.out.println("저장 완료.");
				is.close();
				fos.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
