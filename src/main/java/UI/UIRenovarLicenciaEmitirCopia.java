package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.DTOLicencia;
import dto.DTOTitular;
import enumeradores.tipoDocumento;
import gestores.GestorLicencia;
import gestores.GestorTitular;

public class UIRenovarLicenciaEmitirCopia {
	
	private JFrame frmRenovarLicencia;
	private static JTextField tfNroDoc;
	private static JTextField tfFechaVencimiento;
	private static JTextField tfNombre;
	private static JTextField tfApellido;
	private static JTextField tfDireccion;
	private static JTextField tfFechaNacimiento;
	private static JTextField tfGrupoSanguineo;
	private static JTextField tfFactor;
	private static JTextField tfClaseLicencia;
	private static JTextField tfCostoRenovar;
	private static JButton btnBuscarTitular;
	private static ButtonGroup grupoDonador = new ButtonGroup();
	private static DTOTitular titular;
	private static DTOLicencia licencia;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
	private int aux;//para recorrer el combobox de tipo de licencia
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIRenovarLicenciaEmitirCopia window = new UIRenovarLicenciaEmitirCopia(2);
					window.frmRenovarLicencia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	/**
	 * constructor de la clase
	 * si tipo es 1 renueva licencia
	 * si tipo es 2 emite copia
	 * */
	
	public static void iniciar(int tipo) {
		final Marco frmRenovarLicencia = new Marco(800,600,"Gestor de licencias - Emitir copia");
		frmRenovarLicencia.getContentPane().setLayout(null);
		frmRenovarLicencia.setLocationRelativeTo(null);
		frmRenovarLicencia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
		//----------COMBOBOX----------//
		//capaz no es necesario, solo con el numero debería devolver la licencia
				final JComboBox comboTipoDoc = new JComboBox();
				comboTipoDoc.setBounds(146, 13, 180, 22);
				comboTipoDoc.setModel(new DefaultComboBoxModel(tipoDocumento.values()));
				frmRenovarLicencia.getContentPane().add(comboTipoDoc);
				
				JComboBox comboTipoLicencia = new JComboBox();
				comboTipoLicencia.setBounds(220, 70, 180, 22);
				comboTipoLicencia.addItem("TIPO_LIC");
				comboTipoLicencia.setSelectedItem("TIPO_LICENCIA");
				comboTipoLicencia.setVisible(false);
				comboTipoLicencia.addActionListener(new ActionListener() {
					   @Override
					   public void actionPerformed(ActionEvent e) {
					      int i=0;
					      while(!titular.getLicenciaList().get(i).getTipo().equals(comboTipoLicencia.getSelectedItem())) {
					    	  i++;
					      }
					      licencia= titular.getLicenciaList().get(i);
					      String fechaVenc=dateFormat.format(licencia.getFechaVenc());
					      tfFechaVencimiento.setText(dateFormat.format(licencia.getFechaVenc()));
					      // me falta calcular nuevo vencimiento para poder calcular el costo de renovacion
					   }
					});
				frmRenovarLicencia.getContentPane().add(comboTipoLicencia);
				
				
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
				tfFechaVencimiento.setBounds(220, 110, 181, 20);
				tfFechaVencimiento.setColumns(10);
				tfFechaVencimiento.setText("Prueba fecha vencimiento"); //traer de la bd
				tfFechaVencimiento.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfFechaVencimiento);
				
