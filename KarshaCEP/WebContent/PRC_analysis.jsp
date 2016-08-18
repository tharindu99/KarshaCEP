<%@page import="com.lsf.entity.StockDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.lsf.cep.graph_predata"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>KarshaCEP</title>
<link rel="icon" href="../../favicon.ico">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/c3.css" rel="stylesheet">
<link rel="stylesheet" type="css/easyui.css">
<script src="js/jquery.min.js"></script>
<style type="text/css">
.c3-circles-Turnover {
	display: none;
}

#eq>span {
	height: 120px;
	float: left;
	margin: 15px
}
</style>
<script type="text/javascript">
	var permno_page = 36469;
</script>

<!-- newly updated code segment------------------------------------------------------------------>
<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css
         "
	rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script>
	$(function() {
    	$( "#max_min_tabs" ).tabs();
    });
</script>

<style>
#max_min_tabs {
	font-size: 15px;
}

.ui-widget-header {
	background: #2980B9;
	border: 5px solid #2980B9;
	color: #FFFFFF;
	font-weight: bold;
	border: 5px solid #2980B9;
}
}
</style>

<!-- newly updated code segment------------------------------------------------------------------>
</head>

<%
	graph_predata grp = new graph_predata();
   List<StockDetails> stk_data = grp.stock_details();
