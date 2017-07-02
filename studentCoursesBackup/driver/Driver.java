package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Driver {
    public static void main(String[] args) {
        TreeBuilder tree = new TreeBuilder();

        for (int i = 1; i < 60; i = i + 10) {
            tree.insertOrAddCourse(i, "A");
            tree.insertOrAddCourse(i, "A");
            tree.insertOrAddCourse(i, "B");
            tree.insertOrAddCourse(i, "C");
            tree.removeSubjectForId(i, "A");
            tree.removeSubjectForId(i, "B");
        }

        tree.printNode();



    }
}
