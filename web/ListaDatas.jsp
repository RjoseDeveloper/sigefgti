<%-- 
    Document   : ListaDatas
    Created on : 02/07/2019, 17:46:27
    Author     : Merchan
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="app.controller.DepartmentJpaController"%>
<%@page import="java.util.List"%>
<%@page import="app.model.Department"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <table border="2">
            <thead>
                <tr>
                    
                    <th>Id</th> 
                    <th>Descricao</th> 
                    <th>Data</th> 
                    <th>Detalhes</th>  
                </tr>
                
            </thead>
            <% 
        EntityManagerFactory con = (EntityManagerFactory) getServletContext().getAttribute("factory");
                           List<Department> depto= new DepartmentJpaController(con).findDepartmentEntities();
                          
                           for (Department dep: depto){
                                SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
                              
        %>
           
        <tbody>
            <tr>
                <td><%=dep.getIddepartment() %></td> 
                <td><%=dep.getDescription() %></td> 
                <td><%=s.format(dep.getDateadded()) %></td> 
                <td><%=dep.getDetails()  %></td>
                
                
            </tr>
                
                
                
                
        </tbody>
        <% } %>
        </table>
        
            
            
       
    </body>
    </body>
</html>
