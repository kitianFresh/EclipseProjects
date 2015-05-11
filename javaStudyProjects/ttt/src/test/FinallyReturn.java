package test;

public class FinallyReturn {
	  public static void main(String[] args) {  
		          System.err.println(new FinallyReturn().method1());  
		          System.err.println(new FinallyReturn().method2());  
		      }  
		        
		      public int method1() {  
		          int x = 1;  
		          try {  
		              //return x;  
		          } finally {  
		              ++x;  
		          }
		          
		          return x;
		      }  
		        
		      public int method2() {  
		          int x = 1;  
		          try {  
		              return x;  
		          } finally {  
		              return ++x;  
		          }  
		      }  

}
