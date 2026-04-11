package com.csms.llm;

import com.csms.entities.ServiceE;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class ServiceResponse {
    private List<ServiceE> services;

    // Ensure you have a no-args constructor and public getters/setters
    public ServiceResponse() {}
    public List<ServiceE> getServices() { return services; }
    public void setServices(List<ServiceE> services) { this.services = services; }
}
