package TreeStructure;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(10);

        System.out.println(tree);
        System.out.println(tree.getVertexCnt());
/*        tree.worker = ()-> System.out.println();
        tree.worker = new Workable() {
            @Override
            public void represent() {
                System.out.println("fdsf");
            }
        };
        tree.work();*/
    }
}
