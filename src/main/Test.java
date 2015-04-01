package main;

import java.util.ArrayList;

class Test
{
   static boolean visite[];
   public static void dfs(Graph g, int u)
   {
		visite[u] = true;
		System.out.println("Je visite " + u);
		for (Edge e: g.adj(u))
		  /* Si on prend l'arête dans le bon sens */
			if(e.from == u)
				if (!visite[e.to])
					dfs(g,e.to);
   }	 
   
   public static boolean bfs(Graph g, int u){
	   boolean done = true;
	   ArrayList<Integer> parcours = new ArrayList<Integer>();
	   parcours.add(u);

	   ArrayList<Edge> chemin = new ArrayList<Edge>();
	   //chemin.add(u);
	   visite[u] = true;
	   while(!parcours.isEmpty()){
		   u = parcours.remove(0);
		   System.out.println("Je visite "+u);
		   for(Edge e: g.adj(u)){
			   //System.out.println(visite[e.to]);
			   //System.out.println(e.used != e.capacity);
			   if (!visite[e.to] && e.used != e.capacity){
				   //System.out.println("yo");
				   if(e.used < e.capacity || e.from == g.getAdjLength()-1){
					   //System.out.println("hey");
					   chemin.add(e);
				   }
				   parcours.add(e.to);
				   visite[e.to] = true;
				   //e.used++;
			   }
		   }	
	   }
	   for(Edge e : chemin){
		   System.out.println(e);
	   }

	   parcours.removeAll(parcours);
	   int a = chemin.get((chemin.size())-1).from;
	   
	   for(Edge e : g.adj(a)){
		   if(e.from == chemin.get((chemin.size())-1).from){
			   e.used++;
		   }
	   }
	   
	   parcours.add(a);
	   System.out.println(a);
	   boolean found = false;
	   for(int i = 0; i < visite.length;i++){
		   
		   for(Edge e : chemin){
			   if(e.to == a){
				   a = e.from;
				   e.used++;
			   }
		   }
		   System.out.println(a);
		   
		   for(Edge e : g.adj(a)){
			   if(e.from == chemin.get((chemin.size())-1).from){
				   e.used++;
			   }
		   }
		   parcours.add(a);
		   if(a == 0)
			 break;//  found = true;
	   }
	   System.out.println("parcours : "+parcours);
	   for(boolean b : visite){
		   if(!b){
			   done = false;
		   }
	   }
	   return done;
   }
   
   public static void flotMax(Graph g){
	   boolean found;
	   bfs(g,0);
	   System.out.println(visite[visite.length-1]);
	   while(visite[visite.length-1]){
		   System.out.println("premier test");
		   
		   for(int i = 0;i < visite.length;i++){
			   visite[i] = false;
		   }
		   found = bfs(g,0);
		   
		   //found = bfs(g,0);
		   System.out.println("deuxieme test "+found);
	   }
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
		// dfs à partir du sommet 3
		visite = new boolean[n*n+2];
		//flotMax(g)
		dfs(g, 3);
	 }
   
   public static void testGraph2(){
	   int n = 3;
	   int i,j;
	   Graph g = new Graph(n*n+2);
	   for(i = 0;i < n-1;i++){
		   for(j = 0;j< n-1;j++){
			   g.addEdge(new Edge(j*i, (i+1)+j, 10,5*(i+j)));
		   }
	   }
	   bfs(g,0);
	   g.writeFile("test_graph2");
	   g.reduceGraph();
	   g.writeFile("test_reduce");
   }
   
   public static void main(String[] args)
	 {
	   int[][] tab = {{3,11,24,39,50},
				  	  {8,21,29,39,34},
				  	  {74,80,100,200,87},
				  	  {57,6,75,3,12}};
	   
	   Graph g = new Graph(4*3+2);
	   g = g.toGraph(tab);
	   /*
	   for(Edge e : g.edges()){
		   System.out.println("edge "+e);
	   }
	   */
	   //flotMax(g);
	   //for(int a = 0;a<7;a++){
	   /*
	   bfs(g,0);
	   for(int i = 0;i < visite.length;i++){
		   visite[i] = false;
	   }*/
	   //}
	   visite = new boolean[3*5+2];
	   /*
	   visite = new boolean[5*5+2];
	   
	   Graph g = new Graph(5*5+2);
		
		for (int i = 0; i < 5-1; i++)
		  for (int j = 0; j < 5 ; j++){
			g.addEdge(new Edge(5*i+j+1, 5*(i+1)+j+1, 1664 - (i+j),10*j));
		
			if(j>0){
				g.addEdge(new Edge(5*(i+1)+j+1, 5*i+j, -1,0));
			}
		
			g.addEdge(new Edge(5*(i+1)+j+1, 5*i+j+1, -2,0));
		
			if(j!=5-1){
				g.addEdge(new Edge(5*(i+1)+j+1, 5*i+j+2, -3,0));
			}
		  }
		for (int j = 0; j < 5 ; j++)		  
		  g.addEdge(new Edge(5*5-j, 5*5+1, 666,10*j));
		
		for (int j = 0; j < 5 ; j++)					
		  g.addEdge(new Edge(0, j+1, -1,10*j));
		
		//g.addEdge(new Edge(13,17,1337,0));
	   */
	   //flotMax(g);
	   g.writeFile("test_graph");
	   //testGraph();
		
	 }
}
