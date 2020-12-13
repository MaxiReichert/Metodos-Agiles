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
	private JTextField tfCostoRenovar;
	private JButton btnBuscarTitular;
	private ButtonGroup grupoDonador = new ButtonGroup();
	private DTOTitular titular;
	private DTOLicencia licencia;
	private int aux;//para recorrer el combobox de tipo de licencia
	
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
		
		JComboBox comboTipoLicencia = new JComboBox();
		comboTipoLicencia.setBounds(220, 70, 180, 22);
		comboTipoLicencia.addItem("TIPO_LIC");
		comboTipoLicencia.setSelectedItem("TIPO_LICENCIA");
		comboTipoLicencia.setVisible(false);
		frmRenovarLicencia.getContentPane().add(comboTipoLicencia);
		
		comboTipoLicencia.addActionListener(new ActionListener() { //cambiar la fecha de vencimiento y tipo para calcular el costo, segun la opcion elegida
			@Override
			public void actionPerformed(ActionEvent e) {
				aux = 0;
				while(titular.getLicenciaList().get(aux).getTipo().equals(comboTipoLicencia.getSelectedItem())!=true) { //no funciona bien, se queda en un bucle infinito, OJO
					
					aux++;
				}
				tfFechaVencimiento.setText(""+titular.getLicenciaList().get(aux).getFechaVenc());
			}
		});
		
		
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
		
		JLabel lbEstadoTitular = new JLabel("Prueba se encontro titular o no");
		lbEstadoTitular.setBounds(489, 35, 450, 25);
		lbEstadoTitular.setFont(new Font("Serif", Font.BOLD, 15));
		frmRenovarLicencia.getContentPane().add(lbEstadoTitular);	
		//hacer un if, si se encontro el titular mostrar
		
		JLabel lbEstadoLicencia = new JLabel("Prueba se encontro licencia o no");
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
		
		// ----- MAXI -----//
		//aca habría que traer el costo llamando a la tarea calcular costo y mostrarlo por pantalla seteandoselo a un JTextField por ejemplo
		tfCostoRenovar = new JTextField();
		tfCostoRenovar.setBounds(220, 450, 150, 22);
		tfCostoRenovar.setText("Prueba costo renovar"); 
		tfCostoRenovar.setEditable(false);
		frmRenovarLicencia.getContentPane().add(tfCostoRenovar);

		
		

		
		
		
		//-------------BOTONES------------//
		
		JButton btnBuscarTitular = new JButton("Buscar");
		btnBuscarTitular.setBounds(675, 14, 89, 19);
		frmRenovarLicencia.getContentPane().add(btnBuscarTitular);
		
		comboTipoLicencia.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
			      int i=0;
			      while(titular.getLicenciaList().get(i).getTipo().equals(comboTipoLicencia.getSelectedItem())) {
			    	  
			      }
			   }
			});
		
	
		
		btnBuscarTitular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfNroDoc.getText().length()>3) {
					
					try {
						titular = new DTOTitular();
						GestorTitular gestorT = GestorTitular.getInstance();
						titular = gestorT.obtenerTitularCompleto(tfNroDoc.getText());
						if(titular!=null) {
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
							licencia = new DTOLicencia();
							licencia.setTitular(titular);
							GestorLicencia gestorL = GestorLicencia.getInstance();
							gestorL.obtenerLicencia(titular.getNroDoc(), titular); //metodo void que le asigna una lista de licenciasDTO a titular en su lista de licenciasDTO
							lbEstadoLicencia.setText("Se encontró una o más licencias"); //falta validacion si no se encuentra licencia para ese titular
							lbEstadoLicencia.setForeground(Color.GREEN);
							comboTipoLicencia.setVisible(true);
							for(int i=0;i<titular.getLicenciaList().size();i++) {
								comboTipoLicencia.addItem(titular.getLicenciaList().get(i).getTipo()); //agrego los tipos de licencia al combo
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
		
		JButton btnRenovar = new JButton("Renovar"); //boton renovar licencia
		btnRenovar.setBounds(489, 510, 181, 30);
		frmRenovarLicencia.getContentPane().add(btnRenovar);
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
			
				//ComprobarFecha
				/*if (tfFechaVencimiento.getText()//<fecha actual) { //pasar la fecha a formato date y comprobar si vencio
					completo = false;						//si vencio hay que hacer la persistencia de la nueva licencia renovada con la fecha de vencimiento calculad por la tarea de calcular vencimiento
				}*/
				if (completo) { //Si todos los campos se completaron
					/* HACER LA PERSISTENCIA
					 * DAR DE BAJA LOGICA EL REGISTRO QUE TRAJIMOS DESDE LA BD
					 * QUIZAS SE DEBA CREAR UNA LICENCIADTO COMO AUXILIAR PARA METER TODAS LAS MODIFICACIONS AHI Y ENVIARLO PARA CREAR EN LA BASE DE DATOS
					 * CREO QUE SE PODRIA LLAMAR A ALGUNA FUNCION DEL GESTOR LICENCIA PARA CREAR UNA NUEVA
					 * DAR DE ALTA UN NUEVO REGISTRO CON LOS CAMPOS ACTUALIZADOS (SEA NOMBRE, APELLIDO, DIRECCION, DONANTE O FECHA DE VENCIMIENTO)
					
					*/
				}
				else { //Si no estan todos completos
					//setear un lbl de EstadoLicencia (esta definido mas arriba lbEstadoLicencia) indicando el error
				}
			}
		};
		btnRenovar.addActionListener(AccionCrear);
		frmRenovarLicencia.getContentPane().add(btnRenovar);
		
		
		
		JButton btnAtras = new JButton("Atrás"); //boton atrás, FALTA HACERLO FUNCIONAR
		btnAtras.setBounds(150, 510, 181, 30);
		frmRenovarLicencia.getContentPane().add(btnAtras);
	}
	
	
	
	
	
	
	
	
	
	
	
}
