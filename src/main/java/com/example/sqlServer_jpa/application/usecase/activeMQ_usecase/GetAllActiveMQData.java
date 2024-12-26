package com.example.sqlServer_jpa.application.usecase.activeMQ_usecase;

import com.example.sqlServer_jpa.adapters.persistence.secondary.ActiveMQ_Repository;
import com.example.sqlServer_jpa.core.domain.ActiveMQLock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllActiveMQData {
    private final ActiveMQ_Repository activeMQRepository;

    public GetAllActiveMQData(ActiveMQ_Repository activeMQRepository) {
        this.activeMQRepository = activeMQRepository;
    }

    public List<ActiveMQLock> execute() {
        return activeMQRepository.findAll();
    }
}
