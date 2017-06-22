package songhyeonlee.teamplayboard;

/**
 * Created by Songhyeon on 2017-06-22.
 */

public class Comment {

    public String email;
  //  public String name;
  //  public String author;
    public String text;

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String uid, String name, String text) {
        this.email = uid;
    //    this.name = name;
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
