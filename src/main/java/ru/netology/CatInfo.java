package ru.netology;

public class CatInfo {
    private String id;
    private String text;
    private String type;
    private String user;
    private Long upvotes;

    public CatInfo() {

    }

    public CatInfo(String id, String text, String type, String user, Long upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public Long getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Fact about Cat \n"+
                "id = " + id + '\n' +
                "text = " + text + '\n' +
                "type = " + type + '\n' +
                "user = " + user + '\n' +
                "upvotes = " + upvotes + '\n' +
                "*--------* \n";
    }
}
