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
<p class="text-center">Vous pouvez ajouter des resultats de cas réels afin d'ameliorer la precision</p>
</div>
<!-- Formulaire -->
<form action="Insertion" method="post" class="container center_div">
  <div class="form-group">
    <label for="genre">Quel etait le Genre ?</label>
    <select name="genre" class="custom-select">
    <option value="homme" name="homme">Homme</option>
    <option value="femme" name="femme">Femme</option>
  </select>
  </div>
  
  <div class="form-group">
    <label for="pwd">Quel etait la tranche d'age ?</label>
    <input name="age" class="form-control" id="pwd">
  </div>
  
  <div class="form-group">
    <label for="pwd">Combien de jours etait il hospitalisés ?</label>
    <input name="jourshospi" class="form-control" id="pwd">
  </div>
  
  <div class="form-group">
    <label for="pwd">Quel type de medcins a-t-il a consulté ?</label>
    <select name="typemedcin" class="custom-select">
    <option value="chirurgien" name="chirurgien">Chirurgien</option>
    <option value="dieteticien" name="dieteticien">Dieteticien</option>
  </select>
  </div>
  
    <div class="form-group">
    <label for="pwd">Combien d'analyses a-t-il effectué ?</label>
    <input name="nbanalyse" class="form-control" id="pwd">
    </div>
    
     <div class="form-group">
    <label for="pwd">Combien de fois etait-il pris en urgence a l'hopital ?</label>
    <input name="urgence" class="form-control" id="pwd">
    </div>
    
     <div class="form-group">
    <label for="pwd">Combien de fois etait-il hospitalisé ?</label>
    <input name="nbhospi" class="form-control" id="pwd">
    </div>
    
    <div class="form-group">
    <label for="pwd">Etait-il Diabetique ?</label>
    <select name="diab" class="custom-select">
    <option value="oui" name="oui">Oui</option>
    <option value="non" name="non">Non</option>
  </select>
    </div>
  
  
  <!-- Envoie -->
  <button type="submit" class="btn btn-dark">Valider</button>
</form> 
</body>
</html>