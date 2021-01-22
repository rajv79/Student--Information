
package p4vivekraj;


public class LQHASH {
    
   int N;
    int n = 0;			// the number of nodes in the structure
    int defaultQuotient = 9967;  	// the default 4k+3 prime
    double loadingFactor = 0.75;
    StudentListings deleted;		// the dummy node, v2
    private StudentListings[] data; 	// the primary storage array
    int nd  =0;                         // the number of dummy node
    double plf  =0;
    int w  ;  // --------------------------------- the width of the number 
    int nops =0 ;///------------------- number of operation performed
    int naa =0 ;  ///// number od array accces 

    public LQHASH(int length,int nw){
     int pct = (int)((1.0/loadingFactor-1) *100.0);
       w  = nw;
      N = fourKPlus3(length, pct);
      data = new StudentListings[N];
      deleted = new StudentListings("oyoyoy",40,4.0);
      for(int i = 0; i<N; i++)
         data[i] = null;
    }// end of constructor
    
    public boolean insert(StudentListings newListing){
        
       boolean noError;
       boolean hit = false;
       int pass, q, offset, ip;
       int pk = stringToInt(newListing.getname());  // --------------------preprocess the key
       if( (((double) n)/N) >= loadingFactor) {// -----------------------insert the node
           
            boolean s  = expand(fourKPlus3(N*2,33));
            if(s= false){
                return false;
            }
       }
        pass = 0;
         q = pk / N;
         offset = q;
         ip = pk % N;
         if(q%N == 0)
             offset = defaultQuotient;
         nops++;
         while(pass < N){
             naa++;
           if(data[ip] == null || data[ip] == deleted)
            { hit = true;
               break;
            }
            ip = (ip + offset)%N;
            pass = pass +1;
         }                                       //-------------------------------------------------end of the while loop
         if(hit == true){                        //---------------------------- insert the node
             if(data[ip]== deleted)
                 nd--;
             
          data[ip]=newListing.deepcopy( );
            n++;
            
            plf = ((n+nd)/(double)N);
            
            if(plf>0.75){
                StudentListings[]temp = data;
                data = new StudentListings[N];
                for(int i = 0; i<N; i++){
                   if(temp[i]!= null && temp[i] != deleted)
                       insert(temp[i]);
                }
                nd=0;
            
           
                    } //------------------------- end of the  if plf statement
            
              
              
              showStats();
            return noError = true;
            
         }
         
       
         else
            
            return noError = false;
      // }
       
      //else //structure full to loading factor
            
         // return noError = false;
    
    
    
    
    }//-------------------------------------// end for the Insert method
    
    
    
    public StudentListings fetch(String targetKey)
    {  boolean noError;
        boolean hit = false;
        int pass, q, offset, ip;
        int pk = stringToInt(targetKey);  // preprocess the key
        pass = 0;
        q = pk / N;
        offset = q ;
        ip = pk % N;
        if(q%N == 0)
           offset = defaultQuotient;
         nops++; //-------------------------------------- number of operation  loops
        while(pass < N){
            naa++; // ----------------------------------- number of array acceses 
           if(data[ip] == null) //node not in structure
                break;
            if(data[ip].compareTo(targetKey) == 0) //node found
            {  hit = true;
                break;
            }
            ip = (ip + offset)%N;  //collision occurred
            pass = pass +1;
          //  showStats();
         }
        showStats();
         if(hit == true) //return a deep copy of the node
             
            return data[ip].deepcopy( );
         
         else
            return null;
    }                //----------------------------------end of the Fetch method
    
    
    
    
    
    
    
    
    
    

    public boolean delete(String targetKey)
    {  boolean noError;
        boolean hit = false;
        int pass, q, offset, ip;
        int pk = stringToInt(targetKey);  // preprocess the key
        pass = 0;
        q = pk / N;
        offset = q;
        ip = pk % N;
        if(q%N == 0)
            offset = defaultQuotient;
         nops ++; // ------------------------------------------ number of operations  loops 
        while(pass < N){
            naa++; // --------------------------------- number of array acces 
          if(data[ip] == null) //node not in structure
               break;
            if(data[ip].compareTo(targetKey) == 0) // node found
            { hit = true;
               break;
            }
            ip = (ip + offset)%N;  //collision occurred
            pass = pass +1;
         }
      if(hit == true)  //delete the node
      {  data[ip] = deleted;
            nd++;         //// increment 
           n--;
           showStats();
           return noError = true;
      }
      else
        
          return noError = false;
    }//end of the Delete method

