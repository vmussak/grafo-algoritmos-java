package implementacaografo;

import java.util.Comparator;

public class Aresta {
    public Aresta(Vertice verticeDe, Vertice verticePara, int distancia){
        this.verticeDe = verticeDe;
        this.verticePara = verticePara;
        this.distancia = distancia;
    }
    
    public Aresta(Vertice verticeDe, Vertice vertice2){
        this.verticeDe = verticeDe;
        this.verticePara = verticePara;
        this.distancia = 1;
    }
    
    private Vertice verticeDe;    
    private Vertice verticePara;
    private int distancia;
    
    public Vertice getVerticeDe() {
        return verticeDe;
    }
    
    public Vertice getVerticePara() {
        return verticePara;
    }
    
    public int getDistancia() {
        return this.distancia;
    }
}


