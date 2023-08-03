
import java.util.List;

public class JSONOrder {
    private Client owner;
    private List<Item<Product>> items;
    private  Money totalCost;

    public JSONOrder(Client owner, Money totalCost, List<Item<Product>> items) {
        this.owner = owner;
        this.items = items;
        this.totalCost = totalCost;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public List<Item<Product>> getItems() {
        return items;
    }

    public void setItems(List<Item<Product>> items) {
        this.items = items;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Money totalCost) {
        this.totalCost = totalCost;
    }
    
}