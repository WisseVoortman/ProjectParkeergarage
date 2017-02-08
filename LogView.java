import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LogView extends AbstractView {

	private Model model;
	private JLabel label;
	//class variables here
	
	public LogView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("dit is een de LogView");
		
		this.setLayout(null);
		add(label);
		
		label.setBounds(0, 0, 100, 30);
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {

		repaint();
	}
}
