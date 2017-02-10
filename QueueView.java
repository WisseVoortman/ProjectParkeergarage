import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class QueueView extends AbstractView {

	private Model model;
	private JLabel label;
	//class variables here
	private JLabel entranceCarQueueLabel;
	private JLabel entranceCarQueueSize;
	
	private JLabel entrancePassQueueLabel;
	private JLabel entrancePassQueueSize;
	
	private JLabel paymentCarQueueLabel;
	private JLabel paymentCarQueueSize;
	
	private JLabel exitCarQueueLabel;
	private JLabel exitCarQueueSize;
	
	public QueueView(Model model) {
		super(model);
		this.model=model;

		//code for view here
		label=new JLabel("Rijen: ");
		label.setFont(new Font("default", Font.BOLD, 16));
		
		entranceCarQueueLabel = new JLabel("Rij Hoofdingang: ");
		entranceCarQueueSize = new JLabel();
		entrancePassQueueLabel = new JLabel("Rij abonnementhouders ingang: ");
		entrancePassQueueSize = new JLabel();
		paymentCarQueueLabel = new JLabel("Rij Betaalvoorziening: ");
		paymentCarQueueSize = new JLabel();
		exitCarQueueLabel = new JLabel("Rij Uitgang: ");
		exitCarQueueSize = new JLabel();
		
		this.setLayout(null);
		
		add(label);
		
		add(entranceCarQueueLabel);
		add(entranceCarQueueSize);
		
		add(entrancePassQueueLabel);
		add(entrancePassQueueSize);
		
		add(paymentCarQueueLabel);
		add(paymentCarQueueSize);
		
		add(exitCarQueueLabel);
		add(exitCarQueueSize);
		
		
		label.setBounds(25, 0, 200, 30);
		entranceCarQueueLabel.setBounds(25, 40, 200, 30);
		entranceCarQueueSize.setBounds(225, 40, 200, 30);
		entrancePassQueueLabel.setBounds(25, 70, 200, 30);
		entrancePassQueueSize.setBounds(225, 70, 200, 30);
		paymentCarQueueLabel.setBounds(25, 100, 200, 30);
		paymentCarQueueSize.setBounds(225, 100, 200, 30);
		exitCarQueueLabel.setBounds(25, 130, 200, 30);
		exitCarQueueSize.setBounds(225, 130, 200, 30);
		
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {
		CarQueue entranceCarQueue = model.getEntranceCarQueue();
		CarQueue entrancePassQueue = model.getEntrancePassQueue();
		CarQueue paymentCarQueue = model.getPaymentCarQueue();
		CarQueue exitCarQueue = model.getExitCarQueue();
		
		int entranceCarQueueInt = entranceCarQueue.carsInQueue();
		int entrancePassQueueInt = entrancePassQueue.carsInQueue();
		int paymentCarQueueInt = paymentCarQueue.carsInQueue();
		int exitCarQueueInt = exitCarQueue.carsInQueue();
		
		entranceCarQueueSize.setText(Integer.toString(entranceCarQueueInt));
		entrancePassQueueSize.setText(Integer.toString(entrancePassQueueInt));
		paymentCarQueueSize.setText(Integer.toString(paymentCarQueueInt));
		exitCarQueueSize.setText(Integer.toString(exitCarQueueInt));
		
		//repaint();
	}
}
