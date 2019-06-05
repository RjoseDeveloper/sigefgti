<%-- 
    Document   : tableRole
    Created on : May 23, 2019, 10:40:38 AM
    Author     : Raimundo Jose
--%>

<%@page import="app.model.Role"%>
<%@page import="java.util.List"%>
<%@page import="app.controller.RoleJpaController"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Roles</title>
        <jsp:include page="tamplates/Css_libs.html"/>

    </head>
    <body>

        <br><br>

        <div class="pure-g">
            <img src="img/sala.jpg" style="width: 100%; height: 190px;">
        </div>

        <div class="container">
            <div class="tab">&nbsp;</div>

            <center>
                <br><br>

                <div style="width:60% " class="pure-u-1 pure-u-md-1-3">
                    <div style="float: left" class=""><input class="pure-u-23-24" type="text"></div>
                    <div style="float: right"><a href="#" class="pure-button pure-button-primary btn1">Adicionar Registo </a></div>
                </div>
                <br>

                <table id="customers" class="pure-table pure-table-horizontal"
                       style="border: none; width: 60%;">

                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Operacoes</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            EntityManagerFactory con = (EntityManagerFactory) getServletContext().getAttribute("factory");
                            List<Role> roles = new RoleJpaController(con).findRoleEntities();
                            for (Role r : roles) {
                        %>
                        <tr>
                            <td><%= r.getIdrole()%></td>
                            <td><%= r.getDescription()%></td>
                            <td>
                                <a href="">Editar | </a>
                                <a href="">Remover</a>

                            </td>
                        </tr>

                        <% }%>

                    </tbody>
                </table>
            </center>
        </div>


    </body>
</html>
