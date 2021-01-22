
package p4vivekraj;

import javax.swing.JOptionPane;

public class StudentListings {
    
        private String name;
	private int Id;
	private double gpa;
	
	public StudentListings() {
            this.name ="";
            this.Id = 0;
            this.gpa =0.0;
            
		
	}
	public StudentListings(String name,int Id,double gpa) {
		this.name= name;
		this.Id = Id;
		this.gpa= gpa;
		
		
	}
	
	  public String toString() {
		return ("Name of Student is :-" +name +"\n Student Id is :- " +Id +"\n Gpa of Student is :- " + gpa);
	
	}
	  
	  public StudentListings deepcopy() {
		  StudentListings clone = new StudentListings (name,Id,gpa);
		  return clone;
		  
	  }
	  
	  public void input() {
		  
		 this. name = JOptionPane.showInputDialog(null,"Enter a Name ");
		 this.Id = Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
		  
		 this.gpa = Double.parseDouble(JOptionPane.showInputDialog("Enter your your Gpa"));
		  
	  }
	  public int compareTo(String targetKey) {
		  return (name.compareTo(targetKey) );
	  }
	 public String getname(){
             return name;
         }
          
	 
	  
}
