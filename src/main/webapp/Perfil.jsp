<%-- 
    Document   : Perfil
    Created on : 14-jun-2021, 17:04:27
    Author     : dadxc
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
        <title>Mi Perfil</title>
        <link rel="icon" type="image/png" href="img/logo.png"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">        
        <link href="css/styles.css" rel="stylesheet">
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
            <div class="container mt-5 mb-5 banner-perfil" style="height: 10rem; background-color: #FFCFCF; border-radius: 10px; padding: 10px;">
                <div class="row" style="padding: 55px;">
                    <div class="col">
                        Nombre de usuario: ${user.getNombre()}
                        <button class="btn" data-bs-toggle="modal" data-bs-target="#usuario" type="button">
                            <img src="img/Editar.png" alt="" width="24" height="24" class="d-inline-block align-text-top">                            
                        </button>                        
                    </div>
                    <div class="col">
                        Correo de usuario: ${user.getEmail()}
                    </div>
                    <div class="col">
                        <button class="btn" data-bs-toggle="modal" data-bs-target="#eliminar" type="button">
                            Eliminar cuenta.
                            <img src="img/Eliminar.png" alt="" width="24" height="24" class="d-inline-block align-text-top">
                        </button>
                    </div>
                </div>
            </div>
            <div class="container mt-5 mb-5"> 
                <c:forEach var="obj" items="${lista_pato}">
                    <div class="row">
                        <div class="col-sm-12 mt-3 mb-3">   
                            <div class="card text-white bg-dark">
                                <div class="card-header d-flex justify-content-md-between">${obj.getTitulo()} - Autor: ${obj.getAutor()}
                                    <div class="d-flex justify-content-md-end">
                                        <a href="PostsCTO?opc=enviar&id=${obj.getId()}">
                                            <button class="btn me-3" data-bs-toggle="modal" data-bs-target="#post" type="submit" style="background-color: white;">
                                                <img src="img/Editar.png" alt="" width="24" height="24" class="d-inline-block align-text-top">
                                            </button>
                                        </a>
                                        <a href="PerfilCTO?opc=vBorrar&ahiru=${user.getId()}&id_post=${obj.getId()}">
                                            <button class="btn" type="submit" style="background-color: white;">
                                                <img src="img/Borrar.png" alt="" width="24" height="24" class="d-inline-block align-text-top">
                                            </button>
                                        </a>
                                    </div>    
                                </div>
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
        <div class="modal fade" id="usuario" tabindex="-1" aria-labelledby="usuario" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Cambiar Nombre</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="PerfilCTO?opc=cambio&ahiru=${user.getId()}" method="POST">
                            <div class=" form-floating mb-3">
                                <input class="form-control" type="text" name="usuario" placeholder="nickname" required="true" maxlength="40" autofocus="true"/>
                                <label for="usuario" class="col-form-label">Nuevo Nombre</label>
                            </div>                            
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-danger" value="Cambiar">Cambiar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="eliminar" tabindex="-1" aria-labelledby="eliminar" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">¿Estas seguro que deseas eliminar tu cuenta?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="PerfilCTO?opc=eliminar&ahiru=${user.getId()}" method="POST">
                            <div class=" form-floating mb-3">
                                <input class="form-control" type="text" name="contrasena" placeholder="contrasena" required="true" maxlength="40" autocomplete="off" autofocus="true"/>
                                <label for="contrasena" class="col-form-label">Contraseña</label>
                                <div class="col-auto">
                                    <span id="contrasena" class="form-text">
                                        Si deseas eliminar tu cuenta, por favor escribe la contraseña de la misma.<br>
                                        Despues de esto se eliminaran tus publicaciones, comentarios y toda tu cuenta.
                                    </span>
                                </div>
                            </div>    
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-danger" value="Eliminar">Eliminar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>                    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>    
    </body>
</html>
