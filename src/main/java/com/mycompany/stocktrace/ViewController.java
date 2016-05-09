/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.stocktrace;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * FXML Controller class
 *
 * @author zsq
 */
public class ViewController implements Initializable {
    
    @FXML
    private TextField searchTermTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView resultsTableView;
    
    public void handleStockSearch(ActionEvent e) throws IOException {
        String stockSymbol = searchTermTextField.getText();
        Stock stock = YahooFinance.get(stockSymbol);
        if (stock.getName() != null) {
            final ObservableList<StockData> items = FXCollections.observableArrayList(
                    new StockData("Symbol", stock.getSymbol()),
                    new StockData("Name", stock.getName()),
                    new StockData("Currency", stock.getCurrency()),
                    new StockData("Exchange", stock.getStockExchange()),
                    new StockData("Quote", stock.getQuote().toString().replaceAll(", ", "\n")),
                    new StockData("Stats", stock.getStats().toString().replaceAll(", ", "\n")),
                    new StockData("Dividend", stock.getDividend().toString().replaceAll(", ", "\n"))
            );
            resultsTableView.setItems(items);
            statusLabel.setText("Stock is found");
        } else {
            resultsTableView.setItems(null);
            statusLabel.setText("Stock not exists!");
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stock stock;
        try {
            searchTermTextField.setText("INTC");
            stock = YahooFinance.get("INTC");

            final ObservableList<StockData> items = FXCollections.observableArrayList(
                    new StockData("Symbol", stock.getSymbol()),
                    new StockData("Name", stock.getName()),
                    new StockData("Currency", stock.getCurrency()),
                    new StockData("Exchange", stock.getStockExchange()),
                    new StockData("Quote", stock.getQuote().toString().replaceAll(", ", "\n")),
                    new StockData("Stats", stock.getStats().toString().replaceAll(", ", "\n")),
                    new StockData("Dividend", stock.getDividend().toString().replaceAll(", ", "\n"))
            );
            resultsTableView.setItems(items);

            statusLabel.setText("Initialising");
        } catch (IOException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
}
