package com.cyberlink.youcammakeup.jniproxy.faceshape;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
/* loaded from: classes2.dex */
public final class EyePredictResult {
    public String eyeSet;
    public String eyeSize;
    public EyeFeature left = new EyeFeature();
    public EyeFeature right = new EyeFeature();
}
