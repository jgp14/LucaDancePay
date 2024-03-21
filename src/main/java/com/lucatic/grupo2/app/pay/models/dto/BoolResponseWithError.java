package com.lucatic.grupo2.app.pay.models.dto;

import com.lucatic.grupo2.app.pay.models.Error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BoolResponseWithError devuelve un booleano con un error encapsulado
 *
 * @author BlueDevTeams
 * @version v1.0.0
 * @since 18-03-2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoolResponseWithError {

    /**
     * Atributo con objeto Error
     */
    private Error error;
    /**
     * Atributo con atributo boolean
     */
    private boolean respBool;
    /**
     * Atributo con boolean para decidir si es o no un error
     */
    private boolean isErrorBool;
}
