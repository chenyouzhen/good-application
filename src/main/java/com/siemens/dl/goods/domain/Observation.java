package com.siemens.dl.goods.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Observation.
 */
@Entity
@Table(name = "observation")
public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "phenomenon_time")
    private ZonedDateTime phenomenonTime;

    @NotNull
    @Column(name = "result", nullable = false)
    private String result;

    @NotNull
    @Column(name = "result_time", nullable = false)
    private ZonedDateTime resultTime;

    @NotNull
    @Column(name = "property", nullable = false)
    private String property;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @Column(name = "interval_time")
    private Long intervalTime;

    @Column(name = "reserve")
    private String reserve;

    @ManyToOne
    @JsonIgnoreProperties("observations")
    private AssemblyLine assemblyLine;

    @ManyToOne
    @JsonIgnoreProperties("observations")
    private Mapping mapping;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getPhenomenonTime() {
        return phenomenonTime;
    }

    public Observation phenomenonTime(ZonedDateTime phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
        return this;
    }

    public void setPhenomenonTime(ZonedDateTime phenomenonTime) {
        this.phenomenonTime = phenomenonTime;
    }

    public String getResult() {
        return result;
    }

    public Observation result(String result) {
        this.result = result;
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ZonedDateTime getResultTime() {
        return resultTime;
    }

    public Observation resultTime(ZonedDateTime resultTime) {
        this.resultTime = resultTime;
        return this;
    }

    public void setResultTime(ZonedDateTime resultTime) {
        this.resultTime = resultTime;
    }

    public String getProperty() {
        return property;
    }

    public Observation property(String property) {
        this.property = property;
        return this;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public Observation unitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
        return this;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Long getIntervalTime() {
        return intervalTime;
    }

    public Observation intervalTime(Long intervalTime) {
        this.intervalTime = intervalTime;
        return this;
    }

    public void setIntervalTime(Long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getReserve() {
        return reserve;
    }

    public Observation reserve(String reserve) {
        this.reserve = reserve;
        return this;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }

    public Observation assemblyLine(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
        return this;
    }

    public void setAssemblyLine(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public Observation mapping(Mapping mapping) {
        this.mapping = mapping;
        return this;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Observation)) {
            return false;
        }
        return id != null && id.equals(((Observation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Observation{" +
            "id=" + getId() +
            ", phenomenonTime='" + getPhenomenonTime() + "'" +
            ", result='" + getResult() + "'" +
            ", resultTime='" + getResultTime() + "'" +
            ", property='" + getProperty() + "'" +
            ", unitOfMeasurement='" + getUnitOfMeasurement() + "'" +
            ", intervalTime=" + getIntervalTime() +
            ", reserve='" + getReserve() + "'" +
            "}";
    }
}
