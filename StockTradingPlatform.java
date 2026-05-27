import java.util.ArrayList;
import java.util.Scanner;

// Stock Class
class Stock {

    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

// Transaction Class
class Transaction {

    String type;
    String stockName;
    int quantity;
    double totalAmount;

    Transaction(String type, String stockName, int quantity, double totalAmount) {

        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    void displayTransaction() {

        System.out.println(
            type + " | Stock: " + stockName +
            " | Quantity: " + quantity +
            " | Amount: $" + totalAmount
        );
    }
}

// Portfolio Class
class Portfolio {

    double balance = 10000;

    ArrayList<Transaction> transactions = new ArrayList<>();

    // Buy Stock
    void buyStock(Stock stock, int quantity) {

        double totalCost = stock.price * quantity;

        if (totalCost <= balance) {

            balance -= totalCost;

            transactions.add(
                new Transaction(
                    "BUY",
                    stock.symbol,
                    quantity,
                    totalCost
                )
            );

            System.out.println("Stock purchased successfully!");

        } else {

            System.out.println("Insufficient balance!");
        }
    }

    // Sell Stock
    void sellStock(Stock stock, int quantity) {

        double totalValue = stock.price * quantity;

        balance += totalValue;

        transactions.add(
            new Transaction(
                "SELL",
                stock.symbol,
                quantity,
                totalValue
            )
        );

        System.out.println("Stock sold successfully!");
    }

    // Show Portfolio
    void showPortfolio() {

        System.out.println("\n===== PORTFOLIO =====");

        System.out.println("Current Balance: $" + balance);

        System.out.println("\nTransactions:");

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");

        } else {

            for (Transaction t : transactions) {

                t.displayTransaction();
            }
        }
    }
}

// Main Class
public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Market Stocks
        ArrayList<Stock> market = new ArrayList<>();

        market.add(new Stock("AAPL", 180));
        market.add(new Stock("GOOGLE", 2500));
        market.add(new Stock("TESLA", 900));
        market.add(new Stock("AMAZON", 3200));

        Portfolio portfolio = new Portfolio();

        int choice;

        do {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");

            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                // View Market Data
                case 1:

                    System.out.println("\n===== MARKET DATA =====");

                    for (Stock s : market) {

                        System.out.println(
                            "Stock: " + s.symbol +
                            " | Price: $" + s.price
                        );
                    }

                    break;

                // Buy Stock
                case 2:

                    System.out.print("Enter stock symbol to buy: ");

                    String buySymbol = sc.next();

                    System.out.print("Enter quantity: ");

                    int buyQty = sc.nextInt();

                    boolean buyFound = false;

                    for (Stock s : market) {

                        if (s.symbol.equalsIgnoreCase(buySymbol)) {

                            portfolio.buyStock(s, buyQty);

                            buyFound = true;
                        }
                    }

                    if (!buyFound) {

                        System.out.println("Stock not found!");
                    }

                    break;

                // Sell Stock
                case 3:

                    System.out.print("Enter stock symbol to sell: ");

                    String sellSymbol = sc.next();

                    System.out.print("Enter quantity: ");

                    int sellQty = sc.nextInt();

                    boolean sellFound = false;

                    for (Stock s : market) {

                        if (s.symbol.equalsIgnoreCase(sellSymbol)) {

                            portfolio.sellStock(s, sellQty);

                            sellFound = true;
                        }
                    }

                    if (!sellFound) {

                        System.out.println("Stock not found!");
                    }

                    break;

                // View Portfolio
                case 4:

                    portfolio.showPortfolio();

                    break;

                // Exit
                case 5:

                    System.out.println("Exiting platform...");
                    break;

                default:

                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
