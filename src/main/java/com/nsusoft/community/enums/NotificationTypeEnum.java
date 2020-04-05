package com.nsusoft.community.enums;

public enum NotificationTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private int type;
    private String name;

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getTypeName(int type) {
        for (NotificationTypeEnum value : NotificationTypeEnum.values())
            if (value.type == type)
                return value.name;
        return "";
    }
}
