package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Radon Freedom
 * created at 2019.04.24 上午10:32
 */

public class SeckillGoodVO {

    private final SeckillGoodDTO seckillGoodDTO;
    private Long remainSeconds;

    public SeckillGoodVO(SeckillGoodDTO seckillGoodDTO) {
        this.seckillGoodDTO = seckillGoodDTO;
    }

    public Long getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Long remainSeconds) {
        this.remainSeconds = remainSeconds;
    }


    public GoodDO getGoodDO() {
        return seckillGoodDTO.getGoodDO();
    }


    public void setGoodDO(GoodDO goodDO) {
        seckillGoodDTO.setGoodDO(goodDO);
    }


    public Long getId() {
        return seckillGoodDTO.getId();
    }


    public void setId(Long id) {
        seckillGoodDTO.setId(id);
    }


    public Double getSeckillPrice() {
        return seckillGoodDTO.getSeckillPrice();
    }


    public void setSeckillPrice(Double seckillPrice) {
        seckillGoodDTO.setSeckillPrice(seckillPrice);
    }


    public Integer getStockCount() {
        return seckillGoodDTO.getStockCount();
    }


    public void setStockCount(Integer stockCount) {
        seckillGoodDTO.setStockCount(stockCount);
    }


    public Timestamp getStartDate() {
        return seckillGoodDTO.getStartDate();
    }


    public void setStartDate(Timestamp startDate) {
        seckillGoodDTO.setStartDate(startDate);
    }


    public Timestamp getEndDate() {
        return seckillGoodDTO.getEndDate();
    }


    public void setEndDate(Timestamp endDate) {
        seckillGoodDTO.setEndDate(endDate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeckillGoodVO that = (SeckillGoodVO) o;

        return new EqualsBuilder()
                .append(seckillGoodDTO, that.seckillGoodDTO)
                .append(getRemainSeconds(), that.getRemainSeconds())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(seckillGoodDTO)
                .append(getRemainSeconds())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("seckillGoodDTO", seckillGoodDTO)
                .append("remainSeconds", remainSeconds)
                .toString();
    }
}
