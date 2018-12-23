package com.blankchn.test.zuulgateway.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author BlankCHN
 * @date 2018-12-22 00:43
 */
@Document
public class Route {

    @Id
    private String id;

    private String path;

    @Field("serviceId")
    private String serviceId;
    private String url;
    @Field("stripPrefix")
    private Boolean stripPrefix = true;
    private Boolean retryable = false;
    private Boolean enabled = true;
    private String description;

    public Route(String id, String path, String serviceId, String url, Boolean stripPrefix, Boolean retryable, Boolean enabled, String description) {
        this.id = id;
        this.path = path;
        this.serviceId = serviceId;
        this.url = url;
        this.stripPrefix = stripPrefix;
        this.retryable = retryable;
        this.enabled = enabled;
        this.description = description;
    }

    public Route() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(Boolean stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
