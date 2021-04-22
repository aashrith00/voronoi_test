import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class vtest extends JFrame 
{

	static int cells = 10, size = 512, xpoint[], ypoint[], color[] ;
    static BufferedImage I;

	public vtest() 
    {
		super("Voronoi Diagram");
		setBounds(0, 0, size, size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int n = 0;
		Random r = new Random();
		I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		xpoint = new int[cells];
		ypoint = new int[cells];
		color = new int[cells];
		for (int i = 0; i < cells; i++) 
        {
			xpoint[i] = r.nextInt(size);
			ypoint[i] = r.nextInt(size);
            color[i] = r.nextInt(size);

		}
		for (int x = 0; x < size; x++) 
        {
			for (int y = 0; y < size; y++) 
            {
				n = 0;
				for (byte i = 0; i < cells; i++) 
                {
					if (distance(xpoint[i], x, ypoint[i], y) < distance(xpoint[n], x, ypoint[n], y)) 
                    {
						n = i;
					}
				}
				I.setRGB(x, y, color[n]);

			}
		}

		Graphics2D line = I.createGraphics();
		line.setColor(Color.BLACK);
		for (int i = 0; i < cells; i++) {
			line.fill(new Ellipse2D .Double(xpoint[i] - 2.5, ypoint[i] - 2.5, 5, 5));
		}

		try 
        {
			ImageIO.write(I, "png", new File("voronoidiagram.png"));
		} 
        catch (IOException e) 
        {

		}

	}

	public void paint(Graphics g) 
    {
		g.drawImage(I, 0, 0, this);
	}

	static double distance(int x1, int x2, int y1, int y2) 
    {
		double d;
	    d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	  	return d;
	}

	public static void main(String[] args) 
    {
		new vtest().setVisible(true);
	}
}
