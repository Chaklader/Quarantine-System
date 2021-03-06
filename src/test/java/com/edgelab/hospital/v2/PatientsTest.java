package com.edgelab.hospital.v2;

/**
 * Created by Chaklader on 2/18/17.
 */
import org.junit.Test;
//
import static org.junit.Assert.*;

/**
 * Created by Chaklader on 2/16/17.
 */
public class PatientsTest {

    @Test
    public void getSize_returns_the_group_size() throws Exception {
        Patients patients = new Patients(HealthStatus.Healthy, 5);
        assertEquals(5, patients.getSize());
    }

    @Test
    public void becomes_move_everyone_to_other() throws Exception {
        Patients from = new Patients(HealthStatus.Healthy, 5);
        Patients to = new Patients(HealthStatus.Healthy, 0);

        from.changeHealthStatus(to);
        assertEquals(0, from.getSize());
        assertEquals(5, to.getSize());
    }
}