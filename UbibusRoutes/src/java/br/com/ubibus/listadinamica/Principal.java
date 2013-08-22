package br.com.ubibus.listadinamica;

public class Principal {
    public static void main(String[] args) {
        Lista<String> lista = new ListaDinamica<String>();
  
        lista.add("a");
        lista.add("b");
        lista.add("c");
        lista.add("d");
        System.out.println(lista);
        
        System.out.println("Posição Removida: "+ lista.remove("d"));
        System.out.println(lista);
        
        System.out.println("Contem: "+ lista.contains("a"));
        
        System.out.println("Posição: "+ lista.indexOf("d"));
        
        System.out.println("GET: "+ lista.get(2));
        
        lista.set(0, "Marlon");
        lista.set(1, "Astrogilda");
        lista.set(2, "Jurubeba");
        
        System.out.println(lista);
    }
}
