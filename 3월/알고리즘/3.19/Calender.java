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
			// 어떤 수가 있으면 어떤수 n넘는만큼을 빼버리면 = a / m넘는만큼을 빼버리면 = b
			// 즉 a이나 b 둘중에 하나 기준점에서 n과 m의 최소공배수만큼만 순회해줘서 정답을 찾아주면 됨!
			// 만약 최적화?를 한다면 배수가 큰쪽으로 순회하면 더 빨리 도착점에 다다를수있겠지만
			// 일단은 무조건 n+a를 기준으로 해볼예정, 시간초과나면 바꿔보자
			
			int limit = (n*m/(gcd(n,m)))+a;
			System.out.println("??? + "+limit);
			int num = a; // a로 고정이라서 num%n은 볼필요도 없음, 어차피 a로계속 나올거라 
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
