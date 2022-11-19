package com.tuto.commonlibrary.converter.message;

import com.tuto.commonlibrary.model.message.AggregationMessage;
import com.tuto.commonlibrary.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ActiveProfiles({"test"})
public class AggregationConverterTest {
    @Autowired
    private JacksonTester<AggregationMessage> aggregationMessageJacksonTester;

    @Test
    @SneakyThrows
    void serializeAggregation() {
        // GIVEN
        final var aggregationContentStr = TestUtil.readJsonFrom("message/aggregation", "aggregation.json");
        final var aggregationMessage = aggregationMessageJacksonTester.parseObject(aggregationContentStr);
        // THEN
        assertThat(aggregationMessage).extracting(AggregationMessage::getMessageId).isEqualTo("TEST0002-ID-AGGREGATION");
        assertThat(aggregationMessage).extracting(AggregationMessage::getFid).isEqualTo("TEST0002");
        assertThat(aggregationMessage).extracting(AggregationMessage::getEoid).isEqualTo("TEST0002");
        assertThat(aggregationMessage).extracting(AggregationMessage::getEventTime).isEqualTo("20070216");
        assertThat(aggregationMessage).extracting(AggregationMessage::getRecordTime).isEqualTo("2020-01-01T10:00:00Z");
        assertThat(aggregationMessage).extracting(AggregationMessage::getAggregationType).isEqualTo(3);
        assertThat(aggregationMessage).extracting(AggregationMessage::getParentId).isEqualTo("parentUI");
        assertThat(aggregationMessage).extracting(AggregationMessage::getAggregatedUis1).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(aggregationMessage).extracting(AggregationMessage::getAggregatedUis2).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(aggregationMessage).extracting(AggregationMessage::getComment).isEqualTo("Aggregation comment");
    }

    @Test
    @SneakyThrows
    void deserializeAggregation() {
        // GIVEN
        final var expectedAggregationContentStr = TestUtil.readJsonFrom("message/aggregation", "aggregation.json");
        final var aggregationMessage = new AggregationMessage();
        aggregationMessage.setMessageId("TEST0002-ID-AGGREGATION");
        aggregationMessage.setEoid("TEST0002");
        aggregationMessage.setFid("TEST0002");
        aggregationMessage.setEventTime("20070216");
        aggregationMessage.setRecordTime("2020-01-01T10:00:00Z");
        aggregationMessage.setAggregationType(3);
        aggregationMessage.setParentId("parentUI");
        aggregationMessage.setAggregatedUis1(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        aggregationMessage.setAggregatedUis2(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        aggregationMessage.setComment("Aggregation comment");

        // THEN
        final var actualAggregationContentStr = aggregationMessageJacksonTester.write(aggregationMessage);
        JSONAssert.assertEquals(expectedAggregationContentStr, actualAggregationContentStr.getJson(), true);
    }
}
