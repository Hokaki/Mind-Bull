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
        <link href="<c:url value="/css/datepicker3.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/codebase/dhtmlxscheduler.css" />" rel="stylesheet" type="text/css">
        <link href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.css" rel="stylesheet" >
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <link href="<c:url value="/css/ResultViewStyle.css" />" rel="stylesheet" >
        <title>patient graph</title>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            var selectedExercise;
            var selectedGraph;
            var patient = [];
            var patientName = "${patient.firstName}" + " " + "${patient.lastName}";
            var patientExerciseName= [];
            var index = 0;
            var filteredPatient = [];
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth()+1; //January is 0!
            var yyyy = today.getFullYear();

            if(dd<10) {
                dd='0'+dd;
            } 

            if(mm<10) {
                mm='0'+mm;
            } 
//                        today = mm+'/'+dd+'/'+yyyy;
            today = yyyy+'-'+mm+'-'+ dd;
            var startDate;
            defaultOverview();  
            function defaultOverview(){
                var month = '01';
                startDate = yyyy + '-' + month + '-' + '01';
            }
            
            function monthOverview(){
                startDate = yyyy + '-' + mm + '-' + '01';
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
            
            function dateOverview(input){
                startDate = input;
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
                
                filteredPatient = tempPatient;
                
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
                document.getElementById('paragraph').innerHTML = "Start date: "+ startDate.toString();
            }
        </script> 
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript">
        var dataSet2 = [];
        var index = 0;
            <c:forEach var="result" items="${resultList}">
                dataSet2[index] = ["${result.exercise.name}","${result.date}","${result.repetitions}","${result.maxTime}","${result.avgTime}", "${result.minTime}","${result.maxSpeed}"];
                index++;
            </c:forEach>
        $(document).ready(function() {
            $('#result_table').dataTable( {
                "data": dataSet2,
                "columns": [
                    { "title": "Exercise" },
                    { "title": "Date" },
                    { "title": "Repetitions" },
                    { "title": "Max Time" },
                    { "title": "Avg Time"},
                    { "title": "Min Time"}
                ]
            } ); 
        } );
        </script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script> 
        <script src="<c:url value="/css/bootstrap-datepicker.js" />" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
            $(function () {
//                $('.inputdate').datepicker();
                $('#datepicker').datepicker({dateFormat: 'yy-mm-dd'});
            });
            function dateChange(){
                var currentDate = $('#datepicker').datepicker('getDate');
                currentDate = $.datepicker.formatDate('yy-mm-dd', currentDate);
                dateOverview(currentDate);
                drawChart();
                loadChart();
                document.getElementById('paragraph').innerHTML = "Start date: "+ currentDate;
                
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
                                <select class="form-control" id="exerciseSelect" onchange="exerciseChange();">
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
                                    <option value="1">This month</option>
                                    <option value="2">Last three months</option>
                                </select>
                                <br>
                                <p><span id="paragraph"></span></p>
                            </div>
                            
                            <div class="col-md-3">
                                <div class="inputdate" id="datepicker" onclick="dateChange(this)">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="newboxes" id="chart_repetitions"></div>
                    <div class="newboxes" id="chart_time"></div>
                    <div class="newboxes" id="chart_maxSpeed"></div>
                    <div id="table_holder">
                        <table id="result_table" class="display" cellspacing="0" width="100%"></table>
                    </div>
                    <script>
                        for (i = 0; i < tempPatient.length; i++) {
                        document.write(tempPatient[i].date + ', ' + tempPatient[i].repetitions + ', ');
                        }
                    </script>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
    </body>
</html>
