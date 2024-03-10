import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class Ground {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] score;
	static Map<String, Integer> map;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		score = new int[n+1];
		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i ++) {
			score[i]=Integer.parseInt(st.nextToken());
		}
		for (int y = 0;y < r; y ++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			String key1 = ""+front+","+back;
			String key2 = ""+back+","+front;
			if (! map.containsKey(key1)) {
				map.put(key1, cost);
				map.put(key2, cost);
			} else {
				if (map.get(key1) > cost) {
					map.put(key1, cost);
					map.put(key2, cost);
				}
			}
		}
		for (int start = 1; start <= n; start++) {
			
		}
	}
	
	public static void bfs(int start, int n) {
		boolean[] visit = new boolean[n+1];
		int total = score[start];
		visit[start]=true;
		PriorityQueue<Integer>
		
	}
}
