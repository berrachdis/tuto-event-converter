package com.tuto.commonlibrary.converter.message;

import com.tuto.commonlibrary.model.message.CommissioningMessage;
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
public class CommissioningConverterTest {
    @Autowired
    private JacksonTester<CommissioningMessage> commissioningMessageJacksonTester;

    @Test
    @SneakyThrows
    void serializeCommissioning() {
        // GIVEN
        final var commissioningContentStr = TestUtil.readJsonFrom("message/commissioning", "commissioning.json");
        final var commissioningMessage = commissioningMessageJacksonTester.parseObject(commissioningContentStr);
        // THEN
        assertThat(commissioningMessage).extracting(CommissioningMessage::getMessageId).isEqualTo("TEST0002-ID-COMMISSIONING");
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
        final var expectedCommissioningContentStr = TestUtil.readJsonFrom("message/commissioning", "commissioning.json");
        final var commissioningMessage = new CommissioningMessage();
        commissioningMessage.setMessageId("TEST0002-ID-COMMISSIONING");
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
}
