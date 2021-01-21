package com.ilyabuglakov.raise.model.service.domain.factory.database;

import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.TransactionFactory;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.model.service.domain.Service;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.database.UserDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.factory.ServiceFactory;

import java.util.EnumMap;

public class DatabaseServiceFactory implements ServiceFactory {

    private final EnumMap<ServiceType, DatabaseServiceProducer> serviceCreators;
    private final TransactionFactory transactionFactory = new DatabaseTransactionFactory();

    public DatabaseServiceFactory() {
        serviceCreators = new EnumMap<>(ServiceType.class);
        serviceCreators.put(ServiceType.USER, UserDatabaseService::new);
    }

    public Service createService(ServiceType serviceType){
        return serviceCreators.get(serviceType).create(transactionFactory.createTransaction());
    }

    @Override
    public void close() {
        transactionFactory.close();
    }

    @FunctionalInterface
    private interface DatabaseServiceProducer {
        Service create(Transaction transaction);
    }
}
