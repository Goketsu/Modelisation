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
	
	//System.out.println("largeur : "+width);
	//System.out.println("hauteur : "+height);
	
	//Graph g = new Graph(width*height+2);
	//int[][] inter = SeamCarving.interest(itr);
	/*
	for(i = 0 ; i < height; i++){
		g.addEdge(new Edge(0,i+1,-1,0));
	}
	*/
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
				this.addEdge(new Edge(oriArc, destArc, itr[j][i],0));nb++;
			//g.addEdge(new Edge(width*i+j+1-i, height*(i+1)+j+1, inter[j][i],0));
			
			if(j>0){
				//System.out.println("arc : " + (destArc -1) + " -> " + oriArc);
				this.addEdge(new Edge(destArc -1, oriArc, -1,0));nb++;
			}
			
			//System.out.println("arc : " + destArc + " -> " + oriArc);
			this.addEdge(new Edge(destArc, oriArc, -1,0));nb++;
			
			if(j!=height-1){
				//System.out.println("arc : " + (destArc +1) + " -> " + oriArc);
				this.addEdge(new Edge(destArc +1, oriArc, -1,0));
				nb++;
			}
			
			//}
		}
	}
	//System.out.println("nb : " + nb);
	//System.out.println("\n");
	
	for(i = 1 ; i <= height; i++){
		//System.out.println("arc : " + 0 + " -> " + i);
		this.addEdge(new Edge(0, i,-1,0));
	}
	
	for(i = 0 ; i < height; i++){
		//System.out.println("arc : " + ((height*width -height +1 +i)) + " -> " +(width*height+1));
		this.addEdge(new Edge((height*width -height +1 +i),width*height+1,itr[i][width-1],0));
	}
	
	return this;
	}
	
	
	public int getAdjLength(){
		return this.adj.length;
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
		  // System.out.println("dammit ! "+adj[v]);
	 }
   
   public final ArrayList<Edge> adj(int v)
	 {
		return adj[v];
	 }      
   
   public final Iterable<Edge> edges()
	 {
		ArrayList<Edge> list = new ArrayList<Edge>();
        for (int v = 0; v < V; v++){
        	//System.out.println("NOPE ! "+adj(0));
            for (Edge e : adj(v)) {
                if (e.to != v)
                    list.add(e);
            }
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
