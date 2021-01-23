package com.ilyabuglakov.raise.model.tag;

import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

@Log4j2
public class LinkTag extends SimpleTagSupport {

    @Setter
    private String key;

    @Override
    public void doTag() throws JspException, IOException {
//        log.info(key);
        JspWriter out = getJspContext().getOut();
        out.print(PropertiesStorage.getInstance().getLinks().getProperty(key));
    }
}
