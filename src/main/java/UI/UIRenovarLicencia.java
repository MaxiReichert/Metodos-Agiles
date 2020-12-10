package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.DTOTitular;
import enumeradores.tipoDocumento;
import gestores.GestorTitular;

public class UIRenovarLicencia {
	
	private JFrame frmRenovarLicencia;
	private JTextField tfNroDoc;
	private JTextField tfFechaVencimiento;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDireccion;
	private JTextField tfFechaNacimiento;
	private JTextField tfGrupoSanguineo;
	private JTextField tfFactor;
	private JTextField tfClaseLicencia;
	private JButton btnBuscarTitular;
	private ButtonGroup grupoDonador = new ButtonGroup();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIRenovarLicencia window = new UIRenovarLicencia();
					window.frmRenovarLicencia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//constructor
	public UIRenovarLicencia() {
		initialize();
		this.frmRenovarLicencia.setVisible(true);
	}
	
	//inicializar el contenido del frame
	
	private void initialize() {
		frmRenovarLicencia = new JFrame();
		frmRenovarLicencia.setTitle("Renovar Licencia");
		frmRenovarLicencia.setBounds(100, 100, 800, 600);
		frmRenovarLicencia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRenovarLicencia.getContentPane().setLayout(null);
		
		//----------COMBOBOX----------//
		//capaz no es necesario, solo con el numero debería devolver la licencia
		final JComboBox comboTipoDoc = new JComboBox();
		comboTipoDoc.setBounds(146, 13, 180, 22);
		comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		frmRenovarLicencia.getContentPane().add(comboTipoDoc);
		
		
		//----------TEXTFIELD--------//
		
		tfNroDoc = new JTextField();
		tfNroDoc.setBounds(489, 14, 181, 20);
		tfNroDoc.setColumns(10);
		//traer el valor del documento de la bd con 
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
		frmRenovarLicencia.getContentPane().add(tfNroDoc);
		
		tfFechaVencimiento =new JTextField();
		tfFechaVencimiento.setBounds(220, 70, 181, 20);
		tfFechaVencimiento.setColumns(10);
		tfFechaVencimiento.setText("Prueba fecha vencimiento"); //traer de la bd
		tfFechaVencimiento.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfFechaVencimiento);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(220, 110, 181, 20);
		tfNombre.setColumns(10);
		tfNombre.setText("Prueba Nombre"); //traer de la bd
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
		frmRenovarLicencia.getContentPane().add(tfNombre);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(220, 150, 181, 20);
		tfApellido.setColumns(10);
		tfApellido.setText("Prueba Apellido"); //traer de la bd
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
		frmRenovarLicencia.getContentPane().add(tfApellido);

		tfDireccion = new JTextField();
		tfDireccion.setBounds(220, 190, 181, 20);
		tfDireccion.setColumns(10);
		tfDireccion.setText("Prueba direccion"); //traer el valor de la bd
		tfDireccion.addKeyListener(new KeyAdapter() { //La direccion tiene hasta 30 letras
			@Override
			public void keyTyped(KeyEvent e) {
				int max =30;
				if(tfDireccion.getText().length() > max) {
					e.consume();
				}
			}
		});
		frmRenovarLicencia.getContentPane().add(tfDireccion);
		
