package com.example.daily.demo.Controller;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

@Controller
public class sendController {
    //用户名密码验证，需要实现抽象类Authenticator的抽象方法PasswordAuthentication
    static class MyAuthenricator extends Authenticator {
        String u = null;
        String p = null;
        public MyAuthenricator(String u,String p){
            this.u=u;
            this.p=p;
        }
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(u,p);
        }
    }

    public static  void send(String title,String context,String toUserName){
        Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", "smtps");
        //服务器
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        //端口
        prop.setProperty("mail.smtp.port", "25");//25
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        /**-------用来测试 收件箱**/

        //发件人，进行权限认证
        Session session = Session.getDefaultInstance(prop, new MyAuthenricator("zhangzhiyuan@bz.cn", "BaiZhu7958"));
//        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        System.out.println("---------------------------这个是新测试的");
        System.out.println(mimeMessage);

//        try {
//            Store store=session.getStore("smtps");
//
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        }
        try {
            //发件人地址
            mimeMessage.setFrom(new InternetAddress("zhangzhiyuan@bz.cn",""));
            //收件人的地址
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toUserName));
            mimeMessage.setSubject(title);
            mimeMessage.setSentDate(new Date());
            mimeMessage.setText(context);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        sendController.send("Re:10.29工作日报 高永艳"," 收到\n" +
//                "------------------ Original ------------------\n" +
//                "From:  \"高永艳\"<gaoyongyan@bz.cn>;\n" +
//                "Date:  Tue, Oct 29, 2019 09:43 PM\n" +
//                "To:  \"庞颖\"<pangying@bz.cn>; \"张志远\"<zhangzhiyuan@bz.cn>; \"张驰\"<zhangchi@bz.cn>; \"陈超\"<chenchao@bz.cn>; \"王远程\"<wangyuancheng@bz.cn>; \"逯云非\"<luyunfei@bz.cn>; \"邵舒钦\"<shaoshuqin@bz.cn>; \"陶尚兴\"<taoshangxing@bz.cn>; \"王诗鑫\"<wangshixin@bz.cn>; \"汪洋\"<wangyang@bz.cn>; \"杨旭斌\"<yangxubin@bz.cn>; \"陈颢宽\"<chenhaokuan@bz.cn>; \"郑重\"<zhengzhong@bz.cn>; \"蒋葳\"<jiangwei@bz.cn>; \"孔德君\"<kongdejun@bz.cn>; \"惠喆圆\"<huizheyuan@bz.cn>; \"施翼\"<shiyi@bz.cn>; \"鲁恩骏男\"<luenjunnan@bz.cn>; \"涂强\"<tuqiang@bz.cn>;\n" +
//                "Cc:  \"总办\"<zongban@bz.cn>; \"程磊\"<chenglei@bz.cn>;\n" +
//                "Subject:  10.29工作日报 高永艳\n" +
//                " \n" +
//                "今日工作\n" +
//                "1、邮件收发最近日期发错的同学比较多，给人资带来销分工作上的负担，所以智能化系统重新考虑日报发送时间抓取规则\n" +
//                "2、公共项目组与经理实操，工作每一阶段都应给出相对应落地文档作为一个流程阶段的结果反馈，比如:梳理需求方工作手册后应给出包含:模块、必要性、目的、关联模块字段的Excel表格，作为反馈。除此之外还需根据今日实操完善工作手册。\n" +
//                "3、参与人资价值观测评流程过会，对于智能化方面还是应先整理出前项，例如考勤表等，对于一个人来说如果可以在请假时把身上所有事项都通过系统待办事项转发给另外一个人，那么请假审批流程可以省去。\n" +
//                "5、面试一名产品，参与boss面，管理规划能力有，但是自我定位不清晰，未录用。以后面试时也应从这几方面进行考虑，1.能否放低姿态潜心学习 2.能否直接为公司带来价值利益。\n" +
//                "6、辅助项目申报工作今日已全部完成，明日陶尚兴随研究室一起去合肥，这几天辛苦啦~\n" +
//                "\n" +
//                "明日工作\n" +
//                "1、任务系统发起人接收人为同一人时，进行中编写方案扣分问题解决\n" +
//                "2、Kpi打分小程序接智能化任务系统验收\n" +
//                "3、任务系统说明书绘制\n" +
//                "4、任务系统明天再测一下没什么问题就上线，研发部内部先试用(内测版哈哈哈)\n" +
//                "顺延工作\n" +
//                "定岗定编线上化");
    }
}
