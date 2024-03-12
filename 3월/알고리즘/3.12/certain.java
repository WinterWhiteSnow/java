package certain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location>{
	int next;
	int prefixSum;
	boolean first;
	boolean second;
	
	
	
	public location(int next, int prefixSum, boolean first, boolean second) {
		super();
		this.next = next;
		this.prefixSum = prefixSum;
		this.first = first;
		this.second = second;
	}



	@Override
	public int compareTo(location o) {
		// TODO Auto-generated method stub
		if (this.prefixSum < o.prefixSum) {
			return -1;
		} else if (this.prefixSum > o.prefixSum) {
			return 1;
		}
		return 0;
	}
	
	
}

// https://www.acmicpc.net/problem/1504
// 플루이드 워셜 == 이미 최적화된상태라서 우선순위큐로 막 순회안해도 ok
// a랑 b 중에 뭘 먼저 방문할지는 그리 중요한것도 아니고 최단거리이기만 하면 돼서 둘다 구해야함
public class certain {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] nList; // 모든 경로의 값을 저장할 배열
	static int a,b; // 반드시 거쳐야하는 경로, 딱 두개라고 제시했음
	
	public static void main(String[] args) throws IOException {
		st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		nList = new int[n+1][n+1];
		for (int y = 1; y <= n ; y ++) { // 경로갱신
			for (int x = 1; x <= n; x ++) {
				if (y == x) {
					nList[y][x]=0;
					continue;
				}
				nList[y][x]=200000001; // 가장 큰값으로 갱신
			}
		}
		for (int repeat = 0; repeat < r; repeat ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			// 양방향 경로라서 두번 추가해줘야함
			if (nList[start][end] > cost) { // 혹시몰라 같은 경로를 주면서 값이 더 적은 경우가 있을까봐 조건문으로 확인함
				nList[start][end]=cost;
				nList[end][start]=cost;
			}
		}
		a = 0; // a랑 b 초기화
		b = 0; // a랑 b는 같지않으며, b는 1이아니고 a는 N이 아님, 대소관계도 확실치X
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		//플로이드워셜 시작
		for (int mid = 1; mid <=n; mid ++) {
			for (int start =1; start <=n; start ++) {
				for (int end =1; end <=n; end ++) {
					nList[start][end] = Math.min(nList[start][end], nList[start][mid]+nList[mid][end]);						
				}
			}
		}
		int answer = Math.min(nList[1][a]+nList[a][b]+nList[b][n],nList[1][b]+nList[b][a]+nList[a][n]); // 플루이드워셜하면서 최단거리로 전부 갱신된 상태라서 굳이 우선순위큐로 돌리지 않아도 ok
		if (answer >= 200000001) { // 경로중에 최댓값이 들어가있음 = 경로가 없음
			answer = -1; // 만족하는 경로가 없다고 판단, -1출력을 위해 갱신해줌
		}
		System.out.println(answer);
	}
	
	
}
