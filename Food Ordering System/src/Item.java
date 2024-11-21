import java.util.Scanner;

public class Item {
    private String category;
    private String name;
    private int price;
    private boolean isavailable;

    public Item(String category, String name, int price, boolean isavailable) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.isavailable = isavailable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }

    public void printItem() {
        System.out.println(category + "|" + name + "|" + price + "|" + isavailable);

    }

}
