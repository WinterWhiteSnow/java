package Party;
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
	long prefixSum;
	
	
	
	public location(int next, long prefixSum) {
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
	
}

// https://www.acmicpc.net/problem/1238
// 메모리초과로 통과X
public class Party {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> nList; // 경로를 갈 수 있는지 없는지 저장하는 장소
	static Map<String, Integer> map; // 그 경로를 갈 때마다 드는 비용을 저장할 장소
	static long max;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 학생수
		int r = Integer.parseInt(st.nextToken()); // 경로의 수
		int goal = Integer.parseInt(st.nextToken()); // 파티가 열리는곳
		// 각 지점에서 goal로 이동한 뒤 다시 원상태로 되돌아가는것
		nList = new ArrayList<>();
		max = 0;
		map = new HashMap<String, Integer>();
		for (int asdf = 0; asdf <= n+1; asdf ++) {
			nList.add(new ArrayList<>()); // 1->3 이면 nList.get(1)중에 순회해서 3으로 갈 수 있도록 nList.get(0~n)에 해당하는 배열을 생성함
		}
		for (int y = 0; y< r; y ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			String key = ""+start+","+end; // 112인경우 1->12인지, 11->2인지 구분이 안가니까 ,를 붙여서 구분가능케함
			// 도로가 단방향이기때문에 key는 한개면 ok
			if (! map.containsKey(key)) { // 어떤 지점에서 어떤 지점으로 가는게 여러개일 수 있으니 확인
				// 처음 들어온 경로
				map.put(key, cost);
				nList.get(start).add(end);
			} else {
				if (map.get(key) > cost) {//더 적은 값으로 갈 수 있다면 갱신해줌
					map.put(key, cost);
				}
			}
		}
		for (int start=1; start<= n; start++) {
			if (start == goal) {
				continue;
			}
			long temp = 0;
			temp+=start(start,goal,start); // 현재 지점에서 목적지로 감
			temp+=start(goal,start,start); // 목적지에서 현재지점으로 되돌아감
			if (temp > max) {
				max =temp;
			}
		}
		System.out.println(max);
		
	}
	
	public static long start(int start, int goal, int home) { 
		PriorityQueue<location> queue = new PriorityQueue<>();
		long total = Integer.MAX_VALUE;
		queue.add(new location(start, 0));
		while (! queue.isEmpty()) {
			location temp = queue.poll();
			for (int next : nList.get(temp.next)) {
				long distance = temp.prefixSum+map.get(""+temp.next+","+next); // 지금까지 걸린 시간과 다음 지점으로 가는데 걸리는 시간의 총함
				if (next == goal) { // 다음지점이 목적지이면
					if (total > distance) { // 목적지인데, 저번에 갔던 시간보다 더 빨라졌으면
						total = distance; // 갱신
					}
				} else { 
					if (total > distance) { // 이전기록들보단 그래도 느리진 않으면 일단 가자
						queue.add(new location(next,distance));
					}
				}
			}
		}
		return total;
	}
}
