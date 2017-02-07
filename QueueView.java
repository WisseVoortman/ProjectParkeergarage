import javax.swing.*;

@SuppressWarnings("serial")
public class QueueView extends AbstractView {

	private Model model;
	
	//class variables here
	
	public QueueView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {

		repaint();
	}
}
