package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.ApplicationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicantResource {
    private String lawyerId;
    private Date applicationDate;
    private ApplicationStatus status;
}