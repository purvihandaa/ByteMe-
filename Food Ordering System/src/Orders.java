import java.time.LocalDateTime;
public class Orders {

    private int orderID;
    private Cart cart;
    private String status;
    private String specialRequest;
    private LocalDateTime orderTime;

    public Orders(int orderID, Cart cart, String status, String specialRequest) {
        this.orderID = orderID;
        this.cart = cart;
        this.status = status;
        this.specialRequest = specialRequest;
        this.orderTime = LocalDateTime.now();
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public int getOrderID() {
        return orderID;
    }
    public Cart getCart() {
        return cart;
    }
    public String getSpecialRequest() {
        return specialRequest;
    }

    public void printRequest() {
        System.out.println("Order ID: " + getOrderID());
        this.cart.viewCart();
        System.out.println("SpecialRequest: " + getSpecialRequest());
        System.out.println("---------------------------------");

    }

    public void printOrders(){
        System.out.println("Order ID: "+ getOrderID());
        this.cart.viewCart();
        System.out.println("Status: "+ getStatus());
        System.out.println("---------------------------------");

    }

}
