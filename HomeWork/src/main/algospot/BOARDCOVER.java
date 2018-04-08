package main.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOARDCOVER {
	/**
	 * 
	 * 난이도 하
	 * algospot tutorial _  BOARDCOVER
	 * @author SIDeok
     *
	 * 입력 1 : 테스트케이스
	 * 입력 2 : 세로길이, 가로길이
	 * 입력 n : 가로 길이만큼 반복 ( #은 검은칸 . 은 흰칸)
	 * 
	 * 출력 : 가능한 경우의수
	 * 
	 */
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br.readLine()
		// ┌ └ ┐
		try {
			int cnt = Integer.parseInt(br.readLine()); // 반복수(테스트케이스)
			for(int i = 0; i < cnt; i++) {
				
				// 변수 초기화
				String[] arrSize = br.readLine().split(" "); // 반복수(테스트케이스)
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
				
				// 흰색 공간이 3의배수가 아닐경우 경우의수 0
				if(whiteCnt%3 != 0) {
					System.out.println(0);
					continue;
				}
				
				// 경우의수 체크
				
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
