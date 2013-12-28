 
/**
 * Write a description of class StockFormula here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class StockFormula {

	private float pEG;
	private float[] ePSs;
	private float[] pEs;
	private float[] netIncs;

	public StockFormula(float[] epsss, float[] pe, float peg, float[] neti) {

		setpEG(peg);

		setePSs(new float[] { epsss[0], epsss[1], epsss[2], epsss[3], epsss[4] });

		setpEs(new float[] { pe[0], pe[1], pe[2], pe[3], pe[4] });

		setNetIncs(new float[] { neti[0], neti[1], neti[2], neti[3], neti[4] });

	}

	boolean checkEPS(float[] EPS) {
		float eps = 0;
		int i = 0;
		while (i < 4) {
			eps = eps + ((EPS[i] - EPS[i + 1]) / EPS[i + 1]);
			i = i + 1;
		}
		if (eps < .40) {
			return false;
		} else {
			return true;
		}
	}

	boolean checkPE(float[] PE) {
		float pes = 0;
		int i = 0;
		while (i < 5) {
			pes = PE[i];
			if (pes > 12) {
				return false;
			}
			i = i + 1;
		}
		return true;
	};

	boolean checkPEG(float PEG) {
		float peg = PEG;
		if (peg <= 1.0) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkNetInc(float[] NetInc) {
		float neti = 0;
		int i = 0;
		while (i < 4) {
			neti = neti + ((NetInc[i] - NetInc[i + 1]) / NetInc[i + 1]);
			i = i + 1;
		}
		if (neti < .60) {
			return false;
		} else {
			return true;
		}
	}

	String getGrade(float[] epsss, float[] pe, float peg, float[] neti) {
		int score = 0;
		String grade = "F";
		if ((checkEPS(epsss))) {
			score = 1;
		}
		if (checkPE(pe)) {
			score = score + 1;
		}
		if (checkPEG(peg)) {
			score = score + 1;
		}
		if (checkNetInc(neti)) {
			score = score + 1;
		}
		if (score == 4) {
			grade = "A";
			return grade;
		}
		if (score == 3) {
			grade = "B";
			return grade;
		}
		if (score == 2) {
			grade = "C";
			return grade;
		}
		if (score == 1) {
			grade = "D";
			return grade;
		}
		if (score == 0) {
			grade = "F";
			return grade;
		}
		return grade;
	}

	/**
	 * @return the pEG
	 */
	public float getpEG() {
		return pEG;
	}

	/**
	 * @param pEG
	 *            the pEG to set
	 */
	public void setpEG(float pEG) {
		this.pEG = pEG;
	}

	/**
	 * @return the ePSs
	 */
	public float[] getePSs() {
		return ePSs;
	}

	/**
	 * @param ePSs
	 *            the ePSs to set
	 */
	public void setePSs(float[] ePSs) {
		this.ePSs = ePSs;
	}

	/**
	 * @return the pEs
	 */
	public float[] getpEs() {
		return pEs;
	}

	/**
	 * @param pEs
	 *            the pEs to set
	 */
	public void setpEs(float[] pEs) {
		this.pEs = pEs;
	}

	/**
	 * @return the netIncs
	 */
	public float[] getNetIncs() {
		return netIncs;
	}

	/**
	 * @param netIncs
	 *            the netIncs to set
	 */
	public void setNetIncs(float[] netIncs) {
		this.netIncs = netIncs;
	}
}
