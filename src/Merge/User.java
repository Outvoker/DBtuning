package Merge;

public class User {
    private int user_id;//用户编号
    private int user_age;//用户年龄
    private int user_level;//用户星级
    private String user_name;//用户姓名
    private String user_tel;//用户联系方式
    private String user_gender;//用户性别
    private String user_address;//用户送餐地址

    public int getUser_id(){
        return user_id;
    }
    public void setUser_id(int user_id){
        this.user_id = user_id;
    }
    public int getUser_age(){
        return user_age;
    }
    public void setUser_age(int user_age){
        this.user_age = user_age;
    }
    public int getUser_level() {
        return user_level;
    }
    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_tel() {
        return user_tel;
    }
    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }
    public String getUser_gender() {
        return user_gender;
    }
    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }
    public String getUser_address() {
        return user_address;
    }
    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }
}
