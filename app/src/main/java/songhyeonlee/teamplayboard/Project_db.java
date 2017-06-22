package songhyeonlee.teamplayboard;

/**
 * Created by Songhyeon on 2017-06-23.
 */

public class Project_db {

    public String name;
    public String[]  user;
    //public date;
    public static int cnt_p=0;

    public Project_db() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Project_db(String name) {
        this.name = name;
        cnt_p++;
    }

    public String getProjectName() {
        return name;
    }

    public void setProjectName(String name) {
        this.name = name;
    }

    public int getCnt_p() {
        return cnt_p;
    }

    public void setCnt_p(int cnt) {
        cnt = cnt_p;
    }}
