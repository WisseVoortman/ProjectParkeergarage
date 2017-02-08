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
    	logView = new LogView(model);
    	
    	screen=new JFrame("Parkeer Garage");
		screen.setSize(900, 650);
		screen.setResizable(true);
		screen.setLayout(null);
		screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//- Full screen
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//screen.setUndecorated(true);

		makeMenuBar(screen);
							
		//add controller to the screen
		screen.getContentPane().add(controller);
		
		//add view(s) to the screen
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(settingsView);
		screen.getContentPane().add(earningsView);
		screen.getContentPane().add(cirkelDiagramView);
		screen.getContentPane().add(queueView);
		screen.getContentPane().add(logView);
		
		//setbounds for the controller(s)
		controller.setBounds(0, 0, 900, 50);
		
		//setBounds for the view(s)
		carParkView.setBounds(0,50,900,500);
		settingsView.setBounds(0, 550, 900, 50);
		earningsView.setBounds(0, 600, 900, 200);
        cirkelDiagramView.setBounds(900, 50, 250, 250);
        queueView.setBounds(0, 850, 900, 50);
        logView.setBounds(0, 900, 900, 50);
		
		screen.setVisible(true);

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
		   	
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
        
     // create the Views menu
        menu = new JMenu("Views");
        menubar.add(menu);
        
        item = new JMenuItem("View 1");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, SHORTCUT_MASK));
		item.addActionListener(e -> model.start());
		menu.add(item);
        
		item = new JMenuItem("View 2");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, SHORTCUT_MASK));
		item.addActionListener(e -> model.start());
		menu.add(item);
		
		item = new JMenuItem("View 3");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, SHORTCUT_MASK));
		item.addActionListener(e -> model.start());
		menu.add(item);
		
		item = new JMenuItem("View 4");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, SHORTCUT_MASK));
		item.addActionListener(e -> model.start());
		menu.add(item);
    } 

    private void quit() {
        System.exit(0);
    }
}
