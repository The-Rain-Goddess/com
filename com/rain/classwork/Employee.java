package com.rain.classwork;

public class Employee {
	private String name, department, pos;
	private int id;

	public Employee() {
		
	}
	
	public Employee(String name, int id, String department, String pos){
		this.id = id;
		this.name = name;
		this.department = department;
		this.pos = pos;
	}
	
	@Override
	public String toString(){
		String out = "";
		out = out + name;
		for(int i = name.length(); i<20; i++){
			out = out + " ";
		}
		out = out + " " + id + "     ";
		out = out + " " + department;
		for(int i = department.length(); i<20; i++){
			out = out + " ";
		}
		out = out + " " + pos;
		for(int i = pos.length(); i<20; i++){
			out = out + " ";
		}
		
		return out;
	}

}
