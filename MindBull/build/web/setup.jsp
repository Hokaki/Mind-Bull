<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="support.HibernateUtil" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database setup</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>
    </head>
    <body>
        <section id="main_section">
            <h1>Database setup</h1>
            <form action="setuphandler" method="post">
                <table>
                    <tr>
                        <td> <button type="submit" name="command" value="1">Setup database</button></td>
                        <td> <button type="submit" name="command" value="0">Cancel</button></td>
                    </tr>
                </table>
            </form>
        </section>
    </body>
</html>
