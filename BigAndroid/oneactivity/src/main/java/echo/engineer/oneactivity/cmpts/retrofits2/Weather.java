package echo.engineer.oneactivity.cmpts.retrofits2;

import org.immutables.value.Value;

/**
 * Weather.java
 * Useage: Weather
 * Created by Plucky<py@meitu.com> on 2017/8/14 - 15:18
 */
@Value.Immutable
public abstract class Weather {
    abstract String city();
    abstract String aqi();
    abstract String ganmao();
    abstract String wendu();
    abstract WeatherInfo yesterday();
}
