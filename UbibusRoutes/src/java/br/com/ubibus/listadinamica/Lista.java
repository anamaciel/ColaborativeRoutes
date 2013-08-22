package br.com.ubibus.listadinamica;
/**
 * A interface <code>Lista</code> define o Tipo Abstrado de Dados Lista
 * 
 * <p>Esta classe tem como único e exclusivo objetivo auxiliar o ensino
 * dos conteúdos da disciplina de Estrutura de Dados do curso de Tecnologia em
 * Sistemas para Internet da UTFPR - Câmpus Campo Mourão.
 * 
 * @param <E> o tipo do elemento mantido na lista.
 * 
 * @author Prof. Rafael Liberato Roberto
 * @see fila.Fila
 * @see pilha.Pilha
 * @version 1.0
 *
 */
public interface Lista<E> {
    
    /**
     * Insere o especificado elemento no fim da lista.
     * 
     * @param     elemento Elemento a ser inserido na lista.
     * @throws     NullPointerException se o elemento for igual a <tt>null</tt>
     */
    void add(E elemento);
    
    /**
     * Insere o especificado elemento em uma especificada posição. 
     * 
     * @param      elemento  Elemento a ser inserido
     * @param      index  Índice em que o elemento será inserido.
     * @throws     NullPointerException se o elemento for igual a <tt>null</tt>
     * @throws     IndexOutOfBoundsException se o índice for inválido
     */
    void add(E elemento, int index);
    
   /**
     * Remove e retorna o elemento da especificada posição.
     * 
     * @param      index Índice do elemento que será removido.
     * @return     o elemento removido.
     * @throws     IndexOutOfBoundsException se o índice for inválido
     */
    E remove(int index);
    
    /**
     * Remove a instância do especificado elemento da lista. 
     * 
     * @param      elemento
     * @return     a posição do elemento removido ou -1 caso o elemento não exista
     * @throws     NullPointerException se o elemento for igual a <tt>null</tt>
     */
    int remove(E elemento);
    
    /**
     * Retorna o elemento que está na especificada posição na lista.
     * 
     * @param      index Índice do elemento a ser retornado.
     * @return     o elemento da especificada posição na lista.
     * @throws     IndexOutOfBoundsException se o índice for inválido.
     */
    E get(int index);
    
    /**
     * Substitui o elemento que está na posição <code>index</code> pelo elemento
     * recebido por parâmetro. Retorna o elemento que foi substituído.
     * 
     * @param      index Índice do elemento a ser substituído.
     * @param      elemento Novo Elemento a ser atribuído.
     * @return     o elemento que foi substituído.
     * @throws     IndexOutOfBoundsException se o índice for inválido.
     */
    E set(int index, E elemento);
    
    /**
     * Procura pela primeira ocorrência do especificado elemento na lista,
     * testando a igualdade pelo método <code>equals</code>.
     * 
     * @param      elemento Elemento a ser procurado.
     * @return     o índice do elemento na lista. Retorna <code>-1</code> se o
     *             elemento não for encontrado.
     * @throws     NullPointerException se o elemento for igual a <tt>null</tt>
     */
    int indexOf(E elemento);
    
     /**
     * Retorna <code>true</code> se a lista contém o especificado elemento.
     *
     * @param      elemento Elemento a ser procurado.
     * @return     <code>true</code> se o elemeto está contido na lista.
     */
    boolean contains(E elemento);
    
    /**
     * Retorna o número de elementos da lista.
     * @return o número de elementos da lista.
     * @throws     NullPointerException se o elemento for igual a <tt>null</tt>
     */
    int size();
    
    /**
     * Retorna <code>true</code> se a lista está vazia.
     * @return <code>true</code> se a lista está vazia.
     */
    boolean isEmpty();
}

