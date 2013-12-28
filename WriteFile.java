 
import javax.swing.*;

import java.io.*;
import java.util.Iterator;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * Write a description of class WriteFile here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class WriteFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// instance variables - replace the example below with your own
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
	private FileWriter wfile;
	private int indexOfOpenBracket;
	private int indexOfLastBracket;

	/**
	 * Constructor for objects of class WriteFile
	 */
	public WriteFile() {

	}

	/**
	 * An example of a method - replace this comment with your own
	 * 
	 * @param y
	 *            a sample parameter for a method
	 * @return the sum of x and y
	 */
	@SuppressWarnings("unused")
	private void save() {
		fileChooser.setCurrentDirectory(new File("."));
		int returnVal = fileChooser.showSaveDialog(frame);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return; // cancelled
		}
		File selectedFile = fileChooser.getSelectedFile();
		opensaveFile(selectedFile);

	}

	public void opensaveFile(File sfile) {
		try {
			wfile = new FileWriter(sfile);
		} catch (IOException e) {
			System.err.println("Failed to save to " + sfile.getName());
		}
	}

	public File openFile() {
		fileChooser.setCurrentDirectory(new File("."));
		int returnVal = fileChooser.showOpenDialog(frame);
		int n = 0;
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return null; // cancelled
		}

		File selectedFile = fileChooser.getSelectedFile();
		if (selectedFile.exists()) {// Custom button text

			n = JOptionPane.showConfirmDialog(null,
					"Would you like to Overwrite the file?");

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

				return selectedFile;
			} else {
				return null;
			}
		} else {
			return selectedFile;
		}
	}

	public void writeFile(String line, int i) {
		setIndexOfOpenBracket(line.indexOf("["));
		setIndexOfLastBracket(line.lastIndexOf("]"));
		try {

			if (i == 1) {
				wfile.write(line);
				wfile.write('\n');
			} else {
				wfile.append(line);
				wfile.append('\n');
			}
		} catch (IOException e) {
			System.err.println("Failed to write to File");
		}
	}

	public void writeEFile(File selectedFile, Engine E) {

		try {
			FileOutputStream saveFile = new FileOutputStream(selectedFile);
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			TreeMap<Integer, InputNode> Inputs = E.getInNmaps();
			TreeMap<Integer, MidNode> Mids = E.getMNmaps();
			TreeMap<Integer, OutNode> Outs = E.getONmaps();
			BalastNode bals = E.getbals();
			Integer inputnums = 0;
			Integer midnums = 0;
			Integer outnums = 0;
			ArrayList<Integer> num = new ArrayList<Integer>();
			ArrayList<Double> allinputs = new ArrayList<Double>();
			ArrayList<Double> allmids = new ArrayList<Double>();
			ArrayList<Double> alloutputs = new ArrayList<Double>();
			ArrayList<ArrayList<Double>> midweights = new ArrayList<ArrayList<Double>>();
			ArrayList<ArrayList<Double>> outweights = new ArrayList<ArrayList<Double>>();

			save.writeObject("BeginningFile");
			for (Iterator<?> itr = Inputs.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				inputnums++;
			}
			for (Iterator<?> itr = Mids.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				midnums++;
			}

			for (Iterator<?> itr = Outs.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				outnums++;
			}

			num.add(inputnums);
			num.add(midnums);
			num.add(outnums);
			save.writeObject(num);
			// save.writeObject("StartingFile");
			inputnums = 0;
			midnums = 0;
			outnums = 0;
			for (Iterator<?> itr = Inputs.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				inputnums++;
				// save.writeObject("InputNode_"+inputnums);
				InputNode Itempnode = Inputs.get(inputnums);
				allinputs.add((Double) Itempnode.GetInode(inputnums - 1));

			}
			save.writeObject(allinputs);
			for (Iterator<?> itr = Mids.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				midnums++;
				// save.writeObject("MidNode_"+midnums);

				// save.writeObject(Mids.get(midnums).GetMnode(midnums));
				connections<Weight> w = Mids.get(midnums).GetWeights();
				ArrayList<Double> wieghlist = new ArrayList<Double>();
				int ws = w.size();
				// save.writeObject(ws);
				for (int i = 0; i < ws; i++) {
					{
						wieghlist.add(w.get(i).getWeight());

					}
				}
				allmids.add((Double) Mids.get(midnums).GetMnode(midnums - 1));
				midweights.add(wieghlist);

			} // save.writeObject(i);
			save.writeObject(allmids);
			save.writeObject(midweights);
			// save.writeObject(w.get(i).getWeight());

			for (Iterator<?> itr = Outs.entrySet().iterator(); itr.hasNext();) {
				itr.next();
				outnums++;
				// save.writeObject("MidNode_"+midnums);

				// save.writeObject(Mids.get(midnums).GetMnode(midnums));
				connections<Weight> w = Outs.get(outnums).GetWeights();
				ArrayList<Double> wieghlist = new ArrayList<Double>();
				int ws = w.size();
				// save.writeObject(ws);
				for (int i = 0; i < ws; i++) {
					{
						wieghlist.add(w.get(i).getWeight());

					}
				}
				alloutputs
						.add((Double) Mids.get(outnums).GetMnode(outnums - 1));
				outweights.add(wieghlist);

			}
			// save.writeObject(i);
			save.writeObject(alloutputs);
			save.writeObject(outweights);
			// save.writeObject(w.get(i).getWeight());

			save.writeObject("balast");
			save.writeObject(bals.getBalast());
			save.writeObject("EndingFile");
			save.close();

		} catch (IOException e) {
			System.err.println("Failed to write object to File");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void exit() {
		closeFile();

		System.exit(0);
	}

	public void closeFile() {
		try {
			if (wfile != null) {
				wfile.close();
			}
		} catch (IOException e) {
			System.err.println("Failed to close File");
		}
	}

	/**
	 * @return the submitButton
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}

	/**
	 * @param submitButton
	 *            the submitButton to set
	 */
	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	/**
	 * @return the clearButton
	 */
	public JButton getClearButton() {
		return clearButton;
	}

	/**
	 * @param clearButton
	 *            the clearButton to set
	 */
	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
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
	 * @return the oitem
	 */
	public JMenuItem getOitem() {
		return oitem;
	}

	/**
	 * @param oitem
	 *            the oitem to set
	 */
	public void setOitem(JMenuItem oitem) {
		this.oitem = oitem;
	}

	/**
	 * @return the citem
	 */
	public JMenuItem getCitem() {
		return citem;
	}

	/**
	 * @param citem
	 *            the citem to set
	 */
	public void setCitem(JMenuItem citem) {
		this.citem = citem;
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
	 * @return the indexOfOpenBracket
	 */
	public int getIndexOfOpenBracket() {
		return indexOfOpenBracket;
	}

	/**
	 * @param indexOfOpenBracket
	 *            the indexOfOpenBracket to set
	 */
	public void setIndexOfOpenBracket(int indexOfOpenBracket) {
		this.indexOfOpenBracket = indexOfOpenBracket;
	}

	/**
	 * @return the indexOfLastBracket
	 */
	public int getIndexOfLastBracket() {
		return indexOfLastBracket;
	}

	/**
	 * @param indexOfLastBracket
	 *            the indexOfLastBracket to set
	 */
	public void setIndexOfLastBracket(int indexOfLastBracket) {
		this.indexOfLastBracket = indexOfLastBracket;
	}
}
