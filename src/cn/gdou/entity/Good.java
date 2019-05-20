package cn.gdou.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class Good {
    //手机型号
    String mobileVersion;
    //手机名字
    String mobileName;
    //手机制造商
    String mobileMade;
    //手机价格
    Integer mobilePrice;
    //手机信息
    String mobileMess;
    //手机相片
    String mobilePic;
    //手机ID
    String id;
    //手机类别
    Integer mobileClassify;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return Objects.equals(mobileVersion, good.mobileVersion) &&
                Objects.equals(mobileName, good.mobileName) &&
                Objects.equals(mobileMade, good.mobileMade) &&
                Objects.equals(mobilePrice, good.mobilePrice) &&
                Objects.equals(mobileMess, good.mobileMess) &&
                Objects.equals(mobilePic, good.mobilePic) &&
                Objects.equals(id, good.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mobileVersion, mobileName, mobileMade, mobilePrice, mobileMess, mobilePic, id);
    }
}
