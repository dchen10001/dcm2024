package commoncom.nice.dcm.simulation.core.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nice.dcm.simulation.core.SequenceGenerator;
import com.nice.dcm.simulation.core.entity.Contact;
import com.nice.dcm.simulation.core.entity.ContactImpl;
import com.nice.dcm.simulation.core.entity.Employee;
import com.nice.dcm.simulation.core.entity.EmployeeImpl;
import com.nice.dcm.simulation.core.event.EventType;
import com.nice.dcm.simulation.core.event.TimeEventImpl;

class TimeEventImplTest {
	
	@BeforeEach
	void setUp() {
		SequenceGenerator.reSetAllIds();
	}
	
	@Test
	void idTest() {
		Employee employee1 = new EmployeeImpl("emp1");
		Assertions.assertEquals(0l, employee1.getId());
		Employee employee2 = new EmployeeImpl("emp2");
		Assertions.assertEquals(1l, employee2.getId());
		
		Contact contact1 = new ContactImpl(1, "ct1", 100, 500);
		Assertions.assertEquals(0l, contact1.getId());
		
		Contact contact2 = new ContactImpl(1, "ct2", 101, 500);
		Assertions.assertEquals(1l, contact2.getId());
		
		TimeEventImpl<Employee> employeeEvent1 = new TimeEventImpl<>(1000, 
				EventType.AGENT_ACTIVITY_CHANGED, employee1);
		
		Assertions.assertEquals(0l, employeeEvent1.getId());
		
		TimeEventImpl<Employee> employeeEvent2 = new TimeEventImpl<>(1000, 
				EventType.AGENT_ACTIVITY_CHANGED, employee1);

		Assertions.assertEquals(1l, employeeEvent2.getId());
		
		TimeEventImpl<Contact> contactEvent1 = new TimeEventImpl<>(1000, 
				EventType.CONTACT_ARRIVAL, contact1);
		
		Assertions.assertEquals(2l, contactEvent1.getId());
		TimeEventImpl<Contact> contactEvent2 = new TimeEventImpl<>(1000, 
				EventType.CONTACT_ARRIVAL, contact2);
		
		Assertions.assertEquals(3l, contactEvent2.getId());
	}
}
