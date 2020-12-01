package com.ilyabuglakov.xmltask.model.gem;

import com.ilyabuglakov.xmltask.model.gem.certificate.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gem {
    /*
     Gem identification name [\w]{3, 16}[\d]{2}
     */
    private String name;
    private VisualParameters visualParameters;
    private Mineral mineral;

    private Preciousness preciousness;
    private Origin origin;
    private LocalDate minedDate;
    private Certificate certificate;
    private int weight;

}