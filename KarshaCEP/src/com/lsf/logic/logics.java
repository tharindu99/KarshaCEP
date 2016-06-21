package com.lsf.logic;

import java.util.ArrayList;

import com.lsf.cep.graph_predata;
import com.lsf.entity.Stock_MinimaMaxima;

public class logics {

	public void  maxima_calculate(int permno) {
		graph_predata grp = new graph_predata();
		ArrayList<Stock_MinimaMaxima> stk_maxima = grp.Maxima_dataCollect(permno);	
		ArrayList<Stock_MinimaMaxima> stk_splited = split_update(stk_maxima);	
		ArrayList<Stock_MinimaMaxima> stk_pseudoPRCN = PseudoPRCN_update(stk_splited);
		ArrayList<Stock_MinimaMaxima> stk_rawvol = RawVol_update(stk_pseudoPRCN);
		ArrayList<Stock_MinimaMaxima> stk_pseudoprc_hack = PseudoPRC_hack_update(stk_rawvol);
		
		for (Stock_MinimaMaxima stock_MinimaMaxima : stk_pseudoprc_hack) {
			System.out.println(stock_MinimaMaxima.getPseudoPRC_hack()+" : "+stock_MinimaMaxima.getDate());
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

	public ArrayList<Stock_MinimaMaxima> PreMin_update(ArrayList<Stock_MinimaMaxima> stk_arr) {
		for (int i = 0; i < stk_arr.size(); i++) {
			double preMin = stk_arr.get(i).getPseudoPRC()+((i+1)*0.0000001);
			stk_arr.get(i).setPseudoPRC_hack(pseudoprc_hack);
		}
		return stk_arr;
	}

}
