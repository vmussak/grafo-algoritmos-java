package implementacaografo;

import java.util.ArrayList;

public class Conjunto {
    private int numero;
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void addVertice(Vertice v) {
        vertices.add(v);
    }
    
    public ArrayList<Vertice> getVertices() {
        return vertices;
    }
}
