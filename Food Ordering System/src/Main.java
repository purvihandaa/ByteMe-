import java.util.*;

public class Main {
    public static ArrayList<Item> menu = new ArrayList<>();
    public static ArrayList<Customer> all_customers = new ArrayList<>();
    public static ArrayList<Orders> all_orders = new ArrayList<>();
    public static ArrayList<Orders> all_cancelled_orders = new ArrayList<>();



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        menu.add(new Item("Snacks", "pizza", 70, true));
        menu.add(new Item("Snacks", "burger", 50, true));
        menu.add(new Item("Snacks", "french fries", 30, false));
        menu.add(new Item("Beverages", "coke", 20, true));
        menu.add(new Item("Beverages", "lemonade", 15, true));
        menu.add(new Item("Beverages", "coffee", 25, false));
        menu.add(new Item("Meals", "pasta", 100, true));
        menu.add(new Item("Meals", "salad", 60, true));
        menu.add(new Item("Meals", "chicken", 120, false));

        Customer c1=new Customer(1,"purvi",false);
        Customer c2=new Customer(2,"reva",false);
        Customer c3=new Customer(3,"arav",true);
        all_customers.add(c1);
        all_customers.add(c2);
        all_customers.add(c3);

        Admin loggedInAdmin = new Admin();
        Review review = new Review();

