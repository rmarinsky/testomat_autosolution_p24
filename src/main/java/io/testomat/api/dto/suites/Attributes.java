package io.testomat.api.dto.suites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    private String fileType;
    private Object notes;
    private Object emoji;
    private Integer testCount;
    private String updatedAt;
    private String publicTitle;
    private Object assignedTo;
    @JsonProperty("is-branched")
    private Boolean isBranched;
    private String title;
    private Object issues;
    private Boolean sync;
    private Object labels;
    private List<Object> tags;
    @JsonProperty("is-detail")
    private String isDetail;
    @JsonProperty("filtered-tests")
    private Object filteredTests;
    private Object path;
    private Object file;
    @JsonProperty("jira-issues")
    private Object jiraIssues;
    private String toUrl;
    private String createdAt;
    private Integer position;
    private Object parentId;
    private Boolean isRoot;
}
