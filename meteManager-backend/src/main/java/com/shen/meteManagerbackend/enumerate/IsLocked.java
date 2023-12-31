package com.shen.meteManagerbackend.enumerate;

public enum IsLocked {
    /**
     * 已冻结
     */
    LOCKED(true),
    /**
     * 未被冻结
     */
    UNLOCK(false);

    private final boolean isLocked;

    IsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 获取当前枚举状态
     * @return status true ? isLocked : unLock
     */
    public boolean status() {
        return isLocked;
    }
}
