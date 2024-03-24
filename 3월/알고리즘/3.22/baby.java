package baby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location>{ // 우선순위 큐를 사용하기 위한 비교군
	int y;
	int x;
	int distance; // 나중에 물고기들 순회할때 거리 얼마나 되는지 확인하기 위한 변수
	
	location() {}

	public location(int y, int x, int distance) {
		super();
		this.y = y;
		this.x = x;
		this.distance = distance;
	}

	@Override
	public int compareTo(location o) {
		// TODO Auto-generated method stub
		if (this.distance < o.distance) {
			return -1;
		} else if (this.distance > o.distance) {
			return 1;
		}
		return 0;
	}
	
}

// https://www.acmicpc.net/problem/16236
public class baby {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = {-1,0,0,1}; //북동서남, 우선순위가 가장 위에있을수록, 높이가 같다면 가장 왼쪽에 있을수록이니
	static int[] dc = {0,1,-1,0}; //일단 위로가주고 없으면, 왼쪽 오른쪽 탐색후 마지막에 남쪽으로 내려옴
	static ArrayList<ArrayList<location>> nList; // 크기별로 좌표를 저장해둘 것
	static int[][] list; // 배열 자체 어떤 상태인지 알기 위한 배열.. nList만으로는 안됨..
	static int level = 2; // 아기상어 초기레벨
	static int eating = 0; // 현재 먹은 수, 먹은수와 레벨이 동일하면 레벨업!
	static location shark; // 아기상어의 위치
	static int r; // 배열범위
	static int time = 0; // 아기상어가 혼자서 먹는 시간
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		r = Integer.parseInt(br.readLine());
		
		nList = new ArrayList<>();
		list = new int[r][r];
		for (int y = 0; y <= 7; y ++) { // 물고기 크기는 1~6이니 7줄있으면 ok
			nList.add(new ArrayList<>());
		}
		
		for (int y = 0; y< r; y ++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < r; x ++) {
				int level = Integer.parseInt(st.nextToken());
				if (level == 9) { // 아기 상어임
					shark = new location(y,x,0);
					continue;
				}
				nList.get(level).add(new location(y,x,0)); // 크기에 맞게 배열에 추가
				list[y][x]=level;
			}
		}
