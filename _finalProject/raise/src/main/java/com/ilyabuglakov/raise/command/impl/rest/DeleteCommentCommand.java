package com.ilyabuglakov.raise.command.impl.rest;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestCommentService;
import com.ilyabuglakov.raise.model.service.servlet.RequestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class DeleteCommentCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CommandException, PersistentException {
        ResponseEntity responseEntity = new ResponseEntity();

        Optional<Integer> commentId = RequestService.getInstance().getIntParameter(request, "commentId");
        if (!commentId.isPresent()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        TestCommentService commentService = (TestCommentService) serviceFactory.createService(ServiceType.TEST_COMMENT);
        commentService.deleteComment(commentId.get());
        return null;
    }
}
