package lib;

import javax.swing.JOptionPane;

public class Mensajes extends JOptionPane{
	public static void dialogo(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	public static void error(String str) {
		JOptionPane.showMessageDialog(null, str,"No se pudo cumplir la operacion", ERROR_MESSAGE);
	}
	public static int confirmarELiminar() {
		int s = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer eliminar el registro?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return s;
	}
	public static int confirmarRechazo() {
		int s = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer rechazar este documento?", "Confirmar Rechazo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return s;
	}
}
