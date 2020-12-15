import UI.UIAltaUsuario;
import UI.UIPrincipal;
import gestores.GestorUsuario;

/**
 *
 * @author Maxi
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) { 
    	GestorUsuario.obtenerUsuarioActual();
    	UIPrincipal.iniciar();
    }
    
}
