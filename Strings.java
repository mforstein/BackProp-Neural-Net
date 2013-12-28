 
import java.awt.*;

/**
 * Write a description of class Strings here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Strings {
	// =========================================================== Strings
	int _x; // x coord of bounding rect upper left corner.
	int _y; // y coord of bounding rect upper left corner.
	Color _color;
	String _text;

	// ====================================================== constructor
	Strings(String text, int x, int y, Color color) {
		// ... Change user oriented parameters into more useful values.
		_x = x;
		_y = y;
		_color = color;
		_text = text;
	}

	public void reString(String text2) {
		_text = text2;
	}

	public void drawString(Graphics g) {

		g.drawString(_text, _x, _y);
	}
}
