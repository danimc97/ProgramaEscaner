package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar {

	
	public Menu () {
		
		JMenu menu = new JMenu("Abrir");
		menu.add("Programador de Tareas");
		this.add(menu);
		
	}
	
}
