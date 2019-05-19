package com.cotek.boot.controller;


import com.cotek.boot.dao.GirlRepository;
import com.cotek.boot.prop.Girl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author qezhhnjy
 */
@RestController
@Slf4j
public class GirlController {

    @Resource
    private GirlRepository girlRepository;

    @GetMapping("/girls")
    public List<Girl> girls() {
        return girlRepository.findAll();
    }

    @PostMapping("/girls")
    public ResponseEntity<?> add(@Valid Girl girl, BindingResult result) {
        if (result.hasErrors()) {
            log.error("-{}-", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(girlRepository.save(girl), HttpStatus.OK);
    }

    @GetMapping("/girls/{id}")
    public Optional<Girl> select(@PathVariable("id") Integer id) {
        return girlRepository.findById(id);
    }

    @PutMapping("/girls")
    public Girl update(Girl girl) {
        Girl one = girlRepository.getOne(girl.getId());
        one.setAge(girl.getAge());
        one.setCupSize(girl.getCupSize());
        return girlRepository.saveAndFlush(one);
    }

    @DeleteMapping("/girls/{id}")
    public void delete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping("/girls/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

}
