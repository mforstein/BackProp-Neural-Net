 
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Framework extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String VERSION = "0.0";

	private static JFileChooser fileChooser = new JFileChooser(
			System.getProperty("user.dir"));

	private int cn = 700;
	public P2Panel inputfilenamelabel;
	public P2Panel outputfilenamelabel;
	public JPanel filenamepanel;
	public CircleMain cm;

	private int height;

	private int width;

	private Color col;

	private int x;

	private int y;

	public Framework() {

		this.setTitle("NeuralNet Sample Project");
		setLayout(new BorderLayout());
		filenamepanel = new JPanel();
		inputfilenamelabel = new P2Panel("Training Filename", 20);
		outputfilenamelabel = new P2Panel("Data Filename", 20);
		filenamepanel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		filenamepanel.add(inputfilenamelabel);
		filenamepanel.add(outputfilenamelabel);

		// canvas.setBorder(new EmptyBorder(6,6,6,6));
		cm = new CircleMain();
		this.add(cm, BorderLayout.CENTER);

		makeMenuBar(this);

		setHeights(((getHeight() / 4)) - 45);
		setWidths(getWidth() / 2 - 45);
		setCol(Color.black);
		setXs(90);
		setYs(90);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); // get the
																	// dimensions
																	// of the
																	// screen
		this.setLocation(d.width / 2 - this.getWidth() / 2,
				d.height / 2 - this.getHeight() / 2); // find the center and set
														// the location of the
														// frame.
		this.setSize(1000, 600);
		this.add(filenamepanel, BorderLayout.NORTH);
		this.setFocusable(true); // set the game frame as the focus
		this.setVisible(true); // make the frame visible.
	}

	/*
	 * ---- implementation of menu functions ---- public void makeButtons() {
	 * JPanel buttonpanel = new JPanel(); buttonpanel.setLayout(new
	 * GridLayout(9,1)); Startiterations = new JButton("Train"); Stopiterations
	 * = new JButton("Stop"); addInputNode = new JButton("Add Input Node");
	 * addMidNode = new JButton("Add Mid Node"); addOutputNode = new
	 * JButton("Add Out Node"); addBalast = new JButton("Add Balast Node");
	 * DisplayWeights = new JButton("Display Weights"); SetWeights = new
	 * JButton("Set Weights"); displayresults = new JButton("Display Results");
	 * buttonpanel.setBorder(
	 * BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	 * Startiterations.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { train(); } });
	 * 
	 * Stopiterations.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { stopI(); } });
	 * 
	 * addInputNode.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { addInode(); } });
	 * addMidNode.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { addMnode(); } });
	 * 
	 * addOutputNode.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { addOnode(); } });
	 * 
	 * addBalast.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { addBnode(); } });
	 * DisplayWeights.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { displayW(); } });
	 * 
	 * SetWeights.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { setW(); } });
	 * 
	 * displayresults.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { displayR(); } });
	 * 
	 * buttonpanel.add(Startiterations); buttonpanel.add(Stopiterations);
	 * buttonpanel.add(addInputNode); buttonpanel.add(addMidNode);
	 * buttonpanel.add(addOutputNode); buttonpanel.add(addBalast);
	 * buttonpanel.add(DisplayWeights); buttonpanel.add(SetWeights);
	 * buttonpanel.add(displayresults); this.add(buttonpanel,BorderLayout.WEST);
	 * 
	 * 
	 * 
	 * }
	 */

	private void makeMenuBar(JFrame frame) {
		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit()
				.getMenuShortcutKeyMask();

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JMenu menu;
		JMenuItem item;

		// create the File menu
		menu = new JMenu("File");
		menubar.add(menu);

		item = new JMenuItem("Open Training Data...");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openTFile();
			}
		});
		menu.add(item);
		item = new JMenuItem("Open Results Data...");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_R, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openRFile();
			}
		});
		menu.add(item);
		item = new JMenuItem("Close Data");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		menu.add(item);
		item = new JMenuItem("Close Results Data");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_X, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeRFile();
			}
		});
		menu.add(item);
		//
		// item = new JMenuItem("Save Data As...");
		// item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		// SHORTCUT_MASK));
		// item.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) { saveAs(); }
		// });
		// menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Open Engine...");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_E, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openEngine();
			}
		});
		menu.add(item);

		item = new JMenuItem("Clear Engine");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_Y, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeEngine();
			}
		});
		menu.add(item);

		item = new JMenuItem("Save Engine As...");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_Z, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAsEngine();
			}
		});
		menu.add(item);
		menu.addSeparator();
		item = new JMenuItem("Quit");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		menu.add(item);

		// create the Filter menu
		menu = new JMenu("Edit");
		menubar.add(menu);

		item = new JMenuItem("Remove Input Node...");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_I, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveInputNode();
 			}
		});
		menu.add(item);

		item = new JMenuItem("Remove Middle Node");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_M, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveMidNode();
			}
		});
		menu.add(item);

		item = new JMenuItem("Remove Output Node");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_U, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveOutputNode();
			}
		});
		menu.add(item);

		item = new JMenuItem("Remove Balast");
		item.setAccelerator(KeyStroke
				.getKeyStroke(KeyEvent.VK_B, SHORTCUT_MASK));
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveBalast();
			}
		});
		menu.add(item);
		menu.addSeparator();

		// item = new JMenuItem("ReDraw Neural Map");
		// item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
		// SHORTCUT_MASK));
		// item.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) { Redraw(); }
		// });
		// menu.add(item);
		// create the Help menu
		menu = new JMenu("Help");
		menubar.add(menu);

		item = new JMenuItem("About Predictive Engine...");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
		menu.add(item);

	}

	/**
	 * Open function: open a file chooser to select a new image file, and then
	 * display the chosen image.
	 */
	private void openTFile() {
		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return; // cancelled
		}
		File selectedFile = fileChooser.getSelectedFile();

		if (selectedFile == null) { // input file was not a valid image
			JOptionPane.showMessageDialog(this,
					"The file was not in a recognized file format.",
					"File Load Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.inputfilenamelabel.setTextentry(selectedFile.getPath());
		cm.selectedFile = selectedFile;

	}

	/**
	 * Open function: open a file chooser to select a new image file, and then
	 * display the chosen image.
	 */
	private void openRFile() {
		int returnVal = fileChooser.showOpenDialog(this);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return; // cancelled
		}
		File selectedRFile = fileChooser.getSelectedFile();

		if (selectedRFile == null) { // input file was not a valid image
			JOptionPane.showMessageDialog(this,
					"The file was not in a recognized file format.",
					"File Load Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.outputfilenamelabel.setTextentry(selectedRFile.getPath());
		cm.selectedRFile = selectedRFile;

	}

	/**
	 * Close function: close the current image.
	 */
	private void close() {
		cm.selectedFile = null;
		this.inputfilenamelabel.setTextentry("");
	}

	/**
	 * Save As function: save the current image to a file.
	 */
	@SuppressWarnings("unused")
	private void saveAs() {
		int returnVal = fileChooser.showSaveDialog(this);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return; // cancelled
		}
		File selectedFile = fileChooser.getSelectedFile();
		// FileManager.saveImage(currentImage, selectedFile);

		this.outputfilenamelabel.setTextentry(selectedFile.getPath());
	}

	/**
	 * Quit function: quit the application.
	 */
	private void quit() {
		System.exit(0);
	}

	private void RemoveInputNode() {
		cm.delInode();
	}

	private void RemoveMidNode() {
		cm.delMnode();
	}

	private void RemoveOutputNode() {
		cm.delOnode();
	}

	private void RemoveBalast() {
		cm.delBnode();
	}

	@SuppressWarnings("unused")
	private void Redraw() {
	}

	private void showAbout() {

		JOptionPane.showMessageDialog(new JPanel(), "Back Propagation Engine by Micah Forstein MS.",

				"About", JOptionPane.INFORMATION_MESSAGE);	
	}

	private void closeRFile() {
		cm.selectedRFile = null;
		this.outputfilenamelabel.setTextentry("");
	}

	private void openEngine() {
		// int returnVal = fileChooser.showSaveDialog(this);
		//
		// if(returnVal != JFileChooser.APPROVE_OPTION) {
		// return; // cancelled
		// }
		// File selectedEFile = fileChooser.getSelectedFile();
		// //FileManager.saveImage(currentImage, selectedFile);
		//
		// //this.outputfilenamelabel.setTextentry(selectedFile.getPath());
		// cm.selectedEFile = selectedEFile;
		cm.openEngine();
	}

	private void closeEngine() {
		cm.clear();
	}

	private void saveAsEngine() {
		int returnVal = fileChooser.showSaveDialog(this);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return; // cancelled
		}
		File selectedEFile = fileChooser.getSelectedFile();
		// FileManager.saveImage(currentImage, selectedFile);

		// this.outputfilenamelabel.setTextentry(selectedFile.getPath());
		cm.selectedEFile = selectedEFile;
		cm.saveAsEngine();
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return VERSION;
	}

	/**
	 * @return the cn
	 */
	public int getCn() {
		return cn;
	}

	/**
	 * @param cn
	 *            the cn to set
	 */
	public void setCn(int cn) {
		this.cn = cn;
	}

	/**
	 * @return the height
	 */
	public int getHeights() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeights(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidths() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidths(int width) {
		this.width = width;
	}

	/**
	 * @return the col
	 */
	public Color getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(Color col) {
		this.col = col;
	}

	/**
	 * @return the x
	 */
	public int getXs() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setXs(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getYs() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setYs(int y) {
		this.y = y;
	}

}
