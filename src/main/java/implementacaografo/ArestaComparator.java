package implementacaografo;

import java.util.Comparator;

public class ArestaComparator implements Comparator<Aresta> {
    @Override
    public int compare(Aresta o1, Aresta o2) {
        return o1.getDistancia() - o2.getDistancia();
    }
}
