package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import utils.CacheImagenes;

public class VentanaPrincipal extends JFrame {

	public static VentanaPrincipal instancia=null;
	
	public VentanaPrincipal() {		
		
		this.setJMenuBar(new Menu());
		
		this.add(new GestionEscaner());		
		
		this.setIconImage(CacheImagenes.getCacheImagenes().getImagen("canon.png"));
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();
		
		int alturaPantalla = tamanoPantalla.height;
		
		int anchoPantalla = tamanoPantalla.width;
		
		setSize(anchoPantalla/2, alturaPantalla/2);
		
		setLocation(anchoPantalla/4, alturaPantalla/4);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Gestión de escáner");
		
		setResizable(true);
		
				
	}
	
	public static VentanaPrincipal getInstancia() {
		
		if(instancia==null) {
			
			instancia= new VentanaPrincipal();
			
		}		
		return instancia;
	}
	
}
