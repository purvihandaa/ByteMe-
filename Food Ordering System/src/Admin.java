import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Admin {
    static Scanner obj = new Scanner(System.in);

    public static void addNewItems(){
        System.out.println("Enter the name of the category (Snacks/Beverages/Meals/Desserts): ");
        String category = obj.nextLine();
        System.out.println("Enter the name of the item: ");
        String name = obj.nextLine();
        System.out.println("Enter the price of the item: ");
        int price = obj.nextInt();
        obj.nextLine();  // Consume newline
        System.out.println("Is the item available? (true/false): )");
        boolean isavailable = obj.nextBoolean();
        obj.nextLine();  // Consume newline
        Main.menu.add(new Item(category,name,price,isavailable));

    }

    public static void updateExistingItems(){
        System.out.println("Enter the name of the item you want to update: ");
        String name = obj.nextLine();
        System.out.println("Enter the updated price: ");
        int price = obj.nextInt();
        obj.nextLine();
        System.out.println("Is the item available? (true/false): ");
        boolean isavailable = obj.nextBoolean();

        for(Item i : Main.menu){
            if(i.getName().equals(name)){
                i.setPrice(price);
                i.setIsavailable(isavailable);
            }

        }

    }

    public static void updateOrderStatus(int no,String status){

        boolean found=false;
        for(Orders i: Main.all_orders){
            if(i.getOrderID() == no){
                i.setStatus(status);
                found=true;
                System.out.println("Status changed to: "+ status);
            }
        }
        if(found){
            for(Customer j : Main.all_customers){
                for(Orders i : j.getOrders()){
                    if(i.getOrderID()==no){
                        i.setStatus(status);
                    }
                }
            }
        }
        else{
            System.out.println("Order not found");
        }

    }

    public static void removeItems() {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the name of the item you want to remove: ");
        String name = obj.nextLine();
        for (Item i : Main.menu) {
            if(i.getName().equals(name)){
                Main.menu.remove(i);
                break;
            }
        }

        for(Orders i:Main.all_orders) {
            for (HashMap.Entry<Item, Integer> j : i.getCart().getCartItems().entrySet()) {
                if(j.getKey().getName().equals(name)){
                    updateOrderStatus(i.getOrderID(),"denied");
                    Main.all_orders.remove(i);
                    Main.all_cancelled_orders.add(i);
                    System.out.println("Order ID: " + i.getOrderID()+ "has been denied");
                    break;
                }
            }
        }

        System.out.println(name+ " has been removed from the menu.");

       }

    public boolean getCustomerVipbyID(int id){
        boolean vip=false;
        for (Customer i : Main.all_customers) {
            if(i.getRollNo() == id){
                vip=i.isVip();
                break;
            }
        }
        return vip;

    }
    public void viewPendingOrders(){

        if(Main.all_orders.size()==0){
            System.out.println("There are no orders");
        }

        ArrayList<Orders> vipOrders=new ArrayList<>();
        ArrayList<Orders> regularOrders=new ArrayList<>();

        for(Orders i : Main.all_orders){
            if(getCustomerVipbyID(i.getOrderID())){
                vipOrders.add(i);
            }
            else{
                regularOrders.add(i);
            }
        }

        for(Orders i: vipOrders){
            if((i.getStatus().equals("order received"))||(i.getStatus().equals("preparing"))){
                i.printOrders();
                System.out.println("---------------------------------");
            }
        }

        for(Orders i: regularOrders){
            if((i.getStatus().equals("order received"))||(i.getStatus().equals("preparing"))){
                i.printOrders();
                System.out.println("---------------------------------");
            }
        }


    }

    public void processRefunds(){
        for (Orders i : Main.all_cancelled_orders) {
            i.printOrders();
            System.out.println("Initiate refund?(yes/no) :");
            String refund = obj.nextLine();
            if(refund.equalsIgnoreCase("yes")){
                System.out.println("Refund process initiated. ");
            }

        }

    }

    public void handleSpecReq(){
        for (Orders i : Main.all_orders) {
            i.printRequest();
        }
    }

    public void dailySalesReport(){
        LocalDate today = LocalDate.now();
        int totalsales=0;
        ArrayList<String> combinedpopular=new ArrayList<>();
        for (Orders i: Main.all_orders) {
            if (i.getOrderTime().toLocalDate().isEqual(today)) {
                totalsales += i.getCart().viewTotalPrice();
            }
        }
        int totalorders = (int) Main.all_orders.stream().filter(order -> order.getOrderTime().toLocalDate().isEqual(today)).count();
//        int totalorders=Main.all_orders.size();

        for(Orders i: Main.all_orders){
            if (i.getOrderTime().toLocalDate().isEqual(today)) {
                for (String j : i.getCart().mostPopularDish())
                    if (!combinedpopular.contains(j)) {
                        combinedpopular.add(j);
                    }
            }
        }

        System.out.println("Total orders: "+totalorders);
        System.out.println("Total sales in Rs: "+totalsales);
        System.out.println("Most Popular Item/s of the day: ");
        for (String i:combinedpopular){
            System.out.println(i);
        }
        System.out.println();


    }



}

