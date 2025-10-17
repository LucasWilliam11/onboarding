package com.mybank.onboarding.adapters.inbound.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class PageResponse {
    private List<?> data;
    private Integer size;
    private Integer page;
    private Boolean last;
}
