public class MenuItemObject {
    private String item;
    private int price;
    private int cost;

    public MenuItemObject(String item,int price,int cost) {
        this.item = item;
        this.price = price;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getCost() {
        return cost;
    }
}
