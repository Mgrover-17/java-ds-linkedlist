public class InventoryManagementSystem {

static class Inventory{
        String itemName;
        int itemId;
        int quantity;
        int price;
        Inventory next;

        Inventory(String itemName, int itemId, int quantity, int price){
            this.itemName=itemName;
            this.itemId=itemId;
            this.quantity=quantity;
            this.price=price;
            this.next=null;
        }
    }

    static class InventoryTasks{
        private Inventory head;

        public void addItemAtFirst(String itemName, int itemId, int quantity, int price){
            Inventory newItem=new Inventory(itemName, itemId, quantity, price);
            if(head==null){
                head=newItem;
                return;
            }
            newItem.next=head;
            head=newItem;
            System.out.println("Item added at first");

        }

        public void addItemAtLast(String itemName, int itemId, int quantity, int price){
            Inventory newItem=new Inventory(itemName, itemId, quantity, price);
            if(head==null){
                head=newItem;
                return;
            }
            Inventory currInventory=head;
            while(currInventory.next!=null){
                currInventory=currInventory.next;
            }
            currInventory.next=newItem;
            System.out.println("item added at last");
        }
//
        public void addItemAtSpecificPosition(String itemName, int itemId, int quantity, int price, int position){
            Inventory newItem=new Inventory(itemName, itemId, quantity, price);
            if(position==1){
                newItem.next=head;
                head=newItem;
                System.out.println("item added at position "+position);
                return;
            }
            Inventory currInventory=head;
            for(int i=1;currInventory!=null && i<position-1;i++){
                currInventory=currInventory.next;
            }
            if(currInventory==null){
                System.out.println("position out of range");
                return;
            }
            newItem.next=currInventory.next;
            currInventory.next=newItem;
            System.out.println("item added at position "+position);
        }

        public void deleteItem(int itemId){
            if(head==null){
                System.out.println("list empty");
            }
            if(head.itemId==itemId){
                head=head.next;
                System.out.println("item deleted");
                return;
            }
            Inventory currInventory=head;
            while(currInventory.next!=null) {
                if (currInventory.next.itemId == itemId) {
                    currInventory.next = currInventory.next.next;
                    System.out.println("item deleted");
                    return;
                }
                currInventory=currInventory.next;
            }
        }

        public boolean searchItemById(int itemId){
            Inventory currInventory=head;
            while(currInventory!=null){
                if(currInventory.itemId==itemId){
                    System.out.println("Item found: " + currInventory.itemName + " (ID: " + currInventory.itemId + ", Quantity: " + currInventory.quantity + ", Price: $" + currInventory.price + ")");
                    return true;
                }
                currInventory=currInventory.next;
            }
            System.out.println("Item with ID " + itemId + " not found.");
            return false;
        }

        public boolean searchItemByName(String itemName){
            Inventory currInventory=head;
            while(currInventory!=null){
                if(currInventory.itemName==itemName){
                    System.out.println("Item found: " + currInventory.itemName + " (ID: " + currInventory.itemId + ", Quantity: " + currInventory.quantity + ", Price: $" + currInventory.price + ")");
                    return true;
                }
                currInventory=currInventory.next;
            }
            System.out.println("Item name " + itemName + " not found.");
            return false;
        }

        public void updateItem(int itemId, int newQuantity){
            Inventory currInventory=head;
            while(currInventory!=null){
                if(currInventory.itemId==itemId){
                    currInventory.quantity=newQuantity;
                    System.out.println("Quantity updated for Item ID " + itemId + " successfully.");
                    return;
                }
                currInventory=currInventory.next;
            }
            System.out.println("Item with ID " + itemId + " not found.");
        }

        public void calculateItemValue(){
            double totalValue=0;
            Inventory currInventory=head;
            while(currInventory!=null){
                totalValue+=(currInventory.price*currInventory.quantity);
                currInventory=currInventory.next;
            }
            System.out.println("Total Inventory Value: " + totalValue);
        }

        public void displayItem(){
            Inventory curr=head;
            if (curr == null) {
                System.out.println("No items in inventory.");
                return;
            }
            while(curr!=null){
                System.out.println("item name:"+curr.itemName+" ID:"+curr.itemId+" quantity:"+curr.quantity+" price:Rs"+curr.price);
                curr=curr.next;
            }
        }
    }
    public static void main(String[] args) {
        InventoryTasks list = new InventoryTasks();
        // Adding items
        list.addItemAtFirst("Laptop", 101, 5, 800);
        list.addItemAtLast("Phone", 102, 10, 500);
        list.addItemAtSpecificPosition("Tablet", 103, 7, 300, 3);
        list.deleteItem(101);
        list.searchItemById(102);
        list.searchItemByName("Laptop");
        list.updateItem(103, 15);
        list.calculateItemValue();

        // Displaying list
        list.displayItem();

        // Searching for items
//

        // Updating quantity
//

        // Removing an item
//

        // Calculating total list value
//
    }
}
