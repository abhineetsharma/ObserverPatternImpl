package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

import java.util.ArrayList;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public class TreeBuilder {
    Node root;

    public TreeBuilder() {
        root = null;
    }

    public void insertNodetoTree(Node node) {
        root = insertRec(root, node);
    }

    Node insertRec(Node currentNode, Node node) {
        if (currentNode == null) {
            currentNode = node;

            return currentNode;
        }

        else if (node.id < currentNode.id)
            currentNode.left = insertRec(currentNode.left, node);
        else if (node.id > currentNode.id)
            currentNode.right = insertRec(currentNode.right, node);
        return currentNode;
    }

//    public void removeSubjectForId(Node dataNode) {
//        Node node = searchRec(root, dataNode.id);
//        if (null != node && node.courseList.contains(dataNode.courseList.get(0))) {
//            node.courseList.remove(new String(dataNode.courseList.get(0)));
//        }
//    }

    public Node searchNode(int id) {
        return searchRec(root, id);
    }

    Node searchRec(Node node, int id) {
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

    public void printNode() {
        printNodeRec(root);
    }

    void printNodeRec(Node root) {
        if (root != null) {
            printNodeRec(root.left);
            System.out.println(root.id + " " + root.courseList + " ");
            printNodeRec(root.right);
        }
    }

}
