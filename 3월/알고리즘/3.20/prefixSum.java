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
		int max = Integer.MIN_VALUE; // �����ϱ� ���� ��
		int l = Integer.parseInt(br.readLine());
		int[] nList = new int[l];
		int[] prefixSum = new int[l];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < l; i ++) {
			nList[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.fill(prefixSum, -100000*1000-1); // �����ϱ� ���ؼ� ���������� ����
		if (prefixSum[0]<nList[0]) { // �������������� �׷��� ũ�ٸ� �������ֱ�
			prefixSum[0]=nList[0];
			max = nList[0];
		}
		for (int i = 1; i <l; i ++) {
			if (prefixSum[i-1]+nList[i] >= 0) { // �ϴ� �״�� ����� �״�� ����, ������ �Ǵ� ���� 0���� ����
				prefixSum[i]=prefixSum[i-1]+nList[i];
				if (max < prefixSum[i]) {
					max = prefixSum[i];
				}
			} 
			if (prefixSum[i] < nList[i]) { // ��·�� ���� ���ϰ��� �� ũ�� ����
				prefixSum[i]=nList[i];
			} 

			if (prefixSum[i] > max) { // ���Ϸ������� �� ũ�ٸ�
				max = prefixSum[i];
			}

		}
		System.out.println(max);
	}
}
