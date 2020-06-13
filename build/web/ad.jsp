<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Entidades.Usuario" %>
<%@page import="Modelo.Manejadoras.Manejadora_usuario" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        %>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="css/Index.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">


    </head>
    <body class="letras">
        <h1 >Admin</h1>
        <a class="btn btn-outline-danger" href="ControlUsuario" role="button">Ir al login</a>

        <hr>
        <h1>
            <%
                Manejadora_usuario mane = new Manejadora_usuario();
            %>
        </h1>

        <h1 class="text-center">Usuarios y claves</h1>

        <hr>







        <div class="container col-lg-10 col-sm-12 col-xs-12 containercolor d-flex flex-column bd-highlight mb-3 text-center">
            <div class="accordion" id="accordionExample">
                <div class="card">
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Clientes
                    </button>
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                        <div class="card-body">
                            <!-- -->
                            <div class="container table-sm  col-lg-10 col-sm-12 col-xs-5 mb-5 mar"> 
                                <table class="table table-hover ">
                                    <thead>
                                        <tr>
                                            <th scope="col">idUsuario</th>
                                            <th scope="col">Usuario</th>
                                            <th scope="col">Clave</th>
                                            <th scope="col">Correo</th>
                                            <th scope="col">Permiso</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            try {
                                                for (int i = 0; i < mane.getUsu().size(); i++) {
                                                    if (mane.getUsu().get(i).getTipo_usuario_permiso() == 4) {
                                                        out.print("<tr class='table-dark'>");
                                                        out.print("<td>" + mane.getUsu().get(i).getId_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getNom_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getClave() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getCorreo() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getTipo_usuario_permiso() + "</td>"
                                                                + "</tr>");
                                                    } else {
                                                        if (mane.getUsu().size() == 0) {
                                                            out.print("<td>" + "Sin Clientes registrados" + "</td>");
                                                        }
                                                    }

                                                }
                                            } catch (Exception e) {
                                                out.print(e);
                                            }//
                                        %>
                                    </tbody>
                                </table>
                            </div>  
                            <!-- -->  
                        </div>
                    </div>
                </div>


                <div class="card">
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Proveedores
                    </button>
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body">

                            <div class="container table-sm  col-lg-10 col-sm-12 col-xs-5 mb-5 mar"> 
                                <table class="table table-hover ">
                                    <thead>
                                        <tr>
                                            <th scope="col">idUsuario</th>
                                            <th scope="col">Usuario</th>
                                            <th scope="col">Clave</th>
                                            <th scope="col">Correo</th>
                                            <th scope="col">Permiso</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            try {
                                                for (int i = 0; i < mane.getUsu().size(); i++) {
                                                    if (mane.getUsu().get(i).getTipo_usuario_permiso() == 3) {
                                                        out.print("<tr class='table-dark'>");
                                                        out.print("<td>" + mane.getUsu().get(i).getId_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getNom_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getClave() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getCorreo() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getTipo_usuario_permiso() + "</td>"
                                                                + "</tr>");
                                                    } else {
                                                        if (mane.getUsu().size() == 0) {
                                                            out.print("<td>" + "Sin proveedores registrdos" + "</td>");
                                                        }
                                                    }

                                                }
                                            } catch (Exception e) {
                                                out.print(e);
                                            }//
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="card">
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <button class="btn btn-outline-secondary" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Empleados
                    </button>
                    <!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- --><!-- -->
                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                        <div class="card-body">

                            <div class="container table-sm  col-lg-10 col-sm-12 col-xs-5 mb-5 mar"> 
                                <table class="table table-hover ">
                                    <thead>
                                        <tr>
                                            <th scope="col">idUsuario</th>
                                            <th scope="col">Usuario</th>
                                            <th scope="col">Clave</th>
                                            <th scope="col">Correo</th>
                                            <th scope="col">Permiso</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            try {
                                                for (int i = 0; i < mane.getUsu().size(); i++) {
                                                    if (mane.getUsu().get(i).getTipo_usuario_permiso() == 2) {
                                                        out.print("<tr class='table-dark'>");
                                                        out.print("<td>" + mane.getUsu().get(i).getId_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getNom_usuario() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getClave() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getCorreo() + "</td>"
                                                                + "<td>" + mane.getUsu().get(i).getTipo_usuario_permiso() + "</td>"
                                                                + "</tr>");
                                                    } else {
                                                        if (mane.getUsu().size() == 0) {
                                                            out.print("<td>" + "Sin empleados registrados" + "</td>");
                                                        }
                                                    }

                                                }
                                            } catch (Exception e) {
                                                out.print(e);
                                            }//
                                        %>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <hr>
        </div>

        <div class='container centrar'>
            <div class='row'>
                <img src='https://image.flaticon.com/icons/png/512/14/14598.png'  width='120' height='120'/> 
                <div>
                    <a href='select.jsp'><hr><h1>Agregar usuario</h1></a>
                </div>
                <strong>HostalDC</strong></div>
        </div>

        <!--Bootstrap-->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>



    </body>
</html>