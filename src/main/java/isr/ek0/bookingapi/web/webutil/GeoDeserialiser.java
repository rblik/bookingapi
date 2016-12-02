package isr.ek0.bookingapi.web.webutil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.IOException;

public class GeoDeserialiser extends JsonDeserializer<GeoJsonPoint> {
    @Override
    public GeoJsonPoint deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode rootNode = p.getCodec().readTree(p);
        final JsonNode nodeX = rootNode.get("x");
        final JsonNode nodeY = rootNode.get("y");
        Double x = nodeX.asDouble();
        Double y = nodeY.asDouble();
        return new GeoJsonPoint(x, y);
    }
}
