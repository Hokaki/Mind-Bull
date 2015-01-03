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
        <!--<script src="<c:url value="/css/codebase/dhtmlxscheduler.js" />" type="text/javascript" charset="utf-8"></script>-->
        <link href="<c:url value="/css/font-awesome-4.1.0/css/font-awesome.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/therapist-control.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/style.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/datepicker3.css" />" rel="stylesheet" >
        <link href="<c:url value="/css/codebase/dhtmlxscheduler.css" />" rel="stylesheet" type="text/css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="<c:url value="/css/bootstrap-datepicker.js" />" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
            $(function () {
                $('.input-daterange').datepicker();
            });

            (function () {
                $('.combobox').combobox();
            });

            function printValue(sliderID, textbox) {
                var x = document.getElementById(textbox);
                var y = document.getElementById(sliderID);
                x.value = y.value;
            }

            window.onload = function ()
            {
                printValue('slider1', 'rangeValue1');
//                printValue('slider2', 'rangeValue2');
//                printValue('slider3', 'rangeValue3');
//                printValue('slider4', 'rangeValue4');
            }


        </script>
        <title>Add exercise</title>
    </head>
    <body>

        <div id="wrapper">
            <%@ include file="../navbar.jsp" %>

            <div id="page-wrapper">
                <div class="container-fluid">
                    <h2>${assignment.bsn.firstName} ${assignment.bsn.lastName}</h2>
                    <form:form class="form-horizontal" role="form" method="POST" commandName="assignment" action="${pageContext.request.contextPath}/assignment/add">  

                        <div class="form-group">
                            <label for="inputExercise" class="col-sm-2 control-label">exercise</label>
                            <div class="combobox">
                                <form:select path="exerciseId">
                                    <form:option value="0">Select exercise</form:option>
                                    <form:options items="${exercises}" itemValue="id" itemLabel="name"/>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">                                
                            <div class="col-sm-2"></div>
                            <div class="col-sm-4">  
                                <input id="slider1" type="range" min="0" max="20" step="1" onchange="printValue('slider1', 'rangeValue1')"/>
                                <form:input path="repetitions" id="rangeValue1" type="text" size="2"/>
                            </div>
                        </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="days">Days: </label>
                        <div class="col-sm-10">
                            <input type=checkbox id="chkBox1" value="1"/> Mon
                            <input type=checkbox id="chkBox2"  value="2"/> Tue
                            <input type=checkbox id="chkBox3" value="3"/> Wed
                            <input type=checkbox id="chkBox4" value="4"/> Thu
                            <input type=checkbox id="chkBox5" value="5"/> Fri
                            <input type=checkbox id="chkBox6"  value="6"/> Sat
                            <input type=checkbox id="chkBox7"  value="7"/> Sun
                            <form:input id="binaryDays" type="hidden" path="days" />
                        </div>
                    </div> 


                    <div class="form-group">
                        <label class="col-sm-2 control-label">Date</label>
                        <div class="input-daterange input-group" id="datepicker">
                            <form:input path="start_date" type="text" class="input-sm form-control" name="start" />
                            <span class="input-group-addon">to</span>
                            <form:input path="end_date" type="text" class="input-sm form-control" name="end" />
                        </div>
                    </div>


                    <div class="form-group">                                
                        <div class="col-sm-2"></div>
                        <div class="col-sm-4">     
                            <input class="btn btn-default" type="submit" value="Add" onClick="validate()"/>
                        </div>
                    </div>
                    <form:hidden path="bsn" value="${assignment.bsn.bsn}"/>
                </form:form>

            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->
    </div>
</body>
</html>
<script type="text/javascript">
    var days = "";

    function validate() {
        for (var i = 1, max = 7; i <= max; i++) {
            if (document.getElementById('chkBox'+i).checked) {
                days = days + "1";

            } else {
                days = days + "0";
            }
        }
        var binaryDays = document.getElementById('binaryDays');
        binaryDays.setAttribute('value', days);

    }
</script>