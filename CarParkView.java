import java.awt.*;

@SuppressWarnings("serial")
public class CarParkView extends AbstractView {
	private Model model;

	private Dimension size;
	private Image carParkImage;

	public CarParkView(Model model){
		super(model);
		this.model=model;
		size = new Dimension(0, 0);
		setVisible(true);

		model.addView(this);
	}

	public void updateView() {
		// Create a new car park image if the size has changed.
		if (!size.equals(getSize())) {
			size = getSize();
			carParkImage = createImage(size.width, size.height);
		}
		Graphics graphics = carParkImage.getGraphics();
		for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
			for(int row = 0; row < model.getNumberOfRows(); row++) {
				for(int place = 0; place < model.getNumberOfPlaces(); place++) {
					Location location = new Location(floor, row, place);
					Car car = model.getCarAt(location);
					Color color = car == null ? Color.white : car.getColor();
					drawPlace(graphics, location, color);
				}
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}

	/**
	 * Overriden. The car park view component needs to be redisplayed. Copy the
	 * internal image to screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent( g );

		if (carParkImage == null) {
			return;
		}

		Dimension currentSize = getSize();
		if (size.equals(currentSize)) {
			g.drawImage(carParkImage, 0, 0, null);
		}
		else {
			// Rescale the previous image.
			g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
		}
	}

	private void drawPlace(Graphics graphics, Location location, Color color) {
		graphics.setColor(color);
		graphics.fillRect(
				location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
				60 + location.getPlace() * 10,
				20 - 1,
				10 - 1); // TODO use dynamic size or constants
	}

	public void redraw() {
		size = new Dimension(0, 0);
		updateView();
	}
}