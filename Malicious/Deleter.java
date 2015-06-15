import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;

public class Deleter {
   
   public static void main(String []args) {
   		try {
		    File file = new File("/home");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
		} catch(Exception e){
 
    		e.printStackTrace();
 
    	}

   }
} 
