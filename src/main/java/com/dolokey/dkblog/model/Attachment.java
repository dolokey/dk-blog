package com.dolokey.dkblog.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dolokey.dkblog.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 附件对象<br>
 *
 * @author dolokey
 * @date 2025/09/15
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dk_attachment")
public class Attachment extends BaseEntity {

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件扩展名
     */
    private String fileExt;

}
