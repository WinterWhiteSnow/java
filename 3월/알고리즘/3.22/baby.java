package baby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class location implements Comparable<location>{ // �켱���� ť�� ����ϱ� ���� �񱳱�
	int y;
	int x;
	int distance; // ���߿� ������ ��ȸ�Ҷ� �Ÿ� �󸶳� �Ǵ��� Ȯ���ϱ� ���� ����
	
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
	static int[] dr = {-1,0,0,1}; //�ϵ�����, �켱������ ���� ������������, ���̰� ���ٸ� ���� ���ʿ� ���������̴�
	static int[] dc = {0,1,-1,0}; //�ϴ� ���ΰ��ְ� ������, ���� ������ Ž���� �������� �������� ������
	static ArrayList<ArrayList<location>> nList; // ũ�⺰�� ��ǥ�� �����ص� ��
	static int[][] list; // �迭 ��ü � �������� �˱� ���� �迭.. nList�����δ� �ȵ�..
	static int level = 2; // �Ʊ��� �ʱⷹ��
	static int eating = 0; // ���� ���� ��, �������� ������ �����ϸ� ������!
	static location shark; // �Ʊ����� ��ġ
	static int r; // �迭����
	static int time = 0; // �Ʊ�� ȥ�ڼ� �Դ� �ð�
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		r = Integer.parseInt(br.readLine());
		
		nList = new ArrayList<>();
		list = new int[r][r];
		for (int y = 0; y <= 7; y ++) { // ����� ũ��� 1~6�̴� 7�������� ok
			nList.add(new ArrayList<>());
		}
		
		for (int y = 0; y< r; y ++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < r; x ++) {
				int level = Integer.parseInt(st.nextToken());
				if (level == 9) { // �Ʊ� �����
					shark = new location(y,x,0);
					continue;
				}
				nList.get(level).add(new location(y,x,0)); // ũ�⿡ �°� �迭�� �߰�
				list[y][x]=level;
			}
		}
