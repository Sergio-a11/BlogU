<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es" xml:lang="es" xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
              <title>Crear Publicación</title>
              <link rel="icon" type="image/png" href="img/logo.png"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
            <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!--barra de navegación-->
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
            <!--contenido-->
            <div class="row">
                <div class="container mt-2">
                    <div class="row">
                        <form action="PostsCTO?opc=crear&pasteldepato=${user.getId()}" method="POST" >
                            <div class="mb-3">
                                <label for="titulo" class="col-form-label">Titulo:</label>
                                <input class="form-control" type="text" name="titulo" placeholder="Titulo aqui" required="true" maxlength="140" autocomplete="off" autofocus="true"/>
                            </div>
                            <div class="mb-3">
                                <label for="contenido" class="col-form-label">Contenido</label>
                                <textarea class="form-control" name="contenido" placeholder="Contenido aqui" required="true" style="height: 300px"></textarea>
                            </div>

                            <div class="mb-3 row palabras">
                                <label for="palabras" class="col-form-label">Palabras Claves</label>
                                <div class="col-auto"><input class="form-control" type="text" name="palabras1" placeholder="Palabra Clave" required="true" maxlength="20" autocomplete="off"/></div>
                                <div class="col-auto"><input class="form-control" type="text" name="palabras2" placeholder="Palabra Clave" maxlength="20" autocomplete="off"/></div>
                                <div class="col-auto"><input class="form-control" type="text" name="palabras3" placeholder="Palabra Clave" maxlength="20" autocomplete="off"/></div>
                                <div class="col-auto"><input class="form-control" type="text" name="palabras4" placeholder="Palabra Clave" maxlength="20" autocomplete="off"/></div>
                                <div class="col-auto"><input class="form-control" type="text" name="palabras5" placeholder="Palabra Clave" maxlength="20" autocomplete="off"/></div>

                            </div>
                            
                            <div class="mb-3">
                                <label for="multimedia" class="col-form-label">Multimedia:</label>
                                <input class="form-control" type="text" name="multimedia" placeholder="Inserte link de Youtube" maxlength="70" autocomplete="off"/>
                            </div>
                            
                            <div class="modal-footer">
                                <a href="inicio.jsp"><button type="button" class="btn btn-secondary" value="cancelar" >Cancelar</button></a>
                                <button type="submit" class="btn btn-danger" value="Publicar">Publicar</button>
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
