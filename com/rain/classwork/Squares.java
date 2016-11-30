package com.rain.classwork;

public class Squares {

	public Squares(int a) {
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				System.out.print("* ");
			} System.out.print("\n");
		}
	}
	
	public Squares(){
		for(int i = 0; i<10; i++){
			for(int j =0; j<10; j++){
				if(j>=i)
					System.out.print("* ");
			} System.out.print("\n");
		}
	}
	
	public Squares(double d){
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				if(i>=j)
					System.out.print("  ");
			}
			for(int j = 9; j>-1; j--){
				if(j>=i)
					System.out.print("* ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args){
	}

}
