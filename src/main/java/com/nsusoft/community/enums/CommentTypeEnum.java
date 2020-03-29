package com.nsusoft.community.enums;

import com.nsusoft.community.entity.Comment;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private int type;

    CommentTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static boolean isExit(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values())
            if (commentTypeEnum.type == type)
                return true;
        return false;
    }

}