        System.out.println("Welcome to Byte Me! ");
        while(true) {
            System.out.println("Login as: \n1) Customer \n2) Admin \n3) Exit");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: // Customer login
                    System.out.print("Enter your roll number: ");
                    int roll = input.nextInt();
                    input.nextLine();

                    Customer loggedInCustomer = null;
                    for (Customer i : all_customers) {
                        if (i.getRollNo()==roll)  {
                            loggedInCustomer = i;
                            break;
                        }
                    }

                    if (loggedInCustomer != null) {
                        boolean isCustomerLoggedIn = true;
                        System.out.println("Logged in as : " + loggedInCustomer.getName());
                        while(isCustomerLoggedIn) {
                            System.out.println("Customer Menu:");
                            System.out.println("1) View all items in the menu");
                            System.out.println("2) Search in the menu");
                            System.out.println("3) Filter by category");
                            System.out.println("4) Sort by price");
                            System.out.println("5) Add items in the cart");
                            System.out.println("6) Modify quantities");
                            System.out.println("7) Remove items from the cart");
                            System.out.println("8) View total");
                            System.out.println("9) Checkout Process");
                            System.out.println("10) View order status");
                            System.out.println("11) Cancel order");
                            System.out.println("12) Order history");
                            System.out.println("13) Provide review");
                            System.out.println("14) View reviews");
                            System.out.println("15) Upgrade membership and become VIP");
                            System.out.println("16) Exit");
                            int option = input.nextInt();
                            input.nextLine();  // Consume newline

                            switch (option) {
                                case 1:
                                    System.out.println("---------------------------------");
                                    Customer.viewAllItems();
                                    System.out.println("---------------------------------");
                                    break;
                                case 2:
                                    System.out.println("---------------------------------");
                                    Customer.search();
                                    System.out.println("---------------------------------");
                                    break;
                                case 3:
                                    System.out.println("---------------------------------");
                                    Customer.filterbyCategory();
                                    System.out.println("---------------------------------");
                                    break;
                                case 4:
                                    System.out.println("---------------------------------");
                                    Customer.sortByPrice();
                                    System.out.println("---------------------------------");
                                    break;
                                case 5:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.getCart().addItems();
                                    System.out.println("---------------------------------");
                                    break;
                                case 6:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.getCart().modifyItems();
                                    System.out.println("---------------------------------");
                                    break;
                                case 7:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.getCart().removeItems();
                                    System.out.println("---------------------------------");
                                    break;
                                case 8:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.getCart().viewTotal();
                                    System.out.println("---------------------------------");
                                    break;
                                case 9:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.getCart().checkout(loggedInCustomer.getRollNo(),loggedInCustomer);
                                    System.out.println("---------------------------------");
                                    break;
                                case 10:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.viewOrderStatus();
                                    System.out.println("---------------------------------");

                                    break;
                                case 11:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.cancelOrder(loggedInCustomer.getRollNo(),loggedInAdmin);
                                    System.out.println("---------------------------------");

                                    break;
                                case 12:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.viewOrderHistory();

                                    break;
                                case 13:
                                    System.out.println("---------------------------------");
                                    review.giveReview();
                                    System.out.println("---------------------------------");

                                    break;
                                case 14:
                                    System.out.println("---------------------------------");
                                    System.out.println("Reviews: ");
                                    review.viewReview();
                                    System.out.println("---------------------------------");
                                    break;
                                case 15:
                                    System.out.println("---------------------------------");
                                    loggedInCustomer.upgrade();
                                    System.out.println("---------------------------------");

                                    break;
                                case 16:
                                    System.out.println("Logging out");
                                    System.out.println("-------------------------------------------");
                                    isCustomerLoggedIn = false;
                                    break;
                                default:
                                    System.out.println("ERROR: Invalid choice. Please try again.");
                            }
                        }
                    }

                    else{
                        System.out.println("Customer with this roll number does not exist");
                    }
                    break;

                case 2:   //Admin login
                    System.out.println("Logged in as Admin ");
                    boolean isAdminLoggedIn = true;
                    while(isAdminLoggedIn) {
                        System.out.println("Admin Functionalities:");
                        System.out.println("1) Add new items in the menu");
                        System.out.println("2) Update existing items in the menu");
                        System.out.println("3) Remove items from the menu");
                        System.out.println("4) View pending orders");
                        System.out.println("5) Update order status");
                        System.out.println("6) Process refunds");
                        System.out.println("7) Handle special requests");
                        System.out.println("8) Daily sales report");
                        System.out.println("9) Exit");

                        int option = input.nextInt();
                        input.nextLine();  // Consume newline

                        switch (option) {
                            case 1:
                                System.out.println("---------------------------------");
                                Admin.addNewItems();
                                System.out.println("---------------------------------");
                                break;
                            case 2:
                                System.out.println("---------------------------------");
                                Admin.updateExistingItems();
                                System.out.println("---------------------------------");
                                break;
                            case 3:
                                System.out.println("---------------------------------");
                                Admin.removeItems();
                                System.out.println("---------------------------------");
                                break;
                            case 4:
                                System.out.println("---------------------------------");
                                loggedInAdmin.viewPendingOrders();
                                break;
                            case 5:
                                System.out.println("---------------------------------");
                                System.out.println("Enter the order ID whose status you wish to update: ");
                                int no = input.nextInt();
                                input.nextLine();
                                System.out.println("Change status to (preparing/out for delivery/order denied): ");
                                String status = input.nextLine();
                                loggedInAdmin.updateOrderStatus(no,status);
                                System.out.println("---------------------------------");
                                break;
                            case 6:
                                System.out.println("---------------------------------");
                                loggedInAdmin.processRefunds();
                                System.out.println("---------------------------------");
                                break;
                            case 7:
                                System.out.println("---------------------------------");
                                loggedInAdmin.handleSpecReq();
                                System.out.println("---------------------------------");
                                break;
                            case 8:
                                System.out.println("---------------------------------");
                                loggedInAdmin.dailySalesReport();
                                System.out.println("---------------------------------");
                                break;
                            case 9:
                                System.out.println("Logging out");
                                System.out.println("-------------------------------------------");
                                isAdminLoggedIn = false;
                                break;
                            default:
                                System.out.println("ERROR: Invalid choice. Please try again.");
                        }
                    }
                    break;

                case 3: //Exit
                    System.out.println("Exiting the BYTE ME! application. Goodbye!");
                    System.out.println("-------------------------------------------");
                    return;

                default:
                    System.out.println("ERROR: Invalid choice. Please try again.");
            }

        }
    }
}














