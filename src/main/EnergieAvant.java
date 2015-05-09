package main;

import java.util.Scanner;

public class EnergieAvant {
	
	public static int[][] interestRight(int[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tab = new int[height][width];
		
		
		
		for(int i = 0;i < height;i++){
			tab[i][0] = image[i][1];
			for(int j = 1; j < width-1; j++){
				if(image[i][j-1] > image[i][j+1]){
					tab[i][j] = image[i][j-1]-image[i][j+1];
				}else{
					tab[i][j] = image[i][j+1]-image[i][j-1];
				}
			}
			tab[i][width-1] = image[i][width-2];
		}
		return tab;
	}
	
	public static int[][] interestRightPPM(RGB[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tabR = new int[height][width];
		int[][] tabG = new int[height][width];
		int[][] tabB = new int[height][width];
		
		for(int i = 0;i < height;i++){
			tabR[i][0] = image[i][1].getR();
			tabG[i][0] = image[i][1].getG();
			tabB[i][0] = image[i][1].getB();
			for(int j = 1; j < width-1; j++){
				if(image[i][j-1].getR() > image[i][j+1].getR()){
					tabR[i][j] = image[i][j-1].getR()-image[i][j+1].getR();
				}else{
					tabR[i][j] = image[i][j+1].getR()-image[i][j-1].getR();
				}
				if(image[i][j-1].getG() > image[i][j+1].getG()){
					tabG[i][j] = image[i][j-1].getG()-image[i][j+1].getG();
				}else{
					tabG[i][j] = image[i][j+1].getG()-image[i][j-1].getG();
				}
				if(image[i][j-1].getB() > image[i][j+1].getB()){
					tabB[i][j] = image[i][j-1].getB()-image[i][j+1].getB();
				}else{
					tabB[i][j] = image[i][j+1].getB()-image[i][j-1].getB();
				}
			}
			tabR[i][width-1] = image[i][width-2].getR();
			tabG[i][width-1] = image[i][width-2].getG();
			tabB[i][width-1] = image[i][width-2].getB();
		}
		int[][] resultat = new int[height][width];
		for (int i = 0; i < height; ++i)
			for (int j = 1; j < width-1; ++j)
				resultat[i][j] = tabR[i][j] + tabG[i][j] + tabB[i][j];
		return resultat;
		
	}
	
	public static int[][] interestDown(int[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tab = new int[height-1][width];
		
		
		for(int i = 0;i < height-1;i++){
			tab[i][0] = image[i+1][0];
			for(int j = 1; j < width; j++){	
				if(image[i+1][j] > image[i][j-1]){
					tab[i][j] = image[i+1][j]-image[i][j-1];
				}else{
					tab[i][j] = image[i][j-1]-image[i+1][j];
				}
				
			}
		}
		return tab;
	}
	
	public static int[][] interestDownPPM(RGB[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tabR = new int[height][width];
		int[][] tabG = new int[height][width];
		int[][] tabB = new int[height][width];
		
		
		for(int i = 0;i < height-1;i++){
			tabR[i][0] = image[i+1][0].getR();
			tabG[i][0] = image[i+1][0].getG();
			tabB[i][0] = image[i+1][0].getB();
			for(int j = 1; j < width; j++){	
				if(image[i+1][j].getR() > image[i][j-1].getR()){
					tabR[i][j] = image[i+1][j].getR()-image[i][j-1].getR();
				}else{
					tabR[i][j] = image[i][j-1].getR()-image[i+1][j].getR();
				}	
				if(image[i+1][j].getG() > image[i][j-1].getG()){
					tabG[i][j] = image[i+1][j].getG()-image[i][j-1].getG();
				}else{
					tabG[i][j] = image[i][j-1].getG()-image[i+1][j].getG();
				}	
				if(image[i+1][j].getB() > image[i][j-1].getB()){
					tabB[i][j] = image[i+1][j].getB()-image[i][j-1].getB();
				}else{
					tabB[i][j] = image[i][j-1].getB()-image[i+1][j].getB();
				}
				
			}
		}
		int[][] resultat = new int[height][width];
		for (int i = 0; i < height; ++i)
			for (int j = 1; j < width-1; ++j)
				resultat[i][j] = tabR[i][j] + tabG[i][j] + tabB[i][j];
		return resultat;
	}
	
	public static int[][] interestUp(int[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tab = new int[height-1][width];
		
		
		for(int i = 0;i < height-1;i++){
			tab[i][0] = image[i][0];
			for(int j = 1; j < width; j++){
				if(image[i][j] > image[i+1][j-1]){
					tab[i][j] = image[i][j]-image[i+1][j-1];
				}else{
					tab[i][j] = image[i+1][j-1]-image[i][j];
				}
			}	
		}
		return tab;
	}
	
	public static int[][] interestUpPPM(RGB[][] image){
		
		int width = image[0].length;
		int height = image.length;
		int[][] tabR = new int[height][width];
		int[][] tabG = new int[height][width];
		int[][] tabB = new int[height][width];
		
		
		for(int i = 0;i < height-1;i++){
			tabR[i][0] = image[i][0].getR();
			tabG[i][0] = image[i][0].getG();
			tabB[i][0] = image[i][0].getB();
			for(int j = 1; j < width; j++){
				if(image[i][j].getR() > image[i+1][j-1].getR()){
					tabR[i][j] = image[i][j].getR()-image[i+1][j-1].getR();
				}else{
					tabR[i][j] = image[i+1][j-1].getR()-image[i][j].getR();
				}
				if(image[i][j].getG() > image[i+1][j-1].getG()){
					tabG[i][j] = image[i][j].getG()-image[i+1][j-1].getG();
				}else{
					tabG[i][j] = image[i+1][j-1].getG()-image[i][j].getG();
				}
				if(image[i][j].getB() > image[i+1][j-1].getB()){
					tabB[i][j] = image[i][j].getB()-image[i+1][j-1].getB();
				}else{
					tabB[i][j] = image[i+1][j-1].getB()-image[i][j].getB();
				}
			}	
		}
		int[][] resultat = new int[height][width];
		for (int i = 0; i < height; ++i)
			for (int j = 1; j < width-1; ++j)
				resultat[i][j] = tabR[i][j] + tabG[i][j] + tabB[i][j];
		return resultat;
	}
	
	public static Graph toGraph(int[][] itrRight,int[][] itrDown,int[][] itrUp){
		
		int width = itrRight[0].length;
		int height = itrRight.length;
		int i,j;
		
		//System.out.println("largeur : "+width);
		//System.out.println("hauteur : "+height);
		
		//Graph g = new Graph(width*height+2);
		//int[][] inter = SeamCarving.interest(itr);
		/*
		for(i = 0 ; i < height; i++){
			g.addEdge(new Edge(0,i+1,-1,0));
		}
		*/
		Graph g = new Graph(itrRight.length*itrRight[0].length+2);
		//System.out.println("taille : "+width*height+2);
		int nb= 0;
		for (i = 0; i < width-1; i++){
			for (j = 0; j < height ; j++){
					int oriArc = i * height +j +1;
					int destArc = oriArc +height;
					//System.out.println("i = " + i + ", j = " + j);
					//System.out.println("origine     : " + oriArc);
					//System.out.println("destination : " + destArc);
					
					
					
					//System.out.println("arc : " + oriArc + " -> " + destArc);
					g.addEdge(new Edge(oriArc, destArc, itrRight[j][i],0));nb++;
				//g.addEdge(new Edge(width*i+j+1-i, height*(i+1)+j+1, inter[j][i],0));
				
				if(j>0){
					//System.out.println("arc : " + (destArc -1) + " -> " + oriArc);
					g.addEdge(new Edge(destArc -1, oriArc, -1,0));nb++;
				}
				
				
					
				//System.out.println("arc : " + destArc + " -> " + oriArc);
				g.addEdge(new Edge(destArc, oriArc, -1,0));nb++;
				
				if(j!=height-1){
					//System.out.println("arc : " + (destArc +1) + " -> " + oriArc);
					g.addEdge(new Edge(destArc +1, oriArc, -1,0));
					nb++;
				}
				
				//}
			}
		}
		//System.out.println("nb : " + nb);
		//System.out.println("\n");
		
		for(i = 1 ; i <= height; i++){
			//System.out.println("arc : " + 0 + " -> " + i);
			g.addEdge(new Edge(0, i,-1,0));
		}
		for (i = 0; i < width; i++){
			for (j = 0; j < height-1 ; j++){
					int oriArc = i * height +j +1;
					int destArc = oriArc +height;
					g.addEdge(new Edge(oriArc , oriArc+1, itrDown[j][i],0));nb++;
					g.addEdge(new Edge(oriArc +1, oriArc, itrUp[j][i],0));nb++;
			}
		}
		
		for(i = 0 ; i < height; i++){
			//System.out.println("arc : " + ((height*width -height +1 +i)) + " -> " +(width*height+1));
			g.addEdge(new Edge((height*width -height +1 +i),width*height+1,itrRight[i][width-1],0));
		}
		
		return g;
	}
	
	public static void cutColumnPGM(String fichier, int nb){
		int[][] image = SeamCarving.readpgm(fichier);
		
		int[][] itrR = interestRight(image);
		int[][] itrD = interestDown(image);
		int[][] itrU = interestUp(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("ITERATION "+(i+1));
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrD = interestDown(res);
			itrU = interestUp(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			//g3 = g3.toGraph(SeamCarving.interest(res));
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		
		SeamCarving.writepgm(res,"test_cut.pgm");
		//return image;
	}
	
	public static void cutColumnPPM(String fichier, int nb){
		RGB[][] image = SeamCarving.readppm(fichier);
		
		int[][] itrR = interestRightPPM(image);
		int[][] itrD = interestDownPPM(image);
		int[][] itrU = interestUpPPM(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("ITERATION "+(i+1));
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrD = interestDownPPM(res);
			itrU = interestUpPPM(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			//g3 = g3.toGraph(SeamCarving.interest(res));
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
			//g2 = new Graph(res.length*res[0].length+2);
			//g2 = g2.toGraph(res);
		}
		
		SeamCarving.writeppm(res,"test_cut.ppm");
		//return image;
	}
	
	public static void main(String[] args){
		int[][] tab =  {{3,11,24,39},
			  	  		{8,21,29,39},
			  	  		{74,80,100,200},
						};
		
		Scanner sc = new Scanner(System.in);
	    System.out.println("Combien de colonne voulez-vous supprimer ?");
	    int nb = sc.nextInt();
	    System.out.println("suppression de "+nb+" colonnes");
	    cutColumnPPM("len_top.ppm", nb);
		
	    /*
		int[][] image = SeamCarving.readpgm("ex1.pgm");
		
		int[][] itrR = interestRight(image);
		int[][] itrD = interestDown(image);
		int[][] itrU = interestUp(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		g.writeFile("energie_graph");
		
		Test.visite = new boolean[image.length*image[0].length+2];
		Test.flotMax(g);
		   
		   //g.writeFile("test2_ex1");
		   
		int[][] res = Test.cut(g,image);
		Graph g2 = new Graph(res.length*res[0].length+2);
		g2 = g2.toGraph(res);
		   
		   
		   
		for(int i = 0;i < nb;i++){
			System.out.println("ITERATION "+i+1);
			Graph g3 = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrD = interestDown(res);
			itrU = interestUp(res);
			g3 = EnergieAvant.toGraph(itrR,itrD,itrU);
			//g3 = g3.toGraph(SeamCarving.interest(res));
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g3);
			res = Test.cut(g3,res);
			g3 = new Graph(res.length*res[0].length+2);
			g3 = g3.toGraph(res);
		}
		
		SeamCarving.writepgm(res,"test_cut.pgm");
		   
		   
		   
		//g.writeFile("test_graph");
		SeamCarving.writepgm(image, "test.pgm");
		   
		System.out.println("termine ");
		
		/*
		for(int i = 0;i < itrR.length; i++){
			for(int j = 0; j < itrR[0].length; j++){
				System.out.println(itrR[i][j]);
			}
		}
		*/
	}

}
