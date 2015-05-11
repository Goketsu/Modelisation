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
	        	
	        	System.out.println("Creation du fichier "+filename+" terminée");
	        }
	        
	        catch(Throwable t) {
	            t.printStackTrace(System.err) ;
	            //return null;
	        }
	        
	   
	  
   }
   
   public static void writeppm(RGB[][] image, String filename)// throws FileNotFoundException{
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
    	    
        	writer.write("P3 \n");
        	writer.write(width + " " + height+"\n");
        	writer.write("255 \n");
        	
        	// print out the data, limiting the line lengths to 70 characters
        	int lineLength = 0;
    	    for (int i = 0; i < height; ++i)
    	    {
    	      for (int j = 0; j < width; ++j)
    	      {
    	        int valueR = image[i][j].r;
    	        int valueG = image[i][j].g;
    	        int valueB = image[i][j].b;
    	        
    	        // if we are going over 80 characters on a line,
    	        // start a new line
    	        String stringValue = "" + valueR;
    	        int currentLength = stringValue.length() + 1;
    	        if (currentLength + lineLength > 80)
    	        {
    	          writer.write("\n");;
    	          lineLength = 0;
    	        }
    	        lineLength += currentLength;
    	        writer.write(valueR + " ");
    	        writer.write(valueG + " ");
    	        writer.write(valueB + " ");
    	      }
    	    }
        	
        	writer.close();
        	
        	System.out.println("Creation du fichier "+filename+" terminée");
        }
        
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            //return null;
        }
        
   }
   
   public static RGB[][] readppm(String fn)
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
		   RGB[][] im = new RGB[height][width];
		   s = new Scanner(d);
		   int count = 0;
		   System.out.println(8/2);
		   while (count < height*width) {
			  //im[count / width*3][count % width*3].setR(s.nextInt());
			  //im[count / width*3][count % width*3].setG(s.nextInt());
			  //im[count / width*3][count % width*3].setB(s.nextInt());
			  //System.out.println(count);
			  im[count / width]
					  [count % width] = new RGB(s.nextInt(),s.nextInt(),s.nextInt());
			  count++;
		   }
		   return im;
      }
		
      catch(Throwable t) {
          t.printStackTrace(System.err) ;
          return null;
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
   
   public static int[][] interestPPM (RGB[][] image){
	   
	   int width = image[0].length;
	   int height = image.length;
	   System.out.println("width : "+width);
	   System.out.println("height : "+height);
	   System.out.println(image[0][width-1]);
	   int[][] tabR = new int[height][width];
	   int[][] tabG = new int[height][width];
	   int[][] tabB = new int[height][width];
	   int intermediaireR = 0;
	   int intermediaireG = 0;
	   int intermediaireB = 0;
	   for (int i = 0; i < height; ++i)
	   {
		  if(image[i][1].getR() > image[i][0].getR()){
			  tabR[i][0] = image[i][1].getR() - image[i][0].getR();
		  }else{
			  tabR[i][0] = image[i][0].getR() - image[i][1].getR();
		  }
		  if(image[i][1].getG() > image[i][0].getG()){
			  tabR[i][0] = image[i][1].getG() - image[i][0].getG();
		  }else{
			  tabR[i][0] = image[i][0].getG() - image[i][1].getG();
		  }
		  if(image[i][1].getB() > image[i][0].getB()){
			  tabR[i][0] = image[i][1].getB() - image[i][0].getB();
		  }else{
			  tabR[i][0] = image[i][0].getB() - image[i][1].getB();
		  }
 	      for (int j = 1; j < width-1; ++j)
 	      {
 	    	  //System.out.println(j);
 	    	  //System.out.println(image[i][j+1].getR());
 	    	  intermediaireR = 
 	    			  (image[i][j-1].getR() 
 	    					  + image[i][j+1].getR()) /2;
 	    	  intermediaireG = (image[i][j-1].getG() + image[i][j+1].getG()) /2;
 	    	  intermediaireB = (image[i][j-1].getB() + image[i][j+1].getB()) /2;
 	    	  //System.out.println("intermediraire : "+intermediaire+" "+i+" "+j);
 	    	  if(intermediaireR > image[i][j].getR()){
 	    		  tabR[i][j] = intermediaireR - image[i][j].getR();
 	    	  }else{
 	    		 tabR[i][j] = image[i][j].getR() - intermediaireR;
 	    	  }
 	    	  if(intermediaireG > image[i][j].getG()){
 	    		  tabR[i][j] = intermediaireG - image[i][j].getG();
 	    	  }else{
 	    		 tabR[i][j] = image[i][j].getG() - intermediaireG;
 	    	  }
 	    	  if(intermediaireB > image[i][j].getB()){
 	    		  tabR[i][j] = intermediaireB - image[i][j].getB();
 	    	  }else{
 	    		 tabR[i][j] = image[i][j].getB() - intermediaireB;
 	    	  }
 	      }
 	      if(image[i][width-2].getR() > image[i][width-1].getR()){
			  tabR[i][width-1] = image[i][width-2].getR() - image[i][width-1].getR();
		  }else{
			  tabR[i][width-1] = image[i][width-1].getR() - image[i][width-2].getR();
		  }
 	      if(image[i][width-2].getG() > image[i][width-1].getG()){
			  tabG[i][width-1] = image[i][width-2].getG() - image[i][width-1].getG();
		  }else{
			  tabG[i][width-1] = image[i][width-1].getG() - image[i][width-2].getG();
		  }
 	      if(image[i][width-2].getB() > image[i][width-1].getB()){
			  tabB[i][width-1] = image[i][width-2].getB() - image[i][width-1].getB();
		  }else{
			  tabB[i][width-1] = image[i][width-1].getB() - image[i][width-2].getB();
		  }
 	   }
	   int[][] resultat = new int[height][width];
	   for (int i = 0; i < height; ++i)
		   for (int j = 1; j < width-1; ++j)
			   resultat[i][j] = tabR[i][j] + tabG[i][j] + tabB[i][j];
	   return resultat;
   }
   
   public static int[][] pixelsToDelete(int[][] tab,int x,int y,int width,int height){
	   
	   int tabWidth = tab[0].length;
	   int tabHeight = tab.length;
	   
	   int cpt1 = 0;
	   int cpt2 = 0;

	   for(int i = 0;i < tabHeight; i++){
		   for(int j = 0; j < tabWidth; j++){
			   if(i == 50 && j == 100){
				   System.out.println(i <= x+height);
				   System.out.println(j <= y+width);
				   System.out.println("x "+x);
				   System.out.println("j "+j);
				   System.out.println(j >= x);
				   System.out.println(i >= y);
			   }
			   tab[i][j] = tab[i][j]+10;
			   if(j >= x && i >= y && i < y+height && j < x+width){
				   tab[i][j] = 0;
				   cpt1++;
				   //System.out.println("fdp "+tab[i][j]);
			   }
			   /*else{
				   tab[i][j] = tab[i][j]+10;
				   cpt2++;
			   }*/
		   }
	   }
	   
	   System.out.println("nombre de 0 "+cpt1);
	   System.out.println("nombre de +1 "+cpt2);
	   System.out.println("ntm "+tab[0][0]);
	   return tab;
   }
   
   public static int[][] pixelsToKeep(int[][] tab,int x,int y,int width,int height){
	   
	   int tabWidth = tab[0].length;
	   int tabHeight = tab.length;

	   for(int i = 0;i < tabHeight; i++){
		   for(int j = 0; j < tabWidth; j++){
			   if(j >= x && i >= y && i < y+height && j < x+width){
				   tab[i][j] = Integer.MAX_VALUE;
			   }
			   /*else{
				   tab[i][j] = tab[i][j];
			   }*/
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

   
   public static void main(String[] args){
	   RGB[][] tab = {{new RGB(255,255,255),new RGB(0,0,0)},
	          	  	  {new RGB(255,0,0),new RGB(255,255,0)},
	          	  	  {new RGB(0,255,0),new RGB(0,255,255)},
	          	  	  {new RGB(0,0,255),new RGB(255,0,255)}};
	   
	   RGB[][] image = readppm("len_top.ppm");
	   int[][] interest = interestPPM(image);
	   writepgm(interest,"interet_len.pgm");
	   writeppm(image,"len_test.ppm");
   }
}
