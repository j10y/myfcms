package com.htsoft.core.jbpm.jpdl;

import java.awt.Point;
import java.awt.Rectangle;

public class GeometryUtils {
	public static double getSlope(int x1, int y1, int x2, int y2) {
		return (y2 - y1) / (x2 - x1);
	}

	public static double getYIntercep(int x1, int y1, int x2, int y2) {
		return y1 - x1 * getSlope(x1, y1, x2, y2);
	}

	public static Point getRectangleCenter(Rectangle rect) {
		return new Point((int) rect.getCenterX(), (int) rect.getCenterY());
	}

	public static Point getRectangleLineCrossPoint(Rectangle rectangle, Point p1, int grow) {
		Rectangle rect = rectangle.getBounds();
		rect.grow(grow, grow);
		Point p0 = getRectangleCenter(rect);

		if (p1.x == p0.x) {
			if (p1.y < p0.y) {
				return new Point(p0.x, rect.y);
			}
			return new Point(p0.x, rect.y + rect.height);
		}

		if (p1.y == p0.y) {
			if (p1.x < p0.x) {
				return new Point(rect.x, p0.y);
			}
			return new Point(rect.x + rect.width, p0.y);
		}

		double slope = getSlope(p0.x, p0.y, rect.x, rect.y);
		double slopeLine = getSlope(p0.x, p0.y, p1.x, p1.y);
		double yIntercep = getYIntercep(p0.x, p0.y, p1.x, p1.y);

		if (Math.abs(slopeLine) > slope - 0.01D) {
			if (p1.y < rect.y) {
				return new Point((int) Math.round((rect.y - yIntercep) / slopeLine), rect.y);
			}
			return new Point((int) Math.round((rect.y + rect.height - yIntercep) / slopeLine),
					rect.y + rect.height);
		}

		if (p1.x < rect.x) {
			return new Point(rect.x, (int) Math.round(slopeLine * rect.x + yIntercep));
		}
		return new Point(rect.x + rect.width, (int) Math.round(slopeLine + yIntercep));
	}
}
