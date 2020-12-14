package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import com.toedter.calendar.JDateChooser;

import dto.DTOTitular;
import gestores.GestorTitular;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIEmitirLicencia {

	private JFrame frmAltaCliente;
	private JTextField nroDocTextField;
	private JTextField titularSeleccionadoTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIEmitirLicencia window = new UIEmitirLicencia();
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
	public UIEmitirLicencia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaCliente = new JFrame();
		frmAltaCliente.setTitle("Emitir Licencia");
		frmAltaCliente.setBounds(0, 0, 800, 600);
		frmAltaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaCliente.getContentPane().setLayout(null);
		
		JComboBox<String> comboTipoDNI = new JComboBox<String>();
		comboTipoDNI.setModel(new DefaultComboBoxModel<String>(new String[] {"DNI"}));
		comboTipoDNI.setFont(new Font("Monospaced", Font.PLAIN, 16));
		comboTipoDNI.setBounds(97, 79, 164, 35);
		frmAltaCliente.getContentPane().add(comboTipoDNI);
		
		nroDocTextField = new JTextField();
		nroDocTextField.setFont(new Font("Monospaced", Font.BOLD, 16));
		nroDocTextField.setBounds(343, 79, 173, 34);
		frmAltaCliente.getContentPane().add(nroDocTextField);
		nroDocTextField.setColumns(10);
		
		JLabel titularSeleccionadoLabel = new JLabel("Titular Seleccionado");
		titularSeleccionadoLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		titularSeleccionadoLabel.setBounds(79, 159, 227, 31);
		frmAltaCliente.getContentPane().add(titularSeleccionadoLabel);
		
		titularSeleccionadoTextField = new JTextField();
		titularSeleccionadoTextField.setFont(new Font("Monospaced", Font.PLAIN, 16));
		titularSeleccionadoTextField.setForeground(Color.RED);
		titularSeleccionadoTextField.setEditable(false);
		titularSeleccionadoTextField.setBounds(319, 157, 418, 34);
		frmAltaCliente.getContentPane().add(titularSeleccionadoTextField);
		titularSeleccionadoTextField.setColumns(10);
		
		JTextArea txtTipoDOC = new JTextArea();
		txtTipoDOC.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtTipoDOC.setBackground(UIManager.getColor("CheckBox.background"));
		txtTipoDOC.setEditable(false);
		txtTipoDOC.setText("TipoDOC");
		txtTipoDOC.setBounds(10, 83, 77, 31);
		frmAltaCliente.getContentPane().add(txtTipoDOC);
		
		JTextArea txtNroDOC = new JTextArea();
		txtNroDOC.setBackground(UIManager.getColor("CheckBox.background"));
		txtNroDOC.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtNroDOC.setText("NroDOC");
		txtNroDOC.setBounds(273, 83, 77, 34);
		frmAltaCliente.getContentPane().add(txtNroDOC);
		
		JButton seleccionarTitularButton = new JButton("Seleccionar Titular");
		/*seleccionarTitularButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNroDOC.getText().length()>3) {
					DTOTitular titular = new DTOTitular();
					try {
						titular = new GestorTitular().obtenerTitular(txtNroDOC.getText());
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
		seleccionarTitularButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		seleccionarTitularButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		seleccionarTitularButton.setBounds(544, 79, 220, 34);
		frmAltaCliente.getContentPane().add(seleccionarTitularButton);
		

		
		JComboBox<String> comboTipoLicencia = new JComboBox<String>();
		comboTipoLicencia.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G"}));
		comboTipoLicencia.setFont(new Font("Monospaced", Font.PLAIN, 16));
		comboTipoLicencia.setBounds(319, 258, 95, 35);
		DefaultListCellRenderer centeredListRenderer = new DefaultListCellRenderer();
	    centeredListRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	    comboTipoLicencia.setRenderer(centeredListRenderer);
		frmAltaCliente.getContentPane().add(comboTipoLicencia);
		
		JButton btnNewButton_1 = new JButton("Emitir Licencia");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Monospaced", Font.PLAIN, 16));
		btnNewButton_1.setBounds(449, 465, 220, 35);
		frmAltaCliente.getContentPane().add(btnNewButton_1);
		
		JLabel tipoLicenciaLabel = new JLabel("Tipo Licencia");
		tipoLicenciaLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tipoLicenciaLabel.setBounds(107, 260, 154, 31);
		frmAltaCliente.getContentPane().add(tipoLicenciaLabel);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelarButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		cancelarButton.setBounds(130, 465, 220, 35);
		frmAltaCliente.getContentPane().add(cancelarButton);
		
		JLabel observacionesLabel = new JLabel("Observaciones");
		observacionesLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		observacionesLabel.setBounds(107, 337, 142, 31);
		frmAltaCliente.getContentPane().add(observacionesLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(319, 342, 285, 74);
		frmAltaCliente.getContentPane().add(textArea);
	}
}
