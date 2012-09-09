package grailsshop

import static org.junit.Assert.*
import org.junit.*

class PublisherTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testValidation_nameがある場合はOK() {
        def publisher = new Publisher(name: '翔泳社')
        assert publisher.validate()
    }

    @Test
    void testValidation_nameがない場合はNG() {
        def publisher = new Publisher()
        assert !publisher.validate()
    }
}
