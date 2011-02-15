package com.htsoft.core.jbpm.jpdl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

public class JpdlModelDrawer {
	public static final int RECT_OFFSET_X = -4;
	public static final int RECT_OFFSET_Y = -8;
	public static final int RECT_ROUND = 25;
	public static final int DEFAULT_FONT_SIZE = 12;
	public static final Color DEFAULT_STROKE_COLOR = Color.decode("#03689A");
	public static final Stroke DEFAULT_STROKE = new BasicStroke(2.0F);

	public static final Color DEFAULT_LINE_STROKE_COLOR = Color.decode("#808080");
	public static final Stroke DEFAULT_LINE_STROKE = new BasicStroke(1.0F);

	public static final Color DEFAULT_FILL_COLOR = Color.decode("#F6F7FF");

	private static final Map<String, Object> nodeInfos = JpdlModel.getNodeInfos();

	public BufferedImage draw(JpdlModel jpdlModel) throws IOException {
		Rectangle dimension = getCanvasDimension(jpdlModel);
		BufferedImage bi = new BufferedImage(dimension.width, dimension.height, 2);
		Graphics2D g2 = bi.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, dimension.width, dimension.height);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("宋体", 0, 12);

		g2.setFont(font);
		Map nodes = jpdlModel.getNodes();
		Set activityNames = jpdlModel.getActivityNames();
		drawNode(nodes, activityNames, g2, font);
		drawTransition(nodes, g2);
		return bi;
	}

	public Rectangle getCanvasDimension(JpdlModel jpdlModel) {
		Rectangle rectangle = new Rectangle();
		Rectangle rect = new Rectangle();
		for (Node node : jpdlModel.getNodes().values()) {
			rect = node.getRectangle();
			if (rect.getMaxX() > rectangle.getMaxX()) {
				rectangle.width = (int) Math.round(rect.getMaxX());
			}
			if (rect.getMaxY() > rectangle.getMaxY()) {
				rectangle.height = (int) Math.round(rect.getMaxY());
			}
			for (Transition transition : node.getTransitions()) {
				List<Point> trace = transition.getLineTrace();
				for (Point point : trace) {
					if (rectangle.getMaxX() < point.x) {
						rectangle.width = point.x;
					}
					if (rectangle.getMaxY() < point.y) {
						rectangle.height = point.y;
					}
				}
			}
		}
		rectangle.width += 60;
		rectangle.height += 20;
		return rectangle;
	}

	private void drawTransition(Map<String, Node> nodes, Graphics2D g2) throws IOException {
		g2.setStroke(DEFAULT_LINE_STROKE);
		g2.setColor(DEFAULT_LINE_STROKE_COLOR);
		for (Node node : nodes.values())
			for (Transition transition : node.getTransitions()) {
				String to = transition.getTo();
				Node toNode = (Node) nodes.get(to);
				List trace = transition.getLineTrace();

				int len = trace.size() + 2;
				trace.add(0, new Point(node.getCenterX(), node.getCenterY()));
				trace.add(new Point(toNode.getCenterX(), toNode.getCenterY()));

				int[] xPoints = new int[len];
				int[] yPoints = new int[len];

				for (int i = 0; i < len; ++i) {
					xPoints[i] = ((Point) trace.get(i)).x;
					yPoints[i] = ((Point) trace.get(i)).y;
				}

				int taskGrow = 4;
				int smallGrow = -2;
				int grow = 0;
				if (nodeInfos.get(node.getType()) != null)
					grow = -2;
				else {
					grow = 4;
				}

				Point p = GeometryUtils.getRectangleLineCrossPoint(node.getRectangle(), new Point(
						xPoints[1], yPoints[1]), grow);
				if (p != null) {
					xPoints[0] = p.x;
					yPoints[0] = p.y;
				}
				if (nodeInfos.get(toNode.getType()) != null)
					grow = -2;
				else {
					grow = 4;
				}
				p = GeometryUtils.getRectangleLineCrossPoint(toNode.getRectangle(), new Point(
						xPoints[(len - 2)], yPoints[(len - 2)]), grow);
				if (p != null) {
					xPoints[(len - 1)] = p.x;
					yPoints[(len - 1)] = p.y;
				}

				g2.drawPolyline(xPoints, yPoints, len);
				drawArrow(g2, xPoints[(len - 2)], yPoints[(len - 2)], xPoints[(len - 1)],
						yPoints[(len - 1)]);
				String label = transition.getLabel();

				if ((label == null) || (label.length() <= 0))
					continue;
				int cy;
				int cx;
				if (len % 2 == 0) {
					cx = (xPoints[(len / 2 - 1)] + xPoints[(len / 2)]) / 2;
					cy = (yPoints[(len / 2 - 1)] + yPoints[(len / 2)]) / 2;
				} else {
					cx = xPoints[(len / 2)];
					cy = yPoints[(len / 2)];
				}
				Point labelPoint = transition.getLabelPosition();
				if (labelPoint != null) {
					cx += labelPoint.x;
					cy += labelPoint.y;
				}
				cy += 12;
				g2.setColor(Color.BLUE);
				g2.drawString(label, cx, cy);
				g2.setColor(DEFAULT_LINE_STROKE_COLOR);
			}
	}

	private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
		double len = 8.0D;
		double slopy = Math.atan2(y2 - y1, x2 - x1);
		double cosy = Math.cos(slopy);
		double siny = Math.sin(slopy);
		int[] xPoints = { 0, x2 };
		int[] yPoints = { 0, y2 };
		double a = 8.0D * siny;
		double b = 8.0D * cosy;
		double c = 4.0D * siny;
		double d = 4.0D * cosy;
		xPoints[0] = (int) Math.round(x2 - (b + c));
		yPoints[0] = (int) Math.round(y2 - (a - d));
		xPoints[2] = (int) Math.round(x2 - (b - c));
		yPoints[2] = (int) Math.round(y2 - (d + a));

		g2.fillPolygon(xPoints, yPoints, 3);
	}

	private void drawNode(Map<String, Node> nodes, Set activityNames, Graphics2D g2, Font font)
			throws IOException {
		for (Node node : nodes.values()) {
			String name = node.getName();
			if (nodeInfos.get(node.getType()) != null) {
				BufferedImage bi2 = ImageIO.read(super.getClass().getResourceAsStream(
						"/icons/48/" + nodeInfos.get(node.getType())));
				g2.drawImage(bi2, node.getX(), node.getY(), null);
			} else {
				int x = node.getX();
				int y = node.getY();
				int w = node.getWidth();
				int h = node.getHeight();
				g2.setColor(DEFAULT_FILL_COLOR);
				g2.fillRoundRect(x, y, w, h, 25, 25);

				if (activityNames.contains(name))
					g2.setColor(Color.RED);
				else {
					g2.setColor(DEFAULT_STROKE_COLOR);
				}

				g2.setStroke(DEFAULT_STROKE);

				g2.drawRoundRect(x, y, w, h, 25, 25);

				FontRenderContext frc = g2.getFontRenderContext();
				Rectangle2D r2 = font.getStringBounds(name, frc);
				int xLabel = (int) (node.getX() + (node.getWidth() - r2.getWidth()) / 2.0D);
				int yLabel = (int) (node.getY() + (node.getHeight() - r2.getHeight()) / 2.0D - r2
						.getY());
				g2.setStroke(DEFAULT_LINE_STROKE);
				g2.setColor(Color.black);
				g2.drawString(name, xLabel, yLabel);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		JpdlModel jpdlModel = new JpdlModel(Thread.currentThread().getContextClassLoader()
				.getResource("jpdl/buyCar.jpdl.xml").openStream());
		jpdlModel.getActivityNames().add("经理审批");

		ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", new File("d:/buyCar.png"));
	}
}
