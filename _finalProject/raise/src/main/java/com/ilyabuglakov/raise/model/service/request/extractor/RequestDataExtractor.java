package com.ilyabuglakov.raise.model.service.request.extractor;

import javax.servlet.http.HttpServletRequest;

public interface RequestDataExtractor<T> {
    T extractFrom(HttpServletRequest request);
}
