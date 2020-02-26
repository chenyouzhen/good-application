package com.siemens.dl.goods.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.siemens.dl.goods.domain.Mapping} entity.
 */
public class MappingDTO implements Serializable {

    private Long id;

    private String name;

    @NotNull
    private String deviceId;

    private String description;


    private Long assemblyLineId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssemblyLineId() {
        return assemblyLineId;
    }

    public void setAssemblyLineId(Long assemblyLineId) {
        this.assemblyLineId = assemblyLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MappingDTO mappingDTO = (MappingDTO) o;
        if (mappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MappingDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", description='" + getDescription() + "'" +
            ", assemblyLineId=" + getAssemblyLineId() +
            "}";
    }
}
