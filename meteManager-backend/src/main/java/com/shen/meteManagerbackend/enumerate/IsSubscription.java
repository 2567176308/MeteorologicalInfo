package com.shen.meteManagerbackend.enumerate;

public enum IsSubscription {
    /**
     * 未订阅
     */
    SUBSCRIBE(true),
    /**
     * 已订阅
     */
    UNSUBSCRIBE(false);
    private final boolean isSubscription;

    IsSubscription(boolean isSubscription) {
        this.isSubscription = isSubscription;
    }

    /**
     * 获取当前枚举状态
     *
     */
    public boolean status() {
        return isSubscription;
    }


}
