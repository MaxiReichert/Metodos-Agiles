package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dto.DTOUsuario;
import enumeradores.tipoDocumento;
import exceptions.ExisteUsuarioException;
import gestores.GestorUsuario;

public class UIAltaUsuario {
	
	private JFrame frmAltaUsuario;
	private static JTextField tfNroDoc;
	private static JTextField tfNroLegajo;
	private static JTextField tfNombre;
	private static JTextField tfApellido;
	private static JTextField tfDireccion;
	private static DTOUsuario dtoUsuario;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	private static GestorUsuario gestorUsuario;
	
	
	/**
	 * El constructo se encarga de incializar los elementos
	 * y una vez hecho esto visivilizar la pantalla
	 * @wbp.parser.entryPoint
	 * */
	public static void iniciar() {
		final Marco frmAltaUsuario = new Marco(800,600,"Gestor de licencias - Alta usuario");
		frmAltaUsuario.getContentPane().setLayout(null);
		frmAltaUsuario.setLocationRelativeTo(null);
		frmAltaUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//TipoDocumento
	    final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(146, 13, 180, 22);
		comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		comboTipoDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNroDoc.setText("");
			}
		});
		frmAltaUsuario.getContentPane().add(comboTipoDoc);
		
		//numero de documento
		tfNroDoc = new JTextField();
		tfNroDoc.setBounds(489, 14, 181, 20);
		tfNroDoc.setColumns(10);
		tfNroDoc.addKeyListener(new KeyAdapter() { //cada tipo de documento tiene una longitud
			@Override
			   public void keyTyped(KeyEvent e)
			   {
					int max=8;
					switch(comboTipoDoc.getSelectedIndex()) {
						case 1:
							max=8;
							break;
						case 2:
							max=7;
							break;
						case 3:
							max=7;
							break;
						case 4:
							max=9;
							break;
					}
					char caracter = e.getKeyChar();

					if(((caracter < '0') ||
							(caracter > '9')) &&
							(caracter != '\b'))
					{
						e.consume();
					}
					if(tfNroDoc.getText().length() >= max) {
						e.consume();
					}
			   }
		});
		frmAltaUsuario.getContentPane().add(tfNroDoc);
		
		//numero de legajo
		tfNroLegajo= new JTextField();
		tfNroLegajo.setBounds(220, 70, 181, 20);
		tfNroLegajo.setColumns(10);
		tfNroLegajo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int max=6;
				char caracter = e.getKeyChar();

				if(((caracter < '0') ||
						(caracter > '9')) &&
						(caracter != '\b'))
				{
					e.consume();
				}
				if(tfNroLegajo.getText().length() >= max) {
					e.consume();
				}
			}
		});
		frmAltaUsuario.getContentPane().add(tfNroLegajo);
		
		//nombre
		tfNombre = new JTextField();
		tfNombre.setBounds(220, 110, 181, 20);
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
		frmAltaUsuario.getContentPane().add(tfNombre);
		
		//apellido
		tfApellido = new JTextField();
		tfApellido.setBounds(220, 150, 181, 20);
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
		frmAltaUsuario.getContentPane().add(tfApellido);
		
		//direccion
		tfDireccion = new JTextField();
		tfDireccion.setBounds(220, 190, 181, 20);
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
		frmAltaUsuario.getContentPane().add(tfDireccion);
		
		//fecha de nacimiento
		final JDateChooser dateCFechaNacimiento = new JDateChooser();
		dateCFechaNacimiento.setBounds(220, 230, 181, 20);
		frmAltaUsuario.getContentPane().add(dateCFechaNacimiento);
		
		//etiqueta tipo documento
		JLabel lbTipoDoc = new JLabel("Tipo documento");
		lbTipoDoc.setBounds(23, 10, 153, 22);
		lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbTipoDoc);
		
		//etiqueta numero documento
		JLabel lbNroDoc = new JLabel("Numero documento");
		lbNroDoc.setBounds(350, 11, 153, 22);
		lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbNroDoc);
		
		//etiqueta numero de legajo
		JLabel lbNroLegajo = new JLabel("Numero Legajo");
		lbNroLegajo.setBounds(25, 70, 151, 22);
		lbNroLegajo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbNroLegajo);
		
		//etiqueta nombre
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(25, 110, 151, 22);
		lbNombre.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbNombre);
		
		//etiqueta apellido
		JLabel lbApellido = new JLabel("Apellido");
		lbApellido.setBounds(25, 150, 151, 22);
		lbApellido.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbApellido);
		
		//etiqueta direccion
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setBounds (25, 190, 124, 22);
		lbDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbDireccion);
		
		//etiqueta fecha de nacimiento
		JLabel lbFechaNac = new JLabel("Fecha de nacimiento");
		lbFechaNac.setBounds (25, 230, 151, 22);
		lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 14));
		frmAltaUsuario.getContentPane().add(lbFechaNac);
		
		//label que para mostrar mensajes al usuario
		final JLabel lbEstado = new JLabel("New label");
		lbEstado.setBounds(280, 497, 270, 14);
		lbEstado.setVisible(false);
		frmAltaUsuario.getContentPane().add(lbEstado);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(660, 493, 89, 23);
		btnCrear.addActionListener(new ActionListener() { //le agrego comoportamiento al boton

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//verifico que se haya seleccionado un tipo de documento
				if(comboTipoDoc.getSelectedIndex()==0) {
					lbEstado.setText("Seleccione un tipo de documento");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
				//verifico que se haya seleccionado una fecha de nacimiento
				else if(dateCFechaNacimiento.getDate()==null) {
					lbEstado.setText("Seleccione una fecha de nacimiento");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
				//verifico que no se halla dejado algún campo vacio
				else if(!camposVacios()) {
					gestorUsuario= new GestorUsuario();
					boolean existeUsuario=true;
					//verifico si existe el usuario
					try {
						existeUsuario= gestorUsuario.existeUsuario(tfNroDoc.getText());
					}
					//si se genera una excepcion muestro un mensaje
					catch(Exception e) {
						lbEstado.setText("Hubo un problema con la base de datos");
						lbEstado.setForeground(Color.red);
						lbEstado.setVisible(true);
						e.printStackTrace();
					}
					//si no existe el usuario completo el dto
					if(!existeUsuario) {
						dtoUsuario= new DTOUsuario();
						dtoUsuario.setApellido(tfApellido.getText());
						dtoUsuario.setDireccion(tfDireccion.getText());
						dtoUsuario.setFechaNac(dateCFechaNacimiento.getDate());
						dtoUsuario.setNombre(tfNombre.getText());
						dtoUsuario.setNroDoc(tfNroDoc.getText());
						dtoUsuario.setNroLegajo(tfNroLegajo.getText());
						dtoUsuario.setTipoDoc(comboTipoDoc.getSelectedItem().toString());
						//creo el usuario muestro un mensaje indicando que se ha creado
						try {
							gestorUsuario.crearUsuario(dtoUsuario);
							lbEstado.setText("El usuario se ha creado con exito");
							lbEstado.setForeground(Color.blue);
							lbEstado.setVisible(true);
						}
						//si se produce una excepcion muestro un mensaje
						catch(Exception e) {
							lbEstado.setText("Hubo un problema con la base de datos");
							lbEstado.setForeground(Color.red);
							lbEstado.setVisible(true);
							e.printStackTrace();
						}
					}
					//si el usuario ya existe muestro un mensaje
					else {
						lbEstado.setText("El usuario ya existe");
						lbEstado.setForeground(Color.red);
						lbEstado.setVisible(true);
					}
					
				}
				//si hay campos vacios muestro un mensaje
				else {
					lbEstado.setText("No deje campos vacios");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
				
			}
			
		});
		frmAltaUsuario.getContentPane().add(btnCrear);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			   @Override
			public void actionPerformed(ActionEvent e) {
				UIPrincipal.iniciar();
				 frmAltaUsuario.dispose();
			}
		});
		btnAtras.setBounds(545, 493, 89, 23);
		frmAltaUsuario.getContentPane().add(btnAtras);
	}
	
	
	
	
	
	//metodo que verifica que no existan campos vacios
	private static boolean camposVacios() {
		boolean vacios=false;
		if(tfApellido.getText().length() == 0 || tfNombre.getText().length() == 0 ||
				tfNroDoc.getText().length() == 0 || tfNroLegajo.getText().length() == 0 ||
				tfDireccion.getText().length() == 0) {
			
			vacios=true;
		}
			
		return vacios;
	}	
}
