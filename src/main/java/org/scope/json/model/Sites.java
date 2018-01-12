
package org.scope.json.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Sites model
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Sites"
})
public class Sites {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Sites")
    private List<Site> sites = new ArrayList<Site>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Sites")
    public List<Site> getSites() {
        return sites;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("Sites")
    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sites", sites).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(sites).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Sites) == false) {
            return false;
        }
        Sites rhs = ((Sites) other);
        return new EqualsBuilder().append(sites, rhs.sites).isEquals();
    }

}
