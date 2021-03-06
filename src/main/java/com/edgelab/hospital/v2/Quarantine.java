package com.edgelab.hospital.v2;


import com.edgelab.hospital.v2.Medicines.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Created by Chaklader on 2/17/17.
 */
public class Quarantine {

    private Treatment treatment = new Treatment(None.getInstance());
    private List<Patients> patients = new ArrayList<>(5);

    public Quarantine(String patients) {

        Classifier classifier = new Classifier(patients.toUpperCase());
        this.patients = Arrays.asList(
                new Patients(HealthStatus.Tuberculosis, classifier.getNumberOf("T")),
                new Patients(HealthStatus.Diabetes, classifier.getNumberOf("D")),
                new Patients(HealthStatus.Healthy, classifier.getNumberOf("H")),
                new Patients(HealthStatus.Fever, classifier.getNumberOf("F")),

                /*initially, the number of death is zero*/
                new Patients(HealthStatus.Death, 0)
        );
    }

    public Quarantine(Patients... patients) {
        this.patients = Arrays.asList(patients);
    }

    public void wait40Days() {
        treatment.on(this);
    }

    public void aspirin() {
        distribute(Aspirin.getInstance());
    }

    public void antibiotic() {
        distribute(Antibiotic.getInstance());
    }

    public void insulin() {
        distribute(Insulin.getInstance());
    }

    public void paracetamol() {
        distribute(Paracetamol.getInstance());
    }

    public String report() {
        return String.format("F:%1$d H:%2$d D:%3$d T:%4$d X:%5$d",
                feverish().getSize(), healthy().getSize(), diabetics().getSize(), tuberculous().getSize(), death().getSize());
    }




    // additional functionality for the class 
    public Treatment getTreatment() {
        return treatment;
    }

    //  use the decorator design pattern
    // to distribute the medicines 
    private void distribute(IMedicine medicine) {
        treatment = medicine.combine(treatment);
    }

    public Patients healthy() {
        return get(HealthStatus.Healthy);
    }

    public Patients feverish() {
        return get(HealthStatus.Fever);
    }

    public Patients tuberculous() {
        return get(HealthStatus.Tuberculosis);
    }

    public Patients death() {
        return get(HealthStatus.Death);
    }

    public Patients diabetics() {
        return get(HealthStatus.Diabetes);
    }

    protected Patients get(HealthStatus healthStatus) {
        for (Patients patient : patients) {
            if (healthStatus.equals(patient.getHealthStatus())) {
                return patient;
            }
        }
        throw new NoSuchElementException("No group of patients with " + healthStatus);
    }

    protected Patients get(HealthStatus healthStatus, int size ) {
        for (Patients patient : patients) {
            if (healthStatus.equals(patient.getHealthStatus())) {
                return patient;
            }
        }

        throw new NoSuchElementException("No group of patients with " + healthStatus);
    }
}
