import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location> {
	int next;
	int prefixSum;
	
	
	
	public location(int next, int prefixSum) {
		super();
		this.next = next;
		this.prefixSum = prefixSum;
	}



	@Override
	public int compareTo(location o) { // 우선순위 큐를 사용하기 위한 비교함수
		// TODO Auto-generated method stub
		if (this.prefixSum < o.prefixSum) {
			return -1;
		} else if (this.prefixSum > o.prefixSum) {
			return 1;
		}
		return 0;
	}



	@Override
	public String toString() {
		return "location [next=" + next + ", prefixSum=" + prefixSum + "]";
	}
	
	
}

// https://www.acmicpc.net/problem/1238
// 목적지에서 각기 다른 집으로 가는걸 추가해줬음 goHome
// goHome 으로는 13% 메모리초과 -> 플로이드워셜 언급이 많아서 이거 적용해볼 예정
 public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] nList;
	static Map<String, Long> map; // 그 경로를 갈 때마다 드는 비용을 저장할 장소
	static int[] score; // 파티장에서 집으로 되돌아갈때의 최단경로값 저장
	static int max;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 학생수
		int r = Integer.parseInt(st.nextToken()); // 경로의 수
		int goal = Integer.parseInt(st.nextToken()); // 파티가 열리는곳
		// 각 지점에서 goal로 이동한 뒤 다시 원상태로 되돌아가는것
		nList = new int[n+1][n+1]; //모든 경로를 저장할 배열
		for (int y = 0; y<= n; y ++) { // 자기자신은 0으로 갱신, 아니면 Int의 최댓값으로 갱신
			for (int x = 0; x <= n; x ++) {
				if (y==x) {
					nList[y][x]=0;
				} else {
					nList[y][x]=10000001;					
				}
			}
		}
		max = 0;
		score = new int[n+1];
		map = new HashMap<String, Long>();
		for (int y = 0; y< r; y ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (nList[start][end] > cost) { // 기존값보다 훨씬 작으면 갱신해줌
				nList[start][end]=cost;				
			}
			
		}

		//플로이드워셜 시작
		for (int mid = 1; mid <= n; mid ++) {
			for (int start = 1; start<=n ; start++) {
				for (int end = 1; end <=n; end ++) {
					nList[start][end]=Math.min(nList[start][end], nList[start][mid]+nList[mid][end]);
				}
			}
		}
		
		for (int start = 1; start <= n; start ++) {
			int temp = nList[start][goal]+nList[goal][start];
			if (temp > max) {
				max=temp;
			}
		}
		System.out.println(max);

		
	}
	

	

	

	

}
