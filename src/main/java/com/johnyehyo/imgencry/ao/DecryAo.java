package com.johnyehyo.imgencry.ao;

import java.io.Serializable;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2022-06-30 22:57:19
 */
public class DecryAo implements Serializable {

    private static final long serialVersionUID = 3866296901031950770L;

    private String imgUrl;

    private Double key;

    private String type;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getKey() {
        return key;
    }

    public void setKey(Double key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DecryAo{" +
                "imgUrl='" + imgUrl + '\'' +
                ", key=" + key +
                ", type='" + type + '\'' +
                '}';
    }
}
