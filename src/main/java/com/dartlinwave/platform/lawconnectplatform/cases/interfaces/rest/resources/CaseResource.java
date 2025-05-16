package com.dartlinwave.platform.lawconnectplatform.cases.interfaces.rest.resources;

import com.dartlinwave.platform.lawconnectplatform.cases.domain.model.valueobjects.CaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CaseResource {
    private Long id;
    private String clientId;
    private String title;
    private String description;
    private String specialtyRequired;
    private Date createdAt;
    private CaseStatus status;
    private List<ApplicantResource> applicants;
}