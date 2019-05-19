package com.cotek.boot.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author qezhhnjy
 */
@ConfigurationProperties("girl")
@Component
@Entity
@Data
public class Girl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "请传入女生的胸围")
    private String cupSize;

    @Min(value = 18, message = "未成年少女禁止入内")
    @Max(value = 30, message = "大妈，您还是去跳广场舞吧")
    private Integer age;

}
