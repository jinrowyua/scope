
package org.scope.json.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Availability model
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "IsAvailable",
    "AppointmentDate"
})
public class Availability {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("IsAvailable")
    private Boolean isAvailable;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("AppointmentDate")
    private Object appointmentDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("IsAvailable")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("IsAvailable")
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("AppointmentDate")
    public Object getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("AppointmentDate")
    public void setAppointmentDate(Object appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("isAvailable", isAvailable).append("appointmentDate", appointmentDate).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isAvailable).append(additionalProperties).append(appointmentDate).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Availability) == false) {
            return false;
        }
        Availability rhs = ((Availability) other);
        return new EqualsBuilder().append(isAvailable, rhs.isAvailable).append(additionalProperties, rhs.additionalProperties).append(appointmentDate, rhs.appointmentDate).isEquals();
    }

}
