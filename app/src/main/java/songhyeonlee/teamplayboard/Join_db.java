package songhyeonlee.teamplayboard;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.author;

/**
 * Created by Songhyeon on 2017-06-22.
 */

public class Join_db {

    public String name;
    public String email;
    public String id;
    public String pw;
    public static int cnt=0;

    public Join_db() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Join_db(String name, String uid, String id, String pw) {
        this.name = name;
        this.email = uid;
        this.id = id;
        this.pw = pw;
        cnt++;
    }


    public void fromMap(Map<String, Object> map){
        name = (String)map.get("name");
        email = (String)map.get("email");
        id = (String)map.get("id");
        pw = (String)map.get("pw");

        // starCount = (int) map.get("starCount");
        // stars = (Map<String, Boolean>) map.get("stars");

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("id", id);
        result.put("pw", pw);

        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(String pw) {
        this.cnt = cnt;
    }

}
