import java.util.*;

public class test2 {
	// First Fit
		 static void firstFit( ArrayList<Integer> partitions,int process[] ,int n , int m){
			// Stores block id of the
		        // block allocated to a process
		        ArrayList<Integer> allocation = new ArrayList<Integer>();
		        
		        for (int i = 0; i < n; i++)	// filling all elements with -1 (which mean empty and no process are assigned to t)
		            allocation.add(-1);
		        
		       int sum = 0;
		       for(int i = 0 ; i < n ; i++){							// n--> number of process 
		    	   for(int j = 0 ; j < partitions.size() ; j++){		// m --> number of partitions
		    		   if(partitions.get(j) >= process[i]){
		    			   allocation.set(i, j);						// number of partition occupied 
		    			   												// Reduce available memory in this partition.
		    			   
		    			   if(j == m){
			    			   int diff = partitions.get(j)-process[i];
		    				   partitions.set(j, 0);
		    				   partitions.add(diff);
		    				   sum = sum + diff;
		    				   break;
		    			   }
		    			   else{
			    			int diff = partitions.get(j)-process[i];
		    				partitions.set(j,0);
		    			   	partitions.add(diff);
		    			   	sum = sum + diff;
		    			   	break;							// 0,0,8,4,10
		    			   }
		    		   }
		    	   }
		       }
		       
		       
		       System.out.println("\nID\tProcess Size\tPartition Size\tPartition.no ");
		       for(int i = 0 ; i < n ; i++){
		    	   System.out.print(" " + (i+1) + "\t" +
	                       "\t" + process[i] + "\t" + "\t" + partitions.get(i) + "\t");
		    	   if (allocation.get(i) != -1)
		                System.out.print(allocation.get(i)  );
		            else
		                System.out.print("Not Allocated");
		            System.out.println();
		       }
		       
		       int k;
			 	Scanner sc3 = new Scanner(System.in);
			 	System.out.println("Do you want to compact? \n1.yes\n2.no");
			 	k = sc3.nextInt();
			 	if(k == 1){
	 				System.out.println("\nProcess" + "\t\t" + "Partition no. ");
			 		for(int i = 0 ; i < n ; i++){
			 			if(allocation.get(i) != -1){
			 				System.out.print("\n " + process[i] +  "\t\t" + allocation.get(i));
			 			}
			 		}
			 		partitions.add(sum);
			 		int last = partitions.size();
			 		System.out.println("\nPartition " + last + " = " + sum + "kb" +" -->" +"External Fragment");
			 	}
			 	else if(k == 2){
			 		System.exit(0);
			 	}

		 }
		 
		 //Bestfit
		 static void bestFit(ArrayList<Integer> partitions,int process[] ,int n , int m){

		        ArrayList<Integer> allocation = new ArrayList<Integer>();
		        
		        for (int i = 0; i < n; i++)
		            allocation.add(-1);
		        
    			int sum = 0;

		        for(int i = 0 ; i < n ; i++){
		        	int min = -1;
		        	for(int j = 0 ; j < m ; j++){
		        		if(process[i] <= partitions.get(j)){
		        			if(min == -1){
		        				min = j;
		        			}
		        			else if (partitions.get(min) > partitions.get(j)){
		        				min = j;
		        			}
		        		}
		        	}
		        	if(min != -1){
		        		allocation.set(i, min);
		        		int diff = partitions.get(min)-process[i];
		        		//partitions[min] -= process[i];
		        		partitions.set(min,0);
		    			partitions.add(diff);
		    			sum = sum + diff;
		        		
		        	}
		        }			// 5 
		        
		        System.out.println("\nID\tProcess Size\tPartition Size\tPartition.no ");
			       for(int i = 0 ; i < n ; i++){
			    	   System.out.print(" " + (i+1) + "\t" +
		                       "\t" + process[i] + "\t"  + "\t" + partitions.get(i) + "\t\t");
			    	   if (allocation.get(i) != -1)
			                System.out.print(allocation.get(i));
			            else
			                System.out.print("Not Allocated");
			            System.out.println();
			       }
			       
			       int k;
				 	Scanner sc3 = new Scanner(System.in);
				 	System.out.println("Do you want to compact? \n1.yes\n2.no");
				 	k = sc3.nextInt();
				 	if(k == 1){
		 				System.out.println("\nProcess" + "\t\t" + "Partition no. ");
				 		for(int i = 0 ; i < n ; i++){
				 			if(allocation.get(i) != -1){
				 				System.out.print("\n " + process[i] +  "\t\t" + allocation.get(i));
				 			}
				 		}
				 		partitions.add(sum);
				 		int last = partitions.size()-1;
				 		System.out.println("\nPartition " + last + " = " + sum + "kb" +" -->" +"External Fragment");
				 	}
				 	else if(k == 2){
				 		System.exit(0);
				 	}

		 }
		 
		 
		 //Worst fit
		 
