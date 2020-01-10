package cn.eastvalley.data;

import java.util.Date;
import java.util.Objects;

/**
 * @author java_shj
 * @desc    spittle消息类
 * @createTime 2019/9/30 15:19
 **/
public class Spittle {
    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;
    public Spittle(String message, Date time) {
        this(message,time,null,null);
    }
    private Spittle(String message, Date time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time ;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spittle spittle = (Spittle) o;
        return Objects.equals(id, spittle.id) &&
                Objects.equals(message, spittle.message) &&
                Objects.equals(time, spittle.time) &&
                Objects.equals(latitude, spittle.latitude) &&
                Objects.equals(longitude, spittle.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, time, latitude, longitude);
    }
}
