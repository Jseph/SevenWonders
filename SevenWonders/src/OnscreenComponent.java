import java.awt.Graphics;
import java.awt.Rectangle;

public interface OnscreenComponent
{
	public abstract void setRenderedRectangle(Rectangle r);
	public abstract void renderSelf(Graphics g);
	public abstract void updateMouseLocation(int x, int y);
	
}
