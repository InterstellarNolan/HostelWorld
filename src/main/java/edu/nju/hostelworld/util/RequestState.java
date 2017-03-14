package edu.nju.hostelworld.util;

/**
 * Created by Administrator on 2017/3/11.
 */
public enum RequestState {
    UNCHECK, APPROVED, DENIED;

    public static RequestState strToRequestState(String str) {
        for (RequestState st : RequestState.values()) {
            if (st.toString().toLowerCase().equals(str.toLowerCase())) {
                return st;
            }
        }
        return UNCHECK;
    }
}
