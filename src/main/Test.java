package main;

import java.util.ArrayList;
import java.util.Scanner;

class Test
{
   static boolean visite[];
   public static void dfs(Graph g, int u)
   {
		visite[u] = true;
		System.out.println("Je visite " + u);
		for (Edge e: g.adj(u))
		  /* Si on prend l'arÃªte dans le bon sens */
			if(e.from == u)
				if (!visite[e.to])
					dfs(g,e.to);
   }
   
   public static ArrayList<Integer> bfs(Graph g, int u){
	   boolean done = true;
	   ArrayList<Integer> parcours = new ArrayList<Integer>();
	   parcours.add(u);
	   
	   ArrayList<Edge> chemin = new ArrayList<Edge>();
	   
	   visite[u] = true;
	   while(!parcours.isEmpty()){
		   u = parcours.remove(parcours.size()-1);
		   for(Edge e: g.adj(u)){
			   if (!visite[e.to] && e.used != e.capacity){
				   if(e.from != 0){
					   chemin.add(e);
				   }
				   parcours.add(e.to);
				   visite[e.to] = true;
			   }
			   if(visite[g.getAdjLength()-1]){
				   break;
			   }
		   }	
		   if(visite[g.getAdjLength()-1]){
			   break;
		   }
	   }
	   
	   for(Edge e : chemin){
		   if(visite[g.getAdjLength()-1])
		   e.used++;
	   }
	   
	   parcours.removeAll(parcours);
	   int a = g.getAdjLength()-1;
	   
	   boolean found = false;
	   boolean breaking = true;
	   
	   a = g.getAdjLength()-1;
	   
	   for(int i = 0;i < visite.length;i++){
		   if(visite[i])
			   parcours.add(i);
	   }
	   
	   for(boolean b : visite){
		   if(!b){
			   done = false;
		   }
	   }
	   
	   done = visite[g.getAdjLength()-1];
	   return parcours;
   }
   
   public static void flotMax(Graph g){
	   boolean found;
	   bfs(g,0);
	   while(visite[visite.length-1]){

		   found = visite[g.getAdjLength()-1];
		   for(int i = 0;i < visite.length;i++){
			   visite[i] = false;
		   }
		   bfs(g,0);
	   }
   }

   public static ArrayList<Integer> parcoursLigneGraph(Graph g, int[][] tab){
	   ArrayList<Integer> toDelete = new ArrayList<Integer>();
	   

	   ArrayList<Integer> parcours = new ArrayList<Integer>();
	   int u = 0;
	   visite = new boolean[tab.length*tab[0].length+2];
	   visite[u] = true;
	   ArrayList<Edge> chemin = new ArrayList<Edge>();
	   for(int i = 0;i < tab.length;i++){
		   chemin.add(g.adj(0).get(i));
		   parcours.add(g.adj(0).get(i).to);
		   while(!parcours.isEmpty()){
			   u = parcours.remove(parcours.size()-1);
			   for(Edge e: g.adj(u)){
				   System.out.println(e.to);
				   System.out.println(e.used != e.capacity);
				   if (!visite[e.to] && e.used != e.capacity && e.capacity >= 0){
					   chemin.add(e);
					   parcours.add(e.to);
					   visite[e.to] = true;
				   }
				   if(visite[g.getAdjLength()-1]){
					   break;
				   }
			   }	
			   if(visite[g.getAdjLength()-1]){
				   break;
			   }
		   }
		   toDelete.add(chemin.get(chemin.size()-1).to);
		   parcours.removeAll(parcours);
		   chemin.removeAll(chemin);
	   }
	   return toDelete;
   }
   
