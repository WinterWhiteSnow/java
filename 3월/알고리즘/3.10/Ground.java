import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location> {
	int next; // 다음좌표
	int prefixSum;//지금까지 이동한 거리
	
	public location(int next, int prefixSum) { 
		super();
		this.next = next;
		this.prefixSum = prefixSum;
	}


	@Override
	public int compareTo(location o) { // 지금까지 이동한 거리순으로 정렬하기위함
		// TODO Auto-generated method stub
		if (this.prefixSum < o.prefixSum) {
			return -1;
		} else if (this.prefixSum > o.prefixSum) {
			return 1;
		}
		return 0;
	}
}

// https://www.acmicpc.net/problem/14938
public class Ground {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] score;
	static Map<String, Integer> map;
	static ArrayList<ArrayList<Integer>> nList;
	static int count;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		score = new int[n+1];
		count = 0;
		map = new HashMap<>(); // 경로 이동시 필요한 거리를 저장할 공간
		st = new StringTokenizer(br.readLine());
		nList = new ArrayList<>(); // 3 -> 1 이동이라고 했을때, nList.get(3).
		for (int i = 1; i <= n; i ++) {
			score[i]=Integer.parseInt(st.nextToken());
			nList.add(new ArrayList<>());
		}
		nList.add(new ArrayList<>());
		for (int y = 0;y < r; y ++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			String key1 = ""+front+","+back; // 양방향이기때문에 앞뒤로 저장해줌
			String key2 = ""+back+","+front; // 굳이 ,를 넣는 이유는 문제의 범위를 생각안하고 아주 최악으로 생각해봤을때
			// 1221일경우 122->1일지, 12->21일지 구분할수가없기때문에 굳이 사이사이에 ,를 삽입
			if (! map.containsKey(key1)) {
				map.put(key1, cost);
				map.put(key2, cost);
			} else {
				if (map.get(key1) > cost) { // 입력이 중복으로 들어올 수 있음
					map.put(key1, cost); 
					map.put(key2, cost);
				}
			}
			nList.get(front).add(back);
			nList.get(back).add(front);
		}
		for (int start = 1; start <= n; start++) { // 시작점을 모두 확인해봄
			bfs(start,n,k);
		}
		System.out.println(count);
	}
	
	public static void bfs(int start, int n, int k) {
		boolean[] visit = new boolean[n+1]; // 해당 start기준으로 방문할 수 있는 곳들 전부 저장
		int total = score[start];
		visit[start]=true;
		PriorityQueue<location> queue = new PriorityQueue<>(); // 지금까지 간 거리를 기준으로 우선순위정렬
		queue.add(new location(start, 0));
		while (! queue.isEmpty()) {
			location temp = queue.poll();
			for (int next : nList.get(temp.next)) {
				if (! visit[next]) { // 아직 방문한 곳이 아니라면
					String key = ""+temp.next+","+next;
					int distance = temp.prefixSum+map.get(key); //가려고 하는 곳의 거리를 확인, 누적해서 확인
					// 왜냐하면 처음 이 함수가 실행될때 파라미터로 받은 거리에서 시작해서 그 거리만큼만 가려고하니까
					if (distance <= k) { // 갈 수 있는 거리임
						visit[next]=true;
						total+=score[next];
						queue.add(new location(next, distance));
					}
				}	
			}
		}
		if (total > count) {
			count = total;
		}
		
	}
}
