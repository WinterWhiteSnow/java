package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


// https://www.acmicpc.net/problem/1912
public class prefixSum {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int max = Integer.MIN_VALUE; // 갱신하기 위한 값
		int l = Integer.parseInt(br.readLine());
		int[] nList = new int[l];
		int[] prefixSum = new int[l];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < l; i ++) {
			nList[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.fill(prefixSum, -100000*1000-1); // 갱신하기 위해서 최저값으로 설정
		if (prefixSum[0]<nList[0]) { // 최저설정값보다 그래도 크다면 갱신해주기
			prefixSum[0]=nList[0];
			max = nList[0];
		}
		for (int i = 1; i <l; i ++) {
			if (prefixSum[i-1]+nList[i] >= 0) { // 일단 그대로 양수면 그대로 진행, 음수가 되는 순간 0으로 리셋
				prefixSum[i]=prefixSum[i-1]+nList[i];
				if (max < prefixSum[i]) {
					max = prefixSum[i];
				}
			} 
			if (prefixSum[i] < nList[i]) { // 어쨌건 지금 단일값이 더 크면 갱신
				prefixSum[i]=nList[i];
			} 

			if (prefixSum[i] > max) { // 단일로했을때 더 크다면
				max = prefixSum[i];
			}

		}
		System.out.println(max);
	}
}
