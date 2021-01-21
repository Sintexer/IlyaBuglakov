package com.ilyabuglakov.raise.model.service.domain.factory;

import com.ilyabuglakov.raise.model.service.domain.Service;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;

public interface ServiceFactory {

    Service createService(ServiceType serviceType);
    void close();

}
