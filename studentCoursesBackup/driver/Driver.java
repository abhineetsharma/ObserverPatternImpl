package studentCoursesBackup.driver;

import studentCoursesBackup.util.TreeBuilder;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Driver {
    public static void main(String[] args) {
        TreeBuilder tree1 = new TreeBuilder();
        TreeBuilder tree2 = new TreeBuilder();
        TreeBuilder tree3 = new TreeBuilder();

        for (int i = 1; i < 60; i = i + 10) {
            tree1.insertOrAddCourse(i, "A");
            tree1.insertOrAddCourse(i, "A");
            tree1.insertOrAddCourse(i, "B");
            tree1.insertOrAddCourse(i, "C");
            tree1.removeSubjectForId(i, "A");
            tree1.removeSubjectForId(i, "B");
            tree1.insertOrAddCourse(i, "A");
            tree1.insertOrAddCourse(i, "A");
            tree1.insertOrAddCourse(i, "B");
            tree1.insertOrAddCourse(i, "C");
            tree1.removeSubjectForId(i, "A");
            tree1.removeSubjectForId(i, "B");
        }

        tree1.printNode();



    }
}
