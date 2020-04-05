<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>
<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="jquery.min.js"></script>
<script src="popper.min.js"></script>
<script src="bootstrap.min.js"></script> 
<script type="text/javascript" src="zingchart.min.js"></script> 
<title>Insert title here</title>
</head>
<body>
<!-- La barre de navigation -->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
<ul class="navbar-nav">
  <li class="nav-item">
    <a class="nav-link" href="Acceuil.jsp">Acceuil</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="Ameliorer.jsp">Amelioration</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="Arbre">Arbre de decision</a>
  </li>
</ul>
</nav>

<!-- Grand titre -->
<div class="container">
  <p class="display-4"></p>
  <p class="display-4">E Health Decision<img src="dp2.jpg" width="190" height="150"></p>
<p class="text-center">Vous pouvez jettez un coup d'oeil sur les informations d'algorithme utilisé</p>
</div>
<!-- Affichage Entropie -->
<nav class="col-sm-5 col-8" id="section1">
  <div class="card">
  <div class="card-body">
        <c:forEach var="v" items="${liste}">
<option name="${v}">  ${v} </option>
</c:forEach> 
  </div>
  </div>             
  </nav>
        <nav class="col-sm-6 col-8" id="section1">
      <div class="card">
  <div class="card-body">
             <c:forEach var="l" items="${liste2}">
        <p>  ${l} </p>
          </c:forEach> 
            </div>
  </div>             
      </nav>	
      
</body>
</html>