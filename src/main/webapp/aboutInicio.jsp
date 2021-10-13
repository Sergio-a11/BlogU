<%-- 
    Document   : aboutInicio
    Created on : 23/06/2021, 09:08:31 PM
    Author     : PC
--%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Nosotros</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="img/logo.png"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    </head>
    <body>
     <div class="container-fluid">     
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
         <div class="container-md">
             <h1 class="mt-3">¿Por qué realizamos este proyecto?</h1>
             <p style="text-align: justify;">Dadas las inconformidades del país tras las decisiones del presidente y su gabinete frente a la situación del país, la gran mayoría de los ciudadanos presenta su peticiones por medio de marchas y plantones que escalan un paro a nivel nacional. Dentro del paro nacional las instituciones educativas de nivel superior de carácter público, deciden apoyar la posición de manifestarse frente a la coyuntura y entran en un cese indefinido de actividades.<br>
             Como proyecto curricular se toma la decisión de establecer una metodología de currículo alterno donde se desarrollan actividades relacionadas al paro y con énfasis en la temática de cada materia, particularmente en la materia de "Programación  Avanzada" se decide crear un blog para compartir información relacionada con el paro nacional.</p>
             <h1>¿Como se desarrollo el proyecto?</h1>
             <p style="text-align: justify;">Una vez planteada la idea inicial, con la ayuda del docente para aterrizar la idea se procedió a investigar sobre las herramientas necesarias para desarrollar el Blog. Dado que el lenguaje de programación en el que estábamos desarrollando en la matrería era <a href="https://www.oracle.com/java/technologies/" target="_blank"><span class="badge rounded-pill bg-danger">JAVA</span></a>, procedimos a buscar un host que  admitiera código en dicho lenguaje,  consultando en la web encontramos la pagina de <a href="https://www.heroku.com" target="_blank"><span class="badge rounded-pill bg-danger">HEROKU</span></a> que permitía la integración con Java y bases de datos en <a href="https://www.postgresql.org" target="_blank"><span class="badge rounded-pill bg-danger">POSGRESQL</span></a>, tecnología vista durante la carrera.
                 <br>Cuando ya teníamos la lista de Herramientas necesarias empezamos con el desarrollo del proyecto y por recomendación de compañeros de otros semestres usamos <a href="https://git-scm.com" target="_blank"><span class="badge rounded-pill bg-danger">GIT</span></a> desde el inicio del proyecto para llevar un manejo efectivo y fácil de las versiones, además de que Heroku permite cargar el código del proyecto desde un repositorio en <a href="https://github.com" target="_blank"><span class="badge rounded-pill bg-danger">GITHUB</span></a>.
             <br>El proyecto inicio su desarrollo el 18 de mayo de 2021, según el 'commit' inicial en GitHub , durante el desenlace del proyecto  se presentaron necesidades para cumplir ideas o funcionalidades que como estudiantes a inicio de semestre no saiamos como resolver por lo cual tras investigar en internet y no comprender, el profesor de la materia en pro de cumplir con el currículo alterno nos ofreció tutorías durante las horas de clase para resolver dudas y darnos ayudas técnica con los temas desconocidos.</p>
             <div class="card-group row row-cols-1 row-cols-md-2 g-4">
             <div class="col">    
             <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                  <div class="col-md-4">
                      <img src="img/Perfil-Checho.jpg" class="img-fluid rounded-start" alt="Foto de perfil de Sergio Cruz">
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title">Sergio Andrés Cruz Guerrero</h5>
                      <p class="card-text" style="text-align: justify;">Estudiante de la Universidad Distrital, en el proyecto curricular de Sistematizacion de datos, co-autor de un articulo enfocado en asistentes virtuales y su influencia en empresas pequeñas <a href="https://revistas.udistrital.edu.co/index.php/vinculos/article/view/16874"><span class="badge rounded-pill bg-danger">Articulo aqui</span></a>, posee un certificado tecnico articulado de diseño e integración de multimedia del SENA y un certificado de diseño de paginas web.</p>
                      <p class="card-text"><small class="text-muted">cz.andrs@gmail.com - <a href="https://github.com/Sergio-a11"><span class="badge rounded-pill bg-dark">GITHUB</span></a></small></p>
                    </div>
                  </div>
                </div>
            </div>
            </div>
            <div class="col">      
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                  <div class="col-md-4">
                      <img src="img/Perfil-Daniel.jpg" class="img-fluid rounded-start" alt="Foto de perfil de Daniel Torres">
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h5 class="card-title">Daniel Mauricio Torres Martinez</h5>
                      <p class="card-text" style="text-align: justify;">Estudiante de la Universidad Distrital, en el proyecto curricular de Sistematizacion de datos, co-autor de un articulo enfocado en asistentes virtuales y su influencia en empresas pequeñas <a href="https://revistas.udistrital.edu.co/index.php/vinculos/article/view/16874"><span class="badge rounded-pill bg-danger">Articulo aqui</span></a>, y es pertenciente a un semillero de investigacion enfocado en videojuegos educativos e investigativos.</p>
                      <p class="card-text"><small class="text-muted">dmtm1404@gmail.com - <a href="https://github.com/DanndxFull"><span class="badge rounded-pill bg-dark">GITHUB</span></a></small></p>
                    </div>
                  </div>
                </div>
            </div>
            </div>    
            </div>
         </div> 
     </div>     
    <div class="container-md">
        <div>
            <hr class="dropdown-divider">
            <h2>Contacto</h2>
        </div>  
      <div class="row"> 
            <div class="col-12 mb-3">
                <form action="Contacto?usuario=${user.getId()}" method="POST">
                    <div class="mb-3">
                        <label for="Asunto" class="col-form-label">Asunto:</label>
                        <input class="form-control" type="text" name="Asunto" placeholder="Asunto: aqui" required="true" maxlength="70"/>
                    </div>
                    <div class="mb-3">
                        <label for="contenido" class="col-form-label">Contenido:</label>
                        <textarea class="form-control" name="contenido" placeholder="Contenido aqui" required="true" style="height: 150px"></textarea>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-danger" value="Enviar">Enviar</button>
                    </div>
                </form>
            </div>
        </div>  
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
    </body>
</html>
