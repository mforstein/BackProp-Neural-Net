 
import java.io.Serializable;

public class ConnectionLine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object fromNode = null;
	private Object toWeight = null;

	public Object getfrom() {
		return fromNode;
	}

	public void setfrom(Object fnode) {
		fromNode = fnode;
	}

	public Object getTo() {
		return toWeight;

	}

	public void setTo(Object whichWeight) {
		toWeight = whichWeight;
	}
}
