package com.cotek.boot.service;

import com.cotek.boot.dao.GirlRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author qezhhnjy
 */
@Service
public class GirlService {

    @Resource
    private GirlRepository girlRepository;

    @Transactional(rollbackOn = Exception.class)
    public void delete(int id, int id2) {
        girlRepository.deleteById(id);
        girlRepository.deleteById(id2);
    }
}
