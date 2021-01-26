package com.ilyabuglakov.raise.command.impl.index;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestCategoryService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
public class IndexGetCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered index command");
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("index"));
        return responseEntity;
    }
}
