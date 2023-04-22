package com.ets.SecurePharmacy.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {DateConvertUtils.class})
@ExtendWith(SpringExtension.class)
class DateConvertUtilsTest {
    @Autowired
    private DateConvertUtils dateConvertUtils;

    /**
     * Method under test: {@link DateConvertUtils#convertStringToDate(String)}
     */
    @Test
    void testConvertStringToDate() {
        assertEquals("2020-03-01", dateConvertUtils.convertStringToDate("2020-03-01").toString());
        assertNotNull(dateConvertUtils.convertStringToDate(null));
    }


    /**
     * Method under test: {@link DateConvertUtils#convertDatetoString(LocalDate)}
     */
    @Test
    void testConvertDatetoString() {
        assertEquals("1970-01-02", dateConvertUtils.convertDatetoString(LocalDate.ofEpochDay(1L)));
    }
}

