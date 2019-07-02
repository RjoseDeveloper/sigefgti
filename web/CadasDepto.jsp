<%-- 
    Document   : CadasDepto
    Created on : 02/07/2019, 17:46:09
    Author     : Merchan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro de Departamento</h1>
        
        <form action="cadastro" >
            Descricao:
            <input type="text" value="${Department.desciption}" name="desc"><br>
            Data:
            <input type="date" value="${Department.dateadded}" name="data"><br>
            Detalhes:
            <input type="text" value="${Department.details}" name="deta"><br>
           <input type="submit" value="Cadastrar" >
        </form>
    </body>
</html>
