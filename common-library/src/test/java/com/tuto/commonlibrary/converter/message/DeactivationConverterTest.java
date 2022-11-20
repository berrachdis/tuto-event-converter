package com.tuto.commonlibrary.converter.message;

import com.tuto.commonlibrary.model.message.DeactivationMessage;
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
public class DeactivationConverterTest {
    @Autowired
    private JacksonTester<DeactivationMessage> deactivationMessageJacksonTester;

    @Test
    @SneakyThrows
    void serializeDeactivation() {
        // GIVEN
        final var deactivationContentStr = TestUtil.readJsonFrom("message/deactivation", "deactivation_valid.json");
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
        final var expectedDeactivationContentStr = TestUtil.readJsonFrom("message/deactivation", "deactivation_valid.json");
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

    @CsvSource(value = {
            "deactivation_without_message_id.json; The Message_ID cannot be null or empty",
            "deactivation_without_eo_id.json; The EO_ID cannot be null or empty",
            "deactivation_without_event_time.json; The Event_Time cannot be null or empty",
            "deactivation_with_bad_event_time.json; The Event_Time must match with yyMMddHH format",
            "deactivation_without_record_time.json; The Record_Time cannot be null or empty",
            "deactivation_without_deact_type.json; The Deact_Type must be between [1, 3]",
            "deactivation_without_deact_aUI.json; The Deact_aUI cannot be null or empty",
            "deactivation_without_deact_upUI.json; The Deact_upUI cannot be null or empty",
    }, delimiter = ';')
    @SneakyThrows
    @ParameterizedTest
    void deactivationValidationWithMissedFieldShouldFail(String fileName, String expectedMessageError) {
        final var deactivationContentStr = TestUtil.readJsonFrom("message/deactivation", fileName);
        final var deactivationMessage = deactivationMessageJacksonTester.parseObject(deactivationContentStr);
        final var constraintViolations = VALIDATOR.validate(deactivationMessage);
        assertEquals(1, constraintViolations.size());
        assertEquals(expectedMessageError, constraintViolations.iterator().next().getMessage());
    }
}
