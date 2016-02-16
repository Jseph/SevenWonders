import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class DetailsWindow implements OnscreenComponent{
	Card detailsCard;
	Rectangle renderRectangle;
	public void setDetailsCard(Card detailsCard) 
	{
		this.detailsCard = detailsCard;
	}
	public void setRenderedRectangle(Rectangle r) 
	{
		renderRectangle = new Rectangle(r);
	}
	public void renderSelf(Graphics g) 
	{
		int xOffset = renderRectangle.x;
		int yOffset = renderRectangle.y;
		int width = renderRectangle.width;
		int height = renderRectangle.height;
		g.setColor(Color.GRAY);
		g.fillRoundRect(xOffset, yOffset, width, height, 4, 4);
		//render the card image, more to follow
		int cardHeight = height*3/5;
		int cardWidth = width*3/5;
		Image cardImage = getCardImage();
		double aspectRatio = ((double)cardImage.getHeight(null))/cardImage.getWidth(null);
		cardHeight = (int)Math.min(cardHeight, cardWidth*aspectRatio);
		cardWidth  = (int)Math.min(cardWidth, cardHeight/aspectRatio);
		g.drawImage(cardImage, 
				xOffset + (width-cardWidth)/2, 
				yOffset + (height-cardHeight)/2, 
				cardWidth, cardHeight, null);
		//Should also add things like card name and stats below the card,
		//at that point the card should not be centered as well.
	}
	private Image getCardImage()
	{
		if(detailsCard!=null)
			return detailsCard.getImage();
		Image ans = new Card(Card.Type.values()[0]).getImage();
		Graphics g = ans.getGraphics();
		g.setColor(new Color(40,40,40));
		g.fillRect(0, 0, ans.getWidth(null), ans.getHeight(null));
		
		return ans;
	}
	public void updateMouseLocation(int x, int y) {}

}
