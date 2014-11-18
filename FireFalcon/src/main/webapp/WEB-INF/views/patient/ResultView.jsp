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
            google.load("visualization", "1", {packages: ["corechart"]});
            google.setOnLoadCallback(drawChart);
            function drawChart() {

                var repetitionsData = google.visualization.arrayToDataTable([
                                            ['Date', 'Repetitions'],
            <c:forEach var ="entry" items="${resultList}">
                                            ['${entry.date} \n ${entry.exercise.name}', ${entry.repetitions}],
            </c:forEach>
                            ]);
                var timeData = google.visualization.arrayToDataTable([
                                            ['Date', 'Max duration' , 'Avg duration' , 'Min duration'],
            <c:forEach var ="entry" items="${resultList}">
                                            ['${entry.date} \n ${entry.exercise.name}', ${entry.maxTime},${entry.avgTime},${entry.minTime} ],
            </c:forEach>
                              ]);
                var maxSpeedData = google.visualization.arrayToDataTable([
                                            ['Date', 'max Speed'],
            <c:forEach var ="entry" items="${resultList}">
                                            ['${entry.date} \n ${entry.exercise.name}', ${entry.maxSpeed}],
            </c:forEach>
                              ]);

            var options = {
                title: '',
                lineWidth: 3
            };

            var repetitions = new google.visualization.LineChart(document.getElementById('chart_repetitions'));

            repetitions.draw(repetitionsData, {vAxis: {title: "Repetitions"},hAxis: {title: "Date"}, legend:'none'});

            var time = new google.visualization.LineChart(document.getElementById('chart_time'));

            time.draw(timeData, {vAxis: {title: "Seconds"},hAxis: {title: "Date"}});

            var maxSpeed = new google.visualization.LineChart(document.getElementById('chart_maxSpeed'));

            maxSpeed.draw(maxSpeedData, {vAxis: {title: "m/s"},hAxis: {title: "Date"}, legend:'none'});
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
                    
                    <c:choose>
                        <c:when test="${resultList.size() != 0}">
                            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingOne">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                Repetitions
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <div id="chart_repetitions" style="width: 1000px; height: 500px;"></div>                                        </div>
                                    </div>
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingTwo">
                                        <h4 class="panel-title">
                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                Duration
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseTwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">
                                        <div class="panel-body">
                                            <div id="chart_time" style="width: 1000px; height: 500px;"></div>                                        </div>
                                    </div>
                                </div>
                                 <div class="panel panel-default">
                                    <div class="panel-heading" role="tab" id="headingThree">
                                        <h4 class="panel-title">
                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                                Max Speed
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseFive" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingThree">
                                        <div class="panel-body">
                                            <div id="chart_maxSpeed" style="width: 1000px; height: 500px;"></div>                                        </div>
                                    </div>
                                 </div>
                            </div>
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th>Patient</th>
                                            <th>Exercise</th>
                                            <th>Date</th>
                                            <th>Repetitions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="result" items="${resultList}">
                                            <tr>
                                                <td>${result.patient.firstName} ${result.patient.lastName}</td>
                                                <td>${result.exercise.name}</td>
                                                <td>${result.date}</td>
                                                <td>${result.repetitions}</td>
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
