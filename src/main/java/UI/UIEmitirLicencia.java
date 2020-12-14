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
import enumeradores.tipoDocumento;
import exceptions.EmitirLicenciaException;
import gestores.GestorLicencia;
import gestores.GestorTitular;
import dto.DTOLicencia;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIEmitirLicencia {

	private JFrame frmAltaLicencia;
	private JTextField nroDocTextField;
	private JTextField titularSeleccionadoTextField;
	private JFrame previous;
	private DTOTitular dtoTitularSeleccionado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIEmitirLicencia window = new UIEmitirLicencia();
					window.frmAltaLicencia.setVisible(true);
				}
				catch (Exception e) {
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
		frmAltaLicencia = new JFrame();
		frmAltaLicencia.setTitle("Emitir Licencia");
		frmAltaLicencia.setBounds(0, 0, 800, 600);
		frmAltaLicencia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaLicencia.getContentPane().setLayout(null);
		
		JComboBox<String> comboTipoDNI = new JComboBox<String>();
		comboTipoDNI.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		comboTipoDNI.setFont(new Font("Monospaced", Font.PLAIN, 16));
		comboTipoDNI.setBounds(97, 79, 164, 35);
		frmAltaLicencia.getContentPane().add(comboTipoDNI);
		
		nroDocTextField = new JTextField();
		nroDocTextField.setFont(new Font("Monospaced", Font.BOLD, 16));
		nroDocTextField.setBounds(343, 79, 173, 34);
		frmAltaLicencia.getContentPane().add(nroDocTextField);
		nroDocTextField.setColumns(10);
		
		JLabel titularSeleccionadoLabel = new JLabel("Titular Seleccionado");
		titularSeleccionadoLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		titularSeleccionadoLabel.setBounds(79, 159, 227, 31);
		frmAltaLicencia.getContentPane().add(titularSeleccionadoLabel);
		
		titularSeleccionadoTextField = new JTextField();
		titularSeleccionadoTextField.setFont(new Font("Monospaced", Font.PLAIN, 16));
		titularSeleccionadoTextField.setForeground(Color.RED);
		titularSeleccionadoTextField.setEditable(false);
		titularSeleccionadoTextField.setBounds(319, 157, 418, 34);
		frmAltaLicencia.getContentPane().add(titularSeleccionadoTextField);
		titularSeleccionadoTextField.setColumns(10);
		
		JButton seleccionarTitularButton = new JButton("Seleccionar Titular");
		seleccionarTitularButton.addActionListener(e-> {
			if(nroDocTextField.getText().length()>3) {
				DTOTitular titular = new DTOTitular();
				try {
					titular = GestorTitular.getInstance().obtenerTitular(nroDocTextField.getText().trim());
					if(titular!=null) {
						titularSeleccionadoTextField.setText('['+titular.getTipoDoc()+"] " + titular.getNroDoc() + " - "+titular.getApellido()+", "+titular.getNombre());
						titularSeleccionadoTextField.setForeground(Color.GREEN);
						this.dtoTitularSeleccionado = titular;
					}
					else {
						this.dtoTitularSeleccionado = null;
						titularSeleccionadoTextField.setText("TITULAR NO ENCONTRADO");
						titularSeleccionadoTextField.setForeground(Color.RED);
					}
				} catch (Exception ex){
					this.dtoTitularSeleccionado = null;
					titularSeleccionadoTextField.setText("ERROR DEL SISTEMA AL BUSCAR EL TITULAR");
					titularSeleccionadoTextField.setForeground(Color.RED);
					System.err.println(ex.getMessage());
				}
				
			}
		});

		seleccionarTitularButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		seleccionarTitularButton.setBounds(544, 79, 220, 34);
		frmAltaLicencia.getContentPane().add(seleccionarTitularButton);
		

		
		JComboBox<String> comboTipoLicencia = new JComboBox<String>();
		comboTipoLicencia.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G"}));
		comboTipoLicencia.setFont(new Font("Monospaced", Font.PLAIN, 16));
		comboTipoLicencia.setBounds(319, 258, 95, 35);
		DefaultListCellRenderer centeredListRenderer = new DefaultListCellRenderer();
	    centeredListRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	    comboTipoLicencia.setRenderer(centeredListRenderer);
		frmAltaLicencia.getContentPane().add(comboTipoLicencia);
		
		JButton emitirLicenciaButton = new JButton("Emitir Licencia");
		emitirLicenciaButton.setForeground(Color.BLUE);
		emitirLicenciaButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		emitirLicenciaButton.setBounds(449, 465, 220, 35);
		
		frmAltaLicencia.getContentPane().add(emitirLicenciaButton);
		
		
		JLabel tipoLicenciaLabel = new JLabel("Tipo Licencia");
		tipoLicenciaLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		tipoLicenciaLabel.setBounds(107, 260, 154, 31);
		frmAltaLicencia.getContentPane().add(tipoLicenciaLabel);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e-> {
			
		});
		cancelarButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
		cancelarButton.setBounds(130, 465, 220, 35);
		frmAltaLicencia.getContentPane().add(cancelarButton);
		
		JLabel observacionesLabel = new JLabel("Observaciones");
		observacionesLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
		observacionesLabel.setBounds(107, 337, 142, 31);
		frmAltaLicencia.getContentPane().add(observacionesLabel);
		
		JTextArea observacionesTextArea = new JTextArea();
		observacionesTextArea.setBounds(319, 342, 285, 74);
		frmAltaLicencia.getContentPane().add(observacionesTextArea);
		
		JLabel lblNrodoc = new JLabel("Nro Doc");
		lblNrodoc.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblNrodoc.setBounds(266, 81, 73, 31);
		frmAltaLicencia.getContentPane().add(lblNrodoc);
		
		JLabel lblTipodoc = new JLabel("Tipo Doc");
		lblTipodoc.setFont(new Font("Monospaced", Font.PLAIN, 16));
		lblTipodoc.setBounds(10, 81, 89, 31);
		frmAltaLicencia.getContentPane().add(lblTipodoc);
		
		emitirLicenciaButton.addActionListener(e-> {
			try {
				
				DTOLicencia dtoLic = generarDTOLicencia((String) comboTipoLicencia.getSelectedItem(), observacionesTextArea.getText());
				GestorLicencia.emitirLicencia(dtoLic);
				JOptionPane.showMessageDialog(new JPanel(), "Licencia emitida correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);	
			}
			catch(EmitirLicenciaException emitirEx){
				JOptionPane.showMessageDialog(new JPanel(), emitirEx.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);	
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		});
	}



	/**
	 * @return
	 * @throws EmitirLicenciaException 
	 */
	private DTOLicencia generarDTOLicencia(String tipoLicencia, String observaciones) throws EmitirLicenciaException {
		
		
		
		DTOLicencia dto = new DTOLicencia();
		
		if(dtoTitularSeleccionado != null) {
			System.out.println(dtoTitularSeleccionado.getApellido());
			dto.setTitular(dtoTitularSeleccionado);
		}else {
			throw new EmitirLicenciaException("No se ha seleccionado un titular");
		}
		
		if
		(
				tipoLicencia != null &&
				(tipoLicencia == "A" || tipoLicencia == "B" || tipoLicencia == "C" || tipoLicencia == "D" || tipoLicencia =="F" || tipoLicencia == "G")
		)
		{
			dto.setTipo(tipoLicencia);
		}
		else {
			 throw new EmitirLicenciaException("Tipo de licencia no válido");
		}
		
		if(observaciones != null && observaciones != "") {
			dto.setObservaciones(observaciones.trim());
		}
		else {
			dto.setObservaciones("");
		}

		return dto;
	}
}
