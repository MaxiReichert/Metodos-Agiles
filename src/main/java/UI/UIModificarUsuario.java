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
import gestores.GestorUsuario;

public class UIModificarUsuario {
	private JFrame frmModificarUsuario;
	private JTextField tfNroDoc;
	private JTextField tfNroLegajo;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDireccion;
	private JTextField tfFechaNac;
	private JButton btnActualizar;
	private DTOUsuario dtoUsuario;
	private SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	private GestorUsuario gestorUsuario;
	
	/**
	 * El constructo se encarga de incializar los elementos
	 * y una vez hecho esto visivilizar la pantalla
	 * */
	public UIModificarUsuario() {
		initialize();
		this.frmModificarUsuario.setVisible(true);
	}
	
	private void initialize() {
		frmModificarUsuario = new JFrame();
		frmModificarUsuario.setTitle("Modiciar Usuario");
		frmModificarUsuario.setBounds(100, 100, 800, 600);
		frmModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarUsuario.getContentPane().setLayout(null);
		
		//TipoDocumento
	    final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(146, 13, 180, 22);
		comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		comboTipoDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNroDoc.setText("");
			}
		});
		frmModificarUsuario.getContentPane().add(comboTipoDoc);
		
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
		frmModificarUsuario.getContentPane().add(tfNroDoc);
		
		//numero de legajo
		tfNroLegajo= new JTextField();
		tfNroLegajo.setBounds(220, 70, 181, 20);
		tfNroLegajo.setColumns(10);
		tfNroLegajo.setEditable(false);
		frmModificarUsuario.getContentPane().add(tfNroLegajo);
		
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
		tfNombre.setEditable(false);
		frmModificarUsuario.getContentPane().add(tfNombre);
		
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
		tfApellido.setEditable(false);
		frmModificarUsuario.getContentPane().add(tfApellido);
		
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
		tfDireccion.setEditable(false);
		frmModificarUsuario.getContentPane().add(tfDireccion);
		
		//fecha de nacimiento
		tfFechaNac= new JTextField();
		tfFechaNac.setBounds(220, 230, 181, 20);
		tfFechaNac.setColumns(10);
		tfFechaNac.setEditable(false);
		frmModificarUsuario.getContentPane().add(tfFechaNac);
		
		
		//etiqueta tipo documento
		JLabel lbTipoDoc = new JLabel("Tipo documento");
		lbTipoDoc.setBounds(23, 10, 153, 22);
		lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbTipoDoc);
		
		//etiqueta numero documento
		JLabel lbNroDoc = new JLabel("Numero documento");
		lbNroDoc.setBounds(350, 11, 153, 22);
		lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbNroDoc);
		
		//etiqueta numero de legajo
		JLabel lbNroLegajo = new JLabel("Numero Legajo");
		lbNroLegajo.setBounds(25, 70, 151, 22);
		lbNroLegajo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbNroLegajo);
		
		//etiqueta nombre
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(25, 110, 151, 22);
		lbNombre.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbNombre);
		
		//etiqueta apellido
		JLabel lbApellido = new JLabel("Apellido");
		lbApellido.setBounds(25, 150, 151, 22);
		lbApellido.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbApellido);
		
		//etiqueta direccion
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setBounds (25, 190, 124, 22);
		lbDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbDireccion);
		
		//etiqueta fecha de nacimiento
		JLabel lbFechaNac = new JLabel("Fecha de nacimiento");
		lbFechaNac.setBounds (25, 230, 151, 22);
		lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 14));
		frmModificarUsuario.getContentPane().add(lbFechaNac);
		
		//label que para mostrar mensajes al usuario
		final JLabel lbEstado = new JLabel("New label");
		lbEstado.setBounds(280, 497, 270, 14);
		lbEstado.setVisible(false);
		frmModificarUsuario.getContentPane().add(lbEstado);
		
		//boton buscar
		JButton btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(680, 12, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoDoc.getSelectedIndex()==0) { //verifico que haya elegido un tipo de documento
					//si no lo ha elegido muestro un mensaje
					lbEstado.setText("Seleccione un tipo de documento");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
				else if(tfNroDoc.getText().length()==0) {//verifico que haya escrito un numero de documento
					//si no lo ha completado muestro un mensaje
					lbEstado.setText("Escriba un numero de documento");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
				else {
					GestorUsuario gestorUsuario= new GestorUsuario();
					boolean existeUsuario=false;
					try {// verifico que el usuario exista
						existeUsuario=gestorUsuario.existeUsuario(tfNroDoc.getText());
					}
					catch(Exception e) { // si hay un problema muestro un mensaje
						lbEstado.setText("Hubo un problema con la base de datos");
						lbEstado.setForeground(Color.red);
						lbEstado.setVisible(true);
						e.printStackTrace();
					}
					//si no existe usuario muestro un mensaje
					if(!existeUsuario) {
						lbEstado.setText("El usuario no existe");
						lbEstado.setForeground(Color.red);
						lbEstado.setVisible(true);
					}
					//si existe el usuario lo busco a la base de datos
					else {
						try {
							dtoUsuario=gestorUsuario.buscarUsuario(tfNroDoc.getText());
						}
						catch(Exception e) {
							lbEstado.setText("Hubo un problema con la base de datos");
							lbEstado.setForeground(Color.red);
							lbEstado.setVisible(true);
							e.printStackTrace();
						}
						//completo los campos
						tfNroLegajo.setText(dtoUsuario.getNroLegajo());
						tfNombre.setText(dtoUsuario.getNombre());
						tfApellido.setText(dtoUsuario.getApellido());
						tfDireccion.setText(dtoUsuario.getDireccion());
						String fechaText= dateFormat.format(dtoUsuario.getFechaNac());
						tfFechaNac.setText(fechaText);
						
						//permito editar ciertos campos y actualizar
						tfNombre.setEditable(true);
						tfApellido.setEditable(true);
						tfDireccion.setEditable(true);
						btnActualizar.setEnabled(true);
					}
				}
				
			}
		});
		frmModificarUsuario.getContentPane().add(btnBuscar);
		
		//boton actualizar
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(660, 493, 92, 23);
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//si los campos editables no estan vacios actualizo el usuario
				if(!camposVacios()) {
					GestorUsuario gestorUsuario= new GestorUsuario();
					dtoUsuario.setApellido(tfApellido.getText());
					dtoUsuario.setNombre(tfNombre.getText());
					dtoUsuario.setDireccion(tfDireccion.getText());
					try {
						gestorUsuario.actualizarUsuario(dtoUsuario);
						lbEstado.setText("El usuario se ha actualizado con exito");
						lbEstado.setForeground(Color.blue);
						lbEstado.setVisible(true);
					} catch (Exception e) {
						lbEstado.setText("Hubo un problema con la base de datos");
						lbEstado.setForeground(Color.red);
						lbEstado.setVisible(true);
						e.printStackTrace();
					}
				}
				//sino muestro un mensaje
				else {
					lbEstado.setText("Complete todos los campos");
					lbEstado.setForeground(Color.red);
					lbEstado.setVisible(true);
				}
			}
		});
		frmModificarUsuario.getContentPane().add(btnActualizar);
	}
	
	/**
	 * Metodo que verifica que los campos ediatables no esten vacios
	 * */
	private boolean camposVacios() {
		if(tfNombre.getText().length()==0 || tfApellido.getText().length()==0 ||
				tfDireccion.getText().length()==0) {
			return true;
		}
		return false;
	}
}
