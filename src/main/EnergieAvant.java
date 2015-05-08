package main;

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
	
	public static void main(String[] args){
		int[][] tab =  {{3,11,24,39},
			  	  		{8,21,29,39},
			  	  		{74,80,100,200},
						};
		int[][] itrR = interestRight(tab);
		int[][] itrD = interestDown(tab);
		int[][] itrU = interestUp(tab);
		Graph g = EnergieAvant.toGraph(itrR,itrD,itrU);
		g.writeFile("energie_graph");
		
		
		for(int i = 0;i < itrR.length; i++){
			for(int j = 0; j < itrR[0].length; j++){
				System.out.println(itrR[i][j]);
			}
		}
	}

}
