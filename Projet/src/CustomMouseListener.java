import java.awt.event.*;

class CustomMouseListener implements MouseListener{
	Board item;

	CustomMouseListener(Board item) {
		this.item = item;
	}
	public void mouseClicked(MouseEvent e) {
		int i = e.getX() / Board.side;
		int j = e.getY() / Board.side;
		item.change(item.getState(i,j));
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
