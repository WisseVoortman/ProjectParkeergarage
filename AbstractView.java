import javax.swing.*;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	private Model model;
	
	public AbstractView(Model model){
		this.model=model;
		setVisible(true);
	}
}
