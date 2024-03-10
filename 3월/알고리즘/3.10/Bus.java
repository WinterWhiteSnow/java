import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class city implements Comparable<city>{
	int next;
	long distance;
	
	
	public city(int next, long distance) {
		super();
		this.next = next;
		this.distance = distance;
	}

	@Override
	public int compareTo(city o) {
		// TODO Auto-generated method stub
		if (this.distance < o.distance) {
			return -1;
		} else if (this.distance == o.distance) {
			return 0;
		}
		return 1;
	}
}

class listList {
	ArrayList<city> nList;
	
	listList() {
		this.nList = new ArrayList<>();
	}
}

public class Bus {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static long[] cost;
	static Map<String, Integer> map;
	static ArrayList<listList> nList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		int r = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		nList = new ArrayList<>();
		cost = new long[n+1];
		for (int a = 0; a <= n; a ++) {
			nList.add(new listList());
			cost[a]=Integer.MAX_VALUE;
		}
		for (int y = 0; y < r; y ++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			String key = ""+front+","+back;
			if (map.containsKey(key)) {
				int value = map.get(key);
				if (value > price) {
					map.put(key, price);
				}
			} else {
				map.put(key, price);
				nList.get(front).nList.add(new city(back, price));
			}
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		cost[start]=0;
		queue(start);
//		System.out.println(Arrays.toString(cost));
		System.out.println(cost[end]);
	}
	
	public static void queue(int start) {
		PriorityQueue<city> pq = new PriorityQueue<>();
		pq.add(new city(start,0));
		while (! pq.isEmpty()) {
			city temp = pq.poll();
			for (city next : nList.get(temp.next).nList) {
				long nextCost = cost[temp.next]+map.get(""+temp.next+","+next.next);
				if (cost[next.next] > nextCost) {
					cost[next.next] = nextCost;
					pq.add(new city(next.next, nextCost));
				}
			}
		}
	}
}
