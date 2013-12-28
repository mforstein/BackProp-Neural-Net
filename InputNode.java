 
import java.io.Serializable;

public class InputNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inputkey;
	private Object Inputs;
	private connections<ConnectionLine> lines;

	public InputNode(Integer ikey, Object InData) {
		inputkey = ikey;
		Inputs = InData;

	}

	public Integer GetKey() {
		return inputkey;
	}

	public Object GetInode(Integer ikey) {
		if ((ikey != null) && (ikey == inputkey)) {
			return Inputs;
		} else {
			return null;
		}
	}

	public void SetInode(Integer ikey, Object InData) {
		inputkey = ikey;
		Inputs = InData;

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