    public boolean update(String targetKey, StudentListings newListing)
    {  if(delete(targetKey) == false)
           return false;
       else if(insert(newListing) == false)
           return false;
       return true;
    }//end of the Update method

    public void showAll()
    {  for(int i= 0; i<N; i++)
           if(data[i] != null && data[i] != deleted)
               
              System.out.println( data[i].toString());
    }

    public static int fourKPlus3(int n, int pct)  // from Figure 5.16
    {  boolean fkp3 = false;
        boolean aPrime = false;
        int prime, highDivisor, d;
        double pctd = pct;
        prime = (int)(n * (1.0 + (pctd/100.0)));  // guess the prime pct percent larger than n
        if(prime%2 == 0) //if even make the prime guess odd
        prime = prime +1;
       while(fkp3 == false) // not a 4k+3 prime
       {  while(aPrime == false)  // not a prime
           {  highDivisor = (int)(Math.sqrt(prime) + 0.5);
               for(d = highDivisor; d > 1; d--)
               { if(prime%d == 0)
            break;
               }
               if(d != 1) // prime not found
         prime = prime +2;
              else
         aPrime = true;
        }// end of the prime search loop
        if((prime-3)%4 == 0)
            fkp3 = true;
       else
       {  prime = prime +2;
           aPrime = false;
       }
    }// end of 4k+3 prime search loop
    return prime;
  }
    public static int stringToInt(String aKey)	// from Figure 5.18
    {  int pseudoKey = 0;
        int n = 1;
        int cn= 0;
        char c[] = aKey.toCharArray();
        int grouping =0;
        while (cn < aKey.length()) // there is still more character in the key
         {   grouping = grouping << 8;      // pack next four characters in i
             grouping = grouping + c[cn];
             cn = cn + 1;
             if (n==4 || cn == aKey.length()) // 4 characters are processed or no more characters
             {  pseudoKey = pseudoKey + grouping;
                 n = 0;
                 grouping = 0;
             }
             n = n + 1;
         }
         return Math.abs(pseudoKey);
    }

    private boolean expand (int newSize){
        
        StudentListings []temp =  data;// create a new temp studentlisting
       
        data =  new StudentListings[newSize]; // giving the  new size of data which will follow the  4k+3 rule
        if(data==null){   // check whether the data is empty or not 
            temp = data;// this will help to keep old data safe and doesnt lose 
            return false;
        }
        N = newSize;// this will assign the orignal data to same positon of new size of arra
        for(int i  =0; i<temp.length ;i++){ // this will help to  insert the data to new size array 
                                            // becouse we cant use the SYstem array copy becous they follow the collision
            if(temp[i]!= null && temp[i]!= deleted)       // this deleted = v2 ,which is dummy node when the node get delete from here
                    insert(temp[i]);//  use the insert mthod to insertnode into new nize array 
                
                }
        
        return true;
        
    
}
    
      public  double getPLF(){  // calaculating the pseudo loding factor ,where n = current node , nd = dummy increeamenr node
        
        double pf  = ((n+nd)/(double)N) ;
        
        return Math.round(pf*100.0)/100.0;
    }
    
       public double getCI (){    // Current Loading factor 
        
        double  ci  = ( n/(double)N);
        return  Math.round(ci*100.0)/100.0;
    }
       public double getdensity(){    /// calculating the denisty  of the strcture 
           
          
          double d2 = w/(w+4*((double)N/(double)n));
         
           return Math.round(d2*100.0)/100.0 ;
           
           
           
       }
       
       public double getAvgsl(){   // this will help me to calculate the average search length 
           
           double avgs = ((double)naa/nops);
           return  Math.round(avgs*100.0)/100.0;
           
           
       }
       
        public void showStats() {  // to call all method in showstats methods
           // System.out.println("good work");

        System.out.format("%17s%22s%22s%22s",
                "||Search Length||\t", "||Current Loading Factor||\t",
                "||Pseudo Loading Factor||\t", "||Current Density||\n");
        System.out.format("%11s%29s%30s%32s\n",
                getAvgsl(), getCI(),
                getPLF(), getdensity());

    }
      
    
    
}
    
    


    

