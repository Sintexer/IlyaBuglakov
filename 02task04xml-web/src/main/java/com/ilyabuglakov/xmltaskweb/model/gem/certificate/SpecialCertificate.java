package com.ilyabuglakov.xmltaskweb.model.gem.certificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Contains a special reason, which prompted gem to receive certificate
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialCertificate extends Certificate {
    private String specialReason;

    @Override
    public String toString() {
        return "SpecialCertificate{" + getCertificateId() + " "
                + getOrganisation() + " "
                + getReceivingDate() + "for special reason: " +
                specialReason + '}';
    }
}
