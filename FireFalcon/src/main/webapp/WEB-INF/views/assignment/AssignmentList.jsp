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
        <title>Assingments</title>
    </head>
    <body>  
        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>
            <div id="page-wrapper">

                <div class="container-fluid">

                    <h2>Assignments</h2>
                   
                    <c:choose>
                        <c:when test="${assignment.size() != 0}">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover table-striped">
                                    <thead>
                                        <tr class="info">
                                            <th>Patient Name</th>
                                            <th>exercise</th>
                                            <th>repetitions</th>
                                            <th>start date</th>
                                            <th>end date</th>
                                            <th>days</th>  
                                            <th>edit</th>
                                            <th>delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="assignments" items="${assignment}">
                                            <!-- Per gebruiker wordt nu een rij aangemaakt met daarin zijn gegevens -->
                                            <tr>
                                                <td><a href="${pageContext.request.contextPath}/patient/view/${assignments.bsn.bsn}">${assignments.bsn.firstName} ${assignments.bsn.lastName}</a></td>
                                                <td>${assignments.exerciseId.name}</td>
                                                <td>${assignments.repetitions}</td>
                                                <td>${assignments.start_date}</td>
                                                <td class="end_date">${assignments.end_date}</td>
                                                <td class="days">${assignments.days}</td>  
                                                <td><a class="btn btn-info glyphicon glyphicon-pencil" href="${pageContext.request.contextPath}/assignment/edit/${assignments.idAssignment}"></a></td>
                                                <td><a class="btn btn-danger glyphicon glyphicon-remove"href="${pageContext.request.contextPath}/assignment/delete/${assignments.idAssignment}"></a></td>                    
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>        
                        </c:when>
                        <c:otherwise>
                            There were no Assingments found.
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

    <script type="text/javascript">
        $(document).ready(function() {
            testDate();
        });
        
    function testDate() {      
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; 
        var yyyy = today.getFullYear();
        var end_date = $(this).html.split(' ');
 
        if(dd<10){
            dd='0'+dd;
        }

        if(mm<10){ 
         mm='0'+mm;
        }

        today = mm+'/'+dd+'/'+yyyy;
        
        var columns = $('.end_date');

                columns.each(function () {
                   var set_date = ""; 
                    if(today > console.log(end_date))
                        {
                         set_date = set_date.fontcolor("red");
                        }
                    else
                    {
                      set_date = set_date.fontcolor("green");     
                    }    
                $(this).html(set_date);
//                document.write(set_date);
        }
    }
    </script>
        

        <script type="text/javascript">
            $(document).ready(function () {
                testDays();
            });

            function testDays() {
                var daysOfWeek = [];
                daysOfWeek.push("Mon");
                daysOfWeek.push("Tue");
                daysOfWeek.push("Wed");
                daysOfWeek.push("Thu");
                daysOfWeek.push("Fri");
                daysOfWeek.push("Sat");
                daysOfWeek.push("Sun");

                var columns = $('.days');

                columns.each(function () {
                    var temp = "";

                    var days = $(this).html().split('');

                    for (var i = 0; i < days.length; i++) {
                        console.log(days[i]);
                        if (days[i] === '1') {
                            temp = temp + daysOfWeek[i] + ", ";

                        }
                    }
                    $(this).html(temp);
                });
            }

        </script>