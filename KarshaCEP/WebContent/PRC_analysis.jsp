<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>KarshaCEP</title>
<link rel="icon" href="../../favicon.ico">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<link href="css/c3.css" rel="stylesheet">
</head>
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
					<li class="active"><a href="index.jsp">Home</a></li>
					<li><a href="PRC_analysis.jsp">PRC</a></li>
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
				<li id="m1" class="active"><a href="#C70519"><b>C</b>:70519</a></li>
				<li id="m2"><a href="#WFC38703"><b>WFC</b>:38703</a></li>
				<li id="m3"><a href="#BAC59408"><b>BAC</b>:59408</a></li>
				<li id="m4"><a href="#JPM47896"><b>JPM</b>:47896</a></li>
				<li id="m5"><a href="#AIG66800"><b>AIG</b>:66800</a></li>
				<li id="m6"><a href="#MWD69032"><b>MWD</b>:69032</a></li>
				<li id="m7"><a href="#WB36469"><b>WB</b>:36469</a></li>
				<li id="m8"><a href="#AXP59176"><b>AXP</b>:59176</a></li>
				<li id="m9"><a href="#GS86868"><b>GS</b>:86868</a></li>
				<li id="m10"><a href="#V92611"><b>V</b>:92611</a></li>
			</ul>
			<div class="tab-content">
				<div id="C70519" class="tab-pane fade in active">
					<div style="border-style: groove;">
						<h4>
							<center>PERMNO : 70519 - CITIGROUP INC : C</center>
						</h4>
						<div id="C"></div>
					</div>
				</div>
				3
			</div>
					<script src="js/jquery.min.js"></script>
					<script>
						window.jQuery
								|| document
										.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
					</script>
					<script src="js/bootstrap.min.js"></script>
					<script src="js/d3.min.js"></script>
   					<script src="js/c3.js"></script>
					<script type="text/javascript" src="js/graph/stockPrice_grp.js"></script>
					<script>
						$(document).ready(function() {
							$(".nav-tabs a").click(function() {
								$(this).tab('show');
							});
							StockPRC_graph(70519, '#C');
						});
					</script>
</body>
</html>