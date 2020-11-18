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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UIEmitirLicencia {

	private JFrame frmAltaCliente;
	private JTextField tfNroDNI;

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
		
		JComboBox comboTipoDNI = new JComboBox();
		comboTipoDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboTipoDNI.setBounds(97, 10, 164, 35);
		frmAltaCliente.getContentPane().add(comboTipoDNI);
		
		tfNroDNI = new JTextField();
		tfNroDNI.setBounds(343, 11, 173, 34);
		frmAltaCliente.getContentPane().add(tfNroDNI);
		tfNroDNI.setColumns(10);
		
		JTextArea txtTipoDNI = new JTextArea();
		txtTipoDNI.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtTipoDNI.setBackground(UIManager.getColor("CheckBox.background"));
		txtTipoDNI.setEditable(false);
		txtTipoDNI.setText("TipoDNI");
		txtTipoDNI.setBounds(10, 12, 77, 31);
		frmAltaCliente.getContentPane().add(txtTipoDNI);
		
		JTextArea txtNroDNI = new JTextArea();
		txtNroDNI.setBackground(UIManager.getColor("CheckBox.background"));
		txtNroDNI.setFont(new Font("Monospaced", Font.PLAIN, 16));
		txtNroDNI.setText("NroDNI");
		txtNroDNI.setBounds(271, 12, 77, 34);
		frmAltaCliente.getContentPane().add(txtNroDNI);
		
		JButton btnNewButton = new JButton("Seleccionar Titular");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(539, 11, 184, 34);
		frmAltaCliente.getContentPane().add(btnNewButton);
	}
}
