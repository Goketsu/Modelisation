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
		if(u > 0){
			if (e.from == u && e.capacity != -1)
				if (!visite[e.to])
					dfs(g,e.to);
		}else{
			if(e.from == u){
				if (!visite[e.to])
					dfs(g,e.to);
			}
		}
	 }
   
   public static void bfs(Graph g, int u){
	   ArrayList<Integer> parcours = new ArrayList<Integer>();
	   parcours.add(u);
	   visite[u] = true;
	   while(!parcours.isEmpty()){
		   u = parcours.remove(0);
		   System.out.println("Je visite "+u);
		   for(Edge e: g.adj(u)){
			   if (!visite[e.to]){
				   parcours.add(e.to);
				   visite[e.to] = true;
			   }
		   }	
	   }
	   
	   
		   
   }

   
   public static void testGraph()
	 {
		int n = 5;
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
		dfs(g, 3);
	 }
   
   public static void main(String[] args)
	 {
	   int[][] tab = {{3,11,24,39},
				  {8,21,29,39},
				  {74,80,100,200}};
	   Graph g = new Graph(4*3+2);
	   g = g.toGraph(tab);
	   g.writeFile("test_graph");
	   visite = new boolean[4*3+2];
	   bfs(g,0);
	   //testGraph();
		
	 }
}
