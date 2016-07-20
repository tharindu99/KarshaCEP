package com.lsf.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.lsf.cep.graph_predata;
import com.lsf.entity.Stock_MinimaMaxima;

public class logics {

	public String  maxima_calculate(int permno,int D_got,int d_got,int L,int l ) {
		
		//window details//
		double d = (double)d_got/100;
		double D = (double)D_got/100;
		
		System.out.println("d : "+d+" D : "+D);
		
		
		
		graph_predata grp = new graph_predata();
		ArrayList<Stock_MinimaMaxima> stk_maxima = grp.Maxima_dataCollect(permno);	
		ArrayList<Stock_MinimaMaxima> stk_splited = split_update(stk_maxima);	
		ArrayList<Stock_MinimaMaxima> stk_pseudoPRCN = PseudoPRCN_update(stk_splited);
		ArrayList<Stock_MinimaMaxima> stk_rawvol = RawVol_update(stk_pseudoPRCN);
		ArrayList<Stock_MinimaMaxima> stk_pseudoprc_hack = PseudoPRC_hack_update(stk_rawvol);
		ArrayList<Stock_MinimaMaxima> stk_PreMin = PreMin_update(stk_pseudoprc_hack, l);
		ArrayList<Stock_MinimaMaxima> stk_PostMin = PostMin_update(stk_PreMin, L);
		ArrayList<Stock_MinimaMaxima> stk_WindowMax = WindowMax_update(stk_PostMin,l,L);
		ArrayList<Stock_MinimaMaxima> stk_completed = is_function_update(stk_WindowMax, d, D);
		
		/*for (Stock_MinimaMaxima stock_MinimaMaxima : stk_completed) {
			System.out.println(stock_MinimaMaxima.getDate()+" : "+stock_MinimaMaxima.isIsLocalTop());
			//System.out.println(stock_MinimaMaxima.getPseudoPRC()+" : "+stock_MinimaMaxima.getWindowMax()+" : "+stock_MinimaMaxima.getDate());
			//System.out.println(stock_MinimaMaxima.isIsmax()+" : "+stock_MinimaMaxima.getDate()+" : "+stock_MinimaMaxima.isIsBust());
		}*/
		String [][] LT = getLocalTops(stk_completed);
		String maximas = grp.Maxima_collectData(LT,permno);
		return maximas;
	}
		
	public ArrayList<Stock_MinimaMaxima> split_update(ArrayList<Stock_MinimaMaxima> stk_arr) {
		for (int i = 0; i < stk_arr.size(); i++) {
			if(i==0 || stk_arr.get(i).getSHROUT()==stk_arr.get(i-1).getSHROUT()){
				stk_arr.get(i).setSplit(false);
			}else {
				stk_arr.get(i).setSplit(true);
			}
		}
		return stk_arr;
	}
	
	public ArrayList<Stock_MinimaMaxima> PseudoPRCN_update(ArrayList<Stock_MinimaMaxima> stk_arr) {
		for (int i = 0; i < stk_arr.size(); i++) {
			if(i==0){
				stk_arr.get(i).setPseudoPRCn(1.00);
			}else {
				double pseudoPRCN = stk_arr.get(i-1).getPseudoPRCn()*(1+stk_arr.get(i).getRET());
				stk_arr.get(i).setPseudoPRCn(pseudoPRCN);
			}
		}
		return stk_arr;
	}

	public ArrayList<Stock_MinimaMaxima> RawVol_update(ArrayList<Stock_MinimaMaxima> stk_arr) {
		for (int i = 0; i < stk_arr.size(); i++) {
			int rawVol = (int) (stk_arr.get(i).getVOL()*stk_arr.get(i).getPRC());
			stk_arr.get(i).setRawVol(rawVol);
		}
		return stk_arr;
	}

	public ArrayList<Stock_MinimaMaxima> PseudoPRC_hack_update(ArrayList<Stock_MinimaMaxima> stk_arr) {
		for (int i = 0; i < stk_arr.size(); i++) {
			double pseudoprc_hack = stk_arr.get(i).getPseudoPRC()+((i+1)*0.0000001);
			stk_arr.get(i).setPseudoPRC_hack(pseudoprc_hack);
		}
		return stk_arr;
	}

	public ArrayList<Stock_MinimaMaxima> PreMin_update(ArrayList<Stock_MinimaMaxima> stk_arr,int l) {
		for (int i = 0; i < stk_arr.size(); i++) {
			double preMin[];
			Double preminrow ;
			if(i<l){
				preMin = calculate_min(0,i,stk_arr);
			}else{
				preMin = calculate_min(i-l,i,stk_arr);
			}
			preminrow = preMin[0];
			stk_arr.get(i).setPreMin(preMin[1]);
			stk_arr.get(i).setPreMinRow(preminrow.intValue());
		}
		return stk_arr;
	}
	
