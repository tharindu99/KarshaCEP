package com.lsf.cep;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class home_srvlt
 */
@WebServlet(
		urlPatterns = { "/home_srvlt" }, 
		initParams = { 
				@WebInitParam(name = "intial _value", value = "1")
		})
public class home_srvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home_srvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		DBopenConnection DB_con = new DBopenConnection();
		DB_con.DBopen_me();
		System.out.println("we ready...............");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userPath = request.getServletPath();
		PrintWriter pwr = response.getWriter();
		graph_predata grp = new graph_predata();
		if (userPath.equals("/stockprice")) {
			int PERMNO = Integer.parseInt(request.getParameter("PERMNO"));
			pwr.print(grp.draw_stockprice(PERMNO));
		} else if (userPath.equals("/stockDetails")) {
			pwr.print(grp.stock_details());

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
