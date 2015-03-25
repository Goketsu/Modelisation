package main;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
public class SeamCarving
{

   public static int[][] readpgm(String fn)
	 {		
        try {
            InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
            System.out.println(f);
            BufferedReader d = new BufferedReader(new InputStreamReader(f));
            String magic = d.readLine();
            String line = d.readLine();
		   while (line.startsWith("#")) {
			  line = d.readLine();
		   }
		   Scanner s = new Scanner(line);
		   int width = s.nextInt();
		   int height = s.nextInt();		   
		   line = d.readLine();
		   s = new Scanner(line);
		   int maxVal = s.nextInt();
		   int[][] im = new int[height][width];
		   s = new Scanner(d);
		   int count = 0;
		   while (count < height*width) {
			  im[count / width][count % width] = s.nextInt();
			  count++;
		   }
		   return im;
        }
		
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            return null;
        }
    }
   
   public static void writepgm(int[][] image, String filename)// throws FileNotFoundException{
	   {		
	        try {
	        	File file = new File(filename);
	        	if(file.createNewFile()){
	        		System.out.println("file created");
	        	}else{
	        		System.out.println("file already exists");
	        	}
	        	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        	BufferedWriter writer = new BufferedWriter(fw);
	        	//BufferedImage bi = new BufferedImage(200,300,BufferedImage.TYPE_3BYTE_BGR);
	        	//ImageIO.write(bi, "png", file);
	        	
	        	int width = image[0].length;
	    	    int height = image.length;
	    	    
	        	writer.write("P2 \n");
	        	writer.write(width + " " + height+"\n");
	        	writer.write("255 \n");
	        	
	        	// print out the data, limiting the line lengths to 70 characters
	        	int lineLength = 0;
	    	    for (int i = 0; i < height; ++i)
	    	    {
	    	      for (int j = 0; j < width; ++j)
	    	      {
	    	        int value = image[i][j];
	    	        
	    	        // if we are going over 80 characters on a line,
	    	        // start a new line
	    	        String stringValue = "" + value;
	    	        int currentLength = stringValue.length() + 1;
	    	        if (currentLength + lineLength > 80)
	    	        {
	    	          writer.write("\n");;
	    	          lineLength = 0;
	    	        }
	    	        lineLength += currentLength;
	    	        writer.write(value + " ");
	    	      }
	    	    }
	        	
	        	writer.close();
	        	
	        	System.out.println("DONE");
	        }
	        
	        catch(Throwable t) {
	            t.printStackTrace(System.err) ;
	            //return null;
	        }
	        
	   
	  
   }
   
   public static int[][] interest (int[][] image){
	   
	   
	   int width = image[0].length;
	   int height = image.length;
	   int[][] tab = new int[height][width];
	   int intermediaire = 0;
	   for (int i = 0; i < height; ++i)
	   {
		  if(image[i][1] > image[i][0]){
			  tab[i][0] = image[i][1] - image[i][0];
		  }else{
			  tab[i][0] = image[i][0] - image[i][1];
		  }
 	      for (int j = 1; j < width-1; ++j)
 	      {
 	    	  intermediaire = (image[i][j-1] + image[i][j+1]) /2;
 	    	  //System.out.println("intermediraire : "+intermediaire+" "+i+" "+j);
 	    	  if(intermediaire > image[i][j]){
 	    		  tab[i][j] = intermediaire - image[i][j];
 	    	  }else{
 	    		 tab[i][j] = image[i][j] - intermediaire;
 	    	  }
 	      }
 	     if(image[i][width-2] > image[i][width-1]){
			  tab[i][width-1] = image[i][width-2] - image[i][width-1];
		  }else{
			  tab[i][width-1] = image[i][width-1] - image[i][width-2];
		  }
 	   }
	   return tab;
   }
   
   // Useless ...
   public static String toString(int[][] image){
	   StringBuilder sb = new StringBuilder();
	   System.out.println("taille : "+image.length+", "+image[0].length);
	   for(int i = 0; i < image.length; i++){
		   System.out.println("hgfd "+i);
		   for(int j = 0; j < image[0].length; j++){
			   System.out.println("oui "+image[i][j]);
			   sb.append(i+""+image[i][j]);
		   }
	   }
	   System.out.println(sb.toString());
	   return sb.toString();
   }

   public static void main(String[] args){// throws FileNotFoundException
	   int[][] tab = {{3,11,24,39},
	   				  {8,21,29,39},
	   				  {74,80,100,200}};
	   

	   writepgm(readpgm("ex1.pgm"),"ex1_test.pgm");
	   writepgm(interest(tab),"test.pgm");
	   writepgm(interest(readpgm("ex1.pgm")),"ex1_interest.pgm");
   }
}
