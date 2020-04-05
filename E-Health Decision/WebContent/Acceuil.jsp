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
  <p class="text-center">Bienvenue dans note platforme de decision des cas de maladie de diabete</p>
  <p class="text-center">Un platforme qui utilise l'algorithme d'arbre de decision pour prevenir la possiblité
  d'attraper la maladie de diabete afin de proteger mieu notre santé</p>
</div>

<!-- Affichage rapport -->


    <script>
    <%!
    // --- String Join Function
    public String join(ArrayList<?> arr, String del)
    {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < arr.size(); i++)
        {

            if (i > 0) output.append(del);
            
            // --- Quote strings, only, for JS syntax
            if (arr.get(i) instanceof String) output.append("\"");
            output.append(arr.get(i));
            if (arr.get(i) instanceof String) output.append("\"");
        }

        return output.toString();
    }
    %>
        <%
            Class.forName("com.mysql.jdbc.Driver");
		    String dbURL = "jdbc:mysql://localhost:3306/"+"ehealth"; 
		    Connection conn = (Connection) DriverManager.getConnection(dbURL,"root","");
            String requete="SELECT Age ,count(*) as Nombre FROM diabete where Diabetique='Oui' GROUP by Age";
	        Statement sta=(Statement) conn.createStatement();
	        ResultSet rs=(ResultSet) sta.executeQuery(requete);

            ArrayList<String> age = new ArrayList<String>();
            ArrayList<Integer> diab = new ArrayList<Integer>();

            while(rs.next())
            {
                age.add(rs.getString("Age"));
                diab.add(rs.getInt("Nombre"));
            }

            conn.close();
        %>

        var agee = [<%= join(age, ",") %>];
        var diabb = [<%= join(diab, ",") %>];
        
 
    window.onload = function()
    {   
    	
        zingchart.render
        ({
        	
            id:"myChart",
            width:"75%",
            height:350,
            data:
            {
                "type":"bar",
                "title":
                {
                    "text":"Maladie de diabete selon la section d age"
                },
                "scale-x":
                {
                    "labels": agee
                },
                "plot":
                {
                    "line-width":1
                    
                },
                "series":
                [
                    {
                      "values":diabb
                    }
                ]
            }
        });
    };
    </script>
    <h1></h1>

     <div class="container-fluid">
    <div class="row">
      
      <div class="col-sm-7 col-8">
        <div id="section1"> 
         <div id="myChart"></div>
        </div> 
      </div>
      
      <nav class="col-sm-4 col-8" id="section1">
      <div class="card">
  <div class="card-body">
            On peut avoir cette maladie a tout age c'est pourquoi on vous propose
            ce rapport qui montre le nombre des maladies selon la section d'age   
            </div>
  </div>             
      </nav>
      
    </div>
  </div>



</body>
</html>