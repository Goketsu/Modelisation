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
	
	public static void main(String[] args){
		int[][] tab =  {{3,11,24,39},
			  	  		{8,21,29,39},
			  	  		{74,80,100,200},
						};
		
		int[][] itr = interestUp(tab);
		for(int i = 0;i < itr.length; i++){
			for(int j = 0; j < itr[0].length; j++){
				System.out.println(itr[i][j]);
			}
		}
	}

}