//		System.out.println("아기 상어의 위치 "+shark.y+" "+shark.x);
		sort();
		least();

		System.out.println(time);
	}
	
	public static location least() {
		//레벨이 되는 것들 전부 싹다 돌려봄
		location eat = new location(-1,-1,20*20+1); // 먹을 물고기 위치 저장할 변수
		int fishlevel = 0;
		int fishindex = 0;
//		System.out.println("시작!");
		//레벨 기준으로 할때 20*20에 배열에 먹이를 전부 먹었다고 하면 막 레벨이 6넘고해서 Math.min해야함
		for (int i = 1; i < Math.min(level,7); i ++) { // 현재 아기상어의 레벨미만만 먹을 수 있음
			for (int index = 0; index < nList.get(i).size(); index++) { // 먹을 수 있는 것들 다 순회할 것
//				System.out.println("먹고자 하는 물고기의 레벨 "+i+" "+index);
				location candidate = move(nList.get(i).get(index),eat.distance); { // 지나갈 수 있다면 x와 y의 좌표가 0이상
//				System.out.println("그래서 , 위치와 총 거리는? "+candidate.y+" "+candidate.x+" "+candidate.distance);
				if (candidate.x != -1) { // 배열 내 범위 == 갈 수 있는 곳
					if (eat.distance > candidate.distance) { // 그런데 이전 거리보다 훨씬 짧으면 갱신
						eat.y = candidate.y;
						eat.x = candidate.x;
						eat.distance = candidate.distance;
						fishlevel = i;
						fishindex = index;
//						System.out.println("와 ! 거리때문에 갱신!!!"+eat.y+" "+eat.x);
						} else if (candidate.distance == eat.distance) { // 거리자체는 동일한데, y좌표상으로 더 위쪽에 있다면
							if (candidate.y < eat.y) {
								eat.y = candidate.y;
								eat.x = candidate.x;
								eat.distance = candidate.distance;
								fishlevel = i;
								fishindex = index;
//								System.out.println("와 ! 높이때문에 갱신!!!"+eat.y+" "+eat.x);
							} else if (candidate.y == eat.y && candidate.x < eat.x) { // y좌표상으로도 동일하지만 더 왼쪽에 있다면
								eat.y = candidate.y;
								eat.x = candidate.x;
								eat.distance = candidate.distance;
								fishlevel = i;
								fishindex = index;
//								System.out.println("와 ! 왼쪽이라 갱신!!!"+eat.y+" "+eat.x);
						}
					 }
					}
				}
			}
		}
		if (eat.x != -1) { // 배열 내 좌표값 == 먹을 수 잇음
//			System.out.println("먹는다!!!");
//			System.out.println(eat.y+" "+eat.x+" 거리는 ? : "+eat.distance);
			nList.get(fishlevel).remove(fishindex); // 먹엇으니 없음
			eating++;
			if (eating == level) { // 레벨업!
				level++;
				eating = 0;
			}
			time+=eat.distance;
//			System.out.println("지금까지 경과한 시간 : "+time);
			// 먹었으니 먹이가 있는 곳이 바로 아기 상어의 위치가 됨
			shark.y = eat.y;
			shark.x = eat.x;
			least(); // 먹었기때문에 재귀돌림
		}
		return eat;
	}
	
	public static location move(location goal, int max) { // 지정 위치로 갈 수 있는지 확인하는 메서드, 중간에 자기보다 큰 레벨이 있으면 못지나감
//		System.out.println("목표 위치 : "+goal.y+" "+goal.x);
		PriorityQueue<location> queue = new PriorityQueue<>();
		boolean[][] visit = new boolean[r][r];
//		visit[shark.y][shark.x]=true; // 현재 아기 상어의 위치
		queue.add(new location(shark.y,shark.x,0)); // 현재 아기 상어 위치 기준으로 목적지까지 가기, 미리 만들어서 쓰기엔, 그러면 한개만 만들어서 한개를 계속참조 = 제대로 값이 안나옴
		while (! queue.isEmpty()) {
			location temp = queue.poll();
			if (temp.distance+1 > max) {
				break;
			}
//			System.out.println("현재 위치 : "+temp.y+" "+temp.x+" "+temp.distance);
			for (int i = 0; i <4; i ++) {
				int ny = dr[i]+temp.y;
				int nx = dc[i]+temp.x;
//				System.out.println("방문 예정 : "+ny+" "+nx);
				if (rangeCheck(ny,nx)) { // 배열 내 범위임, 일단 유효한 길
					if (visit[ny][nx]) { // 이미 방문한 적 있던 곳임
						continue;
					}
					if (list[ny][nx] <= level) { // 지나갈 수 있음!
						// 근데 만약 목표지점이라면?
						if (ny == goal.y && nx == goal.x) { // 바로 리턴해주는 이유가 bfs돌리면서 우선순위 큐로 인해 최저값위주로 돌리기때문에
							// 반드시 최저값인 상태임을 보장함!
							return new location(ny,nx,temp.distance+1);							
						}
						queue.add(new location(ny,nx,temp.distance+1));
						visit[ny][nx]=true;
					}
				}
				
			}
		}
		return new location(-1,-1,-1); // 위에서 반환된 게 없으면 결국 못가는 것임?
	}
	
	public static boolean rangeCheck(int y, int x) {
		return 0<= y && y<r && 0<=x && x<r;
	}
	
	public static void sort () { // y값 그리고x값 기준으로 정렬해주기 위한 메서드
		for (int i = 1; i <= 6; i ++) {
			for (int start = 0; start < nList.get(i).size()-1; start ++) {
				for (int end = start+1; end < nList.get(i).size(); end ++) {
					location a = nList.get(i).get(start);
					location b = nList.get(i).get(end);
					if (a.y > b.y) { // 앞에 위치한 것이 더 아래에 위치하고있음
						nList.get(i).set(start, b);
						nList.get(i).set(end, a);
					} else if (a.y == b.y) { // 위치는 같은데
						if (a.x > b.x) { // 더 오른쪽에 위치함
							nList.get(i).set(start, b);
							nList.get(i).set(end, a);
						}
					}
				}
			}
		}
	}
}
