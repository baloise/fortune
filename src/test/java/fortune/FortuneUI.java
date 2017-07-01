package fortune;

import static java.lang.System.currentTimeMillis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.ajwerner.voronoi.Point;
import org.ajwerner.voronoi.Voronoi;
import org.ajwerner.voronoi.VoronoiEdge;

public class FortuneUI {
	private ArrayList<Point> sites = new ArrayList<>();
	
	private void createAndShowGUI() {
		JFrame frame = new JFrame("FortuneUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Random rand = new Random(currentTimeMillis());
		for (int i = 0; i < 20; i++) {
			Point p = new Point(rand.nextInt(490)+5, rand.nextInt(490)+5);
			System.out.println(p);
			sites.add(p);
		}
		Voronoi voronoi = new Voronoi(sites);
		voronoi.compute();
		
		
		JComponent canvas = new JComponent(){
			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				for ( VoronoiEdge e : voronoi.getEdgeList()) {
					drawPoint(g2, (int)e.site1.x, (int)e.site1.y,1);
					drawPoint(g2, (int)e.site2.x, (int)e.site2.y,1);
					g2.drawLine((int)e.p1.x, (int)e.p1.y, (int)e.p2.x, (int)e.p2.y);
				}
			}
		};
		canvas.setPreferredSize(new Dimension(500, 500));
		frame.getContentPane().add(canvas, BorderLayout.CENTER);
		canvas.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
					sites.remove(sites.size()-1);
					sites.add(new Point(e.getX(), e.getY()));
					voronoi.compute();
					SwingUtilities.invokeLater(canvas::repaint);
			}
		});

		frame.pack();
		frame.setVisible(true);
	}

	protected void drawPoint(Graphics2D g2, int x, int y, int r) {
		g2.fillRect(x-r, y-r, 2*r+1, 2*r+1);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FortuneUI().createAndShowGUI();
			}
		});
	}
}
