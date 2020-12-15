package UI;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.DTOLicencia;
import gestores.GestorLicencia;


public class UIListaLicenciasExpiradas {
	
	private JFrame frmListaLicenciasExpiradas;
	private DefaultTableModel modelo;
	private JTable tablaLicencias;
	private List<DTOLicencia> listaLicencias;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	
	public UIListaLicenciasExpiradas() {
		initialize();
		this.frmListaLicenciasExpiradas.setVisible(true);
	}
	
	private void initialize() {
		frmListaLicenciasExpiradas= new JFrame();
		frmListaLicenciasExpiradas.setTitle("Listado Licencias Expiradas");
		frmListaLicenciasExpiradas.setBounds(100,100,800,600);
		frmListaLicenciasExpiradas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListaLicenciasExpiradas.getContentPane().setLayout(null);
		
		modelo= new DefaultTableModel();
		modelo.addColumn("Tipo Licencia");
		modelo.addColumn("Fecha Otorgamiento");
		modelo.addColumn("Fecha Vencimiento");
		modelo.addColumn("Documento");
		modelo.addColumn("Nombre Titular");
		
		JLabel titulo= new JLabel("LISTA DE LICENCiAS EXPIRADAS");
		titulo.setBounds(100, 10, 600, 40);
		titulo.setFont(new Font("Serif", Font.BOLD, 15));
		frmListaLicenciasExpiradas.add(titulo);
		
		GestorLicencia gestorL= GestorLicencia.getInstance();
		listaLicencias=gestorL.buscarLicenciasExpiradas();
		
		Object[] fila= new Object[5];
		fila[0]="Tipo Licencia";
		fila[1]="Fecha Otorgamiento";
		fila[2]="Fecha Vencimiento";
		fila[3]="Documento";
		fila[4]="Nombre Titular";
		modelo.addRow(fila);
		
		for(DTOLicencia dto: listaLicencias) {
			fila= new Object[5];
			fila[0]=dto.getTipo();
			fila[1]=dateFormat.format(dto.getFechaOtor());
			fila[2]=dateFormat.format(dto.getFechaVenc());
			fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
			fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
			modelo.addRow(fila);
		}
		
		tablaLicencias= new JTable(modelo);
		tablaLicencias.setBounds(100, 70, 600, 400);
		frmListaLicenciasExpiradas.add(tablaLicencias);
		
		JButton btnAtras = new JButton("Atrás"); //boton atrás, FALTA UI MENU PARA ENLAZAR
		btnAtras.setBounds(150, 510, 181, 30);
		frmListaLicenciasExpiradas.getContentPane().add(btnAtras);
	}
}
