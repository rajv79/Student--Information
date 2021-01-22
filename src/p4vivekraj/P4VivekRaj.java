


package p4vivekraj;

import javax.swing.JOptionPane;

public class P4VivekRaj {

    public static void main(String[] args) {
       
         int choice;
        StudentListings slisting = new StudentListings() ;
        String name;
       
        
        String s =  JOptionPane.showInputDialog("Enter the Maximum number of student you want ");
        int n = Integer.parseInt(s);
        
        String s2 = JOptionPane.showInputDialog("Whats is the node width of StudentListing ");
        int n2 = Integer.parseInt(s2);
        
        String s3 =  JOptionPane.showInputDialog("Whats is the Initial  number of  Student you want to Start with");
        int n1 = Integer.parseInt(s3);
        
        LQHASH SNYC = new LQHASH(n,n2);
        
        for(int i =0;i<n1;i++){
            slisting.input();
            SNYC.insert(slisting );
            
        }
       // //STARTING PART OF THE WHILE LOOP///
        String s4 = JOptionPane.showInputDialog(null,"Press (1) to insert a new students information" + "\n Press (2) to fetch and output the students information"
                                    +"\n press (3) to delete a students information " +"\n press (4) update student information  "+"\n press (5) to show all information"+"\n press (6) to exist program" );
        
      choice = Integer.parseInt(s4);
        
        while (choice !=6){
            
            switch(choice){
                
                case 1 : 
                   
                    slisting= new StudentListings();
                   slisting.input();
                   
                   if(SNYC.insert(slisting))
                       
                       JOptionPane.showMessageDialog(null,"operation complete");
                   else
                      JOptionPane.showMessageDialog(null,slisting.getname() + " is already in the list.");
                   
                    break;
                   
                   case 2:
                         name = JOptionPane.showInputDialog("Enter a name to fetch");
                   slisting = SNYC.fetch(name);
                   if(slisting == null){
                       JOptionPane.showMessageDialog(null,"node not in structure");
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null,slisting.toString());
                       JOptionPane.showMessageDialog(null,"operation complete");
                   }
                   break;
                   
                   
                   case 3 :
                        name = JOptionPane.showInputDialog("Enter a name to delete");
                   if(SNYC.delete(name)){
                       JOptionPane.showMessageDialog(null,"operation complete");
                   }
                   else{
                      JOptionPane.showMessageDialog(null,"node not in structure");
                   }
                   break;
                   
                   
                   case 4:
                       slisting = new StudentListings();
                   slisting.input();
                   if(SNYC.update(slisting.getname(), slisting)){
                       JOptionPane.showMessageDialog(null,"operation complete");
                   }
                   else
                       JOptionPane.showMessageDialog(null,"node not in structure");
                   break;
                   
                   
                   case 5:
                       SNYC.showAll();
                   JOptionPane.showMessageDialog(null,"operation complete");
                   break;
                   
                   
                   case 6:
                       System.out.println("Thank you");
                   break;
                   
               default:
                   JOptionPane.showMessageDialog(null,"Invalid choice. Please try again!!");    
            }
            //RELOADING THE LOOP WITH SAME OPTION////
             s4 = JOptionPane.showInputDialog(null,"Press (1) to insert a new students information" + "\n Press (2) to fetch and output the students information"
                                    +"\n press (3) to delete a students information " +"\n press (4) update student information  "+"\n press (5) to show all information"+"\n press (6) to exist program" );
        
             choice = Integer.parseInt(s4);
          
        }
        
     
}
        
    }
    
