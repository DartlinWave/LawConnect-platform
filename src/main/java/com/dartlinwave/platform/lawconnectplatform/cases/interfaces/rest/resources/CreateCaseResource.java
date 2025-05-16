package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCaseResource {
    private String clientId;
    private String title;
    private String description;
    private String specialty;
}