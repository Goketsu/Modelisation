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
	        	BufferedImage bi = new BufferedImage(200,300,BufferedImage.TYPE_3BYTE_BGR);
	        	ImageIO.write(bi, "png", file);
	        	//writer.write("Here is a test");
	        	//writer.close();
	        	
	        	System.out.println("DONE");
	        }
	        
	        catch(Throwable t) {
	            t.printStackTrace(System.err) ;
	            //return null;
	        }
	   /*
	    * Repris d'internet mais fonctionne bizarement et genere pas un fichier .pgm
	    * mais un fichier avec un tableau
	    * 
	    * PrintWriter pw = new PrintWriter(filename);
	    int width = image[0].length;
	    int height = image.length;
	    
	    // magic number, width, height, and maxval
	    pw.println("P2");
	    pw.println(width + " " + height);
	    pw.println(255);
	    
	    // print out the data, limiting the line lengths to 70 characters
	    int lineLength = 0;
	    for (int i = 0; i < height; ++i)
	    {
	      for (int j = 0; j < width; ++j)
	      {
	        int value = image[i][j];
	        
	        // if we are going over 70 characters on a line,
	        // start a new line
	        String stringValue = "" + value;
	        int currentLength = stringValue.length() + 1;
	        if (currentLength + lineLength > 70)
	        {
	          pw.println();
	          lineLength = 0;
	        }
	        lineLength += currentLength;
	        pw.print(value + " ");
	      }
	    }
	    pw.close();
	    */
   }
   
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

   public static void main(String[] args)// throws FileNotFoundException
	 {
		writepgm(readpgm("ex1.pgm"),"test.pgm");
	 }
}
