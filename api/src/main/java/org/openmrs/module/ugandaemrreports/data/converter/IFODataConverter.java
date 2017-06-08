/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ugandaemrreports.data.converter;

import org.openmrs.Concept;
import org.openmrs.Obs;
import org.openmrs.module.reporting.data.converter.DataConverter;
import org.openmrs.module.ugandaemrreports.reporting.metadata.Dictionary;

/**
 * Created by Nicholas Ingosi on 5/30/17.
 */
public class IFODataConverter implements DataConverter {
    @Override
    public Object convert(Object obj) {
        if (obj == null) {
            return "";
        }
        Concept iyoConceptAns = ((Obs) obj).getValueCoded();
        if(iyoConceptAns != null && iyoConceptAns.equals(Dictionary.getConcept("dcbd637e-30ab-102d-86b0-7a5022ba4115"))) {
            return "EBF";
        }
        else if(iyoConceptAns != null && iyoConceptAns.equals(Dictionary.getConcept("40fdb5b6-e8ac-424d-988c-f2f2937348db"))) {
            return "RF";
        }

        else if(iyoConceptAns != null && iyoConceptAns.equals(Dictionary.getConcept("dcd5487d-30ab-102d-86b0-7a5022ba4115"))) {
            return "MF";
        }
        return null;
    }

    @Override
    public Class<?> getInputDataType() {
        return Obs.class;
    }

    @Override
    public Class<?> getDataType() {
        return String.class;
    }
}