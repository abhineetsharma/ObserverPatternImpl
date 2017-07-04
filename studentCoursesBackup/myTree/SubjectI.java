package studentCoursesBackup.myTree;

import studentCoursesBackup.util.TreeBuilder;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public interface SubjectI {
    void registerObserver(ObserverI observer);
    void removeObserver(ObserverI observer);
    void notifyObserver();
}
