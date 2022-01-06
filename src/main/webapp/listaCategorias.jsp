<%@ page import="br.com.vaasschool.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categorias</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Código</th>
        <th>Ordem</th>
        <th>Descrição</th>
        <th>Status</th>
        <th>Ícone</th>
        <th>Cor</th>
    </tr>
    </thead>

    <tbody>

    <%
        List<Category> categories = (List<Category>) request.getAttribute("categorias");
        for (Category c : categories) {
    %>
    <tr>
        <td><%=c.getName()%></td>
        %>
        <td><%=c.getCode()%></td>
        <td><%=c.getOrder()%></td>
        <td><%=c.getDescription()%></td>
        <td><%=c.getActive()%></td>
        <td><img src=<%=c.getImagePath()%></td>
        <td><%=c.getColorCode()%></td>
    </tr>

    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
