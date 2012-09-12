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

    void test_Persistence() {
        assert Publisher.count() == 0
        assert Book.count() == 0

        def publisher = new Publisher(name: 'Test Publisher')
        def book = new Book(title: 'Test Title', author: 'Test Author', price: 3000, releaseDate: new Date(), isbn13: '1234567890123')
        publisher.addToBooks(book).save()

        assert Publisher.count() == 1
        assert Book.count() == 1

        publisher.delete()

        assert Publisher.count() == 0
        assert Book.count() == 0
    }
}
