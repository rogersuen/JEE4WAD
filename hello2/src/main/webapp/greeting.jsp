<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en"><head><title>JSP Hello</title></head>
    <body bgcolor="#ffffff">
        <img src="resources/images/duke.waving.gif" alt="Duke waving his hand">
        <form method="get">
            <h2>Hello, my name is Duke. What's yours?</h2>
            <input title="My name is: " type="text" name="username" size="25">
            <p></p>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">

            <jsp:include page="response.jsp"/>
        </form>
    </body>
</html>
