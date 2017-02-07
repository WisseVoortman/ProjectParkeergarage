import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends AbstractController {
	
	private Model model;
	private JButton start;
	private JButton stop;

	public Controller(Model model) {
		super(model);
		this.model=model;
		
		start=new JButton("start");
		start.addActionListener(this);
		stop=new JButton("stop");
		stop.addActionListener(this);
		
		this.setLayout(null);
		add(start);
		add(stop);
		// setBounds x/horizontal coördinates, y/vertical coördinates, width, Height,
		start.setBounds(0, 0, 70, 30);
		stop.setBounds(80, 0, 70, 30);

		setVisible(true);
	}
		
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource()==start) {
			System.out.println("Starting the simulator!");
			model.start();
			System.out.println("The simulator has been started!");
		}
		
		if (e.getSource()==stop) {
			System.out.println("Stopping the simulator!");
			model.stop();
			System.out.println("The simulator has been stopped!");
		}
	}
	
}