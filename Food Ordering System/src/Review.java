import java.util.*;

public class Review {
    Scanner input = new Scanner(System.in);

//  pasta -> tasty
    HashMap<String,ArrayList<String>> review = new HashMap<>();

    public void giveReview() {
        System.out.print("Enter the item you want to review: ");
        String itemName = input.nextLine();
        System.out.print("Enter your review for " + itemName + ": ");
        String detail = input.nextLine();

        if (review.containsKey(itemName)) {
            review.get(itemName).add(detail);
        } else {
            ArrayList<String> reviews = new ArrayList<>();
            reviews.add(detail);
            review.put(itemName, reviews);
        }

        System.out.println("Review added successfully!");
    }


    public void viewReview() {
        System.out.print("Enter the item name you want to view: ");
        String name = input.nextLine();
        for (HashMap.Entry<String, ArrayList<String>> i : review.entrySet()){
            if(i.getKey().equalsIgnoreCase(name)){
                System.out.println(name + "-> "+ i.getValue());
            }

        }
    }
}

