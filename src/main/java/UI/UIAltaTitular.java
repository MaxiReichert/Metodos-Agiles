package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;
import javax.swing.JButton;

public class UIAltaTitular {

	private JFrame frmAltaCliente;
	private JTextField tfNroDNI;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDireccion;
	private JTextField tfGrupoSanguineo;
	private JTextField tfFactor;

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
		frmAltaCliente.setBounds(100, 100, 450, 300);
		frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaCliente.getContentPane().setLayout(null);
		
		JComboBox comboTipoDNI = new JComboBox();
		comboTipoDNI.setBounds(90, 11, 64, 22);
		frmAltaCliente.getContentPane().add(comboTipoDNI);
		
		tfNroDNI = new JTextField();
		tfNroDNI.setBounds(259, 12, 86, 20);
		frmAltaCliente.getContentPane().add(tfNroDNI);
		tfNroDNI.setColumns(10);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(90, 65, 86, 20);
		frmAltaCliente.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(280, 65, 86, 20);
		frmAltaCliente.getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(280, 105, 43, 20);
		frmAltaCliente.getContentPane().add(tfDireccion);
		tfDireccion.setColumns(10);
		
		JTextArea txtTipoDNI = new JTextArea();
		txtTipoDNI.setText("TipoDNI");
		txtTipoDNI.setBounds(23, 10, 57, 22);
		frmAltaCliente.getContentPane().add(txtTipoDNI);
		
		JTextArea txtNroDNI = new JTextArea();
		txtNroDNI.setText("NroDNI");
		txtNroDNI.setBounds(192, 10, 57, 22);
		frmAltaCliente.getContentPane().add(txtNroDNI);
		
		JTextArea txtNombre = new JTextArea();
		txtNombre.setBackground(UIManager.getColor("CheckBox.background"));
		txtNombre.setText("Nombre");
		txtNombre.setBounds(23, 63, 57, 22);
		frmAltaCliente.getContentPane().add(txtNombre);
		
		JTextArea txtApellido = new JTextArea();
		txtApellido.setText("Apellido");
		txtApellido.setBounds(192, 63, 57, 22);
		frmAltaCliente.getContentPane().add(txtApellido);
		
		JTextArea txtrFechanacimiento = new JTextArea();
		txtrFechanacimiento.setText("FechaNacimiento");
		txtrFechanacimiento.setBounds(23, 103, 57, 22);
		frmAltaCliente.getContentPane().add(txtrFechanacimiento);
		
		JTextArea txtrDireccion = new JTextArea();
		txtrDireccion.setText("Direccion");
		txtrDireccion.setBounds(192, 103, 57, 22);
		frmAltaCliente.getContentPane().add(txtrDireccion);
		
		JDateChooser dateCFechaNacimiento = new JDateChooser();
		dateCFechaNacimiento.setBounds(90, 105, 86, 20);
		frmAltaCliente.getContentPane().add(dateCFechaNacimiento);
		
		JTextArea txtGrupoSanguineo = new JTextArea();
		txtGrupoSanguineo.setText("Grupo Sanguineo");
		txtGrupoSanguineo.setBackground(SystemColor.menu);
		txtGrupoSanguineo.setBounds(23, 156, 57, 22);
		frmAltaCliente.getContentPane().add(txtGrupoSanguineo);
		
		tfGrupoSanguineo = new JTextField();
		tfGrupoSanguineo.setBounds(90, 158, 86, 20);
		frmAltaCliente.getContentPane().add(tfGrupoSanguineo);
		tfGrupoSanguineo.setColumns(10);
		
		JTextArea txtFactor = new JTextArea();
		txtFactor.setText("Factor");
		txtFactor.setBackground(SystemColor.menu);
		txtFactor.setBounds(192, 156, 57, 22);
		frmAltaCliente.getContentPane().add(txtFactor);
		
		tfFactor = new JTextField();
		tfFactor.setColumns(10);
		tfFactor.setBounds(259, 158, 86, 20);
		frmAltaCliente.getContentPane().add(tfFactor);
		
		JTextArea txtClaseSolicitada = new JTextArea();
		txtClaseSolicitada.setText("TipoDNI");
		txtClaseSolicitada.setBounds(23, 189, 57, 22);
		frmAltaCliente.getContentPane().add(txtClaseSolicitada);
		
		JComboBox comboClaseSolicitada = new JComboBox();
		comboClaseSolicitada.setBounds(90, 190, 64, 22);
		frmAltaCliente.getContentPane().add(comboClaseSolicitada);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(277, 227, 89, 23);
		frmAltaCliente.getContentPane().add(btnCrear);
	}
}
