
package Modelo.Manejadoras;

import Modelo.DAO.DAOusuario_cli;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.UsuarioCli_detalle;

/**
 *
 * @author Franco
 */
public class Manejadora_cliente {

    DAOusuario_cli dao_det = new DAOusuario_cli();
    Manejadora_usuario mane_usu = new Manejadora_usuario();
    /*
    ingresar
    */
    
  
      public String ingresarClienteCompleto(Usuario usu, UsuarioCli_detalle det) {
        String nom = usu.getNom_usuario();
        String clave = usu.getClave();
        
        String i=mane_usu.ingresarUsuario(usu);

        
        if (mane_usu.obtenerUsuario(nom, clave) == null) {
            if (i.compareToIgnoreCase("1") == 0) {
                return dao_det.ingresarCliente(det) +"USUARIO: "+i;
            }else{
                return i;
            }
        } else {
            return "el usuario ya existe";
        }
        
    }

}
