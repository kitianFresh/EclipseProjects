import java.awt.Graphics;
public interface  Parser {
	public void draw(Graphics g);
	public int getWidth();
	public int getHeight();
	public void setStoped(boolean stoped);
	public boolean isStoped();
}
