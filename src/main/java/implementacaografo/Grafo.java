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
        Collections.sort(this.arestas, new ArestaComparator());
        int custo = 0;
        for(int i = 0; i < this.quantidadeArestas(); i+= (this.orientado ? 1 : 2)) {
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
            for(int j = 0; this.quantidadeVertices() < 1; j++){
                matrizAdjacencia[i][j] = 0;
            }
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
    
    public int[][] matrizDeCustoAdjacencia() {
        int matrizAdjacencia[][] = new int[this.quantidadeVertices()][this.quantidadeVertices()]; 
        //inicializando lista
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            for(int j = 0; j < this.quantidadeVertices(); j++){
                matrizAdjacencia[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            for(int j = 0; j < this.quantidadeVertices(); j++) {
                if(vertices.get(i) == vertices.get(j)) {
                    matrizAdjacencia[i][j] = 0;
                    continue;
                }
                
                for(int a = 0; a < arestas.size(); a++) {
                    if(arestas.get(a).getVerticeDe() == vertices.get(i) &&
                        arestas.get(a).getVerticePara() == vertices.get(j))
                        matrizAdjacencia[i][j] = arestas.get(a).getDistancia();
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
    
    public int contadorKruskal(Grafo novoGrafo) {
        return novoGrafo.orientado ? novoGrafo.quantidadeArestas() : (novoGrafo.quantidadeArestas() / 2);
    }
    
    public Grafo arvoreCustoMinimokruskal() {
        Conjunto conjuntoFinal = new Conjunto();
        ArrayList<Conjunto> conjuntos = this.componentesConexos();
        if(conjuntos.size() > 1)
            return null;
        
        Collections.sort(this.arestas, new ArestaComparator());
        
        Conjunto c = conjuntos.get(0);
        
        Grafo novoGrafo = new Grafo(this.orientado);
        novoGrafo.importarVertices(this.vertices);
        int contArestas = 0;
        
        while(contadorKruskal(novoGrafo) < novoGrafo.quantidadeVertices() - 1){
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
            contArestas += novoGrafo.orientado ? 1 : 2;
        }
        
        return novoGrafo;
    }
    
    private int somar(int a, int b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return a + b;
    }
    
    public ArrayList<Caminho> caminhoMaisCurtoDijkstra(int indice) {
        boolean visitas[] = new boolean[this.quantidadeVertices()];
        int distancia[] = new int[this.quantidadeVertices()];
        int custos[][] = this.matrizDeCustoAdjacencia();
        ArrayList<Caminho> retorno = new ArrayList<Caminho>();
        
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            visitas[i] = false;
            distancia[i] = Integer.MAX_VALUE;
            retorno.add(new Caminho(vertices.get(i)));
        }
        
        visitas[indice] = true;
        distancia[indice] = 0;
        
        Vertice v = vertices.get(indice);
        
        for(int i = 0; i < this.quantidadeArestas(); i++)
            if(arestas.get(i).getVerticeDe() == v)
                distancia[arestas.get(i).getVerticePara().getNumero()] = arestas.get(i).getDistancia();
        
        int cont = 1;
        
        while(cont < this.quantidadeVertices() - 1) {
            int menor = 0;
            for(int i = 0; i < this.quantidadeVertices(); i++) {
                if(distancia[i] < distancia[menor] && visitas[i] == false) {
                    menor = i;
                }
            }

            visitas[menor] = true;

            for(int i = 0; i < this.quantidadeVertices(); i++) {
                if(visitas[i] == false) {
                    if(distancia[i] > somar(distancia[menor], custos[menor][i])) {
                        retorno.get(i).adicionarCaminhoPercorrido(vertices.get(menor));
                        distancia[i] = somar(distancia[menor], custos[menor][i]);
                    }
                }
            }
            
            cont++;
        }
        
        for(int i = 0; i < this.quantidadeVertices(); i++) {
            retorno.get(i).setCusto(distancia[i]);
        }
        
        return retorno;
        
    }
}
