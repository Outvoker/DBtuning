package Tools;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Data_creation {

    private static final int STUDENT_NUMBER = 10000;                //学生数量
    private static final int PAPER_NUMBER = 100;//论文的数量
    private static final int COMPETITION_NUMBER = 1000;
    private static final int SCORE_SIZE = 10000000;            //成绩的数量（成绩表）


    //学生信息的生成
    //学生表的属性值都包括：学号（从1开始，用户的唯一标识）、姓名、班级(4个班)、入学年份、籍贯、出生年月、专业学院、性别
    //                     u_id,u_name,u_class,u_attend_year,u_native_place,u_birthday,u_major,u_gender
    public static void creat_student(String filePath) throws IOException{


        long startTime=System.currentTimeMillis();

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String[] firstName={"李","王","张","刘","陈","杨","赵","黄","周","吴","徐","孙","胡","朱","高","林","何","郭","马","罗","梁","宋","郑","谢","韩","唐","冯","于","董","萧","程","曹","袁","邓","许","傅","沈","曾","彭","吕","苏","卢","蒋","蔡","贾","丁","魏","薛","叶","阎","余","潘","杜","戴","夏","钟","汪","田","任","姜","范","方","石","姚","谭","廖","邹","熊","金","陆","郝","孔","白","崔","康","毛","邱","秦","江","史","顾","侯","邵","孟","龙","万","段","漕","钱","汤","尹","黎","易","常","武","乔","贺","赖","龚","文"};
        String[] nextName={"彬蔚","彬彬","博裕","博文","博艺","承德","澄泓","澹雅","得韬","德辉","方旭","飞轩","和畅","涵畅","浩初","浩然","泓涵","泓浵","嘉言","江沅","瑾瑜","静淞","俊慧","俊杰","俊爽","俊雄","俊誉","峻雅","歌凯","孔阳","可佳","澜清","乐心","乐仪","乐康","乐成","霖霖","灵犀","曼衍","曼语","琦玮","如山","若飞","若光","若华","胜蓝","腾逸","恬如","恬适","恬雅","望舒","文澜","文心","文轩","文雅","文懿","昕昕","信芳","驰星","星阑","星轩","修远","煦涵","雅厚","雅亮","怡然","熠然","潆泓","羽丰","语心","悠然","煜月","悦心","子衿","自珍","朝辉","亮","星"};
        String[] province = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","台湾","内蒙古","广西","西藏","宁夏","新疆","香港","澳门"};
        String[] major = {"文科","理科","工科","农科","商科","医学"};
        String[] gender = {"男", "女"};
        int i = 1;
        String str;
        for(;i <= STUDENT_NUMBER;i++){
            //姓名u_name
            int index_firstname = (int)(Math.random()*(firstName.length));
            String firstname=firstName[index_firstname];
            int index_secondname = (int)(Math.random()*(nextName.length));
            String secondname = nextName[index_secondname];
            String u_name = firstname+secondname;//用户姓名


            //班级u_class
            //Math.random()包前不包后，0~1
            String u_class = String.valueOf((int)(Math.random()*4 + 1));

            //入学年份u_attend_year,入学年份从2013-2016
            String u_attend_year = String.valueOf((int)(Math.random()*4 + 2013));

            //籍贯u_native_place,只精确到省份
            int index_native_place = (int)(Math.random()*(province.length));
            String u_native_place = province[index_native_place];

            //出生年月(1994-1998年出生)u_birthday
            String u_birthday = String.valueOf((int)(Math.random()*5 + 1994));

            //专业学院u_major
            int index_major = (int)(Math.random()*(major.length));
            String u_major = major[index_major];

            //性别u_gender
            int index_gender = (int)(Math.random()*(gender.length));
            String u_gender = gender[index_gender];

            str = i + " " + u_name + " " + u_class + " " + u_attend_year + " " + u_native_place + " " + u_birthday + " " + u_major + " " + u_gender;
            pw.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("学生信息表创建成功，已写入"+ (i-1) +"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }

    //论文表的生成cc_paper
    //属性：论文作者id，论文id，论文题目，发表期刊名，期刊类型（SCI,SSCI,EI,CPCI,A&HCT,CSSCI,北大中文核心,其他期刊），发表日期（年，月），作者顺序（一、二、三、其他）
    // u_id,pap_id,pap_title,pap_name,pap_type,pap_date,pap_author_order
    public static void creat_paper(String filePath) throws IOException{


        long startTime=System.currentTimeMillis();

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String[] papName = {"新华文摘","哲学研究","心理学报","社会学研究","政治学研究","法学研究","经济研究","管理世界","新闻与传播研究","中国图书馆学报","教育研究","体育科学","中国语文","外语教学与研究","外国文学评论","文学评论","历史研究","学术月刊","马克思主义研究","自然辩证法研究","自然辩证法通讯","心理科学", "世界宗教研究", "统计研究",
                "社会科学战线", "中国人口科学", "民族研究", "国际问题研究", "中共党史研究", "台湾研究", "中国法学", "中外法学", "政法论坛", "中国经济史研究", "数量经济技术经济研究", "经济学家",
                "世界经济", "世界经济与政治", "会计研究", "中国农村经济", "农业经济问题", "中国工业经济", "经济理论与经济管理", "财贸经济", "金融研究", "国际贸易问题", "科学学研究", "科研管理", "中国广播电视学刊",
                "中国出版", "大学图书馆学报", "情报学报", "档案学研究", "高等教育研究", "比较教育研究", "教育发展研究", "中国高教研究", "高等工程教育研究", "中国运动医学杂志", "北京体育大学学报", "中国体育科技",
                "声学学报", "高能物理与核物理", "红外与毫米波学报", "中国物理快报", "无机材料学报", "材料研究学报", "化学物理学报", "催化学报", "化学学报", "物理化学学报", "分析化学", "高分子学报", "无机化学学报",
                "有机化学", "高等学校化学学报", "中国稀土学报", "中国化学快报", "硅酸盐学报", "天文学报", "岩石学报", "空间科学学报", "地球物理学报", "地震学报", "气象学报", "大气科学", "地球化学", "地质科学",
                "地质学报", "矿物学报", "海洋学报", "海洋与湖沼", "经济地理", "地理学报", "遥感学报", "生理学报", "遗传学报", "动物学报", "植物学报", "生物物理学报", "生物化学与生物物理学报", "微生物学报", "实验生物学报",
                "低温工程", "复合材料学报", "真空科学与技术学报", "摩擦学学报", "计量学报", "传感技术学报", "煤炭学报", "石油学报", "中国有色金属学报", "机械工程学报", "中国机械工程", "仪器仪表学报", "振动工程学报", "兵工学报",
                "工程热物理学报", "内燃机学报", "动力工程", "内燃机工程", "热力发电", "太阳能学报", "中国电机工程学报", "电力系统自动化", "电工技术学报", "电子学报", "光子学报", "光电工程", "通信学报", "电子信息学报", "电信科学",
                "微波学报", "电路与系统学报", "计算机学报", "自动化学报", "软件学报", "计算机研究与发展", "系统工程理论与实践", "控制理论与应用", "模式识别与人工智能", "计算机辅助设计与图形学学报", "中国图象图形学报", "计算机科学与技术学报",
                "化工学报", "中国腐蚀与防护学报", "高等学校化工学报", "燃料化学学报", "纺织学报", "中国食品学报", "建筑结构学报", "建筑学报", "岩土工程学报", "土木工程学报", "岩石力学与工程学报", "城市规划", "水利学报", "水力发电学报", "海洋工程",
                "测绘学报", "汽车工程", "中国公路学报", "中国环境科学"
        };
        String[] papTitle = { "境外短期交流对医学生医学人文素养影响的研究", "美国匹兹堡大学医学院跨学科生物医学研究生培养计划课程改革与启示", "中华医学会杂志社手机端阅读应用软件中华医学期刊上线通知",
                "广东省医学会检验医学分会换届会议成功召开", "传统医学结合现代医学促进产后康复的临床研究", "医学影像技术在医学影像诊断中的应用分析", "医学的真谛与哲学求解", "试论医学影像技术对医学影像诊断的作用",
                "中华医学会《中华预防医学杂志》理事单位名单", "叙事医学平行病历在医学生人文教育中的价值", "医学院校医事法学专业法医学教学模式探讨", "糖尿病足病医学营养治疗指南", "医学专业留学生医学人文课程现状及需求调查",
                "中国医学的未来取决于医学教育的更新和发展——王辰院士谈中国医学教育问题", "在社区康复医学中应用叙事医学的探讨", "基础医学实验教学改革策略研究", "世界全科医学瞭望——日本全科医学教育和全科医疗服务",
                "医学检验与检验医学的区别", "医学院临床医学研究生学业倦怠的现状分析", "我国医学检验的发展趋势研究", "预防医学专业医学统计学实验室规范化管理与应用", "司法鉴定融入法医学课程助力高素质医学留学生培养",
                "基于计算机网络技术的计算机网络信息安全及其防护策略", "新时代背景下高校非计算机专业计算机公共课的教学探索", "基于CDIO理念的计算机专业应用型实践教学体系的研究", "计算机网络安全与计算机病毒防范措施研究", "计算机辅助康复环境改善脑卒中患者异常步态的应用研究",
                "计算机基础教育中计算机应用及思维能力培养研究", "高职学生普及计算机文化的探讨", "计算机虚拟技术在计算机教学中的应用", "浅谈计算机应用的现状与计算机的发展趋势", "提高中职计算机课堂教学效率探讨",
                "浅谈中职计算机教学策略", "高校计算机机房系统部署策略", "浅论计算机病毒防护与思考", "计算机科学技术应用现状", "计算机网络可靠性优化策略", "基于计算机网络技术的计算机网络信息安全及其防护策略探讨",
                "试论计算机应用的现状与计算机的发展趋势", "计算机档案管理安全性研究", "计算机教学中的科研思维培养", "基于计算机网络技术的计算机网络信息安全及其防护策略","中职非计算机专业开设计算机应用基础课程的思考",
                "大学计算机基础课程教学质量提升研究", "浅析计算机操作系统及其发展","计算机等级考试对大学计算机基础教学影响研究", "浅谈疗养院计算机网络安全管理的难点及对策", "高职计算机网络课程实训教学研究",
                "习总书记现代农业思想与我国农业现代化","国际可持续发展农业政策和行动方案", "农业机械在现代农业中的作用与发展", "创建特色农业强镇发展城郊高效农业", "三昇农业", "浅谈发展高端精品农业助推农业转型升级", "农业科研单位办公室工作的实践与思考——以中国农业科学院农业资源与农业区划研究所为例",
                "提高农业气象服务水平助力农业现代化发展", "农业种植技术和现代农业机械化的相关性探讨", "关于农业技术进步对农业经济发展的影响研究", "曲沃农业生产托管见证农业变革", "山地农业区耕地资源空间分布状况及优化利用——以湖南桑植县为例", "推进农业品牌化建设提升农业综合效益",
                "农业企业标准化研究", "中国高质量农业发展思考", "灌区农业用水测算方法研究", "农业区域专业化研究综述", "农业科普期刊读者再定位"
        };
        String[] papType = { "SCI","SSCI","EI","CPCI","A&HCT","CSSCI","北大中文核心","其他期刊"};
        String[] papAuthorOrder = {"一","二","三","其他"};

        int i = 1;
        String str;
        for(;i <= PAPER_NUMBER;i++){
            //论文作者的学号
            int u_id = (int)(Math.random()*1000 + 1 );

            //论文id
            //pap_id = i

            //论文题目pap_title
            int index_title = (int)(Math.random()*(papTitle.length));
            String pap_title = papTitle[index_title];

            //论文期刊名pap_name
            int index_name = (int)(Math.random()*(papName.length));
            String pap_name = papName[index_name];

            //论文期刊类型pap_type
            int index_type = (int)(Math.random()*(papType.length));
            String pap_type = papName[index_type];

            //论文发表时间pap_date
            String year = String.valueOf((int)(Math.random()*6 + 2013));
            String month = String.valueOf((int)(Math.random()*12 + 1));
            String pap_date = year + "-" + month;

            //作者顺序pap_author_order
            int index_author_order = (int)(Math.random()*(papAuthorOrder.length));
            String pap_author_order = papAuthorOrder[index_author_order];


            str = u_id + " " + i + " " + pap_title + " " + pap_name + " " + pap_type + " " + pap_date + " " + pap_author_order;
            pw.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("论文表创建成功，已写入"+ (i-1) +"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }


    //奖项表的生成
    //奖项表的属性值都包括：学生学号、奖项id、奖项名称、奖项等级、奖项类型、获奖时间
    //                     u_id,comp_id,comp_title,comp_class,comp_type,comp_date
    public static void creat_competition(String filePath) throws IOException{


        long startTime=System.currentTimeMillis();

        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String[] competition = { "国家奖学金", "国家励志奖学金", "三好学生", "学习优秀生", "突出才能奖", "先进个人", "优秀工作者", "优秀学生干部", "优秀共青团员", "优秀志愿者", "优秀团干", "自强之星", "学生协会工作优秀个人",
                "精神文明先进个人", "精神文明奖", "道德风尚奖", "风采之星", "英语演讲比赛", "PPT设计大赛", "歌唱比赛", "全国大学生数学竞赛", "全国大学生物理竞赛", "全国大学生化学竞赛", "全国大学生语文竞赛", "诗歌朗诵比赛", "主持人大赛",
                "辩论赛", "足球比赛", "篮球比赛"
        };
        String[] cclass = { "特等奖", "一等奖", "二等奖", "三等奖", "其他"};
        String[] type = {"国际级", "国家级", "省级", "市级", "校级"};

        int i = 1;
        String str;
        for(;i <= COMPETITION_NUMBER;i++){
            //学生学号u_id
            int u_id = (int)(Math.random()*1000 + 1 );

            //奖项id
            //comp_id = i;

            //奖项名称comp_title
            int index_title = (int)(Math.random()*(competition.length));
            String comp_title = competition[index_title];

            //奖项等级comp_class
            int index_class = (int)(Math.random()*(cclass.length));
            String comp_class = cclass[index_class];

            //奖项类型comp_type
            int index_type = (int)(Math.random()*(type.length));
            String comp_type = type[index_type];

            //获奖时间comp_date
            String year = String.valueOf((int)(Math.random()*6 + 2013));
            String month = String.valueOf((int)(Math.random()*12 + 1));
            String comp_date = year + "-" + month;




            str = u_id + " " + i + " " + comp_title + " " + comp_class + " " + comp_type + " " + comp_date;
            pw.format("%-15s%-15s%-15s%-15s%-15s%-15s\r\n", str.split("\\s+"));//输出到文件


        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime=System.currentTimeMillis();
        System.out.println("奖项信息表创建成功，已写入"+ (i-1) +"行数据!,"+"耗时="+(endTime-startTime)+"ms");

    }


    //成绩表
    public static void creat_score(String file_path) throws IOException{
        long startTime = System.currentTimeMillis();
        File file = new File(file_path);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        int u_id; //用户编号
        int sco_id; //成绩编号
        String sco_year; //学年学期：1：大一上；2:大一下；3:大二上；4:大二下；5:大三上；6:大三下；7:大四上；8:大四下
        int index_year;
        String sco_name; //课程
        int index_sco_name;
        String sco_property; //属性：1:必修；2:专选；3:公选
        int index_sco_property;
        int sco_period; //学时
        int sco_credit; //学分
        int sco_point; //成绩
        String str;

        String[] year = {"大一上","大一下","大二上","大二下","大三上","大三下","大四上","大四下"};
        String[] course = {"C程序设计","大学生心理健康教育","电子信息类学科导论","高等数学A1","军训","思想道德修养与法律基础","体育基础","线性代数A","信息技术基础","信息技术基础实验","形式与政策","英语2","电路与电子学","犯罪案例分析","高等数学A2","离散数学B","面向对象程序设计A","面向对象程序课程设计","名著选读","欧洲旅游与文化","乒乓球","普通物理A1","普通物理实验B","学科专题讲座","中国近现代史纲要","概率论与数理统计A","概论1","会计学","军事理论","口语与写作","马克思主义基本原理概论","普通物理A2","生命科学实验与探索","数据结构与算法","数据结构与算法课程设计","数字电子技术A","数字电子技术课程设计","羽毛球","JAVA程序设计","创业基础","概论2","各种小家电的组装和维修","汇编语言A","汇编语言课程设计","计算机网络","计算机组成原理","计算机组成原理课程设计","可视化变成技术B","社会实践","数据库原理与应用A","英语国家文化概括","质量管理","编译原理","操作系统A","操作系统课程设计","犯罪心理学","服务外包概论","人机交互技术","软件工程A","数据库系统与应用设计","网络工程综合实践","文献检索与阅读","职业发展与就业指导","电子服务信任与信誉","计算机专业英语","软件测试和质量管理","数据库系统实训","项目管理与案例分析"};
        String[] property = {"必修","专选","公选"};
        for(sco_id = 1; sco_id <= SCORE_SIZE; sco_id++) {
            u_id = 1 + (int)(Math.random() * (STUDENT_NUMBER - 1 + 1));
            index_year = (int) (Math.random() * (year.length));
            sco_year = year[index_year];
            index_sco_name = (int) (Math.random() * (course.length));
            sco_name = course[index_sco_name];
            index_sco_property = (int) (Math.random() * (property.length));
            sco_property = property[index_sco_property];
            sco_period = 18 + (int) (Math.random() * (36 - 18 + 1));
            sco_credit = 1 + (int) (Math.random() * (5 - 1 + 1));
            sco_point = 1 + (int)(Math.random() * (100 - 1 + 1));
            str = sco_id + " " + u_id + " " + sco_year + " " + sco_name  + " " + sco_property + " "+ sco_period + " " + sco_credit + " " + sco_point + " ";


            pw.format("%-20s\t%-20s\t%-20s\t%-20s\t %-20s\t%-20s\t%-20s\t%-20s\t\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime = System.currentTimeMillis();
        System.out.println("成绩表创建成功，已写入" + (sco_id - 1) + "行数据!," + "耗时=" + (endTime - startTime) + "ms");
//        System.out.println(nextName.length);
    }

    //毕业信息生成
    //毕业去向表包括：学生学号、ID、去向类型、单位名称、单位所在省份、单位所在地、记录时间
    public static void creat_graDes(String file_path) throws IOException{
        long startTime = System.currentTimeMillis();
        File file = new File(file_path);
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        int u_id; //用户编号
        int des_id; //去向编号
        String des_type; //去向类型：1：升学；2：出国；3：国企；4：企业；5：公务员
        int index_des_type;
        String des_name; //单位名称
        int index_des_name;
        String des_province; //单位所在省份
        int index_des_province;
        String des_location; //单位所在地
        int index_des_location;
        String record_date;//记录时间
        int date_year,date_month,date_day;
        String str;

        String[] desType = {"升学","出国","国企","企业","公务员"};
        //gai
        String[] desName = {"中国石化","中国石油","中国建筑","中国平安","上海汽车","阿里巴巴","宝山钢铁","厦门国贸","青岛海尔","小米集团","百度","贵州茅台","网易公司","美团点评","青岛啤酒","清华大学","北京大学","复旦大学","上海大学","浙江大学","南京大学","武汉大学","厦门大学","安徽大学","湖南大学","山东大学","东北大学","吉林大学"};
        String[] desProvince = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","台湾","内蒙古","广西","西藏","宁夏","新疆","香港","澳门"};
        String[] desLocation = {"南京路","淮海路","新北站街道","朱剪炉街道","皇城街道","大西街道","山东庙街道","风雨坛街道","滨河街道","万莲街道","大南街道","五里河街道","西塔街道","南湖街道","马路湾街道","新华街道","集贤街道","八经街道","长白街道","北市场街道","太原街街道","南市场街道","浑河湾街道","沈水湾街道","小北街道","大北街道","万泉街道","洮昌街道","文官街道","二台子街道","津桥街道","东站街道","辽沈街道","珠林街道","长安街道","新东街道","东塔街道","三台子街道","辽河街道","黄河街道","三洞桥街道","华山街道","寿泉街道","塔湾街道","明廉街道","新乐街道","舍利塔街道","卫工街道","工人村街道","轻重工街道","七路街道","启工街道","艳粉路官街道","兴齐保工街道","齐贤兴顺街道","笃工街道","兴华街道","云峰贵和街道","兴工街道","霁虹街道","凌空街道","解放街道","民主街道","临湖街道","中兴街道","湖西街道","八一镇","红菱堡镇","林盛堡镇","沙河铺镇","十里河镇","陈相屯镇","姚千户屯镇","王纲堡乡","永乐乡","大沟乡","白清寨乡","佟沟乡","南塔街道","泉园街道","丰乐街道","马官桥街道","东陵街道","英达街道","前进街道","东湖街道","浑河站东街道","五三街道","浑河站西街道","满堂街道","深井子街道","白塔街道","桃仙街道","祝家街道","李相街道","高坎街道","王滨沟乡","新城子街道","清水台街道","辉山街道","道义街道","虎石台街","道财落街道","望滨街道","兴隆台镇","清水台镇","新城子乡","黄家锡伯族乡","石佛寺朝鲜族锡伯族乡","尹家乡","马刚乡","迎宾路街道","北塔街道","陵西街道","城东湖路街道","于洪街道","南阳湖街道","北陵街道","陵东街道","沙岭街道","平罗街道","马三家街道","大潘街道","造化街道","翟家街道","大兴街道","大青中朝友谊街道","西三环街道","彰驿站镇","高花镇","光辉乡","东城街道","辽滨街道","西城街道","新柳街道","新城街道","大红旗镇","梁山镇","公主屯镇","兴隆镇","前当堡镇","大民屯镇","大柳屯镇","兴隆堡镇","胡台镇","法哈牛镇","柳河沟镇","高台子乡","金五台子乡","红旗乡","卢屯乡","姚堡乡","周坨子乡","于家窝堡乡","新农村乡","东蛇山子乡","陶屯乡","罗家房子乡","三道岗子乡","张屯乡","辽中镇","于家房镇","朱家房镇","冷子堡镇","刘二堡镇","茨榆坨镇","新民屯镇","满都户镇","杨士岗镇","肖寨门镇","长滩镇","四方台镇","城郊乡","六间房乡","养士堡乡","潘家堡乡","老观坨乡","老大房乡","大黑岗子乡","牛心坨乡","长甸街道","解放街道","山南街道","园林街道","胜利街道","站前街道","钢城街道","和平街道","对炉街道","东长甸街道","湖南街道","常青街道","新兴街道","汪峪街道","启明街道","繁荣街道","八家子街道","兴盛街道","共和街道","永乐街道","北陶官街道","南华街道","大陆街道","新陶街道","永发街道","河东街道","长春街道","葛布街道","将军堡街道","新华街道","抚顺城街道","前甸镇","河北乡","会元乡","新抚区","金山街道","北地街道","高峪街道","明山街道","东兴街道","新明街道","牛心台街道","卧龙街道","高台子镇","胜利街道","清华街道","得胜街道","五台子街道","渔市街道","河北街道","西市场街道","古楼街道","西城街道","东城街道","太阳升街道","团山街道","西海街道","新兴街道","和平街道","西山街道","河北街道","站前街道","西阜新街道","五龙街道","平安西部街道","工人村街道","东梁街道","韩家店镇"};
        for(des_id = 1; des_id <= STUDENT_NUMBER; des_id++) {
            u_id = des_id; //学生学号

            index_des_type = (int) (Math.random() * (desType.length));
            des_type = desType[index_des_type];//去向类型

            index_des_name = (int) (Math.random() * (desName.length));
            des_name = desName[index_des_name];//单位名称

            index_des_province = (int) (Math.random() * (desProvince.length));
            des_province = desProvince[index_des_province];//单位所在省份

            index_des_location = (int) (Math.random() * (desProvince.length));
            des_location = desLocation[index_des_location];//单位所在地

            date_year = (int)(Math.random()*(2018 - 2014 + 1) + 2014);
            date_month = (int)(Math.random()*(12 - 1 + 1) + 1);
            date_day = (int)(Math.random()*(28 - 1 + 1) + 1);
            record_date = String.valueOf(date_year * 10000 + date_month * 100 + date_day);//记录时间

            str = des_id + " " + u_id + " " + des_type + " " + des_name  + " " + des_province + " "+ des_location + " " + record_date + " ";


            pw.format("%-20s\t%-20s\t%-20s\t%-20s\t %-20s\t%-20s\t%-20s\t\r\n", str.split("\\s+"));//输出到文件

        }
        fw.flush();
        pw.close();
        fw.close();
        long endTime = System.currentTimeMillis();
        System.out.println("毕业去向表创建成功，已写入" + (des_id - 1) + "行数据!," + "耗时=" + (endTime - startTime) + "ms");
//        System.out.println(nextName.length);
    }




    public static void main(String[] args)throws IOException{
        String inputPathStudent = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\student.txt";
        String inputPathScore = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\score.txt";
        String inputPathCompetition = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\competition.txt";
        String inputPathPaper = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\paper.txt";
        String inputPathGraDes = "C:\\Users\\admin\\Desktop\\Dave\\dbtuning\\DBtuning\\graDes.txt";

        creat_student(inputPathStudent);
        creat_score(inputPathScore);
        creat_competition(inputPathCompetition);
        creat_paper(inputPathPaper);
        creat_graDes(inputPathGraDes);

    }

}
