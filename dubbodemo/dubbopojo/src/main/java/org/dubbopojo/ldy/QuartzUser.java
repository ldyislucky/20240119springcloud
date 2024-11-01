package org.dubbopojo.ldy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
/**
 * @Accessors(chain = true) 是 Lombok 提供的一个注解，用于生成链式调用的方法。这个注解通常与其他 Lombok 注解（如 @Data 或 @Getter、@Setter）一起使用。
 * 链式调用：当 chain 设置为 true 时，Lombok 会为每个属性生成返回 this 的 setter 方法，这样你可以通过链式调用来设置多个属性。例如：
 * // 使用示例
 * User user = new User()
 *     .setUsername("John")
 *     .setEmail("john@example.com");
 */
@Accessors(chain = true)
@TableName("quatzuser")
@ApiModel(value="QuartzUser对象", description="")
public class QuartzUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "客户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "创建时间戳")
    private LocalDateTime created_at;

    @ApiModelProperty(value = "修改时间戳")
    private LocalDateTime updated_at;


}
