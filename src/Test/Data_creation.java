package Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Data_creation {

    private static final int MEAL_TYPE = 100;                //菜品的种类（菜品表）
    private static final int USER_SIZE = 10000;              //用户的规模（用户表）
    private static final int EMPLOYEE_SIZE = 1000;           //外卖员的数量（外卖员表）
    private static final int ORDER_SIZE = 10000000;          //订单的数量（订单表）
    private static final int EVALUATION_SIZE = 1000;         //评价的数量（评价表）


    //菜品表：菜品编号、菜品名称、价格、适合人数、是否需要配送费
    public static void create_Meal (String filePath) throws IOException{

        long startTime=System.currentTimeMillis();
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String [] meal_type = {"五香熏鱼","红油肚丝","拌卤猪耳","卤猪头肉","五香牛肉","卤鸭","白斩鸡","拍黄瓜","四川泡菜","炝萝卜","家常卤黄豆","糖醋红蒜","盐水鸭热菜","红烧鲤鱼","干烧鲤鱼","酸菜鱼","水煮鱼","红烧鲫鱼","清蒸鲫鱼","干烧武昌鱼","清蒸武昌鱼","清蒸带鱼","糖醋带鱼","老干妈炒鳝片","响油鳝糊","面拖小黄鱼","清蒸黄鱼","干烧鲈鱼","干烧罗非鱼","清蒸平鱼","糖醋平鱼","清蒸鳕鱼","干烧明虾","红烧小龙虾","酱油大虾","香辣蟹","姜葱炒肉蟹","剁椒鱼头","红烧甲鱼","葱姜炒竹蛏","香辣田螺","东坡肉","鱼香肉丝","青椒肉丝","木樨肉","回锅肉","农家小炒肉","百页结烧肉","香煎猪排","糖醋排骨","黄豆焖猪蹄","火爆腰花","五香卤猪肝","药芹肚丝","士豆烧牛肉","孜然羊肉","葱爆羊肉","板栗烧鸡","重庆辣子鸡","宫保鸡丁","番茄炒鸡蛋","黄瓜炒鸡蛋","蒸蛋羹","香辣四季豆","蒜香荷兰豆","西芹炒百合","米饭茄子","白菜炖豆腐","家常豆腐","尖椒土豆丝","剁椒粉皮","虾子茭白","地三鲜汤","鲫鱼豆腐汤","雪菜大汤黄鱼","姜丝鲈鱼汤","黑鱼煨汤","黄颡鱼鸡蛋汤","芙蓉银鱼汤","银耳乳鸽汤","榨菜肉丝汤","酸辣汤","大豆排骨汤","菠菜猪肝汤","扁尖老鸭汤","人参炖鸡","滋补乌骨鸡","西红柿鸡蛋汤","绿豆粥","花生红枣粥","小米粥","担担面","家常肉丝炒面","榨菜肉末拌面","番茄肉酱面","炸酱面","油条","葱油烙饼","窝窝头","扬州炒饭"};
        String [] delivery_cost = {"有","无"};
        int i = 1;
        int num ;
        for(;i <= MEAL_TYPE;i++){
            String meal_name = meal_type[i];//菜品名称
            String meal_price = String.valueOf((int)(Math.random()*(50 - 20 + 1) + 20));//菜品价格
            String people_counting = String.valueOf((int)(Math.random()*(3 - 1 + 1) + 1));//菜品适合人数
            num = (int)(Math.random()*(3 - 1 + 1) + 1);
            String deliver_cost = delivery_cost[num];//是否需要配送费
            String str = i + " " + meal_name + " " + meal_price + " " + people_counting + " " + deliver_cost;
            pw.format("%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件
        }

        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("菜品表创建成功，已写入"+ i +"行数据!,耗时="+(endTime-startTime)+"ms");
    }


    //用户编号、用户姓名、用户性别、用户年龄、用户电话、用户送餐地址、用户星级
    public static void create_User(String filePath) throws IOException{


        long startTime=System.currentTimeMillis();

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String[] firstName={"李","王","张","刘","陈","杨","赵","黄","周","吴","徐","孙","胡","朱","高","林","何","郭","马","罗","梁","宋","郑","谢","韩","唐","冯","于","董","萧","程","曹","袁","邓","许","傅","沈","曾","彭","吕","苏","卢","蒋","蔡","贾","丁","魏","薛","叶","阎","余","潘","杜","戴","夏","钟","汪","田","任","姜","范","方","石","姚","谭","廖","邹","熊","金","陆","郝","孔","白","崔","康","毛","邱","秦","江","史","顾","侯","邵","孟","龙","万","段","漕","钱","汤","尹","黎","易","常","武","乔","贺","赖","龚","文"};
        String[] nextName={"彬蔚","彬彬","博裕","博文","博艺","承德","澄泓","澹雅","得韬","德辉","方旭","飞轩","和畅","涵畅","浩初","浩然","泓涵","泓浵","嘉言","江沅","瑾瑜","静淞","俊慧","俊杰","俊爽","俊雄","俊誉","峻雅","歌凯","孔阳","可佳","澜清","乐心","乐仪","乐康","乐成","霖霖","灵犀","曼衍","曼语","琦玮","如山","若飞","若光","若华","胜蓝","腾逸","恬如","恬适","恬雅","望舒","文澜","文心","文轩","文雅","文懿","昕昕","信芳","驰星","星阑","星轩","修远","煦涵","雅厚","雅亮","怡然","熠然","潆泓","羽丰","语心","悠然","煜月","悦心","子衿","自珍","朝辉","亮","星"};
        String[] telFirst={"130","131","132","133","134","135","136","137","138","139","147","150","151","152","153","155","156","157","158","159","187","188"};
        String[] road={"南京路","淮海路","新北站街道","朱剪炉街道","皇城街道","大西街道","山东庙街道","风雨坛街道","滨河街道","万莲街道","大南街道","五里河街道","西塔街道","南湖街道","马路湾街道","新华街道","集贤街道","八经街道","长白街道","北市场街道","太原街街道","南市场街道","浑河湾街道","沈水湾街道","小北街道","大北街道","万泉街道","洮昌街道","文官街道","二台子街道","津桥街道","东站街道","辽沈街道","珠林街道","长安街道","新东街道","东塔街道","三台子街道","辽河街道","黄河街道","三洞桥街道","华山街道","寿泉街道","塔湾街道","明廉街道","新乐街道","舍利塔街道","卫工街道","工人村街道","轻重工街道","七路街道","启工街道","艳粉路官街道","兴齐保工街道","齐贤兴顺街道","笃工街道","兴华街道","云峰贵和街道","兴工街道","霁虹街道","凌空街道","解放街道","民主街道","临湖街道","中兴街道","湖西街道","八一镇","红菱堡镇","林盛堡镇","沙河铺镇","十里河镇","陈相屯镇","姚千户屯镇","王纲堡乡","永乐乡","大沟乡","白清寨乡","佟沟乡","南塔街道","泉园街道","丰乐街道","马官桥街道","东陵街道","英达街道","前进街道","东湖街道","浑河站东街道","五三街道","浑河站西街道","满堂街道","深井子街道","白塔街道","桃仙街道","祝家街道","李相街道","高坎街道","王滨沟乡","新城子街道","清水台街道","辉山街道","道义街道","虎石台街","道财落街道","望滨街道","兴隆台镇","清水台镇","新城子乡","黄家锡伯族乡","石佛寺朝鲜族锡伯族乡","尹家乡","马刚乡","迎宾路街道","北塔街道","陵西街道","城东湖路街道","于洪街道","南阳湖街道","北陵街道","陵东街道","沙岭街道","平罗街道","马三家街道","大潘街道","造化街道","翟家街道","大兴街道","大青中朝友谊街道","西三环街道","彰驿站镇","高花镇","光辉乡","东城街道","辽滨街道","西城街道","新柳街道","新城街道","大红旗镇","梁山镇","公主屯镇","兴隆镇","前当堡镇","大民屯镇","大柳屯镇","兴隆堡镇","胡台镇","法哈牛镇","柳河沟镇","高台子乡","金五台子乡","红旗乡","卢屯乡","姚堡乡","周坨子乡","于家窝堡乡","新农村乡","东蛇山子乡","陶屯乡","罗家房子乡","三道岗子乡","张屯乡","辽中镇","于家房镇","朱家房镇","冷子堡镇","刘二堡镇","茨榆坨镇","新民屯镇","满都户镇","杨士岗镇","肖寨门镇","长滩镇","四方台镇","城郊乡","六间房乡","养士堡乡","潘家堡乡","老观坨乡","老大房乡","大黑岗子乡","牛心坨乡","长甸街道","解放街道","山南街道","园林街道","胜利街道","站前街道","钢城街道","和平街道","对炉街道","东长甸街道","湖南街道","常青街道","新兴街道","汪峪街道","启明街道","繁荣街道","八家子街道","兴盛街道","共和街道","永乐街道","北陶官街道","南华街道","大陆街道","新陶街道","永发街道","河东街道","长春街道","葛布街道","将军堡街道","新华街道","抚顺城街道","前甸镇","河北乡","会元乡","新抚区","金山街道","北地街道","高峪街道","明山街道","东兴街道","新明街道","牛心台街道","卧龙街道","高台子镇","胜利街道","清华街道","得胜街道","五台子街道","渔市街道","河北街道","西市场街道","古楼街道","西城街道","东城街道","太阳升街道","团山街道","西海街道","新兴街道","和平街道","西山街道","河北街道","站前街道","西阜新街道","五龙街道","平安西部街道","工人村街道","东梁街道","韩家店镇"};
        String[] gender = {"男", "女"};
        int i = 1;
        String str;
        for(;i <= USER_SIZE;i++){
            int index_firstname = (int)(Math.random()*(firstName.length));
            String firstname=firstName[index_firstname];
            int index_secondname = (int)(Math.random()*(nextName.length));
            String secondname = nextName[index_secondname];
            String user_name = firstname+secondname;//用户姓名

            String user_age = String.valueOf((int)(Math.random()*(61 - 18 + 1) + 18));//用户年龄在18到60之间

            int index_tel=(int)(Math.random()*(telFirst.length));
            String first_tel = telFirst[index_tel];
            String second_tel = String.valueOf((int)(Math.random()*(99999999 - 10000000 + 1) + 10000000));
            String user_tel = first_tel + second_tel;//用户联系方式

            int index_road=(int)(Math.random()*(road.length));
            String first_road=road[index_road];
            String second_road=(int)(Math.random()*(1500 - 1 + 1) - 1)+"号";
            String user_address = first_road + second_road;//用户送餐地址

            String user_level = String.valueOf((int)(Math.random()*(10 - 1 + 1) + 1));//用户星级

            int index_gender=(int)(Math.random()*(1 + 1));
            String user_gender = gender[index_gender];//用户性别

            str = i + " " + user_name + " " + user_gender + " " +user_age + " " + user_tel + " " + user_address + " " + user_level;
            pw.format("%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("用户表创建成功，已写入"+ i +"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }



    //外卖员编号、外卖员姓名、外卖员年龄、外卖员电话、外卖员微信号、外卖员工资、外卖员工资级别
    public static void create_Employee(String filePath) throws IOException{
        long startTime=System.currentTimeMillis();

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String[] firstName={"李","王","张","刘","陈","杨","赵","黄","周","吴","徐","孙","胡","朱","高","林","何","郭","马","罗","梁","宋","郑","谢","韩","唐","冯","于","董","萧","程","曹","袁","邓","许","傅","沈","曾","彭","吕","苏","卢","蒋","蔡","贾","丁","魏","薛","叶","阎","余","潘","杜","戴","夏","钟","汪","田","任","姜","范","方","石","姚","谭","廖","邹","熊","金","陆","郝","孔","白","崔","康","毛","邱","秦","江","史","顾","侯","邵","孟","龙","万","段","漕","钱","汤","尹","黎","易","常","武","乔","贺","赖","龚","文"};
        String[] nextName={"彬蔚","彬彬","博裕","博文","博艺","承德","澄泓","澹雅","得韬","德辉","方旭","飞轩","和畅","涵畅","浩初","浩然","泓涵","泓浵","嘉言","江沅","瑾瑜","静淞","俊慧","俊杰","俊爽","俊雄","俊誉","峻雅","歌凯","孔阳","可佳","澜清","乐心","乐仪","乐康","乐成","霖霖","灵犀","曼衍","曼语","琦玮","如山","若飞","若光","若华","胜蓝","腾逸","恬如","恬适","恬雅","望舒","文澜","文心","文轩","文雅","文懿","昕昕","信芳","驰星","星阑","星轩","修远","煦涵","雅厚","雅亮","怡然","熠然","潆泓","羽丰","语心","悠然","煜月","悦心","子衿","自珍","朝辉","亮","星"};
        String[] telFirst={"130","131","132","133","134","135","136","137","138","139","147","150","151","152","153","155","156","157","158","159","187","188"};
        String[] gender = {"男", "女"};
        int i = 0;
        String str;
        for(;i < EMPLOYEE_SIZE; i++){
            int index_firstname = (int)(Math.random()*(firstName.length));
            String firstname=firstName[index_firstname];
            int index_secondname = (int)(Math.random()*(nextName.length));
            String secondname = nextName[index_secondname];
            String employee_name = firstname+secondname;//外卖员姓名

            String employee_age = String.valueOf((int)(Math.random()*(61 - 18 + 1) + 18));//外卖员年龄在18到60之间

            int index_tel=(int)(Math.random()*(telFirst.length));
            String first_tel = telFirst[index_tel];
            String second_tel = String.valueOf((int)(Math.random()*(99999999 - 10000000 + 1) + 10000000));
            String employee_tel = first_tel + second_tel;//外卖员联系方式

            String employee_wechat = String.valueOf((int)(Math.random()*(999999999 - 100000000 + 1) + 100000000));

            String employee_salary = String.valueOf((int)(Math.random()*(15000 - 5000 + 1) + 5000));//外卖员月工资

            int index_gender=(int)(Math.random()*(1 + 1));
            String employee_gender = gender[index_gender];//外卖员性别

            str = i + " " + employee_name + " " + employee_gender + " " +employee_age + " " + employee_tel + " " + employee_wechat + " " + employee_salary;
            pw.format("%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();

        System.out.println("员工表创建成功，已写入"+i+"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }



    //订单编号、客房编号、入住人员、入住日期、入住天数
    public static void create_Order(String filePath)throws IOException{

        long startTime=System.currentTimeMillis();
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        int i = 1;
        String str = "";
        String meal_number;
        String user_id;
        String order_date;
        String order_number;
        int date_year,date_month,date_day;
        for(;i <= ORDER_SIZE;i++){
            meal_number = String.valueOf((int)(Math.random()*(MEAL_TYPE - 1 + 1) + 1));//菜品编号
            user_id = String.valueOf((int)(Math.random()*(USER_SIZE - 1 + 1) + 1));//用户编号
            date_year = (int)(Math.random()*(2018 - 2014 + 1) + 2014);
            date_month = (int)(Math.random()*(12 - 1 + 1) + 1);
            date_day = (int)(Math.random()*(28 - 1 + 1) + 1);
            order_date = String.valueOf(date_year * 10000 + date_month * 100 + date_day);//订餐日期
            order_number = String.valueOf((int)(Math.random()*(3 - 1 + 1) + 1));//订餐份数
            str = i + " " + meal_number + " " + user_id + " " + order_date + " " + order_number + "";

            pw.format("%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件
//            System.out.println(str);
        }

        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("订单表创建成功，已写入"+(i-1)+"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }


    //评价编号、客户姓名、客户编号、评价日期、评价星级
    public static void create_Evaluation(String filePath) throws IOException{

        long startTime=System.currentTimeMillis();
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);



        int i = 1;
        String str;
        int order_id;
        int employee_id;
        int order_grade;
        int employee_grade;
        for(;i<=EVALUATION_SIZE;i++){
            order_id = (int)(Math.random()*(ORDER_SIZE - 1 + 1) + 1);
            employee_id = (int)(Math.random()*(EMPLOYEE_SIZE - 1 + 1) + 1);
            order_grade = (int)(Math.random()*(10 - 1 + 1) + 1);
            employee_grade = (int)(Math.random()*(10 - 1 + 1) + 1);
            str = i + " " + order_id + " " + employee_id + " " + order_grade + " " + employee_grade;
            pw.format("%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();

        long endTime=System.currentTimeMillis();
        System.out.println("评价表创建成功，已写入"+ (i - 1)+"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }


    public static void main(String[] args)throws IOException{
        String inputPath11 = "C:\\university\\dbtuning\\pj\\DBMS-Join\\my_customer.txt";
        create_User(inputPath11);


    }

}

