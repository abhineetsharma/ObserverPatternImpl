package studentCoursesBackup.driver;

import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.Logger;


/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Driver {
    public static void main(String[] args) {
        try {
            if (null != args && args.length > 0) {
                String inputFile = args[0];
                Logger.log(String.format("Input File: %s", inputFile));
                FileProcessor fileProcessor = new FileProcessor(inputFile);

                String outputFile = null;
                if (args.length > 1 && args[1].trim().length()>0) {
                    outputFile = args[1];
                    Logger.log(String.format("Output File: %s", outputFile));
                }
                Results results = new Results(outputFile);

                String str;
                while ((str = fileProcessor.readLine()) != null) {

                }
                results.writeToStdout();
                if (null != outputFile)
                    results.writeToFile();

            } else {
                System.out.println("No arguments pass, Input file needed for execution");
                System.exit(1);
            }
        } finally {
            Logger.stopLogging();
        }
    }
}

