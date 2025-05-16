package com.dartlinwave.platform.lawconnectplatform.tracking.interfaces.resources;

import java.util.Date;

public record StatusResource(long id, long legalCaseId, Date createdAt, Date updatedAt) {
}
