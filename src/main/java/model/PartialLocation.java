package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by k.benhmida on 28/08/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartialLocation {

    private String id;
    private String name;
    private String type;
    private GeoPosition geoPosition;

    public static PartialLocation[] fromJson(BufferedReader bufferedReader){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(bufferedReader, PartialLocation[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }
    @JsonProperty("geo_position")
    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }


    @Override
    public String toString() {
        return "PartialLocation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", latitude='" + geoPosition.getLatitude() + '\'' +
                ", longitude='" + geoPosition.getLongitude() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartialLocation that = (PartialLocation) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
