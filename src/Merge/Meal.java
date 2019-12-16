package Merge;

public class Meal {
    private int meal_id;//菜品编号
    private String meal_name;//菜品名称
    private int meal_price;//菜品价格
    private int people_counting;//菜品适合人数
    private String deliver_cost;//是否需要配送费

    public int getMeal_id(){
        return meal_id;
    }
    public void setMeal_id(int meal_id){
        this.meal_id = meal_id;
    }

    public String getMeal_name(){
        return meal_name;
    }
    public void setMeal_name(String meal_name){
        this.meal_name = meal_name;
    }

    public int getMeal_price(){
        return meal_price;
    }
    public void setMeal_price(int meal_price){
        this.meal_price = meal_price;
    }

    public int getPeople_counting(){
        return people_counting;
    }
    public void setPeople_counting(int people_counting){
        this.people_counting = people_counting;
    }

    public String getDeliver_cost(){
        return deliver_cost;
    }
    public void setDeliver_cost(String deliver_cost){
        this.deliver_cost = deliver_cost;
    }
}
