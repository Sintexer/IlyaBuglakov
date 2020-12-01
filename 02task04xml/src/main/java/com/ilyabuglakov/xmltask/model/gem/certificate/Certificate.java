package com.ilyabuglakov.xmltask.model.gem.certificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Some gems have their certificate, that's making them unique among other gems
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {
    /*
    pattern of certificate id is [\w]{2}[\d}{6}[\w]{3,16}
     */
    private String certificateId;
    private String organisation;
    private LocalDate receivingDate;
}
