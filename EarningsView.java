import javax.swing.*;

@SuppressWarnings("serial")
public class EarningsView extends AbstractView {

	private Model model;
	
	private JButton start;
	private JLabel label;
	
	public EarningsView(Model model) {
		super(model);
		this.model=model;
		
		start=new JButton("start");
		
		label=new JLabel("dit is een label");
		this.setLayout(null);
		add(start);
		add(label);
		
				
		start.setBounds(229, 0, 70, 30);
		label.setBounds(0, 0, 100, 30);
		
		setVisible(true);
		
		model.addView(this);
		
	}
}
