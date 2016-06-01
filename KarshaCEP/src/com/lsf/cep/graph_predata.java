package com.lsf.cep;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lsf.entity.StockDetails;
import com.lsf.entity.stockPrice_grp;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class graph_predata extends DBopenConnection {
	
	public JsonObject draw_stockprice (int PERMNO) {
		DBopen_me();
		String Sql_stockprice = "SELECT date as AllDates,PRC,`Pseudo-PRC` as PseudoPRC,Turnover FROM stock WHERE PERMNO ="+PERMNO+";";
		List<stockPrice_grp> stk = session.createSQLQuery(Sql_stockprice)
								 .addEntity(stockPrice_grp.class)
								 .list();
		List<String> AllDates = new ArrayList<String>();
		List<Double> PRC = new ArrayList<Double>();
		List<Double> PseudoPRC = new ArrayList<Double>();
		List<Double> Turnover  = new ArrayList<Double>();
		
		for (stockPrice_grp stockPrice_grp : stk) {
			AllDates.add(stockPrice_grp.getAllDates());
			PRC.add(stockPrice_grp.getPRC());
			PseudoPRC.add(stockPrice_grp.getPseudoPRC());
			Turnover.add(stockPrice_grp.getTurnover());
		}
		Gson gson = new Gson();
		JsonObject j_obj = new JsonObject();
		
		JsonElement AllDates_J = gson.toJsonTree(AllDates);
		JsonElement PRC_J = gson.toJsonTree(PRC);
		JsonElement PseudoPRC_J = gson.toJsonTree(PseudoPRC);
		JsonElement Turnover_J = gson.toJsonTree(Turnover);
		
		j_obj.add("AllDates",AllDates_J);
		j_obj.add("PRC",PRC_J);
		j_obj.add("PseudoPRC",PseudoPRC_J);
		j_obj.add("Turnover",Turnover_J);
		DBclose_me();
		return j_obj;
	}
	
	public List<StockDetails> stock_details() {
		DBopen_me();
		String Sql_stockDetails = "SELECT PERMNO,COMNAM,TSYMBOL,NAICS,Naics_code as Naicscode,Naics_name as Naicsname FROM stock_details;";
		List<StockDetails> stk_details = session.createSQLQuery(Sql_stockDetails)
								 .addEntity(StockDetails.class)
								 .list();
		//Gson gson = new Gson();
	    //Type type = new TypeToken<List<StockDetails>>() {}.getType();
	    //String json = gson.toJson(stk_details, type);
		
		DBclose_me();
		return stk_details;
	}
}
