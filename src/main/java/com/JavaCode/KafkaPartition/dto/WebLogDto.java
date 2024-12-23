package com.JavaCode.KafkaPartition.dto;

public class WebLogDto {

    public WebLogDto() {
    }

    public WebLogDto(String id, String log) {
        this.id = id;
        this.log = log;
    }

    @Override
    public String toString() {
        return "WebLogDto{" +
            "id='" + id + '\'' +
            ", log='" + log + '\'' +
            '}';
    }

    private String id;
    private String log;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