		 static void worstFit(ArrayList<Integer> partitions,int process[] ,int n , int m){

			 ArrayList<Integer> allocation = new ArrayList<Integer>();
		        
		        for (int i = 0; i < n; i++)
		            allocation.add(-1);
		        
		        int sum = 0;
		        for(int i = 0 ; i < n ; i++){
		        	int max = -1;
		        	for(int j = 0 ; j < partitions.size() ; j++){
		        		if(process[i] <= partitions.get(j)){
		        			if(max == -1){								// to find the maximum partition size
		        				max = j;
		        			}
		        			else if (partitions.get(max) < partitions.get(j)){
		        				max = j;
		        			}
		        		}
		        	}
		        	if(max != -1){								// to allocate the maximum partition size with process
		        		allocation.set(i,max);
		        		int diff = partitions.get(max)-process[i];
		        		partitions.set(max,0);
		        		partitions.add(diff);
		        		sum = sum + diff;
		        	}
		        }
		        
		        
		        System.out.println("\nID\tProcess Size\tPartition Size\tPartition.no ");
			       for(int i = 0 ; i < n ; i++){
			    	   System.out.print(" " + (i+1) + "\t" +
		                        "\t" + process[i] + "\t"+ "\t" + partitions.get(i) + "\t\t");
			    	   if (allocation.get(i) != -1)
			                System.out.print(allocation.get(i));
			            else
			                System.out.print("Not Allocated");
			            System.out.println();
			       }
		 
			       int k;
				 	Scanner sc3 = new Scanner(System.in);
				 	System.out.println("Do you want to compact? \n1.yes\n2.no");
				 	k = sc3.nextInt();
				 	if(k == 1){
		 				System.out.println("\nProcess" + "\t\t" + "Partition no. ");
				 		for(int i = 0 ; i < n ; i++){
				 			if(allocation.get(i) != -1){
				 				System.out.print("\n " + process[i] +  "\t\t" + allocation.get(i));
				 			}
				 		}
				 		partitions.add(sum);
				 		int last = partitions.size()-1;
				 		System.out.println("\nPartition " + last + " = " + sum + "kb" +" -->" +"External Fragment");
				 	}
				 	else if(k == 2){
				 		System.exit(0);
				 	}
				 	
			       
		 }
		 
		 
		 public static void main(String[] args)
		    {
			 	int m,n,s;
			 	Scanner sc=new Scanner(System.in);  						// Partitions Array
			 	System.out.println("Enter the number of partitions: ");  
			 	m=sc.nextInt();  
			 	ArrayList<Integer> partitions= new ArrayList<Integer>();
			 	System.out.println("Enter the size of each partitions: ");  
		        for(int i = 0 ; i < m ; i++){
				 	System.out.print("Partition"+i+ " ");  
		        	partitions.add(sc.nextInt());
		        }
		        
		        Scanner sc1=new Scanner(System.in);  							// Process Array
			 	System.out.println("Enter the number of process requests: ");  
			 	n=sc1.nextInt();  
		        int process[] = new int[n];
		        for(int i = 0 ; i < n ; i++){
				 	System.out.print("Process"+i+ " ");  
		        	process[i]=sc1.nextInt();
		        }
		        
		        Scanner sc2 = new Scanner(System.in);
			 	System.out.println("Select the policy you want to apply: \n1.First fit\n2.BestFit\n3.WorstFit");  
			 	s = sc2.nextInt();
			 	
			 	if(s==1){
			 		firstFit(partitions,process,n,m);
			 	}		// 1 , 5 , 1 , n/a					// (0) 75 20 5 30 (0) 30 80
			 	else if(s==2){
			 		bestFit(partitions,process,n,m);
			 	} 	   // 2 , 1 , 4 , 5
			 	else if(s==3){
			 		worstFit(partitions,process,n,m);
			 	}     // 5,7,1,n/a
			 	
		    }
		 
}
