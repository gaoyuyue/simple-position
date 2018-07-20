package cn.attackme.simpleposition.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Serializable {
    private static final long serialVersionUID = 7330958949718231110L;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 当前时间
     */
    private Date nowTime = new Date();
}
