package helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
                "system.env",
                "file:src/main/resources/config.properties"
})

public interface ConfigProperties extends Config {
    @Config.Key("base.url")
    String baseUrl();

    @Config.Key("first.product")
    String firsProduct();

    @Config.Key("second.product")
    String secondProduct();

    @Config.Key("start.price")
    Integer startPrice();

    @Config.Key("end.price")
    Integer endPrice();

    @Config.Key("time.out")
    Integer timeOut();
}
