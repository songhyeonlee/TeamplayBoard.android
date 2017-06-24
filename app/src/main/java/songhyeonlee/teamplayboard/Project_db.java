package songhyeonlee.teamplayboard;

/**
 * Created by Songhyeon on 2017-06-23.
 */

public class Project_db {

    public String project_name;
    public String project_duedate;
    public String projcet_leader;
  //  public String[] project_member;

  //  public int member_num;
   // public static int project_num;
    //public static int project_num=0;

    public Project_db() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Project_db(String project_name, String project_duedate, String projcet_leader) {
        this.project_name = project_name;
        this.project_duedate = project_duedate;
        this.projcet_leader = projcet_leader;
    //    this.project_member[0] = projcet_leader;
    //    member_num = 1;
       // project_num++;
    }

//    public Project_db(String project_name, String project_duedate, String projcet_leader, String project_member) {
//        this.project_name = project_name;
//        this.project_duedate = project_duedate;
//        this.projcet_leader = projcet_leader;
//        this.project_member[member_num++] = project_member;
      //  project_num++;
  //  }

    public String getProjectName() {
        return project_name;
    }

    public void setProjectName(String name) {
        project_name = name;
    }

    public String getProject_duedate() {
        return project_duedate;
    }

    public void setProject_duedate(String project_duedate) {
        this.project_duedate = project_duedate;
    }

    public String getProject_leader() {
        return projcet_leader;
    }

    public void setProjcet_leader(String projcet_leader) {
        this.projcet_leader = projcet_leader;
    }
 //   public String[] getProject_member() {
//        return project_member;
  //  }

//    public void setProject_member(String member[]){
  //      this.project_member = member;
 //   }

//    public int getMember_num(){
//        return member_num;
//    }

//    public void setMember_num(int member_num){
//        this.member_num = member_num;
//    }

//    public int getProject_num() {
//        return project_num;
//    }

//    public void setProject_num(int num) {
//        num = project_num;
//    }

}
