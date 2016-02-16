import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

public class PlayedCards implements OnscreenComponent
{
	static final double MAXSCALE = 1.0/4;
	static final double SPACE = 1.0/3;
	private boolean stateChanged;
	private BufferedImage img;
	DetailsWindow dw;
	ArrayList<Rectangle> locations;
	public enum Orientation{
		Right, Up, Left
	}
	private Orientation direction;
	private Rectangle renderedRectangle;
	private ArrayList<Card> cards;
	public PlayedCards(Orientation direction, ArrayList<Card> cards, DetailsWindow dw)
	{
		this.direction = direction;
		this.cards = cards;
		this.dw = dw;
		Collections.sort(this.cards);
		stateChanged = true;
		locations = new ArrayList<Rectangle>();
	}
	public void addCard(Card c)
	{
		cards.add(c);
		Collections.sort(cards);
		stateChanged = true;
	}
	private BufferedImage renderImage(int width, int height)
	{
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = bi.getGraphics();
		locations.clear();
		g.setColor(new Color(0, true));
		g.fillRect(0, 0, width, height);
		if(cards.size()==0) return bi;
		//Figure out how to draw in the cards
		//Will they fit all side by side?
		int numCards = cards.size();
		int cardWidth = cards.get(0).getImage().getWidth(null);
		int cardHeight = cards.get(0).getImage().getHeight(null);
		double currentScale = Math.min(MAXSCALE, ((double)height)/cardHeight);
		int totalWidth = (int) (numCards*cardWidth*currentScale + (numCards - 1)*cardWidth*currentScale*SPACE);
		if(totalWidth <= width)
		{
			//Just center all the cards and line them up
			int xOffset = (width - totalWidth)/2;
			int yOffset = (int) ((height - cardHeight*currentScale)); 
			for(int i=0; i<numCards; i++)
			{
				g.drawImage(cards.get(i).getImage(),
						xOffset, yOffset, 
						(int)(cardWidth*currentScale), 
						(int)(cardHeight*currentScale), null);
				locations.add(new Rectangle(xOffset, yOffset, 
						(int)(cardWidth*currentScale), 
						(int)(cardHeight*currentScale)));
				xOffset += currentScale*(1 + SPACE)*cardWidth;
			}
			return bi;
		}
		//Check to see if stacking all the cards diagonally will work
		//1/5 of top of card and 1/5 of width of card
		int maxCardsInGroup = 1 + (int)Math.max(0, (height - cardHeight*currentScale)/(cardHeight*currentScale/5));
		ArrayList<ArrayList<Card>> groups = getGroups();
		setMaxGroupSize(maxCardsInGroup, groups);
		totalWidth = (int) ((groups.size() - 1)*cardWidth*currentScale*SPACE);
		for(int i=0; i<groups.size(); i++)
		{
			totalWidth += cardWidth*currentScale;
			totalWidth += (groups.get(i).size() - 1)*cardWidth*currentScale/5;
		}
		if(totalWidth<=width)
		{
			//Center it and draw stacks
			int xOffset = (width - totalWidth)/2;
			for(int i=0; i<groups.size(); i++)
			{
				int totalHeight = (int) (currentScale*cardHeight*(1 + (groups.get(i).size() - 1)/5.0));
				int yOffset = height - totalHeight;
				for(int j=0; j<groups.get(i).size(); j++)
				{
					g.drawImage(groups.get(i).get(j).getImage(),
							xOffset, yOffset, 
							(int)(cardWidth*currentScale), 
							(int)(cardHeight*currentScale), null);
					locations.add(new Rectangle(xOffset, yOffset, 
							(int)(cardWidth*currentScale), 
							(int)(cardHeight*currentScale)));
					xOffset += currentScale*cardWidth/5;
					yOffset += currentScale*cardHeight/5;
				}
				xOffset += currentScale*cardWidth*(1 + SPACE);
			}
			return bi;
		}
		//Figure out if the current scale will work at all...
		totalWidth = (int) ((groups.size() - 1)*cardWidth*currentScale*SPACE);
		totalWidth += groups.size()*cardWidth*currentScale;
		while(totalWidth>=width)
		{
			maxCardsInGroup++;
			//scale*cardHeight*(1 + (n-1)/5) = height
			//scale = height/(cardHeight*(1+(n-1)/5))
			currentScale = height/(cardHeight*(1 + (maxCardsInGroup - 1)/5.0));
			groups = getGroups();
			setMaxGroupSize(maxCardsInGroup, groups);
			totalWidth = (int) ((groups.size() - 1)*cardWidth*currentScale*SPACE);
			totalWidth += groups.size()*cardWidth*currentScale;
		}
		//Line up all the groups without the diagonal stuff
		int xOffset = (width - totalWidth)/2;
		for(int i=0; i<groups.size(); i++)
		{
			int totalHeight = (int) (currentScale*cardHeight*(1 + (groups.get(i).size() - 1)/5.0));
			int yOffset = height - totalHeight;
			for(int j=0; j<groups.get(i).size(); j++)
			{
				g.drawImage(groups.get(i).get(j).getImage(),
						xOffset, yOffset, 
						(int)(cardWidth*currentScale), 
						(int)(cardHeight*currentScale), null);
				locations.add(new Rectangle(xOffset, yOffset, 
						(int)(cardWidth*currentScale), 
						(int)(cardHeight*currentScale)));
				yOffset += currentScale*cardHeight/5;
			}
			xOffset += currentScale*cardWidth*(1 + SPACE);
		}
		return bi;
	}
	private void setMaxGroupSize(int maxCardsInGroup, ArrayList<ArrayList<Card>> groups)
	{
		for(int i=0; i<groups.size(); i++)
			if(groups.get(i).size() > maxCardsInGroup)
			{
				ArrayList<Card> newGroup = new ArrayList<Card>();
				for(int j=0; j<maxCardsInGroup; j++)
					newGroup.add(groups.get(i).remove(0));
				groups.add(i, newGroup);
			}
	}
	private ArrayList<ArrayList<Card>> getGroups()
	{
		ArrayList<ArrayList<Card>> groups = new ArrayList<ArrayList<Card>>();
		//The cards are already sorted by color
		Card.Color currentColor = cards.get(0).getColor();
		ArrayList<Card> currentGroup = new ArrayList<Card>();
		groups.add(currentGroup);
		for(Card c: cards)
		{
			if(!c.getColor().equals(currentColor))
			{
				currentGroup = new ArrayList<Card>();
				currentColor = c.getColor();
				groups.add(currentGroup);
			}
			currentGroup.add(c);
		}
		return groups;
	}
	public void setRenderedRectangle(Rectangle r) 
	{
		renderedRectangle = new Rectangle(r);
		stateChanged = true;
	}
	public void renderSelf(Graphics g) 
	{
		int width = renderedRectangle.width;
		int height = renderedRectangle.height;
		if(direction == Orientation.Up)
		{
			if(stateChanged)
			{
				img = renderImage(width, height);
				stateChanged = false;
			}
			g.drawImage(img, renderedRectangle.x, renderedRectangle.y, 
					width, height, null);
		}
		if(direction == Orientation.Right)
		{
			if(stateChanged)
			{
				img = renderImage(height, width);
				stateChanged = false;
			}
			int x = renderedRectangle.x;
			int y = renderedRectangle.y;
			System.out.println(height + ":"+width);
			AffineTransform at = AffineTransform.getRotateInstance(Math.PI/2, height/2, width/2);
			AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate((width-height)/2, (height-width)/2);
			g2d.rotate(Math.PI/2, height/2, width/2);
			g2d.translate(y, -x);
			g2d.drawRenderedImage(img, null);
			g2d.dispose();
			//g.drawImage(ato.filter(img, null), 0, 0, null);
			//g.drawImage(img,at,0,0,null);
		}
		if(direction == Orientation.Left)
		{
			if(stateChanged)
			{
				img = renderImage(height, width);
				stateChanged = false;
			}
			int x = renderedRectangle.x;
			int y = renderedRectangle.y;
			System.out.println(height + ":"+width);
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate((width-height)/2, (height-width)/2);
			g2d.rotate(-Math.PI/2, height/2, width/2);
			g2d.translate(-y, x);
			g2d.drawRenderedImage(img, null);
			g2d.dispose();
		}
	}
	public void updateMouseLocation(int x, int y)
	{
		if(!renderedRectangle.contains(x,y))
			return;
		//Figure out where the mouse is on the image (rotated or shifted)
		x-=renderedRectangle.x;
		y-=renderedRectangle.y;
		
		switch(direction)
		{
			case Right: 
				int temp = x;
				x = y;
				y = renderedRectangle.width - temp;
				break;
			case Left:
				temp = x;
				x = renderedRectangle.height - y;
				y = temp;
				break;
		}
		for(int i=locations.size()-1; i>=0; i--)
			if(locations.get(i).contains(x, y))
			{
				dw.setDetailsCard(cards.get(i));
				return;
			}
	}

}
