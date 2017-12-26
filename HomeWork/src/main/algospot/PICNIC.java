package main.algospot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PICNIC {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] arrInput = null; // 입력값 배열
		 
		try {
			int lnC = Integer.parseInt(br.readLine().trim()); // 반복수
			arrInput = new String[lnC*2];
			
			// 입력값 세팅
			for(int i = 0; i < lnC*2; i++ ) {
				arrInput[i] = br.readLine().trim();
			}
			
			for(int i = 0; i < lnC*2; i+=2) {
				int res = 0;
				
				String[] arrCnt = arrInput[i].split(" ");
				String[] arrGrp = arrInput[i + 1].split(" ");
				
				ArrayList<int[]> listGrp = new ArrayList<int[]>();
				
				
				for(int j = 0; j < Integer.parseInt(arrCnt[1])*2; j+=2) {
					int[] tempGrp = new int[2];
					tempGrp[0] = Integer.parseInt(arrGrp[j]); 
					tempGrp[1] = Integer.parseInt(arrGrp[j + 1]); 
					listGrp.add(tempGrp);
					
				}
					
				for(int j = 0; j < listGrp.size(); j++) {
					ArrayList<int[]> tempListGrp = new ArrayList<int[]>();
					ArrayList<int[]> choiceGrp = new ArrayList<int[]>();
					
					tempListGrp.addAll(listGrp);
					choiceGrp.add(tempListGrp.get(j));
					
					for(int n = 0; n < j+1; n++) {
						tempListGrp.remove(0);
					}
					
					res += checkGrp(tempListGrp, choiceGrp, Integer.parseInt(arrCnt[0])/2);
				}
				
				System.out.println(res);
				
			}
			
			
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
	
	
	private static int checkGrp(ArrayList<int[]> listGrp, ArrayList<int[]> choiceGrp, int limit) {
		
		int[] arrChk = new int[choiceGrp.size()*2];
		for(int j = 0; j < choiceGrp.size()*2; j+=2) {
			arrChk[j] = choiceGrp.get(j/2)[0];
			arrChk[j+1] = choiceGrp.get(j/2)[1];
		}
		
		if(!checkDup(arrChk)) {
			choiceGrp.remove(choiceGrp.size()-1);
			return checkGrp(listGrp, choiceGrp, limit);
		}
		
		if(choiceGrp.size() == limit) {
			return 1;
		}
		
		if(listGrp.size() == 0) {
			return 0;
		}
		
		choiceGrp.add(listGrp.get(0));
		listGrp.remove(0);
		
		return checkGrp(listGrp, choiceGrp, limit);
	}
	
	private static boolean checkDup(int[] arrCheck) {
		HashSet<Integer> setChk = new HashSet<Integer>();
		for(int num : arrCheck) {
			setChk.add(num);
		}
		
		if(arrCheck.length == setChk.size()) {
			return true;
		} else {
			return false;
		}
	}
}
