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

    public void insertOrAddCourse(int key, String courseName) {
        root = insertRec(root, key, courseName);
    }

    Node insertRec(Node node, int key, String courseName) {
        if (node == null) {
            node = new Node(key);
            node.subjectList = new ArrayList <>();
            return node;
        }
        if (key == node.id && !node.subjectList.contains(courseName))
            node.subjectList.add(courseName);
        else if (key < node.id)
            node.left = insertRec(node.left, key, courseName);
        else if (key > node.id)
            node.right = insertRec(node.right, key, courseName);
        return node;
    }

    public void removeSubjectForId(int key, String courseName) {
        Node node = searchRec(root, key);
        if (null != node && node.subjectList.contains(courseName)) {
            node.subjectList.remove(new String(courseName));
        }
    }

    Node searchRec(Node node, int key) {
        Node result = null;
        if (node == null)
            return null;
        else if (node.id == key)
            return node;
        else if (node.id > key)
            result = searchRec(node.left, key);
        else if (node.id < key)
            result = searchRec(node.right, key);

        return result;
    }

    public void printNode() {
        printNodeRec(root);
    }

    void printNodeRec(Node root) {
        if (root != null) {
            printNodeRec(root.left);
            System.out.println(root.id + " " + root.subjectList + " ");
            printNodeRec(root.right);
        }
    }


}
