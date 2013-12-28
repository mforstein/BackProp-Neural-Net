 
import java.io.Serializable;

public class MidNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inputkey;
	private Object Inputs;
	// private connections<ConnectionLine> lines;
	private connections<Weight> weights;

	public MidNode(Integer mkey, Object InData) {
		inputkey = mkey;
		Inputs = InData;
		weights = new connections<Weight>();

	}

	public Object GetMnode(Integer mkey) {
		if ((mkey != null) && (mkey == inputkey)) {
			return Inputs;
		} else {
			return null;
		}
	}

	public void SetMnode(Integer mkey, Object InData) {
		inputkey = mkey;
		Inputs = InData;

	}

	public connections<Weight> GetWeights() {
		return weights;
	}

	public void clearWeights(int sz) {
		int ws = sz;
		for (int i = 0; i < ws; i++) {
			weights.remove(i);
		}
	}

	public void SetWeights(connections<Weight> w) {
		int ws = w.size();
		for (int i = 0; i < ws; i++) {
			weights.add(w.get(i));
		}
	}

	public Weight GetWeight(int i) {
		return weights.get(i);
	}

	public void SetWeight(int i, Weight newW) {
		weights.set(i, newW);
	}

}