%>
<body>
	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Karsha-CEP</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">Home</a></li>
					<li class="active"><a href="PRC_analysis.jsp">PRC</a></li>
					<li><a href="#">About</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<div class="container">
			<h2>Price Analysis</h2>
			<p>
				NAICS 52 : <b>Finance and Industry</b> , stock prices changes of
				major 10 equities [Market Capitalization].
			</p>
			<ul class="nav nav-tabs">
				<%
					for(int i=0;i<stk_data.size();i++){ 
												String id = "\"m"+(i+1)+"\"";
												String permno  = "\"#"+stk_data.get(i).getTsymbol()+stk_data.get(i).getPermno()+"\"";
				%>
				<li id=<%=id%>><a href=<%=permno%>><b><%=stk_data.get(i).getTsymbol()%></b>:<%=stk_data.get(i).getPermno()%></a></li>
				<%
					};
				%>
			</ul>
			<div class="tab-content">
				<%
					for(int i=0;i<stk_data.size();i++){
												String tab_id = stk_data.get(i).getPermno()+"-"+stk_data.get(i).getComnam();
				%>
				<div
					id="<%=stk_data.get(i).getTsymbol()%><%=stk_data.get(i).getPermno()%>"
					class="tab-pane fade">
					<div style="border-style: groove;">
						<h4>
							<center>
								PERMNO :
								<%=stk_data.get(i).getPermno()%>
								-
								<%=stk_data.get(i).getComnam()%>
								:
								<%=stk_data.get(i).getTsymbol()%></center>
						</h4>
						<div id="<%=stk_data.get(i).getTsymbol()%>"></div>
					</div>
				</div>
				<%
					};
				%>
			</div>
			<div class="col-xs-12" style="height: 10px;"></div>
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<div class="panel-heading">Maxima and Minima Calculation</div>
					<div class="panel-body">
						<div id="max_min_tabs">
							<div id = "2tabs_max_min">
								<ul class = "active">
									<li><a href="#tabs_2" >Maxima calculator</a></li>
									<li><a href="#tabs_3">Minima calculator</a></li>
								</ul>
							</div>
							<div class="row">
								<div id="tabs_2">
									<p>
										Please click the following "calculate" button to calculate the
										Maximas for this selected equity. &nbsp;<a href="#MAXIMA_calc"
											data-toggle="modal">How Maxima calculation works. </a>
									</p>
									
									<div class="col-lg-6">
										<form class="form-inline" role="form">
											<h5>Maxima Calculation Parameters</h5>
											D:<input type="number" class="form-control" id="insertD"
												placeholder="1-10" min="1" max="10" value=5> d:<input
												type="number" class="form-control" id="insertd"
												placeholder="1-10" min="1" max="10" value=5> L: <input
												type="number" class="form-control" id="insertL"
												placeholder="1-20" min="1" max="20" value=9> l: <input
												type="number" class="form-control" id="insertl"
												placeholder="1-20" min="1" max="20" value=10> <br>
											<br>
											<button id="maximaCalculate" type="button"
												class="btn btn-outline btn-primary btn-xs">Calculate
												Maxima</button>
										</form>
									</div>
								</div>
								<div id="tabs_3">
									<p>
										Please click the following "calculate" button to calculate the
										Minimas for this selected equity. &nbsp;<a href="#MINIMA_calc"
											data-toggle="modal">How Minima calculation works. </a>
									</p>
									<div class="col-lg-6">
										<form class="form-inline" role="form">
											<h5>Minima Calculation Parameters</h5>
											D:<input type="number" class="form-control" id="insertDmin"
												placeholder="1-10" min="1" max="10" value=5> d:<input
												type="number" class="form-control" id="insertdmin"
												placeholder="1-10" min="1" max="10" value=5> L: <input
												type="number" class="form-control" id="insertLmin"
												placeholder="1-20" min="1" max="20" value=9> l: <input
												type="number" class="form-control" id="insertlmin"
												placeholder="1-20" min="1" max="20" value=10> <br>
											<br>
											<button id="minimaCalculate" type="button"
												class="btn btn-outline btn-primary btn-xs">Calculate
												Minima</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- <div class="panel-footer"></div> -->
			</div>
		</div>
		<div class="modal fade" id="MAXIMA_calc" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							<center>MAXIMA Calculation.</center>
						</h4>
					</div>
					<div class="modal-body">
						<img src="img/MAXIMAclaculation.jpg"
							style="max-width: 100%; height: auto;">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="MINIMA_calc" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							<center>MINIMA Calculation.</center>
						</h4>
					</div>
					<div class="modal-body">
						<img src="img/MINIMAclaculation.jpg"
							style="max-width: 100%; height: auto;">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<div id="maxima_container" class="row"></div>

		<script src="js/bootstrap.min.js"></script>
		<script src="js/d3.min.js"></script>
		<script src="js/c3.js"></script>
		
		<script type="text/javascript">
	   					$( "#2tabs_max_min" ).click(function() {
	   						var myNode = document.getElementById("maxima_container");
							while (myNode.firstChild) {
							    myNode.removeChild(myNode.firstChild);
							}
	   					});
	   				</script>

		<script type="text/javascript">
	   					$( "#maximaCalculate" ).click(function() {
	   						var myNode = document.getElementById("maxima_container");
							while (myNode.firstChild) {
							    myNode.removeChild(myNode.firstChild);
							}
	   						var value_D = $('#insertD').val();
	   						var value_d = $('#insertd').val();
	   						var value_l = $('#insertl').val();
	   						var value_L = $('#insertL').val();
	   						//console.log("D: "+value_D+" d: "+value_d+"l: "+value_l+" L: "+value_L);
	   						maximas_getData(permno_page,value_D,value_d,value_L,value_l);
	   					});
   					</script>
   					
   		<script type="text/javascript">
	   					$( "#minimaCalculate" ).click(function() {
	   						var myNode = document.getElementById("maxima_container");
							while (myNode.firstChild) {
							    myNode.removeChild(myNode.firstChild);
							}
	   						var value_Dmin = $('#insertDmin').val();
	   						var value_dmin = $('#insertdmin').val();
	   						var value_lmin = $('#insertlmin').val();
	   						var value_Lmin = $('#insertLmin').val();
	   						//console.log("D: "+value_D+" d: "+value_d+"l: "+value_l+" L: "+value_L);
	   						minimas_getData(permno_page,value_Dmin,value_dmin,value_Lmin,value_lmin);
	   					});
   					</script>

		<script type="text/javascript" src="js/graph/stockPrice_grp.js"></script>
		<script type="text/javascript">
						<%for(int i=1;i<stk_data.size();i++){%>
							$("#m<%=(i+1)%>").click(function(){
								permno_page = <%=stk_data.get(i).getPermno()%>;
								StockPRC_graph(<%=stk_data.get(i).getPermno()%>, '#<%=stk_data.get(i).getTsymbol()%>');
								var myNode = document.getElementById("maxima_container");
								while (myNode.firstChild) {
								    myNode.removeChild(myNode.firstChild);
								}
							});
					 	<%};%>
					</script>
		<script>
						$(document).ready(function() {
							$(".nav-tabs a").click(function() {
								$(this).tab('show');
							});
							
							$( "#m1" ).last().addClass( "active" );
							$( "#<%=stk_data.get(0).getTsymbol()%><%=stk_data.get(0).getPermno()%>" ).last().addClass( "tab-pane fade in active" );
							StockPRC_graph(<%=stk_data.get(0).getPermno()%>, '#<%=stk_data.get(0).getTsymbol()%>');
							});
			$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
				options.async = true;
			});
		</script>
</body>
</html>