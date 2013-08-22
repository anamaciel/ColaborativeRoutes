package br.com.ubibus.listadinamica;
public class ListaDinamica<E> implements Lista<E> {

    private No prim;
    private No fim;
    private int tamanho;

    @Override
    public void add(E elemento) {
        this.verificaElementoNull(elemento);
        if(this.isEmpty()){
            this.addFirst(elemento);
        }else{
            No novo = new No(elemento);
            this.fim.proximo = novo;
            this.fim = novo;
            this.tamanho++;
        }        
    }

    @Override
    public void add(E elemento, int index) throws IndexOutOfBoundsException {
        this.verificaElementoNull(elemento);
        this.verificaPosicaoValida(index);
        if (index == 0){
            this.addFirst(elemento);
        }else if (index == this.size() ){
            this.add(elemento);
        }else{
            No novo = new No(elemento);
            No aux = this.getNo(index-1);
            novo.proximo = aux.proximo;
            aux.proximo = novo;
            this.tamanho++;
        }
    }
    
    private void addFirst(E elemento){
        No novo = new No(elemento);
        novo.proximo = this.prim;
        this.prim = novo;
        if(this.isEmpty()){
            this.fim = novo; 
        }
        this.tamanho++;
    }    

    @Override
    public E remove(int index) {
        this.verificaPosicaoPreenchida(index);
        if(index==0){
            return this.removeFirst();
        }else if(index==this.size()-1){
            return this.removeLast();
        }else{
            No anterior = this.getNo(index-1);
            No removido = anterior.proximo;
            anterior.proximo = removido.proximo;
            this.tamanho--;
            return (E)removido.dado;
        }       
    }

    @Override
    public int remove(E elemento) {
        if(this.isEmpty()){
            return -1;
        }
        if(elemento.equals(prim.dado)){
            this.removeFirst();
            return 0;
        }else if(elemento.equals(fim.dado)){
            this.removeLast();
            return this.size();
        }else{
            No anterior = this.prim;
            No removido = this.prim.proximo;
            int pos;
            for(pos = 1; pos < this.size(); pos++){
                if(elemento.equals(removido.dado)){
                    break;
                }
                anterior = removido;
                removido = removido.proximo;
            }                
            if(removido != null){
                anterior.proximo = removido.proximo;
                removido.proximo = null;
                this.tamanho--;
                return pos;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        this.verificaPosicaoValida(index);
        No aux = this.getNo(index);
        return (E) aux.dado;
    }

    @Override
    public E set(int index, E elemento) {
        this.verificaPosicaoValida(index);
        No aux = this.getNo(index);
        No substituido = aux;
        aux.dado = elemento;
        return (E)substituido.dado;        
    }

    @Override
    public int indexOf(E elemento) {
        No aux = new No(elemento);
        int pos = 0;
        aux = prim;
        while(aux != null){
            if(aux.dado.equals(elemento)){
                return pos;
            }
            aux = aux.proximo;
            pos++;
        }
        return -1;
    }

    @Override
    public boolean contains(E elemento) {
        return this.indexOf(elemento) != -1;
    }

    @Override
    public int size() {
        return this.tamanho;
    }

    @Override
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public String toString() {
        if(this.isEmpty()){
            return "[]";
        }
        
        StringBuilder conteudo = new StringBuilder();
        conteudo.append("[");
        No aux = this.prim;
        while (aux != null) {
            conteudo.append(aux.dado);
            if (aux.proximo != null) {
                conteudo.append(",");
            }
            aux = aux.proximo;
        }
        conteudo.append("]");
        return conteudo.toString();
    }
    
    private void verificaElementoNull(E elemento){
        if(elemento == null){
            throw new NullPointerException("O elemento não pode ser nulo");
        }
    }
    
    private void verificaPosicaoValida(int index){
        if(index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException("Index " +index + ", Size " + this.size());
        }
    }
    
     private void verificaPosicaoPreenchida(int index){
        if(index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException("Index " +index + ", Size " + this.size());
        }
    }
    
    private No getNo(int index) {
        this.verificaPosicaoPreenchida(index);
        No aux = this.prim;
        for (int i = 0; i < index; i++) {
            aux = aux.proximo;
        }
        return aux;
    }
    
    private E removeFirst(){
        this.verificaPosicaoPreenchida(0);
        No removido = this.prim;
        this.prim = this.prim.proximo;
        this.tamanho--;
        if(this.isEmpty()){
            this.fim = null;
        }
        return (E)removido.dado;
    }
    
    private E removeLast(){
        this.verificaPosicaoPreenchida(this.size()-2);
        if(this.size()==1){
            return this.removeFirst();
        }        
        No removido = this.fim;
        No penultimo = this.getNo(this.size()-2);
        penultimo.proximo = null;
        this.fim = penultimo;
        this.tamanho--;
        return (E)removido.dado;
    }
}