//		System.out.println("�Ʊ� ����� ��ġ "+shark.y+" "+shark.x);
		sort();
		least();

		System.out.println(time);
	}
	
	public static location least() {
		//������ �Ǵ� �͵� ���� �ϴ� ������
		location eat = new location(-1,-1,20*20+1); // ���� ����� ��ġ ������ ����
		int fishlevel = 0;
		int fishindex = 0;
//		System.out.println("����!");
		//���� �������� �Ҷ� 20*20�� �迭�� ���̸� ���� �Ծ��ٰ� �ϸ� �� ������ 6�Ѱ��ؼ� Math.min�ؾ���
		for (int i = 1; i < Math.min(level,7); i ++) { // ���� �Ʊ����� �����̸��� ���� �� ����
			for (int index = 0; index < nList.get(i).size(); index++) { // ���� �� �ִ� �͵� �� ��ȸ�� ��
//				System.out.println("�԰��� �ϴ� ������� ���� "+i+" "+index);
				location candidate = move(nList.get(i).get(index),eat.distance); { // ������ �� �ִٸ� x�� y�� ��ǥ�� 0�̻�
//				System.out.println("�׷��� , ��ġ�� �� �Ÿ���? "+candidate.y+" "+candidate.x+" "+candidate.distance);
				if (candidate.x != -1) { // �迭 �� ���� == �� �� �ִ� ��
					if (eat.distance > candidate.distance) { // �׷��� ���� �Ÿ����� �ξ� ª���� ����
						eat.y = candidate.y;
						eat.x = candidate.x;
						eat.distance = candidate.distance;
						fishlevel = i;
						fishindex = index;
//						System.out.println("�� ! �Ÿ������� ����!!!"+eat.y+" "+eat.x);
						} else if (candidate.distance == eat.distance) { // �Ÿ���ü�� �����ѵ�, y��ǥ������ �� ���ʿ� �ִٸ�
							if (candidate.y < eat.y) {
								eat.y = candidate.y;
								eat.x = candidate.x;
								eat.distance = candidate.distance;
								fishlevel = i;
								fishindex = index;
//								System.out.println("�� ! ���̶����� ����!!!"+eat.y+" "+eat.x);
							} else if (candidate.y == eat.y && candidate.x < eat.x) { // y��ǥ�����ε� ���������� �� ���ʿ� �ִٸ�
								eat.y = candidate.y;
								eat.x = candidate.x;
								eat.distance = candidate.distance;
								fishlevel = i;
								fishindex = index;
//								System.out.println("�� ! �����̶� ����!!!"+eat.y+" "+eat.x);
						}
					 }
					}
				}
			}
		}
		if (eat.x != -1) { // �迭 �� ��ǥ�� == ���� �� ����
//			System.out.println("�Դ´�!!!");
//			System.out.println(eat.y+" "+eat.x+" �Ÿ��� ? : "+eat.distance);
			nList.get(fishlevel).remove(fishindex); // �Ծ����� ����
			eating++;
			if (eating == level) { // ������!
				level++;
				eating = 0;
			}
			time+=eat.distance;
//			System.out.println("���ݱ��� ����� �ð� : "+time);
			// �Ծ����� ���̰� �ִ� ���� �ٷ� �Ʊ� ����� ��ġ�� ��
			shark.y = eat.y;
			shark.x = eat.x;
			least(); // �Ծ��⶧���� ��͵���
		}
		return eat;
	}
	
	public static location move(location goal, int max) { // ���� ��ġ�� �� �� �ִ��� Ȯ���ϴ� �޼���, �߰��� �ڱ⺸�� ū ������ ������ ��������
//		System.out.println("��ǥ ��ġ : "+goal.y+" "+goal.x);
		PriorityQueue<location> queue = new PriorityQueue<>();
		boolean[][] visit = new boolean[r][r];
//		visit[shark.y][shark.x]=true; // ���� �Ʊ� ����� ��ġ
		queue.add(new location(shark.y,shark.x,0)); // ���� �Ʊ� ��� ��ġ �������� ���������� ����, �̸� ���� ���⿣, �׷��� �Ѱ��� ���� �Ѱ��� ������� = ����� ���� �ȳ���
		while (! queue.isEmpty()) {
			location temp = queue.poll();
			if (temp.distance+1 > max) {
				break;
			}
//			System.out.println("���� ��ġ : "+temp.y+" "+temp.x+" "+temp.distance);
			for (int i = 0; i <4; i ++) {
				int ny = dr[i]+temp.y;
				int nx = dc[i]+temp.x;
//				System.out.println("�湮 ���� : "+ny+" "+nx);
				if (rangeCheck(ny,nx)) { // �迭 �� ������, �ϴ� ��ȿ�� ��
					if (visit[ny][nx]) { // �̹� �湮�� �� �ִ� ����
						continue;
					}
					if (list[ny][nx] <= level) { // ������ �� ����!
						// �ٵ� ���� ��ǥ�����̶��?
						if (ny == goal.y && nx == goal.x) { // �ٷ� �������ִ� ������ bfs�����鼭 �켱���� ť�� ���� ���������ַ� �����⶧����
							// �ݵ�� �������� �������� ������!
							return new location(ny,nx,temp.distance+1);							
						}
						queue.add(new location(ny,nx,temp.distance+1));
						visit[ny][nx]=true;
					}
				}
				
			}
		}
		return new location(-1,-1,-1); // ������ ��ȯ�� �� ������ �ᱹ ������ ����?
	}
	
	public static boolean rangeCheck(int y, int x) {
		return 0<= y && y<r && 0<=x && x<r;
	}
	
	public static void sort () { // y�� �׸���x�� �������� �������ֱ� ���� �޼���
		for (int i = 1; i <= 6; i ++) {
			for (int start = 0; start < nList.get(i).size()-1; start ++) {
				for (int end = start+1; end < nList.get(i).size(); end ++) {
					location a = nList.get(i).get(start);
					location b = nList.get(i).get(end);
					if (a.y > b.y) { // �տ� ��ġ�� ���� �� �Ʒ��� ��ġ�ϰ�����
						nList.get(i).set(start, b);
						nList.get(i).set(end, a);
					} else if (a.y == b.y) { // ��ġ�� ������
						if (a.x > b.x) { // �� �����ʿ� ��ġ��
							nList.get(i).set(start, b);
							nList.get(i).set(end, a);
						}
					}
				}
			}
		}
	}
}
