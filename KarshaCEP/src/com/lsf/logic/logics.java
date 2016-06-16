package com.lsf.logic;

import java.util.ArrayList;

import com.lsf.cep.graph_predata;
import com.lsf.entity.Stock_MinimaMaxima;

public class logics {

	public void  maxima_calculate(int permno) {
		graph_predata grp = new graph_predata();
		ArrayList<Stock_MinimaMaxima> stk_maxima = grp.Maxima_dataCollect(permno);
		
		for (Stock_MinimaMaxima stock_MinimaMaxima : stk_maxima) {
			System.out.println(stock_MinimaMaxima.getDate());
		}
	}
}
