import javax.swing.*;

@SuppressWarnings("serial")
public class EarningsView extends AbstractView {

	private Model model;
	
	private JLabel label;
	
	private JLabel dag;
	private JLabel uur;
	private JLabel minuut;
	
	private JLabel gemaakteOmzet;
	private JLabel teMakenOmzet;
	
	public EarningsView(Model model) {
		super(model);
		this.model=model;
		
		
		label=new JLabel("Earnings:");
		
		dag = new JLabel("dag: ");
		uur = new JLabel ("uur: ");
		minuut = new JLabel("minuut: ");
		
		gemaakteOmzet = new JLabel("gemaakte omzet: ");
		teMakenOmzet = new JLabel("te maken omzet: ");
		
		
		this.setLayout(null);
		
		add(label);
		add(dag);
		add(uur);
		add(minuut);
		
		add(gemaakteOmzet);
		add(teMakenOmzet);
		
				
		
		label.setBounds(0, 0, 200, 30);
		
		dag.setBounds(0, 40, 200, 30);
		uur.setBounds(0, 70, 200, 30);
		minuut.setBounds(0, 100, 200, 30);
		
		gemaakteOmzet.setBounds(0, 140, 200, 30);
		teMakenOmzet.setBounds(0, 170, 200, 30);
		
		setVisible(true);
		
		model.addView(this);
		
	}
	
	public void updateView() {
		//label.setText("dit is die niewe tekst man");
		//repaint();
	}
}
