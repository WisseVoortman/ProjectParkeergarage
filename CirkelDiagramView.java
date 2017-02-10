import javax.swing.JLabel;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CirkelDiagramView extends AbstractView {

	private Model model;
	private JLabel label;
	private ArrayList<Slice> slices;
	private PieChart pieChart;
	//class variables here
	
	public CirkelDiagramView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("Parkeerplekken bezetting: ");
		label.setFont(new Font("default", Font.BOLD, 16));
		
		slices = new ArrayList<Slice>();

		this.setLayout(null);
		add(label);
		
		label.setBounds(25, 0, 250, 30);

		pieChart = new PieChart(slices);
		add(pieChart);
		pieChart.setBounds(25,40,200,200);
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {
		slices = new ArrayList<Slice>();
		slices.add( new Slice( Color.WHITE, (double)model.getNumberOfOpenSpots() ) );
		slices.add( new Slice( Color.RED, (double)model.getCars( AdHocCar.class.getName() ) ) );
		slices.add( new Slice( Color.BLUE, (double)model.getCars( ParkingPassCar.class.getName() ) ) );

		pieChart.setSlices( slices );
		repaint();
	}
}
