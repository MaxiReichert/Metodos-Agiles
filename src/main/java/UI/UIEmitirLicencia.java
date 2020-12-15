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

	private static JFrame frmAltaLicencia;
	private static JTextField nroDocTextField;
	private static JTextField titularSeleccionadoTextField;
	private static JFrame previous;
	private static DTOTitular dtoTitularSeleccionado = null; 

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public static void iniciar() {
		final Marco frmAltaLicencia = new Marco(800,600,"Gestor de licencias - Emitir licencia");
		frmAltaLicencia.getContentPane().setLayout(null);
		frmAltaLicencia.setLocationRelativeTo(null);
		frmAltaLicencia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JComboBox<String> comboTipoDNI = new JComboBox<String>();
		comboTipoDNI.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
		comboTipoDNI.setFont(new Font("Serif", Font.PLAIN, 14));
		comboTipoDNI.setBounds(131, 11, 164, 22);
		frmAltaLicencia.getContentPane().add(comboTipoDNI);
		
		nroDocTextField = new JTextField();
		nroDocTextField.setFont(new Font("Serif", Font.PLAIN, 14));
		nroDocTextField.setBounds(445, 11, 173, 22);
		frmAltaLicencia.getContentPane().add(nroDocTextField);
		nroDocTextField.setColumns(10);
		
		JLabel titularSeleccionadoLabel = new JLabel("Titular Seleccionado");
		titularSeleccionadoLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		titularSeleccionadoLabel.setBounds(10, 157, 155, 22);
		frmAltaLicencia.getContentPane().add(titularSeleccionadoLabel);
		
		titularSeleccionadoTextField = new JTextField();
		titularSeleccionadoTextField.setFont(new Font("Serif", Font.PLAIN, 14));
		titularSeleccionadoTextField.setForeground(Color.RED);
		titularSeleccionadoTextField.setEditable(false);
		titularSeleccionadoTextField.setBounds(200, 157, 418, 22);
		frmAltaLicencia.getContentPane().add(titularSeleccionadoTextField);
		titularSeleccionadoTextField.setColumns(10);
		
		JButton seleccionarTitularButton = new JButton("Seleccionar Titular");
		seleccionarTitularButton.addActionListener(e-> {
			if(nroDocTextField.getText().length()>3) {
				DTOTitular titular = new DTOTitular();
				try {
					titular = GestorTitular.getInstance().obtenerTitular(nroDocTextField.getText().trim());
					if(titular.getNroDoc() != null) {
						titularSeleccionadoTextField.setText('['+titular.getTipoDoc()+"] " + titular.getNroDoc() + " - "+titular.getApellido()+", "+titular.getNombre());
						titularSeleccionadoTextField.setForeground(Color.GREEN);
						dtoTitularSeleccionado = titular;
					}
					else {
						dtoTitularSeleccionado = null;
						titularSeleccionadoTextField.setText("TITULAR NO ENCONTRADO");
						titularSeleccionadoTextField.setForeground(Color.RED);
					}
				} catch (Exception ex){
					dtoTitularSeleccionado = null;
					titularSeleccionadoTextField.setText("ERROR DEL SISTEMA AL BUSCAR EL TITULAR");
					titularSeleccionadoTextField.setForeground(Color.RED);
					System.err.println(ex.getMessage());
				}
				
			}
		});

		seleccionarTitularButton.setFont(new Font("Serif", Font.PLAIN, 14));
		seleccionarTitularButton.setBounds(445, 56, 173, 22);
		frmAltaLicencia.getContentPane().add(seleccionarTitularButton);
		

		
		JComboBox<String> comboTipoLicencia = new JComboBox<String>();
		comboTipoLicencia.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G"}));
		comboTipoLicencia.setFont(new Font("Serif", Font.PLAIN, 14));
		comboTipoLicencia.setBounds(200, 218, 101, 22);
		DefaultListCellRenderer centeredListRenderer = new DefaultListCellRenderer();
	    centeredListRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	    comboTipoLicencia.setRenderer(centeredListRenderer);
		frmAltaLicencia.getContentPane().add(comboTipoLicencia);
		
		JButton emitirLicenciaButton = new JButton("Emitir Licencia");
		emitirLicenciaButton.setForeground(Color.BLACK);
		emitirLicenciaButton.setFont(new Font("Serif", Font.PLAIN, 14));
		emitirLicenciaButton.setBounds(587, 494, 155, 22);
		
		frmAltaLicencia.getContentPane().add(emitirLicenciaButton);
		
		
		JLabel tipoLicenciaLabel = new JLabel("Tipo Licencia");
		tipoLicenciaLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		tipoLicenciaLabel.setBounds(11, 214, 154, 31);
		frmAltaLicencia.getContentPane().add(tipoLicenciaLabel);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e-> {
			UIPrincipal.iniciar();
			 frmAltaLicencia.dispose();
		});
		cancelarButton.setFont(new Font("Serif", Font.PLAIN, 14));
		cancelarButton.setBounds(423, 494, 131, 22);
		frmAltaLicencia.getContentPane().add(cancelarButton);
		
		JLabel observacionesLabel = new JLabel("Observaciones");
		observacionesLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		observacionesLabel.setBounds(10, 304, 142, 31);
		frmAltaLicencia.getContentPane().add(observacionesLabel);
		
		JTextArea observacionesTextArea = new JTextArea();
		observacionesTextArea.setForeground(Color.BLACK);
		observacionesTextArea.setBackground(Color.WHITE);
		observacionesTextArea.setBounds(10, 346, 452, 100);
		frmAltaLicencia.getContentPane().add(observacionesTextArea);
		
		JLabel lblNrodoc = new JLabel("Nro documento");
		lblNrodoc.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNrodoc.setBounds(324, 11, 111, 22);
		frmAltaLicencia.getContentPane().add(lblNrodoc);
		
		JLabel lblTipodoc = new JLabel("Tipo documento");
		lblTipodoc.setFont(new Font("Serif", Font.PLAIN, 14));
		lblTipodoc.setBounds(10, 7, 111, 31);
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
	private static DTOLicencia generarDTOLicencia(String tipoLicencia, String observaciones) throws EmitirLicenciaException {
		
		
		
		DTOLicencia dto = new DTOLicencia();
		
		if(dtoTitularSeleccionado != null) {
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
