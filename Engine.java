 
import java.util.TreeMap;
import java.io.Serializable;

/**
 * Write a description of class Engine here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Engine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, InputNode> InNmap;
	private TreeMap<Integer, MidNode> MNmap;
	private TreeMap<Integer, OutNode> ONmap;
	private BalastNode bal;
	private Integer ikey;

	public Engine(TreeMap<Integer, InputNode> InNmaps,
			TreeMap<Integer, MidNode> MNmaps, TreeMap<Integer, OutNode> ONmaps,
			BalastNode bals) {
		InNmap = InNmaps;
		MNmap = MNmaps;
		ONmap = ONmaps;
		bal = bals;

	}

	public Object getEngine() {

		return this;

	}

	public void setEngine(TreeMap<Integer, InputNode> InNmaps,
			TreeMap<Integer, MidNode> MNmaps, TreeMap<Integer, OutNode> ONmaps,
			BalastNode bals) {
		this.InNmap = InNmaps;
		this.MNmap = MNmaps;
		this.ONmap = ONmaps;
		this.bal = bals;
		// E = new Engine(InNmap,MNmap,ONmap,bal);

	}

	public void setEngineE(Engine Engin) {
		this.InNmap = Engin.getInNmaps();
		this.MNmap = Engin.getMNmaps();
		this.ONmap = Engin.getONmaps();
		this.bal = Engin.getbals();

	}

	public TreeMap<Integer, InputNode> getInNmaps() {
		return this.InNmap;
	}

	public TreeMap<Integer, MidNode> getMNmaps() {
		return this.MNmap;
	}

	public TreeMap<Integer, OutNode> getONmaps() {
		return this.ONmap;
	}

	public BalastNode getbals() {
		return this.bal;
	}

	/**
	 * @return the ikey
	 */
	public Integer getIkey() {
		return ikey;
	}

	/**
	 * @param ikey
	 *            the ikey to set
	 */
	public void setIkey(Integer ikey) {
		this.ikey = ikey;
	}
}
