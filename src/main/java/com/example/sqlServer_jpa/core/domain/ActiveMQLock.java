package com.example.sqlServer_jpa.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTIVEMQ_LOCK")
public class ActiveMQLock {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TIME")
    private Long time;

    @Column(name = "BROKER_NAME", length = 250)
    private String broker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }
}
