package com.lsf.logic;

import java.util.ArrayList;

import com.lsf.cep.graph_predata;
import com.lsf.entity.Stock_MinimaMaxima;

public class logics {

	public void  maxima_calculate(int permno) {
		graph_predata grp = new graph_predata();
		ArrayList<Stock_MinimaMaxima> stk_maxima = grp.Maxima_dataCollect(permno);	
		ArrayList<Stock_MinimaMaxima> stk_splited = split_update(stk_maxima);		
		
		for (Stock_MinimaMaxima stock_MinimaMaxima : stk_splited) {
			System.out.println(stock_MinimaMaxima.getSplit()+" : "+stock_MinimaMaxima.getDate());
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
}
