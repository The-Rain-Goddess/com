package com.rain.game.Alacrity.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.rain.game.Alacrity.assets.Fleet;
import com.rain.game.Alacrity.assets.Planet;
import com.rain.game.Alacrity.main.Asset;
import com.rain.game.Alacrity.ships.Battleship;
import com.rain.game.Alacrity.ships.Cruiser;
import com.rain.game.Alacrity.ships.Destroyer;
import com.rain.game.Alacrity.ships.Dreadnaught;
import com.rain.game.Alacrity.ships.Frigate;

public class InfoPanel extends JPanel {

	/**
	 *  @author Rain
	 */
	private static final long serialVersionUID = -9078741958857976055L;
	private static final int PANEL_WIDTH = 300, PANEL_HEIGHT = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	protected Asset asset;
	protected GridBagConstraints c = new GridBagConstraints();
	protected JLabel sweep = new JLabel("");
	
	public InfoPanel() {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		asset = null;
	}

	public InfoPanel(Asset s){
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.asset = s;
		this.setLayout(new GridBagLayout());
		if(asset.isCelestialBody())
			panelBodySetup();
		else 
			panelFleetSetup();
	}
	
//non-private access
	public Asset getAsset(){
		return this.asset;
	}
	
//non-private mutators
	public void update(){
		sweep(". ");
		showPop();
		
		showPlanetaryIncome();
		
		showMilitary();
		
		if(asset instanceof Planet)
			showPlanetStats();
	}
	
//private modifyers	
	private void panelBodySetup(){
		//planet picture
		showBodyPicture();
		
		//title
		showTitle();
		
		//exit button
		showExit();
		
		//xtra space grabber
		hideSpace();
		
		//show Population
		showPop();
		
		//show money values
		showPlanetaryIncome();	
		
		//show Military
		showMilitary();
		
		//planet specs
		showPlanetStats();
		
		//industry
		showIndustry();
	}
	
	private void panelFleetSetup(){
		//show Fleet picture
		//showBodyPicture();
		
		//show title
		showTitle();
		
		//show exit
		showExit();
		
		//xtra space grabber
		hideSpace();
	}
	
	private void showIndustry(){
		//TODO: buttons for wealth creation
		//		infrastructure creation
		//		tooltips
		
	}
	
	private void showPlanetStats(){
		c = new GridBagConstraints();
		if(this.stop("Mass:")){
			JLabel multi = new JLabel("<html>Mass: "
					+ "<br>Diameter: "
					+ "<br>Atmosphere: "
					+ "<br>Composition: </html>");
			c.gridx = 1;
			c.gridy = 2;
			this.add(multi, c);
		}
		
		
		if(this.asset instanceof Planet){
			if(this.stop("km.")){
				Planet planet = (Planet)asset;
				c = new GridBagConstraints();
				String html = "<html>" + String.format("%2.3ekg", (double)planet.getMass())+ "."
							+ "<br>" + String.format("%2.3ekm", (double)planet.getDiameter()) + "."
							+ "<br>	" + planet.getAtmosphere()+ "."
							+ "<br>" + planet.getComposition() + ".</html";
				JLabel multi2 = new JLabel(html);
				c.gridx = 2;
				c.gridy = 2;
				this.add(multi2, c);
			}
		}
	}
	
	private void showMilitary(){
		String str = "Military: "; //tmp string
		
		if(this.stop(str)){
			c = new GridBagConstraints();
			JLabel mil = new JLabel("Military: ");
			mil.setFont(new Font("Serif", Font.BOLD, 20));
			c.gridx = 0; c.gridy = 7;
			c.anchor = GridBagConstraints.LINE_START;
			//this.sweep();
			this.add(mil, c);
		}	
		
		c = new GridBagConstraints();
		if(this.asset.getOccupied()){
			//fleet name
			str = "Fleet: ";
			if(this.stop(str)){
				JLabel fleetName = new JLabel(str);
				c.gridx = 0; c.gridy = 8;
				c.anchor = GridBagConstraints.LINE_START;
				this.add(fleetName, c);
			}
			Fleet f1 = this.asset.getOccupyingFleet();
			JLabel label1 = new JLabel(f1.getName()+". ");
			c.gridx = 1; c.gridy = 8;
			c.anchor = GridBagConstraints.LINE_START;
			this.add(label1, c);
			
			//fleet num
			c = new GridBagConstraints();
			str = "Number of Ships: ";
			if(this.stop(str)){
				JLabel fleetSize = new JLabel(str);
				c.gridx = 0; c.gridy = 9;
				//this.sweep("Number of Ships: ");
				c.anchor = GridBagConstraints.LINE_START;
				this.add(fleetSize, c);
			}
				
			JLabel lbl2 = new JLabel(f1.getShipList().size()+". ");
			c.gridx = 1; c.gridy = 9;
			c.anchor = GridBagConstraints.LINE_START;
			this.add(lbl2, c);
			
			//fleet comp
			showFleetComp(f1);
		}
		
	}
	
