package main.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * ���̵� ��
 * algospot tutorial _  BRAVEDUCK
 * @author SIDeok
 */
public class BRAVEDUCK {
	
	/*
	 * �˰��� ����.
	 * 1. Ư�� ���������� ���������� ������ ������ ¡�˴ٸ��� roop�� ���� �ǳʴ� ���̽��� ã�´�.
	 * 2. ã�� ¡�˴ٸ��� ���������� �ǳμ� �ִ� �Ÿ����� üũ�Ѵ�. -> �ǳμ� �ִ� �Ÿ��̸� print("YES"); return;
	 * 3. ã�� ¡�˴ٸ��� ���� ���������� ���� ���ߴٸ� �Լ��� ���ȣ���Ͽ� ���� ¡�˴ٸ��� ã�´�.
	 * 4. ���� ¡�˴ٸ��� ���޾ƿ����� ������ �ƴҰ�� print("NO"); return;
	 */
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// �Է°��� �����س��� String array
		ArrayList<String> arguments = new ArrayList<String>(); 
		
		try {
			int  nRoopCnt = Integer.parseInt(br.readLine()); // ���� �Է��� �ݺ�Ƚ��
			String[] res = {"NO", "NO"};
			for(int i = 0; i < nRoopCnt; i++) {
				
				int nJumpLength = Integer.parseInt(br.readLine()); // �����Ÿ�
				int[] arrStartCor =  stringToArr(br.readLine()); // ������ǥ
				int[] arrEndCor =  stringToArr(br.readLine()); // ��ǥ��ǥ
				int nNodeCnt = Integer.parseInt(br.readLine()); // ¡�˴ٸ� ����
				
				boolean[][] arrDist = new boolean[nNodeCnt+2][nNodeCnt+2];
				
				ArrayList<int[]> listNode = new ArrayList<int[]>(); // ¡�˴ٸ� ArrayList �� ���
				listNode.add(arrStartCor); // �����
				for(int j = 0; j < nNodeCnt; j++) {
					listNode.add(stringToArr(br.readLine()));
				}
				listNode.add(arrEndCor); // ������
				
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
	 * �� ��ǥ���� �Ÿ��� ���ϴ� �޼���
	 *********************/
	public static double getLength(int[] pStartCor, int[] pEndCor) {
		return Math.sqrt((pStartCor[0] - pEndCor[0])*(pStartCor[0] - pEndCor[0]) + (pStartCor[1] - pEndCor[1])*(pStartCor[1] - pEndCor[1]) );
	}
	
	/**************************************
	 * String������ �Ѿ�� ��ǥ���� int array �� �����ϴ� �޼���
	 **************************************/
	public static int[] stringToArr(String str) {
		String[] strSet = str.split(" ");
		int[] rtnArr = {Integer.parseInt(strSet[0]), Integer.parseInt(strSet[1])};
		return rtnArr;
	}
	
	/**************************************
	 * ���ܳ�� üũ �Լ�(����ϸ� ���� �������� ��θ� Ž���Ѵ�.)
	 **************************************/
	public static int chkRoot(int[] pStartCor, int[] pEndCor, int pJumpLength, List<int[]> pNodeList) {

		if(getLength(pStartCor, pEndCor) <= pJumpLength) {
			return 1;
		}
		
		for(int i = 0; i < pNodeList.size(); i++) {
			if(getLength(pStartCor, pNodeList.get(i)) <= pJumpLength) {
				int[] arrNewStartCor = pNodeList.get(i);
				List<int[]> listNewNode = pNodeList;
				listNewNode.remove(i); // ���õ� ���� ����
				return chkRoot(arrNewStartCor, pEndCor, pJumpLength, listNewNode);
			}
		}
		
		return 0;
	}
	
	
}

/*

����

��� ���� ������, '����� ����'��� ���� ���忡�� ������ ������ ���� ������ 1�� �������� ���ʴ�� Ʈ���� ����ְ� �־���. �׷��� �밨�� ���� Pekaz�� �������κ��� Ż���ϰ� ���Ҵ� (���� �� ������ 0��° ������� �˷�����).
Pekaz�� �������κ��� ������ ����� ���ؼ��� Ŀ�ٶ� ���� �ǳʾ� �ߴ�. �����ϰԵ� �ֱ� �帶�� ���� ������ �ʹ� �ż��� ����� ����� ĥ �� ���⿡, �� ��� �� ���� ��ġ�� ���ٸ��� �̿��� �ǳʱ�� �ߴ�.
���⼭ ���� 2���� �������, ���ٸ��� �����ϴ� �� ���� �� ���� ������ ����� ����. �츮�� Pekaz�� ���ٸ��� �ƴ� �� ���� Ư�� �������� ����� ��ǥ �������� �̵��Ϸ��� �Ѵ�. 
Pekaz�� ������ �ڽ��� ��ġ�� ������ �����Ÿ��� J ������ ������ �پ �̵��� �� �ִ�. �� J�� �ִ� �������̶� ����. �پ������ Ƚ������ �ƹ��� ������ ������, ���� ���ٸ��� �ƴ� ������ �� ��� �ż� ���쿡 �۾��� ������ ������ �� ���̴�.
���� �츮�� Pekaz�� Ż�⿡ ������ �� ������? �ƴϸ� �������� ���� ������Ⱑ �ǰ� �� ���ΰ�? ���ٸ��� ������ �������� �־��� ��, Pekaz�� Ż�� ��ȹ�� ������ �� �ִ��� �˾Ƴ��� ���α׷��� �ۼ��϶�.

�Է�

�Է��� T ���� �׽�Ʈ ���̽��� �̷�����, �Է��� ù �ٿ��� T�� �־�����.
�� �׽�Ʈ ���̽��� ù ��° �ٿ��� Pekaz�� �ִ� �������� ���� J (1 <= J <= 1000)�� �־�����. �� ��° �ٰ� �� ��° �ٿ��� ���� Pekaz�� ���� ������ ��ǥ�� ���� ������ ��ǥ�� �־�����, �� ��° �ٿ��� ���ٸ��� �����ϴ� ���� ���� N (0 < N <= 100)�� �־�����. �״��� �ٺ��� N���� �ٿ� ���� �� ���� ��ǥ�� �־�����. ��� ��ǥ�� �������� ���е� �� ���� x, y (-1000 <= x, y <= 1000) �÷� �־����� ���� ����, ���� ���� �� �� ���� ��ǥ���� ������ ���� �־����� �ʴ´�.

���

�� �׽�Ʈ ���̽����� �� �ٿ� �ϳ��� Ż���� �����ϸ� "YES" , �Ұ����ϸ� "NO" �� ����Ѵ�.

���� �Է�
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

���� ���
YES
NO

*/