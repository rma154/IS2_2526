package es.unican.is2.practica5b;

import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;

/**
 * Clase que implementa el ADT ConjuntoOrdenado.
 *
 * @param <E> tipo de los elementos, que deben tener orden natural
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

    private List<E> lista = new ArrayList<E>();

    public E get(int indice) {
        return lista.get(indice);
    }

    public boolean add(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }

        int indice = 0;
        while (indice < lista.size() && elemento.compareTo(lista.get(indice)) > 0) {
            indice++;
        }

        if (indice < lista.size() && elemento.compareTo(lista.get(indice)) == 0) {
            return false;
        }

        lista.add(indice, elemento);
        return true;
    }

    public E remove(int indice) {
        E borrado = lista.remove(indice);
        return borrado;
    }

    public int size() {
        return lista.size();
    }

    public void clear() {
        lista.clear();
    }
}
