import java.awt.Color;

import javax.swing.*;
import javax.swing.JMenuBar;

import java.awt.*;
import java.awt.event.*;

public class Simulator {

	private Model model;
	private JFrame screen;
	// insert view
	private Controller controller;
	private EarningsView earningsView;
	private CarParkView carParkView;
		
    public Simulator() {
    	model=new Model();
    	//creat an instance of controller 
    	controller=new Controller(model);
	
    	//create an instance of a view
    	//countview=new CountView(model);
		//pieview=new PieView(model);
    	earningsView=new EarningsView(model);
    	carParkView = new CarParkView(model);

    	screen=new JFrame("Parkeer Garage");
		screen.setSize(900, 650);
		screen.setResizable(true);
		screen.setLayout(null);
		screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		makeMenuBar(screen);
							
		//add views+controller to the screen
		//screen.getContentPane().add(countview);
		//screen.getContentPane().add(pieview);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(earningsView);
		screen.getContentPane().add(carParkView);

		//setbounds for views + controller
		//countview.setBounds(10, 10, 200, 200);
		//pieview.setBounds(230, 10, 200, 200);
		controller.setBounds(0, 0, 900, 50);
		carParkView.setBounds(0,50,850,550);
		earningsView.setBounds(0, 550, 450, 50);
        
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
