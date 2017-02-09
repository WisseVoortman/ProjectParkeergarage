import java.awt.Color;

import javax.swing.*;
import javax.swing.JMenuBar;

import java.awt.*;
import java.awt.event.*;

public class Simulator {

	private Model model;
	private JFrame screen;
	
	//insert controller
	private Controller controller;
	
	// insert view
	private CarParkView carParkView;
	private SettingsView settingsView;
	private EarningsView earningsView;
	private CirkelDiagramView cirkelDiagramView;
	private QueueView queueView;
	private LogView logView;
	
		
    public Simulator() {
    	model=new Model();
    	
    	//creat an instance of controller 
    	controller=new Controller(model);
	
    	//create an instance of a view
    	carParkView = new CarParkView(model);
    	settingsView = new SettingsView(model);
    	earningsView = new EarningsView(model);
    	cirkelDiagramView = new CirkelDiagramView(model);
    	queueView = new QueueView(model);
    	//logView = new LogView(model);
    	
    	screen=new JFrame("Parkeer Garage");
		screen.setSize(1500, 650);
		screen.setResizable(true);
		screen.setLayout(null);
		screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//- Full screen
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);

		makeMenuBar(screen);
							
		//add controller to the screen
		screen.getContentPane().add(controller);
		
		//add view(s) to the screen
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(settingsView);
		screen.getContentPane().add(earningsView);
		screen.getContentPane().add(cirkelDiagramView);
		screen.getContentPane().add(queueView);
		//screen.getContentPane().add(logView);
		
		//setbounds for the controller(s)
		controller.setBounds(0, 0, 900, 50);
		
		//setBounds for the view(s) (int x, int y, int width, int height)
		carParkView.setBounds		(50,50,900,500);
		settingsView.setBounds		(1000, 50, 500, 500);
		earningsView.setBounds		(50, 600, 300, 250);
        cirkelDiagramView.setBounds	(400, 600, 300, 250);
        queueView.setBounds			(50, 900, 300, 250);
        //logView.setBounds			(50, 960, 500, 250);
		
		screen.setVisible(true);
		screen.getContentPane().setBackground(Color.YELLOW);
		screen.repaint();
		model.updateViews();
		//model.run();
    }

    private void makeMenuBar(JFrame screen)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        screen.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the Options menu
        menu = new JMenu("Options");
        menubar.add(menu);
        
        item = new JMenuItem("start");
    		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
    		item.addActionListener(e -> model.start());
    	menu.add(item);
        
    	item = new JMenuItem("pauzeer");
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, SHORTCUT_MASK));
			item.addActionListener(e -> model.stop());
		menu.add(item);
    	
		item = new JMenuItem("tick");
    		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, SHORTCUT_MASK));
    		item.addActionListener(e -> model.tick());
    	menu.add(item);

        menu.addSeparator();

        item = new JMenuItem("Fullscreen");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, SHORTCUT_MASK));
            item.addActionListener(e -> {
                this.screen.dispose();
                this.screen.setUndecorated(!this.screen.isUndecorated());
                this.screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                this.screen.setVisible(true);
            });
        menu.add(item);

        menu.addSeparator();
		   	
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
        
     // create the Views menu
        menu = new JMenu("Views");
        menubar.add(menu);
        
        item = new JMenuItem("Alle Views");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, SHORTCUT_MASK));
            item.addActionListener(e -> showAllViews());
		menu.add(item);
        
		item = new JMenuItem("Omzet View");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, SHORTCUT_MASK));
            item.addActionListener(e -> showEarningsView());
		menu.add(item);
		
		item = new JMenuItem("Parkeerplekken bezetting");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, SHORTCUT_MASK));
            item.addActionListener(e -> showCirkelDiagramView());
		menu.add(item);
		
		item = new JMenuItem("Rijen");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, SHORTCUT_MASK));
            item.addActionListener(e -> showQueueView());
		menu.add(item);
		
		item = new JMenuItem("verberg/toon Settings menu");
        	item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, SHORTCUT_MASK));
        	item.addActionListener(e -> showSettingsView());
        menu.add(item);
    } 

    private void quit() {
        System.exit(0);
    }
    
    private void showAllViews() {
    	for( AbstractView v : model.getViews() ) {
            v.setVisible(false);
        }
    }
    
    private void showEarningsView() {
    	for( AbstractView v : model.getViews() ) {
            v.setVisible(false);
            earningsView.setBounds(50, 600, 300, 250);
            carParkView.setVisible(true);
            earningsView.setVisible(true);
        }
    	
    }
    
    private void showCirkelDiagramView() {
    	for( AbstractView v : model.getViews() ) {
            v.setVisible(false);
            cirkelDiagramView.setBounds(50, 600, 300, 250);
            cirkelDiagramView.setVisible(true);
            
            carParkView.setVisible(true);
        }
    }
    
    private void showQueueView() {
    	for( AbstractView v : model.getViews() ) {
            v.setVisible(false);
            queueView.setBounds(50, 600, 300, 250);
            queueView.setVisible(true);
            carParkView.setVisible(true);
        }
    }
    
    private void showSettingsView() {
//    	for( AbstractView v : model.getViews() ) {
//            v.setVisible(false);
//            settingsView.setBounds(900, 600, 900, 500);
//            settingsView.setVisible(true);
//            carParkView.setVisible(true);
//        }
    	if(!settingsView.isVisible()) {
			settingsView.setVisible(true);
		} 
		else {
			settingsView.setVisible(false);
		}
    }
}
