package birdStrike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/20529
//비둘기집 이론은 모르겠으나
//n칸이 있고, 물건을 n+1넣는다면
//어떤 칸은 2개이상 들어간 상태이다
// 라는 설명으로도 충분한 것 같다.
// 만약 한종류만 몰빵, 한칸에만 몰빵 넣는다고해도
// 우리가 원하는 답은 충분히 찾을 수 있다
public class BirdStrike {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String, Integer> map; // 해당 키가 3개 이상 존재하는지 확인하는 것
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int repeat = Integer.parseInt(br.readLine());
		for (int r = 0; r < repeat; r ++) {
			int leng = Integer.parseInt(br.readLine());
			answer = 12;
			st = new StringTokenizer(br.readLine()); // 일단 입력 받아둠
			String[] nList = new String[leng];
			if (leng > 32) { // 전부 골고루 펴졌다고 해도 결국엔 33번째부턴 반드시 어떤 MBTI는 3개이상 존재하게됨
				answer=0;
			} else { // 33개 미만임, 이럴땐 직접 전부 돌려봐야함..
				for (int i = 0; i < leng; i ++) {
					nList[i]=st.nextToken();
				}
				for (int i = 0; i < leng; i ++) {
					for (int ii = i+1; ii < leng;  ii++) {
						for (int iii = ii+1; iii < leng; iii++) {
							String first = nList[i];
							String second = nList[ii];
							String third = nList[iii];
							int count = 0;
							count+=check(first,second)+check(second,third)+check(first,third);
							if (count < answer) {
								answer = count;
							}
						}
					}
				}
			}
			System.out.println(answer);
		}
	}
	
	public static int check(String first, String second) {
		int total = 0;
		for (int index = 0; index <4; index ++) {
			if (first.charAt(index) != second.charAt(index)) {
				total++;
			}
		}
		return total;
	}
}
