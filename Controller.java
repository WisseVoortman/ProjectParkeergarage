import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class Controller extends AbstractController {
	
	private Model model;
	private JButton start;
	private JButton stop;
	private JButton tick;
	private JButton ticks;

	public Controller(Model model) {
		super(model);
		this.model=model;
		
		start=new JButton("start");
		start.addActionListener(this);
		stop=new JButton("stop");
		stop.addActionListener(this);
		tick=new JButton("1 minuut verder");
		tick.addActionListener(this);
		ticks=new JButton("1 uur verder");
		ticks.addActionListener(this);
		
		
		this.setLayout(null);
		add(start);
		add(stop);
		add(tick);
		add(ticks);
		// setBounds x/horizontal coördinates, y/vertical coördinates, width, Height,
		start.setBounds(0, 0, 70, 30);
		stop.setBounds(80, 0, 70, 30);
		tick.setBounds(160, 0, 70, 30);
		ticks.setBounds(240, 0, 70, 30);

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
		
		if (e.getSource()==tick) {
			System.out.println("Doing a single tick");
			model.tick();
			System.out.println("Done with doing a single tick");
		}
		
		if (e.getSource()==ticks) {
			System.out.println("Starting the simulator for 60 ticks");
			model.ticks();
			System.out.println("60 ticks done!");
		}
	}
	
}