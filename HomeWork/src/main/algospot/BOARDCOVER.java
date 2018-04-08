package main.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOARDCOVER {
	/**
	 * 
	 * ���̵� ��
	 * algospot tutorial _  BOARDCOVER
	 * @author SIDeok
     *
	 * �Է� 1 : �׽�Ʈ���̽�
	 * �Է� 2 : ���α���, ���α���
	 * �Է� n : ���� ���̸�ŭ �ݺ� ( #�� ����ĭ . �� ��ĭ)
	 * 
	 * ��� : ������ ����Ǽ�
	 * 
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine()
		// �� �� ��
		try {
			int cnt = Integer.parseInt(br.readLine()); // �ݺ���(�׽�Ʈ���̽�)
			for(int i = 0; i < cnt; i++) {
				
				// ���� �ʱ�ȭ
				String[] arrSize = br.readLine().split(" "); // �ݺ���(�׽�Ʈ���̽�)
				int lenH = Integer.parseInt(arrSize[0]);
				int lenW = Integer.parseInt(arrSize[1]);
				
				String[][] arrBoard = new String[lenH][lenW];
				
				int whiteCnt = 0;
				for(int j = 0; j < lenH; j++) {
					String board = br.readLine();
					for(int z = 0; z < lenW; z++) {
						arrBoard[j][z] = board.substring(z, z + 1);
						if(arrBoard[j][z].equals(".")) {
							whiteCnt++;
						}
					}
				}
				
				// ��� ������ 3�ǹ���� �ƴҰ�� ����Ǽ� 0
				if(whiteCnt%3 != 0) {
					System.out.println(0);
					continue;
				}
				
				// ����Ǽ� üũ
				
//				for(int j = 0; j < lenH; j++) {
//					for(int z = 0; z < lenW; z++) {
//						System.out.print(arrBoard[j][z]);
//					}
//					System.out.println("");
//				}
					
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
