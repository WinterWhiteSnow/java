package calender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/6064
public class Calender {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int test = Integer.parseInt(br.readLine());
		for (int t = 0; t < test ; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			// � ���� ������ ��� n�Ѵ¸�ŭ�� �������� = a / m�Ѵ¸�ŭ�� �������� = b
			// �� a�̳� b ���߿� �ϳ� ���������� n�� m�� �ּҰ������ŭ�� ��ȸ���༭ ������ ã���ָ� ��!
			// ���� ����ȭ?�� �Ѵٸ� ����� ū������ ��ȸ�ϸ� �� ���� �������� �ٴٸ����ְ�����
			// �ϴ��� ������ n+a�� �������� �غ�����, �ð��ʰ����� �ٲ㺸��
			
			int limit = (n*m/(gcd(n,m)))+a;
			System.out.println("??? + "+limit);
			int num = a; // a�� �����̶� num%n�� ���ʿ䵵 ����, ������ a�ΰ�� ���ðŶ� 
			while (num <= limit) {
				int second = num%m;
				if (second == 0) {
					second = m;
				}
				if (second == b) {
					break;
				}
				num+=n;
			}
			if (num <= limit) {
				System.out.println(num);
			} else {
				System.out.println(-1);
			}
		}

	}
	
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a%b);
	}
}
