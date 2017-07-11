package echo.engineer.oneactivity.immutables;

import org.immutables.value.Value;

import java.util.Optional;
import java.util.Set;

/**
 * AbstractItem
 * Created by Plucky<plucky.pan@ubnt.com> on 7/11/17 2017 10:45.
 */

@Value.Immutable
public abstract class AbstractItem {
    abstract String getName();
    abstract Set<String> getTags();
    abstract Optional<String> getDescription();
}