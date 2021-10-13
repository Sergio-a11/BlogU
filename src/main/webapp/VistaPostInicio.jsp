<%-- 
    Document   : VistaPost
    Created on : 24/05/2021, 09:26:06 PM
    Author     : Sergio Cruz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.dto.PostDTO"%>
<%@page import="modelo.dao.PostDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Posts</title>
        <link rel="icon" type="image/png" href="img/logo.png"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid" >
            <div class="row">
                <nav class="navbar navbar-expand-md navbar-dark bg-dark nav-pills nav-justified">
                    <a class="navbar-brand" href="./inicio.jsp">
                        <img src="img/logo.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
                        BLOG-UDP
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2 py-md-0">
                            <li class="nav-item active col-6 col-md-auto">
                                <a class="nav-link" href="./inicio.jsp">Home</a>
                            </li>
                            <li class="nav-item col-6 col-md-auto">
                                <a class="nav-link" href="./aboutInicio.jsp">Nosotros</a>
                            </li>
                        </ul> 
                        <hr class="d-md-none text-white-50">
                        <ul class="navbar-nav flex-row flex-wrap ms-md-auto">                            
                            <li class="nav-item col-6 text-align-center">
                                <a style="color: white;" class="nav-link d-lg-inline-block my-2 my-md-0 ms-md-3" href="PerfilCTO?opc=perfil&id=${user.getId()}">
                                    <button type="submit" class="btn btn-outline-light btn-sm">${user.getNombre()}</button>
                                </a>
                            </li>
                            <li class="nav-item col-6">  
                                <a href="InicioCTO?opc=salir" class="nav-link d-lg-inline-block my-2 my-md-0 ms-md-3">
                                    <button type="submit" class="btn btn-danger btn-sm">
                                        Cerrar Sesión
                                    </button>
                                </a>
                            </li> 
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="container mt-5 mb-5"> 
                <c:forEach var="obj" items="${patote}">
                    <div class="row">
                        <div class="col-sm-12 mt-3 mb-3">   
                            <div class="card text-white bg-dark">
                                <div class="card-header">${obj.getTitulo()} - Autor: ${obj.getAutor()}</div>
                                <div class="card-body">                            
                                    <p class="card-text">${obj.getContenido()}...</p>
                                </div>
                                <div class="card-footer d-flex justify-content-md-between">
                                    <small class=" text-muted">${obj.getFecha_publicacion().toString()} - Palabras Clave: ${obj.getPalabras()}</small>
                                    <form action="VerPublicacionCTO?id=${obj.getId()}&opc=iniciado" method="POST">
                                        <button class="btn btn-outline-light" type="submit">Seguir leyendo</button>
                                    </form>
                                </div> 
                            </div>
                        </div>
                    </div>
                </c:forEach>     
            </div>        
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>    
    </body>
</html>
