 
import java.util.ArrayList;

public class DeNormalize {
	// instance variables - replace the example below with your own
	private ArrayList<TestCase> eachCaseDNormal;
	private ArrayList<TestCase> eachCaseOriginal;
	private ArrayList<TestCase> eachResultOriginal;
	private Double ibiggest = 0.0;
	private Double ismallest = 0.0;
	private Double rbiggest = 0.0;
	private Double rsmallest = 0.0;
	private Double irange = 0.0;
	private Double rrange = 0.0;
	private Integer getIsize;
	private Integer getOsize;
	private TestCase nc;
	private int casenumcount;

	/**
	 * Constructor for objects of class Normalize
	 */
	public DeNormalize() {
		// initialise instance variables
		eachCaseDNormal = new ArrayList<TestCase>();

	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	public ArrayList<TestCase> denormalize(ArrayList<TestCase> eachCase,
			ArrayList<TestCase> eachResult,double smalle,double bigg) {
		int casenum = 0;
		setCasenumcount(0);
		eachCaseOriginal = eachCase;
		eachResultOriginal = eachResult;
		TestCase rc = new TestCase();
		TestCase tc = new TestCase();
		TestCase nc = new TestCase();
		for (TestCase testCase : eachCaseOriginal) {

			tc = (TestCase) testCase;
			// TestCase rc = (TestCase)itr.next();
			casenum++;
			setCasenumcount(casenum);
			// System.out.println(tc.getInputs()+"\n");
			// System.out.println(tc.getOutputs()+"\n");
			getIsize = tc.getIsize();
			getOsize = eachResult.get(0).getOsize();
			System.out.println("Isize " + getIsize + ", Osize" + getOsize);
			for (int ssr = 1; ssr < getIsize + 1; ssr++) {

				// System.out.println("biggest="+biggest+
				// " Inputs="+tc.getInput(ssr-1));
				if (ibiggest < tc.getInput(ssr - 1)) {
					ibiggest = tc.getInput(ssr - 1);
				}
				if (ismallest >= tc.getInput(ssr - 1)) {
					// System.out.println("smallest="+biggest+
					// " Inputs="+tc.getInput(ssr-1));
					ismallest = tc.getInput(ssr - 1);
				}
			}
		}
		casenum = 0;
		setCasenumcount(0);
		eachResultOriginal = eachResult;
		for (Object element : eachResultOriginal) {

			rc = (TestCase) element;
			// TestCase rc = (TestCase)itr.next();
			casenum++;
			setCasenumcount(casenum);
			// System.out.println(rc.getInputs()+"\n");
			// System.out.println(rc.getOutputs()+"\n");
			getOsize = rc.getOsize();

			for (int ssr = 1; ssr < getOsize + 1; ssr++) {

				// System.out.println("biggest="+rbiggest+
				// " Outputs="+rc.getOutput(ssr-1));

				if (rbiggest < rc.getOutput(ssr - 1)) {
					rbiggest = rc.getOutput(ssr - 1);
				}
				if (rsmallest >= rc.getOutput(ssr - 1)) {
					// System.out.println("biggest="+rbiggest+
					// " Outputs="+rc.getOutput(ssr-1));
					rsmallest = rc.getOutput(ssr - 1);
				}
			}

		}
		casenum = 0;
		setCasenumcount(0);
		irange = bigg - smalle;
		for (Object element : eachResultOriginal) {
			tc = eachCaseOriginal.get(casenum);
			rc = (TestCase) element;
			// TestCase rc = (TestCase)itr.next();
			casenum++;
			setCasenumcount(casenum);
			//System.out.println(rc.getInputs() + "\n");
			//System.out.println(rc.getOutputs() + "\n");
			getOsize = rc.getOsize();
			nc = new TestCase();
			nc.AddTestCaseKey(casenum);
			for (int sst = 1; sst < getIsize + 1; sst++) {
				nc.AddTestCaseInput(tc.getInput(sst - 1));
			}
			for (int ssr = 1; ssr < getOsize + 1; ssr++) {


				nc.AddTestCaseOutput(rc.getOutput(ssr - 1) * irange);
			}
			//System.out.println(nc.getInputs() + "\n");
			//System.out.println(nc.getOutputs() + "\n");
			eachCaseDNormal.add(nc);
		}

		// System.out.println(nc.getInputs()+"\n");
		// System.out.println(nc.getOutputs()+"\n");

		return eachCaseDNormal;
	}

	public Double igetTop() {
		return ibiggest;
	}

	public Double igetBot() {
		return ismallest;
	}

	public Double igetR() {
		return irange;
	}

	public Double rgetTop() {
		return rbiggest;
	}

	public Double rgetBot() {
		return rsmallest;
	}

	public Double rgetR() {
		return rrange;
	}

	public int count() {
		return eachCaseDNormal.size();
	}

	/**
	 * @return the nc
	 */
	public TestCase getNc() {
		return nc;
	}

	/**
	 * @param nc
	 *            the nc to set
	 */
	public void setNc(TestCase nc) {
		this.nc = nc;
	}

	/**
	 * @return the casenumcount
	 */
	public int getCasenumcount() {
		return casenumcount;
	}

	/**
	 * @param casenumcount
	 *            the casenumcount to set
	 */
	public void setCasenumcount(int casenumcount) {
		this.casenumcount = casenumcount;
	}

}
