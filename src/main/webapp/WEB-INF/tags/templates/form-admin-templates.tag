<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>

    <title>${title}</title>
</head>
<body>
<div class="container">
    <jsp:doBody/>
</div>
</body>
</html>
