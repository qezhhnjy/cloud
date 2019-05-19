package com.cotek.boot.dao;

import com.cotek.boot.prop.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author qezhhnjy
 */
public interface GirlRepository extends JpaRepository<Girl, Integer> {


    List<Girl> findByAge(Integer age);
}
