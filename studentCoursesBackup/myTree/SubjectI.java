package studentCoursesBackup.myTree;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public interface SubjectI {
    void registerObserver(ObserverI observer);
    void removeObserver(ObserverI observer);
    void notifyAllObserver();
}
