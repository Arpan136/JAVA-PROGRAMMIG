import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    double balance;
    HashMap<String, Integer> holdings;

    Portfolio(double balance) {
        this.balance = balance;
        this.holdings = new HashMap<>();
    }

    void buy(String symbol, int qty, double price) {
        double cost = qty * price;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
            System.out.println("✅ Bought " + qty + " shares of " + symbol);
        } else {
            System.out.println("⚠ Not enough balance.");
        }
    }

    void sell(String symbol, int qty, double price) {
        if (holdings.getOrDefault(symbol, 0) >= qty) {
            balance += qty * price;
            holdings.put(symbol, holdings.get(symbol) - qty);
            System.out.println("💰 Sold " + qty + " shares of " + symbol);
        } else {
            System.out.println("⚠ Not enough shares.");
        }
    }

    void show() {
        System.out.printf("Balance: ₹%.2f\n", balance);
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Stock> market = new HashMap<>();
        market.put("TATA", new Stock("TATA", 500));
        market.put("RELIANCE", new Stock("RELIANCE", 2800));
        market.put("INFY", new Stock("INFY", 1500));

        Portfolio p = new Portfolio(10000);

        while (true) {
            System.out.println("\n📈 Market:");
            for (Stock s : market.values()) {
                System.out.println(s.symbol + ": ₹" + s.price);
            }

            System.out.println("[1] Buy [2] Sell [3] View Portfolio [4] Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 4) break;

            System.out.print("Enter stock symbol: ");
            String symbol = scanner.nextLine().toUpperCase();
            if (!market.containsKey(symbol)) {
                System.out.println("❌ Invalid stock.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine(); // consume newline

            Stock stock = market.get(symbol);
            if (choice == 1) p.buy(symbol, qty, stock.price);
            else if (choice == 2) p.sell(symbol, qty, stock.price);

            System.out.println("\n📊 Portfolio:");
            p.show();
        }

        System.out.println("👋 Thanks for trading!");
        scanner.close();
    }
}