package spot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location>{
	int y;
	int x;
	int score;

	
	public location(int y, int x, int score) {
		super();
		this.y = y;
		this.x = x;
		this.score = score;
	}


	@Override
	public int compareTo(location o) { // 우선순위 큐를 사용하기 위한 판별메서드
		// TODO Auto-generated method stub
		if (this.score < o.score) {
			return -1;
		} else if (this.score > o.score) {
			return 1;
		}
		return 0;
	}
}

//https://www.acmicpc.net/problem/1261
public class Spot {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][] nList; // 벽이 있는지 없는지 확인할 배열
	static int[][] visit; // 그 지점으로 가려면 벽을 몇개 부숴야하는지 저장하는 배열
	
	//지점을 확인할 방향
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int r;
	static int c;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		nList = new boolean[r][c];
		visit = new int[r][c];
		for (int y = 0; y < r; y ++) {
			String temp = br.readLine();
//			System.out.println(temp+" ????");
			for (int x = 0; x < c; x ++) {
				char ch = temp.charAt(x);
				if (ch == '1') {
					nList[y][x] = true; // 벽이 있다!
				}
			}
		}
		for (int y = 0; y < r; y ++) {
			Arrays.fill(visit[y], r+c-1); // 모두 벽이있다고 가정하면 r+c-1만큼 부숴주면 됨
		}
		
		//시작점이 0,0이라서, 0,0만 초기화해줌
		if (nList[0][0]) { // 벽이 있음
			visit[0][0]=1; // 벽을 부숴야함
		} else {
			visit[0][0]=0;
		}
		bfs();
		System.out.println(visit[r-1][c-1]);
		
	}
	
	public static void bfs() {
		PriorityQueue<location> queue = new PriorityQueue<>();
		queue.add(new location(0,0,visit[0][0]));
		while (! queue.isEmpty()) {
			location temp = queue.poll();
//			System.out.println("현재 지점 : "+temp.y+" "+temp.x+" score: "+temp.score);
			for (int i = 0; i < 4; i ++) {
				int ny = temp.y+dr[i];
				int nx = temp.x+dc[i];
				if (rangeCheck(ny,nx)) { // 배열 내 이동인지 확인
					int plus = 0;
					if (nList[ny][nx]) { // 벽이면
						plus++;
					}
					if (visit[ny][nx] > temp.score+plus) {
						visit[ny][nx]=temp.score+plus;
						queue.add(new location(ny,nx,visit[ny][nx]));
					}
				}
			}
		}
	}
	
	public static boolean rangeCheck(int y, int x) {
		return 0<= y && y < r && 0 <=x && x < c;
	}
}
