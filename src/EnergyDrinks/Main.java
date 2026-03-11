package EnergyDrinks;

import static EnergyDrinks.Helpers.*;

public class Main
{
    public static void main(String args[]){
        // create default drinks
        Drink reign = new Drink("Reign Storm", 1, 200, 2.50, 1.20, 50 );
        Drink alani = new Drink("Alani", 2, 200, 3.00, 1.30, 50);
        Drink celsius = new Drink("Celsius", 3, 200, 3.50, 0.75, 50);
        Drink monster = new Drink("Monster", 4, 160, 3.50, 2.00, 50 );
        Drink bloom = new Drink("Bloom", 5, 180, 2.75, 1.50, 50 );
        Drink[] drinks = {reign, alani, celsius, monster, bloom};
        displayInventory(drinks);
        boolean isActive = true;
        while(isActive)
        {
            // prompt for action
            int action = chooseAction();
            isActive = executeAction(action, drinks);
            // check for low stock
            checkStock(drinks);
        }
    }

}
