/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOorden_compra;
import Modelo.Entidades.Huesped;
import Modelo.Entidades.Orden_compra;
import Modelo.Manejadoras.Manejadora_hab;
import Modelo.Manejadoras.Manejadora_huesped;
import Modelo.Manejadoras.Manejadora_minuta;
import Modelo.Manejadoras.Manejadora_orden;
import Modelo.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Franco
 */
@WebServlet(name = "AgregarHuesped", urlPatterns = {"/AgregarHuesped"})
public class AgregarHuesped extends HttpServlet {

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
        DAOorden_compra dao_ord = new DAOorden_compra();
        String accion = request.getParameter("accion");
        Util util = new Util();

        Manejadora_huesped mane_hue = new Manejadora_huesped();
        try {
            if (accion.equals("Agregar")) {
                HttpSession hue = request.getSession();

                java.util.Date utilDate = new java.util.Date(); //fecha actual
                java.sql.Date hoy = new java.sql.Date(utilDate.getTime());

                //Variables
                String ha = "";
                String co = request.getParameter("select_co");
                int se_ha = 0;
                int se_co = util.id_min(co);

                Date f_inicio = Date.valueOf(request.getParameter("txt_f_inicio"));
                Date f_fin = Date.valueOf(request.getParameter("txt_f_fin"));
                Date f_compra = hoy;

                int codigo_compra = dao_ord.buscaridMax() + 1;
                String ruta = request.getParameter("txt_rut");
                Orden_compra ord = new Orden_compra(codigo_compra, f_inicio, f_fin, f_compra, 0, se_ha, se_co, ruta,0);
                String rs = dao_ord.ingresarOrden(ord);

                if (rs == "Se ingreso exitosamente") {
                    hue.setAttribute("cod", codigo_compra);

                    hue.setAttribute("desde", "cliente_home.jsp");
                    hue.setAttribute("pag", "huesped.jsp");
                    hue.setAttribute("titulo", "Compra agregada");
                    hue.setAttribute("detalle", "agregue huespedes para validar la compra");
                    hue.setAttribute("sms", "compra registrada");
                    hue.setAttribute("tip", "success");
                    response.sendRedirect("true.jsp");

                } else {
                    out.print(rs + " -----ha:" + ha + " ------co:" + co);
                }
            }
        } catch (Exception e) {
            String error = "";
            if (e.toString().compareToIgnoreCase("Java.Lang.NullPointerException") == 0) {
                error = "Punto vacío";
            }

            HttpSession hue = request.getSession();
            hue.setAttribute("desde", "cliente_home.jsp");
            hue.setAttribute("pag", "cliente_home.jsp");
            hue.setAttribute("titulo", "Algo ocurrió al ingresar los datos");
            hue.setAttribute("detalle", "intente de nuevo");
            hue.setAttribute("sms", "en 'agregar'");
            hue.setAttribute("tip", "error");
            response.sendRedirect("true.jsp");

        }
///////////////////////////////////////

