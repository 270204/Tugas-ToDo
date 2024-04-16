package ap.mobile.to_do;

public class Activity {
    public String id;
    public String what;
    public String time;

    public Activity(String id, String toDo, String time){
        this.id = id;
        this.what = toDo;
        this.time = time;
    }

    public String getId() {
        return this.id;
    }

    public String getToDo() {
        return this.what;
    }

    public String getTime() {
        return this.time;
    }
}
