package com.tuto.commonlibrary.converter.message;

import com.tuto.commonlibrary.model.message.AggregationMessage;
import com.tuto.commonlibrary.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.tuto.commonlibrary.util.TestUtil.VALIDATOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @CsvSource(value = {
            "aggregation_without_message_id.json; The Message_ID cannot be null or empty",
            "aggregation_without_fid.json; The F_ID cannot be null or empty",
            "aggregation_without_eo_id.json; The EO_ID cannot be null or empty",
            "aggregation_without_event_time.json; The Event_Time cannot be null or empty",
            "aggregation_with_bad_event_time.json; The Event_Time must match with yyMMddHH format",
            "aggregation_without_record_time.json; The Record_Time cannot be null or empty",
            "aggregation_with_bad_aggregation_type.json; The Aggregation_Type must be between [1, 3]",
            "aggregation_without_aggregation_type.json; The Aggregation_Type must be between [1, 3]",
            "aggregation_without_parentAUI.json; The parentAUI cannot be null or empty",
            "aggregation_without_aggregated_uis1.json; The Aggregated_UIs1 cannot be null or empty",
            "aggregation_without_aggregated_uis2.json; The Aggregated_UIs2 cannot be null or empty",
    }, delimiter = ';')
    @SneakyThrows
    @ParameterizedTest
    void aggregationValidationWithMissedFieldShouldFail(String fileName, String expectedMessageError) {
        final var aggregationContentStr = TestUtil.readJsonFrom("message/aggregation", fileName);
        final var aggregationMessage = aggregationMessageJacksonTester.parseObject(aggregationContentStr);
        final var constraintViolations = VALIDATOR.validate(aggregationMessage);
        assertEquals(1, constraintViolations.size());
        assertEquals(expectedMessageError, constraintViolations.iterator().next().getMessage());
    }
}
