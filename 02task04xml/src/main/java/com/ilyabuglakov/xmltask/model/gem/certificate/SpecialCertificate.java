package com.ilyabuglakov.xmltask.model.gem.certificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Contains a special reason, which prompted gem to receive sertificate
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpecialCertificate extends Certificate {
    private String specialReason;
}
