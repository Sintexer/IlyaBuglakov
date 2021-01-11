package com.ilyabuglakov.raise.command.impl;

import com.ilyabuglakov.raise.dal.dao.impl.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.exception.TransactionCreationException;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.command.Command;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class IndexPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered POST index command");
        try (Transaction transaction = new DatabaseTransactionFactory().createTransaction()) {
            UserDao dao = (UserDao)transaction.createDao(DaoType.USER);
            Optional<User> usr = dao.read(2L);
            transaction.commit();
            if(usr.isPresent())
                log.info(usr.get());
        } catch (TransactionCreationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().println("010010100101001001");
    }
}
