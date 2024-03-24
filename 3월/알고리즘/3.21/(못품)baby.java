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
	static int level = 2; // 아기상어 초기레벨
	static int eat = 0; // 현재 먹은 수, 먹은수와 레벨이 동일하면 레벨업!
	static location shark; // 아기상어의 위치
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int r = Integer.parseInt(br.readLine());
		nList = new ArrayList<>();
		for (int y = 0; y < 7; y ++) { // 물고기 크기는 1~6이니 7줄있으면 ok
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
			}
		}
	}
	
	public static location least() {
		//레벨이 되는 것들 전부 싹다 돌려봄
		location eat = new location(-1,-1,20*20+1); // 먹을 물고기 위치 저장할 변수
		int fishlevel = 0;
		int fishindex = 0;
		for (int i = 1; i < level; i ++) { // 현재 아기상어의 레벨미만만 먹을 수 있음
			for (location temp : nList.get(i)) {
				location candidate = move(temp); { // 지나갈 수 있다면 x와 y의 좌표가 0이상
				if (candidate.x != -1) { // 배열 내 범위 == 갈 수 있는 곳
					if (eat.distance > candidate.distance) { // 그런데 이전 거리보다 훨씬 짧으면 갱신
						eat.y = candidate.y;
						eat.x = candidate.x;
						eat.distance = candidate.distance;						
						}
					}
				}
			}
		}
		if (eat.x != -1) { // 배열 내 좌표값 == 먹을 수 잇음
			nList.get(fishlevel).remove(fishindex); // 먹엇으니 없음
		}
		return eat;
	}
	
	public static location move(location goal) { // 지정 위치로 갈 수 있는지 확인하는 메서드, 
		PriorityQueue<location> queue = new PriorityQueue<>();
		
		return new location(-1,-1,-1);
	}
}
