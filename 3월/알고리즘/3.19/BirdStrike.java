package birdStrike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/20529
//��ѱ��� �̷��� �𸣰�����
//nĭ�� �ְ�, ������ n+1�ִ´ٸ�
//� ĭ�� 2���̻� �� �����̴�
// ��� �������ε� ����� �� ����.
// ���� �������� ����, ��ĭ���� ���� �ִ´ٰ��ص�
// �츮�� ���ϴ� ���� ����� ã�� �� �ִ�
public class BirdStrike {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String, Integer> map; // �ش� Ű�� 3�� �̻� �����ϴ��� Ȯ���ϴ� ��
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int repeat = Integer.parseInt(br.readLine());
		for (int r = 0; r < repeat; r ++) {
			int leng = Integer.parseInt(br.readLine());
			answer = 12;
			st = new StringTokenizer(br.readLine()); // �ϴ� �Է� �޾Ƶ�
			String[] nList = new String[leng];
			if (leng > 32) { // ���� ���� �����ٰ� �ص� �ᱹ�� 33��°���� �ݵ�� � MBTI�� 3���̻� �����ϰԵ�
				answer=0;
			} else { // 33�� �̸���, �̷��� ���� ���� ����������..
				for (int i = 0; i < leng; i ++) {
					nList[i]=st.nextToken();
				}
				for (int i = 0; i < leng; i ++) {
					for (int ii = i+1; ii < leng;  ii++) {
						for (int iii = ii+1; iii < leng; iii++) {
							String first = nList[i];
							String second = nList[ii];
							String third = nList[iii];
							int count = 0;
							count+=check(first,second)+check(second,third)+check(first,third);
							if (count < answer) {
								answer = count;
							}
						}
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	public static int check(String first, String second) {
		int total = 0;
		for (int index = 0; index <4; index ++) {
			if (first.charAt(index) != second.charAt(index)) {
				total++;
			}
		}
		return total;
	}
}
