package com.example.daily.demo.Enity;

public class Email {
    private  String sendTime;
    private  String toUsernames;
    private  String subject;
    private  String content;

    public Email( String sendTime, String toUsernames, String subject, String content) {

        this.sendTime = sendTime;
        this.toUsernames = toUsernames;
        this.subject = subject;
        this.content = content;
    }

    public Email() {
    }

    @Override
    public String toString() {
        return "Email{" +

                ", sendTime='" + sendTime + '\'' +
                ", toUsernames='" + toUsernames + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getToUsernames() {
        return toUsernames;
    }

    public void setToUsernames(String toUsernames) {
        this.toUsernames = toUsernames;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
