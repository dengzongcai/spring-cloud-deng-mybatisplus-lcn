package com.deng.commons.common;

import lombok.Data;

/**
 * @Auther ZongCai
 * @Date 2021/7/19
 */
@Data
public class ITreeObjectImpl implements ITreeObject {
    private String id;
    private String parentId;
    private String name;
    private String nodeType;

    public ITreeObjectImpl(){}

    public ITreeObjectImpl(String id, String parentId, String name, String nodeType) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.nodeType = nodeType;
    }
}
