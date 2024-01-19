package service.impl;

import java.util.List;
import model.FruitTransaction;
import service.StrategyService;
import service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
    private StrategyService operationStrategy;

    public TransactionServiceImpl(StrategyService operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void processTransactions(List<FruitTransaction> transactions) {
        if (transactions.isEmpty()) {
            throw new RuntimeException("Transaction list is empty");
        }
        for (FruitTransaction transaction : transactions) {
            operationStrategy.get(transaction.getOperation()).processOperation(transaction);
        }
    }
}