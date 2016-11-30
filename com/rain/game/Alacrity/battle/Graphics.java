package com.rain.game.Alacrity.battle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Graphics implements Runnable {
	//private boolean gameRunning;
	//private java.awt.Graphics2D g2;
	// java.awt.Graphics g;
	
	//private MyMouseAdapter mouseAdapter;
	private GamePanel gamePanel;
	private static JTextArea chatField;
	private boolean windowClosed = false;
	
//public constructor	
	public Graphics(){
		//this.gameRunning = true;
	}
	
//non-private access and mutators	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			if(Alpha.gameRunning){
				Thread.sleep(1000);
				EventQueue.invokeLater(new Runnable(){
					@Override
					public void run(){
						createAndShowUI();
					}
				});
			} else{
				Thread.currentThread().interrupt();
				return;
			}
				
		} catch(InterruptedException e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
			return;
		}
	}	
	
//private mutator	
	private void createAndShowUI(){
		JFrame frame = new JFrame("Frame");
		gamePanel = new GamePanel();
		gamePanel.addMouseListener(Game.getMouseAdapter());
		//JOptionPane.showInputDialog(gamePanel, "Command:");
		JTextField field = new JTextField(10);
		field.addActionListener(Game.getInputActionListener(field));
		field.setFocusable(true);

		field.requestFocusInWindow();
		
		frame.add(field, BorderLayout.SOUTH);
		
		chatField = new JTextArea(20, 20);
		chatField.setBackground(new Color(127,127,127));
		chatField.setEditable(false);
		//chatField.setFocusable(false);
		chatField.setAutoscrolls(true);
		chatField.setWrapStyleWord(true);
		chatField.setLineWrap(true);
		
		MouseMotionListener doScrollRectToVisible = new MouseMotionAdapter() {
		     @Override
			public void mouseDragged(MouseEvent e) {
		        Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
		        ((JTextArea)e.getSource()).scrollRectToVisible(r);
		    }
		 };
		 chatField.addMouseMotionListener(doScrollRectToVisible);
		 JScrollPane scroll = new JScrollPane(chatField);
		 scroll.setAutoscrolls(true);
		 scroll.setWheelScrollingEnabled(true);
			
		frame.add(scroll, BorderLayout.WEST);
		
		frame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				windowClosed = true;
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
			public void windowOpened(WindowEvent e) {
				field.requestFocus();
				
			}
	
		});
		
		frame.getContentPane().add(gamePanel);
		frame.setTitle("Battle Window");
		frame.setIconImage(new ImageIcon("rain.png").getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static JTextArea getChatField(){ return chatField; }
	
	public boolean getWindowClosed(){ return windowClosed; }
}

