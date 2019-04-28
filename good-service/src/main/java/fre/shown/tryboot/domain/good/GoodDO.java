package fre.shown.tryboot.domain.good;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:05
 */

public class GoodDO {

    private Long id;
    private String goodName;
    private String goodTitle;
    private String goodImg;
    private String goodDetail;
    private Double goodPrice;
    private Integer goodStock;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;


    public GoodDO() {
    }

    @Override
    public String toString() {
        return "GoodDO{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", goodTitle='" + goodTitle + '\'' +
                ", goodImg='" + goodImg + '\'' +
                ", goodDetail='" + goodDetail + '\'' +
                ", goodPrice=" + goodPrice +
                ", goodStock=" + goodStock +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GoodDO goodDO = (GoodDO) o;

        return new EqualsBuilder()
                .append(getId(), goodDO.getId())
                .append(getGoodName(), goodDO.getGoodName())
                .append(getGoodTitle(), goodDO.getGoodTitle())
                .append(getGoodImg(), goodDO.getGoodImg())
                .append(getGoodDetail(), goodDO.getGoodDetail())
                .append(getGoodPrice(), goodDO.getGoodPrice())
                .append(getGoodStock(), goodDO.getGoodStock())
                .append(getGmtCreate(), goodDO.getGmtCreate())
                .append(getGmtModified(), goodDO.getGmtModified())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getGoodName())
                .append(getGoodTitle())
                .append(getGoodImg())
                .append(getGoodDetail())
                .append(getGoodPrice())
                .append(getGoodStock())
                .append(getGmtCreate())
                .append(getGmtModified())
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public String getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(String goodDetail) {
        this.goodDetail = goodDetail;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(Integer goodStock) {
        this.goodStock = goodStock;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
