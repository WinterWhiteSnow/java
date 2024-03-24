package walk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//https://www.acmicpc.net/problem/11403

public class Walk {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] nList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int r = Integer.parseInt(br.readLine());
		nList = new int[r][r];
		for (int y = 0; y < r; y ++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < r; x ++) {
				nList[y][x]=Integer.parseInt(st.nextToken());
			} 
		}
		
		//플루이드 워셜?
		for (int mid = 0; mid < r; mid ++) {
			for (int start = 0; start < r; start ++) {
				for (int end = 0; end < r; end ++) {
					if (nList[start][end] == 1 || (nList[start][mid] == 1 && nList[mid][end] == 1)) {
						nList[start][end]=1;
					}
				}
			}
		}
		for (int y=0; y < r; y ++) {
			for (int x = 0; x < r; x++) {
				System.out.print(nList[y][x]+" ");
			}
			System.out.println();
		}
	}
}
