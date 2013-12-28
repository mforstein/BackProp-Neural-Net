 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.text.DecimalFormat;

//////////////////////////////////////////////////////////////// CirclePanel
public class CirclePanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// =============================================================== fields
	private int _h; // Height/width of component.
	private int _w;
	private int _size = 200;
	private ArrayList<Circle> _circles = new ArrayList<Circle>();
	private ArrayList<Line> _lines = new ArrayList<Line>();
	private ArrayList<Strings> _stringsTitle = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsTSI = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsTSO = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsI = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsM = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsMW = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsO = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsOW = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsB = new ArrayList<Strings>();
	private ArrayList<Strings> _stringsR = new ArrayList<Strings>();
	private double[] xinlinepoint = new double[200];
	private double[] yinlinepoint = new double[200];
	private double[] xmidlinepoint = new double[200];
	private double[] ymidelinepoint = new double[200];
	private double[][] xweightmidlinepoint = new double[200][200];
	private double[][] yweihgtmidlinepoint = new double[200][200];
	private double[][] xweightoutlinepoint = new double[200][200];
	private double[][] yweightoutlinepoint = new double[200][200];
	private double balastx = 0;
	private double balasty = 0;
	private TreeMap<Integer, Integer> InNodemap = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> MidNodemap = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> OutNodemap = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> MidWeightmap = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> OutWeightmap = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, InputNode> tInNmap = new TreeMap<Integer, InputNode>();
	private TreeMap<Integer, MidNode> tMNmap = new TreeMap<Integer, MidNode>();
	private TreeMap<Integer, OutNode> tONmap = new TreeMap<Integer, OutNode>();
	private BalastNode tbal = new BalastNode(0.5);
	private TestCase ttc;
	private Point balastpoint = new Point();
	private int ok = 0;
	private int mk = 0;
	private TreeMap<Integer, Double> tResults;
	public JPanel buttonpanel;
	public JButton Startiterations;
	public JButton Stopiterations;
	public JButton stepthrough;
	public JButton SetWeights;
	public JButton addInputNode;
	public JButton addMidNode;
	public JButton addOutputNode;
	public JButton addBalast;
	public JButton useengine;
	public JButton clearE;
	public Integer i = 0;
	public Integer j = 0;
	public Integer k = 0;
	public Integer l = 0;
	private boolean train = false;
	private boolean stepthroughp = false;
	private int t = 0;
	public int q = 0;
	public boolean paintInProgress = false;

	// ========================================================== constructor
	public CirclePanel(int h, int w) {
		_h = h;
		_w = w;
		setPreferredSize(new Dimension(_w, _h));

	}

	// ======================================================== paintComponent
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (paintInProgress)
			return;
		paintInProgress = true;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON); // ... Draw background.
		// ... Draw background.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, _h, _w);
		try {
			// ... Draw each of the circles.
			for (Circle c : _circles) {
				c.draw(g);
			}

			for (Line ls : _lines) {
				ls.draw(g);
			}
			for (Strings SsTI : _stringsTSI) {
				SsTI.drawString(g);
			}
			for (Strings SsTO : _stringsTSO) {
				SsTO.drawString(g);
			}
			for (Strings SsI : _stringsI) {
				SsI.drawString(g);
			}
			for (Strings SsM : _stringsM) {
				SsM.drawString(g);
			}
			for (Strings SsMW : _stringsMW) {
				SsMW.drawString(g);
			}
			for (Strings SsO : _stringsO) {
				SsO.drawString(g);
			}
			for (Strings SsOW : _stringsOW) {
				SsOW.drawString(g);
			}
			for (Strings SsB : _stringsB) {
				SsB.drawString(g);
			}
			for (Strings SsR : _stringsR) {
				SsR.drawString(g);
			}
			for (Strings STIT : _stringsTitle) {
				STIT.drawString(g);
			}
		} catch (java.util.ConcurrentModificationException e) {
		}// ignore...

		paintInProgress = false;

	}

	public void makeButtons() {

		Startiterations = new JButton("Train");
		Stopiterations = new JButton("Stop");
		addInputNode = new JButton("Add Input Node");
		addMidNode = new JButton("Add Mid Node");
		addOutputNode = new JButton("Add Out Node");
		addBalast = new JButton("Add Balast Node");
		stepthrough = new JButton("Step Through");
		SetWeights = new JButton("Set Error");
		useengine = new JButton("Use Engine");
		clearE = new JButton("Clear");

		Startiterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				train();
			}
		});

		Stopiterations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopI();
			}
		});

		addInputNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addInnode();
			}


			
		});
	

		
		addMidNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMiNode();
			}
		});

		addOutputNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addOuNode();
			}
		});

		addBalast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBaNode();
			}
		});
		stepthrough.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stepthrough();
			}
		});

		SetWeights.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setE();
			}
		});

		useengine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drive();
			}
		});
		clearE.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		// this.add(buttonpanel,BorderLayout.WEST);
		// JButton submitButton = new JButton("Submit");
		// buttonpanel.setBorder(
		// BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		// ButtonPanel.add(submitButton);

		// panel.add(ButtonPanel,BorderLayout.SOUTH);
		// submitButton.setEnabled(true);

	}

	// ====================================================== mouse listeners
	// ... Ignore all mouse events except mouseClicked.
	int hp = _w / 2;
	int hn = _w / 2;
	int mp = _w / 2 + 40;
	int mn = _w / 2 + 40;
	int lp = _w / 2;
	int ln = _w / 2;
	int bp = 30;
	int bn = _w - 40;
	int lmp = _w / 2 + 40;
	int lmn = _w / 2 + 40;
	int llp = _w / 2;
	int lln = _w / 2;
	int hph = _w / 2;
	int hnh = _w / 2;
	int lph = _w / 2;
	int lnh = _w / 2;
	int lpt = _w / 2;
	int lnt = _w / 2;
	private Integer key;
	private Integer key2;
	private Integer key3;
	private int j2;
	private DecimalFormat newFormat;
	private Integer key4;
	private String inp;
	private String mid;
	private String bal;
	private Integer key5;
	private Integer key6;

	public void delInode(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */
		i = in - 1;
		if (in == 1) {
			hp = _w / 2;
			hn = _w / 2;
			// ListIterator<Circle> iter =
			// _circles.listIterator(_circles.size());
			// iter.hasPrevious();
			_circles.remove(in - 1);

			// _circles.remove(in); //_w/2width and 117

			InNodemap.remove(hn);

			// ListIterator<Strings> iter3=
			// _stringsI.listIterator(_stringsI.size());
			// iter3.hasPrevious();
			_stringsI.remove(in - 1);

			// _stringsI.remove(in); //_w/2width and 117

		} else {
			if (in % 2 == 1) {
				// ListIterator<Circle> iter =
				// _circles.listIterator(_circles.size());
				// iter.hasPrevious();
				_circles.remove(in-1);

				// _circles.remove(in); //_w/2width and 117

				InNodemap.remove(hn);

				// ListIterator<Strings> iter3=
				// _stringsI.listIterator(_stringsI.size());
				// iter3.hasPrevious();
				_stringsI.remove(in - 1);

				// _circles.remove(in); //_w/2width and 117
				// InNodemap.remove(hn);

				// _stringsI.remove(in); //_w/2width and 117
				// hn = hn+90;
			} else {
				// ListIterator<Circle> iter =
				// _circles.listIterator(_circles.size());
				// iter.hasPrevious();
				_circles.remove(in-1);

				// _circles.remove(in); //_w/2width and 117

				InNodemap.remove(hn);

				// ListIterator<Strings> iter3=
				// _stringsI.listIterator(_stringsI.size());
				// iter3.hasPrevious();
				_stringsI.remove(in - 1);
				// _circles.remove(in); //_w/2width and 117
				// InNodemap.remove(hn);

				// _stringsI.remove(in); //_w/2width and 117
				// hp = hp-90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		for (Iterator<Integer> iterator = InNodemap.keySet().iterator(); iterator
				.hasNext();) {
			setKey(iterator.next());
		}
		rewire(i, j, k, l);

		// ... Cause screen to be repainted.
		repaint();

	}

	public void delMnode(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */
		if (in == 1) {
			mp = _w / 2 + 40;
			mn = _w / 2 + 40;
			_circles.remove(in - 1);
			_stringsM.remove(in - 1);
			MidNodemap.remove(mn);
		} else {
			if (in % 2 == 0) {

				_circles.remove(in - 1);
				_stringsM.remove(in - 1);
				MidNodemap.remove(mn);
				mn = mn + 90;
			} else {
				_circles.remove(in - 1);
				_stringsM.remove(in - 1);
				MidNodemap.remove(mp);
				mp = mp - 90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		j = in - 1;

		for (Iterator<Integer> iterator = MidNodemap.keySet().iterator(); iterator
				.hasNext();) {
			setKey2(iterator.next());
		}
		rewire(i, j, k, l);
		// ... Cause screen to be repainted.
		repaint();

	}

	public void submit(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */
		if (in == 1) {
			hp = _w / 2;
			hn = _w / 2;
			_circles.add(new Circle((hn), _h / 6, 40, Color.BLUE)); // _w/2width
																	// and 117
			InNodemap.put(hn, (_h / 6) + 40);

			_stringsI.add(new Strings((Double.toString((Double) tInNmap.get(in)
					.GetInode(in - 1))), hn - 20, _h / 6, Color.BLUE)); // _w/2width
																		// and
																		// 117

		} else {
			if (in % 2 == 1) {

				_circles.add(new Circle(((hn - ((90)))), _h / 6, 40, Color.BLUE)); // 1
																					// 230width
																					// 117,
																					// 2
																					// 140width
																					// 117,
																					// 3
																					// 50width
																					// 117
				InNodemap.put(hn - 90, (_h / 6) + 40);

				_stringsI.add(new Strings((Double.toString((Double) tInNmap
						.get(in).GetInode(in - 1))), hn - 110, _h / 6,
						Color.BLUE)); // _w/2width and 117

				hn = hn - 90;
			} else {
				_circles.add(new Circle(((hp + ((90)))), _h / 6, 40, Color.BLUE)); // 1
																					// 410width
																					// 117,
																					// 2
																					// 500width
																					// 117,
																					// 3
																					// 590width
																					// 117
				InNodemap.put(hp + 90, (_h / 6) + 40);

				_stringsI.add(new Strings((Double.toString((Double) tInNmap
						.get(in).GetInode(in - 1))), hp + 70, _h / 6,
						Color.BLUE)); // _w/2width and 117

				hp = hp + 90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		for (Iterator<Integer> iterator = InNodemap.keySet().iterator(); iterator
				.hasNext();) {
			setKey3(iterator.next());
		}
		rewire(i, j, k, l);

		// ... Cause screen to be repainted.
		repaint();

	}

	private void train() {

		// check to see if the input and output data is loaded
		// if not display a dialog complaining and exit method
		// create forward and backward object if not aready created
		// start training loop.
		// feed data via a loop input forward object and capture output in map
		// feed new output data map and output data into backward object
		// check to see if stop button is pressed. if so leave training method
		setTrain(true);
		t = 1;
		repaint();
	}

	public void buildTitles() {
		int i = tInNmap.size();
		setJ2(tMNmap.size());
		int k = tONmap.size();
		int q = (k + 1) / 2;
		if (i >= k) {
			if (i % 2 == 1) {
				q = (i + 1) / 2;
			} else {
				q = (i) / 2;
			}
		} else {
			if (k % 2 == 1) {
				q = (k + 1) / 2;
			} else {
				q = (k) / 2;
			}
		}

		// _stringsTitle.add(new Strings("Neural Net Model",15,50,Color.BLUE));
		_stringsTitle.add(new Strings("Test Case Output",
				((_w / 2 - (90 + q * 90))), (3 * _h / 6 + 100), Color.RED));
		_stringsTitle.add(new Strings("Test Case Input",
				((_w / 2 - (90 + q * 90))), (_h / 6 - 90), Color.RED));
		_stringsTitle.add(new Strings("Test Results",
				((_w / 2 - (90 + q * 90))), (3 * _h / 6 + 80), Color.RED));
	}

	public void buildInputTCString(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */

		repaint();

		if (in == 1) {
			hph = _w / 2;
			hnh = _w / 2;

			_stringsTSI.add(new Strings(
					(Double.toString(ttc.getInput(in - 1))), hnh - 20,
					_h / 6 - 90, Color.BLUE)); // _w/2width and 117

		} else {
			if (in % 2 == 1) {

				_stringsTSI
						.add(new Strings(
								(Double.toString(ttc.getInput(in - 1))),
								hnh - 110, _h / 6 - 90, Color.BLUE)); // _w/2width
																		// and
																		// 117

				hnh = hnh - 90;
			} else {

				_stringsTSI
						.add(new Strings(
								(Double.toString(ttc.getInput(in - 1))),
								hph + 70, _h / 6 - 90, Color.BLUE)); // _w/2width
																		// and
																		// 117

				hph = hph + 90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);
		repaint();
	}

	public void buildOutputTCString(Integer in) {

		repaint();
		if (in == 1) {
			lph = _w / 2;
			lnh = _w / 2;
			_stringsTSO.add(new Strings(
					(Double.toString(ttc.getOutput(in - 1))), lnh - 20,
					3 * _h / 6 + 100, Color.RED));

		} else {
			if (in % 2 == 1) {

				_stringsTSO.add(new Strings((Double.toString(ttc
						.getOutput(in - 1))), ((lnh - ((110)))),
						3 * _h / 6 + 100, Color.RED));

				lnh = lnh - 90;
			} else {

				_stringsTSO.add(new Strings((Double.toString(ttc
						.getOutput(in - 1))), ((lph + ((70)))),
						(3 * _h / 6 + 100), Color.RED));

				lph = lph + 90;
			}
		}

		// ... Cause screen to be repainted.
		repaint();
	}

	public void buildResultsString(Integer in) {
		setNewFormat(new DecimalFormat("#.#####"));
		repaint();
		if (in == 1) {
			lpt = _w / 2;
			lnt = _w / 2;
			_stringsR.add(new Strings((Double.toString(tResults.get(in))),
					lnt - 20, 3 * _h / 6 + 80, Color.RED));

		} else {
			if (in % 2 == 1) {

				_stringsR.add(new Strings((Double.toString(tResults.get(in))),
						((lnt - ((110)))), 3 * _h / 6 + 80, Color.RED));

				lnt = lnt - 90;
			} else {

				_stringsR.add(new Strings((Double.toString(tResults.get(in))),
						((lpt + ((70)))), (3 * _h / 6 + 80), Color.RED));

				lpt = lpt + 90;
			}
		}

		// ... Cause screen to be repainted.
		repaint();
	}

	private void stopI() {
		setTrain(false);
		setStepthroughp(false);
	}

	public void clear() {
		_circles.clear();
		InNodemap.clear();
		MidNodemap.clear();
		OutNodemap.clear();
		MidWeightmap.clear();
		OutWeightmap.clear();
		_lines.clear();
		repaint();
		_stringsTitle.clear();
		_stringsTSI.clear();
		_stringsTSO.clear();
		_stringsI.clear();
		_stringsM.clear();
		_stringsMW.clear();
		_stringsO.clear();
		_stringsOW.clear();
		_stringsB.clear();
		_stringsR.clear();
		i = 0;
		j = 0;
		k = 0;
		l = 0;
		rewire(i, j, k, l);
		repaint();
	}

	public void redrawstrings() {
		Double rd;
		Double fd;
		DecimalFormat newFormat = new DecimalFormat("#.#####");
		if (t == 1) {
			_stringsTitle.clear();
			buildTitles();
			_stringsTSI.clear();
			for (int ipx = 1; ipx < ttc.getIsize() + 1; ipx++) {
				buildInputTCString(ipx);
			}
			_stringsTSO.clear();
			for (int ipx = 1; ipx < ttc.getOsize() + 1; ipx++) {
				buildOutputTCString(ipx);
			}
			_stringsR.clear();
			for (int ipx = 1; ipx < tResults.size() + 1; ipx++) {
				buildResultsString(ipx);
			}
			t++;
		}
		if (t > 1) {
			for (int ipx = 1; ipx < ttc.getIsize() + 1; ipx++) {
				rd = ((ttc.getInput(ipx - 1)));
				fd = Double.valueOf(newFormat.format(rd));
				_stringsTSI.get(ipx - 1).reString(Double.toString(fd));
			}
			for (int ipx = 1; ipx < ttc.getOsize() + 1; ipx++) {
				rd = ((ttc.getOutput(ipx - 1)));
				fd = Double.valueOf(newFormat.format(rd));
				_stringsTSO.get(ipx - 1).reString(Double.toString(fd));
			}
			for (int ipx = 1; ipx < tResults.size() + 1; ipx++) {
				rd = ((tResults.get(ipx)));
				fd = Double.valueOf(newFormat.format(rd));
				_stringsR.get(ipx - 1).reString(Double.toString(fd));
			}
		}
		for (int ipx = 1; ipx < tInNmap.size() + 1; ipx++) {
			rd = ((Double) (tInNmap.get(ipx)).GetInode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsI.get(ipx - 1).reString(Double.toString(fd));
		}
		for (int ipx = 1; ipx < tMNmap.size() + 1; ipx++) {
			rd = ((Double) (tMNmap.get(ipx)).GetMnode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsM.get(ipx - 1).reString(Double.toString(fd));
		}
		for (int ipx = 1; ipx < tONmap.size() + 1; ipx++) {
			rd = ((Double) (tONmap.get(ipx)).GetOnode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsO.get(ipx - 1).reString(Double.toString(fd));
		}

		rd = tbal.getBalast();
		fd = Double.valueOf(newFormat.format(rd));
		_stringsB.get(0).reString(Double.toString(fd));

		repaint();

	}

	public void redrawnet(TreeMap<Integer, InputNode> tInNmap,
			TreeMap<Integer, MidNode> tMNmap, TreeMap<Integer, OutNode> tONmap,
			BalastNode tbal) {
		Double rd;
		Double fd;
		DecimalFormat newFormat = new DecimalFormat("#.#####");
		clear();
		for (int ipx = 1; ipx < tInNmap.size() + 1; ipx++) {
			submit(ipx);

		}
		for (int ipx = 1; ipx < tMNmap.size() + 1; ipx++) {
			addMnode(ipx);
		}
		for (int ipx = 1; ipx < tONmap.size() + 1; ipx++) {
			addOnode(ipx);

		}
		addBnode(1);
		i = tInNmap.size();
		j = tMNmap.size();
		k = tONmap.size();
		l = 1;
		q = 0;
		if (i % 2 == 1) {
			if (i >= k) {
				q = (i + 1) / 2;
			} else {
				q = (k + 1) / 2;
			}
		}
		if (k % 2 == 1) {
			if (i >= k) {
				q = (i + 1) / 2;
			} else {
				q = (k + 1) / 2;
			}
		}
		rewire(i, j, k, l);
		for (int ipx = 1; ipx < tInNmap.size() + 1; ipx++) {
			rd = ((Double) (tInNmap.get(ipx)).GetInode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsI.get(ipx - 1).reString(Double.toString(fd));
		}
		for (int ipx = 1; ipx < tMNmap.size() + 1; ipx++) {
			rd = ((Double) (tMNmap.get(ipx)).GetMnode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsM.get(ipx - 1).reString(Double.toString(fd));
		}
		for (int ipx = 1; ipx < tONmap.size() + 1; ipx++) {
			rd = ((Double) (tONmap.get(ipx)).GetOnode(ipx - 1));
			fd = Double.valueOf(newFormat.format(rd));
			_stringsO.get(ipx - 1).reString(Double.toString(fd));
		}

		rd = tbal.getBalast();
		fd = Double.valueOf(newFormat.format(rd));
		_stringsB.get(0).reString(Double.toString(fd));

		repaint();

	}

	private void addMnode(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */
		if (in == 1) {
			mp = _w / 2 + 40;
			mn = _w / 2 + 40;
			_circles.add(new Circle((mn), 2 * _h / 6, 40, Color.GREEN)); // _w/2
																			// +40
																			// 234
			_stringsM.add(new Strings((Double.toString((Double) tMNmap.get(in)
					.GetMnode(in - 1))), mn - 20, 2 * _h / 6, Color.GREEN));
			MidNodemap.put(mn, 2 * _h / 6 + 40);
		} else {
			if (in % 2 == 0) {

				_circles.add(new Circle(((mn - ((90)))), 2 * _h / 6, 40,
						Color.GREEN)); // 1 270 234 2 180 234 3 90 234
				_stringsM.add(new Strings((Double.toString((Double) tMNmap.get(
						in).GetMnode(in - 1))), ((mn - ((110)))), 2 * _h / 6,
						Color.GREEN));

				MidNodemap.put(mn - 90, 2 * _h / 6 + 40);
				mn = mn - 90;
			} else {
				_circles.add(new Circle(((mp + ((90)))), 2 * _h / 6, 40,
						Color.GREEN)); // 1 450 234 2 540 234 3 630 234 4 720
										// 234
				_stringsM.add(new Strings((Double.toString((Double) tMNmap.get(
						in).GetMnode(in - 1))), ((mp + ((70)))), (2 * _h / 6),
						Color.GREEN));

				MidNodemap.put(mp + 90, 2 * _h / 6 + 40);
				mp = mp + 90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		for (Iterator<Integer> iterator = MidNodemap.keySet().iterator(); iterator
				.hasNext();) {
			setKey4(iterator.next());
		}
		rewire(i, j, k, l);
		// ... Cause screen to be repainted.
		repaint();

	}

	private void addOnode(Integer in) {
		/*
		 * ... Generate random color and size circle at mouse click point. Color
		 * randColor = new Color((int)(256*Math.random()),
		 * (int)(256*Math.random()), (int)(256*Math.random())); int randRadius =
		 * (int) (_size/4 * Math.random());
		 */
		if (in == 1) {
			lp = _w / 2;
			ln = _w / 2;
			_circles.add(new Circle((ln), 3 * _h / 6, 40, Color.RED)); // _w/2
																		// 351
			_stringsO.add(new Strings((Double.toString((Double) tONmap.get(in)
					.GetOnode(in - 1))), ln - 20, 3 * _h / 6, Color.RED));

		} else {
			if (in % 2 == 1) {

				_circles.add(new Circle(((ln - ((90)))), 3 * _h / 6, 40,
						Color.RED));
				_stringsO.add(new Strings((Double.toString((Double) tONmap.get(
						in).GetOnode(in - 1))), ((ln - ((110)))), 3 * _h / 6,
						Color.RED));

				ln = ln - 90;
			} else {
				_circles.add(new Circle(((lp + ((90)))), 3 * _h / 6, 40,
						Color.RED));
				_stringsO.add(new Strings((Double.toString((Double) tONmap.get(
						in).GetOnode(in - 1))), ((lp + ((70)))), (3 * _h / 6),
						Color.RED));

				lp = lp + 90;
			}
		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		rewire(i, j, k, l);
		// ... Cause screen to be repainted.
		repaint();
	}

	private void addBnode(Integer in) {

		if (in == 1) {
			bp = 30;
			bn = _w - 40;
			_circles.add(new Circle((bn), _h / 12, 40, Color.BLACK));
			_stringsB.add(new Strings((Double.toString(tbal.getBalast())),
					bn - 20, _h / 12, Color.BLACK));
			setBalastx(bn);
			setBalasty(_h / 12);
			balastpoint = new Point(bn, _h / 12);
		} else {
			// one Bias node is sufficient

		}
		// ... Save this circle for painting later.
		// _circles.add(c);

		rewire(i, j, k, l);
		// ... Cause screen to be repainted.
		repaint();
	}

	private void stepthrough() {
		repaint();
	}

	private void setE() {
	}

	private void drive() {
		repaint();
	}

	private void rewire(Integer h, Integer o, Integer z, Integer f) {
		setInp("in");
		setMid("mid");
		String out = "out";
		setBal("bal");
		lmp = _w / 2 + 40;
		lmn = _w / 2 + 40;
		llp = _w / 2;
		lln = _w / 2;
		// if any row in isolation no wiring needed.
		// if any middle row or output node and a balast wire
		// if any input and middle wire
		// if any middle and output node wire.
		// no wires needed between balast and input nodes.
		// for every input node and balast, add a wieght circle to every mid
		// node.
		// for every mid node an balast nodes add a wieght circle to every
		// output node.
		// connect wires from each input node to each mid node weight.
		// connect wires from the balast node to each mid node and output node
		// weights.
		// connect wires from each mid node to each output node weight.

		if ((i != 0) && (j != 0)) {

			for (int np = 0; np < j; np++) {

				if (l != 0) {
					for (int nw = 1; nw < (i + 2); nw++) {

						drawwieghts("mid", np, nw, i + 1);

					}
				} else {
					for (int nw = 1; nw < (i + 1); nw++) {

						drawwieghts("mid", np, nw, i);

					}
				}
			}

		} // wire between input and middle
		if ((j != 0) && (k != 0)) {
			for (int q = 0; q < k; q++) {

				if (l != 0) {
					for (int nw = 1; nw < (j + 2); nw++) {

						drawwieghts(out, q, nw, j + 1);

					}
				} else {
					for (int nw = 1; nw < (j + 1); nw++) {

						drawwieghts(out, q, nw, j);
					}
				}
			}

		} // wire between middle and output

		repaint();
	}

	private void drawwieghts(String level, Integer nodenum, Integer w,
			Integer numberofwieghts) {

		int in = nodenum;
		if (level.equals("mid")) {

			if (in == 0) {

				if (w == 1) {
					_circles.add(new Circle((lmn), (2 * _h / 6) - 45, 5,
							Color.orange)); // _w/2 +40 234

					MidWeightmap.put(lmn, 2 * _h / 6 - 50);

					repaint();

				} else {
					if (w % 2 == 0) {

						_circles.add(new Circle((int) (lmn - (45 * Math
								.sin((6) * ((w) / 2)))),
								(int) ((2 * _h / 6) - (45 * Math
										.cos((6 * ((w) / 2))))), 5,
								Color.orange)); // _w/2 +40 234

						MidWeightmap.put((int) (lmn - (45 * Math
								.sin((6) * ((w) / 2)))),
								(int) ((2 * _h / 6) - (50 * Math
										.cos((6 * ((w) / 2))))));

						repaint();
					} else {

						_circles.add(new Circle((int) (lmn + (45 * Math
								.sin((6) * ((w - 1) / 2)))),
								(int) ((2 * _h / 6) - (45 * Math
										.cos(6 * ((w - 1) / 2)))), 5,
								Color.orange)); // _w/2 +40 234

						MidWeightmap.put((int) (lmn + (45 * Math
								.sin((6) * ((w - 1) / 2)))),
								(int) ((2 * _h / 6) - (50 * Math
										.cos(6 * ((w - 1) / 2)))));

						repaint();

					}
				}

			} else {
				if (in % 2 == 0) {

					if (w == 1) {
						lmn = lmn + 90;

						_circles.add(new Circle((lmn), (2 * _h / 6) - 45, 5,
								Color.orange)); // _w/2 +40 234

						MidWeightmap.put((lmn), 2 * _h / 6 - 50);

						repaint();

					} else {
						if (w % 2 == 0) {

							_circles.add(new Circle((int) (lmn - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((2 * _h / 6) - (45 * Math
											.cos((6 * ((w) / 2))))), 5,
									Color.orange)); // _w/2 +40 234

							MidWeightmap.put((int) (lmn - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((2 * _h / 6) - (50 * Math
											.cos((6 * ((w) / 2))))));

							repaint();

						} else {

							_circles.add(new Circle((int) (lmn + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((2 * _h / 6) - (45 * Math
											.cos(6 * ((w - 1) / 2)))), 5,
									Color.orange)); // _w/2 +40 234

							MidWeightmap.put((int) (lmn + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((2 * _h / 6) - (50 * Math
											.cos(6 * ((w - 1) / 2)))));

							repaint();

						}
					}
				} else {

					if (w == 1) {

						lmp = lmp - 90;
						_circles.add(new Circle((lmp), (2 * _h / 6) - 45, 5,
								Color.orange)); // _w/2 +40 234

						MidWeightmap.put(lmp, (2 * _h / 6) - 50);

						repaint();

					} else {
						if (w % 2 == 0) {

							_circles.add(new Circle((int) (lmp - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((2 * _h / 6) - (45 * Math
											.cos((6 * ((w) / 2))))), 5,
									Color.orange)); // _w/2 +40 234

							MidWeightmap.put((int) (lmp - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((2 * _h / 6) - (50 * Math
											.cos((6 * ((w) / 2))))));

							repaint();

						} else {

							_circles.add(new Circle((int) (lmp + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((2 * _h / 6) - (45 * Math
											.cos(6 * ((w - 1) / 2)))), 5,
									Color.orange)); // _w/2 +40 234

							MidWeightmap.put((int) (lmp + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((2 * _h / 6) - (50 * Math
											.cos(6 * ((w - 1) / 2)))));

							repaint();

						}
					}
				}
			}

			for (Iterator<Integer> iterator = MidWeightmap.keySet().iterator(); iterator
					.hasNext();) {
				setKey5(iterator.next());
			}

		} else if (level.equals("out")) {

			if (in == 0) {

				if (w == 1) {
					_circles.add(new Circle((llp), (3 * _h / 6) - 45, 5,
							Color.orange)); // _w/2 +40 234

					OutWeightmap.put(llp, 3 * _h / 6 - 50);

					repaint();

				} else {
					if (w % 2 == 0) {

						_circles.add(new Circle((int) (llp - (45 * Math
								.sin((6) * ((w) / 2)))),
								(int) ((3 * _h / 6) - (45 * Math
										.cos((6 * ((w) / 2))))), 5,
								Color.orange)); // _w/2 +40 234

						OutWeightmap.put((int) (llp - (45 * Math
								.sin((6) * ((w) / 2)))),
								(int) ((3 * _h / 6) - (50 * Math
										.cos((6 * ((w) / 2))))));

						repaint();

					} else {

						_circles.add(new Circle((int) (llp + (45 * Math
								.sin((6) * ((w - 1) / 2)))),
								(int) ((3 * _h / 6) - (45 * Math
										.cos(6 * ((w - 1) / 2)))), 5,
								Color.orange)); // _w/2 +40 234

						OutWeightmap.put((int) (llp + (45 * Math
								.sin((6) * ((w - 1) / 2)))),
								(int) ((3 * _h / 6) - (50 * Math
										.cos(6 * ((w - 1) / 2)))));

						repaint();

					}
				}

			} else {
				if (in % 2 == 0) {

					if (w == 1) {
						llp = llp - 90;

						_circles.add(new Circle((llp), (3 * _h / 6) - 45, 5,
								Color.orange)); // _w/2 +40 234

						OutWeightmap.put(llp, 3 * _h / 6 - 50);

						repaint();

					} else {
						if (w % 2 == 0) {

							_circles.add(new Circle((int) (llp - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((3 * _h / 6) - (45 * Math
											.cos((6 * ((w) / 2))))), 5,
									Color.orange)); // _w/2 +40 234

							OutWeightmap.put((int) (llp - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((3 * _h / 6) - (50 * Math
											.cos((6 * ((w) / 2))))));

							repaint();

						} else {

							_circles.add(new Circle((int) (llp + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((3 * _h / 6) - (45 * Math
											.cos(6 * ((w - 1) / 2)))), 5,
									Color.orange)); // _w/2 +40 234

							OutWeightmap.put((int) (llp + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((3 * _h / 6) - (50 * Math
											.cos(6 * ((w - 1) / 2)))));

							repaint();

						}
					}
				} else {

					if (w == 1) {

						lln = lln + 90;
						_circles.add(new Circle((lln), (3 * _h / 6) - 45, 5,
								Color.orange)); // _w/2 +40 234

						OutWeightmap.put(lln, 3 * _h / 6 - 50);

						repaint();

					} else {
						if (w % 2 == 0) {

							_circles.add(new Circle((int) (lln - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((3 * _h / 6) - (45 * Math
											.cos((6 * ((w) / 2))))), 5,
									Color.orange)); // _w/2 +40 234

							OutWeightmap.put((int) (lln - (45 * Math
									.sin((6) * ((w) / 2)))),
									(int) ((3 * _h / 6) - (50 * Math
											.cos((6 * ((w) / 2))))));

							repaint();

						} else {

							_circles.add(new Circle((int) (lln + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((3 * _h / 6) - (45 * Math
											.cos(6 * ((w - 1) / 2)))), 5,
									Color.orange)); // _w/2 +40 234

							OutWeightmap.put((int) (lln + (45 * Math
									.sin((6) * ((w - 1) / 2)))),
									(int) ((3 * _h / 6) - (50 * Math
											.cos(6 * ((w - 1) / 2)))));

							repaint();

						}
					}
				}
			}

			for (Iterator<Integer> iterator = OutWeightmap.keySet().iterator(); iterator
					.hasNext();) {
				setKey6(iterator.next());
			}

		}
		repaint();
		drawlines(i, j, k, l);
	}

	private void drawlines(Integer y, Integer u, Integer b, Integer a) {
		_lines.clear();
		/*
		 * private TreeMap<Integer, Integer> SortedInNodemap = new
		 * TreeMap<Integer, Integer>(); private TreeMap<Integer, Integer>
		 * SortedMidNodemap = new TreeMap<Integer, Integer>(); private
		 * TreeMap<Integer, Integer> SortedOutNodemap = new TreeMap<Integer,
		 * Integer>(); private TreeMap<Integer, Integer > SortedMidWeightmap =
		 * new TreeMap<Integer,Integer>(); private TreeMap<Integer, Integer>
		 * SortedOutWeightmap = new TreeMap<Integer, Integer>(); private Point
		 * balastpoint = new Point();
		 */
		ok = 0;
		mk = 0;

		if ((i != 0) && (j != 0) && (l == 0)) {
			// find the smallest x location in the input row and connect it to
			// the x location weight for each middle nodes.
			// if a balast exists connect a line from the balast point to the
			// right most weight for each middle node.

			for (Integer okey : InNodemap.keySet()) {
				ok++;

				for (Integer key : MidWeightmap.keySet()) {
					mk++;

					if ((mk % InNodemap.size() == ok)
							|| ((ok == InNodemap.size()) && (mk
									% InNodemap.size() == 0))) {

						drawline(okey, InNodemap.get(okey), key,
								MidWeightmap.get(key));
					}
				}
				mk = 0;
			}
			ok = 0;
		}

		ok = 0;
		mk = 0;

		if ((i != 0) && (j != 0) && (l != 0)) {

			for (Integer okey : InNodemap.keySet()) {
				ok++;

				for (Integer key : MidWeightmap.keySet()) {
					mk++;

					if (mk % (InNodemap.size() + 1) != 0) {
						if ((mk % (InNodemap.size() + 1) == ok)) {

							drawline(okey, InNodemap.get(okey), key,
									MidWeightmap.get(key));
						}

					} else {

						if (mk % (InNodemap.size() + 1) == 0) {

							drawline((int) balastpoint.getX(),
									(int) balastpoint.getY() + 40, key,
									MidWeightmap.get(key));

						}
					}
				}
				mk = 0;
			}
			ok = 0;

		}

		if ((j != 0) && (k != 0) && (l == 0)) {
			// find the smallest x location in the input row and connect it to
			// the x location weight for each ouput nodes.
			// if a balast exists connect a line from the balast point to the
			// right most weight for each output node.

			for (Integer okey : MidNodemap.keySet()) {
				ok++;

				for (Integer key : OutWeightmap.keySet()) {
					mk++;

					if ((mk % MidNodemap.size() == ok)
							|| ((ok == MidNodemap.size()) && (mk
									% MidNodemap.size() == 0))) {
						drawline(okey, MidNodemap.get(okey), key,
								OutWeightmap.get(key));
					}
				}
				mk = 0;
			}
			ok = 0;
		}

		if ((j != 0) && (k != 0) && (l != 0)) {

			for (Integer okey : MidNodemap.keySet()) {
				ok++;

				for (Integer key : OutWeightmap.keySet()) {
					mk++;

					if (mk % (MidNodemap.size() + 1) != 0) {
						if ((mk % (MidNodemap.size() + 1) == ok)) {
							drawline(okey, MidNodemap.get(okey), key,
									OutWeightmap.get(key));
						}
					} else {
						if (mk % (MidNodemap.size() + 1) == 0) {
							drawline((int) balastpoint.getX(),
									(int) balastpoint.getY() + 40, key,
									OutWeightmap.get(key));

						}
					}
				}
				mk = 0;
			}
			ok = 0;

		}

	}

	// private TreeMap<Integer, Point> InNodemap = new TreeMap<Integer,
	// Point>();
	// private TreeMap<Integer, Point> MidNodemap = new TreeMap<Integer,
	// Point>();
	// private TreeMap<Integer, Point> OutNodemap = new TreeMap<Integer,
	// Point>();
	// private TreeMap<Integer, TreeMap<Integer, Point>> MidWeightmap = new
	// TreeMap<Integer, TreeMap<Integer, Point>>();
	// private TreeMap<Integer, TreeMap<Integer, Point>> OutWeightmap = new
	// TreeMap<Integer, TreeMap<Integer, Point>>();
	// private TreeMap<Integer,Point> MidWeightmap = new
	// TreeMap<Integer,Point>();
	// private TreeMap<Integer,Point> OutWeightmap= new
	// TreeMap<Integer,Point>();
	// private Point balastpoint = new Point();

	public void drawline(Integer h, Integer o, Integer z, Integer f) {
		_lines.add(new Line(h, o, z, f, Color.black));

	}

	public void setpInNode(TreeMap<Integer, InputNode> tInNmap2) {
		// System.out.println("tInNmap2.size()="+tInNmap2.size());

		tInNmap = tInNmap2;
		// System.out.println("tInNmap2.size()="+tInNmap2.size());
		// for(int ipx =1; ipx<tInNmap2.size()+1;ipx++){
		// System.out.println("node value="+tInNmap.get(ipx).GetInode(ipx));
		// }
	}

	public void setpMNode(TreeMap<Integer, MidNode> tMNmap2) {
		// System.out.println(tMNmap2.toString());
		// System.out.println(tMNmap2.get(1).GetMnode(0));
		tMNmap = tMNmap2;
	}

	public void setpONode(TreeMap<Integer, OutNode> tONmap2) {

		tONmap = tONmap2;
	}

	public void setpBalast(BalastNode bal2) {
		// System.out.println((Double)tbal.getBalast());
		tbal = bal2;
	}

	public void setTestCase(TestCase tc2) {
		// System.out.println((Double)tbal.getBalast());
		ttc = tc2;
	}

	public void setresults(TreeMap<Integer, Double> Results2) {
		// System.out.println((Double)tbal.getBalast());
		tResults = Results2;
	}

	/**
	 * @return the _size
	 */
	public int get_size() {
		return _size;
	}

	/**
	 * @param _size
	 *            the _size to set
	 */
	public void set_size(int _size) {
		this._size = _size;
	}

	/**
	 * @return the xinlinepoint
	 */
	public double[] getXinlinepoint() {
		return xinlinepoint;
	}

	/**
	 * @param xinlinepoint
	 *            the xinlinepoint to set
	 */
	public void setXinlinepoint(double[] xinlinepoint) {
		this.xinlinepoint = xinlinepoint;
	}

	/**
	 * @return the yinlinepoint
	 */
	public double[] getYinlinepoint() {
		return yinlinepoint;
	}

	/**
	 * @param yinlinepoint
	 *            the yinlinepoint to set
	 */
	public void setYinlinepoint(double[] yinlinepoint) {
		this.yinlinepoint = yinlinepoint;
	}

	/**
	 * @return the xmidlinepoint
	 */
	public double[] getXmidlinepoint() {
		return xmidlinepoint;
	}

	/**
	 * @param xmidlinepoint
	 *            the xmidlinepoint to set
	 */
	public void setXmidlinepoint(double[] xmidlinepoint) {
		this.xmidlinepoint = xmidlinepoint;
	}

	/**
	 * @return the ymidelinepoint
	 */
	public double[] getYmidelinepoint() {
		return ymidelinepoint;
	}

	/**
	 * @param ymidelinepoint
	 *            the ymidelinepoint to set
	 */
	public void setYmidelinepoint(double[] ymidelinepoint) {
		this.ymidelinepoint = ymidelinepoint;
	}

	/**
	 * @return the xweightmidlinepoint
	 */
	public double[][] getXweightmidlinepoint() {
		return xweightmidlinepoint;
	}

	/**
	 * @param xweightmidlinepoint
	 *            the xweightmidlinepoint to set
	 */
	public void setXweightmidlinepoint(double[][] xweightmidlinepoint) {
		this.xweightmidlinepoint = xweightmidlinepoint;
	}

	/**
	 * @return the yweihgtmidlinepoint
	 */
	public double[][] getYweihgtmidlinepoint() {
		return yweihgtmidlinepoint;
	}

	/**
	 * @param yweihgtmidlinepoint
	 *            the yweihgtmidlinepoint to set
	 */
	public void setYweihgtmidlinepoint(double[][] yweihgtmidlinepoint) {
		this.yweihgtmidlinepoint = yweihgtmidlinepoint;
	}

	/**
	 * @return the xweightoutlinepoint
	 */
	public double[][] getXweightoutlinepoint() {
		return xweightoutlinepoint;
	}

	/**
	 * @param xweightoutlinepoint
	 *            the xweightoutlinepoint to set
	 */
	public void setXweightoutlinepoint(double[][] xweightoutlinepoint) {
		this.xweightoutlinepoint = xweightoutlinepoint;
	}

	/**
	 * @return the yweightoutlinepoint
	 */
	public double[][] getYweightoutlinepoint() {
		return yweightoutlinepoint;
	}

	/**
	 * @param yweightoutlinepoint
	 *            the yweightoutlinepoint to set
	 */
	public void setYweightoutlinepoint(double[][] yweightoutlinepoint) {
		this.yweightoutlinepoint = yweightoutlinepoint;
	}

	/**
	 * @return the balastx
	 */
	public double getBalastx() {
		return balastx;
	}

	/**
	 * @param balastx
	 *            the balastx to set
	 */
	public void setBalastx(double balastx) {
		this.balastx = balastx;
	}

	/**
	 * @return the balasty
	 */
	public double getBalasty() {
		return balasty;
	}

	/**
	 * @param balasty
	 *            the balasty to set
	 */
	public void setBalasty(double balasty) {
		this.balasty = balasty;
	}

	/**
	 * @return the train
	 */
	public boolean isTrain() {
		return train;
	}

	/**
	 * @param train
	 *            the train to set
	 */
	public void setTrain(boolean train) {
		this.train = train;
	}

	/**
	 * @return the stepthroughp
	 */
	public boolean isStepthroughp() {
		return stepthroughp;
	}

	/**
	 * @param stepthroughp
	 *            the stepthroughp to set
	 */
	public void setStepthroughp(boolean stepthroughp) {
		this.stepthroughp = stepthroughp;
	}

	/**
	 * @return the key
	 */
	public Integer getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Integer key) {
		this.key = key;
	}

	/**
	 * @return the key2
	 */
	public Integer getKey2() {
		return key2;
	}

	/**
	 * @param key2
	 *            the key2 to set
	 */
	public void setKey2(Integer key2) {
		this.key2 = key2;
	}

	/**
	 * @return the key3
	 */
	public Integer getKey3() {
		return key3;
	}

	/**
	 * @param key3
	 *            the key3 to set
	 */
	public void setKey3(Integer key3) {
		this.key3 = key3;
	}

	/**
	 * @return the j2
	 */
	public int getJ2() {
		return j2;
	}

	/**
	 * @param j2
	 *            the j2 to set
	 */
	public void setJ2(int j2) {
		this.j2 = j2;
	}

	/**
	 * @return the newFormat
	 */
	public DecimalFormat getNewFormat() {
		return newFormat;
	}

	/**
	 * @param newFormat
	 *            the newFormat to set
	 */
	public void setNewFormat(DecimalFormat newFormat) {
		this.newFormat = newFormat;
	}

	/**
	 * @return the key4
	 */
	public Integer getKey4() {
		return key4;
	}

	/**
	 * @param key4
	 *            the key4 to set
	 */
	public void setKey4(Integer key4) {
		this.key4 = key4;
	}

	/**
	 * @return the inp
	 */
	public String getInp() {
		return inp;
	}

	/**
	 * @param inp
	 *            the inp to set
	 */
	public void setInp(String inp) {
		this.inp = inp;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param mid
	 *            the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the bal
	 */
	public String getBal() {
		return bal;
	}

	/**
	 * @param bal
	 *            the bal to set
	 */
	public void setBal(String bal) {
		this.bal = bal;
	}

	/**
	 * @return the key5
	 */
	public Integer getKey5() {
		return key5;
	}

	/**
	 * @param key5
	 *            the key5 to set
	 */
	public void setKey5(Integer key5) {
		this.key5 = key5;
	}

	/**
	 * @return the key6
	 */
	public Integer getKey6() {
		return key6;
	}

	/**
	 * @param key6
	 *            the key6 to set
	 */
	public void setKey6(Integer key6) {
		this.key6 = key6;
	}

	void addInnode() {
		
		
			i++;
			submit(i);
			if (i % 2 == 1) {
				if (i >= k) {
					q = (i + 1) / 2;
				} else {
					q = (k + 1) / 2;
				}
			}	
			
		}

	public void addMiNode() {
		
		j++;
		addMnode(j);
	}

	public void addOuNode() {
		
		k++;
		addOnode(k);
		if (k % 2 == 1) {
			if (i >= k) {
				q = (i + 1) / 2;
			} else {
				q = (k + 1) / 2;
			}
		}
	}

	public void addBaNode() {
		l++;
		addBnode(l);
		
	}
}