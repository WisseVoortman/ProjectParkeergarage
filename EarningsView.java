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
		
		
		label=new JLabel("Earnings:");
		
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
		
				
		
		label.setBounds(0, 0, 200, 30);
		
		dag.setBounds(0, 40, 200, 30);
		dagData.setBounds(200, 40, 200, 30);
		uur.setBounds(0, 70, 200, 30);
		uurData.setBounds(200, 70, 200, 30);
		minuut.setBounds(0, 100, 200, 30);
		minuutData.setBounds(200, 100, 200, 30);
		
		gemaakteOmzet.setBounds(0, 140, 200, 30);
		gemaakteOmzetData.setBounds(200, 140, 200, 30);
		teMakenOmzet.setBounds(0, 170, 200, 30);
		teMakenOmzetData.setBounds(200, 170, 200, 30);
		
		setVisible(true);
		
		model.addView(this);
		
	}
	
	public void updateView() {
		dagData.setText(model.getDayString());
		uurData.setText(model.getHourString());
		minuutData.setText(model.getMinuteString());
		
		gemaakteOmzetData.setText(model.getRevenueString());
		teMakenOmzetData.setText("bla5");
		//repaint();
	}
}
