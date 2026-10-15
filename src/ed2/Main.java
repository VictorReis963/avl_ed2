//Victor Reis da Silva  ra:10420297


package ed2;

public class Main {

    public static void main(String[] args) {

        AVL arvore;

        System.out.println("===== ITEM A =====");
        arvore = new AVL();
        arvore.insert(1);
        arvore.insert(2);
        arvore.insert(3);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM B =====");
        arvore = new AVL();
        arvore.insert(3);
        arvore.insert(2);
        arvore.insert(1);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM C =====");
        arvore = new AVL();
        arvore.insert(3);
        arvore.insert(1);
        arvore.insert(2);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM D =====");
        arvore = new AVL();
        arvore.insert(1);
        arvore.insert(3);
        arvore.insert(2);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM E =====");
        arvore = new AVL();
        arvore.insert(5);
        arvore.insert(4);
        arvore.insert(3);
        arvore.insert(1);
        arvore.insert(2);
        arvore.insert(6);
        arvore.insert(7);
        arvore.insert(9);
        arvore.insert(8);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM F =====");
        arvore.remove(4);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM G =====");
        arvore.remove(5);
        arvore.imprimirArvore();

        System.out.println("\n===== ITEM H =====");
        arvore.remove(1);
        arvore.imprimirArvore();
    }
}