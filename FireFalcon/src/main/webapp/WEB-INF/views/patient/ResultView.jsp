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
            var patientFiltered;
            var patient = [];
            var patientName = "${patient.firstName}" + " " + "${patient.lastName}";
            var patientExerciseName= [];
            var index = 0;
            
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();

            if(dd<10) {
                dd='0'+dd
            } 

            if(mm<10) {
                mm='0'+mm
            } 
//                        today = mm+'/'+dd+'/'+yyyy;
            today = yyyy+'-'+mm+'-'+ dd;
            var startDate;
            defaultOverview();  
            function defaultOverview(){
                var month = '01';
                startDate = yyyy + '-' + month + '-' + dd;
            }
            
            function monthOverview(){
                var month = mm;
                var year = yyyy;
                if(month !== 01){
                    month = parseInt(month);
                    month = month - 1;
                    if(month < 10){
                        month = '0' + month;
                    }else{
                        month = '' + month;
                    }
                }else{
                    year = parseInt(year);
                    year = year - 1;
                    year = '' + year;
                }
                startDate = year + '-' + month + '-' + dd;
            }
            
            function threeMonthsOverview(){
                var month = mm;
                var year = yyyy;
                month = parseInt(month);
                if(month > 3){
                    month = month - 3;
                }else{
                    month = month + 12 - 3;
                    year = parseInt(year);
                    year = year - 1;
                    year = '' + year;
                }
                if(month < 10){
                    month = '0' + month;
                }else{
                    month = '' + month;
                }
                startDate = year + '-' + month + '-' + dd;
            }
            
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
                        if(patient[i].date > startDate){
                        tempPatient.push(patient[i]);
                        }
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

            function exerciseChange(input){
                selectedExercise = input.value;
                drawChart();
                loadChart();
            }
            function monthChange(input){
                if(input.value  === '1'){
                    monthOverview();
                }
                if(input.value === '2'){
                    threeMonthsOverview();
                }
                drawChart();
                loadChart();
                document.getElementById('paragraph').innerHTML = startDate.toString();
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
                            <div class="col-md-1">                  
                            </div>
                            <div class="col-md-3">
                                <h3>View</h3>
                                <select class="form-control" id="monthSelect" onchange="monthChange(this);">
                                    <option value="1">Last month</option>
                                    <option value="2">Last three months</option>
                                </select>
                                <br>
                                <p>Start date: <span id="paragraph"></span></p>
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
