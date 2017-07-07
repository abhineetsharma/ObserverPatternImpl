package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;

/**
 * TreeBuilder class
 */
public class TreeBuilder {
    private Node root;

    public TreeBuilder() {
        root = null;
    }

    private void insertNodeIntoTree(Node node) {
        root = insertRecord(root, node);
    }

    private Node insertRecord(Node currentNode, Node node) {
        if (currentNode == null) {
            currentNode = node;
            return currentNode;
        }
        else if (node.getId() < currentNode.getId())
            currentNode.setLeft(insertRecord(currentNode.getLeft(), node));
        else if (node.getId() > currentNode.getId())
            currentNode.setRight(insertRecord(currentNode.getRight(), node));
        return currentNode;
    }

    private Node searchRec(Node node, int id) {
        Node result = null;
        if (node == null)
            return null;
        else if (node.getId() == id)
            return node;
        else if (node.getId() > id)
            result = searchRec(node.getLeft(), id);
        else if (node.getId() < id)
            result = searchRec(node.getRight(), id);

        return result;
    }

    private void printNodeRec(Results results,Node node) {
        if (node != null) {
            printNodeRec(results,node.getLeft());

            StringBuilder sbr = new StringBuilder("Courses : ");
            for(String str : node.getCourseList())
                sbr.append(String.format("%s ", str));
            if(node.getCourseList().size()==0)sbr.append("No course enrolled");
            results.storeNewResult(String.format("Student id : %d %s",node.getId(),sbr.toString() ));
            printNodeRec(results,node.getRight());
        }
    }

    public Node searchNode(int id) {
        return searchRec(root, id);
    }

    public void printNode(Results results) {
        printNodeRec(results,root);
    }

    public String insertNode(int id,String courseName,TreeBuilder backupTree2, TreeBuilder backupTree3){
        Node node;
        if ((node = searchNode(id)) != null) {
            node.addCourse(courseName);
            node.notifyAllObservers("insert",courseName);

        } else {
            node = new Node(id, courseName);
            insertNodeIntoTree(node);

            ObserverI nodeBackUp1 = node.clone();
            if (null != nodeBackUp1) {
                backupTree2.insertNodeIntoTree((Node) nodeBackUp1);
                node.registerObserver(nodeBackUp1);
            }
            else{
                Logger.log("nodeBackUp1 is null");
            }

            ObserverI nodeBackUp2 = node.clone();
            if (null != nodeBackUp2) {
                backupTree3.insertNodeIntoTree((Node) nodeBackUp2);
                node.registerObserver(nodeBackUp2);
            }
            else{
                Logger.log("nodeBackUp2 is null");
            }
        }
        return String.format("Courses Enrolled : %s",node.getCourseList());
    }

    public String removeCourseFromNode(int id,String courseName){
        Node node;

        if ((node = searchNode(id)) != null) {
            node.removeCourse(courseName);
            node.notifyAllObservers("delete",courseName);
        }
        return node.getCourseList().size()>0 ?node.getCourseList().toString():"No course enrolled";
    }
}
