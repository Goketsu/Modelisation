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
		
		Graph g = new Graph(itrRight.length*itrRight[0].length+2);
		int nb= 0;
		for (i = 0; i < width-1; i++){
			for (j = 0; j < height ; j++){
					int oriArc = i * height +j +1;
					int destArc = oriArc +height;
					
					g.addEdge(new Edge(oriArc, destArc, itrRight[j][i],0));nb++;
				
				if(j>0){
					g.addEdge(new Edge(destArc -1, oriArc, -1,0));nb++;
				}
				
				g.addEdge(new Edge(destArc, oriArc, -1,0));nb++;
				
				if(j!=height-1){
					g.addEdge(new Edge(destArc +1, oriArc, -1,0));
					nb++;
				}
			}
		}
		
		for(i = 1 ; i <= height; i++){
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
			g.addEdge(new Edge((height*width -height +1 +i),width*height+1,itrRight[i][width-1],0));
		}
		
		return g;
	}
	
	public static void cutColumnPGM(String fichier, int nb){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRight(image);
		int[][] itrD = interestDown(image);
		int[][] itrU = interestUp(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrD = interestDown(res);
			itrU = interestUp(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		
		SeamCarving.writepgm(res,"energie_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixDelCutColumnPGM(String fichier, int nb,int x, int y, int width, int height){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRight(image);
		itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
		int[][] itrD = interestDown(image);
		itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
		int[][] itrU = interestUp(image);
		itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
			itrD = interestDown(res);
			itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
			itrU = interestUp(res);
			itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		
		SeamCarving.writepgm(res,"energie_Del_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixKeepCutColumnPGM(String fichier, int nb,int x, int y, int width, int height){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRight(image);
		itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
		int[][] itrD = interestDown(image);
		itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
		int[][] itrU = interestUp(image);
		itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
			itrD = interestDown(res);
			itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
			itrU = interestUp(res);
			itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		
		SeamCarving.writepgm(res,"energie_Keep_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void cutLinePGM(String fichier, int nb){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		image = Test.rotateLeftPGM(image);
		int[][] itrR = interestRight(image);
		int[][] itrD = interestDown(image);
		int[][] itrU = interestUp(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrD = interestDown(res);
			itrU = interestUp(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		res = Test.rotateRightPGM(res);
		SeamCarving.writepgm(res,"energie_cutLine_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixDelCutLinePGM(String fichier, int nb,int x, int y, int width, int height){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		image = Test.rotateLeftPGM(image);
		int[][] itrR = interestRight(image);
		itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
		int[][] itrD = interestDown(image);
		itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
		int[][] itrU = interestUp(image);
		itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
			itrD = interestDown(res);
			itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
			itrU = interestUp(res);
			itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		res = Test.rotateRightPGM(res);
		SeamCarving.writepgm(res,"energie_Del_cutLine_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixKeepCutLinePGM(String fichier, int nb,int x, int y, int width, int height){
		int[][] image = SeamCarving.readpgm(fichier);
		SeamCarving.writepgm(image,"energie_before_"+fichier);
		
		image = Test.rotateLeftPGM(image);
		int[][] itrR = interestRight(image);
		itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
		int[][] itrD = interestDown(image);
		itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
		int[][] itrU = interestUp(image);
		itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		int[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRight(res);
			itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
			itrD = interestDown(res);
			itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
			itrU = interestUp(res);
			itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cut(g,res);
			g2 = new Graph(res.length*res[0].length+2);
			g2 = g2.toGraph(res);
		}
		res = Test.rotateRightPGM(res);
		SeamCarving.writepgm(res,"energie_Keep_cutLine_"+fichier);
		System.out.println("terminé");
	}
	
	public static void cutColumnPPM(String fichier, int nb){
		RGB[][] image = SeamCarving.readppm(fichier);
		SeamCarving.writeppm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRightPPM(image);
		int[][] itrD = interestDownPPM(image);
		int[][] itrU = interestUpPPM(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrD = interestDownPPM(res);
			itrU = interestUpPPM(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		SeamCarving.writeppm(res,"energie_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixDelCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
		RGB[][] image = SeamCarving.readppm(fichier);
		SeamCarving.writeppm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRightPPM(image);
		itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
		int[][] itrD = interestDownPPM(image);
		itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
		int[][] itrU = interestUpPPM(image);
		itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
			itrD = interestDownPPM(res);
			itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
			itrU = interestUpPPM(res);
			itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		SeamCarving.writeppm(res,"energie_Del_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixKeepCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
		RGB[][] image = SeamCarving.readppm(fichier);
		SeamCarving.writeppm(image,"energie_before_"+fichier);
		
		int[][] itrR = interestRightPPM(image);
		itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
		int[][] itrD = interestDownPPM(image);
		itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
		int[][] itrU = interestUpPPM(image);
		itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
			itrD = interestDownPPM(res);
			itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
			itrU = interestUpPPM(res);
			itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		SeamCarving.writeppm(res,"energie_Keep_cutCol_"+fichier);
		System.out.println("terminé");
	}
	
	public static void cutLinePPM(String fichier, int nb){
		RGB[][] image = SeamCarving.readppm(fichier);
		image = Test.rotateLeftPPM(image);

		SeamCarving.writeppm(image,"energie_before_"+fichier);
		int[][] itrR = interestRightPPM(image);
		int[][] itrD = interestDownPPM(image);
		int[][] itrU = interestUpPPM(image);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe  "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrD = interestDownPPM(res);
			itrU = interestUpPPM(res);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		res = Test.rotateRightPPM(res);
		SeamCarving.writeppm(res,"energie_cutLine_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixDelCutLinePPM(String fichier, int nb,int x, int y, int width, int height){
		RGB[][] image = SeamCarving.readppm(fichier);
		image = Test.rotateLeftPPM(image);

		SeamCarving.writeppm(image,"energie_before_"+fichier);
		int[][] itrR = interestRightPPM(image);
		itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
		int[][] itrD = interestDownPPM(image);
		itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
		int[][] itrU = interestUpPPM(image);
		itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe  "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrR = SeamCarving.pixelsToDelete(itrR, x, y, width, height);
			itrD = interestDownPPM(res);
			itrD = SeamCarving.pixelsToDelete(itrD, x, y, width, height);
			itrU = interestUpPPM(res);
			itrU = SeamCarving.pixelsToDelete(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		res = Test.rotateRightPPM(res);
		SeamCarving.writeppm(res,"energie_Del_cutLine_"+fichier);
		System.out.println("terminé");
	}
	
	public static void pixKeepCutLinePPM(String fichier, int nb,int x, int y, int width, int height){
		RGB[][] image = SeamCarving.readppm(fichier);
		image = Test.rotateLeftPPM(image);

		SeamCarving.writeppm(image,"energie_before_"+fichier);
		int[][] itrR = interestRightPPM(image);
		itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
		int[][] itrD = interestDownPPM(image);
		itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
		int[][] itrU = interestUpPPM(image);
		itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		RGB[][] res = image;
		Graph g2 = new Graph(res.length*res[0].length+2);
		
		for(int i = 0;i < nb;i++){
			System.out.println("Coupe  "+(i+1)+" sur "+nb);
			g = new Graph(res.length*res[0].length+2);
			itrR = interestRightPPM(res);
			itrR = SeamCarving.pixelsToKeep(itrR, x, y, width, height);
			itrD = interestDownPPM(res);
			itrD = SeamCarving.pixelsToKeep(itrD, x, y, width, height);
			itrU = interestUpPPM(res);
			itrU = SeamCarving.pixelsToKeep(itrU, x, y, width, height);
			g = EnergieAvant.toGraph(itrR,itrD,itrU);
			Test.visite = new boolean[res.length*res[0].length+2];
			Test.flotMax(g);
			res = Test.cutPPM(g,res);
		}

		res = Test.rotateRightPPM(res);
		SeamCarving.writeppm(res,"energie_Keep_cutLine_"+fichier);
		//return image;
		System.out.println("terminé");
	}
	
}
