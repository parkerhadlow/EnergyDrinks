package EnergyDrinks;
import java.util.Scanner;

public class Helpers
{
    public static void displayInventory(Drink[] arr){
        /**
         * Iterates through a list of drinks and displays each drink's current stats.
         * @param arr: array of drinks to iterate through
         */
        System.out.println("Welcome to Prof P's EDs! Here's our full menu:");
        for(Drink drink : arr){
            System.out.println(drink.getName() + "-> Caffeine Content: " + drink.getCaffeine() + " mg Price: $"
                    + drink.getPrice() + " Current Stock: " + drink.getStock());
        }
    }

    public static void checkStock(Drink[] arr){
        /**
         * Iterates through a list of drinks and checks for low stock (5 or less remaining). Prints a warning for any low stock drinks.
         * @param arr: array of drinks to iterate through
         */
        for (Drink drink : arr){
            if (drink.getStock() <= 5){
                System.out.println("Low Stock Warning! " + drink.getName() + " Stock: " + drink.getStock());
            }
        }
    }

    public static int chooseAction(){
        /**
         * Prints a list of action options for the user to choose from, then collects and validates that input.
         * @return int 1-7
         */
        Scanner input = new Scanner(System.in);
        System.out.println("\nAvailable Actions:");
        System.out.println("\tRestock drinks: 1");
        System.out.println("\tSell drinks: 2");
        System.out.println("\tRemove a product: 3");
        System.out.println("\tUpdate product information: 4");
        System.out.println("\tCalculate Totals: 5");
        System.out.println("\tPrint menu: 6");
        System.out.println("\tQuit program: 7");
        boolean validInput = false;
        int number = -1;
        //enter validation loop
        do {
            System.out.println("What would you like to do? Enter a number.");
            //check if an integer was entered
            if(input.hasNextInt()) {
                number = input.nextInt();
                //check if it's in range
                if (number >= 1 && number <= 7)
                {
                    validInput = true;
                } else
                {
                    System.out.println("\n\tInvalid input. Please enter a positive integer 1-5.");
                }
            }
            else{
                System.out.println("\n\tInvalid input. Please enter a positive integer 1-5.");
                input.next(); //clear invalid input
            }
        }
        while(!validInput);
        return number;
    }

    public static Drink chooseDrink(Drink[] arr){
        /**
         * Prompts the user to choose a drink via entering its name or SKU number.
         * @param arr: array of drinks to iterate through.
         * @return the chosen Drink object
         */
        Scanner input = new Scanner(System.in);
        int number = -1;
        Drink choice = null;
        boolean validInput = false;
        do{
            System.out.println("Which drink? (Type the name or search by SKU): ");
            //check for numbers
            if(input.hasNextInt()){
                number = input.nextInt();
                for (int i = 0; i < arr.length; i++){
                    if (number == arr[i].getSKU())
                    {
                        validInput = true;
                        choice = arr[i];
                        break;
                    }
                }
            }
            else{
                String response = input.nextLine().trim();
                for (int i = 0; i < arr.length; i++){
                    if (response.equals(arr[i].getName())){
                        validInput = true;
                        choice = arr[i];
                        break;
                    }
                }
                if (!validInput)
                {
                    System.out.println("ERROR: Invalid input. Please enter a drink name or a SKU number.");
                }
            }

        }
        while(!validInput);
        return choice;
    }

