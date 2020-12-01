package com.ilyabuglakov.xmltask.model.gem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisualParameters {
    private Color color;
    private short transparency;
    ///Optional parameter, default value zero
    private short facesNumber;
}
