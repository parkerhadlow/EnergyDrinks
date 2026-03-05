package EnergyDrinks;

public class Drink
{
    // attributes
    private String name;
    private int SKU;
    private int caffeine;
    private double price;
    private double costToMake;

    // constructor
    public Drink(String name, int SKU, int caffeine, double price, double costToMake){
        this.name = name;
        this.SKU = SKU;
        this.caffeine = caffeine;
        this.price = price;
        this.costToMake = costToMake;
    }

    // methods


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

}
