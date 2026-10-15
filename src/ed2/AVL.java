package ed2;

public class AVL {
    private Node root;

    public AVL() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    private int altura(Node node) {
        if (node == null) {
            return 0;
        }

        int esquerda = altura(node.getLeft());
        int direita = altura(node.getRight());

        if (esquerda > direita) {
            return esquerda + 1;
        }

        return direita + 1;
    }

    private int fatorBalanceamento(Node node) {
        if (node == null) {
            return 0;
        }

        return altura(node.getLeft()) - altura(node.getRight());
    }

    public void insert(int key) {
        root = inserir(root, key, null);

        if (root != null) {
            root.setParent(null);
        }
    }

    private Node inserir(Node node, int key, Node parent) {
        if (node == null) {
            Node novo = new Node(key);
            novo.setParent(parent);
            return novo;
        }

        if (key < node.getKey()) {
            node.setLeft(inserir(node.getLeft(), key, node));
        } else if (key > node.getKey()) {
            node.setRight(inserir(node.getRight(), key, node));
        } else {
            return node;
        }

        return balancear(node);
    }

    public void remove(int key) {
        root = remover(root, key);

        if (root != null) {
            root.setParent(null);
        }
    }

    private Node remover(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.getKey()) {
            node.setLeft(remover(node.getLeft(), key));

            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
        } else if (key > node.getKey()) {
            node.setRight(remover(node.getRight(), key));

            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
        } else {
            if (node.getLeft() == null) {
                Node direita = node.getRight();

                if (direita != null) {
                    direita.setParent(node.getParent());
                }

                return direita;
            }

            if (node.getRight() == null) {
                Node esquerda = node.getLeft();

                if (esquerda != null) {
                    esquerda.setParent(node.getParent());
                }

                return esquerda;
            }

            Node sucessor = node.getRight();

            while (sucessor.getLeft() != null) {
                sucessor = sucessor.getLeft();
            }

            node.setKey(sucessor.getKey());
            node.setRight(remover(node.getRight(), sucessor.getKey()));

            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
        }

        return balancear(node);
    }

    private Node balancear(Node node) {
        if (node == null) {
            return null;
        }

        int fator = fatorBalanceamento(node);

        if (fator > 1) {
            if (fatorBalanceamento(node.getLeft()) < 0) {
                System.out.println("Rotação esquerda-direita (LR)");
                node.setLeft(rotacionarEsquerda(node.getLeft()));
                return rotacionarDireita(node);
            }

            System.out.println("Rotação simples à direita (LL)");
            return rotacionarDireita(node);
        }

        if (fator < -1) {
            if (fatorBalanceamento(node.getRight()) > 0) {
                System.out.println("Rotação direita-esquerda (RL)");
                node.setRight(rotacionarDireita(node.getRight()));
                return rotacionarEsquerda(node);
            }

            System.out.println("Rotação simples à esquerda (RR)");
            return rotacionarEsquerda(node);
        }

        return node;
    }

    private Node rotacionarDireita(Node node) {
        Node novaRaiz = node.getLeft();
        Node filho = novaRaiz.getRight();
        Node pai = node.getParent();

        novaRaiz.setParent(pai);

        if (pai == null) {
            root = novaRaiz;
        } else if (pai.getLeft() == node) {
            pai.setLeft(novaRaiz);
        } else {
            pai.setRight(novaRaiz);
        }

        novaRaiz.setRight(node);
        node.setParent(novaRaiz);

        node.setLeft(filho);

        if (filho != null) {
            filho.setParent(node);
        }

        return novaRaiz;
    }

    private Node rotacionarEsquerda(Node node) {
        Node novaRaiz = node.getRight();
        Node filho = novaRaiz.getLeft();
        Node pai = node.getParent();

        novaRaiz.setParent(pai);

        if (pai == null) {
            root = novaRaiz;
        } else if (pai.getLeft() == node) {
            pai.setLeft(novaRaiz);
        } else {
            pai.setRight(novaRaiz);
        }

        novaRaiz.setLeft(node);
        node.setParent(novaRaiz);

        node.setRight(filho);

        if (filho != null) {
            filho.setParent(node);
        }

        return novaRaiz;
    }

    public void imprimirArvore() {
        if (root == null) {
            System.out.println("Árvore vazia.");
            return;
        }

        System.out.println("Raiz: " + root.getKey());
        System.out.println("Dados dos nós:");

        imprimirNos(root);
    }

    private void imprimirNos(Node node) {
        if (node == null) {
            return;
        }

        String pai = "-";
        String esquerda = "-";
        String direita = "-";

        if (node.getParent() != null) {
            pai = String.valueOf(node.getParent().getKey());
        }

        if (node.getLeft() != null) {
            esquerda = String.valueOf(node.getLeft().getKey());
        }

        if (node.getRight() != null) {
            direita = String.valueOf(node.getRight().getKey());
        }

        System.out.println(
            "Chave: " + node.getKey()
            + " | Pai: " + pai
            + " | Filho esquerdo: " + esquerda
            + " | Filho direito: " + direita
            + " | Fator de balanceamento: "
            + fatorBalanceamento(node)
        );

        imprimirNos(node.getLeft());
        imprimirNos(node.getRight());
    }
}