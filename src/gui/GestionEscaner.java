package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import utils.CacheImagenes;

import javax.security.auth.callback.TextInputCallback;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class GestionEscaner extends JPanel {
	private JTextField textField_Origen;
	private JTextField textField_Destino;
	private JTextField textField_General;
	JFileChooser jfileChooser;
	String directorioGuardado;
	String directorioOrigen;
	String directorioDestino;
	String rutaOrigen;
	String rutaDestino;
	String rutaGeneral;
	int funciona=0;
	File archivoTarea= new File("");
	File archivoRevisor;
	File archivoCnfg;
	private JTextField textField_IP;
	JRadioButton rdbtnNo;
	JRadioButton rdbtnSi;
	JButton btnEjecutar;
	

	public GestionEscaner() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{39, 0, 0, 0, 36, 0};
		gridBagLayout.rowHeights = new int[]{38, 28, 0, 31, 0, 0, 0, 42, 37, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblIntroduceLaIp = new JLabel("Introduce la ip de la máquina del cliente:");
		GridBagConstraints gbc_lblIntroduceLaIp = new GridBagConstraints();
		gbc_lblIntroduceLaIp.anchor = GridBagConstraints.EAST;
		gbc_lblIntroduceLaIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntroduceLaIp.gridx = 1;
		gbc_lblIntroduceLaIp.gridy = 1;
		add(lblIntroduceLaIp, gbc_lblIntroduceLaIp);
		
		
		textField_IP = new JTextField();
		textField_IP.setEnabled(false);
		GridBagConstraints gbc_textField_IP = new GridBagConstraints();
		gbc_textField_IP.insets = new Insets(0, 0, 5, 5);
		gbc_textField_IP.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_IP.gridx = 2;
		gbc_textField_IP.gridy = 1;
		add(textField_IP, gbc_textField_IP);
		textField_IP.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Seleccionar directorio donde guardar los archivos:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField_General = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		add(textField_General, gbc_textField_2);
		textField_General.setColumns(10);
		
		JButton btnSeleccionarDGA = new JButton("Seleccionar...");
		GridBagConstraints gbc_btnSeleccionar_2 = new GridBagConstraints();
		gbc_btnSeleccionar_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionar_2.gridx = 3;
		gbc_btnSeleccionar_2.gridy = 3;
		add(btnSeleccionarDGA, gbc_btnSeleccionar_2);
		btnSeleccionarDGA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarFichero1();
				rutaGeneral=textField_General.getText();				
			}
		});
		
		JLabel lblelDirectorioOrigen = new JLabel("\u00BFEl directorio origen es una ubicaci\u00F3n de red?");
		GridBagConstraints gbc_lblelDirectorioOrigen = new GridBagConstraints();
		gbc_lblelDirectorioOrigen.anchor = GridBagConstraints.EAST;
		gbc_lblelDirectorioOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_lblelDirectorioOrigen.gridx = 1;
		gbc_lblelDirectorioOrigen.gridy = 4;
		add(lblelDirectorioOrigen, gbc_lblelDirectorioOrigen);
		
		rdbtnSi = new JRadioButton("S\u00ED");
		GridBagConstraints gbc_rdbtnSi = new GridBagConstraints();
		gbc_rdbtnSi.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnSi.gridx = 2;
		gbc_rdbtnSi.gridy = 4;
		add(rdbtnSi, gbc_rdbtnSi);
		rdbtnSi.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnNo.setSelected(false);
				rdbtnSi.setSelected(true);
				textField_IP.setEnabled(true);				
			}
		});
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNo.gridx = 3;
		gbc_rdbtnNo.gridy = 4;
		add(rdbtnNo, gbc_rdbtnNo);
		rdbtnNo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnSi.setSelected(false);
				rdbtnNo.setSelected(true);
				textField_IP.setEnabled(false);				
			}
		});
		
		JLabel lblDirectorioOrigenDe = new JLabel("Directorio origen de los escaners:");
		GridBagConstraints gbc_lblDirectorioOrigenDe = new GridBagConstraints();
		gbc_lblDirectorioOrigenDe.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirectorioOrigenDe.anchor = GridBagConstraints.EAST;
		gbc_lblDirectorioOrigenDe.gridx = 1;
		gbc_lblDirectorioOrigenDe.gridy = 5;
		add(lblDirectorioOrigenDe, gbc_lblDirectorioOrigenDe);
		
		textField_Origen = new JTextField();
		GridBagConstraints gbc_textField_Origen = new GridBagConstraints();
		gbc_textField_Origen.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Origen.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Origen.gridx = 2;
		gbc_textField_Origen.gridy = 5;
		add(textField_Origen, gbc_textField_Origen);
		textField_Origen.setColumns(10);
		textField_Origen.setText("\\");
		
		JButton btnSeleccionarO = new JButton("Seleccionar...");
		GridBagConstraints gbc_btnSeleccionarO = new GridBagConstraints();
		gbc_btnSeleccionarO.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionarO.gridx = 3;
		gbc_btnSeleccionarO.gridy = 5;
		add(btnSeleccionarO, gbc_btnSeleccionarO);
		btnSeleccionarO.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarFicheroOrigen();
				rutaOrigen=textField_Origen.getText();
			}
		});
		
		JLabel lblDirectorioDeDestino = new JLabel("Directorio de destino de los escaners:");
		GridBagConstraints gbc_lblDirectorioDeDestino = new GridBagConstraints();
		gbc_lblDirectorioDeDestino.anchor = GridBagConstraints.EAST;
		gbc_lblDirectorioDeDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblDirectorioDeDestino.gridx = 1;
		gbc_lblDirectorioDeDestino.gridy = 6;
		add(lblDirectorioDeDestino, gbc_lblDirectorioDeDestino);
		
		textField_Destino = new JTextField();
		GridBagConstraints gbc_textField_Destino = new GridBagConstraints();
		gbc_textField_Destino.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Destino.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Destino.gridx = 2;
		gbc_textField_Destino.gridy = 6;
		add(textField_Destino, gbc_textField_Destino);
		textField_Destino.setColumns(10);
		
		JButton btnSeleccionarD = new JButton("Seleccionar...");
		GridBagConstraints gbc_btnSeleccionarD = new GridBagConstraints();
		gbc_btnSeleccionarD.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionarD.gridx = 3;
		gbc_btnSeleccionarD.gridy = 6;
		add(btnSeleccionarD, gbc_btnSeleccionarD);
		btnSeleccionarD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarFicheroDestino();
				rutaDestino=textField_Destino.getText();
			}
		});
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setEnabled(false);
		GridBagConstraints gbc_btnEjecutar = new GridBagConstraints();
		gbc_btnEjecutar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEjecutar.gridx = 1;
		gbc_btnEjecutar.gridy = 8;
		add(btnEjecutar, gbc_btnEjecutar);
		btnEjecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ruta=archivoTarea.getPath();
				Runtime aplicacion = Runtime.getRuntime();
				try {
					if(!ruta.equals("\\tarea.bat") && !ruta.equals("")) {
						aplicacion.exec("cmd /c start "+archivoTarea);
						JOptionPane.showMessageDialog(null, "Tarea ejecutada.");
					}
					else {
						JOptionPane.showMessageDialog(null, "No se ha encontrado la tarea. Revise la configuración");
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "No se ha podido ejecutar la tarea. Si el problema persiste, contacte con el administrador");
//					e1.printStackTrace();
				}
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 8;
		add(btnGuardar, gbc_btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String respuestas[] = new String[] {"Si", "No"};
				int opcionElegida = JOptionPane.showOptionDialog(null, 
						"¿Desea guardar los cambios?", "Guardar registros", 
				        JOptionPane.OK_CANCEL_OPTION, 
				        JOptionPane.OK_CANCEL_OPTION, 
				        CacheImagenes.getCacheImagenes().getIcono("confirm.png"), 
				        respuestas, respuestas[1]);
				if(opcionElegida==0) {
					funciona=0;
					rutaGeneral=textField_General.getText().trim();
					rutaDestino=textField_Destino.getText().trim();
					rutaOrigen=textField_Origen.getText().trim();
					generarRevisor();
					if(funciona==1) {
						generarCnfg();
					}
					
					if(funciona==2) {
						generarTarea();
					}
					
					if(funciona==3) {
						JOptionPane.showMessageDialog(null, "Guardado correcto");
						btnEjecutar.setEnabled(true);
						
					}
				}
			}
		});
		
		
	}
	
	public void generarRevisor() {
		
		archivoRevisor = new File(this.rutaGeneral+"\\revisor.bat");
//		System.out.println(archivoRevisor.getAbsoluteFile());
		String ruta=archivoRevisor.getPath();
		
		try {
			if(!ruta.equals("\\revisor.bat") && !rutaOrigen.equals("") && !rutaDestino.equals("")) {
				BufferedWriter bw= new BufferedWriter(new FileWriter(archivoRevisor));
				if(rdbtnNo.isSelected()){
					bw.write("@echo off\r\n" +
							"\r\n" + 
							"\r\n" + 
							"forfiles /p "+this.rutaOrigen+" /S /M *.pdf /c \"cmd /c move @file "+this.rutaDestino+(char)34+"\r\n" + 
							"\r\n" + 
							"exit");
					bw.close();
					funciona++;
				}
				else {
					if(!textField_IP.getText().trim().equals("")) {
						bw.write("@echo off\r\n" + 
								"\r\n" + 
								"PUSHD \\\\"+textField_IP.getText().trim()+"\\file_share\r\n" +
								"\r\n" + 
								"\r\n" + 
								"forfiles /p "+this.rutaOrigen+" /S /M *.pdf /c \"cmd /c move @file "+this.rutaDestino+(char)34+"\r\n" + 
								"\r\n" + 
								"POPD\r\n" + 
								"\r\n" +
								"exit");
						bw.close();
						funciona++;
					}
					else {
						JOptionPane.showMessageDialog(null, "El campo ip no puede estar vacío");
					}
				}
				
			}
			else {
				
				if(ruta.equals("\\revisor.bat")) {
					JOptionPane.showMessageDialog(null, "Directorio de los archivos no especificado");
				}
				else {
					if(rutaOrigen.equals("")) {
						
						JOptionPane.showMessageDialog(null, "El campo de la ruta de origen no puede estar en blanco.");
					}
					else {
						if(rutaDestino.equals("")) {
							
							JOptionPane.showMessageDialog(null, "El campo de la ruta de destino no puede estar en blanco.");
						}
					}
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han podido crear los archivos. Permisos insuficientes");
//			e.printStackTrace();
		}
		
		
		
	}
	
	public void generarCnfg() {
		
		archivoCnfg = new File(this.rutaGeneral+"\\cnfg.vbs");
//		System.out.println(archivoCnfg.getAbsoluteFile());
		
		String ruta=archivoCnfg.getPath();
		
		try {
			if(!ruta.equals("\\cnfg.vbs")) {
				BufferedWriter bw= new BufferedWriter(new FileWriter(archivoCnfg));
				
				bw.write("Set WshShell = CreateObject(\"WScript.Shell\")\r\n" + 
						"WshShell.Run chr(34) & "+(char)34+archivoRevisor.getAbsoluteFile()+(char)34+" & Chr(34), 0\r\n" +
						"Set WshShell = Nothing ");
				bw.close();
				funciona++;
			}
			else {				
				JOptionPane.showMessageDialog(null, "Directorio de los archivos no especificado");
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han podido crear los archivos. Permisos insuficientes");
//			e.printStackTrace();
		}
		
		
		
	}
	
	public void generarTarea() {
		
		archivoTarea = new File(this.rutaGeneral+"\\tarea.bat");
//		System.out.println(archivoTarea.getAbsoluteFile());
		
		String ruta=archivoTarea.getPath();
		
		try {
			if(!ruta.equals("\\tarea.bat")) {
				BufferedWriter bw= new BufferedWriter(new FileWriter(archivoTarea));
				
				bw.write("@echo off\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"schtasks /create /SC minute /MO 1 /TN tareaEscaner /TR "+archivoCnfg.getAbsoluteFile()+"\r\n"
								+ "\r\n"
								+ "exit");
				bw.close();
				funciona++;
			}
			else {
				JOptionPane.showMessageDialog(null, "Directorio de los archivos no especificado");
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se han podido crear los archivos. Permisos insuficientes");
//			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void seleccionarFichero1() {
			
	
			this.jfileChooser = new JFileChooser();
			
			// Configurando el componente
			
			// Establecimiento de la carpeta de inicio
			this.jfileChooser.setCurrentDirectory(new File("C:\\"));
			
			// Tipo de selecciï¿½n que se hace en el diï¿½logo
//			this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Sï¿½lo selecciona ficheros
			this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sï¿½lo selecciona ficheros
//			this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Selecciona ficheros y carpetas
			
			int seleccionUsuario = jfileChooser.showOpenDialog(null);
			
			if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
				 
				this.textField_General.setText(""+jfileChooser.getSelectedFile().getAbsoluteFile());
				this.directorioGuardado=""+jfileChooser.getSelectedFile().getAbsoluteFile();
				
				
			}
		
	}
	
	public void seleccionarFicheroOrigen() {
		
		
		this.jfileChooser = new JFileChooser();
		
		// Configurando el componente
		
		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));
		
		// Tipo de selecciï¿½n que se hace en el diï¿½logo
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Sï¿½lo selecciona ficheros
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sï¿½lo selecciona ficheros
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Selecciona ficheros y carpetas
		
		// Filtro del tipo de ficheros que puede abrir
//		this.jfileChooser.setFileFilter(new FileFilter() {
//			
//			@Override
//			public String getDescription() {
//				return "";
//			}
//			
//			@Override
//			public boolean accept(File f) {
//				if (f.isDirectory()) 
//					return true;
//				return false;
//			}
//		});
		
		int seleccionUsuario = jfileChooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			 
			this.textField_Origen.setText(""+jfileChooser.getSelectedFile().getAbsoluteFile());
			this.directorioOrigen=""+jfileChooser.getSelectedFile().getAbsoluteFile();
			
		}
	
	}
	
	public void seleccionarFicheroDestino() {
		
		
		this.jfileChooser = new JFileChooser();
		
		// Configurando el componente
		
		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));
		
		// Tipo de selecciï¿½n que se hace en el diï¿½logo
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Sï¿½lo selecciona ficheros
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Sï¿½lo selecciona ficheros
//		this.jfileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Selecciona ficheros y carpetas
		
		// Filtro del tipo de ficheros que puede abrir
//		this.jfileChooser.setFileFilter(new FileFilter() {
//			
//			@Override
//			public String getDescription() {
//				return "";
//			}
//			
//			@Override
//			public boolean accept(File f) {
//				if (f.isDirectory()) 
//					return true;
//				return false;
//			}
//		});
		
		int seleccionUsuario = jfileChooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			 
			this.textField_Destino.setText(""+jfileChooser.getSelectedFile().getAbsoluteFile());
			this.directorioDestino=""+jfileChooser.getSelectedFile().getAbsoluteFile();
			
		}
	
	}
	
}
