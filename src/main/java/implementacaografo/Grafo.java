package implementacaografo;

import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
    public Grafo(boolean orientado) {
        this.orientado = orientado;
    }
    
    private boolean orientado;
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    
    private int ultimoVertice = 0;
    
    public void importarVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }
    
    public void adicionarAresta(Aresta aresta) {
        this.arestas.add(aresta);
        
        if(!this.orientado) {
            Aresta arestaVolta = new Aresta(aresta.getVerticePara(), aresta.getVerticeDe(), aresta.getDistancia());
            this.arestas.add(arestaVolta);
        }
    }
    
    public void adicionarVertice(Vertice vertice) {
        vertice.setNumero(ultimoVertice++);
        this.vertices.add(vertice);
    }
    
    public void conectar(Vertice verticeDe, Vertice verticePara) {
        this.conectar(verticeDe, verticePara, 1);
    }
    
    public void conectar(Vertice verticeDe, Vertice verticePara, int distancia) {
        Aresta aresta = new Aresta(verticeDe, verticePara, distancia);
        this.arestas.add(aresta);
        
        if(!this.orientado) {
            Aresta arestaVolta = new Aresta(verticePara, verticeDe, distancia);
            this.arestas.add(arestaVolta);
        }
    }
    
    public boolean ehVazio() {
        return vertices.isEmpty();
    }
    
    public int quantidadeVertices() {
        return vertices.size();
    }
    
    public int quantidadeArestas() {
        return arestas.size();
    }
    
    public void buscaEmLargura(int indice) {
        Fila<Vertice> filaVertice = new Fila<Vertice>();
         int matriz[][] = this.matrizDeAdjacencia();
        
        Vertice vertice = vertices.get(indice);
        filaVertice.insere(vertice);
        vertice.setVisitado();
        System.out.println(vertice.getRotulo());
        
        while(!filaVertice.vazia()) {
            Vertice verticeFila = filaVertice.remove();
            for(int i = 0; i < this.quantidadeVertices(); i++) {
                if(matriz[verticeFila.getNumero()][i] == 1) {
                    if(!vertices.get(i).getVisitado()) {
                        filaVertice.insere(vertices.get(i));
                        vertices.get(i).setVisitado();
                        System.out.println(vertices.get(i).getRotulo());
                    }
                }
            }
        }
    }
    
    public void buscaEmProfundidade(int indice) {
        Vertice vertice = vertices.get(indice);
        vertice.setVisitado();
        System.out.println(vertice.getRotulo());
        int matriz[][] = this.matrizDeAdjacencia();
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            if(matriz[indice][i] == 1) {
                if(!vertices.get(i).getVisitado()) {
                    System.out.print("DO " + vertices.get(indice).getRotulo() + " PARA O ");
                    buscaEmProfundidade(i);
                }
            }
        }
    }
    
    public void mostrarArestas() {
        int custo = 0;
        for(int i = 0; i < this.quantidadeArestas(); i+=2) {
            Aresta a = this.arestas.get(i);
            custo += a.getDistancia();
            System.out.println("DE " + a.getVerticeDe().getRotulo() + " PARA " + a.getVerticePara().getRotulo());
        }
        
        System.out.println("CUSTO DE: " + custo);
    }
    
    public void desenharMatrizAdjacencia() {
        int matriz[][] = this.matrizDeAdjacencia();
        
        System.out.println("*** Matriz de Adjacência ***");
        System.out.print("  ");
        for (int i = 0; i < this.quantidadeVertices(); i++) {
            System.out.print(vertices.get(i).getRotulo() + " ");
        }
        System.out.println();
        for (int i = 0; i < this.quantidadeVertices(); i++) {
            System.out.print(vertices.get(i).getRotulo() + " ");
            for (int j = 0; j < this.quantidadeVertices() ; j++) {
                System.out.print(matriz[i][j]+ "  ");
            }
            System.out.println();
        }
        
        for (int i = 0; i < this.quantidadeVertices(); i++) {
            System.out.print("Vértice " + vertices.get(i).getRotulo() + " está conectado em: ");
            for (int j = 0; j <this.quantidadeVertices() ; j++) {
                if(matriz[i][j] == 1) {
                    System.out.print(vertices.get(j).getRotulo() + " ");
                }
            }
            System.out.println();
        }
    }
    
    public int[][] matrizDeAdjacencia() {
        int matrizAdjacencia[][] = new int[this.quantidadeVertices()][this.quantidadeVertices()]; 
        //inicializando lista
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            matrizAdjacencia[i][i] = 0;
        }
        
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            for(int j = 0; j < this.quantidadeVertices(); j++) {
                if(vertices.get(i) == vertices.get(j)) {
                    matrizAdjacencia[i][j] = 1;
                    continue;
                }
                
                for(int a = 0; a < arestas.size(); a++) {
                    if(arestas.get(a).getVerticeDe() == vertices.get(i) &&
                        arestas.get(a).getVerticePara() == vertices.get(j))
                        matrizAdjacencia[i][j] = 1;
                }
            }
        }
        
        return matrizAdjacencia;
    }
    
    public void limparVisitas() {
        for(int i = 0; i < this.quantidadeVertices(); i++){
            vertices.get(i).setNaoVisitado();
        }
    }
    
    public ArrayList<Conjunto> componentesConexos() {
        ArrayList<Vertice> verticesVisitados = new ArrayList<Vertice>();
        ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>();
        this.limparVisitas();
        int contador = 0;
        
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            if(!vertices.get(i).getVisitado()){
                contador++;
                Conjunto c = new Conjunto();
                c.setNumero(contador);
                conjuntos.add(c);
                
                this.buscaEmProfundidade(i);
            }
            
            boolean jaExiste = false;
            for(int v = 0; v < verticesVisitados.size(); v++){
                if(vertices.get(i) == verticesVisitados.get(v))
                    jaExiste = true;
            }
                    
            if(!jaExiste) {
                conjuntos.get(contador - 1).addVertice(vertices.get(i));
                verticesVisitados.add(vertices.get(i));
            }
        }
        
        return conjuntos;
    }
    
    public Grafo kruskal() {
        Conjunto conjuntoFinal = new Conjunto();
        ArrayList<Conjunto> conjuntos = this.componentesConexos();
        if(conjuntos.size() > 1)
            return null;
        
        Collections.sort(this.arestas, new ArestaComparator());
        
        Conjunto c = conjuntos.get(0);
        
        Grafo novoGrafo = new Grafo(false);
        novoGrafo.importarVertices(this.vertices);
        
        int contArestas = 0;
        while((novoGrafo.quantidadeArestas() / 2) < novoGrafo.quantidadeVertices() - 1){
            Aresta a = this.arestas.get(contArestas);
            
            boolean pertenceDe = false;
            boolean pertencePara = false;
            for(int i = 0; i < conjuntoFinal.getVertices().size(); i++) {
                if(conjuntoFinal.getVertices().get(i) == a.getVerticeDe())
                    pertenceDe = true;
                if(conjuntoFinal.getVertices().get(i) == a.getVerticePara())
                    pertencePara = true;
            }
            
            if(!pertenceDe || !pertencePara) {
                novoGrafo.adicionarAresta(a);
                
                if(!pertenceDe)
                    conjuntoFinal.addVertice(a.getVerticeDe());
                if(!pertencePara)
                    conjuntoFinal.addVertice(a.getVerticePara());
                
            }
            contArestas += 2;
        }
        
        return novoGrafo;
    }
}
