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
	static int level = 2; // �Ʊ��� �ʱⷹ��
	static int eat = 0; // ���� ���� ��, �������� ������ �����ϸ� ������!
	static location shark; // �Ʊ����� ��ġ
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int r = Integer.parseInt(br.readLine());
		nList = new ArrayList<>();
		for (int y = 0; y < 7; y ++) { // ����� ũ��� 1~6�̴� 7�������� ok
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
			}
		}
	}
	
	public static location least() {
		//������ �Ǵ� �͵� ���� �ϴ� ������
		location eat = new location(-1,-1,20*20+1); // ���� ����� ��ġ ������ ����
		int fishlevel = 0;
		int fishindex = 0;
		for (int i = 1; i < level; i ++) { // ���� �Ʊ����� �����̸��� ���� �� ����
			for (location temp : nList.get(i)) {
				location candidate = move(temp); { // ������ �� �ִٸ� x�� y�� ��ǥ�� 0�̻�
				if (candidate.x != -1) { // �迭 �� ���� == �� �� �ִ� ��
					if (eat.distance > candidate.distance) { // �׷��� ���� �Ÿ����� �ξ� ª���� ����
						eat.y = candidate.y;
						eat.x = candidate.x;
						eat.distance = candidate.distance;						
						}
					}
				}
			}
		}
		if (eat.x != -1) { // �迭 �� ��ǥ�� == ���� �� ����
			nList.get(fishlevel).remove(fishindex); // �Ծ����� ����
		}
		return eat;
	}
	
	public static location move(location goal) { // ���� ��ġ�� �� �� �ִ��� Ȯ���ϴ� �޼���, 
		PriorityQueue<location> queue = new PriorityQueue<>();
		
		return new location(-1,-1,-1);
	}
}
