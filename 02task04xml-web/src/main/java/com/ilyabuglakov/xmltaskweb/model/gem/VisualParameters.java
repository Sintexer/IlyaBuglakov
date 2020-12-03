package com.ilyabuglakov.xmltaskweb.model.gem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VisualParameters {
    private Color color;
    private int transparency;
    ///Optional parameter, default value zero
    private int facesNumber;
}
