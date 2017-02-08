import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	private Model model;
	private JLabel label;
	//class variables here
	
	public SettingsView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("dit is de SettingsView");
		
		/* Tijd settings:
		*day
		*hour
		*minute
		*/
		
		/*
		*tickpause
		*/
		
		/* 
		*weekday arrivals (red car)
		*weekend arrivals (red car)
		*weekday arrivals (blue car)
		*weekend arrivals (blue car)
		*/
		
		/*
		*enterspeed
		*paymentspeed
		*exitspeed
		*/
		
		/* 
		*number of floors
		*number of rows
		*number of places
		*/
		
		
		this.setLayout(null);
		add(label);
		
		label.setBounds(0, 0, 200, 30);
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {

		repaint();
	}
}
