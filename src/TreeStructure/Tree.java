package TreeStructure;

import java.util.*;

/**
 * Вариант #5
 * m = 5 - возможное количество потомков у узла
 * N = 200 - общее количество узлов
 * R = 100 - количество графов для построения
 * Правило остагновки Б) - число узлов >= заданному числу N && последний уровень  иерархии графа должен быть до конца
 * заполнен  висячими узлами
 */

 class Tree {

    private TreeNode rootElement;
    private int vertexCnt;
    private Vector<Integer> leavesIndexes = new Vector<>();
    private HashMap<Integer, LinkedList<TreeNode>> hierarchy = new HashMap<>();
    private final Random generator = new Random();

    public TreeNode getRootElement() {
        return rootElement;
    }

    public int getVertexCnt() {
        return vertexCnt;
    }

    static class TreeNode {
        LinkedList<TreeNode> childList;
        int index;
        int parentIndex;
        int hierarchyLevel;

        TreeNode(int index, int parentIndex) {
            this.index = index;
            this.parentIndex = parentIndex;
        }
        TreeNode(int index, int parentIndex, int hierarchyLevel) {
            this.index = index;
            this.parentIndex = parentIndex;
            this.hierarchyLevel= hierarchyLevel;
        }

        @Override
        public String toString() {
            return "TreeNode{#"+index+", " +
                            "P#"+parentIndex+", "+
                            "Ch:"+ (childList != null ? childList.size() : 0) +"}";
        }
    }

    private TreeNode rootInitialize() {
        LinkedList<TreeNode> rootLevel = new LinkedList<>();
        TreeNode root = new TreeNode(1, 0,1);
        rootLevel.add(root);
        hierarchy.put(1, rootLevel);
        int childCnt = generator.nextInt(5);
        root.childList = new LinkedList<>();
        for (int i = 2; i <= childCnt + 1; i++) {
            root.childList.add(new TreeNode(i, 1,2));
        }
        hierarchy.put(2, root.childList);
        return root;
    }

    Tree(int N) {
        TreeNode root = rootInitialize();
        this.rootElement = root;
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        int curNodeIndex = root.childList.size() + 1;
        int extremeHierarchyLevel = 0;
        boolean isFirstOverFlowed = false;
        int parentIndex = root.index;
        for (TreeNode childNode : root.childList) {
            if (childNode != null) {
                nodeQueue.addLast(childNode);
            }
        }
        while (!nodeQueue.isEmpty()) {
            TreeNode processedNode = nodeQueue.removeFirst();
            int childCnt = generator.nextInt(5);
            if (childCnt == 0){
                parentIndex += 1;
                continue;
            }
            if (curNodeIndex + 1 > N && processedNode.hierarchyLevel != extremeHierarchyLevel) break;
            processedNode.childList = new LinkedList<>();
            parentIndex += 1;
            for (int i = 0; i < childCnt; i++) {
                curNodeIndex += 1;
                int childHierarchyLevel = processedNode.hierarchyLevel + 1;
                TreeNode childNode = new TreeNode(curNodeIndex, parentIndex, childHierarchyLevel);
                // Создаем уровень в мапе, если его еще не было и вычесляем
                if (!hierarchy.containsKey(childHierarchyLevel)) {
                    hierarchy.put(childHierarchyLevel, new LinkedList<>());
                }
                // Кладем в уровень иерархии ноду
                hierarchy.get(childHierarchyLevel).add(childNode);
                processedNode.childList.add(childNode);
            }
            //if(curNodeIndex == N && hierarchy.get(processedNode.hierarchyLevel)
            if (curNodeIndex > N && !isFirstOverFlowed){
                extremeHierarchyLevel = processedNode.hierarchyLevel;
                isFirstOverFlowed = true;
            }
            if (curNodeIndex <= N) for (TreeNode childNode : processedNode.childList) nodeQueue.addLast(childNode);
        }
        this.vertexCnt = curNodeIndex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= hierarchy.size(); i++){
            builder.append(hierarchy.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}

