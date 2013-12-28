 
import java.io.Serializable;

public class Weight implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double wWeight = 0.7;

	public Weight(Double w) {

		wWeight = w;
	}

	public Double getWeight() {

		return wWeight;

	}

	public void setWeight(Double val) {

		wWeight = val;

	}
}
