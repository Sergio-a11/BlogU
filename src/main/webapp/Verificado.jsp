<%-- 
    Document   : Verificado
    Created on : 1/07/2021, 07:34:57 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <title>Verificado</title>
        <link rel="icon" type="image/png" href="img/logo.png"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
       
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
            <nav class="navbar navbar-expand-md navbar-dark bg-dark nav-pills nav-justified">
                <a class="navbar-brand" href="./index.jsp">
                    <img src="img/logo.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
                    BLOG-UDP
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2 py-md-0">
                        <li class="nav-item active col-6 col-md-auto">
                            <a class="nav-link" href="./index.jsp">Home</a>
                        </li>
                        <li class="nav-item col-6 col-md-auto">
                            <a class="nav-link" href="./about.html">Nosotros</a>
                        </li>
                    </ul> 
                    <hr class="d-md-none text-white-50">
                    <ul class="navbar-nav flex-row flex-wrap ms-md-auto">
                        <li class="nav-item col-6 col-md-auto">
                            <a class="nav-link d-lg-inline-block my-2 my-md-0 ms-md-3">
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#registro" type="button">Registrarse</button>
                            </a>
                        </li>
                        <li class="nav-item col-6 col-md-auto">
                            <a class="nav-link d-lg-inline-block my-2 my-md-0 ms-md-3">
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#incio" type="button" value="Iniciar Sesión">Iniciar Sesión</button>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
            
        <div class="container-md">
            <div class="row">
                <div class="col">
                    <div class="alert alert-warning position-absolute top-50 start-50 translate-middle" role="alert">Cuenta verificada, gracias por confiar en nosotros</div>
                </div>
            </div>  
            <div class="row">
                <div class="col">
                    <div class="position-absolute bottom-0 start-50 translate-middle"><a href="inicio.jsp"><button class="btn btn-dark mb-5">Ir al inicio</button></a></div>
                </div>
            </div>
        </div>    
        </div>
        <div class="modal fade" id="registro" tabindex="-1" aria-labelledby="registro" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Registrarse</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="RegistroCTO" method="POST">
                                <div class="mb-3">
                                    <label for="usuario" class="col-form-label">Usuario</label>
                                    <input class="form-control" type="text" name="usuario" placeholder="nickname" required="true" maxlength="40" autofocus="true" autocomplete="off"/>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="col-form-label">Email</label>
                                    <input class="form-control" type="email" name="email" placeholder="user@email.com" required="true" maxlength="50"/>
                                </div>
                                <div class="mb-3">
                                    <label for="contrasena" class="col-form-label">Contraseña</label>
                                    <input class="form-control" name="contrasena" type="password" required="true" maxlength="20" minlength="3" aria-describedby="contrasena" />
                                    <div class="col-auto">
                                        <span id="contrasena" class="form-text">
                                            Debe tener de 3 a 20 caracteres.
                                        </span>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-danger" value="Registrarse">Registrarse</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="incio" tabindex="-1" aria-labelledby="inicio" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Inicio de Sesión</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="InicioCTO?opc=iniciar" method="POST">
                                <div class="mb-3">
                                    <label for="email" class="col-form-label">Email</label>
                                    <input class="form-control" type="email" name="email" placeholder="user@email.com" required="true" maxlength="50" autofocus="true"/>
                                </div>
                                <div class="mb-3">
                                    <label for="contrasena" class="col-form-label">Contraseña</label>
                                    <input class="form-control" type="password" name="contrasena" id="contrasena" required="true" maxlength="20"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-danger" value="Iniciar Sesión">Iniciar Sesión</button>
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
