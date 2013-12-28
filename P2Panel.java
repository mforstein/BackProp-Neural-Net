 
import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class P1Panel here.
 * 
 * @author Micah Forstein Project 2 fixed
 * @version Sept 22, 2011
 */
public class P2Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// instance variables - replace the example below with your own
	private JTextField textField;
	private JLabel P2Label;// creates a jLabel to display

	/**
	 * Constructor for objects of class P1Panel
	 */

	public P2Panel(String labelstring, int field) {
		setLayout(new FlowLayout());
		// initialise instance variables
		textField = new JTextField(field);
		JLabel Label = new JLabel(labelstring);
		P2Label = Label;
		this.add(P2Label);
		this.add(textField);
	}

	public String getTextentry() {
		return this.textField.getText();
	}

	public void setTextentry(String passtext) {
		this.textField.setText(passtext);
	}

	public void selectAlltext() {
		this.textField.selectAll();
	}
}
