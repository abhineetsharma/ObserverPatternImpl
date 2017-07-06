package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

/**
 * TreeBuilder class
 */
public class TreeBuilder {
    private Node root;

    public TreeBuilder() {
        root = null;
    }

    public void insertNodeIntoTree(Node node) {
        root = insertRecord(root, node);
    }

    private Node insertRecord(Node currentNode, Node node) {
        if (currentNode == null) {
            currentNode = node;
            return currentNode;
        }

        else if (node.id < currentNode.id)
            currentNode.left = insertRecord(currentNode.left, node);
        else if (node.id > currentNode.id)
            currentNode.right = insertRecord(currentNode.right, node);
        return currentNode;
    }


    public Node searchNode(int id) {
        return searchRec(root, id);
    }

    private Node searchRec(Node node, int id) {
        Node result = null;
        if (node == null)
            return null;
        else if (node.id == id)
            return node;
        else if (node.id > id)
            result = searchRec(node.left, id);
        else if (node.id < id)
            result = searchRec(node.right, id);

        return result;
    }

    public void printNode(Results results) {
        printNodeRec(results,root);
    }

    private void printNodeRec(Results results,Node node) {
        if (node != null) {
            printNodeRec(results,node.left);

            StringBuilder sbr = new StringBuilder("Courses : ");
            for(String str : node.courseList)
                sbr.append(String.format("%s ", str));
            if(node.courseList.size()==0)sbr.append("No course enrolled");
            results.storeNewResult(String.format("Student id : %d %s",node.id,sbr.toString() ));
            printNodeRec(results,node.right);
        }
    }

}
