package fre.shown.tryboot.domain.good;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.22 下午7:13
 */

public class SeckillGoodDTO {

    private GoodDO goodDO;

    private Long id;
    private Double seckillPrice;
    private Integer stockCount;
    private Timestamp startDate;
    private Timestamp endDate;


    public SeckillGoodDTO() {
    }

    @Override
    public String toString() {
        return "SeckillGoodDTO{" +
                "goodDO=" + goodDO +
                ", id=" + id +
                ", seckillPrice=" + seckillPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
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

        SeckillGoodDTO that = (SeckillGoodDTO) o;

        return new EqualsBuilder()
                .append(getGoodDO(), that.getGoodDO())
                .append(getId(), that.getId())
                .append(getSeckillPrice(), that.getSeckillPrice())
                .append(getStockCount(), that.getStockCount())
                .append(getStartDate(), that.getStartDate())
                .append(getEndDate(), that.getEndDate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGoodDO())
                .append(getId())
                .append(getSeckillPrice())
                .append(getStockCount())
                .append(getStartDate())
                .append(getEndDate())
                .toHashCode();
    }

    public GoodDO getGoodDO() {
        return goodDO;
    }

    public void setGoodDO(GoodDO goodDO) {
        this.goodDO = goodDO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