   public static void testGraph()
	 {
		int n = 6;
		int i,j;
		Graph g = new Graph(n*n+2);
		
		for (i = 0; i < n-1; i++)
		  for (j = 0; j < n ; j++)
			g.addEdge(new Edge(n*i+j, n*(i+1)+j, 1664 - (i+j),10*j));

		for (j = 0; j < n ; j++)		  
		  g.addEdge(new Edge(n*(n-1)+j, n*n, 666,10*j));
		
		for (j = 0; j < n ; j++)					
		  g.addEdge(new Edge(n*n+1, j, 10*j,10*j));
		
		g.addEdge(new Edge(13,17,1337,0));
		g.writeFile("test.dot");
		visite = new boolean[n*n+2];
		dfs(g, 3);
	 }
   
   public static int[][] cut(Graph g, int[][] tab){
	   ArrayList<Integer> parcours = bfs(g,0);
	   ArrayList<Integer> toDelete = new ArrayList<Integer>();
	   for(int a : parcours){
		   for(Edge e : g.adj(a)){
			   if(e.from == a && e.used == e.capacity/* && e.capacity > 0 */&& !toDelete.contains(a)){
				   for(int i = 1;i < tab[0].length;i++){
					   if(toDelete.contains(a - tab.length*i)){
						   toDelete.remove(toDelete.indexOf(a - tab.length*i));
					   }
				   }
				   toDelete.add(a);
			   }
		   }
	   }
	   int[][] resultat = new int[tab.length][tab[0].length-1];
	   
	   int a,b;
	   boolean yep = false;
	   
	   for(int i = 0;i < resultat.length;i++){
		   yep = false;
		   for(int j = 0;j < resultat[0].length;j++){
			   
			   if(!toDelete.contains((j)*tab.length+1+i)){
				   if(yep){
					   resultat[i][j-1] = tab[i][j];
				   }else{
					   resultat[i][j]= tab[i][j];
				   }
			   }else{
				   yep = true;
			   }
		   }
	   }
	   
	   return resultat;
   }
   
   public static RGB[][] cutPPM(Graph g, RGB[][] tab){
	   ArrayList<Integer> parcours = bfs(g,0);
	   ArrayList<Integer> toDelete = new ArrayList<Integer>();
	   for(int a : parcours){
		   for(Edge e : g.adj(a)){
			   if(e.from == a && e.used == e.capacity && !toDelete.contains(a)){
				   for(int i = 1;i < tab[0].length;i++){
					   if(toDelete.contains(a - tab.length*i)){
						   toDelete.remove(toDelete.indexOf(a - tab.length*i));
					   }
				   }
				   toDelete.add(a);
			   }
		   }
	   }
	   RGB[][] resultat = new RGB[tab.length][tab[0].length-1];
	   
	   int a,b;
	   boolean yep = false;
	   for(int i = 0;i < resultat.length;i++){
		   yep = false;
		   for(int j = 0;j < resultat[0].length;j++){
			   
			   if(!toDelete.contains((j)*tab.length+1+i)){
				   if(yep){
					   
					   resultat[i][j-1] = tab[i][j];
				   }else{
					   
					   resultat[i][j]= tab[i][j];
				   }
			   }else{
				   yep = true;
			   }
		   }
	   }
	   
	   for(int i = 0; i < resultat.length;i++){
		   resultat[i][(resultat[0].length-1)]= tab[i][(resultat[0].length)];
	   }
	   return resultat;
   }
   
   public static int[][] rotateLeftPGM(int[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   int[][] tab2 = new int[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = 
						tab[j][width-i-1];
			}
	   }
	   return tab2;
   }
   
   public static RGB[][] rotateLeftPPM(RGB[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   RGB[][] tab2 = new RGB[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = tab[j][width-i-1];
			}
	   }
	   
	   return tab2;
   }
   
   public static int[][] rotateRightPGM(int[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   
	   int[][] tab2 = new int[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = tab[height-j-1][i];
			}
	   }
	   
	   return tab2;
   }
   
   public static RGB[][] rotateRightPPM(RGB[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   RGB[][] tab2 = new RGB[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = tab[height-j-1][i];
			}
	   }
	   return tab2;
   }
   
