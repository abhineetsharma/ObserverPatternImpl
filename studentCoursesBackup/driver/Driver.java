package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Logger;
import studentCoursesBackup.util.TreeBuilder;


/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Driver {
    public static void main(String[] args) {
        try {
            if (null != args && args.length > 0) {
                String inputFile = args[0];
                Logger.log(String.format("Input File: %s", inputFile));
                FileProcessor inputFileProcessor = new FileProcessor(inputFile);

                String deleteFile = null;


                deleteFile = args[1];
                Logger.log(String.format("delete File: %s", deleteFile));
                FileProcessor deleteFileProcessor = new FileProcessor(deleteFile);
                //Results results = new Results(outputFile);

                TreeBuilder tree1 = new TreeBuilder();
                TreeBuilder tree2 = new TreeBuilder();
                TreeBuilder tree3 = new TreeBuilder();
                String str;
                while ((str = inputFileProcessor.readLine()) != null) {
                    int id = Integer.parseInt(str.split(":")[0]);
                    String courseName = str.split(":")[1];

                    Node node;
                    if ((node = tree1.searchNode(id)) != null) {
                        node.addCourse(courseName);
                        for (ObserverI n : node.getObserverList()) {
                            Node nd = (Node) n;
                            nd.addCourse(courseName);
                        }

                    } else {
                        node = new Node(id, courseName);
                        tree1.insertNodetoTree(node);

                        ObserverI nodeBackUp1 = node.clone();
                        tree2.insertNodetoTree((Node) nodeBackUp1);
                        node.registerObserver(nodeBackUp1);

                        ObserverI nodeBackUp2 = node.clone();
                        tree3.insertNodetoTree((Node) nodeBackUp2);
                        node.registerObserver(nodeBackUp2);
                    }


                }
                System.out.print("Tree1\n");
                tree1.printNode();
                System.out.print("Tree2\n");
                tree2.printNode();
                System.out.print("Tree3\n");
                tree3.printNode();

                while ((str = deleteFileProcessor.readLine()) != null) {
                    int id = Integer.parseInt(str.split(":")[0]);
                    String courseName = str.split(":")[1];

                    Node node;
                    if ((node = tree1.searchNode(id)) != null) {
                        node.removeCourse(courseName);
                        for (ObserverI n : node.getObserverList()) {
                            Node nd = (Node) n;
                            nd.removeCourse(courseName);
                        }
                    }
                }
                System.out.print("\nDelete\nTree1\n");
                tree1.printNode();
                System.out.print("Tree2\n");
                tree2.printNode();
                System.out.print("Tree3\n");
                tree3.printNode();
//                results.writeToStdout();
//                if (null != outputFile)
//                    results.writeToFile();

            } else {
                System.out.println("No arguments pass, Input file needed for execution");
                System.exit(1);
            }
        } finally {
            Logger.stopLogging();
        }
    }
}

