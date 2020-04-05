<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="zingchart.min.js"></script>
<title>Insert title here</title>
</head>
<body>
  <!--Chart Placement[2]-->
  <div id="chartDiv"></div>
  <script>
    var chartData = {
      type: 'bar',  // Specify your chart type here.
      title: {
        text: 'My First Chart' // Adds a title to your chart
      },
      legend: {}, // Creates an interactive legend
      series: [  // Insert your series data here.
          { values: [35, 42, 67, 89]},
          { values: [28, 40, 39, 36]}
      ]
    };
    zingchart.render({ // Render Method[3]
      id: 'chartDiv',
      data: chartData,
      height: 400,
      width: 600
    });
  </script>
</body>
</html>