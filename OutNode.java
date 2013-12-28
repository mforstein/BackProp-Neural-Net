 
import java.io.Serializable;

public class OutNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inputkey;
	private Object Inputs;
	private connections<ConnectionLine> lines;
	private connections<Weight> weights;

	public OutNode(Integer mkey, Object InData) {
		inputkey = mkey;
		Inputs = InData;
		weights = new connections<Weight>();

	}

	public Object GetOnode(Integer mkey) {
		if ((mkey != null) && (mkey == inputkey)) {
			return Inputs;
		} else {
			return null;
		}
	}

	public void SetOnode(Integer mkey, Object InData) {
		inputkey = mkey;
		Inputs = InData;

	}

	public connections<Weight> GetWeights() {
		return weights;
	}

	public void SetWeights(connections<Weight> w) {
		int ws = w.size();
		for (int i = 0; i < ws; i++) {
			weights.add(w.get(i));
		}
	}

	public void clearWeights(int sz) {
		int ws = sz;
		for (int i = 0; i < ws; i++) {
			weights.remove(i);
		}
	}

	public Weight GetWeight(int i) {
		return weights.get(i);
	}

	public void SetWeight(int i, Weight newW) {
		weights.set(i, newW);
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
