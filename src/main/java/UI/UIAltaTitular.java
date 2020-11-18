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

public class UIAltaTitular {

	private JFrame frmAltaCliente;
	private JTextField tfNroDoc;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDireccion;
	private JTextField tfGrupoSanguineo;
	private JTextField tfFactor;
	private ButtonGroup grupoDonador = new ButtonGroup();
	DTOTitular DTOT = new DTOTitular();
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaCliente = new JFrame();
		frmAltaCliente.setTitle("Alta cliente");
		frmAltaCliente.setBounds(100, 100, 450, 410);
		frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaCliente.getContentPane().setLayout(null);
		
		//----------COMBOBOX----------//
		final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(90, 11, 64, 22);
		comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		frmAltaCliente.getContentPane().add(comboTipoDoc);
		
		final JComboBox comboClaseSolicitada = new JComboBox();
		comboClaseSolicitada.setBounds(90, 190, 64, 22);
		comboClaseSolicitada.setModel(new DefaultComboBoxModel(tipoLicencia.values()));
		frmAltaCliente.getContentPane().add(comboClaseSolicitada);
		
		
		
		//----------TEXTFIELD----------//
		tfNroDoc = new JTextField();
		tfNroDoc.setBounds(259, 12, 86, 20);
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
		tfNombre.setBounds(90, 65, 86, 20);
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
		tfApellido.setBounds(280, 65, 86, 20);
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
		tfDireccion.setBounds(280, 105, 43, 20);
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
		tfGrupoSanguineo.setBounds(90, 158, 86, 20);
		frmAltaCliente.getContentPane().add(tfGrupoSanguineo);
		tfGrupoSanguineo.setColumns(10);
		
		
		tfFactor = new JTextField();
		tfFactor.setColumns(10);
		tfFactor.setBounds(259, 158, 86, 20);
		frmAltaCliente.getContentPane().add(tfFactor);
		

		


		//----------ETIQUETAS----------//
		
;
		
		JLabel lbTipoDoc = new JLabel("TipoDoc");
		lbTipoDoc.setBounds(23, 10, 57, 22);
		lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbTipoDoc);
		
		
		JLabel lbNroDoc = new JLabel("NroDoc");
		lbNroDoc.setBounds(192, 10, 57, 22);
		lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbNroDoc);
		
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(23, 63, 57, 22);
		lbNombre.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbNombre);
		
		
		JLabel lbApellido = new JLabel("Apellido");
		lbApellido.setBounds(192, 63, 57, 22);
		lbApellido.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbApellido);
		
		JLabel lbFechaNac = new JLabel("Fecha Nac");
		lbFechaNac.setBounds (23, 103, 57, 22);
		lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbFechaNac);
		
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setBounds (192, 103, 57, 22);
		lbDireccion.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbDireccion);
		
		JLabel lbClaseSolicitada = new JLabel("Clase solicitada");
		lbClaseSolicitada.setBounds (23, 189, 57, 22);
		lbClaseSolicitada.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbClaseSolicitada);
		
		JLabel lbFactorSanguineo = new JLabel("Factor");
		lbFactorSanguineo.setBounds (192, 156, 57, 22);
		lbFactorSanguineo.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbFactorSanguineo);
	
		JLabel lbGrupoSanguineo = new JLabel("Grupo sanguineo");
		lbGrupoSanguineo.setBounds (23, 156, 57, 22);
		lbGrupoSanguineo.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbGrupoSanguineo);
		
		JLabel lbDonante = new JLabel("Donante de organos");
		lbDonante.setBounds(23, 242, 46, 14);
		lbDonante.setFont(new Font("Serif", Font.PLAIN, 18));
		frmAltaCliente.getContentPane().add(lbDonante);
		
		////------TEXTO DE ESTADO 
		final JLabel lbEstado = new JLabel("New label");
		lbEstado.setBounds(10, 306, 270, 14);
		lbEstado.setVisible(false);
		frmAltaCliente.getContentPane().add(lbEstado);
		
		
		//----------DATE----------//
		final JDateChooser dateCFechaNacimiento = new JDateChooser();
		dateCFechaNacimiento.setBounds(90, 105, 86, 20);
		frmAltaCliente.getContentPane().add(dateCFechaNacimiento);
		
		//----------BUTTONS----------//
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(280, 326, 89, 23);
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
				if (comboClaseSolicitada.getSelectedIndex() == 0){
					completo = false;
				};
				//ComprobarFecha
				if (dateCFechaNacimiento.getDate() == null) {
					completo = false;
				}
				if (completo) { //Si todos los campos se completaron
					if(!gestorT.comprobarExistencia(tfNroDoc.getText())){ //Si no existe un titular registrado con ese Doc
						lbEstado.setVisible(true);
						dateFormat.format(dateCFechaNacimiento.getDate());
						DTOT.setTipoDoc(comboTipoDoc.getSelectedItem().toString());
						DTOT.setNroDoc(tfNroDoc.getText());
						DTOT.setNombre(tfNombre.getText());
						DTOT.setApellido(tfApellido.getText());
						DTOT.setDireccion(tfDireccion.getText());
						DTOT.setFactorS(tfFactor.getText());
						DTOT.setGrupoS(tfGrupoSanguineo.getText());
						DTOT.setFechaNac(dateFormat.toString());
						lbEstado.setText("Prodece a crear");
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
		rbDonanteNo.setBounds(90, 238, 64, 23);
		rbDonanteNo.setSelected(true);
		grupoDonador.add(rbDonanteNo);
		frmAltaCliente.getContentPane().add(rbDonanteNo);
		
		JRadioButton rbDonanteSi = new JRadioButton("Si");
		rbDonanteSi.setBounds(183, 238, 57, 23);
		grupoDonador.add(rbDonanteSi);
		frmAltaCliente.getContentPane().add(rbDonanteSi);
		
	}
}
