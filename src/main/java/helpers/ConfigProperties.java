package helpers;

import org.aeonbits.owner.Config;

import java.util.List;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/main/resources/config.properties"})

public interface ConfigProperties extends Config {
    @Config.Key("base.url")
    String baseUrl();

    @Config.Key("laptop.url")
    String laptopUrl();

    @Config.Key("laptopAfterSearch.url")
    String laptopAfterSearchUrl();

    @Config.Key("first.product")
    String firsProduct();

    @Config.Key("second.product")
    String secondProduct();

    @Config.Key("list.products")
    List<String>products();

    @Config.Key("start.price")
    Integer startPrice();

    @Config.Key("end.price")
    Integer endPrice();

    @Config.Key("time.out")
    Integer timeOut();
}
