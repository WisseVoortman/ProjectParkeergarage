import javax.swing.JLabel;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CirkelDiagramView extends AbstractView {

	private Model model;
	private JLabel label;
	//class variables here
	
	public CirkelDiagramView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("dit is een de Cirkeldiagramview");
		
		this.setLayout(null);
		add(label);
		
		label.setBounds(0, 0, 200, 30);

		//- Pie Chart
		ArrayList<Double> values = new ArrayList<Double>();
		values.add(new Double(20));
		values.add(new Double(20));
		values.add(new Double(20));
		values.add(new Double(20));
		values.add(new Double(20));

		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.green);
		colors.add(Color.blue);
		colors.add(Color.pink);
		colors.add(Color.yellow);
		//PieChart pieChart = new PieChart(values, colors);
		//add(pieChart);
		ArrayList<Slice> slices = new ArrayList<Slice>();
		slices.add( new Slice( Color.RED, 20d ) );
		slices.add( new Slice( Color.GREEN, 20d ) );
		slices.add( new Slice( Color.BLUE, 30d ) );
		slices.add( new Slice( Color.YELLOW, 30d ) );

		PieChart pieChart = new PieChart(slices);
		add(pieChart);
		pieChart.setBounds(0,0,200,200);
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {

		repaint();
	}
}
