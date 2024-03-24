package Where;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location>{
	int next;
	int score;
	
	
	public location(int next, int score) {
		super();
		this.next = next;
		this.score = score;
	}


	@Override
	public int compareTo(location o) { // 우선순위 큐를 사용할 것이기 때문에 기준을 설정함
		// TODO Auto-generated method stub
		if (this.score < o.score) {
			return -1;
		} else if (this.score > o.score) {
			return 1;
		}
		return 0;
	}
	
	
	
}

//https://www.acmicpc.net/problem/13549
public class where {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] visit; // 특정 위치에 도달할 때 움직인 횟수를 저장할 곳
	static PriorityQueue<location> queue;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 시작점, visit배열에0부터 시작
		int end = Integer.parseInt(st.nextToken()); // 도착점, visit에서 갱신 될 index값의 위치
		visit = new int[Math.max(start+1, end+1)*2]; // 둘중에 긴거..?
		Arrays.fill(visit,Math.abs(start-end));
		visit[start]=0;
//		System.out.println("시작!");
		bfs(start);
		System.out.println(visit[end]);
	}
	
	public static void bfs(int start) {
//		System.out.println("queue!!!!");
		queue  = new PriorityQueue<>();
		queue.add(new location(start, 0));
		while (! queue.isEmpty()) {
			location temp = queue.poll();
//			System.out.println("현재 지점 : "+temp.next+" "+"현재 점수 : "+temp.score);
			int next1 = temp.next+1;
			int next2 = temp.next-1;
			int next3 = temp.next*2;
			rangeCheck(next1, temp.score+1);
			rangeCheck(next2, temp.score+1);
			if (next3 != 0) {
				rangeCheck(next3, temp.score);				
			}
		}
	}
	
	public static void rangeCheck(int num, int score) {
		if (0<= num && num < visit.length) {
			if (visit[num] > score) {
				visit[num] = score;
				queue.add(new location(num,score));
			}
		}
	}
}
