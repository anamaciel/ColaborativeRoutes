package br.com.ubibus.ws;

import br.com.ubibus.model.pojo.LinhasTrechos;
import java.sql.SQLException;
import java.util.List;
import org.postgis.Geometry;
import org.postgis.LineString;

/**
 *
 * @author <a href="mailto:marvas1987@gmail.com">Marcelo F. Vasconcelos</a>
 */
public class TrechosSort {

    public static void sortList(List<LinhasTrechos> list, int esquerda, int direita) {
        int esq = esquerda;
        int dir = direita;
        LinhasTrechos pivo = list.get((esq + dir) / 2);
        LinhasTrechos troca;

        while (esq <= dir) {
            while (list.get(esq).getSequencia() < pivo.getSequencia()) {
                esq = esq + 1;
            }
            while (list.get(dir).getSequencia() > pivo.getSequencia()) {
                dir = dir - 1;
            }
            if (esq <= dir) {
                troca = list.get(esq);
                list.set(esq, list.get(dir));
                list.set(dir, troca);
                esq = esq + 1;
                dir = dir - 1;
            }
        }
        if (dir > esquerda) {
            sortList(list, esquerda, dir);
        }

        if (esq < direita) {
            sortList(list, esq, direita);
        }
    }
}
