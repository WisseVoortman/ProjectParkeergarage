import javax.swing.*;

@SuppressWarnings("serial")
public class EarningsView extends AbstractView {

	private Model model;
	
	private JLabel label;
	
	private JLabel dag;
	private JLabel dagData;	
	private JLabel uur;
	private JLabel uurData;
	private JLabel minuut;
	private JLabel minuutData;
	
	private JLabel gemaakteOmzet;
	private JLabel gemaakteOmzetData;
	private JLabel teMakenOmzet;
	private JLabel teMakenOmzetData;
	
	public EarningsView(Model model) {
		super(model);
		this.model=model;
		
		
		label=new JLabel("Omzet:");
		
		dag = new JLabel("dag: ");
		dagData = new JLabel();
		uur = new JLabel ("uur: ");
		uurData = new JLabel();
		minuut = new JLabel("minuut: ");
		minuutData = new JLabel();
		
		gemaakteOmzet = new JLabel("gemaakte omzet: ");
		gemaakteOmzetData = new JLabel();
		teMakenOmzet = new JLabel("te maken omzet: ");
		teMakenOmzetData = new JLabel();
		
		
		this.setLayout(null);
		
		add(label);
		
		add(dag);
		add(dagData);
		add(uur);
		add(uurData);
		add(minuut);
		add(minuutData);
		
		add(gemaakteOmzet);
		add(gemaakteOmzetData);
		add(teMakenOmzet);
		add(teMakenOmzetData);
		
				
		
		label.setBounds(25, 0, 200, 30);
		
		dag.setBounds(25, 40, 200, 30);
		dagData.setBounds(225, 40, 200, 30);
		uur.setBounds(25, 70, 200, 30);
		uurData.setBounds(225, 70, 200, 30);
		minuut.setBounds(25, 100, 200, 30);
		minuutData.setBounds(225, 100, 200, 30);
		
		gemaakteOmzet.setBounds(25, 140, 200, 30);
		gemaakteOmzetData.setBounds(225, 140, 200, 30);
		teMakenOmzet.setBounds(25, 170, 200, 30);
		teMakenOmzetData.setBounds(225, 170, 200, 30);
		
		setVisible(true);
		
		model.addView(this);
		
	}
	
	public void updateView() {
		dagData.setText(model.getDayString());
		uurData.setText(model.getHourString());
		minuutData.setText(model.getMinuteString());
		
		gemaakteOmzetData.setText(model.getRevenueString());
		teMakenOmzetData.setText(model.getToMakeRevenueString());
		//repaint();
	}
}