		tfFechaNacimiento = new JTextField();
		tfFechaNacimiento.setBounds(220, 230, 181, 20);
		tfFechaNacimiento.setText("Prueba fecha nacimiento"); //traer de la bd
		tfFechaNacimiento.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfFechaNacimiento);

		
		tfGrupoSanguineo = new JTextField();
		tfGrupoSanguineo.setBounds(220, 270, 181, 20);
		tfGrupoSanguineo.setText("Prueba grupo sanguineo"); //traer de la bd
		tfGrupoSanguineo.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfGrupoSanguineo);
		
		tfFactor = new JTextField();
		tfFactor.setColumns(10);
		tfFactor.setBounds(220, 310, 181, 20);
		tfFactor.setText("Prueba factor"); //traer de la bd
		tfFactor.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfFactor);
		
		tfClaseLicencia =new JTextField();
		tfClaseLicencia.setBounds(220, 350, 181, 20);
		tfClaseLicencia.setColumns(10);
		tfClaseLicencia.setText("Prueba clase licencia"); //traer de la bd
		tfClaseLicencia.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfClaseLicencia);
		
		
		
		//----------ETIQUETAS----------//
		
		JLabel lbTipoDoc = new JLabel("Tipo documento");
		lbTipoDoc.setBounds(23, 10, 153, 22);
		lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbTipoDoc);
		
		
		JLabel lbNroDoc = new JLabel("Numero documento");
		lbNroDoc.setBounds(350, 11, 153, 22);
		lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbNroDoc);
		
		JLabel lbEstadoTitular = new JLabel("Prueba se encontro o no");
		lbEstadoTitular.setBounds(450, 35, 450, 25);
		frmRenovarLicencia.getContentPane().add(lbEstadoTitular);	
		//hacer un if, si se encontro el titular mostrar
		
		JLabel lbFechaVencimiento = new JLabel("Fecha de vencimiento");
		lbFechaVencimiento.setBounds(25, 70, 153, 22);
		lbFechaVencimiento.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbFechaVencimiento);
		
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setBounds(25, 110, 151, 22);
		lbNombre.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbNombre);
		
		
		JLabel lbApellido = new JLabel("Apellido");
		lbApellido.setBounds(25, 150, 151, 22);
		lbApellido.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbApellido);
		
		JLabel lbFechaNac = new JLabel("Fecha de nacimiento");
		lbFechaNac.setBounds (25, 230, 151, 22);
		lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbFechaNac);
		
		JLabel lbDireccion = new JLabel("Direccion");
		lbDireccion.setBounds (25, 190, 124, 22);
		lbDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbDireccion);
		
		JLabel lbClaseSolicitada = new JLabel("Clase de Licencia");
		lbClaseSolicitada.setBounds (25, 350, 151, 22);
		lbClaseSolicitada.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbClaseSolicitada);
		
		JLabel lbFactorSanguineo = new JLabel("Factor sanguineo");
		lbFactorSanguineo.setBounds (25, 310, 140, 22);
		lbFactorSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbFactorSanguineo);
	
		JLabel lbGrupoSanguineo = new JLabel("Grupo sanguineo");
		lbGrupoSanguineo.setBounds (25, 270, 151, 22);
		lbGrupoSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbGrupoSanguineo);
		
		JLabel lbDonante = new JLabel("Donante de organos:");
		lbDonante.setBounds(25, 409, 174, 22);
		lbDonante.setFont(new Font("Serif", Font.PLAIN, 14));
		frmRenovarLicencia.getContentPane().add(lbDonante);
		
		JRadioButton rbDonanteNo = new JRadioButton("No");
		rbDonanteNo.setBounds(23, 438, 64, 23);
		rbDonanteNo.setSelected(true);//traer la selección de la base de datos
		grupoDonador.add(rbDonanteNo);
		frmRenovarLicencia.getContentPane().add(rbDonanteNo);
		
		JRadioButton rbDonanteSi = new JRadioButton("Si");
		rbDonanteSi.setBounds(173, 438, 57, 23);
		grupoDonador.add(rbDonanteSi);
		frmRenovarLicencia.getContentPane().add(rbDonanteSi);
		
		//-------------BOTONES------------//
		
		JButton btnBuscarTitular = new JButton("Buscar");
		btnBuscarTitular.setBounds(675, 14, 89, 19);
		frmRenovarLicencia.getContentPane().add(btnBuscarTitular);
		/*btnBuscarTitular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfNroDoc.getText().length()>3) {
					DTOTitular titular = new DTOTitular();
					try {			
						GestorTitular gestorT = GestorTitular.getInstance();
						titular = gestorT.obtenerTitular(tfNroDoc.getText());
						if(titular!=null) {
							titularSeleccionadoTextField.setText('['+titular.getTipoDoc()+"] " + titular.getNroDoc() + " - "+titular.getApellido()+", "+titular.getNombre());
							titularSeleccionadoTextField.setForeground(Color.GREEN);
						}
						else {
							titularSeleccionadoTextField.setText("TITULAR NO ENCONTRADO");
							titularSeleccionadoTextField.setForeground(Color.RED);
						}
					} catch (Exception ex){
						titularSeleccionadoTextField.setText("ERROR DEL SISTEMA AL BUSCAR EL TITULAR");
						titularSeleccionadoTextField.setForeground(Color.RED);
						System.err.println(ex.getMessage());
					}
					
				}
			}
		});*/
	}
	
	
	
	
	
	
	
	
	
	
	
}
