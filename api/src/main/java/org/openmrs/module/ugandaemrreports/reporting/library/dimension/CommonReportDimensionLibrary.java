/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * <p>
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * <p>
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.ugandaemrreports.reporting.library.dimension;

import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.Parameterizable;
import org.openmrs.module.reporting.indicator.dimension.CohortDefinitionDimension;
import org.openmrs.module.ugandaemrreports.reporting.library.cohort.CommonCohortLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.openmrs.module.ugandaemrreports.reporting.utils.ReportUtils.map;


/**
 * Library of common dimension definitions
 */
@Component
public class CommonReportDimensionLibrary {

    @Autowired
    private CommonCohortLibrary commonCohortLibrary;

    /**
     * Gender dimension
     * @return the dimension
     */
    public CohortDefinitionDimension gender() {
        CohortDefinitionDimension dim = new CohortDefinitionDimension();
        dim.setName("gender");
        dim.addCohortDefinition("M", map(commonCohortLibrary.males()));
        dim.addCohortDefinition("F", map(commonCohortLibrary.females()));
        return dim;
    }

    /**
     * Dimension of age using the 4 standard age groups
     * @return the dimension
     */
    public CohortDefinitionDimension get106AgeGroups() {
        CohortDefinitionDimension dim = new CohortDefinitionDimension();
        dim.setName("age groups (<2, 2-4, 5-14,15+)");
        dim.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        dim.addCohortDefinition("<2", map(commonCohortLibrary.agedAtMost(1), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("2-<5", map(commonCohortLibrary.agedAtLeastAgedAtMost(2, 4), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("5-14", map(commonCohortLibrary.agedAtLeastAgedAtMost(5,14), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("15+", map(commonCohortLibrary.agedAtLeast(15), "effectiveDate=${endDate}"));
        return dim;
    }
    
    /**
     * MoH definiton of auult and children
     * @return
     */
	public CohortDefinitionDimension getMOHDefinedChildrenAndAdultAgeGroups() {
        CohortDefinitionDimension dim = new CohortDefinitionDimension();
        dim.setName("age groups (<15, 15+)");
        dim.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        dim.addCohortDefinition("<15", map(commonCohortLibrary.MoHChildren(), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("15+", map(commonCohortLibrary.MoHAdult(), "effectiveDate=${endDate}"));
        return dim;
	}

	public CohortDefinitionDimension standardAgeGroupsForMaternity() {
        CohortDefinitionDimension dim = new CohortDefinitionDimension();
        dim.setName("age groups (10-19, 20-24, >=25)");
        dim.addParameter(new Parameter("effectiveDate", "Effective Date", Date.class));
        dim.addCohortDefinition("Between10And19", map(commonCohortLibrary.agedAtLeastAgedAtMost(10,19), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("Between20And24", map(commonCohortLibrary.agedAtLeastAgedAtMost(20,24), "effectiveDate=${endDate}"));
        dim.addCohortDefinition("GreaterOrEqualTo25", map(commonCohortLibrary.agedAtLeast(25), "effectiveDate=${endDate}"));
        return dim;
	}
}