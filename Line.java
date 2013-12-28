 
import java.awt.*;

///////////////////////////////////////////////////////////////// Line
class Line {
	// =========================================================== fields
	int _x; // x coord of the start point.
	int _y; // y coord of the start point.
	int _x1; // x coord of the end point.
	int _y1; // y coord of the end point.
	Color _color;

	// ====================================================== constructor
	Line(int x, int y, int x1, int y1, Color color) {
		// ... Change user oriented parameters into more useful values.
		_x = x;
		_y = y;
		_x1 = x1;
		_y1 = y1;
		_color = color;
	}

	// ============================================================= draw
	void draw(Graphics g) {
		// ... Should we save and restore the previous color?
		g.setColor(_color);
		g.drawLine(_x, _y, _x1, _y1);
	}
}