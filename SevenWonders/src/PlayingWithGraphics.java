import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
public class PlayingWithGraphics extends JFrame implements ActionListener, KeyListener, MouseMotionListener
{
	Timer tm;
	ArrayList<Image> cards;
	int x, velX;
	int mouseX, mouseY;
	int cardNum;
	boolean isCardSelected;
	int selectedCard;
	public static void main(String[] args)
	{
		for(double d = -20; d<20; d+=0.1)
		{
			System.out.println(NORMSDIST(d));
		}
		new PlayingWithGraphics();
	}
	public PlayingWithGraphics() {
		tm = new Timer(100, this);
		cards = new ArrayList<Image>();
		for(Card.Type t: Card.Type.values())
		{
			Card c = new Card(t);
			cards.add(c.getImage());
		}
		cardNum = 5;
		tm.start();
		this.addKeyListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.addMouseMotionListener(this);
	}
	
	public void update(Graphics g) {paint(g);};
	public void paint(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();
		g.fillRect(0, 0, screenWidth, screenHeight);
		double scale = 1.0/4;
		
		double space = 1.0/3;
		
		int cardWidth = cards.get(0).getWidth(null);
		int cardHeight = cards.get(0).getHeight(null);
		
		int magnifiedCardHeight = cardHeight;
		//Figure out if the scale is appropriate
		scale = Math.min(scale, 
				screenWidth/(cardWidth*((1+space)*(cardNum) + space)));
		//Figure out what the x offset will be so this is centered
		int xOffset = (int)((screenWidth - cardWidth*((1+space)*cardNum + space)*scale)/2);
		int yOffset = (int) (screenHeight - cardHeight*scale);
		//int yOffset = (int)(screenHeight - cardHeight*(1+space));
		boolean mousedOver = true;
		//figure out if the mouse is over a card
		
		if(mousedOver == false)
		{
			isCardSelected = false;
			for(int i=0; i<cardNum; i++)
				g.drawImage(cards.get(i), 
					(int)(xOffset + cardWidth*((1+space)*i + space)*scale),
					(int)(yOffset),
					(int)(cardWidth*scale),
					(int)(cardHeight*scale),
					this);
		}
		else
		{
			//Figure out normal distribution scaling
			//How far do we want it to extend? (sigma)
			double sigma = cardWidth*scale;
			double scale2 = (cardWidth - cardWidth*scale)/((NORMSDIST(cardWidth*scale/2/sigma)-NORMSDIST(-cardWidth*scale/2/sigma)));
			xOffset += cardWidth*space*scale;
			int oldXOffset = xOffset;
			int x1 = xOffset;
			int x2 = (int)(xOffset + cardWidth*((1+space)*cardNum)*scale - cardWidth*space*scale);
			int y1 = (int) (screenHeight - cardHeight*scale);
			int y2 = screenHeight;			
			//Scale the growth by how far the pointer is from the cards
			double dx = Math.max(0, Math.max(x1 - mouseX, mouseX - x2));
			double dy = Math.max(0, Math.max(y1 - mouseY , mouseY - y2));
			double growSigma = cardHeight*scale/2;
			scale2 *= 2*(1 - NORMSDIST(Math.sqrt(dx*dx + dy*dy)/growSigma));
			
			int widthExpanded = 0;
			if(mouseX > x1)
				widthExpanded = (int) (scale2*(0.5 - NORMSDIST((x1-mouseX)/sigma)));
			if(mouseX > x2)
				widthExpanded -= scale2*(NORMSDIST((0)/sigma) - (NORMSDIST((x2-mouseX)/sigma)));
			xOffset -= widthExpanded;// * (mouseX-x1)/(x2-x1);

			isCardSelected = false;
			for(int i=0; i<cardNum; i++)
			{
				//Draw the card 
				x1 = (int)(oldXOffset + cardWidth*((1+space)*i)*scale);
				x2 = (int)(x1 + cardWidth*scale);
				int width = (int) (cardWidth*scale + scale2*(NORMSDIST((x2-mouseX)/sigma) - NORMSDIST((x1-mouseX)/sigma)));
				double cScale = width/(cardWidth + 0.0);
				g.drawImage(cards.get(i), 
						(int)(xOffset),
						(int)(yOffset - cardHeight*(cScale - scale)),
						(int)(cardWidth*cScale),
						(int)(cardHeight*cScale),
						this);
				Rectangle cardArea = new Rectangle(xOffset, (int)(yOffset-cardHeight*(cScale-scale)), (int)(cardWidth*cScale),(int) (cardHeight*cScale));
				if(cardArea.contains(mouseX, mouseY))
				{
					isCardSelected = true;
					selectedCard = i;
					//Draw a yellow boarder around the card
					g.setColor(Color.yellow);
					g.drawRoundRect(cardArea.x-1, cardArea.y-1, cardArea.width+2, cardArea.height+2, 4, 4);
				}
				xOffset += width;
				//"Draw" the gap
				x1 = x2;
				x2 = (int)(x1 + cardWidth*space*scale);
				xOffset += cardWidth*scale*space + scale2*(NORMSDIST((x2-mouseX)/sigma) - NORMSDIST((x1-mouseX)/sigma));
			}
		}
	}
	
	private static double erf(double x)
	{
	    //A&S formula 7.1.26
	    double a1 = 0.254829592;
	    double a2 = -0.284496736;
	    double a3 = 1.421413741;
	    double a4 = -1.453152027;
	    double a5 = 1.061405429;
	    double p = 0.3275911;
	    x = Math.abs(x);
	    double t = 1 / (1 + p * x);
	    //Direct calculation using formula 7.1.26 is absolutely correct
	    //But calculation of nth order polynomial takes O(n^2) operations
	    //return 1 - (a1 * t + a2 * t * t + a3 * t * t * t + a4 * t * t * t * t + a5 * t * t * t * t * t) * Math.Exp(-1 * x * x);

	    //Horner's method, takes O(n) operations for nth order polynomial
	    return 1 - ((((((a5 * t + a4) * t) + a3) * t + a2) * t) + a1) * t * Math.exp(-1 * x * x);
	}
	public static double NORMSDIST(double z)
	{
		//if(z<-3) return 0;
		//if(z>3) return 1;
	    double sign = 1;
	    if (z < 0) sign = -1;

	    double result=0.5 * (1.0 + sign * erf(Math.abs(z)/Math.sqrt(2)));
	    return result;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==e.VK_DOWN)
		{
			cardNum++;
			System.out.println("DOWN");
		}
		cardNum = cardNum % cards.size();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
}
