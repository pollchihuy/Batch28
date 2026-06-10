package com.sqa;

/**
 * {"name":"morpheus",
 * "job":"leader",
 * "id":"479",
 * "createdAt":"2023-01-01T00:00:00.000Z"
 * }
 */
/**
 * HashMap -> Anonymous Class
 * HashMap hMap = new HashMap();
 * 
 */
public class User {
    private String name;
    private String job;
    private String id;
    private String createdAt;

    public User() {
    }

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public User(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", job=" + job + ", id=" + id + ", createdAt=" + createdAt + "]";
    }
}
