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
            .btn-group-justified{
                margin-top: 15px;
                margin-bottom: 7px;
                margin-left: 2px;
                margin-right: 2px;
            }
            .form-control{
                margin-top: 15px;
                margin-bottom: 7px;
                margin-left: 2px;
                margin-right: 2px;
            }
            #chart_time{
                display: none;
            }
            #chart_maxSpeed{
                display: none;
            }
            h3{
                margin-left: 20px;
                
            }
        </style>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            var selectedExercise;
            var selectedGraph;
            var counter;
            var patientFiltered;
            var patient = [];
            var patientName = "${patient.firstName}" + " " + "${patient.lastName}";
            var patientExerciseName= [];
            var index = 0;
            
            <c:forEach var="result" items="${resultList}">
                patientExerciseName.push("${result.exercise.name}");
                patient[index] = ({
                    exerciseName: "${result.exercise.name}",
                    date: "${result.date}",
                    repetitions: "${result.repetitions}",
                    maxTime: "${result.maxTime}",
                    avgTime: "${result.avgTime}",
                    minTime: "${result.minTime}",
                    maxSpeed: "${result.maxSpeed}"
                });
                index++;
            </c:forEach>
            selectedExercise = patient[0].exerciseName;

            function drawChart() {
                var tempPatient = [];
                //nieuwe array voor geselecteerde exercise
                for (i = 0; i < patient.length; i++) {
                    if(patient[i].exerciseName === selectedExercise){
                        tempPatient.push(patient[i]);
                    }
                }
                patientFiltered = tempPatient;
                
                var repetitionsData = new google.visualization.DataTable();
                repetitionsData.addColumn('string', 'Date');
                repetitionsData.addColumn('number', 'repetitions');
                repetitionsData.addRows(tempPatient.length);
                for (i = 0; i < tempPatient.length; i++) {
                        repetitionsData.setCell(i, 0, tempPatient[i].date);
                        repetitionsData.setCell(i, 1, tempPatient[i].repetitions);
                }
                var timeData = new google.visualization.DataTable();
                timeData.addColumn('string', 'Date');
                timeData.addColumn('number', 'Min Time');
                timeData.addColumn('number', 'Avg Time');
                timeData.addColumn('number', 'Max Time');
                timeData.addRows(tempPatient.length);
                for (i = 0; i < tempPatient.length; i++) {
                        timeData.setCell(i, 0, tempPatient[i].date);
                        timeData.setCell(i, 1, tempPatient[i].minTime);
                        timeData.setCell(i, 2, tempPatient[i].avgTime);
                        timeData.setCell(i, 3, tempPatient[i].maxTime);
                }
                var maxSpeedData = new google.visualization.DataTable();
                maxSpeedData.addColumn('string', 'Date');
                maxSpeedData.addColumn('number', 'Max speed');
                maxSpeedData.addRows(tempPatient.length);
                for (i = 0; i < tempPatient.length; i++) {
                        maxSpeedData.setCell(i, 0, tempPatient[i].date);
                        maxSpeedData.setCell(i, 1, tempPatient[i].maxSpeed);
                }
                
                var repetitions = new google.visualization.ColumnChart(document.getElementById('chart_repetitions'));
                var time = new google.visualization.ColumnChart(document.getElementById('chart_time'));
                var maxSpeed = new google.visualization.ColumnChart(document.getElementById('chart_maxSpeed'));
                
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
                    if ($(this).attr("id") === thechosenone) {
                        $(this).show();
                    }
                    else {
                        $(this).hide();
                    }
                });
                drawChart();
                loadChart();
            }
            
            var uniqueName = patientExerciseName.filter(function(elem, pos) {
                return patientExerciseName.indexOf(elem) === pos;
            }); 

            function selectExercise(input){
                counter = 0;
                selectedExercise = input;
                
                for (i = 0; i < patient.length; i++) {
                    if(selectedExercise === patient[i].exerciseName){
                        counter = counter + 1;
                    }
                }
                drawChart();
                loadChart();
            }
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
                            <div class="col-md-5">
                                <h3>Exercise</h3>
                                <div class="btn-group-justified">
                                    <a class="btn btn-primary" id="myHeader1" href="javascript:showonlyone('chart_repetitions');" >Repetitions </a>
                                    <a class="btn btn-primary" id="myHeader2" href="javascript:showonlyone('chart_time');" >Duration </a>
                                    <a class="btn btn-primary" id="myHeader3" href="javascript:showonlyone('chart_maxSpeed');" >Max speed</a>
                                </div>
                                <select class="form-control" id="exerciseSelect" onchange="exerciseChange(this);">
                                    <script type="text/javascript">
                                     for(i=0;i<uniqueName.length;i++){
                                        document.write('<option value="' + uniqueName[i]+ '">' + uniqueName[i] + '</option>');
                                    }
                                    </script>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <h3>Year</h3>
                                <select class="form-control" id="monthSelect">
                                    <option value="2013">2013</option>
                                    <option value="2014">2014</option>
                                    <option value="2015">2015</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <h3>Month</h3>
                                <select class="form-control" id="monthSelect">
                                    <option value="1">January</option>
                                    <option value="2">February</option>
                                    <option value="3">March</option>
                                    <option value="4">April</option>
                                    <option value="5">May</option>
                                    <option value="6">June</option>
                                    <option value="7">July</option>
                                    <option value="8">August</option>
                                    <option value="9">September</option>
                                    <option value="10">October</option>
                                    <option value="11">November</option>
                                    <option value="12">December</option>
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
                                        <tr class="info">
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
                                                <td><a class="btn btn-success glyphicon glyphicon-eye-open" href="${pageContext.request.contextPath}/RawData/show/"></a></td>
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
