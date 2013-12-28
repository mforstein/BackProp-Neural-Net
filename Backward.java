 
import java.util.TreeMap;

/**
 * Write a description of class Backward here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Backward {

	private Integer numinnode = 0; // number of input nodes;
	private Integer nummnode = 0; // number of mid nodes;
	private Integer numonode = 0;
	private TreeMap<Integer, InputNode> pInNmap;
	private TreeMap<Integer, MidNode> pMNmap;
	private TreeMap<Integer, OutNode> pONmap;
	private BalastNode pbal;
	private Double[] EA;
	private Double[] EI;
	private Double[][] EW;
	private Double[][] NOW;
	private Double[] EAH;
	private Double[] EIH;
	private Double[][] EWH;
	private Double[] NBOW;
	private Double[] NBMW;
	private Double[][] NMW;
	private Double DeltaW = 0.0;
	private Double temp = 0.0;
	private Double tempc = 0.0;
	private Normalize tc;
	private TreeMap<Integer, Double> results;

	/**
	 * Constructor for objects of class Forward
	 */
	public Backward(TreeMap<Integer, InputNode> InNmap,
			TreeMap<Integer, MidNode> MNmap, TreeMap<Integer, OutNode> ONmap,
			BalastNode bal, Normalize TestCase, TreeMap<Integer, Double> Results) {
		pInNmap = InNmap;
		pMNmap = MNmap;
		pONmap = ONmap;
		pbal = bal;
		numinnode = pInNmap.size();
		nummnode = pMNmap.size();
		numonode = pONmap.size();
		setResults(Results);
		setTc(TestCase);
		for (int gh = 1; gh < numinnode + 1; gh++) {
			// //System.out.println("pInNmap++"+pInNmap.get(gh));

			// //System.out.println("inputnode"+gh+"= "+pInNmap.get(gh).GetInode(gh-1));
		}
		for (int gh = 1; gh < nummnode + 1; gh++) {
			// //System.out.println("pMNmap++"+pMNmap.get(gh));
			// //System.out.println("midnode"+gh+"= "+pMNmap.get(gh).GetMnode(gh-1));
		}
		for (int gh = 1; gh < numonode + 1; gh++) {
			// //System.out.println("pONmap++"+pONmap.get(gh));
			// //System.out.println("outnode"+gh+"= "+pONmap.get(gh).GetOnode(gh-1));
		}

		EA = new Double[numonode];
		EI = new Double[numonode];
		EW = new Double[numonode][nummnode];
		NOW = new Double[numonode][nummnode];
		NBOW = new Double[numonode];
		NBMW = new Double[nummnode];

		EAH = new Double[nummnode];
		EIH = new Double[nummnode];
		EWH = new Double[nummnode][numinnode];
		NMW = new Double[nummnode][numinnode];

	}

	public BalastNode getBWeight() {

		BalastNode tempbal = pbal;
		return tempbal;

	}

	public TreeMap<Integer, MidNode> getMWeights() {

		TreeMap<Integer, MidNode> tempMNmap = pMNmap;
		return tempMNmap;

	}

	public TreeMap<Integer, OutNode> getOWeights() {

		TreeMap<Integer, OutNode> tempONmap = pONmap;
		return tempONmap;

	}

	/**
	 * @return the results
	 */
	public TreeMap<Integer, Double> getResults() {
		return results;
	}

	/**
	 * @return the tc
	 */
	public Normalize getTc() {
		return tc;
	}

	/**
	 * @return the tempc
	 */
	public Double getTempc() {
		return tempc;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(TreeMap<Integer, Double> results) {
		this.results = results;
	}

	/**
	 * @param tc
	 *            the tc to set
	 */
	public void setTc(Normalize tc) {
		this.tc = tc;
	}

	/**
	 * @param tempc
	 *            the tempc to set
	 */
	public void setTempc(Double tempc) {
		this.tempc = tempc;
	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public void train(TreeMap<Integer, InputNode> InNmap,
			TreeMap<Integer, MidNode> MNmap, TreeMap<Integer, OutNode> ONmap,
			BalastNode bal, TestCase tc, TreeMap<Integer, Double> results) {
		// Inputs are the input values from the file for each example.
		// Outputs are the Desired Output values from the file for each example.
		// Results are actual output from each output node. also equal to
		// (ONmap.get(key)).values
		// InNmap is a map of the input nodes
		// MNmap is a map of the middle (hidden) nodes
		// ONmap is a map of the output nodes.
		pInNmap = InNmap;
		pMNmap = MNmap;
		pONmap = ONmap;
		pbal = bal;
		numinnode = pInNmap.size();
		nummnode = pMNmap.size();
		numonode = pONmap.size();
		temp = 0.0;
		double Lamda = pbal.getBalast();
		// //System.out.println("Lamda "+Lamda);
		for (int i = 1; i < numonode + 1; i++) { // increment through the output
													// nodes
			// //System.out.println(pONmap.toString());

			// System.out.println("EA[" + i + "+1" +
			// "]*(pONmap.Onode "+(pONmap.get(i).GetOnode(i-1)) +
			// "*( Test Case Output =" + tc.getOutput(i-1) +
			// "-  results "+results.get(i) + ")");

			EA[i - 1] = (Double) (pONmap.get(i)).GetOnode(i - 1)
					- tc.getOutput(i - 1); // calculate EA for each output node
											// which is the feed forward output
											// - the actual output.

			// System.out.println("EA["+(i-1)+"]  "+EA[i-1]);

			// System.out.println("Onode * (1 -Onode Squared)= " +
			// (pONmap.get(i)).GetOnode(i-1) + " - " +
			// ((pONmap.get(i)).GetOnode(i-1))+ " * " +
			// ((pONmap.get(i)).GetOnode(i-1)));

			EI[i - 1] = EA[i - 1]
					* ((Double) (pONmap.get(i)).GetOnode(i - 1) - ((Double) (pONmap
							.get(i)).GetOnode(i - 1))
							* ((Double) (pONmap.get(i)).GetOnode(i - 1))); // Intermediate
																			// Error
																			// EA(o-o*o)

			// System.out.println("EI[i-1]  "+i+"   "+EI[i-1]);

			temp = 0.0;
			DeltaW = 0.0;
			for (int j = 1; j < nummnode + 1; j++) { // increment through the
														// middle or hidden
														// layer.
				// System.out.println("j=" + j+" pMNmap.GetMnnode(j) ="+
				// (pMNmap.get(j)).GetMnode(j-1));
				// System.out.println("i=" + i);
				EW[i - 1][j - 1] = EI[i - 1]
						* (Double) (pMNmap.get(j)).GetMnode(j - 1); // Calculate
																	// Middle
																	// Error
																	// Weight
																	// for each
																	// Output
																	// node this
																	// is a Ixj
																	// operation
				// System.out.println("i="+i+" j="+j+"EW[i-1][j-1]  "+EW[i-1][j-1]);
				DeltaW = -(Lamda / (Math.abs(j - i) + 1)) * EW[i - 1][j - 1];
				// System.out.println("Delta="+DeltaW);
				NOW[i - 1][j - 1] = ((pONmap.get(i)).GetWeight(j - 1))
						.getWeight() + DeltaW; // new output weight calculation;
				// System.out.println("i="+i+" j="+j+"  NOW["+i+"-1]["+j+"-1]  "+NOW[i-1][j-1]);
			}
			// System.out.println("pbal.getBalast() =" +
			// (Double)pbal.getBalast() + "Lamda*EI["+i+"-1]="+Lamda*EI[i-1]);

			setTempc(pbal.getBalast() - (Lamda * EI[i - 1])); // new balast
																// output
																// weight;
			// //System.out.println(i+","+tempc);
			NBOW[i - 1] = pbal.getBalast() - (Lamda * EI[i - 1]); // new balast
																	// output
																	// weight;
			setTempc(0.0);
			// //System.out.println("i="+i+"NBOW[i-1]  "+NBOW[i-1]);
		}

		for (int i = 1; i < nummnode + 1; i++) {
			temp = 0.0;
			for (int j = 1; j < numonode + 1; j++) {
				// System.out.println("EI["+(j-1)+"]="+EI[j-1]+"  (pONmap.get(j)).GetOnode(j-1)="+(Double)(pONmap.get(j)).GetOnode(j-1));
				// temp = temp
				// +NOW[i-1][j-1]*(Double)(pONmap.get(j)).GetOnode(j-1);
				// System.out.println("EI["+(j-1)+"]="+EI[j-1] + "* "+
				// ((pONmap.get(j)).GetWeight(i-1)).getWeight());
				temp = temp + ((pONmap.get(j)).GetWeight(i - 1)).getWeight()
						* EI[j - 1];

				// System.out.println("i="+i+"j="+j+"temp="+temp);
			}
			EAH[i - 1] = temp;
		}
		for (int i = 1; i < nummnode + 1; i++) {
			// System.out.println("i="+i+"Mnode =" +
			// ((Double)(pMNmap.get(i)).GetMnode(i-1)));
			// System.out.println("i="+i+"Mnode Squared" +
			// ((Double)(pMNmap.get(i)).GetMnode(i-1)*(Double)(pMNmap.get(i)).GetMnode(i-1)));
			EIH[i - 1] = EAH[i - 1]
					* ((Double) (pMNmap.get(i)).GetMnode(i - 1) - ((Double) (pMNmap
							.get(i)).GetMnode(i - 1) * (Double) (pMNmap.get(i))
							.GetMnode(i - 1)));

			for (int j = 1; j < numinnode + 1; j++) {
				// System.out.println("EIH["+(i-1)+"]="+EIH[i-1]+"   pInNmap.get(j)).GetInode(j-1)="+pInNmap.get(j).GetInode(j-1));
				EWH[i - 1][j - 1] = EIH[i - 1]
						* (Double) (pInNmap.get(j)).GetInode(j - 1);
				// System.out.println("i="+i+" j =" + j+
				// " EWH = "+EWH[i-1][j-1]);
				DeltaW = -(Lamda / (Math.abs(j - i) + 1)) * EWH[i - 1][j - 1];
				// System.out.println("DeltaW="+DeltaW+"(pMNmap.get(i)).GetWeight(j)).getWeight()="+(pMNmap.get(i)).GetWeight(j-1).getWeight());
				NMW[i - 1][j - 1] = ((pMNmap.get(i)).GetWeight(j - 1))
						.getWeight() + DeltaW; // new middle weight calculation;
				// System.out.println("i="+i+" j =" + j+
				// "  NMW["+(i-1)+","+(j-1)+"] = "+NMW[i-1][j-1]);
			}
			NBMW[i - 1] = pbal.getBalast() - Lamda * EIH[i - 1]; // new balast
																	// middle
																	// weight;
			// //System.out.println("i="+i+ " NBMW["+(i-1)+"] = "+NBMW[i-1]);
		}

		// Transfer new weight values into maps

		for (int i = 1; i < numonode + 1; i++) {
			for (int j = 1; j < nummnode + 1; j++) {
				// System.out.println("NOW["+i+","+j+"]="+NOW[i-1][j-1]);
				((pONmap.get(i)).GetWeight(j - 1)).setWeight(NOW[i - 1][j - 1]);
			}
			// System.out.println("NBOW["+i+"]="+NBOW[i-1]);
			((pONmap.get(i)).GetWeight(nummnode - 1)).setWeight(NBOW[i - 1]);
		}
		// System.out.println("now middle");
		for (int i = 1; i < nummnode + 1; i++) {
			for (int j = 1; j < numinnode + 1; j++) {
				// System.out.println("NMW["+i+","+j+"]="+NMW[i-1][j-1]);
				((pMNmap.get(i)).GetWeight(j - 1)).setWeight(NMW[i - 1][j - 1]);
			}
			// System.out.println("NBMW["+i+"]="+NBMW[i-1]);
			((pMNmap.get(i)).GetWeight(numinnode - 1)).setWeight(NBMW[i - 1]);
		}
		// bal = this.getBWeight();

	}
}