	private void showFleetComp(Fleet f){
		//aquires count of ships in fleet
		int frigate = 0, destroyer = 0, dreadnaught = 0, cruiser = 0, battleship = 0;
		c = new GridBagConstraints();
		for(int i = 0; i<f.getShipList().size(); i++){
			if( f.getShip(i) instanceof Frigate){
				frigate++; }
			else if(f.getShip(i) instanceof Destroyer)
				destroyer++;
			else if(f.getShip(i) instanceof Cruiser)
				cruiser++;
			else if(f.getShip(i) instanceof Dreadnaught)
				dreadnaught++;
			else if(f.getShip(i) instanceof Battleship)
				battleship++;
		}
		String tmp = "";
		
		//frigates
		tmp = "Frigates: ";
		if(this.stop(tmp)){
			JLabel f1 = new JLabel(tmp);
			c.gridx = 0;
			c.gridy = 12;
			c.anchor = GridBagConstraints.LINE_END;
			this.add(f1, c);
		}
		
		c = new GridBagConstraints();
		
		JLabel f2 = new JLabel(frigate+". ");
		//this.sweep(". ");
		c.gridx = 1;
		c.gridy = 12;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(f2, c);
		
		//destroyer
		tmp = "Destroyers: ";
		if(this.stop(tmp)){
			c = new GridBagConstraints();
			JLabel d1 = new JLabel(tmp);
			c.gridx = 0;
			c.gridy = 13;
			c.anchor = GridBagConstraints.LINE_END;
			this.add(d1, c);
		}
		
		c = new GridBagConstraints();
		JLabel d2 = new JLabel(destroyer + ". ");
		//this.sweep(". ");
		c.gridx = 1;
		c.gridy = 13;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(d2, c);
		
		//cruiser
		tmp = "Cruisers: ";
		if(this.stop(tmp)){
			c = new GridBagConstraints();
			JLabel c1 = new JLabel(tmp);
			c.gridx = 0;
			c.gridy = 14;
			c.anchor = GridBagConstraints.LINE_END;
			this.add(c1, c);
		}
		
		c = new GridBagConstraints();
		JLabel c2 = new JLabel(cruiser+ ". ");
		c.gridx = 1;
		c.gridy = 14;
		c.anchor= GridBagConstraints.LINE_START;
		this.add(c2, c);
		
		//battleships
		tmp = "Battleships: ";
		if(this.stop(tmp)){
			c = new GridBagConstraints();
			JLabel b1 = new JLabel(tmp);
			c.gridx = 0;
			c.gridy = 15;
			c.anchor = GridBagConstraints.LINE_END;
			this.add(b1, c);
		}
		
		c = new GridBagConstraints();
		JLabel b2 = new JLabel(battleship+". ");
		c.gridx = 1;
		c.gridy = 15;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(b2, c);
		
		//dreadnaught
		tmp = "Dreadnaughts: ";
		if(this.stop(tmp)){
			c = new GridBagConstraints();
			JLabel n1 = new JLabel(tmp);
			c.gridx = 0;
			c.gridy = 16;
			c.anchor = GridBagConstraints.LINE_END;
			this.add(n1, c);
		}
		
		c = new GridBagConstraints();
		JLabel n2 = new JLabel(dreadnaught+". ");
		c.gridx = 1;
		c.gridy = 16;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(n2, c);
	}
	
