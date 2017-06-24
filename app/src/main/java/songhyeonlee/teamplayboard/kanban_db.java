package songhyeonlee.teamplayboard;

/**
 * Created by Songhyeon on 2017-06-25.
 */

public class kanban_db {

    public String project_name;
    public String kanban_name;
    public String kanban_duedate;
    public String kanban_user;
    public String kanban_memo;
    public String state = "step1";


    public kanban_db() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public kanban_db(String kanban_name, String kanban_duedate, String kanban_memo) {
//        this.project_name = project_name;
        this.kanban_name = kanban_name;
        this.kanban_duedate = kanban_duedate;
//        this.kanban_user = kanban_user;
        this.kanban_memo = kanban_memo;
        this.state = "step1";
    }



    public kanban_db(String project_name, String kanban_name, String kanban_duedate, String kanban_memo, String state) {
        this.project_name = project_name;
        this.kanban_name = kanban_name;
        this.kanban_duedate = kanban_duedate;
//        this.kanban_user = kanban_user;
        this.kanban_memo = kanban_memo;
        this.state = state;
    }


    public kanban_db(String project_name, String kanban_name, String kanban_duedate, String kanban_user, String kanban_memo,String state) {
        this.project_name = project_name;
        this.kanban_name = kanban_name;
        this.kanban_duedate = kanban_duedate;
        this.kanban_user = kanban_user;
        this.kanban_memo = kanban_memo;
        this.state = state;
    }



    public String getProjectName() {
        return project_name;
    }

    public void setProjectName(String name) {
        project_name = name;
    }

    public String getKanban_name() {
        return kanban_name;
    }

    public void setKanban_name(String kanban_name) {
        this.kanban_name = kanban_name;
    }

    public String getKanban_duedate() {
        return kanban_duedate;
    }

    public void setKanban_duedate(String kanban_duedate) {
        this.kanban_duedate = kanban_duedate;
    }

    public String getKanban_user() {
        return kanban_user;
    }

    public void setKanban_user(String kanban_user) {
        this.kanban_user = kanban_user;
    }

    public String getKanban_memo() {
        return kanban_memo;
    }

    public void setKanban_memo(String kanban_memo) {
        this.kanban_memo = kanban_memo;
    }

    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
}

