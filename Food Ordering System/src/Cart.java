import java.util.*;
public class Cart   {
    Scanner input = new Scanner(System.in);

    HashMap<Item,Integer> cart = new HashMap<>();

    public HashMap<Item, Integer> getCartItems() {
        return cart;
    }

    public void addItems(){
        System.out.println("Adding items to the cart. Press 0 to stop.");
        while(true){
            System.out.println("Enter the item name: ");
            String itemName = input.nextLine();

            if (itemName.equals("0")){
                System.out.println("All items has been added to the cart");
                break;
                }

            System.out.println("Enter it's quantity: ");
            int quantity = input.nextInt();
            input.nextLine();
            int c=0;

            for(Item i: Main.menu){
                if((i.getName().equalsIgnoreCase(itemName)) && (i.isIsavailable()==true)) {
                    cart.put(i,quantity);
                    System.out.println(i.getName()+" added to the cart");
                    c++;
                    break;
                }
            }
            if (c==0){
                System.out.println("Item is not available");

            }
        }

        }

    public void modifyItems(){
        System.out.println("Modifying items in the cart. Press 0 to stop.");
        while(true){
            System.out.println("Enter the item name: ");
            String itemName = input.nextLine();

            if(itemName.equals("0")){
                System.out.println("Selected items has been modified in the cart");
                break;
            }
            System.out.println("Enter it's new quantity: ");
            int quantity = input.nextInt();
            input.nextLine();

            for (Item i : cart.keySet()) {
                if((i.getName().equalsIgnoreCase(itemName)) && (i.isIsavailable()==true)) {
                    cart.put(i,quantity);
                    System.out.println(i.getName()+" has been modified");
                }
                else{
                    System.out.println("Item is not available or not in the cart");
                }
            }
        }

    }

    public void removeItems(){
        ArrayList<Item> toRemove = new ArrayList<>();
        System.out.println("Removing items from the cart. Press 0 to stop.");
        while(true){
            System.out.println("Enter the item name: ");
            String itemName = input.nextLine();

            if(itemName.equals("0")){
                System.out.println("Selected items has been removed from the cart");
                break;
            }
            int check=0;
            for (Item i : cart.keySet()) {
                if(i.getName().equalsIgnoreCase(itemName)) {
                    toRemove.add(i);
                    check=1;
                    break;
                }
            }
            if(check==0){
                System.out.println("Item is not available");
            }

            for(Item i: toRemove) {
                cart.remove(i);
            }
        }
    }

    public int viewTotal(){

        int totalprice=0;
        for (HashMap.Entry<Item, Integer> i : cart.entrySet()){
            int price = i.getKey().getPrice() * i.getValue();
            System.out.println(i.getKey().getName() + "  " + i.getValue() + "  " + i.getKey().getPrice()+"Rs" + " -> " + price+"Rs");
            totalprice += price;

        }
        System.out.println("---------------------------------");
        System.out.println("Total of all items: " + totalprice + "Rs" );
        System.out.println("---------------------------------");

        return totalprice;

    }

    public int viewTotalPrice(){

        int totalprice=0;
        for (HashMap.Entry<Item, Integer> i : cart.entrySet()){
            int price = i.getKey().getPrice() * i.getValue();
            totalprice += price;

        }
        return totalprice;
    }

    public ArrayList<String> mostPopularDish(){
        int maxValue = Collections.max(cart.values());

        ArrayList<Item> maxKeys = new ArrayList<>();
        for (Map.Entry<Item, Integer> i : cart.entrySet()) {
            if (i.getValue() == maxValue) {
                maxKeys.add(i.getKey());
            }
        }

        ArrayList<String> popular=new ArrayList<>();
        for(Item i: maxKeys){
            popular.add(i.getName());
        }

        return popular;
    }

    public void viewCart(){
        for (HashMap.Entry<Item, Integer> i : cart.entrySet()){
            System.out.println(i.getKey().getName() + "  (" + i.getValue()+ ") ") ;
        }
    }

    public void checkout(int rollno,Customer customer){
        System.out.println("Checkout Process! This is your final order. ");
        viewTotal();
        System.out.println("You can enter Special request if any:  ");
        String request = input.nextLine();
        System.out.println("Enter address: ");
        String address = input.nextLine();

        System.out.println("Enter UPI ID for payment details : ");
        String payment = input.nextLine();
        System.out.println("press 1 to confirm the order and make payment");
        int confirm = input.nextInt();
        input.nextLine();
        if(confirm == 1){
            Orders order1=new Orders(rollno,this,"order received",request);
            Main.all_orders.add(order1);
            customer.getOrders().add(order1);
            System.out.println("Order confirmed!!!!");
        }

    }
}
