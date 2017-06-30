package fortune;

import static java.lang.System.currentTimeMillis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.ajwerner.voronoi.Point;
import org.ajwerner.voronoi.Voronoi;
import org.ajwerner.voronoi.VoronoiEdge;

public class FortuneUI {
	private static ArrayList<Point> sites = new ArrayList<>();

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("FortuneUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Random rand = new Random(currentTimeMillis());
		for (int i = 0; i < 20; i++) {
			sites.add(new Point(rand.nextInt(90)+10, rand.nextInt(90)+10));
		}
		Voronoi voronoi = new Voronoi(sites);
		
		
		JComponent emptyLabel = new JComponent(){
			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				for ( VoronoiEdge e : voronoi.getEdgeList()) {
					g2.fillRect((int)e.site1.x, (int)e.site1.y, 1, 1);
					g2.fillRect((int)e.site2.x, (int)e.site2.y, 1, 1);
					g2.drawLine((int)e.p1.x, (int)e.p1.y, (int)e.p2.x, (int)e.p2.y);
				}
			}
		};
		emptyLabel.setPreferredSize(new Dimension(175, 100));
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
