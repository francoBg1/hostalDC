/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.Conexion;
import Modelo.Entidades.Orden_compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Franco
 */
public class DAOorden_compra {

    public String ingresarOrden(Orden_compra ord) {
        Conexion c = new Conexion();
        String rs;
        boolean resultado;
        try {
            //inserto datos de la orden
            Connection con = c.getConnection();
            String query1 = "INSERT INTO ORDEN_compra VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query1);
            ps = con.prepareStatement(query1);
            ps.setInt(1, ord.getCodigo_compra());
            ps.setDate(2, ord.getF_inicio());
            ps.setDate(3, ord.getF_fin());
            ps.setDate(4, ord.getF_compra());
            ps.setInt(5, ord.getPrecio_total());
            ps.setInt(6, ord.getTipo_hab());
            ps.setInt(7, ord.getTipo_min());
            ps.setString(8, ord.getCliente_rut_emp());
            ps.setInt(9, 0);

            resultado = ps.executeUpdate() == 1;
            ps.close();

            if (resultado = true) {
                rs = "Se ingreso exitosamente";
            } else {
                rs = "No se pudo ingresar";
            }
        } catch (SQLException ex) {
            rs = ex.toString() + "  en metodo ingresar orden, DAOorden_compra Rut:" + ord.getCliente_rut_emp() + " ha:" + ord.getTipo_hab() + " mi:" + ord.getTipo_min();
        }
        return rs;
    }

    public ArrayList<Orden_compra> ObtenerCompras() {
        ArrayList<Orden_compra> arrayOrd = new ArrayList<Orden_compra>();
        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();
            String query = "select * from orden_compra order by CODIGO_COMPRA";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orden_compra ord = new Orden_compra(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8),rs.getInt(9));
                arrayOrd.add(ord);
            }
            ps.close();
            return arrayOrd;
        } catch (SQLException ex) {
            return null;
        }
    }

    public int buscaridMax() {
        Conexion c = new Conexion();
        int a = 0;
        try {
            Connection con = c.getConnection();
            String query = "select codigo_compra from ORDEN_COMPRA where CODIGO_COMPRA= (select max(CODIGO_COMPRA) from ORDEN_COMPRA)";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = rs.getInt("codigo_compra");
            }
            ps.close();
        } catch (SQLException ex) {
            return 404;
        }
        return a;
    }

    public String eliminarCompra(int codigo) {
        boolean resultado = false;
        String r = "false";

        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();

            String query = "delete from ORDEN_COMPRA where CODIGO_COMPRA=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codigo);
            resultado = ps.executeUpdate() == 1;
            r = "true";
            ps.close();

        } catch (SQLException ex) {
            r = ex.toString();
        }

        return r;
    }

    public String limpiarCompras(String rut) {
        boolean resultado = false;
        String r = "false";

        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();

            String query = "delete from ORDEN_COMPRA where CLIENTE_RUT_EMP=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, rut);
            resultado = ps.executeUpdate() == 1;
            r = "true";
            ps.close();

        } catch (SQLException ex) {
            r = "Exception en DAOorden_compra/limpiarCompras err:" + ex.toString() + " fin/.";
        }

        return r;
    }

    public String actualizarPrecio(int codigo, int precio) {
        boolean resultado = false;
        String r = "false";

        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();

            String query = " update ORDEN_COMPRA set precio_total=" + precio + " where CODIGO_COMPRA=" + codigo;
            PreparedStatement ps = con.prepareStatement(query);
            resultado = ps.executeUpdate() == 1;
            r = "true";
            ps.close();

        } catch (SQLException ex) {
            r = "Exception en DAOorden_compra/actualizarPrecio err:" + ex.toString() + " fin/.";
        }

        return r;
    }
    
     public String asignarFactura(int codigo_factura, int codigo_compra ) {
        boolean resultado = false;
        String r = "false";

        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();

            String query = "update ORDEN_COMPRA set FACTURA_COD_FACTURA="+codigo_factura+" where CODIGO_COMPRA="+codigo_compra;
            PreparedStatement ps = con.prepareStatement(query);
            resultado = ps.executeUpdate() == 1;
            r = "true";
            ps.close();

        } catch (SQLException ex) {
            r = "Exception en DAOorden_compra/asignarFactura err:" + ex.toString() + " fin/.";
        }

        return r;
    }
     
      public String asignarFacturaMultiple(int codigo_factura, String rut ) {
        boolean resultado = false;
        String r = "false";

        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();

            String query = "update ORDEN_COMPRA set FACTURA_COD_FACTURA="+codigo_factura+" where CLIENTE_RUT_EMP='"+rut+"'";
            PreparedStatement ps = con.prepareStatement(query);
            resultado = ps.executeUpdate() == 1;
            r = "true";
            ps.close();

        } catch (SQLException ex) {
            r = "Exception en DAOorden_compra/asignarFacturaMultiple err:" + ex.toString() + " fin/.";
        }

        return r;
    }
      
      
     public ArrayList<Orden_compra> ObtenerComprasPorFactura(int cod_factura) {
        ArrayList<Orden_compra> arrayOrd = new ArrayList<Orden_compra>();
        try {
            Conexion c = new Conexion();
            Connection con = c.getConnection();
            String query = "select * from orden_compra where FACTURA_COD_FACTURA="+cod_factura+" order by CODIGO_COMPRA";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orden_compra ord = new Orden_compra(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8),rs.getInt(9));
                arrayOrd.add(ord);
            }
            ps.close();
            return arrayOrd;
        } catch (SQLException ex) {
            return null;
        }
    }  
      
      
     

}
