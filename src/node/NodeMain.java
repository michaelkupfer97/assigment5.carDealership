package node;
//Assignment: 5
//Author: Michael Kupfer , ID: 209493246
import java.util.Scanner;
/**
 * The main class to run and interact with the doubly linked list.
 * Allows adding, removing, clearing, and printing the list.
 */
public class NodeMain {
    enum Menu {
        ADD_FIRST(1), ADD_LAST(2), CLEAR(3), CONTAINS(4), IS_EMPTY(5),
        PRINT_BACKWARD(6), PRINT_FORWARD(7), REMOVE(8), EXIT(9);

        private final int option;

        Menu(int option) {
            this.option = option;
        }

        public int getOption() {
            return option;
        }

        public static Menu fromOption(int option) {
            for (Menu menu : Menu.values()) {
                if (menu.getOption() == option) {
                    return menu;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            displayMenu();
            int option = scanner.nextInt();
            Menu selectedOption = Menu.fromOption(option);

            if (selectedOption == null) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            switch (selectedOption) {
                case ADD_FIRST:
                    System.out.println("Enter a number: ");
                    int addNumFirst = scanner.nextInt();
                    list.addFirst(addNumFirst);
                    break;
                case ADD_LAST:
                    System.out.println("Enter a number: ");
                    int addNumLast = scanner.nextInt();
                    list.addLast(addNumLast);
                    break;
                case CLEAR:
                    list.clear();
                    break;
                case CONTAINS:
                    System.out.println("Enter a number: ");
                    int num = scanner.nextInt();
                    boolean isContains = list.contains(num);

                    if (isContains)
                        System.out.println(num + " is present in the list.");
                    else
                        System.out.println(num + " was not found.");
                    break;
                case IS_EMPTY:
                    boolean listEmpty = list.isEmpty();

                    if (listEmpty)
                        System.out.println("The list is empty.");
                    else
                        System.out.println("The list is not empty.");
                    break;
                case PRINT_BACKWARD:
                    list.printBackward();
                    break;
                case PRINT_FORWARD:
                    list.printForward();
                    break;
                case REMOVE:
                    System.out.println("Enter a number: ");
                    int removedNum = scanner.nextInt();
                    Integer removedData = list.remove(removedNum);

                    if (removedData != null)
                        System.out.println(removedNum + " was removed successfully.");
                    else
                        System.out.println("Number was not found.");
                    break;
                case EXIT:
                    exitProgram = true;
                    break;
            }
        }
    }
    /**
     * Displays the menu options.
     */
    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add first");
        System.out.println("2. Add last");
        System.out.println("3. Clear");
        System.out.println("4. Contains");
        System.out.println("5. IsEmpty");
        System.out.println("6. Print backward");
        System.out.println("7. Print forward");
        System.out.println("8. Remove");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }
}
