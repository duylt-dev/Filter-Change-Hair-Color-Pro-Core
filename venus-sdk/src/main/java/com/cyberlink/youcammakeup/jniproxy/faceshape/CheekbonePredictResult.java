package com.cyberlink.youcammakeup.jniproxy.faceshape;

import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
/* loaded from: classes2.dex */
public final class CheekbonePredictResult {
    public ClassificationResult left = new ClassificationResult();
    public ClassificationResult right = new ClassificationResult();
    public ClassificationResult overall = new ClassificationResult();
}
