package com.play.util.diff;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

/**
 * Created by "Sunit Chatterjee" created on 31/05/20
 */
public class JaversDiffUtil<T> {

    public Diff generateDiff(T d1, T d2) {
        Javers javers = JaversBuilder.javers().build();
        Diff diff = javers.compare(d1, d2);
        return diff;
    }
}
