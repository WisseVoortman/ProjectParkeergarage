@SuppressWarnings("serial")
public class SettingsView extends AbstractView {

	private Model model;
	
	//class variables here
	
	public SettingsView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		
		setVisible(true);
		
		model.addView(this);
	}
}
