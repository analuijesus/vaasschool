<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/resources/css/style.css" rel="stylesheet">

    <title>${title}</title>
</head>
<div class="container">
    <body>
    <jsp:doBody/>
    </body>
</div>

<script src="/resources/js/jQuery.js"></script>
<script src="/resources/js/deactivate.js"></script>
</html>