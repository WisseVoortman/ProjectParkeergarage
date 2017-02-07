@SuppressWarnings("serial")
public class LogView extends AbstractView {

	private Model model;
	
	//class variables here
	
	public LogView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		
		setVisible(true);
		
		model.addView(this);
	}
}
