package com.ets.SecurePharmacy.serviceimpl;

import com.ets.SecurePharmacy.tenant.dao.SalesInvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {SaleReportsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SaleReportsServiceImplTest {
    @Autowired
    private SaleReportsServiceImpl saleReportsServiceImpl;

    @MockBean
    private SalesInvoiceRepository salesInvoiceRepository;

    /**
     * Method under test: {@link SaleReportsServiceImpl#getSaleReport(LocalDate, LocalDate, String, String, String, String)}
     */
    @Test
    void testGetSaleReport() {
        // Arrange, Act and Assert
        assertNull(saleReportsServiceImpl.getSaleReport(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L), "Item Name",
                "Group Name", "Mfg Name", "Supplier Name"));
        assertNull(saleReportsServiceImpl.getSaleReport(null, LocalDate.ofEpochDay(1L), "Item Name", "Group Name",
                "Mfg Name", "Supplier Name"));
        assertNull(saleReportsServiceImpl.getSaleReport(LocalDate.ofEpochDay(1L), null, "Item Name", "Group Name",
                "Mfg Name", "Supplier Name"));
    }
}

