package com.example.sqlServer_jpa.adapters.persistence;

import com.example.sqlServer_jpa.core.domain.ActiveMQLock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveMQ_Repository extends JpaRepository<ActiveMQLock, Long> {
}
