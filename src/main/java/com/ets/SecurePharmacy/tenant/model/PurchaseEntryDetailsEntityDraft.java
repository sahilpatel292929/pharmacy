package com.ets.SecurePharmacy.tenant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TBL_PURCHASE_ENTRY_DETAILS_DRAFT")
@Getter
@Data
@Setter
@NoArgsConstructor
@Transactional
public class PurchaseEntryDetailsEntityDraft {

    /**
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @NotNull
    @Column(name = "invoice_num")
    private String invoiceNo;
    @Column(name = "batch")
    private String batch;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "best_before")
    private String bestBefore;
    @Column(name = "qty")
    private Long qty;
    @Column(name = "free_qty")
    private Long freeQty;
    @Column(name = "mrp")
    private Long mrp;
    @Column(name = "purchase_rate")
    private Long purchaseRate;
    @Column(name = "discount")
    private Long discount;
    @Column(name = "disc_amount")
    private Long discAmount;
    @Column(name = "schdiscAmount")
    private Long schdiscAmount;
    @Column(name = "gst")
    private Long gst;
    @Column(name = "taxAmount")
    private Long taxAmount;
    @Column(name = "qpk")
    private Long qpk;
    @Column(name = "maxdate")
    private Long maxdate;
    @Column(name = "srt")
    private Long srt;
    @Column(name = "gross_amt")
    private Long grossAmt;
    @Column(name = "netAmt")
    private Long netAmt;
    @Column(name = "mfgdateFlag")
    private Boolean mfgdateFlag;
    @Column(name = "status")
    private String status;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    PurchaseEntryEntityDraft purchaseEntryEntity;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    ItemCreationEntity items;

}
