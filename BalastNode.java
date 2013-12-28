 
import java.io.Serializable;

public class BalastNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double BalastWeight = 0.5;
	private connections<ConnectionLine> lines;

	public BalastNode(double val) {

		this.BalastWeight = val;
	}

	public double getBalast() {
		return BalastWeight;
	}

	public void setBalast(double val) {

		BalastWeight = val;

	}

	/**
	 * @return the lines
	 */
	public connections<ConnectionLine> getLines() {
		return lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public void setLines(connections<ConnectionLine> lines) {
		this.lines = lines;
	}

}
