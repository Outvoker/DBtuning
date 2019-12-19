package Merge;

public class Competition {
    private int u_id;//学生学号
    private int comp_id;//奖项编号
    private String comp_title;//奖项名称
    private String comp_class;//奖项等级
    private String comp_type;//奖项类型
    private String comp_date;//获奖时间

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getComp_id() {
        return comp_id;
    }

    public void setComp_id(int comp_id) {
        this.comp_id = comp_id;
    }

    public String getComp_title() {
        return comp_title;
    }

    public void setComp_title(String comp_title) {
        this.comp_title = comp_title;
    }

    public String getComp_class() {
        return comp_class;
    }

    public void setComp_class(String comp_class) {
        this.comp_class = comp_class;
    }

    public String getComp_type() {
        return comp_type;
    }

    public void setComp_type(String comp_type) {
        this.comp_type = comp_type;
    }

    public String getComp_date() {
        return comp_date;
    }

    public void setComp_date(String comp_date) {
        this.comp_date = comp_date;
    }
}
