package com.ilyabuglakov.xmltaskweb.model.gem;


import com.ilyabuglakov.xmltaskweb.model.gem.certificate.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
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