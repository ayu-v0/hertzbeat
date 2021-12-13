package com.usthe.alert.service.impl;

import com.usthe.alert.dao.AlertDao;
import com.usthe.alert.pojo.entity.Alert;
import com.usthe.alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * 告警信息服务实现
 *
 *
 */
@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertDao alertDao;

    @Override
    public void addAlert(Alert alert) throws RuntimeException {
        alertDao.save(alert);
    }

    @Override
    public Page<Alert> getAlerts(Specification<Alert> specification, PageRequest pageRequest) {
        return alertDao.findAll(specification, pageRequest);
    }

    @Override
    public void deleteAlerts(HashSet<Long> ids) {
        alertDao.deleteAlertsByIdIn(ids);
    }
}