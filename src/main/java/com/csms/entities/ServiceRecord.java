package com.csms.entities;

import dev.langchain4j.model.output.structured.Description;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "service")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRecord {
    @Id
    @Description("The unique service_id for each service")
    private int service_id;
    @Description("The name of the service, use to store the service string")
    private String service_name;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
