package io.testomat.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Attributes {
    private String fileType;
    private Object file;
    private Object testCount;
    private Object updatedAt;
    private Object createdAt;
    private Object position;
    private String title;
    private Boolean sync;
    private Boolean isRoot;
}
