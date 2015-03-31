package main;

import java.util.ArrayList;
import java.io.*;

public class Graph
{
   private ArrayList<Edge>[] adj;
   private final int V;
   int E;
@SuppressWarnings("unchecked")
	public Graph(int N)
	 {
		this.V = N;
		this.E = 0;
		 adj = (ArrayList<Edge>[]) new ArrayList[N];
		for (int v= 0; v < N; v++)
		  adj[v] = new ArrayList<Edge>(8);
		
	 }

	public Graph toGraph(int[][] itr){
		
		int width = itr[0].length;
		int height = itr.length;
		int i,j;
		
		Graph g = new Graph(width*height+2);
		int[][] inter = SeamCarving.interest(itr);
		for(i = 0 ; i < height; i++){
			g.addEdge(new Edge(0,i+1,-1,0));
		}
		for (i = 0; i < width-1; i++){
			for (j = 0; j < height ; j++){
				g.addEdge(new Edge(width*i+j+1-i, height*(i+1)+j+1, inter[j][i],0));
				
				if(j>0){
					g.addEdge(new Edge(height*(i+1)+j+1, width*i+j-i, -1,0));
				}
				
				g.addEdge(new Edge(height*(i+1)+j+1, width*i+j+1-i, -1,0));
				
				if(j!=height-1){
					g.addEdge(new Edge(height*(i+1)+j+1, width*i+j+1-i+1, -1,0));
				}
				//}
			}
		}
		for(i = 0 ; i < height; i++){
			g.addEdge(new Edge((width*height-(2-i)),width*height+1,inter[i][width-1],0));
		}
		return g;
	}
	/**
	 * TOTALEMENT INUTILE ONE NE FAIT PAS DE GRAPHE RESIDUEL !!
	 * @return
	 */
	public Graph reduceGraph(){
		Graph g = this;
		//for(int i = 0;i < g.adj.length-1;i++){
			//System.out.println("serieux ? "+(g.adj.length-1));
			//for (Edge e: g.adj(0)){
		Edge e;
				for(int i = 0;i<3;i++){
					e = g.adj(0).get(i);
				
				System.out.println("serieusement mec ? "+g.adj(0).get(i));
				if(e.from <= 0 && e.used > 0){
					System.out.println("serieusement mec ? ");
					g.addEdge(new Edge(e.to,e.from,-1,e.used));
					e.capacity = e.capacity-e.used;
					e.used = 0;
				}
			}
		//}
		return g;
	}

	public int vertices()
	 {
		return V;
	 }
   
   public void addEdge(Edge e)
	 {
		int v = e.from;
		int w = e.to;
		adj[v].add(e);
		adj[w].add(e);
	 }
   
   public final ArrayList<Edge> adj(int v)
	 {
		return adj[v];
	 }      
   
   public final Iterable<Edge> edges()
	 {
		ArrayList<Edge> list = new ArrayList<Edge>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj(v)) {
                if (e.to != v)
                    list.add(e);
            }
        return list;
    }
   
   
   public void writeFile(String s)
	 {
		try
		  {			 
			 PrintWriter writer = new PrintWriter(s, "UTF-8");
			 writer.println("digraph G{");
			 for (Edge e: edges())
			   writer.println(e.from + "->" + e.to + "[label=\"" + e.used  +"/" + e.capacity + "\"];");
			 writer.println("}");
			 writer.close();
		  }
		catch (IOException e)
		  {
		  }						
	 }
   
}
