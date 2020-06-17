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
        Vertice v7 = new Vertice("V7");
        Vertice v8 = new Vertice("V8");
        
        Grafo grafo = new Grafo(true);
        grafo.adicionarVertice(v1);
        grafo.adicionarVertice(v2);
        grafo.adicionarVertice(v3);
        grafo.adicionarVertice(v4);
        grafo.adicionarVertice(v5);
        grafo.adicionarVertice(v6);
        grafo.adicionarVertice(v7);
        grafo.adicionarVertice(v8);
        
        /*grafo.conectar(v1, v6);
        grafo.conectar(v1, v4);
        grafo.conectar(v1, v2);
        grafo.conectar(v1, v5);
        
        grafo.conectar(v2, v4);
        grafo.conectar(v2, v3);
        
        grafo.conectar(v3, v4);
        
        grafo.conectar(v4, v5);
        
        grafo.conectar(v5, v6);*/
        
        /*grafo.conectar(v1, v2, 16);
        grafo.conectar(v1, v6, 21);
        grafo.conectar(v1, v5, 19);
        
        grafo.conectar(v2, v6, 11);
        grafo.conectar(v2, v3, 5);
        grafo.conectar(v2, v4, 6);
        
        grafo.conectar(v3, v4, 10);
        
        grafo.conectar(v4, v6, 14);
        grafo.conectar(v4, v5, 18);
        
        grafo.conectar(v5, v6, 33);*/
        
        
        
        //Grafo g = grafo.arvoreCustoMinimokruskal();
       //g.mostrarArestas();
       
       
       grafo.conectar(v5, v4, 150);
       grafo.conectar(v5, v6, 25);
       
       grafo.conectar(v4, v3, 120);
       
       grafo.conectar(v3, v2, 80);
       grafo.conectar(v3, v1, 100);
       
       grafo.conectar(v2, v1, 30);
       
       grafo.conectar(v6, v7, 90);
       grafo.conectar(v6, v4, 100);
       grafo.conectar(v6, v8, 140);
       
       grafo.conectar(v7, v8, 100);
       
       grafo.conectar(v8, v1, 170);
       
       ArrayList<Caminho> c = grafo.caminhoMaisCurtoDijkstra(4);
       for(Caminho item : c) {
           item.mostrarCaminho();
       }
       
        
        
        
    }
}
