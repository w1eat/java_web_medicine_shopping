package world.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 疾病实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("goods")
public class Goods {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    /**
     * 药物名字
     */
    private String medicineName;
    /**
     * 药物的价格
     */
    private BigDecimal medicinePrice;
    private Integer medicineNum;//购买数量
    private String uuid;
}
