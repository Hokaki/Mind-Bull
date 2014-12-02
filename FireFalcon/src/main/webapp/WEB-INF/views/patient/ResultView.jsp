<%-- 
    Document   : ResultView
    Created on : Nov 10, 2014, 8:04:19 PM
    Author     : Jeff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <link href="<c:url value="/css/font-awesome-4.1.0/css/font-awesome.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/therapist-control.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
        <title>patient graph</title>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            function drawChart() {

                var repetitionsData = google.visualization.arrayToDataTable([
                ['Date', 'Repetitions'],
            <c:forEach var ="entry" items="${resultList}">
                ['${entry.date}', ${entry.repetitions}],
            </c:forEach>
                ]);
                var timeData = google.visualization.arrayToDataTable([
                ['Date', 'Max duration', 'Avg duration', 'Min duration'],
            <c:forEach var ="entry" items="${resultList}">
                ['${entry.date}', ${entry.maxTime},${entry.avgTime},${entry.minTime} ],
            </c:forEach>
                ]);
                var maxSpeedData = google.visualization.arrayToDataTable([
                ['Date', 'max Speed'],
            <c:forEach var ="entry" items="${resultList}">
                ['${entry.date} \n ${entry.exercise.name}', ${entry.maxSpeed}],
            </c:forEach>
                ]);

                var options1 = {
                    vAxis: {title: "Repetitions"},
                    hAxis: {title: "Date"},
                    legend: 'none'
                };
                var options2 = {
                    vAxis: {title: "Seconds"},
                    hAxis: {title: "Date"}
                };
                var options3 = {
                    vAxis: {title: "m/s"},
                    hAxis: {title: "Date"},
                    legend: 'none'
                };

                var repetitions = new google.visualization.LineChart(document.getElementById('chart_repetitions'));
                var time = new google.visualization.LineChart(document.getElementById('chart_time'));
                var maxSpeed = new google.visualization.LineChart(document.getElementById('chart_maxSpeed'));
                repetitions.draw(repetitionsData, options1);
                time.draw(timeData, options2);
                maxSpeed.draw(maxSpeedData, options3);
            }
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawChart);
        
        $(document).ready(function () {
            $(window).resize(function(){
                drawChart();
            });
        });
        </script>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
        <script type="text/javascript">
            function showonlyone(thechosenone) {
                $('.newboxes').each(function(index) {
                    if ($(this).attr("id") == thechosenone) {
                        $(this).show();
                    }
                    else {
                        $(this).hide();
                    }
                });
            }
        </script>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <h1>Patient progress</h1>
                    <h2>${patient.firstName} ${patient.lastName}</h2>
                    <script>
                        var xarray = [];
                            <c:forEach var="result" items="${resultList}">
                                xarray.push("${result.exercise.name}");
                            </c:forEach>
                        
                        var uxarray = [];
                        $.each(xarray, function(i, el){
                            if($.inArray(el, uxarray) === -1) uxarray.push(el);
                        });
                        
                        function exerciseChange(ce){
                            var abe = ce.value;
                            document.getElementById("paragraph").innerHTML = abe;
                        }
                    </script>
                    <div class="well">
                        <h2 id="paragraph">Exercise</h2>
                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-6">
                                <div class="btn-group-justified">
                                    <a class="btn btn-primary" id="myHeader1" href="javascript:showonlyone('chart_repetitions');" >Repetitions </a>
                                    <a class="btn btn-primary" id="myHeader2" href="javascript:showonlyone('chart_time');" >Duration </a>
                                    <a class="btn btn-primary" id="myHeader3" href="javascript:showonlyone('chart_maxSpeed');" >Max speed</a>
                                </div>
                                <br>
                                <select class="form-control" id="continent" onchange="exerciseChange(this);">
                                    <option value="...">None</option>
                                    <script>
                                     for(i=0;i<uxarray.length;i++){
                                        document.write('<option value="' + uxarray[i]+ '">' + uxarray[i] + '</option>');
                                    }   
                                    </script>
                                </select>
                                <br>
                            </div>
                        </div>
                    </div>
                            <div class="newboxes" id="chart_repetitions" style="width:100%; height:400px;"></div>
                            <div class="newboxes" id="chart_time" style="width:100%; height:400px;"></div>
                            <div class="newboxes" id="chart_maxSpeed" style="width:100%; height:400px;"></div>

                    <c:choose>
                        <c:when test="${resultList.size() != 0}">

                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>Patient</th>
                                            <th>Exercise</th>
                                            <th>Date</th>
                                            <th>Repetitions</th>
                                            <th>Raw data</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="result" items="${resultList}">
                                            <tr>
                                                <td>${result.patient.firstName} ${result.patient.lastName}</td>
                                                <td>${result.exercise.name}</td>
                                                <td>${result.date}</td>
                                                <td>${result.repetitions}</td>
                                                <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/RawData/show/">View</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>        
                        </c:when>
                        <c:otherwise>
                            There were no results found.
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>
