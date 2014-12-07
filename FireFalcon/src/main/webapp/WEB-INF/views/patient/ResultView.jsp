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
        <style>
            .newboxes{
                width:100%; 
                height:400px;
            }
        </style>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            var selectedExercise;
            var selectedGraph;
            var counter;
            var patientName = "${patient.firstName}" + " " + "${patient.lastName}";
            var patientExerciseName= [];
            var patientDate= [];
            var patientRepetitions= [];
            var patientMaxTime= [];
            var patientAvgTime= [];
            var patientMinTime= [];
            var patientMaxSpeed= [];
            
            <c:forEach var="result" items="${resultList}">
                patientExerciseName.push("${result.exercise.name}");
                patientDate.push("${result.date}");
                patientRepetitions.push("${result.repetitions}");
                patientMaxTime.push("${result.maxTime}");
                patientAvgTime.push("${result.avgTime}");
                patientMinTime.push("${result.minTime}");
                patientMaxSpeed.push("${result.maxSpeed}");
            </c:forEach>
            selectedExercise = patientExerciseName[0];
                
            function selectExercise(input){
                counter = 0;
                selectedExercise = input;
                
                for (i = 0; i < patientDate.length; i++) {
                    if(selectedExercise == patientExerciseName[i]){
                        counter = counter + 1;
                    }
                }
                drawChart();
                loadChart();
            }
            function drawChart() {
                
                var repetitionsData = new google.visualization.DataTable();
                repetitionsData.addColumn('string', 'Date');
                repetitionsData.addColumn('number', 'repetitions');
                repetitionsData.addRows(patientDate.length);
                for (i = 0; i < patientDate.length; i++) {
                    if(patientExerciseName[i] == selectedExercise){
                        repetitionsData.setCell(i, 0, ""+patientDate[i]);
                        repetitionsData.setCell(i, 1, patientRepetitions[i]);
                    }
                }
                var timeData = new google.visualization.DataTable();
                timeData.addColumn('string', 'Date');
                timeData.addColumn('number', 'Avg Time');
                timeData.addColumn('number', 'min Time');
                timeData.addColumn('number', 'max Time');
                timeData.addRows(patientDate.length);
                for (i = 0; i < patientDate.length; i++) {
                    if(patientExerciseName[i] == selectedExercise){
                        timeData.setCell(i, 0, ""+patientDate[i]);
                        timeData.setCell(i, 1, patientAvgTime[i]);
                        timeData.setCell(i, 2, patientMinTime[i]);
                        timeData.setCell(i, 3, patientMaxTime[i]);
                    }
                }
                var maxSpeedData = new google.visualization.DataTable();
                maxSpeedData.addColumn('string', 'Date');
                maxSpeedData.addColumn('number', 'Max speed');
                maxSpeedData.addRows(patientDate.length);
                for (i = 0; i < patientDate.length; i++) {
                    if(patientExerciseName[i] == selectedExercise){
                        maxSpeedData.setCell(i, 0, ""+patientDate[i]);
                        maxSpeedData.setCell(i, 1, patientMaxSpeed[i]);
                    }
                }
                
                var options1 = {
                    title: selectedExercise,
                    vAxis: {title: "Repetitions"},
                    hAxis: {title: "Date"},
                    legend: 'none'
                };
                var options2 = {
                    title: selectedExercise,
                    vAxis: {title: "Seconds"},
                    hAxis: {title: "Date"}
                };
                var options3 = {
                    title: selectedExercise,
                    vAxis: {title: "m/s"},
                    hAxis: {title: "Date"},
                    legend: 'none'
                };

                var repetitions = new google.visualization.ColumnChart(document.getElementById('chart_repetitions'));
                var time = new google.visualization.ColumnChart(document.getElementById('chart_time'));
                var maxSpeed = new google.visualization.ColumnChart(document.getElementById('chart_maxSpeed'));
                repetitions.draw(repetitionsData, options1);
                time.draw(timeData, options2);
                maxSpeed.draw(maxSpeedData, options3);           
            }
            
            function loadChart(){
                google.load("visualization", "1", {packages: ["corechart"]});
                google.setOnLoadCallback(drawChart);

                $(document).ready(function () {
                    $(window).resize(function(){
                        drawChart();
                    });
                });
            }
            
            loadChart();
        </script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
        <script type="text/javascript">
            function showonlyone(thechosenone) {
                selectedGraph = thechosenone;
                $('.newboxes').each(function(index) {
                    if ($(this).attr("id") == thechosenone) {
                        $(this).show();
                    }
                    else {
                        $(this).hide();
                    }
                });
                drawChart();
                loadChart();
            }
            var uniqueName = [];
            $.each(patientExerciseName, function(i, uniqueCheck){
                if($.inArray(uniqueCheck, uniqueName) === -1) uniqueName.push(uniqueCheck);
            });

            function exerciseChange(input){
                selectExercise(input.value);
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
                    <div class="well">
                        <div class="row">
                            <div class="col-md-2">
                                <h3>Select exercise</h3>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group-justified">
                                    <a class="btn btn-primary" id="myHeader1" href="javascript:showonlyone('chart_repetitions');" >Repetitions </a>
                                    <a class="btn btn-primary" id="myHeader2" href="javascript:showonlyone('chart_time');" >Duration </a>
                                    <a class="btn btn-primary" id="myHeader3" href="javascript:showonlyone('chart_maxSpeed');" >Max speed</a>
                                </div>
                                <br>
                                <select class="form-control" id="exerciseSelect" onchange="exerciseChange(this);">
                                    <script type="text/javascript">
                                     for(i=0;i<uniqueName.length;i++){
                                        document.write('<option value="' + uniqueName[i]+ '">' + uniqueName[i] + '</option>');
                                    }
                                    </script>
                                </select>
                            </div>
                        </div>
                    </div>
                            <div class="newboxes" id="chart_repetitions"></div>
                            <div class="newboxes" id="chart_time"></div>
                            <div class="newboxes" id="chart_maxSpeed"></div>
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
