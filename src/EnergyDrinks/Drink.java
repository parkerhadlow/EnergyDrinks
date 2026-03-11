package EnergyDrinks;

public class Drink
{
    // attributes
    private String name;
    private int SKU;
    private int caffeine;
    private double price;
    private double costToMake;
    private int stock = 0;

    // constructor
    public Drink(String name, int SKU, int caffeine, double price, double costToMake, int stock){
        this.name = name;
        this.SKU = SKU;
        this.caffeine = caffeine;
        this.price = price;
        this.costToMake = costToMake;
        this.stock = stock;
    }

    // methods
    public static void restock(Drink drink, int quantity){
        drink.setStock(drink.getStock() + quantity); // add to stock
        System.out.println("Now there are " + drink.getStock() + " " + drink.getName() + "s in stock.");
    }

    public static void sell(Drink drink, int quantity){
        if (drink.getStock() >= quantity){
            drink.setStock(drink.getStock() - quantity); //take drinks out of stock
        }
        else{
            System.out.println("ERROR! Not enough drinks in stock.");
        }
    }


    //getters
    public String getName(){
        return name;
    }

    public int getSKU(){
        return SKU;
    }

    public int getCaffeine(){
        return caffeine;
    }

    public double getPrice(){
        return price;
    }

    public double getCostToMake(){
        return costToMake;
    }

    public int getStock(){
        return stock;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setSKU(int SKU){
        this.SKU = SKU;
    }

    public void setCaffeine(int caffeine){
        this.caffeine = caffeine;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setCostToMake(double cost){
        this.costToMake = cost;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

}
