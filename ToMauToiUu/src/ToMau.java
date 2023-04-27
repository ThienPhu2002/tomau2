import java.util.ArrayList;

public class ToMau{
	
	static int matrixAdjacent[][] = {{-5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			  						 {-5, 0, 1, 2, 1, 1, 2, 2, 2, 2},
									 {-5, 1, 0, 1, 2, 2, 2, 2, 2, 2},
									 {-5, 2, 1, 0, 1, 2, 2, 1, 2, 2},
									 {-5, 1, 2, 1, 0, 1, 1, 2, 2, 2},
									 {-5, 1, 2, 2, 1, 0, 1, 2, 2, 1},
									 {-5, 2, 2, 2, 1, 1, 0, 2, 1, 1},
									 {-5, 2, 2, 1, 2, 2, 2, 0, 2, 2},
									 {-5, 2, 2, 2, 2, 2, 1, 2, 0, 2},
									 {-5, 2, 2, 2, 2, 1, 1, 2, 2, 0}
									} ;
	
	static ArrayList<Vertex> listVertex = new ArrayList<>();
	static ArrayList listColor = new ArrayList<>();
	
	public static void main(String []ar) {
		initialListVertex();
		sortFollowDegree();
		
		int indexVertex = 0;
		
		while(remainVertexDegreeGreaterThanZero()) {
			int indexDegreeGreaterBest = findVertexDegreeMax();
			
			paintForVertex(indexDegreeGreaterBest);
		}
		
		// to mau cho cac dinh con lai
		for(int i = 0 ; i<listVertex.size() ; i++) {
			if(listVertex.get(i).getIDColor() == 0) {
				paintColorNormally(i);
			}
		}
		
		
		output();
	}
	
	public static void output() {
		for(int i = 1; i<=4 ; i++) {
			listColor.add(i);
		}
		
		for(int i = 0; i<listColor.size() ; i++) {
			System.out.print("mau " +  (listColor.get(i))  + " to cho cac dinh: ");
			for(int j = 0 ; j<listVertex.size() ; j++ ) {
				if(listVertex.get(j).getIDColor() == (int)listColor.get(i)) {
					System.out.print(listVertex.get(j).getID() + "  ");
				}
			}
			System.out.println();
		}
	}
	
	public static void initialListVertex() {
		for(int i = 1 ; i<=9 ; i++) {
			listVertex.add(new Vertex(i, countDegree(i)));	
		}
	}
	
	public static int countDegree(int vertex) {
		int degree = 0;
		for(int colum = 1; colum<=9 ; colum++) {
			if(matrixAdjacent[vertex][colum] == 1)
				degree++;
		}
		return degree;
	}
	
	public static void sortFollowDegree() {
		for(int i = 0; i<listVertex.size() - 1; i++) {
			for(int j = i+1; j<listVertex.size(); j++) {
				int degree1 = listVertex.get(i).getDegree();
				int degree2 = listVertex.get(j).getDegree();
				Vertex vertex1 = listVertex.get(i);
				Vertex vertex2 = listVertex.get(j);
				if(degree1 < degree2) {
					listVertex.remove(i);
					listVertex.add(i, vertex2);
					
					listVertex.remove(j);
					listVertex.add(j, vertex1);
				}
			}
		}
	}
	
	public static boolean remainVertexDegreeGreaterThanZero() {
		for(int i = 0; i<listVertex.size(); i++) {
			if(listVertex.get(i).getDegree() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static int findVertexDegreeMax() {
		int index = -1;
		int degree = -1;
		for(int i = 0; i<listVertex.size(); i++) {
			if(listVertex.get(i).getDegree() > degree) {
				index = i;
				degree = listVertex.get(i).getDegree();
			}
		}
		return index;
	}
	
	public static void paintForVertex(int indexInList) {
		for(int i = 1; i <= 4; i++) {
			int flag = 0;
			for(int j = 0; j<listVertex.get(indexInList).getColorBan().size(); j++) {
				if(i == (int) listVertex.get(indexInList).getColorBan().get(j)) {
					flag = 1;
					break;
				}
			}
			
			if(flag == 0) {
				listVertex.get(indexInList).setIDColor(i);
				listVertex.get(indexInList).setDegree(0);
				MinusDegreeAdjacent(indexInList);
				break;
			}
		}
	}
	
	public static void paintColorNormally(int indexInList) {
		for(int i = 1; i <= 4; i++) {
			int flag = 0;
			for(int j = 0; j<listVertex.get(indexInList).getColorBan().size(); j++) {
				if(i == (int) listVertex.get(indexInList).getColorBan().get(j)) {
					flag = 1;
					break;
				}
			}
			
			if(flag == 0) {
				listVertex.get(indexInList).setIDColor(i);
			}
		}
	}
	
	public static void MinusDegreeAdjacent(int indexJudge) {
		for(int i = 0 ; i<listVertex.size() ; i++) {
			int row = listVertex.get(indexJudge).getID();
			int colum = listVertex.get(i).getID();
			if(matrixAdjacent[row][colum] == 1) {
				int IDColorBan = listVertex.get(indexJudge).getIDColor();
				listVertex.get(i).MinusDegree();
				listVertex.get(i).addColorBan(IDColorBan);
			}
		}
	}
}