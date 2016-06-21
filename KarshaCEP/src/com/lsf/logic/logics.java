package com.lsf.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.lsf.cep.graph_predata;
import com.lsf.entity.Stock_MinimaMaxima;

public class logics {

	public void  maxima_calculate(int permno) {
		
		//window details//
		double d = 0.04;
		double D = 0.04;
		int l = 10;
		int L = 9;
		
		graph_predata grp = new graph_predata();
		ArrayList<Stock_MinimaMaxima> stk_maxima = grp.Maxima_dataCollect(permno);	
		ArrayList<Stock_MinimaMaxima> stk_splited = split_update(stk_maxima);	
		ArrayList<Stock_MinimaMaxima> stk_pseudoPRCN = PseudoPRCN_update(stk_splited);
		ArrayList<Stock_MinimaMaxima> stk_rawvol = RawVol_update(stk_pseudoPRCN);
		ArrayList<Stock_MinimaMaxima> stk_pseudoprc_hack = PseudoPRC_hack_update(stk_rawvol);
		ArrayList<Stock_MinimaMaxima> stk_PreMin = PreMin_update(stk_pseudoprc_hack, l);
		ArrayList<Stock_MinimaMaxima> stk_PostMin = PostMin_update(stk_PreMin, L);
		
		for (Stock_MinimaMaxima stock_MinimaMaxima : stk_PreMin) {
			System.out.println(stock_MinimaMaxima.getPostMin()+" : "+stock_MinimaMaxima.getDate()+" : "+stock_MinimaMaxima.getPostMinRow());
		}
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
				postMin = calculate_min(i,i+L,stk_arr);
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
	
	

}
