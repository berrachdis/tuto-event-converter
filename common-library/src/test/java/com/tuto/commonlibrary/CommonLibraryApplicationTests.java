package com.tuto.commonlibrary;

import com.tuto.commonlibrary.model.message.AggregationMessage;
import com.tuto.commonlibrary.model.message.CommissioningMessage;
import com.tuto.commonlibrary.model.message.DeactivationMessage;
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
class CommonLibraryApplicationTests {

    @Autowired
    private JacksonTester<CommissioningMessage> commissioningMessageJacksonTester;
    @Autowired
    private JacksonTester<AggregationMessage> aggregationMessageJacksonTester;
    @Autowired
    private JacksonTester<DeactivationMessage> deactivationMessageJacksonTester;

    @Test
    @SneakyThrows
    void serializeCommissioning() {
        // GIVEN
        final var commissioningContentStr = TestUtil.readJsonFrom("message", "commissioning.json");
        final var commissioningMessage = commissioningMessageJacksonTester.parseObject(commissioningContentStr);
        // THEN
        assertThat(commissioningMessage).extracting(CommissioningMessage::getMessageId).isEqualTo("TEST0002-ID-DEACTIVATION");
        assertThat(commissioningMessage).extracting(CommissioningMessage::getFid).isEqualTo("TEST0002");
        assertThat(commissioningMessage).extracting(CommissioningMessage::getEventTime).isEqualTo("20070216");
        assertThat(commissioningMessage).extracting(CommissioningMessage::getRecordTime).isEqualTo("2020-01-01T10:00:00Z");
        assertThat(commissioningMessage).extracting(CommissioningMessage::getUpUI1).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(commissioningMessage).extracting(CommissioningMessage::getUpUI2).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(commissioningMessage).extracting(CommissioningMessage::getCommissioningComment).isEqualTo("Commissioning comment");
    }

    @Test
    @SneakyThrows
    void deserializeCommissioning() {
        // GIVEN
        final var expectedCommissioningContentStr = TestUtil.readJsonFrom("message", "commissioning.json");
        final var commissioningMessage = new CommissioningMessage();
        commissioningMessage.setMessageId("TEST0002-ID-DEACTIVATION");
        commissioningMessage.setFid("TEST0002");
        commissioningMessage.setEventTime("20070216");
        commissioningMessage.setRecordTime("2020-01-01T10:00:00Z");
        commissioningMessage.setUpUI1(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        commissioningMessage.setUpUI2(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        commissioningMessage.setCommissioningComment("Commissioning comment");

        // THEN
        final var actualCommissioningContentStr = commissioningMessageJacksonTester.write(commissioningMessage);
        JSONAssert.assertEquals(expectedCommissioningContentStr, actualCommissioningContentStr.getJson(), true);
    }

    @Test
    @SneakyThrows
    void serializeAggregation() {
        // GIVEN
        final var aggregationContentStr = TestUtil.readJsonFrom("message", "aggregation.json");
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
        final var expectedAggregationContentStr = TestUtil.readJsonFrom("message", "aggregation.json");
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

    @Test
    @SneakyThrows
    void serializeDeactivation() {
        // GIVEN
        final var deactivationContentStr = TestUtil.readJsonFrom("message", "deactivation.json");
        final var deactivationMessage = deactivationMessageJacksonTester.parseObject(deactivationContentStr);

        // THEN
        assertThat(deactivationMessage).extracting(DeactivationMessage::getMessageId).isEqualTo("TEST0002-ID-DEACTIVATION");
        assertThat(deactivationMessage).extracting(DeactivationMessage::getEoid).isEqualTo("TEST0002");
        assertThat(deactivationMessage).extracting(DeactivationMessage::getEventTime).isEqualTo("20070216");
        assertThat(deactivationMessage).extracting(DeactivationMessage::getRecordTime).isEqualTo("2020-01-01T10:00:00Z");
        assertThat(deactivationMessage).extracting(DeactivationMessage::getDeactType).isEqualTo(3);
        assertThat(deactivationMessage).extracting(DeactivationMessage::getDeactUpUIs).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(deactivationMessage).extracting(DeactivationMessage::getDeactAUIs).isEqualTo(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        assertThat(deactivationMessage).extracting(DeactivationMessage::getDeactivationComment).isEqualTo("Deactivation comment");
    }

    @Test
    @SneakyThrows
    void deserializeDeactivation() {
        // GIVEN
        final var expectedDeactivationContentStr = TestUtil.readJsonFrom("message", "deactivation.json");
        final var deactivationMessage = new DeactivationMessage();
        deactivationMessage.setMessageId("TEST0002-ID-DEACTIVATION");
        deactivationMessage.setEoid("TEST0002");
        deactivationMessage.setEventTime("20070216");
        deactivationMessage.setRecordTime("2020-01-01T10:00:00Z");
        deactivationMessage.setDeactType(3);
        deactivationMessage.setDeactUpUIs(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        deactivationMessage.setDeactAUIs(List.of("LEGR1seriSB128", "LEGR1seriSB129"));
        deactivationMessage.setDeactivationComment("Deactivation comment");

        // THEN
        final var actualDeactivationMessage = deactivationMessageJacksonTester.write(deactivationMessage);
        JSONAssert.assertEquals(expectedDeactivationContentStr, actualDeactivationMessage.getJson(), true);
    }

}
