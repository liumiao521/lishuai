package com.lishuai.spring.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("tb_student")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "tb_student对象", description = "学生表")
public class TestPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "学号")
    private String sid;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField(strategy = FieldStrategy.IGNORED)
    private String sname;
}
