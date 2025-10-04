package com.dolokey.dkblog;


import lombok.Data;

/**
 * 附件数据传输对象
 *
 * @author dolokey
 * @date 2025/09/15
 */
@Data
public class AttachmentDTO {

    /**
     * 附件编号
     */
    private String id;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 文件名称
     */
    private String fileName;

}
