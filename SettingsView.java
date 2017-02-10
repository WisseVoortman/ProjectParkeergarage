import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SettingsView extends AbstractView {
	private Model model;

	private JButton bApplySettings;
	
	private JLabel titelLabel;

	private JLabel lblDay, lblHour, lblMinute;
	private JLabel lblTickPause;
	private JLabel lblWeekdayAdHoc, lblWeekDayParkingPass, lblWeekendDayAdHoc, lblWeekendDayParkingPass;
	private JLabel lblEnterSpeed, lblPaymentSpeed, lblExitSpeed;
	private JLabel lblFloors, lblRows, lblPlaces;
	private JLabel lblPricePerMinute;

	private JTextField tDay, tHour, tMinute;
	private JTextField tTickPause;
	private JTextField tWeekdayAdHoc, tWeekDayParkingPass, tWeekendDayAdHoc, tWeekendDayParkingPass;
	private JTextField tEnterSpeed, tPaymentSpeed, tExitSpeed;
	private JTextField tFloors, tRows, tPlaces;
	private JTextField tPricePerMinute;

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
		bApplySettings = new JButton( "Toepassen" );
		bApplySettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applySettings();
			}
		});

		//code for view here
		
		titelLabel 					= new JLabel("Instellingen: ");
		titelLabel.setFont(new Font("default", Font.BOLD, 16));
		
		lblDay 					= new JLabel( "Dag: " );
		lblHour 				= new JLabel( "Uur: " );
		lblMinute 				= new JLabel( "Minuut: " );
		labels.add( lblDay );
		labels.add( lblHour );
		labels.add( lblMinute );

		tDay 					= new JTextField( model.getDayString() );
		tHour 					= new JTextField( model.getHourString() );
		tMinute 				= new JTextField( model.getMinuteString() );
		textFields.add( tDay );
		textFields.add( tHour );
		textFields.add( tMinute );

		lblTickPause			= new JLabel( "Tick pause in miliseconden: " );
		labels.add( lblTickPause );

		tTickPause				= new JTextField( String.valueOf( model.getTickPause() ) );
		textFields.add( tTickPause );


		lblWeekdayAdHoc			= new JLabel( "Reguliere bezoekers werkdagen per uur: " ); 	// Weekday arrivals AdHoc
		lblWeekDayParkingPass	= new JLabel( "Abbonementhouders werkdagen per uur: " ); 	// Weekday arrivals ParkingPass
		lblWeekendDayAdHoc		= new JLabel( "Reguliere bezoekers Weekend per uur: " );	// WeekendDay arrivals AdHoc
		lblWeekendDayParkingPass= new JLabel( "Abbonementhouders weekend per uur: " );		// WeekendDay arrivals ParkingPass
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

		lblEnterSpeed 		= new JLabel( "Inrij snelheid per minuut: " );
		lblPaymentSpeed 	= new JLabel( "Betaal snelheid per minuut: " );
		lblExitSpeed		= new JLabel( "Exit speed per minuut: " );
		labels.add(lblEnterSpeed);
		labels.add(lblPaymentSpeed);
		labels.add(lblExitSpeed);

		tEnterSpeed			= new JTextField( String.valueOf( model.getEnterSpeed() ) );
		tPaymentSpeed		= new JTextField( String.valueOf( model.getPaymentSpeed() ) );
		tExitSpeed			= new JTextField( String.valueOf( model.getExitSpeed() ) );
		textFields.add(tEnterSpeed);
		textFields.add(tPaymentSpeed);
		textFields.add(tExitSpeed);

		lblFloors			= new JLabel( "Verdiepingen: " );
		lblRows				= new JLabel( "Rijen per verdieping: " );
		lblPlaces			= new JLabel( "Plaatsen per rij: " );
		labels.add(lblFloors);
		labels.add(lblRows);
		labels.add(lblPlaces);

		tFloors				= new JTextField( String.valueOf( model.getNumberOfFloors() ) );
		tRows				= new JTextField( String.valueOf( model.getNumberOfRows() ) );
		tPlaces				= new JTextField( String.valueOf( model.getNumberOfPlaces() ) );
		textFields.add(tFloors);
		textFields.add(tRows);
		textFields.add(tPlaces);

		lblPricePerMinute	= new JLabel( "Kosten per minuut in cent: " );
		labels.add(lblPricePerMinute);

		tPricePerMinute		= new JTextField( String.valueOf( model.getPricePerMinute() ) );
		textFields.add(tPricePerMinute);


		
		add(titelLabel);

		titelLabel.setBounds(25, 0, (int)(titelLabel.getText().length() * 9), 30);
		
		//- Positioning
		for( int i = 0; i < labels.size(); i++ ) {
			JLabel label = labels.get( i );
			JTextField textField = textFields.get( i );

			add( label );
			add( textField );

			label.setBounds(25, 30 * (i+1), (int)(label.getText().length() * 9),30);
			textField.setBounds(300 ,30 * (i+1), 100,30);
		}

		add(bApplySettings);
		bApplySettings.setBounds( 300, (labels.size() * 30) + 30, 100, 30 );


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

		//- Update some settings
		if( model.getSimulatorRunning() ) {
			tDay.setText(model.getDayString());
			tHour.setText(model.getHourString());
			tMinute.setText(model.getMinuteString());
		}
		repaint();
	}

	private void applySettings() {
		//- Apply all the settings
		for( int i = 0; i < textFields.size(); i++ ) {
			JTextField textField = textFields.get( i );
			switch( i ) {
				//- Time
				case 0:
					if( !textField.getText().toLowerCase().equals( model.getDayString().toLowerCase() ) )
						model.setDay( textField.getText() );
					break;
				case 1:
					if( Integer.valueOf( textField.getText() ) != model.getHour() )
						model.setHour( Integer.valueOf( textField.getText() ) );
					break;
				case 2:
					if( Integer.valueOf( textField.getText() ) != model.getMinute() )
						model.setMinute( Integer.valueOf( textField.getText() ) );
					break;
				//- Tick pause
				case 3:
					if( Integer.valueOf( textField.getText() ) != model.getTickPause() )
						model.setTickPause( Integer.valueOf( textField.getText() ) );
					break;
				//- Arrival speed
				case 4:
					if( Integer.valueOf( textField.getText() ) != model.getWeekDayArrivals() )
						model.setWeekDayArrivals( Integer.valueOf( textField.getText() ) );
					break;
				case 5:
					if( Integer.valueOf( textField.getText() ) != model.getWeekDayPassArrivals() )
						model.setWeekDayPassArrivals( Integer.valueOf( textField.getText() ) );
					break;
				case 6:
					if( Integer.valueOf( textField.getText() ) != model.getWeekendArrivals() )
						model.setWeekendArrivals( Integer.valueOf( textField.getText() ) );
					break;
				case 7:
					if( Integer.valueOf( textField.getText() ) != model.getWeekendPassArrivals() )
						model.setWeekendPassArrivals( Integer.valueOf( textField.getText() ) );
					break;
				//- Queue speed
				case 8:
					if( Integer.valueOf( textField.getText() ) != model.getEnterSpeed() )
						model.setEnterSpeed( Integer.valueOf( textField.getText() ) );
					break;
				case 9:
					if( Integer.valueOf( textField.getText() ) != model.getPaymentSpeed() )
						model.setPaymentSpeed( Integer.valueOf( textField.getText() ) );
					break;
				case 10:
					if( Integer.valueOf( textField.getText() ) != model.getExitSpeed() )
						model.setExitSpeed( Integer.valueOf( textField.getText() ) );
					break;
				//- Parking garage layout
				case 11:
					if( Integer.valueOf( textField.getText() ) != model.getNumberOfFloors() )
						model.setNumberOfFloors( Integer.valueOf( textField.getText() ) );
					break;
				case 12:
					if( Integer.valueOf( textField.getText() ) != model.getNumberOfRows() )
						model.setNumberOfRows( Integer.valueOf( textField.getText() ) );
					break;
				case 13:
					if( Integer.valueOf( textField.getText() ) != model.getNumberOfPlaces() )
						model.setNumberOfPlaces( Integer.valueOf( textField.getText() ) );
					break;
				//- Payment
				case 14:
					if( Integer.valueOf( textField.getText() ) != model.getPricePerMinute() )
						model.setPricePerMinute( Integer.valueOf( textField.getText() ) );
					break;
				//- This shouldn't be happening. This DEFINITELY ISN'T HAPPENING.
				default:
					break;
			}
		}
		model.updateViews();
	}
}