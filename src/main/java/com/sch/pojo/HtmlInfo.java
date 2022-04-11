package com.sch.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2022/4/9 15:47
 */

@AllArgsConstructor
@NoArgsConstructor
public class HtmlInfo implements Serializable {
    private Integer overseasInput;
    private Integer Asymptomatic;
    private Integer existingDiagnosis;
    private Integer cumulativeDiagnosis;
    private Integer cumulativeDeath;
    private Integer cumulativeCure;

    public Integer getOverseasInput() {
        return overseasInput;
    }

    public HtmlInfo setOverseasInput(Integer overseasInput) {
        this.overseasInput = overseasInput;
        return this;
    }

    public Integer getAsymptomatic() {
        return Asymptomatic;
    }

    public void setAsymptomatic(Integer asymptomatic) {
        Asymptomatic = asymptomatic;
    }

    public Integer getExistingDiagnosis() {
        return existingDiagnosis;
    }

    public void setExistingDiagnosis(Integer existingDiagnosis) {
        this.existingDiagnosis = existingDiagnosis;
    }

    public Integer getCumulativeDiagnosis() {
        return cumulativeDiagnosis;
    }

    public void setCumulativeDiagnosis(Integer cumulativeDiagnosis) {
        this.cumulativeDiagnosis = cumulativeDiagnosis;
    }

    public Integer getCumulativeDeath() {
        return cumulativeDeath;
    }

    public void setCumulativeDeath(Integer cumulativeDeath) {
        this.cumulativeDeath = cumulativeDeath;
    }

    public Integer getCumulativeCure() {
        return cumulativeCure;
    }

    public void setCumulativeCure(Integer cumulativeCure) {
        this.cumulativeCure = cumulativeCure;
    }
}
