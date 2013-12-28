 
import java.awt.*;

///////////////////////////////////////////////////////////////// Circle
public class Circle {
	// =========================================================== fields
	int _x; // x coord of bounding rect upper left corner.
	int _y; // y coord of bounding rect upper left corner.
	int _diameter; // Height and width of bounding rectangle.
	Color _color;

	// ====================================================== constructor
	Circle(int x, int y, int radius, Color color) {
		// ... Change user oriented parameters into more useful values.
		_x = x - radius;
		_y = y - radius;
		_diameter = 2 * radius;
		_color = color;
	}

	// ============================================================= draw
	void draw(Graphics g) {
		// ... Should we save and restore the previous color?
		g.setColor(_color);
		g.drawOval(_x, _y, _diameter, _diameter);
	}
}