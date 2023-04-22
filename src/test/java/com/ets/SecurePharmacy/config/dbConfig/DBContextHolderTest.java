package com.ets.SecurePharmacy.config.dbConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DBContextHolderTest {
    /**
     * Method under test: {@link DBContextHolder#setCurrentDb(String)}
     */
    @Test
    void testSetCurrentDb() {
        // Arrange and Act
        DBContextHolder.setCurrentDb("Db Type");
        String result = DBContextHolder.getCurrentDb();
        Assertions.assertNotNull(result);
    }

    /**
     * Method under test: {@link DBContextHolder#clear()}
     */
    @Test
    void testClear() {
        // Arrange and Act
        DBContextHolder.clear();
        String result = DBContextHolder.getCurrentDb();
        Assertions.assertNull(result);
    }
}

