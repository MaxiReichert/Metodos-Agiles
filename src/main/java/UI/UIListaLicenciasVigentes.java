package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.DTOLicencia;
import gestores.GestorLicencia;

public class UIListaLicenciasVigentes {
	private JFrame frmListaLicenciasVigentes;
	private DefaultTableModel modelo;
	private JTable tablaLicencias;
	private List<DTOLicencia> listaLicencias;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	int criterio=1;
	GestorLicencia gestorL= GestorLicencia.getInstance();
	
	public UIListaLicenciasVigentes() {
		initialize();
		this.frmListaLicenciasVigentes.setVisible(true);
	}
	
	private void initialize() {
		frmListaLicenciasVigentes= new JFrame();
		frmListaLicenciasVigentes.setTitle("Listado Licencias Expiradas");
		frmListaLicenciasVigentes.setBounds(100,100,800,600);
		frmListaLicenciasVigentes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListaLicenciasVigentes.getContentPane().setLayout(null);
		
		modelo= new DefaultTableModel();
		ButtonGroup radioGroup= new ButtonGroup();
		
		//creo los radio buttons
		JRadioButton rbNomApe= new JRadioButton("Nombre y Apellido");
		rbNomApe.setBounds(50,50,150,20);
		rbNomApe.setSelected(true);
		rbNomApe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listaLicencias=gestorL.obtenerLicenciasVigentesActivas(1);
					for(int i=0; i<modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}
					//agrego las filas a de encabezado
					Object[] fila= new Object[7];
					fila[0]="Tipo Licencia";
					fila[1]="Fecha Otorgamiento";
					fila[2]="Fecha Vencimiento";
					fila[3]="Documento";
					fila[4]="Nombre Titular";
					fila[5]="Grupo Sanguineo";
					fila[6]="Donante";
					modelo.addRow(fila);
					//completo la tabla
					for(DTOLicencia dto: listaLicencias) {
						fila= new Object[7];
						fila[0]=dto.getTipo();
						fila[1]=dateFormat.format(dto.getFechaOtor());
						fila[2]=dateFormat.format(dto.getFechaVenc());
						fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
						fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
						fila[5]=dto.getTitular().getGrupoS()+" "+dto.getTitular().getFactorS();
						if(dto.getTitular().getDonador())
							fila[6]="SI";
						else
							fila[6]="NO";
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		frmListaLicenciasVigentes.getContentPane().add(rbNomApe);
		
		JRadioButton rbGrupo= new JRadioButton("Grupo Sanguineo");
		rbGrupo.setBounds(200,50,150,20);
		rbGrupo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listaLicencias=gestorL.obtenerLicenciasVigentesActivas(2);
					for(int i=0; i<modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}
					//agrego las filas a de encabezado
					Object[] fila= new Object[7];
					fila[0]="Tipo Licencia";
					fila[1]="Fecha Otorgamiento";
					fila[2]="Fecha Vencimiento";
					fila[3]="Documento";
					fila[4]="Nombre Titular";
					fila[5]="Grupo Sanguineo";
					fila[6]="Donante";
					modelo.addRow(fila);
					//completo la tabla
					for(DTOLicencia dto: listaLicencias) {
						fila= new Object[7];
						fila[0]=dto.getTipo();
						fila[1]=dateFormat.format(dto.getFechaOtor());
						fila[2]=dateFormat.format(dto.getFechaVenc());
						fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
						fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
						fila[5]=dto.getTitular().getGrupoS()+" "+dto.getTitular().getFactorS();
						if(dto.getTitular().getDonador())
							fila[6]="SI";
						else
							fila[6]="NO";
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		frmListaLicenciasVigentes.getContentPane().add(rbGrupo);
		
		JRadioButton rbFactor= new JRadioButton("Factor");
		rbFactor.setBounds(350,50,100,20);
		rbFactor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listaLicencias=gestorL.obtenerLicenciasVigentesActivas(3);
					for(int i=0; i<modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}
					//agrego las filas a de encabezado
					Object[] fila= new Object[7];
					fila[0]="Tipo Licencia";
					fila[1]="Fecha Otorgamiento";
					fila[2]="Fecha Vencimiento";
					fila[3]="Documento";
					fila[4]="Nombre Titular";
					fila[5]="Grupo Sanguineo";
					fila[6]="Donante";
					modelo.addRow(fila);
					//completo la tabla
					for(DTOLicencia dto: listaLicencias) {
						fila= new Object[7];
						fila[0]=dto.getTipo();
						fila[1]=dateFormat.format(dto.getFechaOtor());
						fila[2]=dateFormat.format(dto.getFechaVenc());
						fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
						fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
						fila[5]=dto.getTitular().getGrupoS()+" "+dto.getTitular().getFactorS();
						if(dto.getTitular().getDonador())
							fila[6]="SI";
						else
							fila[6]="NO";
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		frmListaLicenciasVigentes.getContentPane().add(rbFactor);
		
		JRadioButton rbDonante= new JRadioButton("Donante");
		rbDonante.setBounds(450,50,100,20);
		rbDonante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listaLicencias=gestorL.obtenerLicenciasVigentesActivas(4);
					for(int i=0; i<modelo.getRowCount(); i++) {
						modelo.removeRow(i);
					}
					//agrego las filas a de encabezado
					Object[] fila= new Object[7];
					fila[0]="Tipo Licencia";
					fila[1]="Fecha Otorgamiento";
					fila[2]="Fecha Vencimiento";
					fila[3]="Documento";
					fila[4]="Nombre Titular";
					fila[5]="Grupo Sanguineo";
					fila[6]="Donante";
					modelo.addRow(fila);
					//completo la tabla
					for(DTOLicencia dto: listaLicencias) {
						fila= new Object[7];
						fila[0]=dto.getTipo();
						fila[1]=dateFormat.format(dto.getFechaOtor());
						fila[2]=dateFormat.format(dto.getFechaVenc());
						fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
						fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
						fila[5]=dto.getTitular().getGrupoS()+" "+dto.getTitular().getFactorS();
						if(dto.getTitular().getDonador())
							fila[6]="SI";
						else
							fila[6]="NO";
						modelo.addRow(fila);
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		frmListaLicenciasVigentes.getContentPane().add(rbDonante);
		
		// los añado al grupo para que sean excluyentes
		radioGroup.add(rbNomApe);
		radioGroup.add(rbGrupo);
		radioGroup.add(rbFactor);
		radioGroup.add(rbDonante);
		
		//creo el modelo para la tabla
		modelo.addColumn("Tipo Licencia");
		modelo.addColumn("Fecha Otorgamiento");
		modelo.addColumn("Fecha Vencimiento");
		modelo.addColumn("Documento");
		modelo.addColumn("Nombre Titular");
		modelo.addColumn("Grupo Sanguineo");
		modelo.addColumn("Donante");
		
		//label titulo
		JLabel titulo= new JLabel("LISTA DE LICENCiAS VIGENTES");
		titulo.setBounds(100, 10, 600, 20);
		titulo.setFont(new Font("Serif", Font.BOLD, 15));
		frmListaLicenciasVigentes.add(titulo);
		
		
		
		try {
			listaLicencias=gestorL.obtenerLicenciasVigentesActivas(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//agrego las filas a de encabezado
		Object[] fila= new Object[7];
		fila[0]="Tipo Licencia";
		fila[1]="Fecha Otorgamiento";
		fila[2]="Fecha Vencimiento";
		fila[3]="Documento";
		fila[4]="Nombre Titular";
		fila[5]="Grupo Sanguineo";
		fila[6]="Donante";
		modelo.addRow(fila);
		//completo la tabla
		for(DTOLicencia dto: listaLicencias) {
			fila= new Object[7];
			fila[0]=dto.getTipo();
			fila[1]=dateFormat.format(dto.getFechaOtor());
			fila[2]=dateFormat.format(dto.getFechaVenc());
			fila[3]=dto.getTitular().getTipoDoc()+": "+dto.getTitular().getNroDoc();
			fila[4]=dto.getTitular().getApellido()+", "+dto.getTitular().getNombre();
			fila[5]=dto.getTitular().getGrupoS()+" "+dto.getTitular().getFactorS();
			if(dto.getTitular().getDonador())
				fila[6]="SI";
			else
				fila[6]="NO";
			modelo.addRow(fila);
		}
		//mestro la tabla
		tablaLicencias= new JTable(modelo);
		tablaLicencias.setBounds(90, 70, 600, 400);
		frmListaLicenciasVigentes.add(tablaLicencias);
		
		JButton btnAtras = new JButton("Atrás"); //boton atrás, FALTA UI MENU PARA ENLAZAR
		btnAtras.setBounds(150, 510, 181, 30);
		frmListaLicenciasVigentes.getContentPane().add(btnAtras);
	}
}
