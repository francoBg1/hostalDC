/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Entidades.Orden_compra;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.UsuarioEmpleado;
import Modelo.Manejadoras.Manejadora_empleado;
import Modelo.Manejadoras.Manejadora_orden;
import Modelo.Manejadoras.Manejadora_usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Franco
 */
@WebServlet(name = "ControlEmp", urlPatterns = {"/ControlEmp"})
public class ControlEmp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        PrintWriter out = response.getWriter();
        Manejadora_usuario mane_usu = new Manejadora_usuario();
        Manejadora_empleado mane_emp = new Manejadora_empleado();

        String accion = request.getParameter("accion");

        if (accion.equals("recepcionar")) {
            //request.getRequestDispatcher("empleado_home.jsp").forward(request, response);
            String rut = request.getParameter("txt_rut");
            Manejadora_orden ord = new Manejadora_orden();
            ArrayList<Orden_compra> arrayC = ord.listaComprasPorRUT(rut);

            if (arrayC.size() > 0) {
                request.setAttribute("rut", rut);

                RequestDispatcher rd = request.getRequestDispatcher("recepcion.jsp");
                rd.include(request, response);
            }else{
                  out.print("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.print("<script>");
                out.print("$(document).ready(function(){");
                out.print("swal('No se encontró el rut indicado','Coincidencias: " + arrayC.size() + "','error');");
                out.print("});");
                out.print("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("empleado_home.jsp");
                rd.include(request, response);
            }

        }

        //REGISTRO CLIENTE
        if (accion.equals("RegistrarEmple")) {
            int id = mane_usu.idMax() + 1;
            String nom_usu = request.getParameter("txt_nom_emp");
            String textoSinEncriptar = request.getParameter("txt_clave");
             String clave = DigestUtils.md5Hex(textoSinEncriptar);
            String correo = request.getParameter("txt_correo_emp");
            int tipo = 2;

            String rut = request.getParameter("txt_rut_emp");
            String nom = request.getParameter("txt_nom_emp");
            String ape = request.getParameter("txt_ape_emp");

            Usuario u = new Usuario(id, nom_usu, clave, correo, tipo, 0);
            UsuarioEmpleado d = new UsuarioEmpleado(rut, nom, ape, id);

            String rs = mane_emp.ingresarEmpleadoCompleto(u, d);
            if (rs.compareToIgnoreCase("Se ingreso exitosamente") == 0) {
                out.print("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.print("<script>");
                out.print("$(document).ready(function(){");
                out.print("swal('Bien, " + nom + "!','" + rs + "','success');");
                out.print("});");
                out.print("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.include(request, response);
            } else {
                out.print("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
                out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
                out.print("<script>");
                out.print("$(document).ready(function(){");
                out.print("swal('Ocurrio algo','intenta otra vez por favor, " + rs + "','error');");
                out.print("});");
                out.print("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
                rd.include(request, response);
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
