package io.github.tanghuibo.mybatissqlify.bean;

/**
 * 结果
 * @author tanghuibo
 * @date 2019/12/21下午7:39
 */
public class BizResult<T> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 信息
     */
    private String message;

    /**
     * 结果
     */
    private T data;

    public BizResult(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public BizResult() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
