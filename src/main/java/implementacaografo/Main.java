package implementacaografo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Vertice v1 = new Vertice("V1");
        Vertice v2 = new Vertice("V2");
        Vertice v3 = new Vertice("V3");
        Vertice v4 = new Vertice("V4");
        Vertice v5 = new Vertice("V5");
        Vertice v6 = new Vertice("V6");
        
        Grafo grafo = new Grafo(false);
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);
        grafo.adicionarVertice(v5);
        grafo.adicionarVertice(v6);
        
        /*grafo.conectar(v1, v6);
        grafo.conectar(v1, v4);
        grafo.conectar(v1, v2);
        grafo.conectar(v1, v5);
        
        grafo.conectar(v2, v4);
        grafo.conectar(v2, v3);
        
        grafo.conectar(v3, v4);
        
        grafo.conectar(v4, v5);
        
        grafo.conectar(v5, v6);*/
        
        grafo.conectar(v1, v2, 16);
        grafo.conectar(v1, v6, 21);
        grafo.conectar(v1, v5, 19);
        
        grafo.conectar(v2, v6, 11);
        grafo.conectar(v2, v3, 5);
        grafo.conectar(v2, v4, 6);
        
        grafo.conectar(v3, v4, 10);
        
        grafo.conectar(v4, v6, 14);
        grafo.conectar(v4, v5, 18);
        
        grafo.conectar(v5, v6, 33);
        
        Grafo g = grafo.kruskal();
        g.mostrarArestas();
        
        
        
    }
}
