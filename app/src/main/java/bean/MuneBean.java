package bean;

/**
 * 菜谱的实体类，用来存放数据的
 */
public class MuneBean {
    private String imgUrl;// 图片的url
    private String info;// 菜谱的简介
    private String number;// 菜谱点赞数

    public MuneBean(String imgUrl, String info, String number) {
        this.imgUrl = imgUrl;
        this.info = info;
        this.number = number;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
