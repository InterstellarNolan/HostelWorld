package edu.nju.hostelworld.util;

/**
 * Created by Administrator on 2017/3/11.
 */
public enum MemberState {
    UNACTIVATED("未激活"), NORMAL("正常"), PAUSED("已暂停"), STOP("已停卡");

    public static MemberState strToMemberState(String str) {
        for (MemberState st : MemberState.values()) {
            if (st.toString().toLowerCase().equals(str.toLowerCase())) {
                return st;
            }
        }
        return NORMAL;
    }

    public String toChineseString() {
        return this.name;
    }

    private MemberState(String str) {
        this.name = str;
    }

    private String name;
}
