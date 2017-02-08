import javax.swing.*;

@SuppressWarnings("serial")
public class QueueView extends AbstractView {

	private Model model;
	private JLabel label;
	//class variables here
	
	public QueueView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("dit is een de QueueView");
		
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
