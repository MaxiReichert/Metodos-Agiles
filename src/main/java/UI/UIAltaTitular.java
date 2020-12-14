package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

import dto.DTOTitular;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import enumeradores.tipoDocumento;
import enumeradores.tipoLicencia;
import gestores.GestorTitular;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class UIAltaTitular {

	private JFrame frmAltaCliente;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDireccion;
	private JTextField tfGrupoSanguineo;
	private JTextField tfFactor;
	private ButtonGroup grupoDonador = new ButtonGroup();
	DTOTitular dTOT = new DTOTitular();
	GestorTitular gestorT = GestorTitular.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIAltaTitular window = new UIAltaTitular();
					window.frmAltaCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIAltaTitular() {
		initialize();
		this.frmAltaCliente.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaCliente = new JFrame();
		frmAltaCliente.setTitle("Alta cliente");
		frmAltaCliente.setBounds(100, 100, 800, 600);
		frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaCliente.getContentPane().setLayout(null);
		
		//----------COMBOBOX----------//
		final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(146, 13, 180, 22);
		comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		frmAltaCliente.getContentPane().add(comboTipoDoc);
	
		
		
		//----------TEXTFIELD----------//
		tfNroDoc = new JTextField();
		tfNroDoc.setBounds(489, 14, 181, 20);
		tfNroDoc.setColumns(10);
		tfNroDoc.addKeyListener(new KeyAdapter() { //El nro
			@Override
			   public void keyTyped(KeyEvent e)
			   {
					int max = 9;
					char caracter = e.getKeyChar();

					if(((caracter < '0') ||
							(caracter > '9')) &&
							(caracter != '\b'))
					{
						e.consume();
					}
					if(tfNroDoc.getText().length() > max) {
						e.consume();
					}
			   }
		});
		frmAltaCliente.getContentPane().add(tfNroDoc);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(220, 70, 181, 20);
		tfNombre.setColumns(10);
		tfNombre.addKeyListener(new KeyAdapter() { //El nombre tiene hasta 15 letras
			@Override
			public void keyTyped(KeyEvent e) {
				int max =15;
				char caracter = e.getKeyChar();
				if(!Character.isLetter(caracter))
				{
					e.consume();
				}
				if(tfNombre.getText().length() > max) {
					e.consume();
				}
			}
		});

		frmAltaCliente.getContentPane().add(tfNombre);
	
		
		tfApellido = new JTextField();
		tfApellido.setBounds(220, 110, 181, 20);
		tfApellido.setColumns(10);
		tfApellido.addKeyListener(new KeyAdapter() { //El apellido tiene hasta 15 letras
			@Override
			public void keyTyped(KeyEvent e) {
				int max =15;
				char caracter = e.getKeyChar();
				if(!Character.isLetter(caracter))
				{
					e.consume();
				}
				if(tfApellido.getText().length() > max) {
					e.consume();
				}
			}
		});
		frmAltaCliente.getContentPane().add(tfApellido);

		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(220, 150, 181, 20);
		tfDireccion.setColumns(10);
		tfDireccion.addKeyListener(new KeyAdapter() { //La direccion tiene hasta 30 letras
			@Override
			public void keyTyped(KeyEvent e) {
				int max =30;
				if(tfDireccion.getText().length() > max) {
					e.consume();
				}
			}
		});
		frmAltaCliente.getContentPane().add(tfDireccion);
		
		
		tfGrupoSanguineo = new JTextField();
		tfGrupoSanguineo.setBounds(220, 274, 181, 20);
		tfGrupoSanguineo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max =2;
				char caracter = e.getKeyChar();
				if(!Character.isLetter(caracter))
				{
					e.consume();
				}
				if(tfGrupoSanguineo.getText().length() > max) {
					e.consume();
				}
			}
		});
		tfGrupoSanguineo.setColumns(10);
		frmAltaCliente.getContentPane().add(tfGrupoSanguineo);

		
		
		tfFactor = new JTextField();
		tfFactor.setColumns(10);
		tfFactor.setBounds(220, 230, 181, 20);
		tfFactor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max =1;
				char caracter = e.getKeyChar();
				if(tfFactor.getText().length() > max) {
					e.consume();
				}
			}
		});
		frmAltaCliente.getContentPane().add(tfFactor);
		

		


		//----------ETIQUETAS----------//
		
