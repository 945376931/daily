package com.example.daily.demo.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.daily.demo.Enity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 回复日报控制器
 **/

@EnableScheduling  //用于定义在配置类上，用于开启定时任务的支持 ，是方法上的注解@Scheduled的前提
@Controller
public class DailyController {

    @Autowired
    private RestTemplate restTemplete;
    //企业id
    private static final String CORPID = "wmb729cd7944c3585b";
    //应用的凭证密钥
    private static final String SECRECT = "zhl7fTnl02cJTIuUDHyeDgi1d1bX5thAAQLtO2FtraPs4a2OPankDHXmMggAoLbS";

    @ResponseBody
//    @Scheduled(cron ="0 0 */1 * * ?")
    @RequestMapping(value = "/daily", produces = "application/json;charset=utf-8")              //接收的任务
    public JSONArray  dailyReward() throws Exception {
        String accessUrl = "https://api.exmail.qq.com/cgi-bin/gettoken?corpid=" + CORPID + "&corpsecret=" + SECRECT;
        String accessResult = restTemplete.getForObject(accessUrl, String.class);
        JSONObject json = JSONObject.parseObject(accessResult);
        String accessToken = json.getString("access_token");  //拿到了accessToken
        String emailUrl = "https://api.exmail.qq.com/cgi-bin/log/mail?access_token=" + accessToken;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        Email returnEmail;
        Map<String, Object> map = new HashMap<String, Object>();
        ca.setTime(new Date()); //设置为当前时间
        ca.add(Calendar.DATE, -1); //当前时间前一天
        Date lastday = ca.getTime();//获取一天

      map.put("begin_date", df.format(lastday));
//        map.put("begin_date", df.format(new Date()));  //开始时间和截止时间都是今天的，判断每个在今天发送的日报是否没回（方法：去查看我发送的日报里有没有包含Re:10.28工作日报 张志远的）
//       有的话就是回了，没有的话就是没有，然后发送
        map.put("end_date", df.format(new Date()));
        map.put("mailtype", 2);
        map.put("userid", "zhangzhiyuan@bz.cn");
        String result = restTemplete.postForObject(emailUrl, map, String.class);   //到这边已经获取到收到的日报
        JSONObject jsonResult = JSONObject.parseObject(result); //将其转换为json格式的类
        SimpleDateFormat sdf = new SimpleDateFormat("M.d");
        JSONArray emailArray = jsonResult.getJSONArray("list");
        JSONArray judgeEmailArray = new JSONArray();
        for (int i = 0; i < emailArray.size(); i++) {
            JSONObject emailJson = (JSONObject) emailArray.get(i);
            try {
                String s = emailJson.getString("subject").substring(5, 9);
                String s2 = emailJson.getString("subject").substring(4, 8);
                if (s.equals("工作日报") || s2.equals("工作日报")) {
                    judgeEmailArray.add(emailJson);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        System.out.println("今天收到的工作日报" + judgeEmailArray); //也就是今天别人发送的工作日报
////        map.put("begin_date", df.format(new Date()));
        map.put("begin_date", df.format(lastday));
        map.put("end_date", df.format(new Date()));
        map.put("mailtype", 1);  //发送
        map.put("userid", "zhangzhiyuan@bz.cn");
        String sendResult = restTemplete.postForObject(emailUrl,map,String.class);  //查询到发送的邮件
        JSONObject jsonSendResult = JSONObject.parseObject(sendResult);
        JSONArray  jsonSendArray=jsonSendResult.getJSONArray("list");
//        System.out.println("以下为今日已经回复的任务");
//        System.out.println(jsonSendArray);

        /**----------------------------------------------建立连接------------------------------------------------------------------ **/
//        在这去打开连接，需要时去解析，这样第一部可以先减少几次连接
        Properties props = new Properties();
        props.setProperty("mail.pop3.host", "pop.exmail.qq.com"); // 按需要更改
        props.setProperty("mail.pop3.port", "995");
        // SSL安全连接参数
        props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.pop3.socketFactory.fallback", "true");
        props.setProperty("mail.pop3.socketFactory.port", "995");
        // 创建Session实例对象
        Session session = Session.getInstance(props);
        Store store = session.getStore("pop3");
        store.connect("zhangzhiyuan@bz.cn", "BaiZhu7958");
        Folder folder = store.getFolder("INBOX");//获得收件箱
        folder.open(Folder.READ_WRITE);	//打开收件箱
        //解析邮件
        Message[] messages = folder.getMessages();
        /** ----------------------------------------------连接关闭--------------------------------------------------------------------**/

        /**该方法为主要的方法去判断是否发送**/
        for (int i = 0; i < judgeEmailArray.size(); i++) {
            //去判断在发送的邮件中有没有含有该标题的邮件
            boolean isreply = false;
            JSONObject getJsonObject = (JSONObject) judgeEmailArray.get(i);  //得到每一个收的邮件
            for (int j = 0; j < jsonSendArray.size(); j++) {
                JSONObject sendJsonObject = (JSONObject) jsonSendArray.get(j);
                if (sendJsonObject.getString("subject").contains(getJsonObject.getString("subject"))){
                    isreply = true;
                    System.out.println();
                    System.out.println(getJsonObject+"已回复");
                }
            }
            if(!isreply){ //去回复
                System.out.println(getJsonObject+"日报未回复");
                //这一步成功  接下来去查他们的日报内容 然后拼接 去发送\
                String substr= getJsonObject.getString("subject");
                String s1="";
                String s2="";
                try {
                    returnEmail=POP3ReceiveMailTest.getContentBysubject(messages,substr);
                   s1= returnEmail.getContent();
                   int index = s1.indexOf("<");
                   s2= s1.substring(0,index);
                    sendController.send("Re:"+returnEmail.getSubject(),"收到\n"+"\n"+"\n"+"\n"+
                                    "------------------ Original ------------------\n"+
                                    "From:"+ getJsonObject.getString("sender")+"\n"+
                                    "Date:"+ returnEmail.getSendTime()+"\n"+
                                    "To:"+returnEmail.getToUsernames()+"\n"+
                            "Subject:"+returnEmail.getSubject()+"\n"+
                            "\n" + s2,getJsonObject.getString("sender"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        folder.close(true);
        store.close();
        return emailArray;
    }

}
