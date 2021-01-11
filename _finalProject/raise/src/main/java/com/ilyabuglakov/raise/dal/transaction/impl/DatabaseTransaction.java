package com.ilyabuglakov.raise.dal.transaction.impl;

import com.ilyabuglakov.raise.dal.dao.impl.AnswerDao;
import com.ilyabuglakov.raise.dal.dao.impl.BaseDao;
import com.ilyabuglakov.raise.dal.dao.impl.QuestionDao;
import com.ilyabuglakov.raise.dal.dao.impl.TestCommentDao;
import com.ilyabuglakov.raise.dal.dao.impl.TestDao;
import com.ilyabuglakov.raise.dal.dao.impl.UserDao;
import com.ilyabuglakov.raise.dal.dao.impl.UserTestResultDao;
import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.model.DaoType;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumMap;

/**
 * An implementation of Transaction Interface, specified to work with database connection
 */
@Log4j2
public class DatabaseTransaction implements Transaction {

    private final Connection connection;

    private  EnumMap<DaoType, BaseDao> daoMap;

    public DatabaseTransaction(Connection connection) {
        this.connection = connection;
        daoMap = new EnumMap<>(DaoType.class);
        daoMap.put(DaoType.USER, new UserDao(connection));
        daoMap.put(DaoType.TEST_COMMENT, new TestCommentDao(connection));
        daoMap.put(DaoType.TEST, new TestDao(connection));
        daoMap.put(DaoType.ANSWER, new AnswerDao(connection));
        daoMap.put(DaoType.QUESTION, new QuestionDao(connection));
        daoMap.put(DaoType.USER_TEST_RESULT, new UserTestResultDao(connection));
    }

    @Override
    public BaseDao createDao(DaoType daoType) {
        return daoMap.get(daoType);
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException("Can't commit transaction", e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Can't rollback transaction", e);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
