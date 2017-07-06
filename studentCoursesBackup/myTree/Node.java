package studentCoursesBackup.myTree;

import java.util.ArrayList;

/**
 * Created by abhineetsharma on 6/29/17.
 */
public class Node implements ObserverI, SubjectI, Cloneable {
    public int id;
    public ArrayList <String> courseList;
    public Node left, right;

    private ArrayList <ObserverI> observerList;

    public Node(int idI, String courseName) {
        id = idI;
        left = right = null;
        courseList = new ArrayList <>();
        courseList.add(courseName);

    }

    public void addCourse(String courseName) {
        if (null == courseList) {
            courseList = new ArrayList <>();
        }
        if (!courseList.contains(courseName))
            courseList.add(courseName);
    }

    public void removeCourse(String courseName) {
        if (null != courseList && courseList.contains(courseName))
            courseList.remove(courseName);
    }

    @Override
    public void registerObserver(ObserverI observer) {
        if (null == getObserverList())
            setObserverList(new ArrayList <>());
        getObserverList().add(observer);
    }

    @Override
    public void removeObserver(ObserverI observer) {
        int index = observerList.indexOf(observer);
        if (index > -1){
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyAllObservers(String courseName) {
        for (ObserverI node : observerList)
            node.update(courseName);
    }


    @Override
    public Node clone() {
        Node clone = null;
        try {
            clone = (Node) super.clone();
            clone.id = this.id;
            clone.courseList = new ArrayList <>(this.courseList);
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return clone;
    }

    public ArrayList <ObserverI> getObserverList() {
        return observerList;
    }

    private void setObserverList(ArrayList <ObserverI> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void update( String courseName) {
        Node node = this;
        node.removeCourse(courseName);
    }
}

