package com.minxing.client.ocu;

import java.util.ArrayList;
import java.util.List;

public class CancelOcuTop {
    private List<Long> msgIds;

    public CancelOcuTop() {
        this.msgIds = new ArrayList<Long>();
    }

    public List<Long> getMsgIds() {
        return msgIds;
    }

    public void setMsgIds(List<Long> msgIds) {
        this.msgIds = msgIds;
    }
}
