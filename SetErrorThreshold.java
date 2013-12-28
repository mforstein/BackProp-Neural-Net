 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * DBDialog Class (from example) CM335
 */
public class SetErrorThreshold extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField errorthreshold;
	private JTextField Iterationsthreshold;
	private JButton okButton;
	private JButton cancelButton;
	private boolean ok;

	public SetErrorThreshold(JFrame parent) {
		super(parent, "Set Error Threshold", true);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 2));
		mainPanel.add(new JLabel("Error Threshold:"));
		errorthreshold = new JTextField("1.0", 20);
		mainPanel.add(new JLabel("Iterations Threshold:"));
		Iterationsthreshold = new JTextField("100000", 20);

		mainPanel.add(errorthreshold);
		mainPanel.add(Iterationsthreshold);
		add(mainPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateP()) {
					ok = true;
					setVisible(false);
				}
			}
		});
		buttonPanel.add(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ok = false;
				setVisible(false);
			}
		});
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		setSize(300, 200);
	}

	public String getError() {
		return new String(errorthreshold.getText());
	}

	public String getIterthreshold() {
		return new String(Iterationsthreshold.getText());
	}

	public boolean getOK() {
		return ok;
	}

	public boolean validateP() {
		boolean test = true;
		if ((getError() == "") || (getIterthreshold() == "")) {
			JOptionPane.showMessageDialog(null,
					"Enter Values for Threshold and Iterations.");
			test = false;
		} else {
			if (!isInteger(getIterthreshold())) {
				JOptionPane.showMessageDialog(null,
						"Enter a Whole Number (1,2,3...) for Iterations.");
				test = false;
			}
			if (!isDouble(getError())) {
				JOptionPane
						.showMessageDialog(null,
								"Enter a Decimal Value (1.0,2.0,3.0...) for Error Threshold.");
				test = false;
			}

		}
		return test;
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}

	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
}
