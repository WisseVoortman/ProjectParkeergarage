@SuppressWarnings("serial")
public class CirkelDiagramView extends AbstractView {

	private Model model;
	
	//class variables here
	
	public CirkelDiagramView(Model model) {
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
