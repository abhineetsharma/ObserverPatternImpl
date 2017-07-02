package studentCoursesBackup.myTree;

import java.util.ArrayList;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Node {
    public int id;
    public ArrayList<String> subjectList;
    public Node left, right;

    public Node(int item)
    {
        id = item;
        left = right = null;
    }
}