   public static void cutColumnPGM(String fichier, int nb){
	   
	   int[][] tab = SeamCarving.readpgm(fichier);
	   Graph g = new Graph(tab.length*tab[0].length+2);
	   g.writeFile("test_graph");
	   SeamCarving.writepgm(tab, "test_"+fichier);
	   int[][] res = tab;
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   for(int i = 0;i < nb; i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interest(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   flotMax(g);
	   
		   res = cut(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(res);
	   }
	   
	   SeamCarving.writepgm(res,"test_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void cutColumnPPM(String fichier, int nb){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interestPPM(res));
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   
	   SeamCarving.writeppm(res,"Test_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixDelCutColumnPGM(String fichier, int nb,int x, int y, int width, int height){
	   
	   int[][] image = SeamCarving.readpgm(fichier);
	   SeamCarving.writepgm(image,"Test_Del_"+fichier);


	   int[][] interest = SeamCarving.interest(image);
	   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   int[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cut(g,res);
		   interest = SeamCarving.interest(res);
		   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interest(res));
	   }
	   
	   SeamCarving.writepgm(res,"Test_Del_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixDelCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Del_"+fichier);


	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   interest = SeamCarving.interestPPM(res);
		   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   
	   SeamCarving.writeppm(res,"Test_Del_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixDelCutLinePGM(String fichier, int nb,int x, int y, int width, int height){
	   
	   int[][] image = SeamCarving.readpgm(fichier);
	   SeamCarving.writepgm(image,"Test_Del_"+fichier);

	   image = Test.rotateLeftPGM(image);
	   
	   int[][] interest = SeamCarving.interest(image);
	   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   int[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cut(g,res);
		   interest = SeamCarving.interest(res);
		   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interest(res));
	   }
	   res = Test.rotateRightPGM(res);
	   SeamCarving.writepgm(res,"Test_Del_cutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixDelCutLinePPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Del_"+fichier);

	   image = Test.rotateLeftPPM(image);
	   
	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   interest = SeamCarving.interestPPM(res);
		   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   res = Test.rotateRightPPM(res);
	   SeamCarving.writeppm(res,"Test_Del_cutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixKeepCutColumnPGM(String fichier, int nb,int x, int y, int width, int height){
	   
	   int[][] image = SeamCarving.readpgm(fichier);
	   SeamCarving.writepgm(image,"Test_Keep_"+fichier);
	   image = Test.rotateLeftPGM(image);
	   
	   int[][] interest = SeamCarving.interest(image);
	   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   int[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cut(g,res);
		   interest = SeamCarving.interest(res);
		   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interest(res));
	   }
	   res = Test.rotateRightPGM(res);
	   SeamCarving.writepgm(res,"Test_Keep_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixKeepCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Keep_"+fichier);
	   image = Test.rotateLeftPPM(image);
	   
	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   interest = SeamCarving.interestPPM(res);
		   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   res = Test.rotateRightPPM(res);
	   SeamCarving.writeppm(res,"Test_Keep_cutCol_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixKeepCutLinePGM(String fichier, int nb,int x, int y, int width, int height){
	   
	   int[][] image = SeamCarving.readpgm(fichier);
	   SeamCarving.writepgm(image,"Test_Keep_"+fichier);


	   int[][] interest = SeamCarving.interest(image);
	   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   int[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cut(g,res);
		   interest = SeamCarving.interest(res);
		   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interest(res));
	   }
	   
	   SeamCarving.writepgm(res,"Test_Keep_cutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void pixKeepCutLinePPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Keep_"+fichier);


	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   interest = SeamCarving.interestPPM(res);
		   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   
	   SeamCarving.writeppm(res,"Test_Keep_cutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void cutLinePGM(String fichier, int nb){

	   int[][] tab = SeamCarving.readpgm(fichier);
	   Graph g = new Graph(tab.length*tab[0].length+2);
	   g.writeFile("test_graph");
	   SeamCarving.writepgm(tab, "Test.pgm");
	   tab = Test.rotateLeftPGM(tab);
	   int[][] res = tab;
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   for(int i = 0;i < nb; i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interest(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   flotMax(g);
	   
		   res = cut(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(res);
	   }
	   res = Test.rotateRightPGM(res);
	   

	   g2.writeFile("test_graph_cutLine");
	   SeamCarving.writepgm(res,"Test_CutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   public static void cutLinePPM(String fichier, int nb){

	   RGB[][] tab = SeamCarving.readppm(fichier);
	   Graph g = new Graph(tab.length*tab[0].length+2);
	   g.writeFile("test_graph");
	   SeamCarving.writeppm(tab, "test.ppm");
	   tab = Test.rotateLeftPPM(tab);
	   RGB[][] res = tab;
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   for(int i = 0;i < 10; i++){
		   System.out.println("Coupe "+(i+1)+" sur "+nb);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interestPPM(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   flotMax(g);
	   
		   res = cutPPM(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   res = Test.rotateRightPPM(res);
	   

	   g2.writeFile("test_graph_cutLine");
	   SeamCarving.writeppm(res,"test_cutLine_"+fichier);
	   
	   System.out.println("terminé");
   }
   
   
   public static void main(String[] args)
	 {
	   
	   int[][] tab = {{3,11,24,39,54},
				  	  {8,21,29,39,68},
				  	  {74,80,100,200,9},
				  	  {8,21,29,39,68}
				  	  };
	   
	   // INTERFACE UTILISATEUR
	   Scanner sc = new Scanner(System.in);
	   int algo = 0;
	   int nb = 0;
	   while(nb < 1 || nb > 2){
		   System.out.println("Quel algorithme voulez-vous utilisez ?");
		   System.out.println("1 : Algorithme normal");
		   System.out.println("2 : Algorithme d'energie avant");
		   nb = sc.nextInt();
		   if(nb < 1 || nb > 2){
			   System.out.println("L'option choisie n'est pas valide");
		   }
	   }
	   if(nb == 1){
		   System.out.println("Vous avez choisi : Algorithme normal");
		   algo = 1;
	   }else{
		   System.out.println("Vous avez choisi : Algorithme d'energie avant");
		   algo = 2;
		   
	   }
	   
	   int im = 0;
	   String fichier = "ex1.pgm";
	   while(im < 1 || im > 4){
		   System.out.println("Quelle image voulez-vous modifier ?");
		   System.out.println("1 : ex1.pgm");
		   System.out.println("2 : ex2.pgm");
		   System.out.println("3 : ex3.pgm");
		   System.out.println("4 : len_top.ppm (couleur)");
		   im = sc.nextInt();
		   if(im < 1 || im > 4){
			   System.out.println("L'option choisie n'est pas valide");
		   }
	   }
	   if(im == 1){
		   System.out.println("Vous avez choisi : ex1.pgm");
		   fichier = "ex1.pgm";
	   }if(im == 2){
		   System.out.println("Vous avez choisi : ex2.pgm");
		   fichier = "ex2.pgm";
	   }if(im == 3){
		   System.out.println("Vous avez choisi : ex3.pgm");
		   fichier = "ex3.pgm";
	   }if(im == 4){
		   System.out.println("Vous avez choisi : len_top.ppm");
		   fichier = "len_top.ppm";
	   }
	   int type = 0;
	   while(type < 1 || type > 2){
		   System.out.println("Voulez-vous faire une coupe en ligne ou en colonne ?");
		   System.out.println("1 : en colonne");
		   System.out.println("2 : en ligne");
		   type = sc.nextInt();
		   if(type < 1 || type > 2)
			   System.out.println("L'option choisie n'est pas valide");
	   }
	   if(type == 1)
		   System.out.println("Vous avez choisi une coup en colonne");
	   if(type == 2)
		   System.out.println("Vous avez choisi une coupe en ligne");
	   
	   int col = 0;
	   while(col < 1){
		   if(type == 1)
			   System.out.println("Combien de colonnes voulez-vous suprrimer ?");
		   else
			   System.out.println("Combien de lignes voulez-vous suprrimer ?");
		   col = sc.nextInt();
		   if(col < 1)
			   System.out.println("Veuillez entrer un nombre superieur à 0");
	   }
	   int zone = 0;
	   while(zone < 1 || zone > 3){
		   System.out.println("Voulez-vous definir une zone à supprimer/conserver dans l'image ?");
		   System.out.println("1 : Ne rien faire");
		   System.out.println("2 : Definir une zone à supprimer");
		   System.out.println("3 : Definir une zone à conserver");
		   zone = sc.nextInt();
		   if(zone < 1 || zone > 3)
			   System.out.println("L'option choisie n'est pas valide");
	   }
	   int x = -1;
	   int y = -1;
	   int width = -1;
	   int height = -1;
	   if(zone != 1){
		   while(x < 0 || y <0){
			   System.out.println("Veuillez entrer les coordonnées d'un point (angle en haut à gauche"
					   + "de la zone definie)");
			   x = sc.nextInt();
			   y = sc.nextInt();
			   if(x < 0 || y <0)
				   System.out.println("L'option choisie n'est pas valide");
		   }
		   while(width < 0 || height < 0){
			   System.out.println("Veuillez entrer les largeur et hauteur de la zone");
			   width = sc.nextInt();
			   height = sc.nextInt();
			   if(width < 0 || height <0)
				   System.out.println("L'option choisie n'est pas valide");
		   }
	   }
	   System.out.println("Vous voulez supprimez "+col+" colonnes");
	   if(algo == 1){
		   if(fichier == "ex1.pgm" || fichier == "ex2.pgm" || fichier == "ex3.pgm")
			   if(type == 1){
				   if(zone == 2){
					   pixDelCutColumnPGM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   pixKeepCutColumnPGM(fichier,col,x,y,width,height);
				   }else{
					   cutColumnPGM(fichier,col);
				   }
			   }else{
				   if(zone == 2){
					   pixDelCutLinePGM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   pixKeepCutLinePGM(fichier,col,x,y,width,height);
				   }else{
					   cutLinePGM(fichier,col);
				   }
			   }
		   else
			   if(type == 1){
				   if(zone == 2){
					   pixDelCutColumnPPM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   pixKeepCutColumnPPM(fichier,col,x,y,width,height);
				   }else{
					   cutColumnPPM(fichier,col);
				   }
			   }else{
				   if(zone == 2){
					   pixDelCutLinePPM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   pixKeepCutLinePPM(fichier,col,x,y,width,height);
				   }else{
					   cutLinePPM(fichier,col);
				   }
			   }
	   }else{
		   if(fichier == "ex1.pgm" || fichier == "ex2.pgm" || fichier == "ex3.pgm")
			   if(type == 1){
				   if(zone == 2){
					   EnergieAvant.pixDelCutColumnPGM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   EnergieAvant.pixKeepCutColumnPGM(fichier,col,x,y,width,height);
				   }else{
					   EnergieAvant.cutColumnPGM(fichier, col);
				   }
			   }else{
				   if(zone == 2){
					   EnergieAvant.pixDelCutLinePGM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   EnergieAvant.pixKeepCutLinePGM(fichier,col,x,y,width,height);
				   }else{
					   EnergieAvant.cutLinePGM(fichier, col);
				   }
			   }
		   else
			   if(type == 1){
				   if(zone == 2){
					   EnergieAvant.pixDelCutColumnPPM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   EnergieAvant.pixKeepCutColumnPPM(fichier,col,x,y,width,height);
				   }else{
					   EnergieAvant.cutColumnPPM(fichier, col);
				   }
			   }else{
				   if(zone == 2){
					   EnergieAvant.pixDelCutLinePPM(fichier,col,x,y,width,height);
				   }else if(zone == 3){
					   EnergieAvant.pixKeepCutLinePPM(fichier,col,x,y,width,height);
				   }else{
					   EnergieAvant.cutLinePPM(fichier, col);
				   }
			   }
	   }
	 }

}
