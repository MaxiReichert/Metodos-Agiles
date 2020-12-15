package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class UIPrincipal {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void iniciar () {
		final Marco frmPrincipal = new Marco(800,600,"Gestor de licencias");
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.setLocationRelativeTo(null);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAltaUsuario = new JButton("Alta usuario");
		btnAltaUsuario.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				UIAltaUsuario.iniciar();
				frmPrincipal.dispose();
			}
		});
		btnAltaUsuario.setBounds(35, 38, 170, 23);
		frmPrincipal.getContentPane().add(btnAltaUsuario);
		
		JButton btnRenovarLicencia = new JButton("Renovar licencia");
		btnRenovarLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UIRenovarLicenciaEmitirCopia.iniciar(1);
				frmPrincipal.dispose();
			}
		});
		btnRenovarLicencia.setBounds(35, 245, 170, 23);
		frmPrincipal.getContentPane().add(btnRenovarLicencia);
		
		JButton btnLicenciasVigentes = new JButton("Licencias vigentes");
		btnLicenciasVigentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIListaLicenciasVigentes.iniciar();
				frmPrincipal.dispose();
			}
		});
		btnLicenciasVigentes.setBounds(35, 367, 170, 23);
		frmPrincipal.getContentPane().add(btnLicenciasVigentes);
		
		JButton btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UIModificarUsuario.iniciar();
				frmPrincipal.dispose();
			}
		});
		btnModificarUsuario.setBounds(256, 38, 170, 23);
		frmPrincipal.getContentPane().add(btnModificarUsuario);
		
		JButton btnEmitirCopia = new JButton("Emitir copia");
		btnEmitirCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIRenovarLicenciaEmitirCopia.iniciar(2);
				frmPrincipal.dispose();
			}
		});
		btnEmitirCopia.setBounds(256, 245, 170, 23);
		frmPrincipal.getContentPane().add(btnEmitirCopia);
		
		JButton btnAltaTitular = new JButton("Alta titular");
		btnAltaTitular.setBounds(35, 98, 170, 23);
		btnAltaTitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIAltaTitular.iniciar();
				frmPrincipal.dispose();
			}
		});
		frmPrincipal.getContentPane().add(btnAltaTitular);
		
		
		JLabel lblUsuarioAutentificado = new JLabel("Usuario autentificado:");
		lblUsuarioAutentificado.setBounds(23, 11, 136, 14);
		frmPrincipal.getContentPane().add(lblUsuarioAutentificado);
		
		JButton btnEmitirLicencia = new JButton("Emitir licencia");
		btnEmitirLicencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIEmitirLicencia.iniciar();
				frmPrincipal.dispose();
			}
		});
		btnEmitirLicencia.setBounds(35, 184, 170, 23);
		frmPrincipal.getContentPane().add(btnEmitirLicencia);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "¿Desea realmente salir del sistema?",
						"Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					System.exit(0);
				}
			}
		});
		btnSalir.setBounds(569, 480, 89, 23);
		frmPrincipal.getContentPane().add(btnSalir);
		
		JButton btnLicenciasExpiradas = new JButton("Licencias expiradas");
		btnLicenciasExpiradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UIListaLicenciasExpiradas.iniciar();
				frmPrincipal.dispose();
			}
		});
		btnLicenciasExpiradas.setBounds(256, 367, 170, 23);
		frmPrincipal.getContentPane().add(btnLicenciasExpiradas);

	}
}