;
		
		JLabel lbTipoDoc = new JLabel("Tipo documento");
		lbTipoDoc.setBounds(23, 10, 153, 22);
		lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbTipoDoc);
		
		
		JLabel lbNroDoc = new JLabel("Numero documento");
		lbNroDoc.setBounds(350, 11, 153, 22);
		lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbNroDoc);
		
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(25, 70, 151, 22);
		lbNombre.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbNombre);
		
		
		JLabel lbApellido = new JLabel("Apellido");
		lbApellido.setBounds(25, 110, 151, 22);
		lbApellido.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbApellido);
		
		JLabel lbFechaNac = new JLabel("Fecha de nacimiento");
		lbFechaNac.setBounds (25, 190, 151, 22);
		lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbFechaNac);
		
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setBounds (25, 150, 124, 22);
		lbDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbDireccion);
		
		JLabel lbClaseSolicitada = new JLabel("Clase solicitada");
		lbClaseSolicitada.setBounds (25, 310, 151, 22);
		lbClaseSolicitada.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbClaseSolicitada);
		
		JLabel lbFactorSanguineo = new JLabel("Factor sanguineo");
		lbFactorSanguineo.setBounds (25, 230, 140, 22);
		lbFactorSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbFactorSanguineo);
	
		JLabel lbGrupoSanguineo = new JLabel("Grupo sanguineo");
		lbGrupoSanguineo.setBounds (25, 270, 151, 22);
		lbGrupoSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbGrupoSanguineo);
		
		JLabel lbDonante = new JLabel("Donante de organos:");
		lbDonante.setBounds(25, 369, 174, 22);
		lbDonante.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaCliente.getContentPane().add(lbDonante);
		
		////------TEXTO DE ESTADO 
		final JLabel lbEstado = new JLabel("New label");
		lbEstado.setBounds(280, 497, 270, 14);
		lbEstado.setVisible(false);
		frmAltaCliente.getContentPane().add(lbEstado);
		
		
		//----------DATE----------//
		final JDateChooser dateCFechaNacimiento = new JDateChooser();
		dateCFechaNacimiento.setBounds(220, 190, 181, 20);
		frmAltaCliente.getContentPane().add(dateCFechaNacimiento);
		
		//----------BUTTONS----------//
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(660, 493, 89, 23);
		ActionListener AccionCrear = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				boolean completo = true;
				//Chequeo que se hayan completado los campos y seleccionado en las combo box
				//TextFields
				if (tfNroDoc.getText().length() == 0) {
					completo = false;
				} 
				if (tfNombre.getText().length() == 0) {
					completo = false;
				} 
				if (tfApellido.getText().length() == 0) {
					completo = false;
				} 
				if (tfDireccion.getText().length() == 0) {
					completo = false;
				} 
				if (tfGrupoSanguineo.getText().length() == 0) {
					completo = false;
				} 
				if (tfFactor.getText().length() == 0) {
					completo = false;
				} 
				//ComboBox
				if (comboTipoDoc.getSelectedIndex() == 0){
					completo = false;
				};
				//ComprobarFecha
				if (dateCFechaNacimiento.getDate() == null) {
					completo = false;
				}
				if (completo) { //Si todos los campos se completaron
					if(!gestorT.comprobarExistencia(tfNroDoc.getText())){ //Si no existe un titular registrado con ese Doc
						lbEstado.setVisible(true);
						//dateFormat.format(dateCFechaNacimiento.getDate());
						dTOT.setTipoDoc(comboTipoDoc.getSelectedItem().toString());
						dTOT.setNroDoc(tfNroDoc.getText());
						dTOT.setNombre(tfNombre.getText());
						dTOT.setApellido(tfApellido.getText());
						dTOT.setDireccion(tfDireccion.getText());
						dTOT.setFactorS(tfFactor.getText());
						dTOT.setGrupoS(tfGrupoSanguineo.getText());
						dTOT.setFechaNac(dateCFechaNacimiento.getDate());
						lbEstado.setText("Prodece a crear");
						try {
							gestorT.darDeAltaTitular(dTOT);
							lbEstado.setText("Titular creado");
						}
						catch(Exception ex){
							lbEstado.setText("Error al guardar el titular");
						}
						
						
					}
					else{ //Si existe titular con ese doc
						lbEstado.setVisible(true);
						lbEstado.setText("Documento ya registrado");
					}
				}
				else { //Si no estan todos completos
					lbEstado.setVisible(true);
					lbEstado.setText("Campos incompletos");
				}
			}
		};
		btnCrear.addActionListener(AccionCrear);
		frmAltaCliente.getContentPane().add(btnCrear);
		

		
		JRadioButton rbDonanteNo = new JRadioButton("No");
		rbDonanteNo.setBounds(23, 398, 64, 23);
		rbDonanteNo.setSelected(true);
		grupoDonador.add(rbDonanteNo);
		frmAltaCliente.getContentPane().add(rbDonanteNo);
		
		JRadioButton rbDonanteSi = new JRadioButton("Si");
		rbDonanteSi.setBounds(173, 398, 57, 23);
		grupoDonador.add(rbDonanteSi);
		frmAltaCliente.getContentPane().add(rbDonanteSi);
		
	}
}
