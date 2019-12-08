package TreeStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private TreeNode root;
    public int[] gI = {0,0,0,0,0};
    private int vertexCnt;
    private LinkedList<TreeNode> terminalVertexes = new LinkedList<>();
    private HashMap<Integer, LinkedList<TreeNode>> hierarchy = new HashMap<>();
    private final Random generator = new Random();

    public TreeNode getRoot() {
        return root;
    }

    public int getVertexCnt() {
        return vertexCnt;
    }

    public HashMap<Integer, LinkedList<TreeNode>> getHierarchy(){
        return hierarchy;
    }

    public LinkedList<TreeNode> getTerminalVertexes() {
        return terminalVertexes;
    }

    private TreeNode rootInitialize(int maxChildCnt, boolean isRandom) {
        LinkedList<TreeNode> rootLevel = new LinkedList<>();
        TreeNode root = new TreeNode(1, 0, 1);
        int childCnt = 0;
        rootLevel.add(root);
        hierarchy.put(1, rootLevel);
        if(isRandom) {
            while (childCnt == 0) {
                childCnt = generator.nextInt(maxChildCnt);
                gI[childCnt]++;
            }
        }else childCnt = maxChildCnt;
        root.childList = new LinkedList<>();
        for (int i = 2; i <= childCnt + 1; i++) {
            root.childList.add(new TreeNode(i, 1, 2));
        }
        hierarchy.put(2, root.childList);
        return root;
    }

    Tree(int maxVertexCnt, int maxChildCnt, boolean isRandom) {
        File file = new File("graphics.txt");
        try (FileWriter writer = new FileWriter(file)) {
            this.root = rootInitialize(maxChildCnt, isRandom);
            Deque<TreeNode> nodeQueue = new LinkedList<>();
            int curNodeIndex = this.root.childList.size() + 1;
            int extremeHierarchyLevel = 0;
            boolean isFirstOverFlowed = false;
            int parentIndex = root.index;
            for (TreeNode childNode : root.childList) {
                if (childNode != null) {
                    nodeQueue.addLast(childNode);
                }
            }
            int index = 1;
            while (!nodeQueue.isEmpty()) {
                writer.write(Integer.toString(index));
                writer.write("\t");
                writer.write(Integer.toString(curNodeIndex));
                writer.write("\t");
                writer.write(Double.toString(Math.round((double)curNodeIndex/((double)nodeQueue.size() + terminalVertexes.size() )* 1000.0) / 1000.0));
                writer.write("\n");
                index++;
                TreeNode processedNode = nodeQueue.removeFirst();
                int childCnt;
                if (isRandom) {
                    childCnt = generator.nextInt(maxChildCnt);
                    gI[childCnt]++;
                } else childCnt = maxChildCnt;

                if (childCnt == 0) {
                    terminalVertexes.add(processedNode);
                    parentIndex++;
                    continue;
                }
                if (curNodeIndex + 1 > maxVertexCnt && processedNode.hierarchyLevel != extremeHierarchyLevel) {
                    terminalVertexes.add(processedNode);
                    for (TreeNode node : nodeQueue) {
                        terminalVertexes.add(node);
                    }
                    break;
                }
                processedNode.childList = new LinkedList<>();
                parentIndex++;
                for (int i = 0; i < childCnt; i++) {
                    curNodeIndex++;
                    int childHierarchyLevel = processedNode.hierarchyLevel + 1;
                    TreeNode childNode = new TreeNode(curNodeIndex, parentIndex, childHierarchyLevel);
                    if (!hierarchy.containsKey(childHierarchyLevel)) {
                        hierarchy.put(childHierarchyLevel, new LinkedList<TreeNode>());
                    }
                    hierarchy.get(childHierarchyLevel).add(childNode);
                    processedNode.childList.add(childNode);
                }
                if (curNodeIndex >= maxVertexCnt && !isFirstOverFlowed) {
                    extremeHierarchyLevel = processedNode.hierarchyLevel;
                    isFirstOverFlowed = true;
                }
                for (TreeNode childNode : processedNode.childList) nodeQueue.addLast(childNode);
            }
            this.vertexCnt = curNodeIndex;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TreeNode {
        LinkedList<TreeNode> childList;
        int index;
        int parentIndex;
        int hierarchyLevel;

        TreeNode(int index, int parentIndex, int hierarchyLevel) {
            this.index = index;
            this.parentIndex = parentIndex;
            this.hierarchyLevel = hierarchyLevel;
        }

        @Override
        public String toString() {
            return "(" + index + "-" + parentIndex /*+ "-" + (childList != null ? childList.size() : 0 )*/ + ")";
        }

    }

    public String noChild(){
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.addLast(root);
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (!nodeQueue.isEmpty()) {
            TreeNode processedNode = nodeQueue.removeFirst();
            if((processedNode.childList != null ? processedNode.childList.size() : 0) == 0) {
                builder.append(processedNode.index);
                builder.append(", ");
            }
            if (processedNode.childList == null) continue;
            for (TreeNode childNode : processedNode.childList) nodeQueue.addLast(childNode);
        }
        builder.append("]");
        return builder.toString();
    }

    public double getAlphaValue(){
        return (double) vertexCnt / (double) terminalVertexes.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= hierarchy.size(); i++) {
            builder.append(hierarchy.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}

