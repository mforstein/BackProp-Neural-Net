 
import java.util.ArrayList;
import java.util.Iterator;

public class Normalize {
	// instance variables - replace the example below with your own
	private ArrayList<TestCase> eachCaseNormal;
	private ArrayList<TestCase> eachCaseOriginal;
	private Double biggest = 0.0;
	private Double smallest = 0.0;
	private Double range = 0.0;
	private Integer getIsize;
	private Integer getOsize;
	private TestCase nc;

	/**
	 * Constructor for objects of class Normalize
	 */
	public Normalize() {
		// initialise instance variables
		eachCaseNormal = new ArrayList<TestCase>();
		

	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public ArrayList<TestCase> normalize(ArrayList<TestCase> eachCase) {

		eachCaseOriginal = eachCase;
		for (Iterator<TestCase> itr = eachCaseOriginal.iterator(); itr
				.hasNext();) {

			TestCase tc = (TestCase) itr.next();

			// System.out.println(tc.getInputs()+"\n");
			// System.out.println(tc.getOutputs()+"\n");
			getIsize = tc.getIsize();
			getOsize = tc.getOsize();

			for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
				// System.out.println("biggest="+biggest+
				// " INputs="+tc.getInput(ssr-1));
				if (biggest < tc.getInput(ssr - 1)) {
					biggest = tc.getInput(ssr - 1);
				}
				if (smallest >= tc.getInput(ssr - 1)) {
					// System.out.println("smallest="+biggest+
					// " INputs="+tc.getInput(ssr-1));
					smallest = tc.getInput(ssr - 1);
				}
			}

			for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {
				if (biggest < tc.getOutput(ssr - 1)) {
					// System.out.println("biggest="+biggest+
					// " Outputs="+tc.getOutput(ssr-1) );
					biggest = tc.getOutput(ssr - 1);
				}
				if (smallest >= tc.getOutput(ssr - 1)) {
					// System.out.println("smallest="+smallest+
					// " Outputs="+tc.getOutput(ssr-1) );
					smallest = tc.getOutput(ssr - 1);
				}
			}
		}

		// System.out.println("Starting to Normalize");
		for (Iterator<TestCase> itr = eachCaseOriginal.iterator(); itr
				.hasNext();) {
			nc = new TestCase();
			TestCase tc = (TestCase) itr.next();
			Double[] newinputs = new Double[getIsize];
			Double[] newoutputs = new Double[getOsize];
			if ((biggest != smallest) && (biggest > smallest)) {
				if (smallest < 0.0) {
					range = biggest - smallest;
					// System.out.println("biggest = "+biggest+"  smallest="+smallest+"  range="+range);
					for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
						// System.out.println("Shifted By="+smallest+
						// " INputs="+(tc.getInput(ssr-1)-smallest));

						newinputs[ssr - 1] = (tc.getInput(ssr - 1) - smallest)
								/ range;
						// System.out.println("newinputs"+(ssr-1)+" normalized = "+newinputs[ssr-1]);
						nc.AddTestCaseKey(ssr - 1);
						nc.AddTestCaseInput(newinputs[ssr - 1]);

					}
					// System.out.println(nc.getInputs()+"\n");
					for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {

						// System.out.println("Shifted By="+smallest+
						// " INputs="+(tc.getOutput(ssr-1)-smallest));
						newoutputs[ssr - 1] = (tc.getOutput(ssr - 1) - smallest)
								/ range;

						nc.AddTestCaseOutput(newoutputs[ssr - 1]);
						// System.out.println("newoutputs"+(ssr-1)+" offset = "+newoutputs[ssr-1]);
					}
					// System.out.println(nc.getOutputs()+"\n");
				} else {
					range = biggest - smallest;
					for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
						// System.out.println("biggest="+biggest+
						// " INputs="+tc.getInput(ssr-1));

						newinputs[ssr - 1] = (tc.getInput(ssr - 1)) / range;
						nc.AddTestCaseKey(ssr - 1);
						nc.AddTestCaseInput(newinputs[ssr - 1]);
						// System.out.println("newinputs"+(ssr-1)+" offset = "+newinputs[ssr-1]);

					}

					for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {

						// System.out.println("biggest="+biggest+
						// " Outputs="+tc.getOutput(ssr-1) );
						newoutputs[ssr - 1] = (tc.getOutput(ssr - 1)) / range;

						nc.AddTestCaseOutput(newoutputs[ssr - 1]);
						// System.out.println("newoutputs"+(ssr-1)+" offset = "+newoutputs[ssr-1]);
					}
				}

			}
			eachCaseNormal.add(nc);
			// System.out.println(nc.getInputs()+"\n");
			// System.out.println(nc.getOutputs()+"\n");
		}

		return eachCaseNormal;
	}
	public ArrayList<TestCase> normalizeresult(ArrayList<TestCase> eachCase,double smalle,double bigg) {

		eachCaseOriginal = eachCase;
		for (Iterator<TestCase> itr = eachCaseOriginal.iterator(); itr
				.hasNext();) {

			TestCase tc = (TestCase) itr.next();

			// System.out.println(tc.getInputs()+"\n");
			// System.out.println(tc.getOutputs()+"\n");
			getIsize = tc.getIsize();
			getOsize = tc.getOsize();

			for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
				// System.out.println("biggest="+biggest+
				// " INputs="+tc.getInput(ssr-1));
				if (biggest < tc.getInput(ssr - 1)) {
					biggest = tc.getInput(ssr - 1);
				}
				if (smallest >= tc.getInput(ssr - 1)) {
					// System.out.println("smallest="+biggest+
					// " INputs="+tc.getInput(ssr-1));
					smallest = tc.getInput(ssr - 1);
				}
			}

			for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {
				if (biggest < tc.getOutput(ssr - 1)) {
					// System.out.println("biggest="+biggest+
					// " Outputs="+tc.getOutput(ssr-1) );
					biggest = tc.getOutput(ssr - 1);
				}
				if (smallest >= tc.getOutput(ssr - 1)) {
					// System.out.println("smallest="+smallest+
					// " Outputs="+tc.getOutput(ssr-1) );
					smallest = tc.getOutput(ssr - 1);
				}
			}
		}

		// System.out.println("Starting to Normalize");
		for (Iterator<TestCase> itr = eachCaseOriginal.iterator(); itr
				.hasNext();) {
			nc = new TestCase();
			TestCase tc = (TestCase) itr.next();
			Double[] newinputs = new Double[getIsize];
			Double[] newoutputs = new Double[getOsize];
			if ((biggest != smallest) && (biggest > smallest)) {
				if (smallest < 0.0) {
					range = bigg - smalle;
					// System.out.println("biggest = "+biggest+"  smallest="+smallest+"  range="+range);
					for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
						// System.out.println("Shifted By="+smallest+
						// " INputs="+(tc.getInput(ssr-1)-smallest));

						newinputs[ssr - 1] = (tc.getInput(ssr - 1) - smallest)
								/ range;
						// System.out.println("newinputs"+(ssr-1)+" normalized = "+newinputs[ssr-1]);
						nc.AddTestCaseKey(ssr - 1);
						nc.AddTestCaseInput(newinputs[ssr - 1]);

					}
					// System.out.println(nc.getInputs()+"\n");
					for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {

						// System.out.println("Shifted By="+smallest+
						// " INputs="+(tc.getOutput(ssr-1)-smallest));
						newoutputs[ssr - 1] = (tc.getOutput(ssr - 1) - smallest)
								/ range;

						nc.AddTestCaseOutput(newoutputs[ssr - 1]);
						// System.out.println("newoutputs"+(ssr-1)+" offset = "+newoutputs[ssr-1]);
					}
					// System.out.println(nc.getOutputs()+"\n");
				} else {
					range = bigg - smalle;
					for (int ssr = 1; ssr < tc.getIsize() + 1; ssr++) {
						// System.out.println("biggest="+biggest+
						// " INputs="+tc.getInput(ssr-1));

						newinputs[ssr - 1] = (tc.getInput(ssr - 1)) / range;
						nc.AddTestCaseKey(ssr - 1);
						nc.AddTestCaseInput(newinputs[ssr - 1]);
						// System.out.println("newinputs"+(ssr-1)+" offset = "+newinputs[ssr-1]);

					}

					for (int ssr = 1; ssr < tc.getOsize() + 1; ssr++) {

						// System.out.println("biggest="+biggest+
						// " Outputs="+tc.getOutput(ssr-1) );
						newoutputs[ssr - 1] = (tc.getOutput(ssr - 1)) / range;

						nc.AddTestCaseOutput(newoutputs[ssr - 1]);
						// System.out.println("newoutputs"+(ssr-1)+" offset = "+newoutputs[ssr-1]);
					}
				}

			}
			eachCaseNormal.add(nc);
			// System.out.println(nc.getInputs()+"\n");
			// System.out.println(nc.getOutputs()+"\n");
		}

		return eachCaseNormal;
	}
	public Double getTop() {
		return biggest;
	}

	public Double getBot() {
		return smallest;
	}

	public Double getR() {
		return range;
	}

	public int count() {
		return eachCaseNormal.size();
	}

}
