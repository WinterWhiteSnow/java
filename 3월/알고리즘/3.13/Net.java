package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Computer implements Comparable<Computer>{
	int start;
	int end;
	int distacne;
	public Computer(int start, int end, int distacne) {
		super();
		this.start = start;
		this.end = end;
		this.distacne = distacne;
	}
	@Override
	public int compareTo(Computer o) {
		if (this.distacne < o.distacne) {
			return -1;
		} else if (this.distacne > o.distacne) {
			return 1;
		}
		return 0;
	}
	
	
}
//https://www.acmicpc.net/problem/1922
//크루스칼 알고리즘 공부시작!

public class Net {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static PriorityQueue<Computer> queue;
	static int[] parent; //부모 저장
	static boolean[] visit;
	static int count;
	static int total;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		int r = Integer.parseInt(br.readLine());
		queue = new PriorityQueue<>();
		parent = new int[n+1];
		visit = new boolean[n+1];
		visit[1]=true;
		count = 1;
		total = 0;
		for (int i = 1; i <= n; i ++) {
			parent[i]=i;
		}
		for (int i = 0; i < r; i++) {
			st= new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			queue.add(new Computer(start,end,cost));
		}
		q(n);
		System.out.println(total);
	}
	
	public static int getParent(int num) {
		if (parent[num]==num) {
			return num;
		}
		return getParent(parent[num]);
	}
	
	public static void reset(int start, int num) {
		int next = parent[start];
		if (parent[start] > num) {
			parent[start]=num;
			if (num == 1 && ! visit[start]) {
//				System.out.println("해당되는것 : "+start);
				visit[start]=true;
				count++;
			}
			if (start != next) {
				reset(next,num);				
			}
		}
	}
	
	public static void union(int a, int b) {
		int aa = getParent(a);
		if (parent[a] != aa) {
			parent[a] = aa;
			if (aa == 1) {
				visit[a]=true;
				count++;
			}
		}
		int bb = getParent(b);
		if (parent[b] != bb) {
			parent[b]=bb;
			if (bb == 1) {
				visit[b]=true;
				count++;
			}
		}
//		System.out.println("a의 부모 : "+aa);
//		System.out.println("b의 부모 : "+bb);
		if (aa < bb) {
			reset(b, aa);
//			parent[b]=aa;
		} else {
			reset(a, bb);
//			parent[a]=bb;
		}
	}
	
	public static void q(int n) {
		while (! queue.isEmpty()) {
			Computer temp = queue.poll();
//			System.out.println("출발!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			System.out.println("현재 경로 : "+temp.start+" -> "+temp.end);
//			System.out.println("부모 : "+Arrays.toString(parent));
//			System.out.println("필요한 값 : "+temp.distacne);
			if (getParent(temp.start) != getParent(temp.end)) { // 서로다른부모 = 사이클 X
				union(temp.start,temp.end);
				total+=temp.distacne;
			} else {
//				System.out.println("이건 안더함");
			}
			if (count == n) {
				break;
			}
//			System.out.println("병합 한 후");
//			System.out.println("부모 : "+Arrays.toString(parent));
//			System.out.println(Arrays.toString(visit));
//			System.out.println(count);
			}
	}
	
}
