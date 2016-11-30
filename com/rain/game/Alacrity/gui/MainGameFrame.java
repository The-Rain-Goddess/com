package com.rain.game.Alacrity.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.rain.game.Alacrity.battle.Alpha;
import com.rain.game.Alacrity.main.Genesis;
import com.rain.game.Alacrity.main.MouseWheelHandler;
import com.rain.game.Alacrity.main.UniverseGame;

public class MainGameFrame implements Runnable{
	private static JTextArea msgField = new JTextArea(20,20);;
	private static JLabel gameClock = new JLabel();
	private static JPanel drawPanel = new DrawPanel();

//public constructor
	public MainGameFrame() {
		// TODO Auto-generated constructor stub
	}
	
//public constructor	
	public MainGameFrame(int n){
		
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(1000L);
			EventQueue.invokeLater(new Runnable(){
				@Override
				public void run(){
					//create UI and show on screen
						gui();
				}
			});
		} catch(InterruptedException e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
			return;
		}
	}
	
//non-private access and mutators
	public static JTextArea getMsgField(){
		return msgField;
	}
	
	public static JLabel getGameClock(){ return gameClock; }
	
	public static JPanel getDrawPanel(){ return drawPanel; }
	
//private access and mutators	
	private void gui(){
		JFrame mainFrame = new JFrame("main_frame");
		JTextField field = new JTextField(10);
		JButton battleStart = new JButton("Battle Button");
		
		//JOptionPane gameClock = new JOptionPane();
		
		drawPanel.addMouseMotionListener(UniverseGame.getMouseHandler());
		drawPanel.addMouseListener(UniverseGame.getMouseActionHandler());
		drawPanel.addMouseWheelListener(new MouseWheelHandler());
		drawPanel.setDoubleBuffered(true);
		field.addActionListener(UniverseGame.getInputActionListener(field));
		field.setBackground(Color.WHITE);
		String time = "Time:" + Genesis.getTime();
		gameClock.setText("<html>" + time +  "<font color='red'>red</font></html>");
		gameClock.setBorder(BorderFactory.createTitledBorder("Game Time"));
		gameClock.setPreferredSize(new Dimension(90,50));
		
		msgField.setBackground(new Color(127,127,127));
		msgField.setEditable(false);
		msgField.setAutoscrolls(true);
		msgField.setLineWrap(true);
		
		mainFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				field.requestFocus();
				
			}
			
		});
		
		battleStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					new Alpha();
			}
		});
		
		MouseMotionListener doScrollRectToVisible = new MouseMotionAdapter() {
		     @Override
			public void mouseDragged(MouseEvent e) {
		        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		        ((JTextArea)e.getSource()).scrollRectToVisible(r);
		     }
		};
		msgField.addMouseMotionListener(doScrollRectToVisible);
		JScrollPane scroll = new JScrollPane(msgField);
		
		scroll.setAutoscrolls(true);
		scroll.setWheelScrollingEnabled(true);
		drawPanel.setLayout(new BorderLayout());	
		drawPanel.add(gameClock, BorderLayout.NORTH);
		drawPanel.add(battleStart, BorderLayout.SOUTH);
		mainFrame.add(drawPanel);
		mainFrame.add(field, BorderLayout.SOUTH);
		mainFrame.add(scroll, BorderLayout.WEST);
		
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setUndecorated(false);
		mainFrame.setTitle("Alacrity - main");
		mainFrame.setIconImage(new ImageIcon("rain.png").getImage());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}

