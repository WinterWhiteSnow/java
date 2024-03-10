import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int index;
	long value;
	
	public Node(long value, int index) {
		this.index = index;
		this.value = value;
	}
	
	

	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public long getValue() {
		return value;
	}



	public void setValue(long value) {
		this.value = value;
	}



	@Override
	public int compareTo(Node n) {
		// TODO Auto-generated method stub
		if (this.value < n.value) {
			return -1;
		} else if (this.value == n.value) {
			return 0;
		} else {
			return 1;			
		}
	}
	
	
}

class list {
	ArrayList<Node> nList;
	
	list() {
		this.nList = new ArrayList<>();
	}
}

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String, Integer> map;
	static ArrayList<list> nList;
	static long[] cost;
	static int n;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		cost = new long[20001];
		map = new HashMap<>();
		nList = new ArrayList<>();
		for (int y = 0; y <= n; y ++) {
			nList.add(new list());
			cost[y] = 200001; // 모든지점을 일단 최댓값으로
		}
		cost[start]=0;// 시작지점 초기화
		for (int y = 0; y< r ; y ++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken()); // 일방향성이라 front -> back만 기록함
			int back = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			String key = ""+front+","+back;
			if (map.containsKey(key)) {
				if (map.get(key) > price) {
					Node node = new Node(price, back);
					map.put(key, price); // front->back의 값 저장
					nList.get(front).nList.add(node); // front 기준으로 갈 수 있는 모든 지점을 기록					
				}
			} else {
				Node node = new Node(price, back);
				map.put(key, price); // front->back의 값 저장
				nList.get(front).nList.add(node); // front 기준으로 갈 수 있는 모든 지점을 기록			
			}
		}
		bfs(start);
		for (int i = 1; i <= n; i ++) {
			if (cost[i] == 200001) {
				System.out.println("INF");
			} else {
				System.out.println(cost[i]);
			}
		}
		
	}
	
	public static void bfs(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0,start));
		while (! queue.isEmpty()) {
			Node temp = queue.poll();
			for (Node next : nList.get(temp.index).nList) {
				long nextCost = cost[temp.index]+map.get(""+temp.index+","+next.index);
//				System.out.println("현재 지점 : "+temp.index+" "+temp.value);
//				System.out.println("후보지 : "+next.index+" value : "+nextCost);
				if (cost[next.index] > nextCost) {
					cost[next.index]=nextCost;
					queue.offer(new Node(nextCost, next.index));
				}
			}
//			System.out.println(temp.index);
//			System.out.println(queue.size());
		}
	}
		
	
}
