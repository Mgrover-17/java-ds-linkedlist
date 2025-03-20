class FriendNode {
    int friendID;
    FriendNode nextFriend;

    public FriendNode(int friendID) {
        this.friendID = friendID;
        this.nextFriend = null;
    }
}

class FriendList {
    FriendNode head;

    public void addFriend(int friendID) {
        if (!isFriend(friendID)) {
            FriendNode newFriend = new FriendNode(friendID);
            newFriend.nextFriend = head;
            head = newFriend;
        }
    }

    public void removeFriend(int friendID) {
        FriendNode temp = head, prev = null;

        while (temp != null && temp.friendID != friendID) {
            prev = temp;
            temp = temp.nextFriend;
        }

        if (temp == null) return; // Friend not found
        if (prev == null) head = temp.nextFriend;
        else prev.nextFriend = temp.nextFriend;
    }

    public boolean isFriend(int friendID) {
        FriendNode temp = head;
        while (temp != null) {
            if (temp.friendID == friendID) return true;
            temp = temp.nextFriend;
        }
        return false;
    }

    public void displayFriends() {
        FriendNode temp = head;
        while (temp != null) {
            System.out.print(temp.friendID + " ");
            temp = temp.nextFriend;
        }
        System.out.println();
    }

    public int countFriends() {
        int count = 0;
        FriendNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.nextFriend;
        }
        return count;
    }
}

class UserNode {
    int userID;
    String name;
    int age;
    FriendList friendList;
    UserNode nextUser;

    public UserNode(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friendList = new FriendList();
        this.nextUser = null;
    }
}

class SocialMediaManager {
    UserNode head;

    public void addUser(int userID, String name, int age) {
        UserNode newUser = new UserNode(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.nextUser != null) {
                temp = temp.nextUser;
            }
            temp.nextUser = newUser;
        }
    }

    private UserNode findUser(int userID) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userID == userID) return temp;
            temp = temp.nextUser;
        }
        return null;
    }

    public void addFriend(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 != null && user2 != null) {
            user1.friendList.addFriend(userID2);
            user2.friendList.addFriend(userID1);
        }
    }

    public void removeFriend(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 != null && user2 != null) {
            user1.friendList.removeFriend(userID2);
            user2.friendList.removeFriend(userID1);
        }
    }

    public void displayFriends(int userID) {
        UserNode user = findUser(userID);
        if (user != null) {
            System.out.print("Friends of " + user.name + ": ");
            user.friendList.displayFriends();
        } else {
            System.out.println("User not found.");
        }
    }

    public void findMutualFriends(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        System.out.print("Mutual Friends: ");
        FriendNode temp = user1.friendList.head;
        while (temp != null) {
            if (user2.friendList.isFriend(temp.friendID)) {
                System.out.print(temp.friendID + " ");
            }
            temp = temp.nextFriend;
        }
        System.out.println();
    }

    public void searchUserByName(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: " + temp.userID + " - " + temp.name);
                return;
            }
            temp = temp.nextUser;
        }
        System.out.println("User not found.");
    }

    public int countFriends(int userID) {
        UserNode user = findUser(userID);
        return (user != null) ? user.friendList.countFriends() : -1;
    }
}

public class SocialMediaConnections {
    public static void main(String[] args) {
        SocialMediaManager sm = new SocialMediaManager();
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 27);
        sm.addUser(3, "Charlie", 30);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);
        sm.addFriend(2, 3);

        sm.displayFriends(1);
        sm.displayFriends(2);
        sm.displayFriends(3);

        sm.findMutualFriends(1, 2);

        sm.removeFriend(1, 2);
        sm.displayFriends(1);

        sm.searchUserByName("Riya");
    }
}
