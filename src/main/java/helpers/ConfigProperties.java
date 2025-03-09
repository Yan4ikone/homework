package helpers;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/config.properties"})

public interface ConfigProperties extends Config {
    @Config.Key("base.url")
    String baseUrl();

    @Config.Key("time.out")
    Integer timeOut();
}
