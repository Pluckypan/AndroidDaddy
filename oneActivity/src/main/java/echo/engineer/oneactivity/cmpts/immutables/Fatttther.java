package echo.engineer.oneactivity.cmpts.immutables;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * Fatttther
 * Created by Plucky<plucky@echo.engineer> on 7/11/17 2017 10:34.
 */
@Value.Immutable
public interface Fatttther {
    String name();
    List<Integer> counts();
    Optional<String> description();
}
