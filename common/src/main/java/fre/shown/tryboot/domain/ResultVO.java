package fre.shown.tryboot.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Radon Freedom
 * created at 2019.04.27 下午5:45
 */

public class ResultVO <T> {

    private Boolean success;
    private T data;
    private String msg;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("success", success)
                .append("data", data)
                .append("msg", msg)
                .toString();
    }

    public ResultVO() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setErrorMsg(String msg) {
        this.setSuccess(false);
        this.setMsg(msg);
    }

    public void setSuccecssData(T data) {
        this.setSuccess(true);
        this.setData(data);
    }

    public void setSuccessDataAndMsg(T data, String msg) {
        this.setSuccess(true);
        this.setData(data);
        this.setMsg(msg);
    }
}
