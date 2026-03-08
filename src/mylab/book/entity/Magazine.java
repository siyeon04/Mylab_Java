package mylab.book.entity;

public class Magazine extends Publication {
    private String publishPeriod; // 잡지만의 고유 속성

    public Magazine(String title, String publishDate, int page, int price, String publishPeriod) {
        super(title, publishDate, page, price);
        this.publishPeriod = publishPeriod;
    }

    public String getPublishPeriod() { return publishPeriod; }

    @Override
    public String toString() {
        return super.toString() + " [잡지] 발행주기:" + publishPeriod
                + ", " + getPage() + "쪽, " + getPrice() + "원, 출판일:" + getPublishDate();
    }
}