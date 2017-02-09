import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	private Model model;

	private JButton bApplySettings;

	private JLabel lblDay, lblHour, lblMinute;
	private JLabel lblTickPause;
	private JLabel lblWeekdayAdHoc, lblWeekDayParkingPass, lblWeekendDayAdHoc, lblWeekendDayParkingPass;
	private JLabel lblEnterSpeed, lblPaymentSpeed, lblExitSpeed;
	private JLabel lblFloors, lblRows, lblPlaces;

	private JTextField tDay, tHour, tMinute;
	private JTextField tTickPause;
	private JTextField tWeekdayAdHoc, tWeekDayParkingPass, tWeekendDayAdHoc, tWeekendDayParkingPass;
	private JTextField tEnterSpeed, tPaymentSpeed, tExitSpeed;
	private JTextField tFloors, tRows, tPlaces;

	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textFields;
	//class variables here
	
	public SettingsView(Model model) {
		super(model);
		this.model=model;

		//- Lists
		labels = new ArrayList<>();
		textFields = new ArrayList<>();

		//- Apply button
		bApplySettings = new JButton( "Apply" );
		bApplySettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applySettings();
			}
		});

		//code for view here
		lblDay 					= new JLabel( "Day" );
		lblHour 				= new JLabel( "Hour" );
		lblMinute 				= new JLabel( "Minute" );
		labels.add( lblDay );
		labels.add( lblHour );
		labels.add( lblMinute );

		tDay 					= new JTextField( model.getDayString() );
		tHour 					= new JTextField( model.getHourString() );
		tMinute 				= new JTextField( model.getMinuteString() );
		textFields.add( tDay );
		textFields.add( tHour );
		textFields.add( tMinute );

		lblTickPause			= new JLabel( "Tick pause" );
		labels.add( lblTickPause );

		tTickPause				= new JTextField( String.valueOf( model.getTickPause() ) );
		textFields.add( tTickPause );


		lblWeekdayAdHoc			= new JLabel( "Weekday arrivals AdHoc" );
		lblWeekDayParkingPass	= new JLabel( "Weekday arrivals ParkingPass" );
		lblWeekendDayAdHoc		= new JLabel( "WeekendDay arrivals AdHoc" );
		lblWeekendDayParkingPass= new JLabel( "WeekendDay arrivals ParkingPass" );
		labels.add(lblWeekdayAdHoc);
		labels.add(lblWeekDayParkingPass);
		labels.add(lblWeekendDayAdHoc);
		labels.add(lblWeekendDayParkingPass);

		tWeekdayAdHoc 			= new JTextField( String.valueOf( model.getWeekDayArrivals() ) );
		tWeekDayParkingPass		= new JTextField( String.valueOf( model.getWeekDayPassArrivals() ) );
		tWeekendDayAdHoc		= new JTextField( String.valueOf( model.getWeekendArrivals() ) );
		tWeekendDayParkingPass	= new JTextField( String.valueOf( model.getWeekendPassArrivals() ) );
		textFields.add(tWeekdayAdHoc );
		textFields.add(tWeekDayParkingPass);
		textFields.add(tWeekendDayAdHoc);
		textFields.add(tWeekendDayParkingPass);

		lblEnterSpeed 		= new JLabel( "Enter speed" );
		lblPaymentSpeed 	= new JLabel( "Payment speed" );
		lblExitSpeed		= new JLabel( "Exit speed" );
		labels.add(lblEnterSpeed);
		labels.add(lblPaymentSpeed);
		labels.add(lblExitSpeed);

		tEnterSpeed			= new JTextField( String.valueOf( model.getEnterSpeed() ) );
		tPaymentSpeed		= new JTextField( String.valueOf( model.getPaymentSpeed() ) );
		tExitSpeed			= new JTextField( String.valueOf( model.getExitSpeed() ) );
		textFields.add(tEnterSpeed);
		textFields.add(tPaymentSpeed);
		textFields.add(tExitSpeed);

		lblFloors			= new JLabel( "Floors" );
		lblRows				= new JLabel( "Rows" );
		lblPlaces			= new JLabel( "Places" );
		labels.add(lblFloors);
		labels.add(lblRows);
		labels.add(lblPlaces);

		tFloors				= new JTextField( String.valueOf( model.getNumberOfFloors() ) );
		tRows				= new JTextField( String.valueOf( model.getNumberOfRows() ) );
		tPlaces				= new JTextField( String.valueOf( model.getNumberOfPlaces() ) );
		textFields.add(tFloors);
		textFields.add(tRows);
		textFields.add(tPlaces);

		//- Positioning
		for( int i = 0; i < labels.size(); i++ ) {
			JLabel label = labels.get( i );
			JTextField textField = textFields.get( i );

			add( label );
			add( textField );

			label.setBounds(0, 30 * ( i + 1), (int)(label.getText().length() * 9),30);
			textField.setBounds(225 ,30 * ( i + 1), 80,30);
		}

		add(bApplySettings);
		bApplySettings.setBounds( 325, labels.size() * 30, 80, 30 );


		this.setLayout(null);
		setVisible(true);
		
		model.addView(this);
	}

	public void updateView() {
		//- Disable/enable items
		if( model.getSimulatorRunning() ) {
			bApplySettings.setEnabled( false );
			for( int i = 0; i < textFields.size(); i++ )
				textFields.get(i).setEnabled( false) ;
		}else{
			bApplySettings.setEnabled( true );
			for( int i = 0; i < textFields.size(); i++ )
				textFields.get(i).setEnabled( true) ;
		}

		repaint();
	}

	private void applySettings() {
		//- Apply all the settings
		for( int i = 0; i < textFields.size(); i++ ) {
			JTextField textField = textFields.get( i );
			switch( i ) {
				case 0:
					//if( Integer.valueOf( textField.getText() ) != model.getDay() )
					//	model.setDay( textField.getText() );
					break;

				case 1:
					if( Integer.valueOf( textField.getText() ) != model.getHour() )
						model.setHour( Integer.valueOf( textField.getText() ) );
					break;

				case 2:
					if( Integer.valueOf( textField.getText() ) != model.getMinute() )
						model.setMinute( Integer.valueOf( textField.getText() ) );
					break;

				default:
					break;
			}
		}
		model.updateViews();
	}
}