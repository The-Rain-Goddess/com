package com.rain.web.dragcave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static List<Egg> eggs = Collections.synchronizedList(new ArrayList<Egg>());
	private static boolean continue1 = true;
	
	public static void main(String[] args){
		Thread t1, t2, t3, t4, t5, t6;
		t1 = new Thread(new WebListener("http://dragcave.net/locations/1",1));
		t2 = new Thread(new WebListener("http://dragcave.net/locations/2",2));
		t3 = new Thread(new WebListener("http://dragcave.net/locations/3",3));
		t4 = new Thread(new WebListener("http://dragcave.net/locations/4",4));
		t5 = new Thread(new WebListener("http://dragcave.net/locations/5",5));
		t6 = new Thread(new WebListener("http://dragcave.net/locations/6",6));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		Scanner kb = new Scanner(System.in);
		int a = 0;
		while(a!=2){
			menu();
			System.out.println("Please enter selection: ");
			a = kb.nextInt();
			while(a!=1 && a!=2){
				menu();
				System.out.println("Input not recognized, please try again: ");
				a = kb.nextInt();
			}
			
			if(a==1){
				System.out.println("");
				print(eggs);
				System.out.println("");
			} else if(a==2){
				System.out.println("");
				print(eggs);
				System.out.println("");
				continue1 = false;
				System.out.println("Thanks for using this.");
				System.out.println("Sorting, then Outputing to file...");
				Collections.sort(eggs, new NameComparator());
				File file = new File("dragons.txt");
				try {
					PrintWriter pw = new PrintWriter(file);
					for(int i = 0; i<eggs.size(); i++){
						pw.println(eggs.get(i));
						pw.flush();
					}
					pw.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		kb.close();
	}
	
	public static void print(List<Egg> a){
		for(int i = 0; i< a.size(); i++){
			System.out.println(a.get(i));
		}
	}
	
	public static boolean getContinue(){ return continue1; }
	
	private static void menu(){
		System.out.println("MENU--------------------");
		System.out.println("1)Print the current List");
		System.out.println("2) Quit the program");
	}
	
	public static List<Egg> getEggList(){ return eggs; }
}

class NameComparator implements Comparator<Egg>{
	@Override
	public int compare(Egg a, Egg b){
		return a.getType().compareToIgnoreCase(b.getType());
	}
}