	private void sweep(String str) {
		int n = this.getComponentCount();
		if (n > 0) {
			Component[] components = this.getComponents();
			for (int i = 0; i < components.length; i++) {
				if (components[i] instanceof JLabel) {
					JLabel label = (JLabel) components[i];
					String tmp = label.getText();
					if (tmp != null && tmp.contains(str))
						label.setText("");
				}
			}
		}
	}
	
	private boolean stop(String str) {
		int n = this.getComponentCount();
		if (n > 0) {
			Component[] components = this.getComponents();
			for (int i = 0; i < components.length; i++) {
				if (components[i] instanceof JLabel) {
					JLabel label = (JLabel) components[i];
					String tmp = label.getText();
					if (tmp != null && tmp.contains(str))
						return false;
				}
			}
		}
		return true;
	}
	
	private void showPop() {
		String str = "Economics: ";
		if (this.stop(str)) {
			c = new GridBagConstraints();
			JLabel eco = new JLabel(str);
			eco.setFont(new Font("Serif", Font.BOLD, 20));
			c.gridx = 0;
			c.gridy = 3;
			c.anchor = GridBagConstraints.LINE_START;
			this.add(eco, c);
		}

		c = new GridBagConstraints();
		str = "Population: ";
		if (this.stop(str)) {
			JLabel population = new JLabel(str);
			c.gridx = 0;
			c.gridy = 4;
			c.anchor = GridBagConstraints.LINE_END;
			c.ipadx = 5;
			this.add(population, c);
		}

		JLabel num1 = new JLabel(String.format("%,d", asset.getPopulation()) + ". ");
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.LINE_START;
		this.add(num1, c);
	}
	
	private void hideSpace(){
		c = new GridBagConstraints();
		c.weighty=1;
		c.gridy = 20;
		this.add(new JLabel(" "), c);
	}
	
	private void showPlanetaryIncome(){
		String str = "Planetary Income: ";
		if(this.stop(str)){
			c = new GridBagConstraints();
			JLabel moneys = new JLabel(str);
			c.gridx = 0;
			c.gridy = 5;
			c.ipadx = 5;
			c.anchor = GridBagConstraints.LINE_END;
			this.sweep("Planetary Income: ");
			this.add(moneys, c);
		}
		
		c = new GridBagConstraints();
		this.sweep("$");
		JLabel num1 = new JLabel(String.format("$%2.3e ", asset.getIncomeOverYear()));
		c.gridx = 1; c.gridy = 5;
		c.anchor = GridBagConstraints.LINE_END;
		this.add(num1, c);
		
		str = "per Galactic year.";
		if(this.stop(str)){
			c = new GridBagConstraints();
			JLabel units = new JLabel(str);
			c.gridx = 2;
			c.gridy = 5;
			c.anchor = GridBagConstraints.LINE_START;
			this.add(units, c);
		}
	}
	
	private void showBodyPicture(){
		try{
			c = new GridBagConstraints();
			String type;
			switch(asset.getType()){
				case 1: type = "terran_human.png"; break;
				case 2: type = "terran_iron.png"; break;
				case 3: type = "gas_sulfur.png"; break;
				case 4: type = "terran_asteroid.png"; break;
				case 5: type = "star_yellow.png"; break;
				default: type = "";
			}
			BufferedImage bodyImage = ImageIO.read(new File(type));
			c.gridx=0;
			c.gridy=2;
			c.fill = GridBagConstraints.BOTH;
			c.ipadx = 10; c.ipady=10;
			this.add(new JLabel(new ImageIcon(bodyImage)),c);
		} catch(IOException e){
			e.printStackTrace();
			//handle exception
		}
	}
	
	private void showExit(){
		c = new GridBagConstraints();
		JButton exit = new JButton(" X ");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					hidePanel();
			}
		});
		exit.setBackground(Color.RED);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.insets = new Insets(0,10,0,0);
		this.add(exit, c);
	}
	
	private void showTitle(){
		c = new GridBagConstraints();
		JLabel title = new JLabel(asset.getName(), JLabel.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 30));
		title.setBackground(Color.GRAY);
		
		c.gridx = 0; c.gridy = 1;
		c.weightx = .5;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		//c.gridwidth = 2;
		c.ipadx = 10; c.ipady=10;
		this.add(title, c);
	}
	
	private void hidePanel(){
		this.setVisible(false);
		this.setEnabled(false);
		MainGameFrame.getDrawPanel().remove(this);
	}
}
