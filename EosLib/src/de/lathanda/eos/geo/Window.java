package de.lathanda.eos.geo;

import de.lathanda.eos.base.Picture;
import de.lathanda.eos.base.Readout;
import de.lathanda.eos.base.event.CleanupListener;
import de.lathanda.eos.gui.ViewFrame;
import de.lathanda.eos.gui.event.FigureListener;
import de.lathanda.eos.util.ConcurrentLinkedList;

import java.awt.Color;
import java.util.LinkedList;

/**
 * Das Fenster stellt alle Figuren dar.
 *
 * @author Peter (Lathanda) Schneider
 */
public class Window implements FigureGroup, CleanupListener, Readout {
    ViewFrame vf;
    ChangeMultiCaster cmc;
    protected ConcurrentLinkedList<Figure> members;
    protected int mouseX, mouseY;
    protected boolean mouseClick = false;

    public Window() {
        members = new ConcurrentLinkedList<Figure>();
        cmc = new ChangeMultiCaster();
        vf = new ViewFrame(this);
        vf.setVisible(true);
    }                          

    @Override
    public void addFigure(Figure figure) {
        figure.replaceGroup(this);
        members.add(figure);
        cmc.fireDataChanged();
    }

    @Override
    public void removeFigure(Figure figure) {
        members.remove(figure);
    }

    @Override
    public Group getGroup() {
        return null;
    }
    
    public void draw(Picture g) {
    	for (Figure m : members) {
    		m.draw(g);
    	}
    }
    public void addFigureListener(FigureListener gol) {
        cmc.add(gol);
    }
    public void removeFigureListener(FigureListener gol) {
        cmc.remove(gol);
    }

    @Override
    public void fireDataChanged() {
        cmc.fireDataChanged();
    }

    public void setGridColor(Color color) {
        vf.setGridColor(color);
        fireDataChanged();
    }

    public Color getGridColor() {
        return vf.getGridColor();
    }

    public void setBackgroundColor(Color color) {
        vf.setBackgroundColor(color);
    }

    public Color getBackgroundColor() {
        return vf.getBackgroundColor();
    }

    public void setHeight(double height) {
        vf.setHeightMM(height);
    }

    public double getHeight() {
        return vf.getHeightMM();
    }

    public void setWidth(double width) {
        vf.setWidthMM(width);
    }

    public double getWidth() {
        return vf.getWidthMM();
    }

    public void setTop(double top) {
        vf.setTop(top);
    }

    public double getTop() {
        return vf.getTop();
    }

    public void setLeft(double left) {
        vf.setLeft(left);
    }

    public double getLeft() {
        return vf.getLeft();
    }
    
    public void setCenter(double x, double y) {
    	vf.setCenter(x, y);
    }

    public void setZoom(double zoom) {
    	vf.setZoom(zoom);
    }
    public void setGridWidth(double gridwidth) {
        vf.setGridWidth(gridwidth);
    }

    public double getGridWidth() {
        return vf.getGridWidth();
    }

    public void move(double dx, double dy) {
        vf.move(dx, dy);
    }

    public void setGridVisible(boolean b) {
        vf.setGridVisible(b);
    }
    public boolean getGridVisible() {
        return vf.getGridVisible();
    }
    public void turnGridOn() {
        vf.setGridVisible(true);
    }
    public void turnGridOff() {
        vf.setGridVisible(false);
    }
    public void setTitle(String title) {
        vf.setTitle(title);
    }

    public String getTitle() {
        return vf.getTitle();
    }
    private class ChangeMultiCaster {
        private final LinkedList<FigureListener> figureListener;
        protected ChangeMultiCaster() {
            figureListener = new LinkedList<>();
        }
        void add(FigureListener cl) {
            figureListener.add(cl);
        }
        void remove(FigureListener cl) {
            figureListener.remove(cl);
        }
        void fireDataChanged() {
            figureListener.forEach((cl) -> {
                cl.dataChanged();
            });
        }        
    }
    @Override
    public void terminate() {
        if (vf != null) {
            vf.dispose();
            vf = null;
        }
        cmc = null;
        members.clear();
        members = null;
    }

	@Override
	public void getAttributes(LinkedList<Attribut> attributes) {
        attributes.add(new Attribut("gridcolor", getGridColor()));
        attributes.add(new Attribut("height", getHeight()));
        attributes.add(new Attribut("width", getWidth()));
        attributes.add(new Attribut("top", getTop()));
        attributes.add(new Attribut("left", getLeft()));
        attributes.add(new Attribut("title", getTitle()));
        attributes.add(new Attribut("backcolor", getBackgroundColor()));
        attributes.add(new Attribut("gridwidth", getGridWidth()));
        attributes.add(new Attribut("gridvisible", getGridVisible()));	
	}

	public void setMouse(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public void setMouseClick() {
		mouseClick = true;		
	}
	public boolean isMouseClick() {
		if (mouseClick) {
			mouseClick = false;
			return mouseClick;
		}
		return false;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
}
