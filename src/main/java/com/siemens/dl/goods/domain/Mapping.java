package com.siemens.dl.goods.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Mapping.
 */
@Entity
@Table(name = "mapping")
public class Mapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "mapping")
    private Set<Observation> observations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("mappings")
    private AssemblyLine assemblyLine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Mapping name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Mapping deviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public Mapping description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Observation> getObservations() {
        return observations;
    }

    public Mapping observations(Set<Observation> observations) {
        this.observations = observations;
        return this;
    }

    public Mapping addObservation(Observation observation) {
        this.observations.add(observation);
        observation.setMapping(this);
        return this;
    }

    public Mapping removeObservation(Observation observation) {
        this.observations.remove(observation);
        observation.setMapping(null);
        return this;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }

    public AssemblyLine getAssemblyLine() {
        return assemblyLine;
    }

    public Mapping assemblyLine(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
        return this;
    }

    public void setAssemblyLine(AssemblyLine assemblyLine) {
        this.assemblyLine = assemblyLine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mapping)) {
            return false;
        }
        return id != null && id.equals(((Mapping) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mapping{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
