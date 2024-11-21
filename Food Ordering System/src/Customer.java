import java.util.*;
public class Customer {
    private String name;
    private int rollNo;
    private Cart cart;
    private Orders order;
    private boolean isVip;
    private ArrayList<Orders> customerOrders;

    static Scanner obj = new Scanner(System.in);

    public Customer(int rollNo, String name, boolean isVip) {
        this.rollNo = rollNo;
        this.name = name;
        this.cart = new Cart();
        this.customerOrders = new ArrayList<>();
        this.isVip = isVip;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public ArrayList<Orders> getOrders() {
        return customerOrders;
    }
    public Cart getCart() {
        return cart;
    }
    public int getRollNo() {
        return rollNo;
    }
    public String getName() {
        return name;
    }


    public static void viewAllItems(){
        for (Item i: Main.menu){
            i.printItem();
        }
    }

    public static void search(){
        System.out.println("Enter name/specific keyword: ");
        String name = obj.nextLine();
        int c=0;
        for (Item i: Main.menu) {
            if (i.getName().equalsIgnoreCase(name) || i.getCategory().equalsIgnoreCase(name)) {
                i.printItem();
                c++;
            }
        }
        if (c==0){
            System.out.println("Search for another keyword");
        }

    }

    public static void filterbyCategory(){
        System.out.println("Enter the name of the category to filter (Snacks/Beverages/Meals/Desserts): ");
        String category = obj.nextLine();
        for(Item i: Main.menu){
            if (i.getCategory().equalsIgnoreCase(category)){
                i.printItem();
            }
        }
    }

    public static void  sortByPrice(){
        System.out.println("Menu Sorted on the bases of price: ");
        Main.menu.sort(Comparator.comparingInt(Item -> Item.getPrice()));
        viewAllItems();
    }

    public void viewOrderStatus(){
        for (Orders i: customerOrders){
            i.printOrders();
        }


    }

    public void cancelOrder(int orderId,Admin admin){
        int c=0;
        for (Orders i: customerOrders){
            if((i.getOrderID()==orderId)&&(i.getStatus().equalsIgnoreCase("order received"))){
                admin.updateOrderStatus(orderId,"order cancelled");
                c=1;
                Main.all_orders.remove(i);
                Main.all_cancelled_orders.add(i);
            }
        }
        if (c==0){
            System.out.println("Order can not be cancelled, it has been processed !!!");
        }

    }

    public void viewOrderHistory(){
        for (Orders i: customerOrders){
            i.getCart().viewTotal();
        }

    }

    public void upgrade(){
        System.out.println("To upgrade your status to VIP for quicker deliveries, pay Rs 100:");
        System.out.println("Press 1 to pay and any other key to exit: ");
        int key = obj.nextInt();
        obj.nextLine();
        if (key == 1){
            setVip(true);
            System.out.println("You are a VIP now!!!!");
        }

    }

}