        try {
            if (accion.equals("AgregarHue")) {
                String rut = "";
                String nombre = "";
                String apellido = "";
                int cod = 4040;
                String num_hab = "a";

                //int cod_com = Integer.parseInt(hue.getAttribute("codigo_com").toString());
                //manejadoras implicadas
                Manejadora_orden mane_ord = new Manejadora_orden();
                Manejadora_huesped mane = new Manejadora_huesped();
                Manejadora_minuta ma_mi = new Manejadora_minuta();
                Manejadora_hab mane_ha = new Manejadora_hab();
                //Manejadora_huesped mane_hue = new Manejadora_huesped();
                try {

                    rut = request.getParameter("txt_rut");
                    nombre = request.getParameter("txt_nombre");
                    apellido = request.getParameter("txt_apellido");

                    //obtenido el codigo de compra
                    cod = Integer.parseInt(request.getParameter("txt_codigo"));
                    //obtenido el tipo de  habitacion con el numero extraido de una cadena de caracteres
                    num_hab = request.getParameter("select_habitacion");
                    int numero_ha = Integer.parseInt(num_hab.substring(0, 1));
                    int tipo_ha = mane_ha.getHab().get(numero_ha).getTIPO_HAB_id_tipo_hab();
                    //

                    //devuelve la orden de compra completa
                    Orden_compra ord = mane_ord.devolverCompraCompleta(cod);
                    //tipo de min de la compra obtenida
                    int tipo_mi = ord.getTipo_min();
                    //

                    //necesarios para el calculo de precio
                    int precio_mi = ma_mi.valorMinuta(tipo_mi);
                    int precio_ha = mane_ha.valorHab(tipo_ha);
                    int precio_actual = ord.getPrecio_total();

                    int huespedes = 1;
                    for (int i = 0; i < mane.getHuesped().size(); i++) {
                        if (mane.getHuesped().get(i).getORDEN_compra_codigo_compra() == cod) {
                            huespedes = 1;
                        }
                    }

                    precio_actual += huespedes * (precio_mi + precio_ha);

                    Huesped hues = new Huesped(rut, nombre, apellido, cod, numero_ha);
                    mane_ord.actualizarPrecio(cod, precio_actual);
                    String rs = mane_hue.ingresarHuesped(hues);

                    HttpSession hue = request.getSession();
                    hue.setAttribute("cod", cod);
                    if (rs.compareToIgnoreCase("Se ingreso exitosamente") == 0) {

                        hue.setAttribute("desde", "huesped.jsp");
                        hue.setAttribute("pag", "huesped.jsp");
                        hue.setAttribute("titulo", "Huesped agregado");
                        hue.setAttribute("detalle", "codigo de compra" + cod);
                        hue.setAttribute("sms", "Se ingreso exitosamente ♠");
                        hue.setAttribute("tip", "success");
                        response.sendRedirect("true.jsp");

                    } else {

                        hue.setAttribute("desde", "huesped.jsp");
                        hue.setAttribute("pag", "huesped.jsp");
                        hue.setAttribute("titulo", "Huesped agregado");
                        hue.setAttribute("detalle", "codigo de compra" + cod);
                        hue.setAttribute("sms", rs);
                        hue.setAttribute("tip", "success");
                        response.sendRedirect("true.jsp");

                    }
                } catch (Exception e) {
                    out.print("|" + rut + "-" + nombre + "-" + apellido + "-" + cod + "- |||num|||:" + num_hab + " ||| error:" + e + "|");
                }

            }
        } catch (Exception e) {
            String error = "";
            if (e.toString().compareToIgnoreCase("Java.Lang.NullPointerException") == 0) {
                error = "Punto vacío";
            }
         
            HttpSession hue = request.getSession();
            hue.setAttribute("desde", "huesped.jsp");
            hue.setAttribute("pag", "huesped.jsp");
            hue.setAttribute("titulo", "Algo ha salido mal");
            hue.setAttribute("detalle", "por favor, intenta agregar huesped nuevamente");
            hue.setAttribute("sms", "error en servlet agregarHuesped  + " + e);
            hue.setAttribute("tip", "error");
            response.sendRedirect("true.jsp");

        }
        ///////////////////////////////////////////////////////
        try {

            if (accion.equals("Cancelar")) {
                HttpSession hue = request.getSession();

                int codigo = Integer.parseInt(hue.getAttribute("cod").toString());
                String rs = mane_hue.cancelar(codigo);
                if (rs == "realizado") {
                    
                       hue.setAttribute("desde", "huesped.jsp");
                    hue.setAttribute("pag", "cliente_home.jsp");
                    hue.setAttribute("titulo", "Eliminado");
                    hue.setAttribute("detalle", "Se ha eliminado el " +  codigo);
                    hue.setAttribute("sms", rs);
                    hue.setAttribute("tip", "success");
                    response.sendRedirect("true.jsp");
                    
                } else {
                    hue.setAttribute("desde", "huesped.jsp");
                    hue.setAttribute("pag", "huesped.jsp");
                    hue.setAttribute("titulo", "No se puede eliminar");
                    hue.setAttribute("detalle", "Vuelva a intentarlo");
                    hue.setAttribute("sms", rs);
                    hue.setAttribute("tip", "error");
                    response.sendRedirect("true.jsp");
                }
            }
            ///////////////////////////////////////////////////////
         

        } catch (Exception e) {
            String error = "";
            if (e.toString().compareToIgnoreCase("Java.Lang.NullPointerException") == 0) {
                error = "Punto vacío";
            }
            request.setAttribute("desde", "cliente_home.jsp");
            request.setAttribute("pag", "cliente_home.jsp");
            request.setAttribute("titulo", "Algo ha salido mal");
            request.setAttribute("detalle", "try general ");
            request.setAttribute("sms", "try catch general de agregar huesped");
            request.setAttribute("tipo", "error");
            RequestDispatcher rd = request.getRequestDispatcher("true.jsp");
            rd.include(request, response);
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
