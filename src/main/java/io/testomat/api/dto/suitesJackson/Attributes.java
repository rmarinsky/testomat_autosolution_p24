package io.testomat.api.dto.suitesJackson;

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

    @JsonProperty("code")
    private String code;

    @JsonProperty("file-type")
    private String fileType;

    @JsonProperty("notes")
    private Object notes;

    @JsonProperty("emoji")
    private Object emoji;

    @JsonProperty("test-count")
    private Integer testCount;

    @JsonProperty("updated-at")
    private String updatedAt;

    @JsonProperty("public-title")
    private String publicTitle;

    @JsonProperty("assigned-to")
    private Object assignedTo;

    @JsonProperty("is-branched")
    private Boolean isBranched;

    @JsonProperty("title")
    private String title;

    @JsonProperty("issues")
    private Object issues;

    @JsonProperty("sync")
    private Boolean sync;

    @JsonProperty("labels")
    private List<Object> labels;

    @JsonProperty("tags")
    private List<Object> tags;

    @JsonProperty("is-detail")
    private Boolean isDetail;

    @JsonProperty("description")
    private String description;

    @JsonProperty("filtered-tests")
    private Object filteredTests;

    @JsonProperty("path")
    private Object path;

    @JsonProperty("file")
    private Object file;

    @JsonProperty("jira-issues")
    private Object jiraIssues;

    @JsonProperty("to-url")
    private String toUrl;

    @JsonProperty("created-at")
    private String createdAt;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("parent-id")
    private Object parentId;

    @JsonProperty("is-root")
    private Boolean isRoot;
}
