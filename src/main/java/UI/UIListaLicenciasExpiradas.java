package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private static DefaultTableModel modelo;
	private static JTable tablaLicencias;
	private static List<DTOLicencia> listaLicencias;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	
	
	public static void iniciar() {
		final Marco frmListaLicenciasExpiradas = new Marco(800,600,"Gestor de licencias - Listado licencias expiradas");
		frmListaLicenciasExpiradas.getContentPane().setLayout(null);
		frmListaLicenciasExpiradas.setLocationRelativeTo(null);
		frmListaLicenciasExpiradas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			   @Override
			public void actionPerformed(ActionEvent e) {
				UIPrincipal.iniciar();
				 frmListaLicenciasExpiradas.dispose();
			}
		});
		btnAtras.setBounds(545, 493, 89, 23);
		frmListaLicenciasExpiradas.getContentPane().add(btnAtras);
		}
	}

