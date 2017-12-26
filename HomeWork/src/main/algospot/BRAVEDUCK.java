package main.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 난이도 하
 * algospot tutorial _  BRAVEDUCK
 * @author SIDeok
 */
public class BRAVEDUCK {
	
	/*
	 * 알고리즘 설계.
	 * 1. 특정 시작지점과 시작지점을 제외한 나머지 징검다리를 roop를 돌며 건너는 케이스를 찾는다.
	 * 2. 찾은 징검다리와 종료지점이 건널수 있는 거리인지 체크한다. -> 건널수 있는 거리이면 print("YES"); return;
	 * 3. 찾은 징검다리가 아직 종료지점에 닫지 못했다면 함수를 재귀호출하여 다음 징검다리를 찾는다.
	 * 4. 최종 징검다리에 도달아였으나 끝점이 아닐경우 print("NO"); return;
	 */
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력값을 저장해놓는 String array
		ArrayList<String> arguments = new ArrayList<String>(); 
		
		try {
			int  nRoopCnt = Integer.parseInt(br.readLine()); // 최초 입력은 반복횟수
			String[] res = {"NO", "NO"};
			for(int i = 0; i < nRoopCnt; i++) {
				
				int nJumpLength = Integer.parseInt(br.readLine()); // 점프거리
				int[] arrStartCor =  stringToArr(br.readLine()); // 시작좌표
				int[] arrEndCor =  stringToArr(br.readLine()); // 목표좌표
				int nNodeCnt = Integer.parseInt(br.readLine()); // 징검다리 개수
				
				boolean[][] arrDist = new boolean[nNodeCnt+2][nNodeCnt+2];
				
				ArrayList<int[]> listNode = new ArrayList<int[]>(); // 징검다리 ArrayList 에 담기
				listNode.add(arrStartCor); // 출발점
				for(int j = 0; j < nNodeCnt; j++) {
					listNode.add(stringToArr(br.readLine()));
				}
				listNode.add(arrEndCor); // 도착점
				
				for(int x = 0; x < listNode.size(); x++) {
					for(int y = 0; y < listNode.size(); y++) {
						if(x != y && getLength(listNode.get(x), listNode.get(y)) <= nJumpLength ) {
							arrDist[x][y] = true;
						} else {
							arrDist[x][y] = false;
						}
					}
				}
				
				boolean[] arrHist = new boolean[nNodeCnt+2];
				int baseIndex = 0;
				arrHist[baseIndex] = true;
				boolean flag = true;
				while(flag) {
					for(int y = 0; y < listNode.size(); ) {
						if(baseIndex != y && arrDist[baseIndex][y] && !arrHist[y]) {
							arrHist[y] = true;
							baseIndex = y;
							y = 0; 
							continue;
						} else if(y == (listNode.size() - 1)) {
							flag = false;
							if(baseIndex == (listNode.size() - 1)) {
								res[i] = "YES";
							}
							break;
						} else {
							y++;
						}
					}
				}
			}
			
			for(String s : res) System.out.println(s);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/*********************
	 * 두 좌표값의 거리를 구하는 메서드
	 *********************/
	public static double getLength(int[] pStartCor, int[] pEndCor) {
		return Math.sqrt((pStartCor[0] - pEndCor[0])*(pStartCor[0] - pEndCor[0]) + (pStartCor[1] - pEndCor[1])*(pStartCor[1] - pEndCor[1]) );
	}
	
	/**************************************
	 * String셋으로 넘어온 좌표값을 int array 로 변경하는 메서드
	 **************************************/
	public static int[] stringToArr(String str) {
		String[] strSet = str.split(" ");
		int[] rtnArr = {Integer.parseInt(strSet[0]), Integer.parseInt(strSet[1])};
		return rtnArr;
	}
	
	/**************************************
	 * 말단노드 체크 함수(재귀하며 최종 노드까지의 경로를 탐색한다.)
	 **************************************/
	public static int chkRoot(int[] pStartCor, int[] pEndCor, int pJumpLength, List<int[]> pNodeList) {

		if(getLength(pStartCor, pEndCor) <= pJumpLength) {
			return 1;
		}
		
		for(int i = 0; i < pNodeList.size(); i++) {
			if(getLength(pStartCor, pNodeList.get(i)) <= pJumpLength) {
				int[] arrNewStartCor = pNodeList.get(i);
				List<int[]> listNewNode = pNodeList;
				listNewNode.remove(i); // 선택된 노드는 제거
				return chkRoot(arrNewStartCor, pEndCor, pJumpLength, listNewNode);
			}
		}
		
		return 0;
	}
	
	
}

/*

문제

어느 더운 여름날, '모두의 오리'라는 오리 농장에서 복날을 맞이해 농장 주인이 1번 오리부터 차례대로 트럭에 집어넣고 있었다. 그러나 용감한 오리 Pekaz가 농장으로부터 탈출하고 말았다 (후일 이 오리는 0번째 오리라고 알려졌다).
Pekaz가 농장으로부터 완전히 벗어나기 위해서는 커다란 강을 건너야 했다. 불행하게도 최근 장마로 인해 물살이 너무 거세져 제대로 헤엄을 칠 수 없기에, 그 대신 강 위에 설치된 돌다리를 이용해 건너기로 했다.
여기서 강은 2차원 평면으로, 돌다리를 구성하는 각 돌은 그 위의 점으로 보기로 하자. 우리의 Pekaz는 돌다리가 아닌 강 위의 특정 지점에서 출발해 목표 지점까지 이동하려고 한다. 
Pekaz는 언제건 자신이 위치한 곳에서 직선거리가 J 이하인 곳으로 뛰어서 이동할 수 있다. 이 J를 최대 점프력이라 하자. 뛰어오르는 횟수에는 아무런 제약이 없지만, 만약 돌다리가 아닌 곳으로 뛸 경우 거센 물살에 휩쓸려 내려가 버리고 말 것이다.
과연 우리의 Pekaz는 탈출에 성공할 수 있을까? 아니면 안정적인 맛의 오리고기가 되고 말 것인가? 돌다리의 구성과 점프력이 주어질 때, Pekaz의 탈출 계획이 성공할 수 있는지 알아내는 프로그램을 작성하라.

입력

입력은 T 개의 테스트 케이스로 이뤄지며, 입력의 첫 줄에는 T가 주어진다.
각 테스트 케이스의 첫 번째 줄에는 Pekaz의 최대 점프력인 정수 J (1 <= J <= 1000)가 주어진다. 두 번째 줄과 세 번째 줄에는 각각 Pekaz의 시작 지점의 좌표와 도착 지점의 좌표가 주어지고, 네 번째 줄에는 돌다리를 구성하는 돌의 개수 N (0 < N <= 100)이 주어진다. 그다음 줄부터 N개의 줄에 걸쳐 각 돌의 좌표가 주어진다. 모든 좌표는 공백으로 구분된 두 정수 x, y (-1000 <= x, y <= 1000) 꼴로 주어지며 시작 지점, 도착 지점 및 각 돌의 좌표들이 동일한 경우는 주어지지 않는다.

출력

각 테스트 케이스마다 한 줄에 하나씩 탈출이 가능하면 "YES" , 불가능하면 "NO" 를 출력한다.

예제 입력
2
2
1 1
4 1
3
4 2
1 2
3 2
3
1 1
10 10
5
6 7
4 1
9 7
6 4
4 4

예제 출력
YES
NO

*/