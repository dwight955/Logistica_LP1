package lib;

import javax.swing.JOptionPane;

public class Mensajes extends JOptionPane{
	public static void dialogo(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	public static void errorRegistro(String str) {
		JOptionPane.showMessageDialog(null, str,"ERROR", ERROR_MESSAGE);
	}
}
