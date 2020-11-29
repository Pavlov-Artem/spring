package com.epam.esm.db.data;

import com.epam.esm.db.DAOConstants;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = DAOConstants.CERTIFICATE_TABLE)
public class GiftCertificate extends RepresentationModel<GiftCertificate> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DAOConstants.GC_ID)
    private Long id;
    @Column(name = DAOConstants.GC_NAME, nullable = false)
    private String name;
    @Column(name = DAOConstants.GC_DESCRIPTION)
    private String description;
    @Column(name = DAOConstants.GC_PRICE)
    private BigDecimal price;
    @Column(name = DAOConstants.GC_CREATE_DATE)
    private Timestamp createDate;
    @Column(name = DAOConstants.GC_LAST_UPDATE_DATE)
    private Timestamp lastUpdateTime;
    @Column(name = DAOConstants.GC_DURATION)
    private int duration;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = DAOConstants.CERTIFICATE_TAG_TABLE,
            //Certificate foreign key
            joinColumns = @JoinColumn(name = DAOConstants.CT_CERTIFICATE_ID),
            //Tag foreign key
            inverseJoinColumns = @JoinColumn(name = DAOConstants.CT_TAG_ID))
    private List<Tag> certificateTags;


    public GiftCertificate() {
    }

    public GiftCertificate(Long id, String name, String description, BigDecimal price, Timestamp createDate, Timestamp lastUpdateTime, int duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateTime = lastUpdateTime;
        this.duration = duration;
    }

    public GiftCertificate(String name, String description, BigDecimal price, Timestamp createDate, Timestamp lastUpdateTime, int duration, List<Tag> certificateTags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateTime = lastUpdateTime;
        this.duration = duration;
        this.certificateTags = certificateTags;
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

    public List<Tag> getCertificateTags() {
        return certificateTags;
    }

    public void setCertificateTags(List<Tag> certificateTags) {
        this.certificateTags = certificateTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiftCertificate)) return false;
        GiftCertificate that = (GiftCertificate) o;
        return duration == that.duration &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, createDate, lastUpdateTime, duration);
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", lastUpdateTime=" + lastUpdateTime +
                ", duration=" + duration +
                '}';
    }
}