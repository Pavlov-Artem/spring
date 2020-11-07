package com.epam.esm.data;

import com.epam.esm.db.data.Tag;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GiftCertificateDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Timestamp createDate;
    private Timestamp lastUpdateTime;
    private int duration;
    private List<Tag> tags = new ArrayList<>();

    public GiftCertificateDto() {
    }

    public GiftCertificateDto(Long id, String name, String description, BigDecimal price, Timestamp createDate, Timestamp lastUpdateTime, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateTime = lastUpdateTime;
        this.duration = duration;
    }

    public GiftCertificateDto(Long id, String name, String description, BigDecimal price, Timestamp createDate, Timestamp lastUpdateTime, int duration, List<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateTime = lastUpdateTime;
        this.duration = duration;
        this.tags = tags;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiftCertificateDto)) return false;
        GiftCertificateDto that = (GiftCertificateDto) o;
        return duration == that.duration &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, createDate, lastUpdateTime, duration, tags);
    }

    @Override
    public String toString() {
        return "GiftCertificateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }
}
