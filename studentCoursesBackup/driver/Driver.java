package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Logger;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;


/**
 * Driver
 */
public class Driver {
    class StudentInfo {
        final int id;
        final String courseName;

        StudentInfo(int idI, String courseNameI) {
            id = idI;
            courseName = courseNameI;
        }
    }

    private StudentInfo processString(String str) {
        StudentInfo studentInfo = null;
        try {
            String[] strArr = str.split(":");
            if (strArr.length == 2) {
                int id = Integer.parseInt(strArr[0]);
                String courseName = strArr[1];
                studentInfo = new StudentInfo(id, courseName);
            }

        } catch (NumberFormatException ex) {
            Logger.log(String.format("error in processing string %s",str));
            Logger.log(ex.getMessage());
        }
        return studentInfo;
    }

    public static void main(String[] args) {

        Results results = new Results("");
        results.storeNewResult("Process started..");
        try {
            if (null != args && args.length > 0) {
                String inputFile = args[0];
                Logger.log(String.format("Input File: %s", inputFile));
                FileProcessor inputFileProcessor = new FileProcessor(inputFile);

                TreeBuilder tree1 = new TreeBuilder();
                TreeBuilder backupTree2 = new TreeBuilder();
                TreeBuilder backupTree3 = new TreeBuilder();
                String str;

                Driver driver = new Driver();

                //insert into the tree
                while ((str = inputFileProcessor.readLine()) != null) {

                    StudentInfo st = driver.processString(str);
                    if (null == st)
                        continue;
                    int id = st.id;
                    String courseName = st.courseName;

                    Node node;
                    if ((node = tree1.searchNode(id)) != null) {
                        node.addCourse(courseName);
                        for (ObserverI n : node.getObserverList()) {
                            Node nd = (Node) n;
                            nd.addCourse(courseName);
                        }

                    } else {
                        node = new Node(id, courseName);
                        tree1.insertNodeIntoTree(node);

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


                }

                if (args.length > 1) {
                    String deleteFile = args[1];
                    Logger.log(String.format("delete File: %s", deleteFile));
                    FileProcessor deleteFileProcessor = new FileProcessor(deleteFile);

                    //delete course from a tree
                    while ((str = deleteFileProcessor.readLine()) != null) {
                        StudentInfo studentInfo = driver.processString(str);

                        if (null == studentInfo) continue;

                        int id = studentInfo.id;
                        String courseName = studentInfo.courseName;

                        Node node;
                        if ((node = tree1.searchNode(id)) != null) {
                            node.removeCourse(courseName);
                            node.notifyAllObservers(courseName);
                        }
                    }
                } else {
                    Logger.log("Delete file needed for execution");
                    results.storeNewResult("Error : Delete file needed for execution");
                    results.writeToStdout();
                    Logger.stopLogging();
                    System.exit(1);
                }

                //Result
                Results results1 = null, results2 = null, results3 = null;
                if (args.length > 2 && args[2].trim().length() > 0) {
                    String outputFile1 = args[2];
                    Logger.log(String.format("output 1 File: %s", outputFile1));
                    results1 = new Results(outputFile1);
                }
                else {
                    Logger.log("Error : No output file 1 present");
                    results.storeNewResult("Output 1 file needed for execution");
                    results.writeToStdout();
                    Logger.stopLogging();
                    System.exit(1);
                }
                if (args.length > 3 && args[3].trim().length() > 0) {
                    String outputFile2 = args[3];
                    Logger.log(String.format("output 2 File: %s", outputFile2));
                    results2 = new Results(outputFile2);
                }
                else {
                    Logger.log("No output file 2 present");
                    results.storeNewResult("Error::Output 2 file needed for execution");
                    results.writeToStdout();
                    Logger.stopLogging();
                    System.exit(1);
                }
                if (args.length > 4 && args[4].trim().length() > 0) {
                    String outputFile3 = args[4];
                    Logger.log(String.format("output 3 File: %s", outputFile3));
                    results3 = new Results(outputFile3);
                }
                else {
                    Logger.log("No output file 3 present");
                    results.storeNewResult("Error:Output 3 file needed for execution");
                    results.writeToStdout();
                    Logger.stopLogging();
                    System.exit(1);
                }

                if (null != results1) {
                    tree1.printNode(results1);
                    results1.writeToFile();
                }
                if (null != results2) {
                    backupTree2.printNode(results2);
                    results2.writeToFile();
                }
                if (null != results3) {
                    backupTree3.printNode(results3);
                    results3.writeToFile();
                }
                results.storeNewResult("Done, 3 output file generated");

            } else {
                results.storeNewResult("Error: No arguments pass, Input file needed for execution");
                Logger.stopLogging();
                results.writeToStdout();
                System.exit(1);
            }
        } finally {
            Logger.stopLogging();
            results.writeToStdout();
        }
    }
}

