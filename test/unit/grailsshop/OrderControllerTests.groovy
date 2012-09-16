package grailsshop



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
//@TestFor(OrderController)
class OrderControllerTests extends grails.test.WebFlowTestCase {

    def flow
    def getFlow() {
        flow
    }
    def setFlowId(flow) {
        this.flow = flow
    }

    void testIndex() {
        def orderController = new OrderController()
        orderController.index()
        assert orderController.response.redirectedUrl == "/order/search"
    }
}
