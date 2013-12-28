 
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class ReadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TestCase> eachCase = new ArrayList<TestCase>();
	private TestCase newTestCase;
	private int i;
	private int j;
	private int k;
	private int numoinputs;
	private int numooutputs;
	private JFileChooser fileChooser = new JFileChooser();
	// instance variables - replace the example below with your own
	// private P5Panel showPanel;
	private JButton submitButton;
	private JButton clearButton;
	private JButton exitButton;
	private JFrame frame;
	private JLabel filenameLabel;
	private File currentFile;
	private JMenu menu;
	private JMenuItem oitem;
	private JMenuItem citem;
	private JMenuItem sitem;
	private JMenuItem eitem;
	private FileReader rfile;
	public boolean fileexists = false;
	private Integer inputnums;
	private Integer midnums;
	private Integer outnums;
	private ObjectInputStream restore;
	private Object obj;
	private String obj2;
	private int ins;

	public ReadFile(int numofinp, int numofoutput) {
		i = 0;
		j = 0;
		numoinputs = numofinp;
		numooutputs = numofoutput;
	}

	public boolean check() {
		if (fileexists == true) {
			return true;// Custom button text
		} else {
			return false;
		}
	}

	public ArrayList<TestCase> loadFile(File textFile) {
		// System.out.println(textFile.getPath());
		try {
			if (textFile == null) {

				JOptionPane.showMessageDialog(new JPanel(),
						"No File was defined", "File Not Found Error",
						JOptionPane.ERROR_MESSAGE);
				return null;
			} else {
				fileexists = true;
				BufferedReader readFile = new BufferedReader(new FileReader(
						textFile));

				String line = readFile.readLine();

				while (line != null) {
					// System.out.println(i +")-->"+line);
					StringTokenizer st = new StringTokenizer(line, ",");
					int numotokens = st.countTokens();
					if (numotokens >= (numoinputs+numooutputs)){
					newTestCase = new TestCase();
					newTestCase.AddTestCaseKey(i);
					while (j != numoinputs) {
						String inputS = st.nextToken();
						// System.out.println(j +"'"+inputS+"\n");
						newTestCase
								.AddTestCaseInput(Double.parseDouble(inputS));
						j++;
					}
					j = 0;
					while (j != numooutputs) {
						String outputS = st.nextToken();
						// System.out.println(j +"'"+outputS+"\n");
						newTestCase.AddTestCaseOutput(Double
								.parseDouble(outputS));
						j++;
					}
					eachCase.add(newTestCase);
					// //System.out.println(eachCase);
					// newTestCase.clear();
					line = readFile.readLine();
					i++;
					j = 0;
				}
				if (numotokens < (numoinputs+numooutputs)){   
					

						JOptionPane.showMessageDialog(new JPanel(),
								"Not enough data for current engine",
								"choose a different data set or change the engine",
								JOptionPane.ERROR_MESSAGE);
						readFile.close();
						return null;
					
					
					
				}}
				readFile.close();
			}
			return eachCase;
		} catch (IOException exc) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public Engine readEFile(File selectedEfile) {
		TreeMap<Integer, InputNode> Inputs = new TreeMap<Integer, InputNode>();
		TreeMap<Integer, MidNode> Mids = new TreeMap<Integer, MidNode>();
		TreeMap<Integer, OutNode> Outs = new TreeMap<Integer, OutNode>();
		BalastNode bals = new BalastNode(0.5);
		Double balsS = null;
		setInputnums(0);
		setMidnums(0);
		setOutnums(0);
		ArrayList<String> num = null;
		ArrayList<String> allinputs = null;
		ArrayList<String> allmids = null;
		ArrayList<String> alloutputs = null;
		ArrayList<ArrayList<String>> midweights = null;
		ArrayList<ArrayList<String>> outweights = null;
		Engine E;

		try {
			// reader = new ObjectInputStream(new FileInputStream("sth"));
			FileInputStream saveFile = new FileInputStream(selectedEfile);
			restore = new ObjectInputStream(saveFile);
			setObj(null);
			setObj2(null);
			int lines = 0;
			boolean flag = true;
			while (flag == true) {
				lines++;
				if (lines == 1) {
					setObj(restore.readObject());
				}

				if (lines == 2) {

					num = extracted();
					// System.out.println(num);
				}
				if (lines == 3) {
					allinputs = extracted();
					// System.out.println(allinputs);
				}
				if (lines == 4) {
					allmids = extracted();
					// System.out.println(allmids);
				}
				if (lines == 5) {
					midweights = (ArrayList<ArrayList<String>>) restore
							.readObject();
					// System.out.println(midweights);
				}

				if (lines == 6) {
					alloutputs = extracted();
					// System.out.println(allmids);
				}
				if (lines == 7) {
					outweights = (ArrayList<ArrayList<String>>) restore
							.readObject();
					// System.out.println(outweights);
				}
				if (lines == 8) {
					setObj(restore.readObject());
				}
				if (lines == 9) {
					balsS = (Double) restore.readObject();
					// System.out.println(balsS);
					flag = false;
				}
			}

		} catch (EOFException e) {
			System.out.println("finished reading");
			// E = new Engine(Inputs,Mids,Outs,bals);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("Error: the file was not found!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setIns(0);
		Integer numofinputs = 0;
		Integer numofmids = 0;
		Integer numofouts = 0;

		Object insS = num.get(0);
		// System.out.println(insS.toString());
		numofinputs = Integer.parseInt(insS.toString());
		insS = num.get(1);
		// System.out.println(insS.toString());
		numofmids = Integer.parseInt(insS.toString());
		insS = num.get(2);
		// System.out.println(insS.toString());
		numofouts = Integer.parseInt(insS.toString());

		// Integer numofinputs = Integer.valueOf(num.get(0));
		for (int i = 1; i <= numofinputs; i++) {
			Inputs.put(
					i,
					new InputNode(i - 1, Double.parseDouble(((Object) allinputs
							.get(i - 1)).toString())));
		}
		// Integer numofmids = Integer.parseInt(num.get(1));
		for (int j = 1; j <= numofmids; j++) {
			Mids.put(
					j,
					new MidNode(j - 1, Double.parseDouble(((Object) allmids
							.get(j - 1)).toString())));
		}

		// //System.out.println(weights.size());
		for (int j = 1; j <= numofmids; j++) {
			connections<Weight> weights = new connections<Weight>();
			int ws = numofinputs;
			for (int iu = 0; iu < ws; iu++) {
				weights.add(new Weight(Double.parseDouble(((Object) midweights
						.get(j - 1).get(iu)).toString())));
			}
			(Mids.get(j)).SetWeights(weights);

		} // Integer numofouts = Integer.parseInt(num.get(2));
		for (int j = 1; j <= numofouts; j++) {
			Outs.put(
					j,
					new OutNode(j - 1, Double.parseDouble(((Object) alloutputs
							.get(j - 1)).toString())));
		}

		// //System.out.println(weights.size());
		for (int j = 1; j <= numofouts; j++) {
			connections<Weight> weights = new connections<Weight>();
			int ws = numofmids;
			for (int iu = 0; iu < ws; iu++) {
				weights.add(new Weight(Double.parseDouble(((Object) outweights
						.get(j - 1).get(iu)).toString())));
			}
			(Outs.get(j)).SetWeights(weights);

		} // Integer numofouts = Integer.parseInt(num.get(2));
		bals.setBalast(balsS);
		E = new Engine(Inputs, Mids, Outs, bals);
		return E;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<String> extracted() throws IOException,
			ClassNotFoundException {
		return (ArrayList<String>) restore.readObject();
	}

	public File openFile() {
		fileChooser.setCurrentDirectory(new File("."));
		int returnVal = fileChooser.showOpenDialog(frame);
		int n = 0;
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return null; // cancelled
		}

		File selectedFile = fileChooser.getSelectedFile();
		if (selectedFile.exists()) {
			setfileexists();
			return selectedFile;// Custom button text
		} else {
			n = JOptionPane.showConfirmDialog(null, "No File found ");

			if (JOptionPane.YES_OPTION == n) {

				/*
				 * currentText = this.loadFile(selectedFile); if(currentText ==
				 * null) { // File file was not a valid File
				 * JOptionPane.showMessageDialog(frame,
				 * "The file was not in a recognized File file format.",
				 * "File Load Error", JOptionPane.ERROR_MESSAGE);
				 * 
				 * }
				 */

				return null;
			} else {
				return null;
			}

		}
	}

	public void setfileexists() {
		fileexists = true;
	}

	public void openreadFile(File sfile) {
		try {
			rfile = new FileReader(sfile);
		} catch (IOException e) {
			System.err.println("Failed to read from " + sfile.getName());
		}
	}

	public void closeFile() {
		try {
			if (rfile != null) {
				rfile.close();
			}
		} catch (IOException e) {
			System.err.println("Failed to close File");
		}
	}

	@SuppressWarnings("unused")
	private void open() {
		File ofile;
		fileChooser.setCurrentDirectory(new File("."));
		ofile = this.openFile();
		if (ofile != null) {
			openreadFile(ofile);
			citem.setEnabled(true);
			// sitem.setEnabled(true);
			submitButton.setEnabled(true);
			clearButton.setEnabled(true);
			oitem.setEnabled(false);
		}
	}

	/**
	 * @return the k
	 */
	public int getK() {
		return k;
	}

	/**
	 * @param k
	 *            the k to set
	 */
	public void setK(int k) {
		this.k = k;
	}

	/**
	 * @return the exitButton
	 */
	public JButton getExitButton() {
		return exitButton;
	}

	/**
	 * @param exitButton
	 *            the exitButton to set
	 */
	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	/**
	 * @return the filenameLabel
	 */
	public JLabel getFilenameLabel() {
		return filenameLabel;
	}

	/**
	 * @param filenameLabel
	 *            the filenameLabel to set
	 */
	public void setFilenameLabel(JLabel filenameLabel) {
		this.filenameLabel = filenameLabel;
	}

	/**
	 * @return the currentFile
	 */
	public File getCurrentFile() {
		return currentFile;
	}

	/**
	 * @param currentFile
	 *            the currentFile to set
	 */
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}

	/**
	 * @return the menu
	 */
	public JMenu getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	/**
	 * @return the sitem
	 */
	public JMenuItem getSitem() {
		return sitem;
	}

	/**
	 * @param sitem
	 *            the sitem to set
	 */
	public void setSitem(JMenuItem sitem) {
		this.sitem = sitem;
	}

	/**
	 * @return the eitem
	 */
	public JMenuItem getEitem() {
		return eitem;
	}

	/**
	 * @param eitem
	 *            the eitem to set
	 */
	public void setEitem(JMenuItem eitem) {
		this.eitem = eitem;
	}

	/**
	 * @return the inputnums
	 */
	public Integer getInputnums() {
		return inputnums;
	}

	/**
	 * @param inputnums
	 *            the inputnums to set
	 */
	public void setInputnums(Integer inputnums) {
		this.inputnums = inputnums;
	}

	/**
	 * @return the midnums
	 */
	public Integer getMidnums() {
		return midnums;
	}

	/**
	 * @param midnums
	 *            the midnums to set
	 */
	public void setMidnums(Integer midnums) {
		this.midnums = midnums;
	}

	/**
	 * @return the outnums
	 */
	public Integer getOutnums() {
		return outnums;
	}

	/**
	 * @param outnums
	 *            the outnums to set
	 */
	public void setOutnums(Integer outnums) {
		this.outnums = outnums;
	}

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 *            the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * @return the obj2
	 */
	public String getObj2() {
		return obj2;
	}

	/**
	 * @param obj2
	 *            the obj2 to set
	 */
	public void setObj2(String obj2) {
		this.obj2 = obj2;
	}

	/**
	 * @return the ins
	 */
	public int getIns() {
		return ins;
	}

	/**
	 * @param ins
	 *            the ins to set
	 */
	public void setIns(int ins) {
		this.ins = ins;
	}
}
