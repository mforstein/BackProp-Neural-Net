 
import java.util.TreeMap;

/**
 * Write a description of class Forward here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Forward {
	private Integer numinnode = 0; // number of input nodes;
	private Integer nummnode = 0; // number of mid nodes;
	private Integer numonode = 0;
	private TestCase tc;
	private TreeMap<Integer, InputNode> pInNmap;
	private TreeMap<Integer, MidNode> pMNmap;
	private TreeMap<Integer, OutNode> pONmap;
	private BalastNode pbal;
	private InputNode unode;
	private Double[] NM;
	private Double[] OM;

	/**
	 * Constructor for objects of class Forward
	 */
	public Forward(TreeMap<Integer, InputNode> InNmap,
			TreeMap<Integer, MidNode> MNmap, TreeMap<Integer, OutNode> ONmap,
			BalastNode bal) {

		pInNmap = InNmap;
		pMNmap = MNmap;
		pONmap = ONmap;
		pbal = bal;

		numinnode = pInNmap.size();
		nummnode = pMNmap.size();
		numonode = pONmap.size();
		setNM(new Double[nummnode]);
		setOM(new Double[numonode]);

		// for(int gh =1; gh<numinnode+1;gh++){
		// System.out.println("pInNmap++"+pInNmap.get(gh));

		// System.out.println("inputnode"+gh+"= "+pInNmap.get(gh).GetInode(gh-1));
		// }
		// for(int gh =1; gh<nummnode+1;gh++){
		// System.out.println("pMNmap++"+pMNmap.get(gh));
		// System.out.println("midnode"+gh+"= "+pMNmap.get(gh).GetMnode(gh-1));
		// }
		// for(int gh =1; gh<numonode+1;gh++){
		// System.out.println("pONmap++"+pONmap.get(gh));
		// System.out.println("outnode"+gh+"= "+pONmap.get(gh).GetOnode(gh-1));
		// }
	}

	// Get Input

	// Get Initial Weights if not set to 0.7

	// Get Balast Value if not set to 1.0

	// Calculate Net for each Mid Node;
	public TreeMap<Integer, Double> Net(TreeMap<Integer, InputNode> InNmap,
			TreeMap<Integer, MidNode> MNmap, TreeMap<Integer, OutNode> ONmap,
			BalastNode bal, TestCase tc) {
		pInNmap = InNmap;
		pMNmap = MNmap;
		pONmap = ONmap;
		pbal = bal;
		// System.out.println(tc.getIsize());
		for (int rk = 1; rk < (tc.getIsize()) + 1; rk++) {
			// System.out.println("rk"+rk);
			// System.out.println("pInNmap"+pInNmap);
			// System.out.println("Inode"+pInNmap.get(rk));
			unode = (pInNmap.get(rk));
			// System.out.println("unode>>"+unode);
			unode.SetInode(rk - 1, (tc.getInput(rk - 1)));
			// System.out.println((rk+","+tc.getInput(rk-1))+" unode.GetInode="+unode.GetInode(rk-1));
			pInNmap.put(rk, unode);
		}
		// System.out.println("pInNmap-->"+pInNmap.toString());
		TreeMap<Integer, Double> net = new TreeMap<Integer, Double>();

		// System.out.println("r="+numinnode+",q="+nummnode+",p="+numonode);
		for (int r = 1; r < (nummnode) + 1; r++) { // Calculate Hvalue for each
													// Mid Node
			// System.out.println(r);
			double tempnet = 0;
			double tempHvalue = 0;
			for (int q = 1; q < numinnode + 1; q++) {
				//
				// System.out.println("r="+r+",q="+q);
				// System.out.println(pInNmap);
				// System.out.println(pMNmap);
				// System.out.println(pInNmap.get(q));
				// System.out.println(pInNmap.get(q).GetInode(q));
				// System.out.println("MidNode>>"+pMNmap.get(r));
				// System.out.println("MidNode value="+pMNmap.get(r).GetMnode(r-1));
				if (pMNmap.get(r).GetWeight(q - 1) != null) {
					// System.out.println("notnull");
					// System.out.println("Weights"+ q + " --> " +
					// pMNmap.get(r).GetWeight(r-1).getWeight());
				} else {
					// System.out.println("nulled");
					pMNmap.get(r).SetWeight(q - 1, new Weight(0.7));
					// System.out.println(pMNmap.get(p).GetWeight(q-1).getWeight());
				}
				// System.out.println("Weights"+(pMNmap.get(r)).GetWeight(r));
				// System.out.println(pInNmap.get(r));
				// System.out.println(pInNmap.get(r).GetInode(r-1));
				// System.out.println(pMNmap.get(r).GetWeight(q-1).getWeight());
				// System.out.println("MidNode--->r"+r+",q"+q+",Weight="+((Double)(pInNmap.get(q).GetInode(q-1))*(Double)(((pMNmap.get(r)).GetWeight(q-1)).getWeight())));
				tempnet = tempnet
						+ ((Double) (pInNmap.get(q).GetInode(q - 1)) * (((pMNmap
								.get(r)).GetWeight(q - 1)).getWeight()));
				// System.out.println("tempnet-->"+tempnet);

			}

			tempnet = tempnet + pbal.getBalast();
			// System.out.println("MidNode -->tempnet+ Bal-->"+tempnet);
			tempHvalue = 1 / (1 + (Math.exp(-tempnet)));
			// System.out.println("MidNode-->tempHvalue-->"+tempHvalue);
			// NM[r-1] = tempHvalue;
			(pMNmap.get(r)).SetMnode(r - 1, tempHvalue);
		}
		for (int r = 1; r < numonode + 1; r++) { // Calculate Hvalue for each
													// Out Node
			double tempnet = 0;
			double tempHvalue = 0;
			for (int q = 1; q < nummnode + 1; q++) {
				// System.out.println(pONmap.get(r).GetWeight(q-1).getWeight());
				// System.out.println("OutNode--->r"+r+",q"+q+",Weight="+"(Double)(pMNmap.get(q).GetMnode(q-1))"+(Double)(pMNmap.get(q).GetMnode(q-1))+",   "+((Double)(pMNmap.get(q).GetMnode(q-1))*(Double)(((pONmap.get(r)).GetWeight(q-1)).getWeight())));
				tempnet = tempnet
						+ ((Double) (pMNmap.get(q).GetMnode(q - 1)) * (((pONmap
								.get(r)).GetWeight(q - 1)).getWeight()));
				// System.out.println("tempnet-->"+tempnet);

			}
			tempnet = tempnet + pbal.getBalast();
			// System.out.println("OutNode -->tempnet2+ Bal-->"+tempnet);
			tempHvalue = 1 / (1 + (Math.exp(-tempnet)));
			// System.out.println("OutNode " + r+
			// " -->tempHvalue2-->"+tempHvalue);
			// OM[r-1] = tempHvalue;
			(pONmap.get(r)).SetOnode(r - 1, tempHvalue);
			net.put(r, tempHvalue);
		}

		// System.out.println(net.toString());
		return net;
	}

	public TreeMap<Integer, InputNode> GetINode() {

		return pInNmap;
	}

	public TreeMap<Integer, MidNode> GetMNode() {

		return pMNmap;
	}

	public TreeMap<Integer, OutNode> GetONode() {

		return pONmap;
	}

	public BalastNode GetBalast() {
		return pbal;
	}

	/**
	 * @return the tc
	 */
	public TestCase getTc() {
		return tc;
	}

	/**
	 * @param tc
	 *            the tc to set
	 */
	public void setTc(TestCase tc) {
		this.tc = tc;
	}

	/**
	 * @return the nM
	 */
	public Double[] getNM() {
		return NM;
	}

	/**
	 * @param nM
	 *            the nM to set
	 */
	public void setNM(Double[] nM) {
		NM = nM;
	}

	/**
	 * @return the oM
	 */
	public Double[] getOM() {
		return OM;
	}

	/**
	 * @param oM
	 *            the oM to set
	 */
	public void setOM(Double[] oM) {
		OM = oM;
	}
}
