package com.rongyixuan.cms.domain;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;
@Document(indexName = "article",type = "article")
public class ArticleWithBLOBs extends Article implements Serializable{
    private String content;

    private String summary;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}