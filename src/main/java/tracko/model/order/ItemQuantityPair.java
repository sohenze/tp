package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import tracko.model.item.Item;
import tracko.model.item.Price;
import tracko.model.item.Quantity;

/**
 * Represents an item and quantity pair to be added to an {@code Order's} item list.
 * */
public class ItemQuantityPair {

    private final Item item;
    private Quantity quantity;
    private Price itemPrice;

    /**
     *  * Constructs an ItemQuantityPair with the given Item and Quantity.
     * @param item The given item
     * @param quantity The given quantity
     */
    public ItemQuantityPair(Item item, Quantity quantity) {
        requireAllNonNull(item, quantity);
        this.item = item;
        this.quantity = quantity;
        calculatePrice();
    }

    public void updatePrice() {
        calculatePrice();
    }

    /**
     * Returns the referenced {@code Item} object of this ItemQuantityPair.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the item name of the referenced {@code Item} object of this ItemQuantityPair.
     */
    public String getItemName() {
        return item.getItemName().toString();
    }

    /**
     * Get the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Sets the {@code Quantity} of this ItemQuantityPair.
     */
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns true if the given {@code ItemQuantityPair} has the same item.
     */
    public boolean hasSameItem(ItemQuantityPair otherPair) {
        return item.isSameItem(otherPair.item);
    }

    /**
     * Returns the quantity value of the referenced {@code Quantity} object of this ItemQuantityPair.
     */
    public Integer getQuantityValue() {
        return quantity.getQuantity();
    }

    public void calculatePrice() {
        Price singleItemPrice = this.item.getSellPrice();
        double itemPrice = singleItemPrice.getPrice() * quantity.getQuantity();
        this.itemPrice = new Price(itemPrice);
    }

    public Price getPrice() {
        return this.itemPrice;
    }

    @Override
    public String toString() {
        return quantity + " * " + this.getItemName();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItemQuantityPair)) {
            return false;
        }

        ItemQuantityPair otherPair = (ItemQuantityPair) other;
        return getItem().equals(otherPair.getItem())
            && getQuantity().equals(otherPair.getQuantity());
    }
}
