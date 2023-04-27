import java.util.ArrayList;
import java.util.List;

public class Vertex{
	
	private int ID;
	
	private int degree;
	
	private int IDColor;
	
	private List colorBan;
	
	Vertex(int ID){
		this.ID = ID;
		this.IDColor = 0;
		colorBan = new ArrayList<>();
	}
	
	Vertex(int ID, int degree){
		this.ID = ID;
		this.degree = degree;
		this.IDColor = 0;
		colorBan = new ArrayList<>();
	}
	
	public void MinusDegree() {
		this.degree = this.degree - 1;
	}
	
	public void addColorBan(int IDColorBan) {
		colorBan.add(IDColorBan);
	}
	
	public List getColorBan() {
		return this.colorBan;
	}
	
	public int getIDColor() {
		return this.IDColor;
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	public void setIDColor(int IDColor) {
		this.IDColor = IDColor;
	}
}