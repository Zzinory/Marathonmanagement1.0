import java.util.*;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        //建立user map集合 预存几个用户先
        //todo 做好后封装起来
        Map<String,String> user = new HashMap();//用户
        Map<String,Integer> results = new HashMap();//成绩
        Set<String> messages = new HashSet<>(); //通知
        messages.add("叶初航因有点NT退出比赛！！");
        messages.add("希望大家积极报名，踊跃参加^ ^！");
        //用UUID确保不会重复
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));
        user.put("user"+ UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,6));

        user.put("user123","123456"); //可登录运动员

        for (String key:user.keySet()){
            results.put(key,(int)(Math.random()*5400)+7800);  //成绩用秒记录 输出转换成时分秒  不用数据库只能随机生成了
        }


        user.put("manager123","123456");//可登录管理员

        System.out.println("\n\n欢迎进入马拉松赛信息管理系统");
        System.out.println("-------------------------");
        System.out.println("运动员入口：1");
        System.out.println("管理员入口：2");
        Scanner in = new Scanner(System.in);
        int chose1 = in.nextInt(); //运动员或管理员
        int chose2;//运动员登录1或注册2
        int chose3=-1;//报名参赛
        String account ;
        String password ;

        if(chose1==1){
            System.out.println("运动员登录：1");
            System.out.println("运动员注册：2");
            chose2 = in.nextInt();
            in.nextLine();
            if (chose2==1)//运动员登录 1 1
            {
                System.out.print("请输入账号：");
                account = in.nextLine();
//                in.nextLine();
                System.out.print("请输入密码: ");
                password = in.nextLine();
                if (!user.containsKey(account)){
                    System.out.println("用户不存在，请检查账号输入或注册");
                    System.exit(1);
                }
                else
                {
                    if (user.get(account).equals(password)){
                        System.out.println("登录成功！");
                    }
                    else{
                        System.out.println("密码错误！");
                        System.exit(1);
                    }
                }
            }
            else//运动员注册 1 2
            {
                System.out.print("输入注册账号：");
                account=in.nextLine();
                System.out.print("输入密码：");
                password=in.nextLine();
                if (user.containsKey(account)){
                    System.out.println("此账号已存在");
                    System.exit(1);
                }
                else{
                    user.put(account,password);
                    System.out.println("注册成功！开始登录");
                    System.out.print("请输入账号：");
                    account = in.nextLine();
//                in.nextLine();
                    System.out.print("请输入密码: ");
                    password = in.nextLine();
                    if (!user.containsKey(account)){
                        System.out.println("用户不存在，请检查账号输入或注册");
                        System.exit(1);
                    }
                    else
                    {
                        if (user.get(account).equals(password)){
                            System.out.println("登录成功！");
                        }
                        else{
                            System.out.println("密码错误！");
                            System.exit(1);
                        }
                    }
                }

            }
            //登录成功才能到这一步
            while(chose3!=4) {
                System.out.println("\n\n欢迎进入运动员界面");
                System.out.println("--------------------");
                System.out.println("报名参赛：1");
                System.out.println("查询成绩排名：2");//进去后可查询 大赛排行榜
                System.out.println("查看通知:3");
                System.out.println("退出系统:4");
                chose3=in.nextInt();
                if (chose3 == 1) {  //报名 直接写入成绩
                    System.out.println("报名成功！");
                    results.put(account, ((int) Math.random()) * 5400 + 7800);// 报名就添加成绩
                }
                if (chose3 == 2) { //查看成绩排名
                    Map<String, Integer> result1 = results.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder())) //naturalOrder升序 reverseOrder降序
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                    System.out.println(result1);//换成10个 输出 结果转换一下
                }
                if(chose3==3){ //查看通知.
                    System.out.println(messages);
                }
                if (chose3==4){
                    System.exit(1);
                }
            }
        }
    }
}
