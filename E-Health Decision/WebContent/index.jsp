<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.mysql.jdbc.Driver" %>

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

<!DOCTYPE html>
<html>
<head>
    <title>MySQL Demo</title>
    <script src="jquery.min.js"></script>
<script src="popper.min.js"></script>
<script src="bootstrap.min.js"></script> 
<script src="zingchart.min.js"></script> 
</head>
<body >
    <script>
        <%
            Class.forName("com.mysql.jdbc.Driver");
		    String dbURL = "jdbc:mysql://localhost:3306/"+"elibrairie"; 
		    Connection conn = (Connection) DriverManager.getConnection(dbURL,"root","");
            String requete="select * from livres ";
	        Statement sta=(Statement) conn.createStatement();
	        ResultSet rs=(ResultSet) sta.executeQuery(requete);

            ArrayList<String> months = new ArrayList<String>();
            ArrayList<Integer> users = new ArrayList<Integer>();

            while(rs.next())
            {
                months.add(rs.getString("auteur"));
                users.add(rs.getInt("prix"));
            }

            conn.close();
        %>

        var monthData = [<%= join(months, ",") %>];
        var userData = [<%= join(users, ",") %>];
        
 
    window.onload = function()
    {   
    	
        zingchart.render
        ({
        	
            id:"myChart",
            width:"100%",
            height:400,
            data:
            {
                "type":"bar",
                "title":
                {
                    "text":"Data Pulled from MySQL Database"
                },
                "scale-x":
                {
                    "labels": monthData
                },
                "plot":
                {
                    "line-width":1
                    
                },
                "series":
                [
                    {
                      "values":userData
                    }
                ]
            }
        });
    };
    </script>
    <h1>Data from MySQL Database</h1>
    <div id="myChart"></div>
</body>
</html>