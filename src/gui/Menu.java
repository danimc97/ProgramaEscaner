package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utils.CacheImagenes;

public class Menu extends JMenuBar {

	
	public Menu () {
		
		JMenu menu = new JMenu("Abrir");
		menu.add(crearMenuItemLanzamientoPanelGestionComoJDialog("Programador de tareas","ruedadentada.png", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try 
				{ 
				   Process p = Runtime.getRuntime().exec ("cmd /c %windir%\\system32\\taskschd.msc /s"); 
				} 
				catch (Exception e) 
				{ 
				   JOptionPane.showMessageDialog(null, "No se ha podido abrir el programador de Tareas");
				}			
			}
		}));
		this.add(menu);
		
	}
	
	private JMenuItem crearMenuItemLanzamientoPanelGestionComoJDialog (String titulo,String icono, ActionListener actionListener) {
		JMenuItem mi = new JMenuItem(titulo);
		mi.addActionListener(actionListener);
		mi.setIcon(CacheImagenes.getCacheImagenes().getIcono(icono));
		return mi;
	}
	
}
