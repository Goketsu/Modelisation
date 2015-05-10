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
	   //chemin.add(u);
	   //System.out.println("test de adj "+(g.getAdjLength()-2));
	   
	   //System.out.println("taille visite : "+visite.length);
	   visite[u] = true;
	   while(!parcours.isEmpty()){
		   u = parcours.remove(parcours.size()-1);
		   //System.out.println("Je visite "+u);
		   for(Edge e: g.adj(u)){
			   //System.out.println(e.to);
			   //System.out.println(e.used != e.capacity);
			   if (!visite[e.to] && e.used != e.capacity){
				   //System.out.println("yo");
				   if(/*e.used < e.capacity ||*/ e.from != 0){
					   //System.out.println("hey");
					   chemin.add(e);
					   //System.out.println(e);
				   }
				   parcours.add(e.to);
				   visite[e.to] = true;
				   //e.used++;
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
		   //System.out.println(e);
	   }
	   
	   parcours.removeAll(parcours);
	   int a = g.getAdjLength()-1;
	   //int a = chemin.get((chemin.size())-1).from;
	   //System.out.println("g.adj(21) : "+g.adj(a));
	   //System.out.println("dernier du chemin : "+chemin.get((chemin.size())-1));
	   //System.out.println("chemin : "+chemin);
	   //System.out.println("je suis dans le bfs");
	   
	   
	   
	   //parcours.add(a);
	   //System.out.println("point : "+a);
	   boolean found = false;
	   boolean breaking = true;
	   //if(a != g.getAdjLength()-1){
	   /*
	   for(int i = 0; i < visite.length;i++){
		   if(i%10000 == 0)
			   System.out.println(i);
		   
		   for(Edge e : chemin){
			   if(e.to == a){
				   a = e.from;
				   //parcours.add(a);
				   e.used++;
				   //breaking = false;
				   //System.out.println("yep "+e);
			   }
		   }
		   /*
		   if(breaking){
			   System.out.println("???");
			   break;
		   }
		   */
		   //System.out.println("point : "+a);
		   /*
		   for(Edge e : g.adj(a)){
			   if(e.from == chemin.get((chemin.size())-1).from){
				   System.out.println("ghjykiyjhfdsx");
				   e.used++;
			   }
		   }
		   */
		   //parcours.add(a);
	   /*
		   if(a == 0)
			 break;//  found = true;
	   }
   */
	   //}
	   //a = chemin.get((chemin.size())-1).from;
	   a = g.getAdjLength()-1;
	   
	   //a = chemin.get(g.getAdjLength()-1).from;
	   //System.out.println("a : "+a);
	   //System.out.println("test "+parcours.contains(g.getAdjLength()-1));
	   //if(parcours.contains(g.getAdjLength()-1))
	   /*
	   for(Edge e : g.adj(a)){
		   if(e.from == chemin.get((chemin.size())-1).from){
			   System.out.println("batard "+e);
			   e.used++;
		   }
	   }
	   */
	   /*
	   for(int i : parcours){
		   for(Edge e :g.adj(i)){
			   if(e.to == i){
				   e.used++;
			   }
		   }
	   }
	   */
	   for(int i = 0;i < visite.length;i++){
		   if(visite[i])
			   parcours.add(i);
	   }
	   //System.out.println("parcours : "+parcours);
	   //System.out.println("j'ai ajouté les used");
	   for(boolean b : visite){
		   //System.out.println("SERIEUX "+b);
		   if(!b){
			   done = false;
		   }
	   }
	   
	   done = visite[g.getAdjLength()-1];
	   //System.out.println("done ? "+done);
	   return parcours;
   }
   
   public static void flotMax(Graph g){
	   boolean found;
	   bfs(g,0);
	   while(visite[visite.length-1]){
		   //System.out.println("Flot");

		   found = visite[g.getAdjLength()-1];
		   //System.out.println("visited : "+(visite.length-1));
		   //System.out.println("visited : "+visite[visite.length-1]);
		   for(int i = 0;i < visite.length;i++){
			   visite[i] = false;
		   }
		   bfs(g,0);
		   //found = bfs(g,0);
		   //System.out.println("deuxieme test "+found);
	   }
   }

   public static ArrayList<Integer> parcoursLigneGraph(Graph g, int[][] tab){
	   ArrayList<Integer> toDelete = new ArrayList<Integer>();
	   

	   ArrayList<Integer> parcours = new ArrayList<Integer>();
	   //parcours.add(0);
	   int u = 0;
	   visite = new boolean[tab.length*tab[0].length+2];
	   visite[u] = true;
	   ArrayList<Edge> chemin = new ArrayList<Edge>();
	   //System.out.println(g.adj(u).get(0));
	   for(int i = 0;i < tab.length;i++){
		   System.out.println("HFUIZHFIUHGZF : "+g.adj(0).get(i).to);
		   chemin.add(g.adj(0).get(i));
		   parcours.add(g.adj(0).get(i).to);
		   while(!parcours.isEmpty()){
			   u = parcours.remove(parcours.size()-1);
			   System.out.println("Je visite "+u);
			   for(Edge e: g.adj(u)){
				   System.out.println(e.to);
				   System.out.println(e.used != e.capacity);
				   if (!visite[e.to] && e.used != e.capacity && e.capacity >= 0){
					   chemin.add(e);
					   System.out.println("tralala : "+e.to);
					   parcours.add(e.to);
					   visite[e.to] = true;
					   //e.used++;
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
			   System.out.println("chemin : "+e);
		   }
		   toDelete.add(chemin.get(chemin.size()-1).to);
		   parcours.removeAll(parcours);
		   chemin.removeAll(chemin);
	   }
	   for(Integer a : toDelete){
		   System.out.println("a suppr : "+a);
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
		// dfs Ã  partir du sommet 3
		visite = new boolean[n*n+2];
		//flotMax(g)
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
						   //System.out.println("yolo");
						   toDelete.remove(toDelete.indexOf(a - tab.length*i));
					   }
				   }
				   toDelete.add(a);
			   }
		   }
	   }
	   /*
	   for(Integer a : toDelete){
		   System.out.println("a suppr : "+a);
	   }
	   */
	   int[][] resultat = new int[tab.length][tab[0].length-1];
	   //System.out.println("parcours : "+parcours);
	   //System.out.println("to delete : "+toDelete);
	   /*
	   for(int i = 0;i < tab.length;i++){
		   for(int j = 0;j < tab[0].length;j++){
			   System.out.println("val : "+tab[i][j]+", i : "+i+", j : "+j);
			   System.out.println("test : "+(j*tab.length+1+i));
		   }
	   }
	   */
	   //System.out.println("tableau : "+tab);
	   /*
	   for(int i = tab.length;i > 0;i--){
		   System.out.println(parcours.get(parcours.size()-i));
		   toDelete.add(parcours.get(parcours.size()-i));
		   parcours.remove(parcours.size()-i);
	   }
	   */
	   int a,b;
	   boolean yep = false;
	   //a = toDelete.get(2)/tab.length;
	   //b = toDelete.get(2)/tab.length-1;
	   //System.out.println("parcours : "+parcours);
	   for(int i = 0;i < resultat.length;i++){
		   yep = false;
		   for(int j = 0;j < resultat[0].length;j++){
			   //System.out.println("val : "+tab[i][j]+", i : "+i+", j : "+j);
			   //if(j != resultat[0].length)
			   
			   if(!toDelete.contains((j)*tab.length+1+i)){
				   //System.out.println("ready ? "+tab[i][j]);
				   //System.out.println("yep : "+yep);
				   if(yep){
					   resultat[i][j-1] = tab[i][j];
				   }else{
					   resultat[i][j]= tab[i][j];
				   }
			   }else{
				   yep = true;
			   }
			   //if(toDelete.contains(tab[i][j]))
				//   System.out.println("on y est presque "+tab[i][j]);
		   }
	   }
	   System.out.println("to delete : "+toDelete.size());
	   System.out.println("parcours : "+parcours.size());
	   //System.out.println("width ? : "+resultat[0].length);
	   System.out.println(resultat[0][(resultat[0].length-1)]);
	   //System.out.println("height ? : "+resultat.length);
	   //System.out.println("tableau : "+tab);
	   return resultat;
   }
   
   public static RGB[][] cutPPM(Graph g, RGB[][] tab){
	   ArrayList<Integer> parcours = bfs(g,0);
	   ArrayList<Integer> toDelete = new ArrayList<Integer>();
	   for(int a : parcours){
		   for(Edge e : g.adj(a)){
			   if(e.from == a && e.used == e.capacity/* && e.capacity > 0 */&& !toDelete.contains(a)){
				   for(int i = 1;i < tab[0].length;i++){
					   if(toDelete.contains(a - tab.length*i)){
						   //System.out.println("yolo");
						   toDelete.remove(toDelete.indexOf(a - tab.length*i));
					   }
				   }
				   toDelete.add(a);
			   }
		   }
	   }
	   /*
	   for(Integer a : toDelete){
		   System.out.println("a suppr : "+a);
	   }
	   */
	   RGB[][] resultat = new RGB[tab.length][tab[0].length-1];
	   //System.out.println("parcours : "+parcours);
	   //System.out.println("to delete : "+toDelete);
	   /*
	   for(int i = 0;i < tab.length;i++){
		   for(int j = 0;j < tab[0].length;j++){
			   System.out.println("val : "+tab[i][j]+", i : "+i+", j : "+j);
			   System.out.println("test : "+(j*tab.length+1+i));
		   }
	   }
	   */
	   //System.out.println("tableau : "+tab);
	   /*
	   for(int i = tab.length;i > 0;i--){
		   System.out.println(parcours.get(parcours.size()-i));
		   toDelete.add(parcours.get(parcours.size()-i));
		   parcours.remove(parcours.size()-i);
	   }
	   */
	   int a,b;
	   boolean yep = false;
	   //a = toDelete.get(2)/tab.length;
	   //b = toDelete.get(2)/tab.length-1;
	   //System.out.println("parcours : "+parcours);
	   System.out.println(resultat[0].length);
	   for(int i = 0;i < resultat.length;i++){
		   yep = false;
		   for(int j = 0;j < resultat[0].length;j++){
			   //System.out.println("val : "+tab[i][j]+", i : "+i+", j : "+j);
			   //if(j != resultat[0].length)
			   
			   if(!toDelete.contains((j)*tab.length+1+i)){
				   //System.out.println("ready ? "+tab[i][j]);
				   //System.out.println("yep : "+yep);
				   if(yep){
					   
					   resultat[i][j-1] = tab[i][j];
					   //if(j==398)
					   //System.out.println("test "+j+" "+i);
					   //System.out.println("i : "+i+"j : "+j+" :"+resultat[i][j-1].getR());
				   }else{
					   
					   resultat[i][j]= tab[i][j];
					   //System.out.println("i : "+i+"j : "+j+" :"+resultat[i][j].getR());
				   }
			   }else{
				   yep = true;
			   }
			   //if(toDelete.contains(tab[i][j]))
				//   System.out.println("on y est presque "+tab[i][j]);
		   }
	   }
	   
	   for(int i = 0; i < resultat.length;i++){
		   //System.out.println("serious ?"+(resultat[0].length-1));
		   resultat[i][(resultat[0].length-1)]= tab[i][(resultat[0].length)];
	   }
	   System.out.println("to delete : "+toDelete.size());
	   System.out.println("parcours : "+parcours.size());
	   //System.out.println("width ? : "+resultat[0].length);
	   //System.out.println("height ? : "+resultat.length);
	   //System.out.println("tableau : "+tab);
	   //System.out.println(resultat[0][398].getR());
	   return resultat;
   }
   
   public static int[][] rotateLeftPGM(int[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   System.out.println("largeur : "+width);
	   System.out.println("hauteur : "+height);
	   int[][] tab2 = new int[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				//System.out.println("i : "+i+", j : "+j);
				//System.out.println("tab 1 : "+tab[i][j]);
				tab2[i][j] = 
						tab[j][width-i-1];
				//System.out.println("tab 2 : "+tab2[i][j]);
			}
	   }
	   System.out.println("largeur 2 : "+tab2[0].length);
	   System.out.println("hauteur 2 : "+tab2.length);
	   return tab2;
   }
   
   public static RGB[][] rotateLeftPPM(RGB[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   System.out.println("largeur : "+width);
	   System.out.println("hauteur : "+height);
	   RGB[][] tab2 = new RGB[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				//System.out.println("i : "+i+", j : "+j);
				//System.out.println("tab 1 : "+tab[i][j]);
				tab2[i][j] = 
						tab[j][width-i-1];
				//System.out.println("tab 2 : "+tab2[i][j]);
			}
	   }
	   System.out.println("largeur 2 : "+tab2[0].length);
	   System.out.println("hauteur 2 : "+tab2.length);
	   return tab2;
   }
   
   public static int[][] rotateRightPGM(int[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   System.out.println("largeur : "+width);
	   System.out.println("hauteur : "+height);
	   int[][] tab2 = new int[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = tab[height-j-1][i];
				//System.out.println("tab 2 : "+tab2[i][j]);
			}
	   }
	   System.out.println("largeur 2 : "+tab2[0].length);
	   System.out.println("hauteur 2 : "+tab2.length);
	   return tab2;
   }
   
   public static RGB[][] rotateRightPPM(RGB[][] tab){
	   int width = tab[0].length;
	   int height = tab.length;
	   System.out.println("largeur : "+width);
	   System.out.println("hauteur : "+height);
	   RGB[][] tab2 = new RGB[width][height];
	   for (int i = 0; i < width; i++){
			for (int j = 0; j < height ; j++){
				tab2[i][j] = tab[height-j-1][i];
				//System.out.println("tab 2 : "+tab2[i][j]);
			}
	   }
	   System.out.println("largeur 2 : "+tab2[0].length);
	   System.out.println("hauteur 2 : "+tab2.length);
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
		   System.out.println("ITERATION "+i);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interest(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   flotMax(g);
	   
		   res = cut(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(res);
	   }
	   
	   //g2.writeFile("test_graph_cutLine");
	   SeamCarving.writepgm(res,"test_cutCol_"+fichier);
	   
	   System.out.println("termine ");
   }
   
   public static void cutColumnPPM(String fichier, int nb){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("ITERATION "+i);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interestPPM(res));
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g);
		   res = cutPPM(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   
	   SeamCarving.writeppm(res,"Test_cutCol_"+fichier);
   }
   
   public static void pixDelCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Del_"+fichier);


	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToDelete(interest, x, y, width, height);
	   //System.out.println("test "+interest[0][0]);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("ITERATION "+i);
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
   }
   
   public static void pixKeepCutColumnPPM(String fichier, int nb,int x, int y, int width, int height){
	   
	   RGB[][] image = SeamCarving.readppm(fichier);
	   SeamCarving.writeppm(image,"Test_Keep_"+fichier);


	   int[][] interest = SeamCarving.interestPPM(image);
	   interest = SeamCarving.pixelsToKeep(interest, x, y, width, height);
	   //System.out.println("test "+interest[0][0]);
	   SeamCarving.writepgm(interest,"Interest_"+fichier);
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   
	   RGB[][] res = image;
	   
	   Graph g2 = new Graph(res.length*res[0].length+2);	   
	   
	   for(int i = 0;i < nb;i++){
		   System.out.println("ITERATION "+i);
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
	   
	   SeamCarving.writeppm(res,"Test_Keep_cutCol_"+fichier);
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
		   System.out.println("ITERATION "+i);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interest(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   //bfs(g,0);
		   flotMax(g);
	   
		   //g.writeFile("test2_ex1");
	   
		   res = cut(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(res);
	   }
	   res = Test.rotateRightPGM(res);
	   

	   g2.writeFile("test_graph_cutLine");
	   SeamCarving.writepgm(res,"Test_CutLine_"+fichier);
	   
	   //bfs(g,0);
	   
	   System.out.println("termine ");
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
		   System.out.println("ITERATION "+i);
		   g = new Graph(res.length*res[0].length+2);
		   g = g.toGraph(SeamCarving.interestPPM(res));
		   visite = new boolean[res.length*res[0].length+2];
	   
		   //bfs(g,0);
		   flotMax(g);
	   
		   //g.writeFile("test2_ex1");
	   
		   res = cutPPM(g,res);
		   g2 = new Graph(res.length*res[0].length+2);
		   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   }
	   res = Test.rotateRightPPM(res);
	   

	   g2.writeFile("test_graph_cutLine");
	   SeamCarving.writeppm(res,"test_cutLine_"+fichier);
	   
	   //bfs(g,0);
	   
	   System.out.println("termine ");
   }
   
   
   public static void main(String[] args)
	 {
	   
	   int[][] tab = {{3,11,24,39,54},
				  	  {8,21,29,39,68},
				  	  {74,80,100,200,9},
				  	  {8,21,29,39,68}
				  	  };
	   /*
	   //Test de fonction cutColumn
	   cutColumnPPM("len_top.ppm",20);
	   
	   //Test de pixels à delete
	   pixDelCutColumnPPM("len_top.ppm",20,160,20,50,100);
	   
	   //Test de pixels à garder
	   pixKeepCutColumnPPM("len_top.ppm",20,0,0,200,225);
	   */
	   
	   //cutColumnPGM("ex1.pgm",10);
	   
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
	   System.out.println("Vous voulez supprimez "+col+" colonnes");
	   if(algo == 1){
		   if(fichier == "ex1.pgm" || fichier == "ex2.pgm" || fichier == "ex3.pgm")
			   if(type == 1)
				   cutColumnPGM(fichier,col);
			   else
				   cutLinePGM(fichier,col);
		   else
			   if(type == 1)
				   cutColumnPPM(fichier,col);
			   else
				   cutLinePPM(fichier,col);
	   }else{
		   if(fichier == "ex1.pgm" || fichier == "ex2.pgm" || fichier == "ex3.pgm")
			   if(type == 1)
				   EnergieAvant.cutColumnPGM(fichier, col);
			   else
				   EnergieAvant.cutLinePGM(fichier, col);
		   else
			   if(type == 1)
				   EnergieAvant.cutColumnPPM(fichier, col);
			   else
				   EnergieAvant.cutLinePPM(fichier, col);
	   }
	   //EnergieAvant.cutColumnPGM(fichier, col);
	   
	   /*
	   //TEST PIXEL A SUPPRIMER/GARDER
	   RGB[][] image = SeamCarving.readppm("len_top.ppm");
	   int[][] interest = SeamCarving.interestPPM(image);
	   SeamCarving.writepgm(interest,"interet_len.pgm");
	   //interest = SeamCarving.pixelsToDelete(interest, 160, 20, 50, 100);
	   //System.out.println("test "+interest[0][0]);
	   //SeamCarving.writepgm(interest,"interetDel_len.pgm");
	   interest = SeamCarving.pixelsToKeep(interest, 0, 0, 200, 225);
	   SeamCarving.writepgm(interest,"interetKeep_len.pgm");
	   
	   //TEST COUPE PIXEL A SUPPRIMER
	   Graph g = new Graph(image.length*image[0].length+2);
	   g = g.toGraph(interest);
	   /*
	   for(int i = 0;i < interest.length; i++){
		   for(int j = 0; j < interest[0].length; j++){
			   if(interest[i][j] == 0)
				   System.out.println("i et j "+i+ " "+j);
		   }
	   }*/
	   
	   /*
	   g.writeFile("test_graph");
	   visite = new boolean[image.length*image[0].length+2];
	   
	   flotMax(g);
	   
	   RGB[][] res = cutPPM(g,image);
	   interest = SeamCarving.interestPPM(res);
	   interest = SeamCarving.pixelsToKeep(interest, 0, 0, 200, 225);
	   System.out.println(res[0][398].getR());
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   g2 = g2.toGraph(interest);
	   
	   for(int i = 0;i < 19;i++){
		   System.out.println("ITERATION "+i);
		   Graph g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(interest);
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g3);
		   res = cutPPM(g3,res);
		   interest = SeamCarving.interestPPM(res);
		   interest = SeamCarving.pixelsToKeep(interest, 0, 0, 200, 225);
		   g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(interest);
	   }
	   
	   SeamCarving.writeppm(res,"len_test_cut.ppm");
	   
	   /*
	   // TEST COULEUR
	   RGB[][] image = SeamCarving.readppm("len_top.ppm");
	   //int[][] interest = SeamCarving.interestPPM(image);
	   //writepgm(interest,"interet_len.pgm");
	   SeamCarving.writeppm(image,"len_test.ppm");
	   
	   Graph g = new Graph(image.length*image[0].length+2);
	   g = g.toGraph(SeamCarving.interestPPM(image));
	   visite = new boolean[image.length*image[0].length+2];
	   
	   //bfs(g,0);
	   flotMax(g);
	   
	   //g.writeFile("test2_ex1");
	   
	   RGB[][] res = cutPPM(g,image);
	   System.out.println(res[0][398].getR());
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   g2 = g2.toGraph(SeamCarving.interestPPM(res));
	   
	   
	   
	   for(int i = 0;i < 19;i++){
		   System.out.println("ITERATION "+i);
		   Graph g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(SeamCarving.interestPPM(res));
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g3);
		   res = cutPPM(g3,res);
		   g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(SeamCarving.interestPPM(res));
	   }
	   
	   SeamCarving.writeppm(res,"len_test_cut.ppm");
	   //FIN TEST COULEUR
	   */
	   
	   //Test.cutLine();
/*
	   visite = new boolean[tab.length*tab[0].length+2];
	   Graph g = new Graph(tab.length*tab[0].length+2);
	   g = g.toGraph(SeamCarving.interest(tab));
	   flotMax(g);
	   Test.parcoursLigneGraph(g,tab);
	   */
	   /*
	   int[][] tab2 = Test.rotateRight(tab);
	   
	   int[][] tab3 = SeamCarving.readpgm("ex1.pgm");
	   tab3 = Test.rotateRight(tab3);
	   SeamCarving.writepgm(tab3, "testRotate.pgm");
	   
	   System.out.println("avant : " + tab[0][1]);
	   System.out.println("apres : "+tab2[0][1]);
	   */
	   
	  
	   /*
	   // Aurait du fonctionner
	   if(args[0] == null){
		   System.out.println("Vous n'avez pas proposez d'argument");
		   System.out.println("Le programme va s'executer sur ex1.pgm");
		   tab = SeamCarving.readpgm("ex1.pgm");
	   }else{
		   if (args[0].matches("[a-zA-Z]*[0-9]*\\.(pgm)")){
			   System.out.println("Le format du fichier est correcte!");
			   System.out.println(args[0]);
			   tab = SeamCarving.readpgm(args[0]);
		   }else{
			   System.out.println("Le fichier doit etre ex1.pgm, ex2.pgm ou ex3.pgm");
			   System.exit(1);
		   }
	   }
	   
	   //int[][] tab = SeamCarving.readpgm("ex.pgm");
	   
	   
	   Graph g = new Graph(tab.length*tab[0].length+2);
	   g = g.toGraph(SeamCarving.interest(tab));
	   visite = new boolean[tab.length*tab[0].length+2];
	   
	   //bfs(g,0);
	   flotMax(g);
	   
	   //g.writeFile("test2_ex1");
	   
	   int[][] res = cut(g,tab);
	   Graph g2 = new Graph(res.length*res[0].length+2);
	   g2 = g2.toGraph(res);
	   
	   
	   
	   for(int i = 0;i < 4;i++){
		   System.out.println("ITERATION "+i);
		   Graph g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(SeamCarving.interest(res));
		   visite = new boolean[res.length*res[0].length+2];
		   flotMax(g3);
		   res = cut(g3,res);
		   g3 = new Graph(res.length*res[0].length+2);
		   g3 = g3.toGraph(res);
	   }
	   
	   /*
	   for(int i = 0;i < res.length;i++){
		   for(int j = 0;j < res[0].length;j++){
			   System.out.println("val : "+res[i][j]+", i : "+i+", j : "+j);
		   }
	   }
	   */
	   
	   /*
	   g2.writeFile("test_graph_cut");
	   SeamCarving.writepgm(res,"test_cut.pgm");
	   
	   //bfs(g,0);
	   
	   g.writeFile("test_graph");
	   SeamCarving.writepgm(tab, "test.pgm");
	   
	   System.out.println("termine ");
	   */
	 }

}
