package com.poison.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Resource {
    private String rsId;

    private String rsPid;

    private String component;

    private String redirect;

    private String name;

    private Byte hidden;




    private Meta meta;

    private String path;
    private List<Resource> children =new ArrayList<Resource>();
    public String getRsId() {
        return rsId;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId == null ? null : rsId.trim();
    }

    public String getRsPid() {
        return rsPid;
    }

    public void setRsPid(String rsPid) {
        this.rsPid = rsPid == null ? null : rsPid.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect == null ? null : redirect.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getHidden() {
        return hidden;
    }

    public void setHidden(Byte hidden) {
        this.hidden = hidden;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}