	public ArrayList<Stock_MinimaMaxima> PostMin_update(ArrayList<Stock_MinimaMaxima> stk_arr,int L) {
		for (int i = 0; i < stk_arr.size(); i++) {
			double postMin[];
			Double postminrow  ;
			if(i>=stk_arr.size()-L){
				postMin = calculate_min(i,stk_arr.size()-1,stk_arr);
			}else{
				postMin = calculate_min(i+1,i+L,stk_arr);
			}
			postminrow = postMin[0];
			stk_arr.get(i).setPostMin(postMin[1]);
			stk_arr.get(i).setPostMinRow(postminrow.intValue());
		}
		return stk_arr;
	}
	
	public double[] calculate_min(int low, int high, ArrayList<Stock_MinimaMaxima> stk_arr) {
		double minValue[] = {0,10000};
		for (int i = low; i <= high; i++) {
			if(minValue[1]>stk_arr.get(i).getPseudoPRC_hack()){
				minValue[1] = stk_arr.get(i).getPseudoPRC_hack();
				minValue[0] = i;
			}
		}
		return minValue;
	}
	
	public ArrayList<Stock_MinimaMaxima> WindowMax_update(ArrayList<Stock_MinimaMaxima> stk_arr,int l,int L) {
		for (int i = 0; i < stk_arr.size(); i++) {
			if(i<=l){
				stk_arr.get(i).setWindowMax(MaxCalculate(0,i+L, stk_arr));
			}else if(i+L>=stk_arr.size()){
				stk_arr.get(i).setWindowMax(MaxCalculate(i-l,stk_arr.size()-1, stk_arr));
			}else {
				stk_arr.get(i).setWindowMax(MaxCalculate(i-l,i+L, stk_arr));
			}
		}
		return stk_arr;
	}
	
	public double MaxCalculate(int low,int high, ArrayList<Stock_MinimaMaxima>stk_arr) {
		double maxValue = 0;
		for (int j = low; j <= high; j++) {
			if(maxValue<stk_arr.get(j).getPseudoPRC()){
				maxValue = stk_arr.get(j).getPseudoPRC();
			}
		}
		return maxValue;	
	}

	public ArrayList<Stock_MinimaMaxima> is_function_update(ArrayList<Stock_MinimaMaxima>stk_arr,double d,double D) {
		for (int i = 0; i < stk_arr.size(); i++) {
			//is max//
			if ( Double.compare(stk_arr.get(i).getPseudoPRC(), stk_arr.get(i).getWindowMax())==0) {
				stk_arr.get(i).setIsmax(true);
			}else {
				stk_arr.get(i).setIsmax(false);
			}
			//is boom //
			double boom =(stk_arr.get(i).getPseudoPRC()-stk_arr.get(i).getPreMin())/stk_arr.get(i).getPreMin();
			if (boom>d) {
				//System.out.println("d : "+d);
				stk_arr.get(i).setIsBoom(true);
			}else{
				stk_arr.get(i).setIsBoom(false);
			}
			//is bust //
			double bust = (stk_arr.get(i).getPseudoPRC()-stk_arr.get(i).getPostMin())/stk_arr.get(i).getPseudoPRC();
			if (bust>D) {
				stk_arr.get(i).setIsBust(true);
			}else{
				stk_arr.get(i).setIsBust(false);
			}
			//is local top 
			Stock_MinimaMaxima stk = stk_arr.get(i);
			int isboom = stk.isIsBoom() ? 1:0;
			int isbust = stk.isIsBust() ? 1:0;
			int ismax = stk.isIsmax() ? 1:0;
			boolean isLT = isboom*isbust*ismax == 1 ? true:false;
			stk_arr.get(i).setIsLocalTop(isLT);
		}
		
		return stk_arr;
	}

	public String[][] getLocalTops(ArrayList<Stock_MinimaMaxima> stk_arr) {
		int count_LT = 0;
		for (int i = 0; i < stk_arr.size(); i++) {
			if(stk_arr.get(i).isIsLocalTop())count_LT++;
		}
		String LocalTop[][]= new String[count_LT][2] ;
		int count = 0;
		for (int i = 0; i <stk_arr.size(); i++) {
			if(stk_arr.get(i).isIsLocalTop()){
				LocalTop[count][0] = stk_arr.get(stk_arr.get(i).getPreMinRow()).getDate();
				LocalTop[count][1] = stk_arr.get(stk_arr.get(i).getPostMinRow()).getDate();
				count++;
			}
		}
		return LocalTop;
	}

}
