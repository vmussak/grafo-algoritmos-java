package implementacaografo;

import java.util.ArrayList;

/**
 *
 * @author Vinicius Mussak
 */
public class Caminho {
    public Caminho(Vertice destino) {
        this.destino = destino;
    }
    
    private Vertice destino;
    private int custo;
    private ArrayList<Vertice> caminhoPercorrido = new ArrayList<Vertice>();
    
    public void setCusto(int custo) {
        this.custo = custo;
    }
    
    public void adicionarCaminhoPercorrido(Vertice vertice) {
        caminhoPercorrido.add(vertice);
    }
    
    public int getCusto() {
        return this.custo;
    }
    
    public Vertice getDestino() {
        return this.destino;
    }
    
    public ArrayList<Vertice> getCaminho() {
        return caminhoPercorrido;
    }
    
    public void mostrarCaminho() {
        for(int i = caminhoPercorrido.size() - 1; i > 0 ; i--) {
            System.out.print(caminhoPercorrido.get(i).getRotulo() + " -> ");
        }

        System.out.print(destino.getRotulo());
        System.out.println(" (" + this.getCusto() + ") ");
    }
    
}
