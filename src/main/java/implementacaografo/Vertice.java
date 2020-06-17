package implementacaografo;

import java.util.ArrayList;
import java.util.Set;

public class Vertice {
    public Vertice() {
        
    }
    
    public Vertice(String rotulo) {
        this.rotulo = rotulo;
    }
    
    private String rotulo;
    private int numero;
    private boolean visitado;
    
    public String getRotulo() {
        if(rotulo != null && !rotulo.isEmpty())
            return rotulo;
        else
            return String.valueOf(numero);
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setVisitado() {
        this.visitado = true;
    }
    
    public void setNaoVisitado() {
        this.visitado = false;
    }
    
    public boolean getVisitado() {
        return this.visitado;
    }
}