    public static boolean executeAction(int action, Drink[] drinks){
        /**
         * Preforms the action the user selected (restock, sell, remove product, edit info, calculate total, print menu, or quit)
         * @param action: int 1-7 corresponding to the desired action
         * @param drinks: array of drinks to iterate through
         * @return boolean value that is only false if the user chooses to quit the program.
         */
        if (action == 1){ //RESTOCK
            Drink drink = chooseDrink(drinks);
            int quantity = onlyPos("How many would you like to restock? ");
            Drink.restock(drink, quantity);
        }
        else if (action == 2){ //SELL
            Drink drink = chooseDrink(drinks);
            int quantity = onlyPos("How many would you like to sell? ");
            Drink.sell(drink, quantity);
        }
        else if (action == 3){ //REMOVE PRODUCT
            Drink drink = chooseDrink(drinks);
            drink.setStock(0);
        }
        else if (action == 4){ //EDIT INFO
            Drink drink = chooseDrink(drinks);
            System.out.print("Information available to edit:");
            System.out.println("\n\tPrice: 1 \n\t Cost to Make: 2 \n\t Caffeine Content: 3 \n\t SKU: 4");
            int choice = oneThruFour("Enter a number from the options above: ");
            if (choice == 1){ //Set new price
                double price = onlyPosDouble("Select a new price: $");
                drink.setPrice(price);
            }
            else if(choice == 2){ //Set new cost
                double cost = onlyPosDouble("Select a new cost: $");
                drink.setCostToMake(cost);
            }
            else if(choice == 3){ //Set new caffeine content
                int caff = onlyPos("Select new caffeine content (mg): ");
                drink.setCaffeine(caff);
            }
            else if (choice == 4){
                int newSKU = onlyPos("Select a new SKU number: ");
                drink.setSKU(newSKU);
            }
        }
        else if (action == 5){ //CALCULATE AND DISPLAY TOTALS
            calculateTotals(drinks);
        }
        else if (action == 6){ //RE-PRINT MENU
            displayInventory(drinks);
        }
        else if (action == 7){
            return false;
        }
        return true;
    }

    public static void calculateTotals(Drink[] arr){
        /**
         * Calculates and prints profit statistics for the store.
         * @param arr: array of drinks to iterate through
         */
        int stockTotal = 0;
        double inventoryValue = 0;
        double expectedProfit = 0;
        int totalCaffeineAmt = 0;
        for (Drink drink: arr){
            stockTotal += drink.getStock();
            inventoryValue += (drink.getStock() * drink.getPrice());
            expectedProfit += (drink.getStock() * drink.getPrice()) - (drink.getStock() * drink.getCostToMake());
            totalCaffeineAmt += (drink.getStock() * drink.getCaffeine());
        }
        System.out.println("Total amount of drinks in stock: " + stockTotal);
        System.out.println("Total inventory value: $" + inventoryValue);
        System.out.println("Total expected profit: $" + expectedProfit);
        System.out.println("Total amount of caffeine in stock: " + totalCaffeineAmt);
    }

    public static int onlyPos(String prompt){
        /**
         * Prompts the user to enter a positive integer, then re-prompts until input is valid.
         * @param prompt: the message displayed to the user when asking for input
         * @return a positive int entered by the user
         */
        int number = -1;
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        //enter validation loop
        do {
            System.out.printf("\n%s", prompt);
            //check if an integer was entered
            if(input.hasNextInt()) {
                number = input.nextInt();
                //check if it's positive
                if (number > 0)
                {
                    validInput = true;
                } else
                {
                    System.out.println("\n\tInvalid input. Please enter a positive integer.");
                }
            }
            else{
                System.out.println("\n\tInvalid input. Please enter a positive integer.");
                input.next(); //clear invalid input
            }
        }
        while(!validInput);
        return number;
    }

    public static double onlyPosDouble(String prompt){
        /**
         * Prompts the user to enter a positive integer, then re-prompts until input is valid.
         * @param prompt: the message displayed to the user when asking for input
         * @return a positive int entered by the user
         */
        double number = -1;
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        //enter validation loop
        do {
            System.out.printf("\n%s", prompt);
            //check if an integer was entered
            if(input.hasNextDouble()) {
                number = input.nextDouble();
                //check if it's positive
                if (number > 0)
                {
                    validInput = true;
                } else
                {
                    System.out.println("\n\tInvalid input. Please enter a positive double.");
                }
            }
            else{
                System.out.println("\n\tInvalid input. Please enter a positive double.");
                input.next(); //clear invalid input
            }
        }
        while(!validInput);
        return number;
    }

    public static int oneThruFour(String prompt){
        /**
         * Prompts the user to enter a positive integer 1-4, then re-prompts until input is valid.
         * @param prompt: the message displayed to the user when asking for input
         * @return a positive int entered by the user
         */
        int number = -1;
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        //enter validation loop
        do {
            System.out.printf("\n%s", prompt);
            //check if an integer was entered
            if(input.hasNextInt()) {
                number = input.nextInt();
                //check if it's positive
                if (number >= 1 && number <= 4)
                {
                    validInput = true;
                } else
                {
                    System.out.println("\n\tInvalid input. Please enter a positive integer 1-4.");
                }
            }
            else{
                System.out.println("\n\tInvalid input. Please enter a positive integer 1-4.");
                input.next(); //clear invalid input
            }
        }
        while(!validInput);
        return number;
    }
}

