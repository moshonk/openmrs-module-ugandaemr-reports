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
package org.openmrs.module.ugandaemrreports.library;

import org.openmrs.Concept;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.ugandaemrreports.reporting.metadata.Dictionary;
import org.openmrs.module.ugandaemrreports.reporting.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ugandaemrreports.UgandaEMRReportUtil.map;
import static org.openmrs.module.ugandaemrreports.reporting.utils.EmrReportingUtils.cohortIndicator;

/**
 * Created by Nicholas Ingosi on 5/23/17.
 * * Library of ANC related indicator definitions. All indicators require parameters ${startDate} and ${endDate}
 */
@Component
public class Moh105IndicatorLibrary {

	@Autowired
    private Moh105CohortLibrary cohortLibrary;

    @Autowired
    private CommonCohortDefinitionLibrary cclibrary;

    /**
     * Number of female patients with ANC 1st visit
     */
    public CohortIndicator anc1stVisit(){
        return cohortIndicator("Patients who have ANC 1st Visit", map(cohortLibrary.femaleAndHasAncVisit(0.0, 1.0), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Number of female patients with ANC 4th visit
     */
    public CohortIndicator anc4thVisit(){
        return cohortIndicator("Patients who have ANC 4th Visit", map(cohortLibrary.femaleAndHasAncVisit(3.0, 4.0), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Number of female patients with ANC 4th visit and above
     */
    public CohortIndicator anc4thPlusVisit(){
        return cohortIndicator("Patients who have ANC 4th Visit and above", map(cohortLibrary.femaleAndHasAncVisit(4.0, 9.0), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Total number of patients with ANC visits
     * @return CohortIndicator
     */
    public CohortIndicator totalAncVisits() {
        return cohortIndicator("Patients who have ANC Visits", map(cohortLibrary.femaleAndHasAncVisit(0.0, 9.0), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Referral to ANC unit from community services
     * @return CohortIndicator
     */
    public CohortIndicator referalToAncUnitFromCommunityServices(){
        return cohortIndicator("Referral to ANC unit from community services", map(cclibrary.hasObs(Dictionary.getConcept("cd27f0ac-0fd3-4f40-99a3-57742106f5fd"), Dictionary.getConcept("03997d45-f6f7-4ee2-a6fe-b16985e3495d")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Referral to ANC unit total
     * @return CohortIndicator
     */
    public CohortIndicator referalToAncUnitTotal(){
        return cohortIndicator("Referral to ANC unit totals", map(cclibrary.hasObs(Dictionary.getConcept("cd27f0ac-0fd3-4f40-99a3-57742106f5fd"), Dictionary.getConcept("03997d45-f6f7-4ee2-a6fe-b16985e3495d"), Dictionary.getConcept("14714862-6c78-49da-b65b-f249cccddfb6")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Referral from ANC unit total
     * @return CohortIndicator
     */
    public CohortIndicator referalFromAncUnitTotal(){
        return cohortIndicator("Referral from ANC unit totals", map(cclibrary.hasObs(Dictionary.getConcept("cd27f0ac-0fd3-4f40-99a3-57742106f5fd"), Dictionary.getConcept("6442c9f6-25e8-4c8e-af8a-e9f6845ceaed"), Dictionary.getConcept("3af0aae4-4ea7-489d-a5be-c5339f7c5a77")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    /**
     * Referral from ANC unit FSG
     * @return CohortIndicator
     */
    public CohortIndicator referalFromAncUniFsg(){
        return cohortIndicator("Referral from ANC unit FSG", map(cclibrary.hasObs(Dictionary.getConcept("cd27f0ac-0fd3-4f40-99a3-57742106f5fd"), Dictionary.getConcept("3af0aae4-4ea7-489d-a5be-c5339f7c5a77")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * IPT(IPT1) Dose
     * @return CohortIndicator
     */
    public CohortIndicator iptDose(Concept answer){
        return cohortIndicator("Ipt dose on "+answer.getName().getName(), map(cclibrary.hasObs(Dictionary.getConcept("1da3cb98-59d8-4bfd-b0bb-c9c1bcd058c6"), answer), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * A9-Pregnant Women receiving Iron/Folic Acid on ANC 1 st Visit
     * @return CohortIndicator
     */
    public CohortIndicator pregnantAndReceivingIronOrFolicAcidAnc1stVisit() {
        return cohortIndicator("Pregnant Women receiving Iron/Folic Acid on ANC 1 st Visit", map(cohortLibrary.pregnantAndReceivingIronOrFolicAcidAnc1stVisit(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant women having different obs
     * @return CohortIndicator
     */
    public CohortIndicator pregnantAndReceivingServices(Concept question, Concept answer) {
        return cohortIndicator("Receiving different servicess", map(cclibrary.hasObs(question, answer), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant women tested for syphilis
     * @return CohortIndicator
     */
    public CohortIndicator pregnantAndTestedForSyphilis() {
        return cohortIndicator("Pregnant women tested for syphilis", map(cclibrary.hasObs(Dictionary.getConcept("275a6f72-b8a4-4038-977a-727552f69cb8")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant Women newly tested for HIV this pregnancy (TR & TRR)
     * @return CohortIndicator
     */
    public CohortIndicator pregnantWomenNewlyTestedForHivThisPregnancyTRAndTRR() {
        return cohortIndicator("Pregnant Women newly tested for HIV this pregnancy (TR & TRR)", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("05f16fc5-1d82-4ce8-9b44-a3125fbbf2d7"), Dictionary.getConcept("86e394fd-8d85-4cb3-86d7-d4b9bfc3e43a")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant Women tested HIV+ for 1st time this pregnancy (TRR) at any visit
     * @return CohortIndicator
     */
    public CohortIndicator pregnantWomenNewlyTestedForHivThisPregnancyTRR() {
        return cohortIndicator("Pregnant Women tested HIV+ for 1st time this pregnancy (TRR) at any visit", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"),  Dictionary.getConcept("86e394fd-8d85-4cb3-86d7-d4b9bfc3e43a")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * HIV+ Pregnant women assessed by CD4 or WHO clinical stage the 1 time
     * @return CohortIndicator
     */
    public CohortIndicator hivPositiveAndAccessedWithCd4WhoStage(Concept question) {
        return cohortIndicator("Pregnant women assessed by "+question.getName().getName(), map(cohortLibrary.hivPositiveAndAccessedWithCd4WhoStage(question), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**Pregnant women assessed by
     * HIV+ Pregnant Women initiated on ART for EMTCT (ART)
     * @return CohortIndicator
     */
    public CohortIndicator hivPositiveInitiatedART() {
        return cohortIndicator("Pregnant Women tested HIV+ and initiated on ART", map(cclibrary.hasObs(Dictionary.getConcept("a615f932-26ee-449c-8e20-e50a15232763"),  Dictionary.getConcept("026e31b7-4a26-44d0-8398-9a41c40ff7d3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant women who knew status before 1st ANC total (TRK+TRRK)
     * @return CohortIndicator
     */
    public CohortIndicator pregnantTrkTrrk() {
        return cohortIndicator("Pregnant women who knew status before 1st ANC total (TRK+TRRK)", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("81bd3e58-9389-41e7-be1a-c6723f899e56"), Dictionary.getConcept("1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant women who knew status before 1st ANC total TRRK
     * @return CohortIndicator
     */
    public CohortIndicator pregnantTrrk() {
        return cohortIndicator("Pregnant women who knew status before 1st ANC total TRRK", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant Women already on ART before 1 ANC (ART-K)
     * @return CohortIndicator
     */
    public CohortIndicator alreadyOnARTK(){
        return cohortIndicator("Pregnant Women already on ART before 1 ANC (ART-K)", map(cclibrary.hasObs(Dictionary.getConcept("a615f932-26ee-449c-8e20-e50a15232763"), Dictionary.getConcept("2aa7d442-6cbb-4609-9dd3-bc2ad6f05016")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant Women re-tested later in pregnancy (TR+&TRR+)
     * @return CohortIndicator
     */
    public CohortIndicator retestedTrTrrPlus(){
        return cohortIndicator("Pregnant Women re-tested later in pregnancy (TR+&TRR+))", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("81bd3e58-9389-41e7-be1a-c6723f899e56"), Dictionary.getConcept("1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Pregnant Women testing HIV+ on a retest (TRR+)
     * @return CohortIndicator
     */
    public CohortIndicator retestedTrrPlus() {
        return cohortIndicator("Pregnant Women testing HIV+ on a retest (TRR+)", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * HIV+ Pregnant Women initiated on Cotrimoxazole
     * @return CohortIndicator
     */
    public CohortIndicator initiatedOnCtx() {
        return cohortIndicator("Pregnant Women initiated on Cotrimoxazole", map(cclibrary.hasObs(Dictionary.getConcept("1da3cb98-59d8-4bfd-b0bb-c9c1bcd058c6"), Dictionary.getConcept("fca28768-50dc-4d6b-a3d2-2aae3b376b27")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * Male partiners received HIV test results in eMTCT total
     * @return CohortIndicator
     */
    public CohortIndicator malePatinersRecievedHivResultTotal() {
        return cohortIndicator("Male partners received HIV test results in eMTCT - Totals", map(cclibrary.hasObs(Dictionary.getConcept("62a37075-fc2a-4729-8950-b9fae9b22cfb"), Dictionary.getConcept("05f16fc5-1d82-4ce8-9b44-a3125fbbf2d7"), Dictionary.getConcept("86e394fd-8d85-4cb3-86d7-d4b9bfc3e43a")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Male partiners received HIV test results in eMTCT HIV+
     * @return CohortIndicator
     */
    public CohortIndicator malePatinersRecievedHivResultHivPositive() {
        return cohortIndicator("Male partners received HIV test results in eMTCT - HIV+", map(cclibrary.hasObs(Dictionary.getConcept("62a37075-fc2a-4729-8950-b9fae9b22cfb"), Dictionary.getConcept("86e394fd-8d85-4cb3-86d7-d4b9bfc3e43a")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *Maternity Admissions
     * @return CohortIndicator
     */
    public CohortIndicator maternityAdmissions() {
        return cohortIndicator("Maternity admissions", map(cohortLibrary.maternityAdmissions(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *Referrals to Maternity Unit
     * @return CohortIndicator
     */
    public CohortIndicator referralsToMaternityUnit() {
        return cohortIndicator("Referrals to maternity unit", map(cclibrary.hasTextObs(Dictionary.getConcept("c9159851-557b-4c09-8942-65b7989aa20a"), "REF"), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Referrals out of Maternity Unit
     * @return CohortIndicator
     */
    public CohortIndicator maternityReferralsOut() {
        return cohortIndicator("Maternity referrals Out", map(cclibrary.hasObs(Dictionary.getConcept("e87431db-b49e-4ab6-93ee-a3bd6c616a94"), Dictionary.getConcept("6e4f1db1-1534-43ca-b2a8-5c01bc62e7ef")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Deliveries in unit - Total
     * @return CohortIndicator
     */
    public CohortIndicator deliveriesInUnit() {
        return cohortIndicator("Deliveries in unit", map(cohortLibrary.deliveriesInUnit(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Deliveries in unit - 10 -19
     * @return CohortIndicator
     */
    public CohortIndicator deliveriesInUnit10To19() {
        return cohortIndicator("Deliveries in unit 10-19", map(cohortLibrary.deliveriesInUnit10To19(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Deliveries in unit - 20 - 24
     * @return CohortIndicator
     */
    public CohortIndicator deliveriesInUnit20To24() {
        return cohortIndicator("Deliveries in unit 20-24", map(cohortLibrary.deliveriesInUnit20To24(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Deliveries in unit - 25 and above
     * @return CohortIndicator
     */
    public CohortIndicator deliveriesInUnit25AndAbove() {
        return cohortIndicator("Deliveries in unit 25 and above", map(cohortLibrary.deliveriesInUnit25AndAbove(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *Fresh still birth deliveries
     * @return CohortIndicator
     */
    public CohortIndicator freshStillBirthDeliveries() {
        return cohortIndicator("Fresh still birth", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"), Dictionary.getConcept("7a15616a-c12a-44fc-9a11-553639128b69")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Macerated still birth deliveries
     * @return CohortIndicator
     */
    public CohortIndicator maceratedStillBirthDeliveries() {
        return cohortIndicator("Macerated still birth", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"), Dictionary.getConcept("fda5ad21-6ba4-4526-a0f3-ea1269d43422")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Live births
     * @return CohortIndicator
     */
    public CohortIndicator liveBirthDeliveries() {
        return cohortIndicator("Live births", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"), Dictionary.getConceptList("eb7041a0-02e6-4e9a-9b96-ff65dd09a416,23ac7575-f0ea-49a5-855e-b3348ad1da01")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *Pre-Term births
     * @return CohortIndicator
     */
    public CohortIndicator pretermBirthDeliveries() {
        return cohortIndicator("Pre-Term births", map(cclibrary.hasObs(Dictionary.getConcept("161033AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), Dictionary.getConcept("129218AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M5: Women tested for HIV in labour - 1st time this Pregnancy
     * @return CohortIndicator
     */
    public CohortIndicator womenTestedForHivInLabourFirstTimePregnancy() {
        return cohortIndicator("Women tested for HIV in labour - 1st time this Pregnancy", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConceptList("81bd3e58-9389-41e7-be1a-c6723f899e56,1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M5: Women tested for HIV in labour - Retest this Pregnancy
     * @return CohortIndicator
     */
    public CohortIndicator womenTestedForHivInLabourRetestThisPregnancy() {
        return cohortIndicator("Women tested for HIV in labour - Retest this Pregnancy", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConceptList("05f16fc5-1d82-4ce8-9b44-a3125fbbf2d7,86e394fd-8d85-4cb3-86d7-d4b9bfc3e43a,25c448ff-5fe4-4a3a-8c0a-b5aaea9d5465,60155e4d-1d49-4e97-9689-758315967e0f")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M6: Women testing HIV+ in labour - 1st time this Pregnancy
     * @return CohortIndicator
     */
    public CohortIndicator womenTestingHivPositiveInLabourFirstTimePregnancy() {
        return cohortIndicator("M6: Women testing HIV+ in labour - 1st time this Pregnancy", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConcept("1f177240-85f6-4f10-964a-cfc7722408b3")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M6: Women testing HIV+ in labour - Retest this Pregnancy
     * @return CohortIndicator
     */
    public CohortIndicator womenTestingHivPositiveInLabourRetestThisPregnancy() {
        return cohortIndicator("M6: Women testing HIV+ in labour - Retest this Pregnancy", map(cclibrary.hasObs(Dictionary.getConcept("d5b0394c-424f-41db-bc2f-37180dcdbe74"), Dictionary.getConceptList("25c448ff-5fe4-4a3a-8c0a-b5aaea9d5465,60155e4d-1d49-4e97-9689-758315967e0f")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M7: HIV+ women initiating ART in maternity
     * @return CohortIndicator
     */
    public CohortIndicator hivPositiveWomenInitiatingArtInMaternity() {
        return cohortIndicator("HIV+ women initiating ART in maternity", map(cohortLibrary.hivPositiveWomenInitiatingArvInMaternity(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M8: Deliveries to HIV+ women in unit - Total
     * @return CohortIndicator
     */
    public CohortIndicator deliveriesTohivPositiveWomen() {
        return cohortIndicator("Deliveries to HIV+ women", map(cohortLibrary.deliveriesToHIVPositiveWomen(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M8: Deliveries to HIV+ women in unit - Live births
     * @return CohortIndicator
     */
    public CohortIndicator liveBirthDeliveriesTohivPositiveWomen() {
        return cohortIndicator("Live birth deliveries to HIV+ women", map(cohortLibrary.liveBirthDeliveriesToHIVPositiveWomen(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M9: HIV Exposed babies given ARVs
     * @return CohortIndicator
     */
    public CohortIndicator hivExposedBabiesGivenArvs() {
        return cohortIndicator("HIV exposed babies given ARVs", map(cclibrary.hasObs(Dictionary.getConcept("9e825e42-be00-4d4d-8774-257ddb29581b")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M10: No. of mothers who initiated breastfeeding within the 1st hour after delivery - Total
     * @return CohortIndicator
     */
    public CohortIndicator initiatedBreastfeedingWithinFirstHourAfterDelivery() {
        return cohortIndicator("Initiated breastfeeding within the 1st hour after delivery", map(cohortLibrary.initiatedBreastfeedingWithinFirstHourAfterDelivery(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M10: No. of mothers who initiated breastfeeding within the 1st hour after delivery - HIV+
     * @return CohortIndicator
     */
    public CohortIndicator initiatedBreastfeedingWithinFirstHourAfterDeliveryAndHivPositive() {
        return cohortIndicator("Initiated breastfeeding within the 1st hour after delivery and HIV+", map(cohortLibrary.initiatedBreastfeedingWithinFirstHourAfterDeliveryAndHIVPositive(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M11: Babies born with low birth weight (<2.5kg)
     * @return CohortIndicator
     */
    public CohortIndicator babiesBornWithLowBirthWeight() {
        return cohortIndicator("Babies born with low birthweight", map(cohortLibrary.babiesBornWithLowBirthWeight(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M12: Live babies
     * @return CohortIndicator
     */
    public CohortIndicator liveBabies() {
        return cohortIndicator("Live babies", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"),Dictionary.getConceptList("eb7041a0-02e6-4e9a-9b96-ff65dd09a416,23ac7575-f0ea-49a5-855e-b3348ad1da01")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M13: Babies born with defect
     * @return CohortIndicator
     */
    public CohortIndicator babiesBornWithDefect() {
        return cohortIndicator("Babies born with defect", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"),Dictionary.getConcept("23ac7575-f0ea-49a5-855e-b3348ad1da01")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M15: Newborn deaths (0-7 days)
     * @return CohortIndicator
     */
    public CohortIndicator newBornDeaths() {
        return cohortIndicator("New born deaths", map(cclibrary.hasObs(Dictionary.getConcept("a5638850-0cb4-4ce8-8e87-96fc073de25d"),Dictionary.getConcept("ab3a7679-f5ee-48d6-b690-f55a1dfe95ea")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M16: Maternal deaths 10-19
     * @return CohortIndicator
     */
    public CohortIndicator maternalDeaths10To19() {
        return cohortIndicator("Maternal deaths 10-19", map(cohortLibrary.maternalDeathsAge10To19(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M16: Maternal deaths 20-24
     * @return CohortIndicator
     */
    public CohortIndicator maternalDeaths20To24() {
        return cohortIndicator("Maternal deaths 20-24", map(cohortLibrary.maternalDeathsAge20To24(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *M16: Maternal deaths 25 and above
     * @return CohortIndicator
     */
    public CohortIndicator maternalDeaths25AndAbove() {
        return cohortIndicator("Maternal deaths 25 and above", map(cohortLibrary.maternalDeathsAge25AndAbove(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M18: Birth asphyxia
     * @return CohortIndicator
     */
    public CohortIndicator birthAsphyxia() {
        return cohortIndicator("Birth asphyxia", map(cclibrary.hasObs(Dictionary.getConcept("121397AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), Dictionary.getConcept("1065AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *M19: No. of babies who received PNC at 6 hours
     * @return CohortIndicator
     */
    public CohortIndicator babiesReceivedPncAt6Hours() {
        return cohortIndicator("Babies received PNC at 6 hours", map(cclibrary.hasObs(Dictionary.getConcept("93ca1215-5346-4fde-8905-84e930d9f1c1")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    // Begin Family planning section
    
    /**
     *F1-Oral : Lo-Femenal
     * @return CohortIndicator
     */
    public CohortIndicator oralLofemenalFamilyPlanningUsers() {
        return cohortIndicator("Oral : Lo-Femenal", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("38aa1dc0-1aaa-4bdd-b26f-28f960dfb16c")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F2-Oral: Microgynon
     * @return CohortIndicator
     */
    public CohortIndicator oralMicrogynonFamilyPlanningUsers() {
        return cohortIndicator("Oral: Microgynon", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("4b0899f2-395e-4e0f-8b58-d304b214615e")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F3-Oral: Ovrette or another POP
     * @return CohortIndicator
     */
    public CohortIndicator oralOvretteFamilyPlanningUsers() {
        return cohortIndicator("Oral: Ovrette", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("82624AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F4-Oral: Others
     * @return CohortIndicator
     */
    public CohortIndicator oralOtherFamilyPlanningUsers() {
        return cohortIndicator("Oral: Others", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("670b7048-d71e-483a-b2ec-f10d2326dd84")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F5-Female condoms
     * @return CohortIndicator
     */
    public CohortIndicator femaleCondomsFamilyPlanningUsers() {
        return cohortIndicator("Oral: Female Condoms", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("dc882c84-30ab-102d-86b0-7a5022ba4115")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     *F6-Male condoms
     * @return CohortIndicator
     */
    public CohortIndicator maleCondomsFamilyPlanningUsers() {
        return cohortIndicator("Oral: Male Condoms", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("aeee4ccf-cbf8-473c-9d9f-846643afbf11")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F7-IUDs
     * @return CohortIndicator
     */
    public CohortIndicator iudFamilyPlanningUsers() {
        return cohortIndicator("Oral: IUDs", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConceptList("dcb2f595-30ab-102d-86b0-7a5022ba4115,fed07c37-7bb6-4baa-adf9-596ce4c4e93c,dd4c3016-13cf-458a-8e93-fe54460be667")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F8-Injectable
     * @return CohortIndicator
     */
    public CohortIndicator injectableFamilyPlanningUsers() {
        return cohortIndicator("Oral: Injectable", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("dcb30ba3-30ab-102d-86b0-7a5022ba4115")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F9-Natural
     * @return CohortIndicator
     */
    public CohortIndicator naturalFamilyPlanningUsers() {
        return cohortIndicator("Oral: Injectable", map(cclibrary.hasObs(Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),Dictionary.getConcept("dcb30381-30ab-102d-86b0-7a5022ba4115")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F10-Other methods
     * @return CohortIndicator
     */
    public CohortIndicator otherFamilyPlanningUsers() {
        return cohortIndicator("Oral: Other Method", map(cclibrary.hasObs(
        	Dictionary.getConcept(Metadata.Concept.FAMILY_PLANNING_METHOD),
        	Dictionary.getConceptList("5622AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA,"
        		+ "dcb2fba9-30ab-102d-86b0-7a5022ba4115,"
        		+ "dcdd8d8d-30ab-102d-86b0-7a5022ba4115,"
        		+ "bb83fd9d-24c5-4d49-89c0-97e13c792aaf,"
        		+ "dcdd91a7-30ab-102d-86b0-7a5022ba4115")), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *Total family planning users
     * @return CohortIndicator
     */
    public CohortIndicator allFamilyPlanningUsers() {
        return cohortIndicator("Total family planning users", map(cohortLibrary.allFamilyPlanningUsers(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     *F11: Number HIV+ FP users
     * @return CohortIndicator
     */
    public CohortIndicator hivPositiveFamilyPlanningUsers() {
        return cohortIndicator("Total family planning users", map(cohortLibrary.hivPositiveFamilyPlanningUsers(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    //End Family planning section
 
    //Begin HIV/AIDS counseling and testing (HCT)
    
    /**
     * H1-Number of Individuals counseled
     * @return CohortIndicator
     */
    public CohortIndicator individualsCounselled() {
        return cohortIndicator("Number of Individuals counseled", map(cohortLibrary.counseledAsIndividuals(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * H2-Number of Individuals tested
     * @return CohortIndicator
     */
    public CohortIndicator individualsTested() {
        return cohortIndicator("Number of Individuals tested", map(cohortLibrary.individualsTested(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    /**
     * H3-Number of Individuals who received HIV test results
     * @return CohortIndicator
     */
    public CohortIndicator individualsWhoReceivedHIVTestResults() {
        return cohortIndicator("Number of Individuals who received HIV test results", map(cohortLibrary.individualsWhoReceivedHIVTestResults(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }

    /**
     * H4- Number of individuals who received HIV results in the last 12months
     * @return CohortIndicator
     */
    public CohortIndicator individualsWhoReceivedHIVTestResultsInLast12Months() {
        return cohortIndicator("Number of Individuals who received HIV test results in last 12 Months", map(cohortLibrary.individualsWhoReceivedHIVTestResultsInLast12Months(), "onOrAfter=${startDate},onOrBefore=${endDate}"));
    }
    
    
    
    //End HIV/AIDS counseling and testing (HCT)        
}
