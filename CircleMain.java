 
// File   : CircleMain.java - Applet/main program
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.util.TreeMap;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

/////////////////////////////// CircleMain
/*
 * CircleMain is just the beginning of this journey.  
 * 
 * 
 * 
 * 
 * 
 */
public class CircleMain extends JPanel {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CircleMain [statuspanel=" + statuspanel + ", palletpanel="
				+ palletpanel + ", inputfilenamelabel=" + inputfilenamelabel
				+ ", outputfilenamelabel=" + outputfilenamelabel + ", cp=" + cp
				+ ", commentpanel=" + commentpanel + ", numberofInputs="
				+ numberofInputs + ", numberofOutputs=" + numberofOutputs
				+ ", numberofmidnodes=" + numberofmidnodes + ", InNmap="
				+ InNmap + ", MNmap=" + MNmap + ", ONmap=" + ONmap + ", bal="
				+ bal + ", Inputs=" + Inputs + ", Results=" + Results
				+ ", TrainingSetError=" + TrainingSetError
				+ ", GeneralSetError=" + GeneralSetError + ", Eng=" + Eng
				+ ", ThreshholdError=" + ThreshholdError + ", forward="
				+ forward + ", backward=" + backward + ", TrainingCases="
				+ TrainingCases + ", SingleTC=" + SingleTC + ", iterations="
				+ iterations + ", casenumber=" + casenumber + ", Errornum="
				+ Errornum + ", Iterationscount=" + Iterationscount
				+ ", statuslist=" + statuslist + ", js=" + js
				+ ", selectedFile=" + selectedFile + ", selectedRFile="
				+ selectedRFile + ", selectedEFile=" + selectedEFile + ", i="
				+ i + ", j=" + j + ", k=" + k + ", b=" + b + ", pathname="
				+ pathname + ", Iterationcount=" + Iterationcount
				+ ", casecount=" + casecount + ", tempnode=" + tempnode
				+ ", stattext=" + stattext + ", cntThreshold=" + cntThreshold
				+ ", erThreshold=" + erThreshold + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel statuspanel;
	private JPanel palletpanel;

	private static final String VERSION = "0.0";
	public P2Panel inputfilenamelabel;
	public P2Panel outputfilenamelabel;

	private CirclePanel cp;
	private JPanel commentpanel;
	private P2Panel numberofInputs;
	private P2Panel numberofOutputs;
	private P2Panel numberofmidnodes;
	private TreeMap<Integer, InputNode> InNmap;
	private TreeMap<Integer, MidNode> MNmap;
	private TreeMap<Integer, OutNode> ONmap;
	private BalastNode bal;
	private TreeMap<Integer, Double> Inputs;
	private TreeMap<Integer, Double> Results;
	private TreeMap<Integer, Double> TrainingSetError; // Error per Test Case
	private TreeMap<Integer, Double> GeneralSetError; // Error per Iteration.
	private Engine Eng;
	private Double ThreshholdError = 0.0002; // Default error level;
	private Forward forward;
	private Backward backward;
	private TreeMap<Integer, TestCase> TrainingCases;
	private Normalize SingleTC;
	private P2Panel iterations;
	private P2Panel casenumber;
	private P2Panel Errornum;
	private Integer Iterationscount;
	private static boolean Iter = false;
	private static boolean stept = false;
	private JLabel statuslist;
	private JScrollPane js;
	public File selectedFile;
	public File selectedRFile;
	public File selectedEFile;
	public Integer i = 0;
	public Integer j = 0;
	public Integer k = 0;
	public Integer b = 0;
	public String pathname;
	private Integer Iterationcount;
	private Integer casecount;
	private MidNode tempnode;
	private JTextArea stattext;
	public int cntThreshold = 10000;
	public Double erThreshold = 0.01;

	// ====================================================== constructor
	public CircleMain() {
		// ... Set layout and add our new component.
		setLayout(new BorderLayout());
		cp = new CirclePanel(650, 2000);
		// add(cp, BorderLayout.CENTER);
		add(js = new JScrollPane(cp), BorderLayout.CENTER);
		js.getViewport().setViewPosition(new java.awt.Point(650, 0));
		stattext = new JTextArea(40, 10);
		numberofInputs = new P2Panel("# of Inputs", 4);
		numberofOutputs = new P2Panel("# of Outputs", 4);
		numberofmidnodes = new P2Panel("# of MidNodes", 4);
		inputfilenamelabel = new P2Panel("Training Filename", 20);
		outputfilenamelabel = new P2Panel("Data Filename", 20);
		casenumber = new P2Panel("Cases", 4);
		iterations = new P2Panel("Iterations", 4);
		Errornum = new P2Panel("Mean Sqr Error", 7);

		statuslist = new JLabel("How to Use.");
		statuspanel = new JPanel();
		palletpanel = new JPanel();
		InNmap = new TreeMap<Integer, InputNode>();
		MNmap = new TreeMap<Integer, MidNode>();
		ONmap = new TreeMap<Integer, OutNode>();
		Results = new TreeMap<Integer, Double>();
		SingleTC = new Normalize();
		TrainingSetError = new TreeMap<Integer, Double>();
		GeneralSetError = new TreeMap<Integer, Double>();
		bal = new BalastNode(0.5);
		setEng(new Engine(InNmap, MNmap, ONmap, bal));
		commentpanel = new JPanel();

		statuspanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

		commentpanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

		statuspanel.add(numberofInputs);
		statuspanel.add(numberofmidnodes);
		statuspanel.add(numberofOutputs);
		statuspanel.add(casenumber);
		statuspanel.add(iterations);
		statuspanel.add(Errornum);
		commentpanel.setLayout(new BorderLayout());
		commentpanel.add(statuslist, BorderLayout.NORTH);
		commentpanel.add(new JScrollPane(stattext), BorderLayout.CENTER);

		statuspanel.setSize(700, 100);
		cp.makeButtons();
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(10, 1));
		buttonpanel.add(cp.Startiterations);
		buttonpanel.add(cp.Stopiterations);
		buttonpanel.add(cp.addInputNode);
		buttonpanel.add(cp.addMidNode);
		buttonpanel.add(cp.addOutputNode);
		buttonpanel.add(cp.addBalast);
		buttonpanel.add(cp.clearE);
		buttonpanel.add(cp.stepthrough);
		buttonpanel.add(cp.SetWeights);
		buttonpanel.add(cp.useengine);
		add(buttonpanel, BorderLayout.WEST);
		this.add(statuspanel, BorderLayout.SOUTH);
		addtext();
		statuspanel.setSize(700, 100);
		cp.Startiterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Trainthread().start();
			}
		});

		cp.Stopiterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Stopthread().start();
			}
		});

		cp.addInputNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInode();
			}
		});
		cp.addMidNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMnode();
			}
		});

		cp.addOutputNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addOnode();
			}
		});

		cp.addBalast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBnode();
			}
		});
		cp.stepthrough.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stept = true;
				stepthrough();
			}
		});

		cp.SetWeights.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setW();
			}
		});

		cp.useengine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UseEngine().start();
				stept = false;
			}
		});
		cp.clearE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		this.add(commentpanel, BorderLayout.EAST); // cp.setVisible(true);
		statuspanel.setVisible(true);
		palletpanel.setVisible(true);
	}

	@SuppressWarnings("unused")
	private void submit() {
		i = i + 1;

		setTextinput(Integer.toString(i));

	}
	public void addtext() {
		stattext.setWrapStyleWord(true);
		stattext.setEditable(false);
		stattext.setText("Step One: \n" +
				"Create a set of \n" +
				"training cases \n" +
				"in a comma delimited \n" +
				"text file where the \n" +
				"first set of columns\n" +
				"are the inputs \n" +
				"and the second set \n" +
				"of columns are \n" +
				"the outputs \n" +
				"each row is a \n" +
				"single case \n" +
				"The more cases \n" +
				"the better the \n" +
				"model will be \n" +
				"represented.\n" +
				"\n" +
				"Step 2: \n" +
				"Create a csv file \n" +
				"with the same number \n" +
				"of inputs \n" +
				"to be tested as \n" +
				"the training file. \n" +
				"\n" +
				"Step 3: \n" +
				"Build a neural net \n" +
				"with the same number \n" +
				"of inputs and outputs \n" +
				"as the training files \n" +
				"Use the add node buttons \n" +
				"on the left. Be sure \n" +
				"to add at least one \n" +
				"of each kind of node. \n" +
				"\n" +
				"Step 4: \n" +
				"Select the training file \n" +
				"from the file menu \n" +
				"\n" +
				"Step 5: \n" +
				"Choose the results \n" +
				"file from the file menu.\n" +
				"\n" +
				"Step 6: \n" +
				"Press the Train\n" +
				"button to begin \n" +
				"\n" +
				"Step 7: \n" +
				"Press the \n" +
				"Set Error button\n" +
				"to set the max error \n" +
				"and max number of \n" +
				"training cycles. \n" +
				"\n" +
				"Step 8: \n" +
				"Once the training \n" +
				"is complete \n" +
				"press the Use Engine \n" +
				"button to write the \n" +
				"new outputs in the \n" +
				"results file. \n" +
				"\n" +
				"Step 9: \n" +
				"Save the Engine \n" +
				"by choosing the \n" +
				"save engine \n" +
				"menu under the \n" +
				"file menu. \n");
	}

	public void clear() {

;		i = 0;
		InNmap.clear();
		setTextinput(Integer.toString(i));
		j = 0;
		MNmap.clear();
		setTextmid(Integer.toString(j));
		k = 0;
		ONmap.clear();
		setTextout(Integer.toString(k));
		b = 0;
		bal = null;
		casenumber.setTextentry("");
		Errornum.setTextentry("");
		cp.clear();
		iterations.setTextentry("");
	}

	private void addInode() {
		i = i + 1;
		InNmap.put(i, new InputNode(i - 1, 0.5));
		setTextinput(Integer.toString(i));
		// //System.out.println("InNmap---->"+InNmap.get(i).GetInode(i));
		adjustWeights();
		// //System.out.println("InNmap.size="+InNmap.size());
		cp.setpInNode(InNmap);

	}

	public void delInode() {
		if (i >= 1) {
			int itemp = i;
			int jtemp = j;
			int ktemp = k;
			int btemp = b;
			clear();
			while( itemp-1 >0) {
				addInode();	
				cp.addInnode();
				itemp--;
			}
			while(jtemp >0) {
				addMnode();	
				cp.addMiNode();
				jtemp--;
			}
			while ( ktemp>0){
				addOnode();	
				cp.addOuNode();
				ktemp--;
			}
			while (btemp>0) {
				addBnode();
				cp.addBaNode();
				btemp--;
			}
			adjustWeights();



		}
		// }
	}

	@SuppressWarnings("unused")
	private void loadInode(final int numInodes, final Double[] val) {
		for (int i = 1; i == numInodes; i++) {
			InNmap.put(i, new InputNode(i - 1, val[i]));
			setTextinput(Integer.toString(i));
			// //System.out.println("InNmap---->"+InNmap.get(i).GetInode(i));
			adjustWeights();
			// //System.out.println("InNmap.size="+InNmap.size());
			cp.setpInNode(InNmap);
		}
	}

	private void addMnode() {
		j = j + 1;

		MNmap.put(j, new MidNode(j - 1, 0.5));
		adjustWeights();
		setTextmid(Integer.toString(j));
		cp.setpMNode(MNmap);
		// //System.out.println("MNmap---->"+MNmap);
		// //System.out.println("j" + j+" MNmap.GetMnnode(j) "+
		// (MNmap.get(j)).GetMnode(j));
	}

	public void delMnode() {
		if (j >= 1) {
			int itemp = i;
			int jtemp = j;
			int ktemp = k;
			int btemp = b;
			clear();
			while( itemp >0) {
				addInode();	
				cp.addInnode();
				itemp--;
			}
			while(jtemp-1 >0) {
				addMnode();	
				cp.addMiNode();
				jtemp--;
			}
			while ( ktemp>0){
				addOnode();	
				cp.addOuNode();
				ktemp--;
			}
			while (btemp>0) {
				addBnode();
				cp.addBaNode();
				btemp--;
			}
			adjustWeights();



		}
		// }
	}

	@SuppressWarnings("unused")
	private void loadMnode(final int numMnodes, final Double[] val,
			final int numMweights, final Double[][] weightval) {
		for (int j = 1; j <= numMnodes; j++) {
			MNmap.put(j, new MidNode(j - 1, val[j]));
			for (int ij = 1; ij <= numMweights; ij++) {
				MNmap.get(i).GetWeight(j - 1)
						.setWeight(weightval[j - 1][ij - 1]);
			}
			adjustWeights();
			setTextmid(Integer.toString(j));
			cp.setpMNode(MNmap);
			// //System.out.println("MNmap---->"+MNmap);
			// //System.out.println("j" + j+" MNmap.GetMnnode(j) "+
			// (MNmap.get(j)).GetMnode(j));
		}
	}

	private void addOnode() {
		k = k + 1;
		ONmap.put(k, new OutNode(k - 1, 0.5));
		adjustWeights();
		setTextout(Integer.toString(k));
		cp.setpONode(ONmap);
		// //System.out.println("ONmap---->"+ONmap);
	}

	public void delOnode() {
		if (k >= 1) {
			int itemp = i;
			int jtemp = j;
			int ktemp = k;
			int btemp = b;
			clear();
			while( itemp >0) {
				addInode();	
				cp.addInnode();
				itemp--;
			}
			while(jtemp >0) {
				addMnode();	
				cp.addMiNode();
				jtemp--;
			}
			while ( ktemp-1>0){
				addOnode();	
				cp.addOuNode();
				ktemp--;
			}
			while (btemp>0) {
				addBnode();
				cp.addBaNode();
				btemp--;
			}
			adjustWeights();



		}
		// }
	}

	@SuppressWarnings("unused")
	private void loadOnode(final int numOnodes, final Double[] val,
			final int numOweights, final Double[][] weightval) {
		for (int j = 1; j <= numOnodes; j++) {
			ONmap.put(j, new OutNode(j - 1, val[j]));
			for (int ij = 1; ij <= numOweights; ij++) {
				ONmap.get(i).GetWeight(j - 1)
						.setWeight(weightval[j - 1][ij - 1]);
			}
			adjustWeights();
			setTextmid(Integer.toString(j));
			cp.setpONode(ONmap);
			// //System.out.println("MNmap---->"+MNmap);
			// //System.out.println("j" + j+" MNmap.GetMnnode(j) "+
			// (MNmap.get(j)).GetMnode(j));
		}
	}

	private void addBnode() {
		b = 1;
		bal = new BalastNode(0.5);
		adjustWeights();
		cp.setpBalast(bal);
		// //System.out.println("bal---->"+bal);

	}

	public void delBnode() {
		if (k >= 1) {
			int itemp = i;
			int jtemp = j;
			int ktemp = k;
			int btemp = b;
			clear();
			while( itemp >0) {
				addInode();	
				cp.addInnode();
				itemp--;
			}
			while(jtemp >0) {
				addMnode();	
				cp.addMiNode();
				jtemp--;
			}
			while ( ktemp>0){
				addOnode();	
				cp.addOuNode();
				ktemp--;
			}
			while (btemp-1>0) {
				addBnode();
				cp.addBaNode();
				btemp--;
			}
			adjustWeights();



		}

	}

	@SuppressWarnings("unused")
	private void loadBnode() {
		b = b + 1;
		bal = new BalastNode(bal.getBalast());
		adjustWeights();
		cp.setpBalast(bal);
		// //System.out.println("bal---->"+bal);

	}

	private void adjustWeights() {

		Integer l = b;

		// if any row in isolation no wiring needed.
		// if any middle row or output node and a balast wire
		// if any input and middle wire
		// if any middle and output node wire.
		// no wires needed between balast and input nodes.
		// for every input node and balast, add a wieght 0.7 to every mid node.
		// for every mid node an balast nodes add a wieght 0.7to every output
		// node.

		if ((i != 0) && (j != 0)) {
			// System.out.println("i="+i+" j="+j);
			for (int npq = 0; npq < i; npq++) {
				if (InNmap.get(npq + 1) != null) {
					// System.out.println("innode"+npq+"="+InNmap.get(npq+1).GetInode(npq));
					if (InNmap.get(npq + 1).GetInode(npq) == null) {
						InNmap.get(npq + 1).SetInode(npq, 0.5);
					}
				} else {
					InNmap.get(npq + 1).SetInode(npq, 0.5);
				}
				// System.out.println("innode"+npq+"="+InNmap.get(npq+1).GetInode(npq));
			}
			for (int np = 0; np < j; np++) {

				if (l != 0) {

					// //System.out.println("np = "+np+"i+1="+(i+1));
					addweights("mid", np, i + 1);

				} else {

					// //System.out.println("np = "+np+"i="+(i));
					addweights("mid", np, i);

				}
				MNmap.get(np + 1).SetMnode(np, 0.5);
				// //System.out.println("Mnnode"+np+"="+MNmap.get(np+1).GetMnode(np));
			}

		} // wire between input and middle
		if ((j != 0) && (k != 0)) {
			// //System.out.println("j="+j+" k="+k);
			for (int q = 0; q < k; q++) {

				if (l != 0) {

					// //System.out.println("q = "+q+"j+1="+(j+1));
					addweights("out", q, j + 1);

				} else {

					// //System.out.println("q = "+q+"j="+(j));
					addweights("out", q, j);
				}
				ONmap.get(q + 1).SetOnode(q, 0.5);
				// //System.out.println("Outnode"+q+"="+ONmap.get(q+1).GetOnode(q));
			}

		} // wire between middle and output

	}

	@SuppressWarnings("unused")
	private void loadWeights(final double[] weightvals) {

		Integer l = b;

		// if any row in isolation no wiring needed.
		// if any middle row or output node and a balast wire
		// if any input and middle wire
		// if any middle and output node wire.
		// no wires needed between balast and input nodes.
		// for every input node and balast, add a wieght 0.7 to every mid node.
		// for every mid node an balast nodes add a wieght 0.7to every output
		// node.

		if ((i != 0) && (j != 0)) {
			// //System.out.println("i="+i+" j="+j);
			for (int npq = 0; npq < i; npq++) {
				InNmap.get(npq + 1).SetInode(npq, 0.5);
				// //System.out.println("innode"+npq+"="+InNmap.get(npq+1).GetInode(npq));
			}
			for (int np = 0; np < j; np++) {

				if (l != 0) {

					// //System.out.println("np = "+np+"i+1="+(i+1));
					addweights("mid", np, i + 1);

				} else {

					// //System.out.println("np = "+np+"i="+(i));
					addweights("mid", np, i);

				}
				MNmap.get(np + 1).SetMnode(np, 0.5);
				// //System.out.println("Mnnode"+np+"="+MNmap.get(np+1).GetMnode(np));
			}

		} // wire between input and middle
		if ((j != 0) && (k != 0)) {
			// //System.out.println("j="+j+" k="+k);
			for (int q = 0; q < k; q++) {

				if (l != 0) {

					// //System.out.println("q = "+q+"j+1="+(j+1));
					addweights("out", q, j + 1);

				} else {

					// //System.out.println("q = "+q+"j="+(j));
					addweights("out", q, j);
				}
				ONmap.get(q + 1).SetOnode(q, 0.5);
				// //System.out.println("Outnode"+q+"="+ONmap.get(q+1).GetOnode(q));
			}

		} // wire between middle and output

	}

	private void addweights(String level, Integer nodenum,
			Integer numberofweights) {

		int in = nodenum;

		connections<Weight> weights = new connections<Weight>();
		int ws = numberofweights;
		for (int iu = 0; iu < ws; iu++) {
			weights.add(new Weight(0.7));
		}
		// //System.out.println(weights.size());
		// //System.out.println(weights.toString());
		if (level.equals("mid")) {
			// System.out.println(MNmap.get(in+1));

			if (((MNmap.get(in + 1)) != null)) {
				while (!((MNmap.get(in + 1)).GetWeights()).isEmpty()) {
					(MNmap.get(in + 1)).clearWeights(((MNmap.get(in + 1))
							.GetWeights()).size());

				}
			}
			(MNmap.get(in + 1)).SetWeights(weights);

		} else {
			if (level.equals("out")) {
				// //System.out.println(ONmap.get(in+1));
				while ((!((ONmap.get(in + 1)).GetWeights()).isEmpty())) { // ((ONmap.get(in+1))!=null)
																			// ||
					(ONmap.get(in + 1)).clearWeights(((ONmap.get(in + 1))
							.GetWeights()).size());
				}
			}
			(ONmap.get(in + 1)).SetWeights(weights);

		}
	}

	private void stepthrough() {
		new StepTrainthread().start();
		stept = false;
	}

	private void setW() {
		SetErrorThreshold seThreshold = new SetErrorThreshold(new JFrame());
		seThreshold.setVisible(true);
		if (seThreshold.getOK() == true) {
			cntThreshold = Integer.parseInt(seThreshold.getIterthreshold());
			erThreshold = Double.parseDouble(seThreshold.getError());
		}
	}// do this when you want to see it}

	public void setTextinput(String i) {
		numberofInputs.setTextentry(i);
	}

	public void setTextmid(String i) {
		numberofmidnodes.setTextentry(i);
	}

	public void setTextout(String i) {
		numberofOutputs.setTextentry(i);
	}

	public void setTextitr(String i) {
		iterations.setTextentry(i);
	}

	public void setTextError(String i) {
		Errornum.setTextentry(i);
	}

	public String getTextinput() {
		return numberofInputs.getTextentry();
	}

	public String getTextmid() {
		return numberofmidnodes.getTextentry();
	}

	public String getTextout() {
		return numberofOutputs.getTextentry();
	}

	public void openEngine() {
		new OpenEngine().start();
	}

	public void saveAsEngine() {
		new SaveEngine().start();
	}

	// ============================================================= main
	public static void main(String[] args) {
		Framework window = new Framework();
		window.setLocationRelativeTo(null); // Center window.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return VERSION;
	}

	/**
	 * @return the inputs
	 */
	public TreeMap<Integer, Double> getInputs() {
		return Inputs;
	}

	/**
	 * @param inputs
	 *            the inputs to set
	 */
	public void setInputs(TreeMap<Integer, Double> inputs) {
		Inputs = inputs;
	}

	/**
	 * @return the eng
	 */
	public Engine getEng() {
		return Eng;
	}

	/**
	 * @param eng
	 *            the eng to set
	 */
	public void setEng(Engine eng) {
		Eng = eng;
	}

	/**
	 * @return the threshholdError
	 */
	public Double getThreshholdError() {
		return ThreshholdError;
	}

	/**
	 * @param threshholdError
	 *            the threshholdError to set
	 */
	public void setThreshholdError(Double threshholdError) {
		ThreshholdError = threshholdError;
	}

	/**
	 * @return the trainingCases
	 */
	public TreeMap<Integer, TestCase> getTrainingCases() {
		return TrainingCases;
	}

	/**
	 * @param trainingCases
	 *            the trainingCases to set
	 */
	public void setTrainingCases(TreeMap<Integer, TestCase> trainingCases) {
		TrainingCases = trainingCases;
	}

	/**
	 * @return the iterationscount
	 */
	public Integer getIterationscount() {
		return Iterationscount;
	}

	/**
	 * @param iterationscount
	 *            the iterationscount to set
	 */
	public void setIterationscount(Integer iterationscount) {
		Iterationscount = iterationscount;
	}

	/**
	 * @return the casecount
	 */
	public Integer getCasecount() {
		return casecount;
	}

	/**
	 * @param casecount
	 *            the casecount to set
	 */
	public void setCasecount(Integer casecount) {
		this.casecount = casecount;
	}

	/**
	 * @return the tempnode
	 */
	public MidNode getTempnode() {
		return tempnode;
	}

	/**
	 * @param tempnode
	 *            the tempnode to set
	 */
	public void setTempnode(MidNode tempnode) {
		this.tempnode = tempnode;
	}

	public class Trainthread extends Thread {
		@Override
		public void run() {
			new Trainer().train();
		}
	}

	public class StepTrainthread extends Thread {
		@Override
		public void run() {
			new StepTrainer().steptrain();
		}
	}

	public class Stopthread extends Thread {
		@Override
		public void run() {
			new Stopper().stopI();
		}
	}

	public class UseEngine extends Thread {
		@Override
		public void run() {
			new UseEnginer().drive();
		}
	}

	public class SaveEngine extends Thread {
		@Override
		public void run() {
			new SaveEnginer(InNmap, MNmap, ONmap, bal).saveE();
		}
	}

	public class OpenEngine extends Thread {
		@Override
		public void run() {
			new SaveEnginer(InNmap, MNmap, ONmap, bal).openE();
		}
	}

	public class SaveEnginer {
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
		private Engine Eng;

		public SaveEnginer(TreeMap<Integer, InputNode> InNmap,
				TreeMap<Integer, MidNode> MNmap,
				TreeMap<Integer, OutNode> ONmap, BalastNode bal) {

			pInNmap = InNmap;
			pMNmap = MNmap;
			pONmap = ONmap;
			pbal = bal;

			setNuminnode(pInNmap.size());
			nummnode = pMNmap.size();
			numonode = pONmap.size();
			setNM(new Double[nummnode]);
			setOM(new Double[numonode]);
			Eng = new Engine(pInNmap, pMNmap, pONmap, pbal);
		}

		public void saveE() {
			try {
				if (i != 0) {
					// Are Middle Nodes defined? if not complain
					if (j != 0) {
						// Are Output Nodes defined? if not complain
						if (k != 0) {
							// is a balast node defined? if not complain
							if (b != 0) {
								// set up feed forward net elements

								Engine E = Eng;
								WriteFile wfile = new WriteFile();
								// File selectedEFile = wfile.openFile();
								wfile.opensaveFile(selectedEFile);
								wfile.writeEFile(selectedEFile, E);
								wfile.closeFile();
								// if ((Iterationcount >
								// 10000)||(gerror<ThreshholdError)) {
								Iter = false;
								// }
								// gerror = 0.0;

							} else {

								JOptionPane.showMessageDialog(new JPanel(),
										"No Bias Node was defined",
										"Node Not Found Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(new JPanel(),
									"No Output Node was defined",
									"Node Not Found Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(new JPanel(),
								"No Middle Node was defined",
								"Node Not Found Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				} else {
					JOptionPane.showMessageDialog(new JPanel(),
							"No Input Node was defined",
							"Node Not Found Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (Exception ie) {
				Thread.currentThread().interrupt();
			}

		}

		public Engine openE() {
			try {
				i = 0;
				InNmap.clear();
				setTextinput(Integer.toString(i));
				j = 0;
				MNmap.clear();
				setTextmid(Integer.toString(j));
				k = 0;
				ONmap.clear();
				setTextout(Integer.toString(k));
				b = 0;
				bal = null;

				Engine E = new Engine(InNmap, MNmap, ONmap, bal);
				ReadFile rfile = new ReadFile(i, k);
				File selectedEFile = rfile.openFile();
				rfile.openreadFile(selectedEFile);
				E = rfile.readEFile(selectedEFile);
				rfile.closeFile();
				i = (E.getInNmaps()).size();

				j = (E.getMNmaps()).size();

				k = (E.getONmaps()).size();
				Eng = E;
				InNmap = Eng.getInNmaps();
				MNmap = Eng.getMNmaps();
				ONmap = Eng.getONmaps();
				bal = Eng.getbals();
				setTextinput(Integer.toString(i));
				setTextmid(Integer.toString(j));
				setTextout(Integer.toString(k));
				// if ((Iterationcount > 10000)||(gerror<ThreshholdError)) {
				// System.out.println(i + ","+ j + "," + k);
				Iter = false;
				cp.setpInNode(InNmap);
				cp.setpMNode(MNmap);
				cp.setpONode(ONmap);
				cp.setpBalast(bal);
				// cp.setresults(Results);
				cp.redrawnet(InNmap, MNmap, ONmap, bal);
				b = 1;
				// gerror = 0.0;
				if (i != 0) {
					// Are Middle Nodes defined? if not complain
					if (j != 0) {
						// Are Output Nodes defined? if not complain
						if (k != 0) {
							// is a balast node defined? if not complain
							if (b != 0) {
								// set up feed forward net elements
								return E;
							} else {

								JOptionPane.showMessageDialog(new JPanel(),
										"No Bias Node was defined",
										"Node Not Found Error",
										JOptionPane.ERROR_MESSAGE);
								return null;
							}
						} else {
							JOptionPane.showMessageDialog(new JPanel(),
									"No Output Node was defined",
									"Node Not Found Error",
									JOptionPane.ERROR_MESSAGE);
							return null;
						}
					} else {
						JOptionPane.showMessageDialog(new JPanel(),
								"No Middle Node was defined",
								"Node Not Found Error",
								JOptionPane.ERROR_MESSAGE);
						return null;
					}
				} else {
					JOptionPane.showMessageDialog(new JPanel(),
							"No Input Node was defined",
							"Node Not Found Error", JOptionPane.ERROR_MESSAGE);
					return null;
				}

			} catch (Exception ie) {
				System.out.println(ie);
				Thread.currentThread().interrupt();
			}
			return null;
		}

		/**
		 * @return the numinnode
		 */
		public Integer getNuminnode() {
			return numinnode;
		}

		/**
		 * @param numinnode
		 *            the numinnode to set
		 */
		public void setNuminnode(Integer numinnode) {
			this.numinnode = numinnode;
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
		 * @return the unode
		 */
		public InputNode getUnode() {
			return unode;
		}

		/**
		 * @param unode
		 *            the unode to set
		 */
		public void setUnode(InputNode unode) {
			this.unode = unode;
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

	public class UseEnginer {
		private Double testcaseerror;
		private Double gerror;
		private boolean igo;
		private int totalnumresults;

		public void drive() {
			// This class is to use the engine onces trained.
			// check to see if stop button is pressed. if so leave training
			// method
			ArrayList<TestCase> eachCase = new ArrayList<TestCase>();
			ArrayList<TestCase> eachTCase = new ArrayList<TestCase>();
			ArrayList<TestCase> eachResult = new ArrayList<TestCase>();
			ArrayList<TestCase> normalizedCase;
			ArrayList<TestCase> normalizedTCase;
			ArrayList<TestCase> DenormalizedCase;
			// //System.out.println("InNmap>>"+InNmap);
			WriteFile wfile = new WriteFile();

			ReadFile rf = new ReadFile(i, 0);
			ReadFile tf = new ReadFile(i,k);
			eachTCase = tf.loadFile(selectedFile);
			eachCase = rf.loadFile(selectedRFile);
			if (eachCase != null) {
				Normalize nml = new Normalize();
				Normalize nmlt = new Normalize();

				normalizedTCase = nmlt.normalize(eachTCase);

				double biggestNumineachtCase = nmlt.getTop();
				double smallestNumineachtCase = nmlt.getBot();
				normalizedCase = nml.normalizeresult(eachCase,smallestNumineachtCase,biggestNumineachtCase);
				int totalnumcases = nml.count();
				DeNormalize dnml = new DeNormalize();
				setTotalnumresults(dnml.count());
				int casenumbercount = 0;
				forward = new Forward(InNmap, MNmap, ONmap, bal);
				// backward = new
				// Backward(InNmap,MNmap,ONmap,bal,SingleTC,Results);
				// Iterator itr = eachCase.iterator();
				int casenum = 0;
				if (!iterations.getTextentry().isEmpty()) {
					Iterationcount = Integer
							.parseInt(iterations.getTextentry());
				} else {
					Iterationcount = 0;
					Iter = true;
					iterations.setTextentry(Integer.toString(Iterationcount));
				}
				if (!casenumber.getTextentry().isEmpty()) {
					casenumbercount = Integer.parseInt(casenumber
							.getTextentry());
				} else {
					casenumbercount = 0;
					Iter = true;
					casenumber.setTextentry(Integer.toString(casenumbercount));
				}
				try {
					// Are Input nodes defined? if not complain
					if (i != 0) {
						// Are Middle Nodes defined? if not complain
						if (j != 0) {
							// Are Output Nodes defined? if not complain
							if (k != 0) {
								// is a balast node defined? if not complain
								if (b != 0) {
									// set up feed forward net elements

									setTestcaseerror(0.0);
									setGerror(0.0);

									// }
									// //System.out.println("Output Node Values");
									// for(int wre = 0;wre<ONmap.size();wre++){
									// //System.out.println((ONmap.get(wre+1)).GetOnode(wre));
									// //System.out.println((ONmap.get(wre+1)).GetWeights().toString());

									setIgo(true);
									if (Iter != false) {
										Iter = true;
									} else {
										if (Iter != true) {
											{
												Iter = true;
											}
											if (stept != false) {
												stept = true;
											}
										}
									}

									while (Iter != false) {

										casenum = 0;
										for (Iterator<TestCase> itr = normalizedCase
												.iterator(); itr.hasNext();) {
											Thread.sleep(10);
											// //System.out.println("Inter="+Iterationcount+"CaseNum="+casenum);
											TestCase tc = itr.next();
											TestCase rc = tc;

											// //System.out.println(tc.getInputs()+"\n");
											// //System.out.println("           "+InNmap.get(1).GetInode(0)+","+InNmap.get(2).GetInode(1)+","+InNmap.get(3).GetInode(2)+","+InNmap.get(4).GetInode(3)+","+InNmap.get(5).GetInode(4));
											// //System.out.println("Inodemap= "+InNmap.toString());
											// //System.out.println(MNmap.get(1).GetMnode(0));
											// //System.out.println("Mnodemap= "+MNmap.toString());
											// //System.out.println("Onodemap= "+ONmap.toString());
											// //System.out.println(tc.getOutputs()+"\n");
											// //System.out.println(casenum
											// +"\n");
											casenum++;
											casenumbercount = casenum;
											// //System.out.println("tc"+tc);
											// //System.out.println("forward"+forward);
											casenumber.setTextentry(Integer
													.toString(casenumbercount));
											cp.setTestCase(tc);
											Results = forward.Net(InNmap,
													MNmap, ONmap, bal, tc);
											// System.out.println("Results = " +
											// Results.get(casenum));
											// System.out.println("results"+Results.toString());
											// System.out.println("rc = " +
											// rc.getInputs()+ "  ,  " +
											// rc.getOutputs());
											rc.setOutputs(new ArrayList<Double>(
													Results.values()));

											// System.out.println("midnode"+gh+"= "+MNmap.get(gh).GetMnode(gh-1));

											// DenormalizedCase =
											// dnml.denormalize(EachCase.set(rc),rc2);
											// System.out.println("outputs"+rc.getOutputs());
											// System.out.println("results"+Results.toString());
											// System.out.println("rc = " +
											// rc.getInputs()+ "  ,  " +
											// rc.getOutputs());
											// System.out.println("eachCase = "
											// +
											// eachCase.get(casenum-1).getInputs()
											// + "  ,  " +
											// eachCase.get(casenum-1).getOutputs());
											eachResult.add(rc);
											// System.out.println("eachResult = "
											// +
											// eachResult.get(casenum-1).getInputs()
											// +
											// eachResult.get(casenum-1).getOutputs());

											// for(int gh =1;
											// gh<MNmap.size()+1;gh++){
											// //System.out.println("MNmap++"+MNmap.get(gh));
											// //System.out.println("midnode"+gh+"= "+MNmap.get(gh).GetMnode(gh-1));
											// }
											// for(int gh =1;
											// gh<ONmap.size()+1;gh++){
											// //System.out.println("ONmap++"+ONmap.get(gh));
											// //System.out.println("outnode"+gh+"= "+ONmap.get(gh).GetOnode(gh-1));
											// }

											setTestcaseerror(0.0);
											cp.setpInNode(InNmap);
											cp.setpMNode(MNmap);
											cp.setpONode(ONmap);
											cp.setpBalast(bal);
											cp.setresults(Results);
											cp.redrawstrings();

											// for (int per
											// =1;per<tc.getOsize()+1;per++){
											//
											// //testcaseerror = testcaseerror +
											// (tc.getOutput(per-1)-Results.get(per))*(tc.getOutput(per-1)-Results.get(per));
											// //System.out.println("testerror -->"
											// +testcaseerror);
											// }
											// System.out.println("I number "+Iterationcount+" TestCase Error for case # "+casenum+" = "+testcaseerror);
											// System.out.println(Results.toString());

											// gerror = gerror +testcaseerror;
											// System.out.println("gerror="+gerror);
											// backward.Train(InNmap,MNmap,ONmap,bal,tc,Results);
											// for(int gh =1;
											// gh<MNmap.size()+1;gh++){
											// //System.out.println("MNmap++"+MNmap.get(gh));
											// //System.out.println("midnode"+gh+"= "+MNmap.get(gh).GetMnode(gh-1));
											// }
											// TrainingSetError.put(casenum,testcaseerror);
											ONmap = backward.getOWeights();
											MNmap = backward.getMWeights();
											bal = backward.getBWeight();

											// //System.out.println("Results--->"+Results.toString());
											Thread.sleep(10);

										}

										if (casenumbercount == totalnumcases) {
											Iterationcount++;
										}
										// gerror = gerror/casenum;

										for (int gh = 1; gh < totalnumcases + 1; gh++) {
											// System.out.println("eachResult = "
											// +
											// eachResult.get(gh-1).getInputs()
											// +
											// eachResult.get(gh-1).getOutputs());

										}
										DenormalizedCase = dnml.denormalize(
												eachCase, eachResult,smallestNumineachtCase,biggestNumineachtCase);

										wfile.opensaveFile(selectedRFile);

										int casenumcount = 0;
										String line = "";
										// System.out.println("eachResult = " +
										// eachResult.get(gh-1).getInputs() +
										// eachResult.get(gh-1).getOutputs());
										TestCase tc = new TestCase();
										for (Iterator<TestCase> itr = DenormalizedCase
												.iterator(); itr.hasNext();) {
											tc = itr.next();
											// TestCase rc =
											// (TestCase)itr.next();

											casenumcount++;
											// System.out.println(tc.getInputs()+"\n");
											// e
											// System.out.println(tc.getOutputs()+"\n");
											line = "";
											for (int ssr = 1; ssr < tc
													.getIsize() + 1; ssr++) {

												line = line
														+ tc.getInput(ssr - 1)
														+ ",";
											}

											int osize = tc.getOsize();
											for (int ssr = 1; ssr < osize; ssr++) {

												line = line
														+ tc.getOutput(ssr - 1)
														+ ",";
											}
											line = line
													+ tc.getOutput(osize - 1);
											// System.out.println("DenormalizedCase ="+
											// line);
											wfile.writeFile(line, casenumcount);
										}

										// System.out.println("Iteration "+Iterationcount+" General Mean Squared Error = "+gerror);
										// GeneralSetError.put(Iterationcount,gerror);
										// Errornum.setTextentry(Double.toString(gerror));
										// DenormalizedCase =
										// dnml.denormalize(eachCase,eachResult);

										// System.out.println(DenormalizedCase.toString());
										// iterations.setTextentry(Integer.toString(Iterationcount));
										Thread.sleep(10);
										// Calculate output Error here.
										wfile.closeFile();
										// if ((Iterationcount >
										// 10000)||(gerror<ThreshholdError)) {
										Iter = false;
										// }
										// gerror = 0.0;
									}

								} else {
									JOptionPane.showMessageDialog(new JPanel(),
											"No Output Node was defined",
											"Node Not Found Error",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
							} else {
								JOptionPane.showMessageDialog(new JPanel(),
										"No Middle Node was defined",
										"Node Not Found Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(new JPanel(),
									"No Input Node was defined",
									"Node Not Found Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				} catch (Exception ie) {
					Thread.currentThread().interrupt();
				}

			} else {
				JOptionPane.showMessageDialog(new JPanel(), "File Not Defined",
						"Fil Not Found Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		/**
		 * @return the testcaseerror
		 */
		public Double getTestcaseerror() {
			return testcaseerror;
		}

		/**
		 * @param testcaseerror
		 *            the testcaseerror to set
		 */
		public void setTestcaseerror(Double testcaseerror) {
			this.testcaseerror = testcaseerror;
		}

		/**
		 * @return the gerror
		 */
		public Double getGerror() {
			return gerror;
		}

		/**
		 * @param gerror
		 *            the gerror to set
		 */
		public void setGerror(Double gerror) {
			this.gerror = gerror;
		}

		/**
		 * @return the igo
		 */
		public boolean isIgo() {
			return igo;
		}

		/**
		 * @param igo
		 *            the igo to set
		 */
		public void setIgo(boolean igo) {
			this.igo = igo;
		}

		/**
		 * @return the totalnumresults
		 */
		public int getTotalnumresults() {
			return totalnumresults;
		}

		/**
		 * @param totalnumresults
		 *            the totalnumresults to set
		 */
		public void setTotalnumresults(int totalnumresults) {
			this.totalnumresults = totalnumresults;
		}
	}

	public class Trainer {
		private boolean igo;

		public boolean checkfile(ReadFile rf) {
			if (rf.check() == true) {
				return true;
			} else {
				return false;
			}

		}

		public void train() { // check to see if the input and output data is
								// loaded
								// if not display a dialog complaining and exit
								// method
								// create forward and backward object if not
								// aready created
								// start training loop.
								// feed data via a loop input forward object and
								// capture output in map
								// feed new output data map and output data into
								// backward object
								// check to see if stop button is pressed. if so
								// leave training method
			ArrayList<TestCase> eachCase = new ArrayList<TestCase>();
			ArrayList<TestCase> normalizedCase;
			// //System.out.println("InNmap>>"+InNmap);

			ReadFile rf = new ReadFile(i, k);

			eachCase = rf.loadFile(selectedFile);
			if (eachCase != null) {
				Normalize nml = new Normalize();
				normalizedCase = nml.normalize(eachCase);
				int totalnumcases = nml.count();
				int casenumbercount = 0;
				forward = new Forward(InNmap, MNmap, ONmap, bal);
				backward = new Backward(InNmap, MNmap, ONmap, bal, SingleTC,
						Results);
				// Iterator itr = eachCase.iterator();
				int casenum = 0;
				if (!iterations.getTextentry().isEmpty()) {
					Iterationcount = Integer
							.parseInt(iterations.getTextentry());
				} else {
					Iterationcount = 0;
					Iter = true;
					iterations.setTextentry(Integer.toString(Iterationcount));
				}
				if (!casenumber.getTextentry().isEmpty()) {
					casenumbercount = Integer.parseInt(casenumber
							.getTextentry());
				} else {
					casenumbercount = 0;
					Iter = true;
					casenumber.setTextentry(Integer.toString(casenumbercount));
				}
				try {
					// Are Input nodes defined? if not complain
					if (i != 0) {
						// Are Middle Nodes defined? if not complain
						if (j != 0) {
							// Are Output Nodes defined? if not complain
							if (k != 0) {
								// is a balast node defined? if not complain
								if (b != 0) {
									// set up feed forward net elements

									Double testcaseerror = 0.0;
									Double gerror = 0.0;
									// //System.out.println("Training Network");
									// //System.out.println(InNmap.toString());
									// //System.out.println(MNmap.toString());
									// //System.out.println(ONmap.toString());
									// //System.out.println("InNode Values");
									// for(int wre = 0;wre<InNmap.size();wre++){
									// //System.out.println((InNmap.get(wre+1)).GetInode(wre));
									// }
									// //System.out.println("Hidden Node Values");
									// for(int wre = 0;wre<MNmap.size();wre++){
									// //System.out.println((MNmap.get(wre+1)).GetMnode(wre));
									// //System.out.println((MNmap.get(wre+1)).GetWeights().toString());

									// }
									// //System.out.println("Output Node Values");
									// for(int wre = 0;wre<ONmap.size();wre++){
									// //System.out.println((ONmap.get(wre+1)).GetOnode(wre));
									// //System.out.println((ONmap.get(wre+1)).GetWeights().toString());

									setIgo(true);
									if (Iter != false) {
										Iter = true;
									} else {
										if (Iter != true) {
											{
												Iter = true;
											}
											if (stept != false) {
												stept = true;
											}
										}
									}

									while (Iter != false) {

										casenum = 0;
										for (Iterator<TestCase> itr = normalizedCase
												.iterator(); itr.hasNext();) {
											Thread.sleep(10);
											// //System.out.println("Inter="+Iterationcount+"CaseNum="+casenum);
											TestCase tc = itr.next();
											// //System.out.println(tc.getInputs()+"\n");
											// //System.out.println("           "+InNmap.get(1).GetInode(0)+","+InNmap.get(2).GetInode(1)+","+InNmap.get(3).GetInode(2)+","+InNmap.get(4).GetInode(3)+","+InNmap.get(5).GetInode(4));
											// //System.out.println("Inodemap= "+InNmap.toString());
											// //System.out.println(MNmap.get(1).GetMnode(0));
											// //System.out.println("Mnodemap= "+MNmap.toString());
											// //System.out.println("Onodemap= "+ONmap.toString());
											// //System.out.println(tc.getOutputs()+"\n");
											// //System.out.println(casenum
											// +"\n");
											casenum++;
											casenumbercount = casenum;
											// //System.out.println("tc"+tc);
											// //System.out.println("forward"+forward);
											casenumber.setTextentry(Integer
													.toString(casenumbercount));
											cp.setTestCase(tc);
											Results = forward.Net(InNmap,
													MNmap, ONmap, bal, tc);
											for (int gh = 1; gh < MNmap.size() + 1; gh++) {
												// System.out.println("MNmap++"+MNmap.get(gh));
												// System.out.println("midnode"+gh+"= "+MNmap.get(gh).GetMnode(gh-1));
											}
											for (int gh = 1; gh < ONmap.size() + 1; gh++) {
												// System.out.println("ONmap++"+ONmap.get(gh));
												// System.out.println("outnode"+gh+"= "+ONmap.get(gh).GetOnode(gh-1));
											}

											testcaseerror = 0.0;
											cp.setpInNode(InNmap);
											cp.setpMNode(MNmap);
											cp.setpONode(ONmap);
											cp.setpBalast(bal);
											cp.setresults(Results);
											cp.redrawstrings();
											// //System.out.println("outputs"+tc.getOutputs());
											// //System.out.println("results"+Results.toString());
											for (int per = 1; per < tc
													.getOsize() + 1; per++) {

												testcaseerror = testcaseerror
														+ (tc.getOutput(per - 1) - Results
																.get(per))
														* (tc.getOutput(per - 1) - Results
																.get(per));
												// System.out.println("testerror -->"
												// +testcaseerror);
											}
											// System.out.println("I number "+Iterationcount+" TestCase Error for case # "+casenum+" = "+testcaseerror);
											// System.out.println(Results.toString());

											gerror = gerror + testcaseerror;
											// System.out.println("gerror="+gerror);
											backward.train(InNmap, MNmap,
													ONmap, bal, tc, Results);
											for (int gh = 1; gh < MNmap.size() + 1; gh++) {
												// System.out.println("MNmap++"+MNmap.get(gh));
												// System.out.println("midnode"+gh+"= "+MNmap.get(gh).GetMnode(gh-1));
											}
											TrainingSetError.put(casenum,
													testcaseerror);
											ONmap = backward.getOWeights();
											MNmap = backward.getMWeights();
											bal = backward.getBWeight();

											// //System.out.println("Results--->"+Results.toString());
											Thread.sleep(10);

										}

										if (casenumbercount == totalnumcases) {
											Iterationcount++;
										}
										gerror = gerror / casenum;
										// //System.out.println(TrainingSetError.toString());
										// System.out.println("Iteration "+Iterationcount+" General Mean Squared Error = "+gerror);
										GeneralSetError.put(Iterationcount,
												gerror);
										Errornum.setTextentry(Double
												.toString(gerror));

										iterations.setTextentry(Integer
												.toString(Iterationcount));
										Thread.sleep(10);
										// Calculate output Error here.

										if ((Iterationcount > cntThreshold)
												|| (gerror < erThreshold)) {
											Iter = false;
										}
										gerror = 0.0;
									}

								} else {

									JOptionPane.showMessageDialog(new JPanel(),
											"No Bias Node was defined",
											"Node Not Found Error",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
							} else {
								JOptionPane.showMessageDialog(new JPanel(),
										"No Output Node was defined",
										"Node Not Found Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(new JPanel(),
									"No Middle Node was defined",
									"Node Not Found Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} else {
						JOptionPane.showMessageDialog(new JPanel(),
								"No Input Node was defined",
								"Node Not Found Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

				} catch (Exception ie) {
					Thread.currentThread().interrupt();
				}

			} else {
				JOptionPane.showMessageDialog(new JPanel(), "File Not Defined",
						"Fil Not Found Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		/**
		 * @return the igo
		 */
		public boolean isIgo() {
			return igo;
		}

		/**
		 * @param igo
		 *            the igo to set
		 */
		public void setIgo(boolean igo) {
			this.igo = igo;
		}
	}

	public class StepTrainer {

		public void steptrain() { // check to see if the input and output data
									// is loaded
									// if not display a dialog complaining and
									// exit method
									// create forward and backward object if not
									// aready created
									// start training loop.
									// feed data via a loop input forward object
									// and capture output in map
									// feed new output data map and output data
									// into backward object
									// check to see if stop button is pressed.
									// if so leave training method

			ArrayList<TestCase> eachCase = new ArrayList<TestCase>();
			ArrayList<TestCase> normalizedCase;
			// //System.out.println("InNmap>>"+InNmap);
			forward = new Forward(InNmap, MNmap, ONmap, bal);
			backward = new Backward(InNmap, MNmap, ONmap, bal, SingleTC,
					Results);
			ReadFile rf = new ReadFile(i, k);
			eachCase = rf.loadFile(selectedFile);
			if (eachCase != null) {
				Normalize nml = new Normalize();
				normalizedCase = nml.normalize(eachCase);
				int totalnumcases = nml.count();
				int casenumbercount = 0;
				// Iterator itr = eachCase.iterator();
				int casenum = 0;
				int casenum2 = 0;
				// Iterationcount = 0;
				// casenumber.setTextentry(Integer.toString(casenumbercount));
				// //System.out.println("Total Case Number "+totalnumcases);
				stept = true;
				if (!casenumber.getTextentry().isEmpty()) {
					casenumbercount = Integer.parseInt(casenumber
							.getTextentry());
				} else {
					casenumbercount = 0;
					Iter = true;
					casenumber.setTextentry(Integer.toString(casenumbercount));
				}
				if (!iterations.getTextentry().isEmpty()) {
					Iterationcount = Integer
							.parseInt(iterations.getTextentry());
				} else {
					Iterationcount = 0;
					Iter = true;
					iterations.setTextentry(Integer.toString(Iterationcount));
				}
				try {
					// Are Input nodes defined? if not complain
					if (i != 0) {
						// Are Middle Nodes defined? if not complain
						if (j != 0) {
							// Are Output Nodes defined? if not complain
							if (k != 0) {
								// is a balast node defined? if not complain
								if (b != 0) {
									// set up feed forward net elements
									if (casenumbercount >= totalnumcases) {
										Iterationcount++;
										casenum = 0;
										casenumbercount = 0;
									}
									Double testcaseerror = 0.0;
									Double gerror = 0.0;
									// //System.out.println("Training Network");
									// //System.out.println(InNmap.toString());
									// //System.out.println(MNmap.toString());
									// //System.out.println(ONmap.toString());
									// //System.out.println("InNode Values");
									// for(int wre = 0;wre<InNmap.size();wre++){
									// //System.out.println((InNmap.get(wre+1)).GetInode(wre));
									// }
									// //System.out.println("Hidden Node Values");
									// for(int wre = 0;wre<MNmap.size();wre++){
									// //System.out.println((MNmap.get(wre+1)).GetMnode(wre));
									// //System.out.println((MNmap.get(wre+1)).GetWeights().toString());

									// }
									// //System.out.println("Output Node Values");
									// for(int wre = 0;wre<ONmap.size();wre++){
									// //System.out.println((ONmap.get(wre+1)).GetOnode(wre));
									// //System.out.println((ONmap.get(wre+1)).GetWeights().toString());

									// }
									boolean igo = true;
									if (Iter) {
										igo = false; // new
														// Stopthread().start();
									} else {
										if (stept) {
											igo = true;
										} else {
											igo = false;
										}
									}
									while (igo) {
										igo = false;
										casenum = Integer.parseInt(casenumber
												.getTextentry());
										// totalnumcases = nml.count();

										Iterator<TestCase> itr = normalizedCase
												.iterator();
										boolean itflag = true;
										if (Iter) {
											itflag = false;
										} else {
											if (stept) {
												itflag = true;
											} else {
												itflag = false;
											}
										}
										while (itflag) {
											itflag = false;
											if (casenum >= totalnumcases) {
												// Iterationcount++;
												casenum = 0;
												casenumbercount = 0;
											}
											// //System.out.println("Inter="+Iterationcount+"CaseNum="+casenum);
											casenum2 = 0;
											TestCase tc = itr.next();
											while (casenum2 != casenum) {
												tc = itr.next();
												casenum2++;
											}
											// //System.out.println(tc.getInputs()+"\n");
											// //System.out.println("           "+InNmap.get(1).GetInode(0)+","+InNmap.get(2).GetInode(1)+","+InNmap.get(3).GetInode(2)+","+InNmap.get(4).GetInode(3)+","+InNmap.get(5).GetInode(4));
											// //System.out.println("Inodemap= "+InNmap.toString());
											// //System.out.println(MNmap.get(1).GetMnode(0));
											// //System.out.println("Mnodemap= "+MNmap.toString());
											// //System.out.println("Onodemap= "+ONmap.toString());
											// //System.out.println(tc.getOutputs()+"\n");
											// //System.out.println(casenum
											// +"\n");
											casenum++;
											casenumbercount = casenum;
											// //System.out.println("tc"+tc);
											// //System.out.println("forward"+forward);
											casenumber.setTextentry(Integer
													.toString(casenumbercount));

											cp.setTestCase(tc);
											Results = forward.Net(InNmap,
													MNmap, ONmap, bal, tc);
											testcaseerror = 0.0;
											cp.setpInNode(InNmap);
											cp.setpMNode(MNmap);
											cp.setpONode(ONmap);
											cp.setpBalast(bal);
											cp.setresults(Results);
											cp.redrawstrings();
											// //System.out.println("outputs"+tc.getOutputs());
											// //System.out.println("results"+Results.toString());
											for (int per = 1; per < tc
													.getOsize() + 1; per++) {

												testcaseerror = testcaseerror
														+ (tc.getOutput(per - 1) - Results
																.get(per))
														* (tc.getOutput(per - 1) - Results
																.get(per));
												// //System.out.println(testcaseerror);
											}
											// //System.out.println("I number "+Iterationcount+" TestCase Error for case # "+casenum+" = "+testcaseerror);
											// //System.out.println(Results.toString());

											gerror = gerror + testcaseerror;
											// //System.out.println("gerror="+gerror);
											backward.train(InNmap, MNmap,
													ONmap, bal, tc, Results);
											TrainingSetError.put(casenum,
													testcaseerror);
											ONmap = backward.getOWeights();
											MNmap = backward.getMWeights();
											bal = backward.getBWeight();

											// //System.out.println("Results--->"+Results.toString());
											Thread.sleep(10);

										}

										gerror = gerror / casenum;
										// //System.out.println(TrainingSetError.toString());
										// System.out.println("Iteration "+Iterationcount+" General Mean Squared Error = "+gerror);
										GeneralSetError.put(Iterationcount,
												gerror);
										Errornum.setTextentry(Double
												.toString(gerror));

										iterations.setTextentry(Integer
												.toString(Iterationcount));
										Thread.sleep(10);
										// Calculate output Error here.

										if ((Iterationcount > cntThreshold)
												|| (gerror < erThreshold)) {

											Iter = false;
										}
										gerror = 0.0;
									}
									if (casenum >= totalnumcases) {
										Iterationcount++;
										casenum = 0;
										casenumbercount = 0;
									}
								} else {
									JOptionPane.showMessageDialog(new JPanel(),
											"No Output Node was defined",
											"Node Not Found Error",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
							} else {
								JOptionPane.showMessageDialog(new JPanel(),
										"No Middle Node was defined",
										"Node Not Found Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						} else {
							JOptionPane.showMessageDialog(new JPanel(),
									"No Input Node was defined",
									"Node Not Found Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				} catch (Exception ie) {
					Thread.currentThread().interrupt();
				}

			} else {
				JOptionPane.showMessageDialog(new JPanel(), "File Not Defined",
						"Fil Not Found Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

	public class Stopper {
		public void stopI() {
			try {

				if (Iter != false) {
					Iter = false;
				} else {
					if (Iter != true) {
						Iterationcount = 0;
						iterations.setTextentry(Integer
								.toString(Iterationcount));
						if (stept != false) {
							stept = false;
						} else {
							if ((stept == false) && (Iter != false)) {
							} else {
								if ((stept == false) && (Iter == false)) {
									Iterationcount = 0;
									iterations.setTextentry(Integer
											.toString(Iterationcount));
								}
							}// Thread.currentThread().interrupt();
						}
					}
				}

			} catch (Exception ie) {
				Thread.currentThread().interrupt();
			}
		}
	}
}