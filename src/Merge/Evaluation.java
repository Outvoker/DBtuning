package Merge;

public class Evaluation {
    private int evaluation_id;//评价编号
    private int order_id;//订单编号
    private int employee_id;//外卖员编号
    private int order_grade;//订单评分
    private int employee_grade;//外卖员评分

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getOrder_grade() {
        return order_grade;
    }

    public void setOrder_grade(int order_grade) {
        this.order_grade = order_grade;
    }

    public int getEmployee_grade() {
        return employee_grade;
    }

    public void setEmployee_grade(int employee_grade) {
        this.employee_grade = employee_grade;
    }
}
