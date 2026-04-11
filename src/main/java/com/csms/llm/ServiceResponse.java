package com.csms.llm;

import com.csms.entities.ServiceRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class ServiceResponse {
    private List<ServiceRecord> services;

    public List<ServiceRecord> getServices() { return services; }
}
