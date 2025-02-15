package com.deepjyot.designpatterns.observer_design;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(String stockSymbol, double price);
}

// Subject Interface
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Concrete Subject
class Stock implements Subject {
    private List<Observer> observers;
    private String stockSymbol;
    private double price;

    public Stock(String stockSymbol, double initialPrice) {
        this.stockSymbol = stockSymbol;
        this.price = initialPrice;
        observers = new ArrayList<>();
    }

    // Register an observer
    @Override
    public void registerObserver(Observer o) {
        if (o != null && !observers.contains(o)) {
            observers.add(o);
        }
    }

    // Remove an observer
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // Notify all observers of a change
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
    }

    // Method to set a new price
    public void setPrice(double newPrice) {
        if (this.price != newPrice) {
            this.price = newPrice;
            System.out.println("\n[Stock] " + stockSymbol + " price updated to $" + price);
            notifyObservers();
        }
    }

    // Getters
    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getPrice() {
        return price;
    }
}

// Concrete Observer - Current Price Display
class CurrentPriceDisplay implements Observer {
    private String stockSymbol;
    private double currentPrice;

    @Override
    public void update(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.currentPrice = price;
        display();
    }

    public void display() {
        System.out.println("[CurrentPriceDisplay] " + stockSymbol + " Current Price: $" + currentPrice);
    }
}

// Concrete Observer - Price Change Percentage Display
class PriceChangePercentageDisplay implements Observer {
    private String stockSymbol;
    private double oldPrice;
    private double newPrice;
    private double percentageChange;

    @Override
    public void update(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.oldPrice = this.newPrice;
        this.newPrice = price;
        if (oldPrice != 0) {
            this.percentageChange = ((newPrice - oldPrice) / oldPrice) * 100;
            display();
        } else {
            // Initial update, no percentage change to display
            System.out.println("[PriceChangePercentageDisplay] " + stockSymbol + " Initial Price Set to $" + newPrice);
        }
    }

    public void display() {
        System.out.printf("[PriceChangePercentageDisplay] %s Price Change: %.2f%%\n", stockSymbol, percentageChange);
    }
}

// Concrete Observer - Historical Chart Display
class HistoricalChartDisplay implements Observer {
    private String stockSymbol;
    private List<Double> priceHistory;

    public HistoricalChartDisplay() {
        priceHistory = new ArrayList<>();
    }

    @Override
    public void update(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        priceHistory.add(price);
        display();
    }

    public void display() {
        System.out.print("[HistoricalChartDisplay] " + stockSymbol + " Price History: ");
        for (double p : priceHistory) {
            System.out.print("$" + p + " ");
        }
        System.out.println();
    }
}

// Main Class to Demonstrate the Observer Pattern
public class StockMarketObserverDemo {
    public static void main(String[] args) {
        // Create a stock subject
        Stock appleStock = new Stock("AAPL", 150.00);

        // Create observers
        CurrentPriceDisplay currentPriceDisplay = new CurrentPriceDisplay();
        PriceChangePercentageDisplay percentageDisplay = new PriceChangePercentageDisplay();
        HistoricalChartDisplay historicalChartDisplay = new HistoricalChartDisplay();

        // Register observers with the stock
        appleStock.registerObserver(currentPriceDisplay);
        appleStock.registerObserver(percentageDisplay);
        appleStock.registerObserver(historicalChartDisplay);

        // Simulate stock price updates
        appleStock.setPrice(151.00);
        appleStock.setPrice(149.50);
        appleStock.setPrice(152.30);

        // Remove one observer
        appleStock.removeObserver(percentageDisplay);

        // Further stock price updates
        appleStock.setPrice(153.00);
        appleStock.setPrice(154.50);
    }
}
