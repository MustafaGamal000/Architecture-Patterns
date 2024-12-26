package com.example.sqlServer_jpa.application.Service;

import com.example.sqlServer_jpa.adapters.persistence.ActiveMQ_Repository;
import com.example.sqlServer_jpa.core.domain.ActiveMQLock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveMQService {
    private final ActiveMQ_Repository activeMQRepository;

    public ActiveMQService(ActiveMQ_Repository activeMQRepository) {
        this.activeMQRepository = activeMQRepository;
    }

    public List<ActiveMQLock> getAllActiveMQData() {
        return activeMQRepository.findAll();
    }
}