				tfNombre = new JTextField();
				tfNombre.setBounds(220, 150, 181, 20);
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
				tfApellido.setBounds(220, 190, 181, 20);
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
				tfDireccion.setBounds(220, 230, 181, 20);
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
				tfFechaNacimiento.setBounds(220, 270, 181, 20);
				tfFechaNacimiento.setText("Prueba fecha nacimiento"); //traer de la bd
				tfFechaNacimiento.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfFechaNacimiento);

				
				tfGrupoSanguineo = new JTextField();
				tfGrupoSanguineo.setBounds(220, 310, 181, 20);
				tfGrupoSanguineo.setText("Prueba grupo sanguineo"); //traer de la bd
				tfGrupoSanguineo.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfGrupoSanguineo);
				
				tfFactor = new JTextField();
				tfFactor.setColumns(10);
				tfFactor.setBounds(220, 350, 181, 20);
				tfFactor.setText("Prueba factor"); //traer de la bd
				tfFactor.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfFactor);
				
				
				/*tfClaseLicencia =new JTextField();//cambiar por combobox
				tfClaseLicencia.setBounds(220, 70, 181, 20);
				tfClaseLicencia.setColumns(10);
				tfClaseLicencia.setText("Prueba clase licencia"); //traer de la bd
				tfClaseLicencia.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfClaseLicencia);*/
				
				
				
				//----------ETIQUETAS----------//
				
				JLabel lbTipoDoc = new JLabel("Tipo documento");
				lbTipoDoc.setBounds(23, 10, 153, 22);
				lbTipoDoc.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbTipoDoc);
				
				
				JLabel lbNroDoc = new JLabel("Numero documento");
				lbNroDoc.setBounds(350, 11, 153, 22);
				lbNroDoc.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbNroDoc);
				
				JLabel lbEstadoTitular = new JLabel("");
				lbEstadoTitular.setBounds(489, 35, 450, 25);
				lbEstadoTitular.setFont(new Font("Serif", Font.BOLD, 15));
				frmRenovarLicencia.getContentPane().add(lbEstadoTitular);	
				//hacer un if, si se encontro el titular mostrar
				
				JLabel lbEstadoLicencia = new JLabel("");
				lbEstadoLicencia.setBounds(220, 35, 279, 25);
				lbEstadoLicencia.setFont(new Font("Serif", Font.BOLD, 15));
				frmRenovarLicencia.getContentPane().add(lbEstadoLicencia);	

				JLabel lbFechaVencimiento = new JLabel("Fecha de vencimiento");
				lbFechaVencimiento.setBounds(25, 110, 153, 22);
				lbFechaVencimiento.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbFechaVencimiento);
				
				
				JLabel lbNombre = new JLabel("Nombre");
				lbNombre.setBounds(25, 150, 151, 22);
				lbNombre.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbNombre);
				
				
				JLabel lbApellido = new JLabel("Apellido");
				lbApellido.setBounds(25, 190, 151, 22);
				lbApellido.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbApellido);
				
				JLabel lbFechaNac = new JLabel("Fecha de nacimiento");
				lbFechaNac.setBounds (25, 270, 151, 22);
				lbFechaNac.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbFechaNac);
				
				JLabel lbDireccion = new JLabel("Direccion");
				lbDireccion.setBounds (25, 230, 124, 22);
				lbDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbDireccion);
				
				JLabel lbClaseSolicitada = new JLabel("Clase de Licencia");
				lbClaseSolicitada.setBounds (25, 70, 151, 22);
				lbClaseSolicitada.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbClaseSolicitada);
				
				JLabel lbFactorSanguineo = new JLabel("Factor sanguineo");
				lbFactorSanguineo.setBounds (25, 350, 140, 22);
				lbFactorSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbFactorSanguineo);
			
				JLabel lbGrupoSanguineo = new JLabel("Grupo sanguineo");
				lbGrupoSanguineo.setBounds (25, 310, 151, 22);
				lbGrupoSanguineo.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbGrupoSanguineo);
				
				JLabel lbDonante = new JLabel("Donante de organos:");
				lbDonante.setBounds(25, 390, 174, 22);
				lbDonante.setFont(new Font("Serif", Font.PLAIN, 14));
				frmRenovarLicencia.getContentPane().add(lbDonante);
				
				JRadioButton rbDonanteNo = new JRadioButton("No");
				rbDonanteNo.setBounds(220, 390, 64, 23);
				rbDonanteNo.setSelected(true);//traer la selección de la base de datos
				grupoDonador.add(rbDonanteNo);
				frmRenovarLicencia.getContentPane().add(rbDonanteNo);
				
				JRadioButton rbDonanteSi = new JRadioButton("Si");
				rbDonanteSi.setBounds(284, 390, 57, 23);
				grupoDonador.add(rbDonanteSi);
				frmRenovarLicencia.getContentPane().add(rbDonanteSi);
				
				JLabel lbCostoRenovacion = new JLabel("Costo de renovación:");
				lbGrupoSanguineo.setFont(new Font("Serif", Font.BOLD, 16));
				lbCostoRenovacion.setBounds(25, 450, 150, 22);
				frmRenovarLicencia.getContentPane().add(lbCostoRenovacion);
				
				tfCostoRenovar = new JTextField();
				tfCostoRenovar.setBounds(220, 450, 150, 22);
				tfCostoRenovar.setEditable(false);
				frmRenovarLicencia.getContentPane().add(tfCostoRenovar);

				
				

				
				
				
				//-------------BOTONES------------//
				
				JButton btnBuscarTitular = new JButton("Buscar");
				btnBuscarTitular.setBounds(675, 14, 89, 19);
				frmRenovarLicencia.getContentPane().add(btnBuscarTitular);
				
				btnBuscarTitular.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(tfNroDoc.getText().length()>3) {
							
							try {
								GestorTitular gestorT = GestorTitular.getInstance();
								
								if(gestorT.existeTitular(tfNroDoc.getText())) {// verifico que exista el titular
									titular = gestorT.obtenerTitularCompleto(tfNroDoc.getText());// busco titular
									lbEstadoTitular.setText('['+titular.getTipoDoc()+"] " + titular.getNroDoc() + " - "+titular.getApellido()+", "+titular.getNombre());
									lbEstadoTitular.setForeground(Color.GREEN);
									tfNombre.setText(titular.getNombre()); //luego de encontrada la licencia seteo todos los texteFields
									tfApellido.setText(titular.getApellido());
									tfDireccion.setText(titular.getDireccion());
									tfFechaNacimiento.setText(""+(titular.getFechaNac()));
									tfGrupoSanguineo.setText(titular.getGrupoS());
									tfFactor.setText(titular.getFactorS());
									if(titular.getDonador()) {
										rbDonanteSi.setSelected(true);
									}
									else {
										rbDonanteNo.setSelected(true);
									}
									//buscar licencia
									GestorLicencia gestorL = GestorLicencia.getInstance();
									if(gestorL.existeLicenciaRenovar(titular.getNroDoc())) {
										gestorL.obtenerLicencia(titular.getNroDoc(), titular); //metodo void que le asigna una lista de licenciasDTO a titular en su lista de licenciasDTO
										lbEstadoLicencia.setText("Se encontró una o más licencias"); //falta validacion si no se encuentra licencia para ese titular
										lbEstadoLicencia.setForeground(Color.GREEN);
										comboTipoLicencia.setVisible(true);
										for(int i=0;i<titular.getLicenciaList().size();i++) {
											comboTipoLicencia.addItem(titular.getLicenciaList().get(i).getTipo()); //agrego los tipos de licencia al combo
										}
									}
									else {
										lbEstadoLicencia.setText("NO HAY LICENCIAS PARA RENOVAR");
										lbEstadoLicencia.setForeground(Color.RED);
										lbEstadoLicencia.setVisible(true);
									}
								}
								else {
									lbEstadoTitular.setText("TITULAR NO ENCONTRADO");
									lbEstadoTitular.setForeground(Color.RED);
								}
							} catch (Exception ex){
								lbEstadoTitular.setText("ERROR DEL SISTEMA AL BUSCAR EL TITULAR");
								lbEstadoTitular.setForeground(Color.RED);
								System.err.println(ex.getMessage());
							}
							
						}
					}
				});
				
				JButton btnRenovarOCopia = new JButton(); //boton renovar licencia o emitir copia
				if(tipo==1) {
					btnRenovarOCopia.setText("Renovar");
				}
				else {
					btnRenovarOCopia.setText("Emitir Copia");
				}
				btnRenovarOCopia.setBounds(660, 493, 92, 23);
				frmRenovarLicencia.getContentPane().add(btnRenovarOCopia);
				ActionListener AccionCrear = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) { 
						boolean completo = true;
						//Chequeo que esten completos los campos y seleccionado en las combo box
						//TextFields
						
						if (tfNombre.getText().length() == 0) {
							completo = false;
						} 
					
						if (tfApellido.getText().length() == 0) {
							completo = false;
						} 
					
						
						if (tfDireccion.getText().length() == 0) {
							completo = false;
						} 
					
						//ComboBox
						if (comboTipoLicencia.getSelectedIndex() == 0){ //ver esta condicion porque no me funciono lo del combobox
							completo = false;
						};
						
						if (completo) { //Si todos los campos se completaron
							licencia.setTitular(titular);
							GestorLicencia gestorL= GestorLicencia.getInstance();
							try {// renuevo la licencia y muestro un mensaje de exito
								if(tipo==1) {
									gestorL.renovarLicencia(licencia);
									lbEstadoLicencia.setText("LA LICENCIA SE HA RENOVADO CON EXITO");
									lbEstadoLicencia.setForeground(Color.GREEN);
									lbEstadoLicencia.setVisible(true);
								}
								else {
									gestorL.emitirCopia(licencia);
									lbEstadoLicencia.setText("SE HA EMITIDO UNA COPIA CON EXITO");
									lbEstadoLicencia.setForeground(Color.GREEN);
									lbEstadoLicencia.setVisible(true);
								}
								
							} catch (Exception e1) {// si se produce una excepcion muestro un error
								lbEstadoLicencia.setText("ERROR DEL SISTEMA AL BUSCAR EL TITULAR");
								lbEstadoLicencia.setForeground(Color.RED);
								System.err.println(e1.getMessage());
							}
							
						}
						else { //Si no estan todos completos
							lbEstadoLicencia.setText("NO DEJAR CAMPOS VACIOS O SIN SELECCIOANR");
							lbEstadoLicencia.setForeground(Color.RED);
							lbEstadoLicencia.setVisible(true);
						}
					}
				};
				btnRenovarOCopia.addActionListener(AccionCrear);
				frmRenovarLicencia.getContentPane().add(btnRenovarOCopia);
				
				
				
				JButton btnAtras = new JButton("Atras");
				btnAtras.addActionListener(new ActionListener() {
					   @Override
					public void actionPerformed(ActionEvent e) {
						UIPrincipal.iniciar();
						frmRenovarLicencia.dispose();
					}
				});
				btnAtras.setBounds(545, 493, 89, 23);
				frmRenovarLicencia.getContentPane().add(btnAtras);
			}
}

